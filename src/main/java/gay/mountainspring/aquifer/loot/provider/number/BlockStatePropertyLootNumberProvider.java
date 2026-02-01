package gay.mountainspring.aquifer.loot.provider.number;

import java.util.Optional;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameter;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.provider.number.LootNumberProvider;
import net.minecraft.loot.provider.number.LootNumberProviderType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Property;

public record BlockStatePropertyLootNumberProvider(RegistryEntry<Block> block, Property<?> property, Optional<Float> fallback) implements LootNumberProvider {
	public static final MapCodec<BlockStatePropertyLootNumberProvider> CODEC = RecordCodecBuilder.<BlockStatePropertyLootNumberProvider>mapCodec(
		instance -> instance.group(
			Registries.BLOCK.getEntryCodec().fieldOf("block").forGetter(BlockStatePropertyLootNumberProvider::block),
			Codec.STRING.fieldOf("property").forGetter((provider) -> provider.property().getName()),
			Codec.FLOAT.optionalFieldOf("fallback").forGetter(BlockStatePropertyLootNumberProvider::fallback)
		).apply(instance, BlockStatePropertyLootNumberProvider::create)
	);
	
	public static BlockStatePropertyLootNumberProvider create(RegistryEntry<Block> block, Property<?> property, Optional<Float> fallback) {
		if (!block.value().getStateManager().getProperties().contains(property)) throw new IllegalArgumentException(block.getIdAsString() + " does not contain the property " + property.getName());
		
		return new BlockStatePropertyLootNumberProvider(block, property, fallback);
	}
	
	public static BlockStatePropertyLootNumberProvider create(RegistryEntry<Block> block, Property<?> property) {
		return create(block, property, Optional.of(0.0f));
	}
	
	@SuppressWarnings("deprecation")
	public static BlockStatePropertyLootNumberProvider create(Block block, Property<?> property, Optional<Float> fallback) {
		return create(block.getRegistryEntry(), property, fallback);
	}
	
	public static BlockStatePropertyLootNumberProvider create(Block block, Property<?> property) {
		return create(block, property, Optional.of(0.0f));
	}
	
	public static BlockStatePropertyLootNumberProvider create(RegistryEntry<Block> block, String property, Optional<Float> fallback) {
		Property<?> prop = block.value().getStateManager().getProperty(property);
		return create(block, prop, fallback);
	}
	
	public static BlockStatePropertyLootNumberProvider create(RegistryEntry<Block> block, String property) {
		return create(block, property, Optional.of(0.0f));
	}
	
	@SuppressWarnings("deprecation")
	public static BlockStatePropertyLootNumberProvider create(Block block, String property, Optional<Float> fallback) {
		return create(block.getRegistryEntry(), property, fallback);
	}
	
	public static BlockStatePropertyLootNumberProvider create(Block block, String property) {
		return create(block, property, Optional.of(0.0f));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public float nextFloat(LootContext context) {
		BlockState state = context.get(LootContextParameters.BLOCK_STATE);
		
		if (state == null || !state.isOf(this.block())) return this.fallback().orElse(0.0f);
		
		if (this.property() instanceof BooleanProperty booleanProperty) {
			return state.get(booleanProperty) ? 1.0f : 0.0f;
		} else if (this.property() instanceof EnumProperty enumProperty) {
			return ((Enum<?>) state.get(enumProperty)).ordinal();
		} else if (this.property() instanceof IntProperty intProperty) {
			return state.get(intProperty).floatValue();
		} else {
			return this.fallback().orElse(0.0f);
		}
	}
	
	@Override
	public LootNumberProviderType getType() {
		return AquiferLootNumberProviderTypes.BLOCK_STATE_PROPERTY;
	}
	
	@Override
	public Set<LootContextParameter<?>> getRequiredParameters() {
		return ImmutableSet.of(LootContextParameters.BLOCK_STATE);
	}
}