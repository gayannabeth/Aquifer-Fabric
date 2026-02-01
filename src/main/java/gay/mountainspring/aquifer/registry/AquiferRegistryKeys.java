package gay.mountainspring.aquifer.registry;

import gay.mountainspring.aquifer.Aquifer;
import gay.mountainspring.aquifer.block.cauldron.CauldronContentsType;
import gay.mountainspring.aquifer.block.cauldron.CauldronGroup;
import gay.mountainspring.aquifer.loot.condition.predicate.number.LootNumberPredicate;
import gay.mountainspring.aquifer.loot.provider.number.expression.LootNumberExpression;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class AquiferRegistryKeys {
	private AquiferRegistryKeys() {}
	
	public static final RegistryKey<Registry<LootNumberPredicate>> LOOT_NUMBER_PREDICATE = RegistryKey.ofRegistry(Identifier.of(Aquifer.MOD_ID, "loot_number_predicate"));
	
	public static final RegistryKey<Registry<LootNumberExpression>> LOOT_NUMBER_EXPRESSION = RegistryKey.ofRegistry(Identifier.of(Aquifer.MOD_ID, "loot_number_expression"));
	
	public static final RegistryKey<Registry<CauldronGroup>> CAULDRON_GROUP = RegistryKey.ofRegistry(Identifier.of(Aquifer.MOD_ID, "cauldron_group"));
	public static final RegistryKey<Registry<CauldronContentsType>> CAULDRON_CONTENTS_TYPE = RegistryKey.ofRegistry(Identifier.of(Aquifer.MOD_ID, "cauldron_contents_type"));
}