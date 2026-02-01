package gay.mountainspring.aquifer.loot.function;

import java.util.List;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.passive.TropicalFishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.function.ConditionalLootFunction;
import net.minecraft.loot.function.LootFunctionType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.DyeColor;

public class SetTropicalFishVariantLootFunction extends ConditionalLootFunction {
	public static final MapCodec<SetTropicalFishVariantLootFunction> CODEC = RecordCodecBuilder.mapCodec(
		instance -> addConditionsField(instance)
		.<TropicalFishEntity.Variety, DyeColor, DyeColor>and(
			instance.group(
				TropicalFishEntity.Variety.CODEC.fieldOf("variety").forGetter(function -> function.variety),
				DyeColor.CODEC.fieldOf("base_color").forGetter(function -> function.baseColor),
				DyeColor.CODEC.fieldOf("pattern_color").forGetter(function -> function.patternColor)
			)
		).apply(instance, SetTropicalFishVariantLootFunction::new)
	);
	
	final TropicalFishEntity.Variety variety;
	final DyeColor baseColor;
	final DyeColor patternColor;
	
	public SetTropicalFishVariantLootFunction(List<LootCondition> conditions, TropicalFishEntity.Variety variety, DyeColor base, DyeColor pattern) {
		super(conditions);
		this.variety = variety;
		this.baseColor = base;
		this.patternColor = pattern;
	}
	
	@Override
	public LootFunctionType<SetTropicalFishVariantLootFunction> getType() {
		return AquiferLootFunctionTypes.SET_TROPICAL_FISH_VARIANT;
	}
	
	@Override
	protected ItemStack process(ItemStack stack, LootContext context) {
		TropicalFishEntity.Variant variant = new TropicalFishEntity.Variant(this.variety, this.baseColor, this.patternColor);
		NbtCompound nbt = stack.get(DataComponentTypes.BUCKET_ENTITY_DATA).copyNbt();
		if (!nbt.contains("Health")) nbt.putFloat("Health", 3.0f);
		nbt.putInt("Variant", variant.getId());
		stack.set(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.of(nbt));
		return stack;
	}
	
	public static ConditionalLootFunction.Builder<?> builder(TropicalFishEntity.Variant variant) {
		return builder(variant.variety(), variant.baseColor(), variant.patternColor());
	}
	
	public static ConditionalLootFunction.Builder<?> builder(TropicalFishEntity.Variety variety, DyeColor baseColor, DyeColor patternColor) {
		return builder(conditions -> new SetTropicalFishVariantLootFunction(conditions, variety, baseColor, patternColor));
	}
}