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
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class Aquifer implements ModInitializer {
	public static final String MOD_ID = "aquifer";
	
	public static final Identifier AQUIFER_ID = Identifier.of(MOD_ID, MOD_ID);
	public static final Identifier CAULDRONSPLUS_ID = Identifier.of(MOD_ID, "cauldronsplus");
	public static final Identifier CAULDRONSPLUS_SSWPLUS_ID = Identifier.of(MOD_ID, "cauldronsplus_sswplus");
	public static final Identifier MOUNTAINSPRING_ID = Identifier.of(MOD_ID, "mountainspring");
	public static final Identifier MS_ID = Identifier.of(MOD_ID, "ms");
	public static final Identifier NANVIL_ID = Identifier.of(MOD_ID, "nanvil");
	public static final Identifier SSWPLUS_ID = Identifier.of(MOD_ID, "sswplus");
	
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
		
		ItemGroupEvents.MODIFY_ENTRIES_ALL.addPhaseOrdering(SSWPLUS_ID, MOUNTAINSPRING_ID);
		ItemGroupEvents.MODIFY_ENTRIES_ALL.addPhaseOrdering(CAULDRONSPLUS_ID, MOUNTAINSPRING_ID);
		ItemGroupEvents.MODIFY_ENTRIES_ALL.addPhaseOrdering(SSWPLUS_ID, CAULDRONSPLUS_SSWPLUS_ID);
		ItemGroupEvents.MODIFY_ENTRIES_ALL.addPhaseOrdering(CAULDRONSPLUS_ID, CAULDRONSPLUS_SSWPLUS_ID);
		ItemGroupEvents.MODIFY_ENTRIES_ALL.addPhaseOrdering(CAULDRONSPLUS_SSWPLUS_ID, MOUNTAINSPRING_ID);
		
		for (RegistryKey<ItemGroup> key : Registries.ITEM_GROUP.getKeys()) {
			ItemGroupEvents.modifyEntriesEvent(key).addPhaseOrdering(SSWPLUS_ID, MOUNTAINSPRING_ID);
			ItemGroupEvents.modifyEntriesEvent(key).addPhaseOrdering(CAULDRONSPLUS_ID, MOUNTAINSPRING_ID);
			ItemGroupEvents.modifyEntriesEvent(key).addPhaseOrdering(SSWPLUS_ID, CAULDRONSPLUS_SSWPLUS_ID);
			ItemGroupEvents.modifyEntriesEvent(key).addPhaseOrdering(CAULDRONSPLUS_ID, CAULDRONSPLUS_SSWPLUS_ID);
			ItemGroupEvents.modifyEntriesEvent(key).addPhaseOrdering(CAULDRONSPLUS_SSWPLUS_ID, MOUNTAINSPRING_ID);
		}
		
		ServerLifecycleEvents.SERVER_STARTING.register(server -> validateCauldronGroups());
	}
	
	public static void validateCauldronGroups() {
		AquiferRegistries.CAULDRON_GROUP.forEach(group -> AquiferRegistries.CAULDRON_CONTENTS_TYPE.forEach(contents -> {
			if (group.get(contents) == null && FabricLoader.getInstance().isDevelopmentEnvironment()) {
				LOGGER.error(String.format("{} does not have a registered block for {}!", group, contents));
			}
		}));
	}
}