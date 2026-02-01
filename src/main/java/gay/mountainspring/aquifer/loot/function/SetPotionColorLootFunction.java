package gay.mountainspring.aquifer.loot.function;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.google.common.collect.Sets;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import gay.mountainspring.aquifer.util.PotionsUtil;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameter;
import net.minecraft.loot.function.ConditionalLootFunction;
import net.minecraft.loot.function.LootFunctionType;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.LootNumberProvider;
import net.minecraft.loot.provider.number.LootNumberProviderTypes;

public class SetPotionColorLootFunction extends ConditionalLootFunction {
	public static final MapCodec<SetPotionColorLootFunction> CODEC = RecordCodecBuilder.mapCodec(
		instance -> addConditionsField(instance)
		.<LootNumberProvider, LootNumberProvider, LootNumberProvider, ColorOverflowHandler>and(
			instance.group(
				LootNumberProviderTypes.CODEC.optionalFieldOf("r", ConstantLootNumberProvider.create(0.0f)).forGetter(function -> function.red),
				LootNumberProviderTypes.CODEC.optionalFieldOf("g", ConstantLootNumberProvider.create(0.0f)).forGetter(function -> function.green),
				LootNumberProviderTypes.CODEC.optionalFieldOf("b", ConstantLootNumberProvider.create(0.0f)).forGetter(function -> function.blue),
				ColorOverflowHandler.CODEC.optionalFieldOf("color_overflow_handler", ColorOverflowHandler.CLAMP).forGetter(function -> function.colorOverflowHandler)
			)
		).apply(instance, SetPotionColorLootFunction::new)
	);
	
	final LootNumberProvider red;
	final LootNumberProvider green;
	final LootNumberProvider blue;
	final ColorOverflowHandler colorOverflowHandler;
	
	public SetPotionColorLootFunction(List<LootCondition> conditions, LootNumberProvider r, LootNumberProvider g, LootNumberProvider b, ColorOverflowHandler handler) {
		super(conditions);
		this.red = r;
		this.green = g;
		this.blue = b;
		this.colorOverflowHandler = handler;
	}
	
	@Override
	public LootFunctionType<SetPotionColorLootFunction> getType() {
		return AquiferLootFunctionTypes.SET_POTION_COLOR_LOOT_FUNCTION;
	}
	
	@Override
	protected ItemStack process(ItemStack stack, LootContext context) {
		Optional<Integer> color = Optional.of((this.colorOverflowHandler.apply(this.red.nextInt(context)) << 16) + (this.colorOverflowHandler.apply(this.green.nextInt(context)) << 8) + this.colorOverflowHandler.apply(this.blue.nextInt(context)));
		stack.apply(DataComponentTypes.POTION_CONTENTS, PotionContentsComponent.DEFAULT, color, PotionsUtil::withColor);
		return stack;
	}
	
	@Override
	public Set<LootContextParameter<?>> getRequiredParameters() {
		return Sets.union(this.red.getRequiredParameters(), Sets.union(this.green.getRequiredParameters(), this.blue.getRequiredParameters()));
	}
	
	public static ConditionalLootFunction.Builder<?> builder(float red, float green, float blue) {
		return builder(red, green, blue, ColorOverflowHandler.CLAMP);
	}
	
	public static ConditionalLootFunction.Builder<?> builder(LootNumberProvider red, LootNumberProvider green, LootNumberProvider blue) {
		return builder(red, green, blue, ColorOverflowHandler.CLAMP);
	}
	
	public static ConditionalLootFunction.Builder<?> builder(float red, float green, float blue, ColorOverflowHandler handler) {
		return builder(ConstantLootNumberProvider.create(red), ConstantLootNumberProvider.create(green), ConstantLootNumberProvider.create(blue), handler);
	}
	
	public static ConditionalLootFunction.Builder<?> builder(LootNumberProvider red, LootNumberProvider green, LootNumberProvider blue, ColorOverflowHandler handler) {
		return builder(conditions -> new SetPotionColorLootFunction(conditions, red, green, blue, handler));
	}
}