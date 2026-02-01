package gay.mountainspring.aquifer.loot.condition;

import com.mojang.serialization.MapCodec;

import gay.mountainspring.aquifer.Aquifer;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class AquiferLootConditionTypes {
	private AquiferLootConditionTypes() {}
	
	public static void init() {}
	
	public static final LootConditionType NUMBER_COMPARISON = register("number_comparison", NumberComparisonLootCondition.CODEC);
	
	public static LootConditionType register(String name, MapCodec<? extends LootCondition> codec) {
		return Registry.register(Registries.LOOT_CONDITION_TYPE, Identifier.of(Aquifer.MOD_ID, name), new LootConditionType(codec));
	}
}