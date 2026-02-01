package gay.mountainspring.aquifer.loot.function;

import java.util.List;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.UnbreakableComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.function.ConditionalLootFunction;
import net.minecraft.loot.function.LootFunctionType;

public class SetUnbreakableLootFunction extends ConditionalLootFunction {
	public static final MapCodec<SetUnbreakableLootFunction> CODEC = RecordCodecBuilder.mapCodec(
		instance -> addConditionsField(instance)
		.<Boolean>and(
			Codec.BOOL.optionalFieldOf("show_in_tooltip", true).forGetter(function -> function.showInTooltip)
		).apply(instance, SetUnbreakableLootFunction::new)
	);
	
	final boolean showInTooltip;
	
	public SetUnbreakableLootFunction(List<LootCondition> conditions, boolean showInTooltip) {
		super(conditions);
		this.showInTooltip = showInTooltip;
	}
	
	@Override
	public LootFunctionType<SetUnbreakableLootFunction> getType() {
		return AquiferLootFunctionTypes.SET_UNBREAKABLE;
	}
	
	@Override
	protected ItemStack process(ItemStack stack, LootContext context) {
		stack.apply(DataComponentTypes.UNBREAKABLE, new UnbreakableComponent(true), this.showInTooltip, UnbreakableComponent::withShowInTooltip);
		return stack;
	}
	
	public static ConditionalLootFunction.Builder<?> builder() {
		return builder(true);
	}
	
	public static ConditionalLootFunction.Builder<?> builder(boolean showInTooltip) {
		return builder(conditions -> new SetUnbreakableLootFunction(conditions, showInTooltip));
	}
}