package gay.mountainspring.aquifer.datagen;

import gay.mountainspring.aquifer.mixin.BoundedIntUnaryOperatorInvoker;
import net.minecraft.loot.function.ConditionalLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.operator.BoundedIntUnaryOperator;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.LootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;

public class LootUtil {
	private LootUtil() {}
	
	public static BoundedIntUnaryOperator createBoundedIntUnaryOperator(LootNumberProvider provider) {
		return BoundedIntUnaryOperatorInvoker.newBoundedIntUnaryOperator(provider, provider);
	}
	
	public static BoundedIntUnaryOperator createBoundedIntUnaryOperator(LootNumberProvider min, LootNumberProvider max) {
		return BoundedIntUnaryOperatorInvoker.newBoundedIntUnaryOperator(min, max);
	}
	
	public static BoundedIntUnaryOperator createBoundedIntUnaryOperator(int min, LootNumberProvider max) {
		return createBoundedIntUnaryOperator(ConstantLootNumberProvider.create(min), max);
	}
	
	public static BoundedIntUnaryOperator createBoundedIntUnaryOperator(LootNumberProvider min, int max) {
		return createBoundedIntUnaryOperator(min, ConstantLootNumberProvider.create(max));
	}
	
	public static ConditionalLootFunction.Builder<?> setCountLootFunctionBuilder(float val) {
		return SetCountLootFunction.builder(ConstantLootNumberProvider.create(val));
	}
	
	public static ConditionalLootFunction.Builder<?> setCountLootFunctionBuilder(float val, boolean add) {
		return SetCountLootFunction.builder(ConstantLootNumberProvider.create(val), add);
	}
	
	public static ConditionalLootFunction.Builder<?> setCountLootFunctionBuilder(float min, float max) {
		return SetCountLootFunction.builder(UniformLootNumberProvider.create(min, max));
	}
	
	public static ConditionalLootFunction.Builder<?> setCountLootFunctionBuilder(float min, float max, boolean add) {
		return SetCountLootFunction.builder(UniformLootNumberProvider.create(min, max), add);
	}
}