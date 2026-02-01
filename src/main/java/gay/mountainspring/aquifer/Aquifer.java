package gay.mountainspring.aquifer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import gay.mountainspring.aquifer.block.AquiferBlockTypes;
import gay.mountainspring.aquifer.block.cauldron.AquiferCauldronBehavior;
import gay.mountainspring.aquifer.block.cauldron.CauldronContentsType;
import gay.mountainspring.aquifer.block.cauldron.CauldronGroup;
import gay.mountainspring.aquifer.block.entity.AquiferBlockEntityTypes;
import gay.mountainspring.aquifer.config.AquiferConfig;
import gay.mountainspring.aquifer.loot.condition.AquiferLootConditionTypes;
import gay.mountainspring.aquifer.loot.condition.predicate.number.LootNumberPredicates;
import gay.mountainspring.aquifer.loot.function.AquiferLootFunctionTypes;
import gay.mountainspring.aquifer.loot.provider.number.AquiferLootNumberProviderTypes;
import gay.mountainspring.aquifer.loot.provider.number.expression.LootNumberExpressions;
import gay.mountainspring.aquifer.registry.AquiferRegistries;
import gay.mountainspring.aquifer.util.BlockUtil;
import gay.mountainspring.aquifer.util.ItemUtil;
import gay.mountainspring.aquifer.util.RepairUtil;
import net.fabricmc.api.ModInitializer;

public class Aquifer implements ModInitializer {
	public static final String MOD_ID = "aquifer";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final Gson GSON = new GsonBuilder()
			.setPrettyPrinting()
			.disableHtmlEscaping()
			.registerTypeAdapter(AquiferConfig.class, AquiferConfig.getSerializer())
			.create();
	
	@Override
	public void onInitialize() {
		AquiferConfig.init();
		AquiferRegistries.init();
		LootNumberExpressions.init();
		LootNumberPredicates.init();
		AquiferLootNumberProviderTypes.init();
		AquiferLootFunctionTypes.init();
		AquiferLootConditionTypes.init();
		AquiferBlockTypes.init();
		AquiferBlockEntityTypes.init();
		CauldronGroup.init();
		CauldronContentsType.init();
		AquiferCauldronBehavior.init();
		RepairUtil.init();
		BlockUtil.init();
		ItemUtil.init();
	}
}