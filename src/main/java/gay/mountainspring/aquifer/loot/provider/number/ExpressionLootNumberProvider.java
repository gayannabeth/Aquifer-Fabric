package gay.mountainspring.aquifer.loot.provider.number;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import gay.mountainspring.aquifer.loot.provider.number.expression.LootNumberExpression;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameter;
import net.minecraft.loot.provider.number.LootNumberProvider;
import net.minecraft.loot.provider.number.LootNumberProviderType;
import net.minecraft.loot.provider.number.LootNumberProviderTypes;
import net.minecraft.registry.entry.RegistryEntry;

public record ExpressionLootNumberProvider(RegistryEntry<LootNumberExpression> expression, List<LootNumberProvider> subProviders) implements LootNumberProvider {
	public static final MapCodec<ExpressionLootNumberProvider> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(
					LootNumberExpression.ENTRY_CODEC.fieldOf("expression").forGetter(ExpressionLootNumberProvider::expression),
					LootNumberProviderTypes.CODEC.listOf().fieldOf("values").forGetter(ExpressionLootNumberProvider::subProviders)
					).apply(instance, ExpressionLootNumberProvider::new));
	
	@Override
	public float nextFloat(LootContext context) {
		LootNumberExpression ex = this.expression().value();
		return ex.applyAsFloat(context, ex.createVariableMap(this.subProviders()));
	}
	
	@Override
	public LootNumberProviderType getType() {
		return AquiferLootNumberProviderTypes.EXPRESSION;
	}
	
	public static ExpressionLootNumberProvider create(String exStr, LootNumberProvider... values) {
		List<LootNumberProvider> valueList = Arrays.asList(values);
		return new ExpressionLootNumberProvider(RegistryEntry.of(validate(exStr, valueList)), valueList);
	}
	
	public static ExpressionLootNumberProvider create(RegistryEntry<LootNumberExpression> ex, LootNumberProvider... values) {
		return new ExpressionLootNumberProvider(ex, Arrays.asList(values));
	}
	
	private static LootNumberExpression validate(String exStr, List<LootNumberProvider> values) {
		return validate(LootNumberExpression.create(exStr), values);
	}
	
	private static LootNumberExpression validate(LootNumberExpression ex, List<LootNumberProvider> values) {
		if (ex.variableCount() != values.size()) throw new IllegalArgumentException(String.format("Variable count mismatch! Found: {}, expected: {}", values.size(), ex.variableCount()));
		return ex;
	}
	
	@Override
	public Set<LootContextParameter<?>> getRequiredParameters() {
		if (this.subProviders().size() == 1) return this.subProviders().get(0).getRequiredParameters();
		if (this.subProviders().size() == 2) return Sets.union(this.subProviders().get(0).getRequiredParameters(), this.subProviders().get(1).getRequiredParameters());
		
		Set<LootContextParameter<?>> set = Sets.newHashSet();
		
		for (LootNumberProvider provider : this.subProviders()) {
			set = Sets.union(set, provider.getRequiredParameters());
		}
		
		return set;
	}
}