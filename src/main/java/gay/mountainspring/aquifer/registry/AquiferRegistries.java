package gay.mountainspring.aquifer.registry;

import gay.mountainspring.aquifer.block.cauldron.CauldronContentsType;
import gay.mountainspring.aquifer.block.cauldron.CauldronGroup;
import gay.mountainspring.aquifer.loot.condition.predicate.number.LootNumberPredicate;
import gay.mountainspring.aquifer.loot.provider.number.expression.LootNumberExpression;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.registry.Registry;

public class AquiferRegistries {
	private AquiferRegistries() {}
	
	public static void init() {}
	
	public static final Registry<CauldronContentsType> CAULDRON_CONTENTS_TYPE = FabricRegistryBuilder.createSimple(AquiferRegistryKeys.CAULDRON_CONTENTS_TYPE).buildAndRegister();
	public static final Registry<CauldronGroup> CAULDRON_GROUP = FabricRegistryBuilder.createSimple(AquiferRegistryKeys.CAULDRON_GROUP).buildAndRegister();
	
	static {
		DynamicRegistries.registerSynced(AquiferRegistryKeys.LOOT_NUMBER_EXPRESSION, LootNumberExpression.CODEC);
		DynamicRegistries.registerSynced(AquiferRegistryKeys.LOOT_NUMBER_PREDICATE, LootNumberPredicate.CODEC);
	}
}