package gay.mountainspring.aquifer.loot.function;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import com.google.common.collect.Sets;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextAware;
import net.minecraft.loot.context.LootContextParameter;
import net.minecraft.loot.function.ConditionalLootFunction;
import net.minecraft.loot.function.LootFunctionType;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.LootNumberProvider;
import net.minecraft.loot.provider.number.LootNumberProviderTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;

public class SetCustomPotionEffectsLootFunction extends ConditionalLootFunction {
	public static final MapCodec<SetCustomPotionEffectsLootFunction> CODEC = RecordCodecBuilder.mapCodec(
		instance -> addConditionsField(instance)
		.<List<CustomStatusEffectSupplier>>and(
			CustomStatusEffectSupplier.CODEC.codec().listOf().fieldOf("effects").forGetter(function -> function.effects)
		).apply(instance, SetCustomPotionEffectsLootFunction::new)
	);
	
	private final List<CustomStatusEffectSupplier> effects;
	
	public SetCustomPotionEffectsLootFunction(List<LootCondition> conditions, List<CustomStatusEffectSupplier> effects) {
		super(conditions);
		this.effects = effects;
	}
	
	@Override
	public LootFunctionType<SetCustomPotionEffectsLootFunction> getType() {
		return AquiferLootFunctionTypes.SET_CUSTOM_POTION_EFFECTS;
	}
	
	@Override
	protected ItemStack process(ItemStack stack, LootContext context) {
		this.effects.forEach(effect -> stack.apply(DataComponentTypes.POTION_CONTENTS, PotionContentsComponent.DEFAULT, effect.apply(context), PotionContentsComponent::with));
		return stack;
	}
	
	@Override
	public Set<LootContextParameter<?>> getRequiredParameters() {
		var set = super.getRequiredParameters();
		
		for (var effect : this.effects) {
			set = Sets.union(set, effect.getRequiredParameters());
		}
		
		return set;
	}
	
	public static ConditionalLootFunction.Builder<?> builder(CustomStatusEffectSupplier.Builder... effects) {
		return builder2(Arrays.asList(effects));
	}
	
	public static ConditionalLootFunction.Builder<?> builder(CustomStatusEffectSupplier... effects) {
		return builder(Arrays.asList(effects));
	}
	
	public static ConditionalLootFunction.Builder<?> builder2(List<CustomStatusEffectSupplier.Builder> effects) {
		return builder(effects.stream().map(CustomStatusEffectSupplier.Builder::build).toList());
	}
	
	public static ConditionalLootFunction.Builder<?> builder(List<CustomStatusEffectSupplier> effects) {
		return builder(conditions -> new SetCustomPotionEffectsLootFunction(conditions, effects));
	}
	
	public static record CustomStatusEffectSupplier(RegistryEntry<StatusEffect> effect, LootNumberProvider durationProvider, LootNumberProvider amplifierProvider) implements LootContextAware, Function<LootContext, StatusEffectInstance> {
		public static final MapCodec<CustomStatusEffectSupplier> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(
				Registries.STATUS_EFFECT.getEntryCodec().fieldOf("effect").forGetter(CustomStatusEffectSupplier::effect),
				LootNumberProviderTypes.CODEC.optionalFieldOf("duration", ConstantLootNumberProvider.create(0.0f)).forGetter(CustomStatusEffectSupplier::durationProvider),
				LootNumberProviderTypes.CODEC.optionalFieldOf("amplifier", ConstantLootNumberProvider.create(0.0f)).forGetter(CustomStatusEffectSupplier::amplifierProvider)
			).apply(instance, CustomStatusEffectSupplier::new)
		);
		
		@Override
		public Set<LootContextParameter<?>> getRequiredParameters() {
			return Sets.union(this.durationProvider().getRequiredParameters(), this.amplifierProvider().getRequiredParameters());
		}
		
		@Override
		public StatusEffectInstance apply(LootContext context) {
			var duration = this.durationProvider().nextInt(context);
			var amplifier = this.amplifierProvider().nextInt(context);
			return new StatusEffectInstance(this.effect(), duration, amplifier);
		}
		
		public static Builder builder(RegistryEntry<StatusEffect> effect) {
			return new Builder(effect);
		}
		
		protected static final class Builder {
			private RegistryEntry<StatusEffect> effect;
			private LootNumberProvider duration;
			private LootNumberProvider amplifier;
			
			Builder(RegistryEntry<StatusEffect> effect) {
				this.effect = effect;
				this.duration = ConstantLootNumberProvider.create(0.0f);
				this.amplifier = ConstantLootNumberProvider.create(0.0f);
			}
			
			public Builder duration(LootNumberProvider provider) {
				this.duration = provider;
				return this;
			}
			
			public Builder amplifier(LootNumberProvider provider) {
				this.amplifier = provider;
				return this;
			}
			
			public CustomStatusEffectSupplier build() {
				return new CustomStatusEffectSupplier(this.effect, this.duration, this.amplifier);
			}
		}
	}
}