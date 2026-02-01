package gay.mountainspring.aquifer.loot.condition.predicate.number;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.ezylang.evalex.EvaluationException;
import com.ezylang.evalex.Expression;
import com.ezylang.evalex.config.ExpressionConfiguration;
import com.ezylang.evalex.data.EvaluationValue;
import com.ezylang.evalex.data.EvaluationValue.DataType;
import com.ezylang.evalex.parser.ParseException;
import com.google.common.collect.Maps;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import gay.mountainspring.aquifer.loot.provider.number.expression.LootNumberExpression;
import gay.mountainspring.aquifer.registry.AquiferRegistryKeys;
import gay.mountainspring.aquifer.util.expression.ExpressionUtil;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.provider.number.LootNumberProvider;
import net.minecraft.registry.entry.RegistryElementCodec;
import net.minecraft.registry.entry.RegistryEntry;

public record LootNumberPredicate(Expression expression, int variableCount) implements BiPredicate<LootContext, Map<String, LootNumberProvider>> {
	public static final Codec<Expression> EXPRESSION_CODEC = LootNumberExpression.EXPRESSION_CODEC;
	public static final Codec<LootNumberPredicate> EXPRESSION_INLINE_CODEC = EXPRESSION_CODEC.xmap(LootNumberPredicate::create, LootNumberPredicate::expression);
	public static final MapCodec<LootNumberPredicate> BASE_CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(
					EXPRESSION_CODEC.fieldOf("expression").forGetter(LootNumberPredicate::expression),
					Codec.INT.fieldOf("variable_count").forGetter(LootNumberPredicate::variableCount))
			.apply(instance, LootNumberPredicate::new));
	public static final Codec<LootNumberPredicate> CODEC = Codec.lazyInitialized(() -> Codec.withAlternative(BASE_CODEC.codec(), EXPRESSION_INLINE_CODEC));
	public static final Codec<RegistryEntry<LootNumberPredicate>> ENTRY_CODEC = RegistryElementCodec.of(AquiferRegistryKeys.LOOT_NUMBER_PREDICATE, CODEC);
	
	public LootNumberPredicate(Expression expression, int variableCount) {
		this.expression = convertExpression(validate(expression, variableCount));
		this.variableCount = variableCount;
	}
	
	public static LootNumberPredicate create(String exStr) {
		return create(createExpression(exStr));
	}
	
	public static LootNumberPredicate create(Expression ex) {
		try {
			return new LootNumberPredicate(ex, ex.getUndefinedVariables().size());
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	@Override
	public boolean test(LootContext context, Map<String, LootNumberProvider> variables) {
		if (this.variableCount() != variables.size())
			throw new IllegalArgumentException(String.format("Incorrect number of variables: {}! Must be {} instead!", variables.size(), this.variableCount()));
		
		boolean result = false;
		
		try {
			result = this
					.expression()
					.copy()
					.withValues(variables
							.keySet()
							.stream()
							.collect(Collectors.toMap(
									Function.identity(),
									key -> variables
											.get(key)
											.nextFloat(context))))
					.evaluate()
					.getBooleanValue();
		} catch (EvaluationException | ParseException exception) {
			exception.printStackTrace();
		}
		return result;
	}
	
	public Map<String, LootNumberProvider> createVariableMap(List<LootNumberProvider> providers) {
		if (providers.size() != this.variableCount())
			throw new IllegalArgumentException(String.format("Cannot create variable map because {} is the wrong number of number providers! Must be {} instead", providers.size(), this.variableCount()));
		
		Map<String, LootNumberProvider> map = Maps.newHashMap();
		
		try {
			List<String> variables = new ArrayList<>(this.expression().getUndefinedVariables());
			Collections.sort(variables);
			for (int i = 0; i < this.variableCount(); i++) {
				map.put(variables.get(i), providers.get(i));
			}
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
		
		return map;
	}
	
	private static Expression validate(Expression expression, int variableCount) {
		try {
			var variables = expression.getUndefinedVariables();
			if (variables.size() != variableCount) throw new IllegalArgumentException(String.format("Variable count mismatch! Found: {}, expected: {}", variables.size(), variableCount));
			
			Expression copy = expression.copy();
			
			for (String variable : variables) {
				copy = copy.with(variable, 1);
			}
			
			EvaluationValue value = copy.evaluate();
			
			if (!value.isBooleanValue()) {
				throw new IllegalArgumentException(String.format("Expected result of type {}, found {} instead", DataType.BOOLEAN, value.getDataType()));
			}
		} catch (ParseException | EvaluationException e) {
			throw new IllegalArgumentException(e);
		}
		return expression;
	}
	
	public static Expression createExpression(String exStr) {
		return new Expression(exStr, createExpressionConfig());
	}
	
	private static Expression convertExpression(Expression ex) {
		return new Expression(ex.getExpressionString(), createExpressionConfig());
	}
	
	private static ExpressionConfiguration createExpressionConfig() {
		return ExpressionConfiguration.defaultConfiguration()
				.withAdditionalOperators(ExpressionUtil.getOperators())
				.withAdditionalFunctions(ExpressionUtil.getFunctions());
	}
}