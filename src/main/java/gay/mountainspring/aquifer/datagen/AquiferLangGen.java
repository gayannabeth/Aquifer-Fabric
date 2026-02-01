package gay.mountainspring.aquifer.datagen;

import java.util.concurrent.CompletableFuture;

import gay.mountainspring.aquifer.Aquifer;
import gay.mountainspring.aquifer.block.cauldron.CauldronContentsType;
import gay.mountainspring.aquifer.block.cauldron.CauldronGroup;
import gay.mountainspring.aquifer.tag.AquiferTags;
import gay.mountainspring.aquifer.util.TagHandlingLevel;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;

public class AquiferLangGen extends FabricLanguageProvider {
	protected AquiferLangGen(FabricDataOutput dataOutput, CompletableFuture<WrapperLookup> registryLookup) {
		super(dataOutput, registryLookup);
	}
	
	@Override
	public void generateTranslations(WrapperLookup lookup, TranslationBuilder builder) {
		builder.add(CauldronGroup.VANILLA_IRON.getTranslationKey(), "Iron");
		
		builder.add(CauldronContentsType.EMPTY.getTranslationKey(), "Empty");
		builder.add(CauldronContentsType.WATER.getTranslationKey(), "Water");
		builder.add(CauldronContentsType.LAVA.getTranslationKey(), "Lava");
		builder.add(CauldronContentsType.POWDER_SNOW.getTranslationKey(), "Powder Snow");
		
		builder.add(TagHandlingLevel.DISABLED.getTranslationKey(), "Disabled");
		builder.add(TagHandlingLevel.DISABLED.getTooltipTranslationKey(), "Disabled");
		builder.add(TagHandlingLevel.FALLBACK_TO_VANILLA.getTranslationKey(), "Fallback to Vanilla Behavior");
		builder.add(TagHandlingLevel.FALLBACK_TO_VANILLA.getTooltipTranslationKey(), "If checking the tag fails, fallback to the default vanilla behaiovr");
		builder.add(TagHandlingLevel.STRICT.getTranslationKey(), "Strict");
		builder.add(TagHandlingLevel.STRICT.getTooltipTranslationKey(), "Strict");
		
		builder.add(Aquifer.MOD_ID + ".config.tag_handling_level", "Tag Handling Level");
		builder.add(Aquifer.MOD_ID + ".config.tag_handling_level.tooltip", "How to deal with tags added by Aquifer");
		
		builder.add(AquiferTags.Blocks.AZALEA_BUSH_MAY_PLACE_ON, "Azalea Bush May Place On");
		builder.add(AquiferTags.Blocks.BLAST_FURNACES, "Blast Furnaces (Aquifer)");
		builder.add(AquiferTags.Blocks.BOOKSHELVES, "Bookshelves (Aquifer)");
		builder.add(AquiferTags.Blocks.CACTUS_MAY_GROW_ON, "Cactus May Grow On");
		builder.add(AquiferTags.Blocks.CANNOT_CONNECT_TO, "Cannot Connect To");
		builder.add(AquiferTags.Blocks.CARTOGRAPHY_TABLES, "Cartography Tables (Aquifer)");
		builder.add(AquiferTags.Blocks.CHORUS_MAY_GROW_ON, "Chorus May Grow On");
		builder.add(AquiferTags.Blocks.COCOA_MAY_GROW_ON, "Cocoa May Grow On");
		builder.add(AquiferTags.Blocks.COMPLETES_WAX_OFF_ADVANCEMENT, "Completes Wax Off Advancement");
		builder.add(AquiferTags.Blocks.COMPLETES_WAX_ON_ADVANCEMENT, "Completes Wax On Advancement");
		builder.add(AquiferTags.Blocks.COMPOSTERS, "Composters (Aquifer)");
		builder.add(AquiferTags.Blocks.CONDUIT_ACTIVATING_BLOCKS, "Conduit Activating Blocks");
		builder.add(AquiferTags.Blocks.CRAFTED_BEEHIVES, "Crafted Beehives (Aquifer)");
		builder.add(AquiferTags.Blocks.CRAFTING_TABLES, "Crafting Tables (Aquifer)");
		builder.add(AquiferTags.Blocks.DIRT_LIKE, "Dirt Like (Aquifer)");
		builder.add(AquiferTags.Blocks.ENCHANTING_TABLES, "Enchanting Tables (Aquifer)");
		builder.add(AquiferTags.Blocks.END_CRYSTAL_MAY_PLACE_ON, "End Crystal May Place On");
		builder.add(AquiferTags.Blocks.FARMLAND_LIKE, "Farmland Like (Aquifer)");
		builder.add(AquiferTags.Blocks.FLETCHING_TABLES, "Fletching Tables (Aquifer)");
		builder.add(AquiferTags.Blocks.FURNACES, "Furnaces (Aquifer)");
		builder.add(AquiferTags.Blocks.GRINDSTONES, "Grindstones (Aquifer)");
		builder.add(AquiferTags.Blocks.INFESTABLE, "Infestable");
		builder.add(AquiferTags.Blocks.INFESTED, "Infested");
		builder.add(AquiferTags.Blocks.LADDERS, "Ladders (Aquifer)");
		builder.add(AquiferTags.Blocks.LEAF_CARPETS, "Leaf Carpets");
		builder.add(AquiferTags.Blocks.LEAF_SLABS, "Leaf Slabs");
		builder.add(AquiferTags.Blocks.LEAF_STAIRS, "Leaf Stairs");
		builder.add(AquiferTags.Blocks.LEAF_WALLS, "Leaf Walls");
		builder.add(AquiferTags.Blocks.LECTERNS, "Lecterns (Aquifer)");
		builder.add(AquiferTags.Blocks.LOOMS, "Looms (Aquifer)");
		builder.add(AquiferTags.Blocks.NETHER_FUNGUS_MAY_PLACE_ON, "Nether Fungus May Place On");
		builder.add(AquiferTags.Blocks.NETHER_PLANT_MAY_PLACE_ON, "Nether Plant May Place On");
		builder.add(AquiferTags.Blocks.NETHER_WART_GROWABLE, "Nether Wart Growable");
		builder.add(AquiferTags.Blocks.PROPAGULE_MAY_GROW_UNDER, "Propagule May Grow Under");
		builder.add(AquiferTags.Blocks.PROPAGULE_MAY_PLANT_ON_TOP, "Propagule May Plant On Top");
		builder.add(AquiferTags.Blocks.SHEARS_MINEABLE, "Shears Mineable");
		builder.add(AquiferTags.Blocks.SHEARS_MINEABLE_FAST, "Shears Mineable (Fast)");
		builder.add(AquiferTags.Blocks.SHEARS_MINEABLE_MEDIUM, "Shears Mineable (Medium)");
		builder.add(AquiferTags.Blocks.SHEARS_MINEABLE_SLOW, "Shears Mineable (Slow)");
		builder.add(AquiferTags.Blocks.SIGNAL_FIRE_BASE_BLOCKS, "Signal Fire Base Blocks");
		builder.add(AquiferTags.Blocks.SMITHING_TABLES, "Smithing Tables (Aquifer)");
		builder.add(AquiferTags.Blocks.SMOKERS, "Smokers (Aquifer)");
		builder.add(AquiferTags.Blocks.STONECUTTERS, "Stonecutters (Aquifer)");
		builder.add(AquiferTags.Blocks.SUGAR_CANE_MAY_GROW_BESIDE, "Sugar Cane May Grow Beside");
		builder.add(AquiferTags.Blocks.SUGAR_CANE_MAY_GROW_ON, "Sugar Cane May Grow On");
		builder.add(AquiferTags.Blocks.TRIPWIRE_HOOKS, "Tripwire Hooks (Aquifer)");
		builder.add(AquiferTags.Blocks.WITHER_ROSE_MAY_PLACE_ON, "Wither Rose May Place On");
		builder.add(AquiferTags.Blocks.WOODEN_BARRELS, "Wooden Barrels (Aquifer)");
		builder.add(AquiferTags.Blocks.WOODEN_CHESTS, "Wooden Chests (Aquifer)");
		builder.add(AquiferTags.Blocks.WOODEN_FENCE_GATES, "Wooden Fence Gates, (Aquifer)");
		builder.add(AquiferTags.Blocks.WOODEN_LADDERS, "Wooden Ladders (Aquifer)");
		builder.add(AquiferTags.Blocks.WOODEN_WALLS, "Wooden Walls");
		
		builder.add(AquiferTags.Items.ARMADILLO_ARMOR_MATERIALS, "Armadillo Armor Materials");
		builder.add(AquiferTags.Items.BLAST_FURNACES, "Blast Furnaces (Aquifer)");
		builder.add(AquiferTags.Items.BOOKSHELVES, "Bookshelves (Aquifer)");
		builder.add(AquiferTags.Items.CACTUS_PROOF, "Cactus Proof");
		builder.add(AquiferTags.Items.CARTOGRAPHY_TABLES, "Cartography Tables (Aquifer)");
		builder.add(AquiferTags.Items.CAULDRONS, "Cauldrons (Aquifer)");
		builder.add(AquiferTags.Items.CHAIN_ARMOR_MATERIALS, "Chain Armor Materials");
		builder.add(AquiferTags.Items.COMPOSTERS, "Composters (Aquifer)");
		builder.add(AquiferTags.Items.CRAFTED_BEEHIVES, "Crafted Beehives (Aquifer)");
		builder.add(AquiferTags.Items.CRAFTING_TABLES, "Crafting Tables (Aquifer)");
		builder.add(AquiferTags.Items.DESPAWN_PROOF, "Despawn Proof");
		builder.add(AquiferTags.Items.DIAMOND_ARMOR_MATERIALS, "Diamond Armor Materials");
		builder.add(AquiferTags.Items.DIAMOND_TOOL_MATERIALS, "Diamond Tool Materials");
		builder.add(AquiferTags.Items.ELYTRA_REPAIR_MATERIALS, "Elytra Repair Materials");
		builder.add(AquiferTags.Items.ENCHANTING_TABLES, "Enchanting Tables (Aquifer)");
		builder.add(AquiferTags.Items.ENCHANTMENT_PAYMENT_ITEMS, "Enchantment Payment Items");
		builder.add(AquiferTags.Items.EXPLOSION_PROOF, "Explosion Proof");
		builder.add(AquiferTags.Items.FIRE_PROOF, "Fire Proof");
		builder.add(AquiferTags.Items.FLETCHING_TABLES, "Fletching Tables (Aquifer)");
		builder.add(AquiferTags.Items.FURNACES, "Furnaces (Aquifer)");
		builder.add(AquiferTags.Items.GOLD_ARMOR_MATERIALS, "Golden Armor Materials");
		builder.add(AquiferTags.Items.GOLD_TOOL_MATERIALS, "Golden Tool Materials");
		builder.add(AquiferTags.Items.GRINDSTONES, "Grindstones (Aquifer)");
		builder.add(AquiferTags.Items.INFESTABLE, "Infestable");
		builder.add(AquiferTags.Items.INFESTED, "Infested");
		builder.add(AquiferTags.Items.IRON_ARMOR_MATERIALS, "Iron Armor Materials");
		builder.add(AquiferTags.Items.IRON_TOOL_MATERIALS, "Iron Tool Materials");
		builder.add(AquiferTags.Items.LADDERS, "Ladders (Aquifer)");
		builder.add(AquiferTags.Items.LEAF_CARPETS, "Leaf Carpets");
		builder.add(AquiferTags.Items.LEAF_SLABS, "Leaf Slabs");
		builder.add(AquiferTags.Items.LEAF_STAIRS, "Leaf Stairs");
		builder.add(AquiferTags.Items.LEAF_WALLS, "Leaf Walls");
		builder.add(AquiferTags.Items.LEATHER_ARMOR_MATERIALS, "Leather Armor Materials");
		builder.add(AquiferTags.Items.LECTERNS, "Lecterns (Aquifer)");
		builder.add(AquiferTags.Items.LOOMS, "Looms (Aquifer)");
		builder.add(AquiferTags.Items.NETHERITE_ARMOR_MATERIALS, "Netherite Armor Materials");
		builder.add(AquiferTags.Items.NETHERITE_TOOL_MATERIALS, "Netherite Tool Materials");
		builder.add(AquiferTags.Items.PRESSURE_PLATES, "Pressure Plates (Aquifer)");
		builder.add(AquiferTags.Items.RESPAWN_ANCHOR_CHARGE_ITEMS, "Respawn Anchor Charge Items");
		builder.add(AquiferTags.Items.SMITHING_TABLES, "Smithing Tables (Aquifer)");
		builder.add(AquiferTags.Items.SMOKERS, "Smokers (Aquifer)");
		builder.add(AquiferTags.Items.STICKS, "Sticks (Aquifer)");
		builder.add(AquiferTags.Items.STONECUTTERS, "Stonecutters (Aquifer)");
		builder.add(AquiferTags.Items.TRIPWIRE_HOOKS, "Tripwire Hooks (Aquifer)");
		builder.add(AquiferTags.Items.TURTLE_ARMOR_MATERIALS, "Turtle Armor Materials");
		builder.add(AquiferTags.Items.VOID_PROOF, "Void Proof");
		builder.add(AquiferTags.Items.WOOD_TOOL_MATERIALS, "Wooden Tool Materials");
		builder.add(AquiferTags.Items.WOODEN_BARRELS, "Wooden Barrels (Aquifer)");
		builder.add(AquiferTags.Items.WOODEN_CHESTS, "Wooden Chests (Aquifer)");
		builder.add(AquiferTags.Items.WOODEN_FENCE_GATES, "Wooden Fence Gates (Aquifer)");
		builder.add(AquiferTags.Items.WOODEN_HANGING_SIGNS, "Wooden Hanging Signs (Aquifer)");
		builder.add(AquiferTags.Items.WOODEN_LADDERS, "Wooden Ladders (Aquifer)");
		builder.add(AquiferTags.Items.WOODEN_SIGNS, "Wooden Signs (Aquifer)");
		builder.add(AquiferTags.Items.WOODEN_WALLS, "Wooden Walls");
		
		builder.add(AquiferTags.Fluids.SUGAR_CANE_MAY_GROW_BESIDE, "Sugar Cane May Grow Beside");
	}
}