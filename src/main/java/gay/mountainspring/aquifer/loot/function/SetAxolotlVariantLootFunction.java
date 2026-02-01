package gay.mountainspring.aquifer.loot.function;

import java.util.List;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.function.ConditionalLootFunction;
import net.minecraft.loot.function.LootFunctionType;
import net.minecraft.nbt.NbtCompound;

public class SetAxolotlVariantLootFunction extends ConditionalLootFunction {
	public static final MapCodec<SetAxolotlVariantLootFunction> CODEC = RecordCodecBuilder.mapCodec(
		instance -> addConditionsField(instance)
		.<AxolotlEntity.Variant>and(
			AxolotlEntity.Variant.CODEC.fieldOf("variant").forGetter(function -> function.variant)
		).apply(instance, SetAxolotlVariantLootFunction::new)
	);
	
	final AxolotlEntity.Variant variant;
	
	public SetAxolotlVariantLootFunction(List<LootCondition> conditions, AxolotlEntity.Variant variant) {
		super(conditions);
		this.variant = variant;
	}
	
	@Override
	public LootFunctionType<SetAxolotlVariantLootFunction> getType() {
		return AquiferLootFunctionTypes.SET_AXOLOTL_VARIANT;
	}
	
	@Override
	protected ItemStack process(ItemStack stack, LootContext context) {
		NbtCompound nbt = stack.get(DataComponentTypes.BUCKET_ENTITY_DATA).copyNbt();
		if (!nbt.contains("Heath")) nbt.putFloat("Health", 14.0f);
		if (!nbt.contains("Age")) nbt.putInt("Age", 0);
		nbt.putInt("Variant", this.variant.getId());
		stack.set(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.of(nbt));
		return stack;
	}
	
	public static ConditionalLootFunction.Builder<?> builder(AxolotlEntity.Variant variant) {
		return builder(conditions -> new SetAxolotlVariantLootFunction(conditions, variant));
	}
}