package gay.mountainspring.aquifer.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator.Pack;

public class AquiferDataGen implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator gen) {
		Pack pack = gen.createPack();
		
		pack.addProvider(AquiferAdvancementGen::new);
		pack.addProvider(AquiferTagGen.BlocksGen::new);
		pack.addProvider(AquiferTagGen.ItemsGen::new);
		pack.addProvider(AquiferTagGen.FluidsGen::new);
		pack.addProvider(AquiferTagGen.EntityTypesGen::new);
		pack.addProvider(AquiferLangGen::new);
	}
}