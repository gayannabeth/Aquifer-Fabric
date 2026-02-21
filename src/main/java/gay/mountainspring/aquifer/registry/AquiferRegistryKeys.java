package gay.mountainspring.aquifer.registry;

import gay.mountainspring.aquifer.Aquifer;
import gay.mountainspring.aquifer.block.cauldron.CauldronContentsType;
import gay.mountainspring.aquifer.block.cauldron.CauldronGroup;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class AquiferRegistryKeys {
	private AquiferRegistryKeys() {}
	
	public static final RegistryKey<Registry<CauldronGroup>> CAULDRON_GROUP = RegistryKey.ofRegistry(Identifier.of(Aquifer.MOD_ID, "cauldron_group"));
	public static final RegistryKey<Registry<CauldronContentsType>> CAULDRON_CONTENTS_TYPE = RegistryKey.ofRegistry(Identifier.of(Aquifer.MOD_ID, "cauldron_contents_type"));
}