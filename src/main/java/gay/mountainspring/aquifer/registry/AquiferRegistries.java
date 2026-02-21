package gay.mountainspring.aquifer.registry;

import gay.mountainspring.aquifer.block.cauldron.CauldronContentsType;
import gay.mountainspring.aquifer.block.cauldron.CauldronGroup;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.registry.Registry;

public class AquiferRegistries {
	private AquiferRegistries() {}
	
	public static void init() {}
	
	public static final Registry<CauldronContentsType> CAULDRON_CONTENTS_TYPE = FabricRegistryBuilder.createSimple(AquiferRegistryKeys.CAULDRON_CONTENTS_TYPE).buildAndRegister();
	public static final Registry<CauldronGroup> CAULDRON_GROUP = FabricRegistryBuilder.createSimple(AquiferRegistryKeys.CAULDRON_GROUP).buildAndRegister();
}