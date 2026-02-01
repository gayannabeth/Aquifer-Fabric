package gay.mountainspring.aquifer.loot.function;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameter;
import net.minecraft.loot.function.ConditionalLootFunction;
import net.minecraft.loot.function.LootFunctionType;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.LootNumberProvider;
import net.minecraft.loot.provider.number.LootNumberProviderTypes;

public class SetDyedColorLootFunction extends ConditionalLootFunction {
	public static final MapCodec<SetDyedColorLootFunction> CODEC = RecordCodecBuilder.mapCodec(
		instance -> addConditionsField(instance)
		.<LootNumberProvider, LootNumberProvider, LootNumberProvider, ColorOverflowHandler, Boolean>and(
			instance.group(
				LootNumberProviderTypes.CODEC.optionalFieldOf("r", ConstantLootNumberProvider.create(0.0f)).forGetter(function -> function.red),
				LootNumberProviderTypes.CODEC.optionalFieldOf("g", ConstantLootNumberProvider.create(0.0f)).forGetter(function -> function.green),
				LootNumberProviderTypes.CODEC.optionalFieldOf("b", ConstantLootNumberProvider.create(0.0f)).forGetter(function -> function.blue),
				ColorOverflowHandler.CODEC.optionalFieldOf("color_overflow_handler", ColorOverflowHandler.CLAMP).forGetter(function -> function.colorOverflowHandler),
				Codec.BOOL.optionalFieldOf("show_in_tooltip", true).forGetter(function -> function.showInTooltip)
			)
		).apply(instance, SetDyedColorLootFunction::new)
	);
	
	final LootNumberProvider red;
	final LootNumberProvider green;
	final LootNumberProvider blue;
	final ColorOverflowHandler colorOverflowHandler;
	final boolean showInTooltip;
	
	public SetDyedColorLootFunction(List<LootCondition> conditions, LootNumberProvider r, LootNumberProvider g, LootNumberProvider b, ColorOverflowHandler handler, boolean showInTooltip) {
		super(conditions);
		this.red = r;
		this.green = g;
		this.blue = b;
		this.colorOverflowHandler = handler;
		this.showInTooltip = showInTooltip;
	}
	
	@Override
	public LootFunctionType<SetDyedColorLootFunction> getType() {
		return AquiferLootFunctionTypes.SET_DYED_COLOR;
	}
	
	@Override
	protected ItemStack process(ItemStack stack, LootContext context) {
		int color = (this.colorOverflowHandler.apply(this.red.nextInt(context)) << 16) + (this.colorOverflowHandler.apply(this.green.nextInt(context)) << 8) + this.colorOverflowHandler.apply(this.blue.nextInt(context));
		DyedColorComponent component = new DyedColorComponent(color, this.showInTooltip);
		stack.set(DataComponentTypes.DYED_COLOR, component);
		return stack;
	}
	
	@Override
	public Set<LootContextParameter<?>> getRequiredParameters() {
		return Sets.union(this.red.getRequiredParameters(), Sets.union(this.green.getRequiredParameters(), this.blue.getRequiredParameters()));
	}
	
	public static ConditionalLootFunction.Builder<?> builder(float red, float green, float blue) {
		return builder(red, green, blue, ColorOverflowHandler.CLAMP, true);
	}
	
	public static ConditionalLootFunction.Builder<?> builder(LootNumberProvider red, LootNumberProvider green, LootNumberProvider blue) {
		return builder(red, green, blue, ColorOverflowHandler.CLAMP, true);
	}
	
	public static ConditionalLootFunction.Builder<?> builder(float red, float green, float blue, boolean showInTooltip) {
		return builder(red, green, blue, ColorOverflowHandler.CLAMP, showInTooltip);
	}
	
	public static ConditionalLootFunction.Builder<?> builder(LootNumberProvider red, LootNumberProvider green, LootNumberProvider blue, boolean showInTooltip) {
		return builder(red, green, blue, ColorOverflowHandler.CLAMP, showInTooltip);
	}
	
	public static ConditionalLootFunction.Builder<?> builder(float red, float green, float blue, ColorOverflowHandler handler) {
		return builder(red, green, blue, handler, true);
	}
	
	public static ConditionalLootFunction.Builder<?> builder(LootNumberProvider red, LootNumberProvider green, LootNumberProvider blue, ColorOverflowHandler handler) {
		return builder(red, green, blue, handler, true);
	}
	
	public static ConditionalLootFunction.Builder<?> builder(float red, float green, float blue, ColorOverflowHandler handler, boolean showInTooltip) {
		return builder(ConstantLootNumberProvider.create(red), ConstantLootNumberProvider.create(green), ConstantLootNumberProvider.create(blue), handler, showInTooltip);
	}
	
	public static ConditionalLootFunction.Builder<?> builder(LootNumberProvider red, LootNumberProvider green, LootNumberProvider blue, ColorOverflowHandler handler, boolean showInTooltip) {
		return builder(conditions -> new SetDyedColorLootFunction(conditions, red, green, blue, handler, showInTooltip));
	}
}