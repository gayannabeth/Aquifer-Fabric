package gay.mountainspring.aquifer.loot.provider.number.expression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.ezylang.evalex.EvaluationException;
import com.ezylang.evalex.Expression;
import com.ezylang.evalex.config.ExpressionConfiguration;
import com.ezylang.evalex.parser.ParseException;
import com.google.common.collect.Maps;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import gay.mountainspring.aquifer.registry.AquiferRegistryKeys;
import gay.mountainspring.aquifer.util.expression.ACscFunction;
import gay.mountainspring.aquifer.util.expression.ACscHFunction;
import gay.mountainspring.aquifer.util.expression.ACscRFunction;
import gay.mountainspring.aquifer.util.expression.ASecFunction;
import gay.mountainspring.aquifer.util.expression.ASecHFunction;
import gay.mountainspring.aquifer.util.expression.ASecRFunction;
import gay.mountainspring.aquifer.util.expression.CbrtFunction;
import gay.mountainspring.aquifer.util.expression.InfixXOrOperator;
import gay.mountainspring.aquifer.util.expression.InvSqrtFunction;
import gay.mountainspring.aquifer.util.expression.PostfixFactorialOperator;
import gay.mountainspring.aquifer.util.function.ToFloatBiFunction;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.provider.number.LootNumberProvider;
import net.minecraft.registry.entry.RegistryElementCodec;
import net.minecraft.registry.entry.RegistryEntry;

public record LootNumberExpression(Expression expression, int variableCount) implements ToFloatBiFunction<LootContext, Map<String, LootNumberProvider>> {
	public static final Codec<Expression> EXPRESSION_CODEC = Codec.stringResolver(Expression::getExpressionString, LootNumberExpression::createExpression);
	public static final Codec<LootNumberExpression> EXPRESSION_INLINE_CODEC = EXPRESSION_CODEC.xmap(LootNumberExpression::create, LootNumberExpression::expression);
	public static final MapCodec<LootNumberExpression> BASE_CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(
					EXPRESSION_CODEC.fieldOf("expression").forGetter(LootNumberExpression::expression),
					Codec.INT.fieldOf("variable_count").forGetter(LootNumberExpression::variableCount))
			.apply(instance, LootNumberExpression::new));
	public static final Codec<LootNumberExpression> CODEC = Codec.lazyInitialized(() -> Codec.withAlternative(BASE_CODEC.codec(), EXPRESSION_INLINE_CODEC));
	public static final Codec<RegistryEntry<LootNumberExpression>> ENTRY_CODEC = RegistryElementCodec.of(AquiferRegistryKeys.LOOT_NUMBER_EXPRESSION, CODEC);
	
	public LootNumberExpression(Expression expression, int variableCount) {
		this.expression = convertExpression(expression);
		this.variableCount = variableCount;
	}
	
	public static LootNumberExpression create(String exStr) {
		return create(createExpression(exStr));
	}
	
	public static LootNumberExpression create(Expression ex) {
		try {
			return new LootNumberExpression(ex, ex.getUndefinedVariables().size());
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	@Override
	public float applyAsFloat(LootContext context, Map<String, LootNumberProvider> variables) {
		if (this.variableCount() != variables.size())
			throw new IllegalArgumentException(String.format("Incorrect number of variables: {}! Must be {} instead!", variables.size(), this.variableCount()));
		
		float result = 0.0f;
		
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
					.getNumberValue()
					.floatValue();
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
	
	public static Expression createExpression(String exStr) {
		return new Expression(exStr, createExpressionConfig());
	}
	
	private static Expression convertExpression(Expression ex) {
		return new Expression(ex.getExpressionString(), createExpressionConfig());
	}
	
	private static ExpressionConfiguration createExpressionConfig() {
		return ExpressionConfiguration.defaultConfiguration()
				.withAdditionalOperators(Map.entry("!", new PostfixFactorialOperator()),
						Map.entry("^^", new InfixXOrOperator()))
				.withAdditionalFunctions(Map.entry("ASEC", new ASecFunction()),
						Map.entry("ASECH", new ASecHFunction()),
						Map.entry("ASECR", new ASecRFunction()),
						Map.entry("ACSC", new ACscFunction()),
						Map.entry("ACSCH", new ACscHFunction()),
						Map.entry("ACSCR", new ACscRFunction()),
						Map.entry("CBRT", new CbrtFunction()),
						Map.entry("INV_SQRT", new InvSqrtFunction()));
	}
}