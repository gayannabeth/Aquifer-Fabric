package gay.mountainspring.aquifer.loot.function;

import java.util.List;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.function.ConditionalLootFunction;
import net.minecraft.loot.function.LootFunctionType;
import net.minecraft.util.DyeColor;

public class SetShieldColorLootFunction extends ConditionalLootFunction {
	public static final MapCodec<SetShieldColorLootFunction> CODEC = RecordCodecBuilder.mapCodec(
		instance -> addConditionsField(instance)
		.and(DyeColor.CODEC.fieldOf("color").forGetter(function -> function.color))
		.apply(instance, SetShieldColorLootFunction::new)
	);
	
	final DyeColor color;
	
	public SetShieldColorLootFunction(List<LootCondition> conditions, DyeColor color) {
		super(conditions);
		this.color = color;
	}
	
	@Override
	public LootFunctionType<SetShieldColorLootFunction> getType() {
		return AquiferLootFunctionTypes.SET_SHIELD_COLOR;
	}
	
	@Override
	protected ItemStack process(ItemStack stack, LootContext context) {
		stack.set(DataComponentTypes.BASE_COLOR, this.color);
		return stack;
	}
	
	public static ConditionalLootFunction.Builder<?> builder(DyeColor color) {
		return builder(conditions -> new SetShieldColorLootFunction(conditions, color));
	}
}