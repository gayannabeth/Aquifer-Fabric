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

public class SequenceLootFunction extends ConditionalLootFunction {
	public static final MapCodec<SequenceLootFunction> CODEC = RecordCodecBuilder.mapCodec(
		instance -> addConditionsField(instance)
		.<List<LootFunction>>and(LootFunctionTypes.BASE_CODEC.listOf(1, Integer.MAX_VALUE).fieldOf("functions").forGetter(function -> function.functions))
		.apply(instance, SequenceLootFunction::new)
	);
	
	final List<LootFunction> functions;
	
	public SequenceLootFunction(List<LootCondition> conditions, List<LootFunction> functions) {
		super(conditions);
		this.functions = functions;
	}
	
	@Override
	public LootFunctionType<? extends ConditionalLootFunction> getType() {
		return AquiferLootFunctionTypes.SEQUENCE;
	}
	
	@Override
	protected ItemStack process(ItemStack stack, LootContext context) {
		for (LootFunction function : this.functions) {
			stack = function.apply(stack, context);
		}
		return stack;
	}
	
	@Override
	public Set<LootContextParameter<?>> getRequiredParameters() {
		var set = super.getRequiredParameters();
		for (LootFunction function : this.functions) {
			set = Sets.union(set, function.getRequiredParameters());
		}
		return set;
	}
	
	public static ConditionalLootFunction.Builder<?> builder(LootFunction.Builder... functions) {
		return builder2(Arrays.asList(functions));
	}
	
	public static ConditionalLootFunction.Builder<?> builder(LootFunction... functions) {
		return builder(Arrays.asList(functions));
	}
	
	public static ConditionalLootFunction.Builder<?> builder2(List<LootFunction.Builder> functions) {
		return builder(functions.stream().map(LootFunction.Builder::build).toList());
	}
	
	public static ConditionalLootFunction.Builder<?> builder(List<LootFunction> functions) {
		return builder(conditions -> new SequenceLootFunction(conditions, functions));
	}
}