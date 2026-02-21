package gay.mountainspring.aquifer.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator.Pack;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.BlockTagProvider;

public class AquiferDataGen implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator gen) {
		Pack pack = gen.createPack();
		
		pack.addProvider(AquiferAdvancementGen::new);
		BlockTagProvider blockTagProvider = pack.addProvider(AquiferTagGen.BlocksGen::new);
		pack.addProvider((output, registries) -> new AquiferTagGen.ItemsGen(output, registries, blockTagProvider));
		pack.addProvider(AquiferTagGen.FluidsGen::new);
		pack.addProvider(AquiferTagGen.EntityTypesGen::new);
		pack.addProvider(AquiferLangGen::new);
	}
}