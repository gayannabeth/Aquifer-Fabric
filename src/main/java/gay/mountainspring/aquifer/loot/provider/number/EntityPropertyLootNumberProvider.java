package gay.mountainspring.aquifer.loot.provider.number;

import java.util.Optional;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.entity.Entity;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameter;
import net.minecraft.loot.provider.number.LootNumberProvider;
import net.minecraft.loot.provider.number.LootNumberProviderType;
import net.minecraft.nbt.AbstractNbtNumber;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.predicate.NbtPredicate;

public record EntityPropertyLootNumberProvider(LootContext.EntityTarget entity, String property, Optional<Float> fallback) implements LootNumberProvider {
	public static final MapCodec<EntityPropertyLootNumberProvider> CODEC = RecordCodecBuilder.<EntityPropertyLootNumberProvider>mapCodec(
		instance -> instance.group(
			LootContext.EntityTarget.CODEC.fieldOf("entity").forGetter(EntityPropertyLootNumberProvider::entity),
			Codec.STRING.fieldOf("property").forGetter(EntityPropertyLootNumberProvider::property),
			Codec.FLOAT.optionalFieldOf("fallback").forGetter(EntityPropertyLootNumberProvider::fallback)
		).apply(instance, EntityPropertyLootNumberProvider::create)
	);
	
	public static EntityPropertyLootNumberProvider create(LootContext.EntityTarget entity, String property, Optional<Float> fallback) {
		return new EntityPropertyLootNumberProvider(entity, property, fallback);
	}
	
	public static EntityPropertyLootNumberProvider create(LootContext.EntityTarget entity, String property, float fallback) {
		return create(entity, property, Optional.of(fallback));
	}
	
	public static EntityPropertyLootNumberProvider create(LootContext.EntityTarget entity, String property) {
		return create(entity, property, 0.0f);
	}
	
	@Override
	public float nextFloat(LootContext context) {
		Entity entity = context.get(this.entity.getParameter());
		
		if (entity == null) return this.fallback().get();
		
		NbtCompound nbt = NbtPredicate.entityToNbt(entity);
		
		if (nbt.contains(this.property())) {
			NbtElement element = nbt.get(this.property());
			
			if (element instanceof AbstractNbtNumber nbtNumber) {
				return nbtNumber.floatValue();
			}
		}
		
		return this.fallback().get();
	}
	
	@Override
	public LootNumberProviderType getType() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Set<LootContextParameter<?>> getRequiredParameters() {
		return ImmutableSet.of(this.entity().getParameter());
	}
}