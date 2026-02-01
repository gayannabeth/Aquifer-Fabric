package gay.mountainspring.aquifer.loot.condition;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import gay.mountainspring.aquifer.loot.condition.predicate.number.LootNumberPredicate;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameter;
import net.minecraft.loot.provider.number.LootNumberProvider;
import net.minecraft.loot.provider.number.LootNumberProviderTypes;
import net.minecraft.registry.entry.RegistryEntry;

public record NumberComparisonLootCondition(RegistryEntry<LootNumberPredicate> predicate, List<LootNumberProvider> providers) implements LootCondition {
	public static final MapCodec<NumberComparisonLootCondition> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(
					LootNumberPredicate.ENTRY_CODEC.fieldOf("predicate").forGetter(NumberComparisonLootCondition::predicate),
					LootNumberProviderTypes.CODEC.listOf().fieldOf("values").forGetter(NumberComparisonLootCondition::providers)
					).apply(instance, NumberComparisonLootCondition::new));
	
	@Override
	public boolean test(LootContext context) {
		return this.predicate().value().test(context, this.predicate().value().createVariableMap(providers));
	}
	
	@Override
	public LootConditionType getType() {
		return AquiferLootConditionTypes.NUMBER_COMPARISON;
	}
	
	@Override
	public Set<LootContextParameter<?>> getRequiredParameters() {
		if (this.providers().size() == 1) return this.providers().get(0).getRequiredParameters();
		if (this.providers().size() == 2) return Sets.union(this.providers().get(0).getRequiredParameters(), this.providers().get(1).getRequiredParameters());
		
		Set<LootContextParameter<?>> set = Sets.newHashSet();
		
		for (LootNumberProvider provider : this.providers()) {
			set = Sets.union(set, provider.getRequiredParameters());
		}
		
		return set;
	}
	
	public static LootCondition.Builder builder(String exStr, LootNumberProvider... providers) {
		var list = List.of(providers);
		return builder(RegistryEntry.of(validate(exStr, list)), list);
	}
	
	public static LootCondition.Builder builder(String exStr, List<LootNumberProvider> providers) {
		return builder(RegistryEntry.of(validate(exStr, providers)), providers);
	}
	
	public static LootCondition.Builder builder(RegistryEntry<LootNumberPredicate> predicate, LootNumberProvider... providers) {
		return builder(predicate, List.of(providers));
	}
	
	public static LootCondition.Builder builder(RegistryEntry<LootNumberPredicate> predicate, List<LootNumberProvider> providers) {
		return () -> new NumberComparisonLootCondition(predicate, providers);
	}
	
	private static LootNumberPredicate validate(String exStr, List<LootNumberProvider> values) {
		return validate(LootNumberPredicate.create(exStr), values);
	}
	
	private static LootNumberPredicate validate(LootNumberPredicate pred, List<LootNumberProvider> values) {
		if (pred.variableCount() != values.size()) throw new IllegalArgumentException(String.format("Variable count mismatch! Found: {}, expected: {}", values.size(), pred.variableCount()));
		return pred;
	}
}