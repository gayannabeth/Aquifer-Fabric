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
import com.ezylang.evalex.parser.ParseException;
import com.google.common.collect.Maps;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import gay.mountainspring.aquifer.loot.provider.number.expression.LootNumberExpression;
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
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.provider.number.LootNumberProvider;
import net.minecraft.registry.entry.RegistryElementCodec;
import net.minecraft.registry.entry.RegistryEntry;

public record LootNumberPredicate(Expression expression, int variableCount) implements BiPredicate<LootContext, Map<String, LootNumberProvider>> {
	public static final Codec<Expression> EXPRESSION_CODEC = LootNumberExpression.EXPRESSION_CODEC;
	public static final Codec<LootNumberPredicate> EXPRESSION_INLINE_CODEC = EXPRESSION_CODEC.xmap(LootNumberPredicate::create, LootNumberPredicate::expression);
	public static final MapCodec<LootNumberPredicate> BASE_CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(
					EXPRESSION_CODEC.fieldOf("predicate").forGetter(LootNumberPredicate::expression),
					Codec.INT.fieldOf("variable_count").forGetter(LootNumberPredicate::variableCount))
			.apply(instance, LootNumberPredicate::new));
	public static final Codec<LootNumberPredicate> CODEC = Codec.lazyInitialized(() -> Codec.withAlternative(BASE_CODEC.codec(), EXPRESSION_INLINE_CODEC));
	public static final Codec<RegistryEntry<LootNumberPredicate>> ENTRY_CODEC = RegistryElementCodec.of(AquiferRegistryKeys.LOOT_NUMBER_PREDICATE, CODEC);
	
	public LootNumberPredicate(Expression expression, int variableCount) {
		this.expression = convertExpression(expression);
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