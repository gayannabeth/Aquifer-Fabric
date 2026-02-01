package gay.mountainspring.aquifer.loot.function;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.item.ItemStack;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameter;
import net.minecraft.loot.function.ConditionalLootFunction;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.loot.function.LootFunctionType;
import net.minecraft.loot.function.LootFunctionTypes;
import net.minecraft.loot.provider.number.LootNumberProvider;
import net.minecraft.loot.provider.number.LootNumberProviderTypes;
import net.minecraft.util.math.MathHelper;

public class SelectorLootFunction extends ConditionalLootFunction {
	public static final MapCodec<SelectorLootFunction> CODEC = RecordCodecBuilder.mapCodec(
		instance -> addConditionsField(instance)
		.<LootNumberProvider, List<LootFunction>>and(
			instance.group(
				LootNumberProviderTypes.CODEC.fieldOf("selector").forGetter(function -> function.selector),
				LootFunctionTypes.BASE_CODEC.listOf(1, Integer.MAX_VALUE).fieldOf("functions").forGetter(function -> function.functions)
			)
		).apply(instance, SelectorLootFunction::new)
	);
	
	final LootNumberProvider selector;
	final List<LootFunction> functions;
	
	public SelectorLootFunction(List<LootCondition> conditions, LootNumberProvider selector, List<LootFunction> functions) {
		super(conditions);
		this.selector = selector;
		this.functions = functions;
	}
	
	@Override
	public LootFunctionType<? extends ConditionalLootFunction> getType() {
		return AquiferLootFunctionTypes.SELECTOR;
	}
	
	@Override
	protected ItemStack process(ItemStack stack, LootContext context) {
		return this.functions.get(MathHelper.clamp(this.selector.nextInt(context), 0, this.functions.size() - 1)).apply(stack, context);
	}
	
	@Override
	public Set<LootContextParameter<?>> getRequiredParameters() {
		var set = Sets.union(super.getRequiredParameters(), this.selector.getRequiredParameters());
		for (LootFunction function : this.functions) {
			set = Sets.union(set, function.getRequiredParameters());
		}
		return set;
	}
	
	public static ConditionalLootFunction.Builder<?> builder(LootNumberProvider selector, LootFunction.Builder... functions) {
		return builder2(selector, Arrays.asList(functions));
	}
	
	public static ConditionalLootFunction.Builder<?> builder(LootNumberProvider selector, LootFunction... functions) {
		return builder(selector, Arrays.asList(functions));
	}
	
	public static ConditionalLootFunction.Builder<?> builder2(LootNumberProvider selector, List<LootFunction.Builder> functions) {
		return builder(selector, functions.stream().map(LootFunction.Builder::build).toList());
	}
	
	public static ConditionalLootFunction.Builder<?> builder(LootNumberProvider selector, List<LootFunction> functions) {
		return builder(conditions -> new SelectorLootFunction(conditions, selector, functions));
	}
}