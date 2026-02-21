package gay.mountainspring.aquifer.datagen;

import java.util.concurrent.CompletableFuture;

import gay.mountainspring.aquifer.block.cauldron.CauldronContentsType;
import gay.mountainspring.aquifer.block.cauldron.CauldronGroup;
import gay.mountainspring.aquifer.tag.AquiferTags;
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
		
		builder.add(AquiferTags.Blocks.WOODEN_WALLS, "Wooden Walls");
		builder.add(AquiferTags.Blocks.WOODEN_FENCE_GATES, "Wooden Fence Gates");
		builder.add(AquiferTags.Blocks.WOODEN_CHESTS, "Wooden Chests");
		builder.add(AquiferTags.Blocks.WOODEN_BARRELS, "Wooden Barrels");
		builder.add(AquiferTags.Blocks.WOODEN_LADDERS, "Wooden Ladders");
		builder.add(AquiferTags.Blocks.ALL_WOODEN_SIGNS, "All Wooden Signs");
		builder.add(AquiferTags.Blocks.WOODEN_SIGNS, "Wooden Signs");
		builder.add(AquiferTags.Blocks.WOODEN_STANDING_SIGNS, "Wooden Standing Signs");
		builder.add(AquiferTags.Blocks.WOODEN_WALL_SIGNS, "Wooden Wall Signs");
		builder.add(AquiferTags.Blocks.WOODEN_HANGING_SIGNS, "Wooden Hanging Signs");
		builder.add(AquiferTags.Blocks.WOODEN_CEILING_HANGING_SIGNS, "Wooden Ceiling Hanging Signs");
		builder.add(AquiferTags.Blocks.WOODEN_WALL_HANGING_SIGNS, "Wooden Wall Hanging Signs");
		
		builder.add(AquiferTags.Blocks.LEAF_SLABS, "Leaf Slabs");
		builder.add(AquiferTags.Blocks.LEAF_STAIRS, "Leaf Stairs");
		builder.add(AquiferTags.Blocks.LEAF_WALLS, "Leaf Walls");
		builder.add(AquiferTags.Blocks.LEAF_CARPETS, "Leaf Carpets");
		
		builder.add(AquiferTags.Blocks.LADDERS, "Ladders");
		
		builder.add(AquiferTags.Blocks.BARS, "Bars");
		
		builder.add(AquiferTags.Blocks.CHAINS, "Chains");
		
		builder.add(AquiferTags.Blocks.BOOKSHELVES, "Bookshelves");
		
		builder.add(AquiferTags.Blocks.CRAFTING_TABLES, "Crafting Tables");
		
		builder.add(AquiferTags.Blocks.LOOMS, "Looms");
		
		builder.add(AquiferTags.Blocks.CARTOGRAPHY_TABLES, "Cartography Tables");
		
		builder.add(AquiferTags.Blocks.FLETCHING_TABLES, "Fletching Tables");
		
		builder.add(AquiferTags.Blocks.SMITHING_TABLES, "Smithing Tables");
		
		builder.add(AquiferTags.Blocks.TRIPWIRE_HOOKS, "Tripwire Hooks");
		
		builder.add(AquiferTags.Blocks.CRAFTED_BEEHIVES, "Crafted Beehives");
		
		builder.add(AquiferTags.Blocks.LECTERNS, "Lecterns");
		
		builder.add(AquiferTags.Blocks.COMPOSTERS, "Composters");
		
		builder.add(AquiferTags.Blocks.ENCHANTING_TABLES, "Enchanting Tables");
		
		builder.add(AquiferTags.Blocks.FURNACES, "Furnaces");
		
		builder.add(AquiferTags.Blocks.SMOKERS, "Smokers");
		
		builder.add(AquiferTags.Blocks.BLAST_FURNACES, "Blast Furnaces");
		
		builder.add(AquiferTags.Blocks.STONECUTTERS, "Stonecutters");
		
		builder.add(AquiferTags.Blocks.GRINDSTONES, "Grindstones");
		
		builder.add(AquiferTags.Blocks.CHISELED_BOOKSHELVES, "Chiseled Bookshelves");
		
		builder.add(AquiferTags.Blocks.COMPLETES_WAX_OFF_ADVANCEMENT, "Completes Wax Off Advancement");
		builder.add(AquiferTags.Blocks.COMPLETES_WAX_ON_ADVANCEMENT, "Completes Wax On Advancement");
		
		builder.add(AquiferTags.Blocks.DIRT_LIKE, "Dirt Like");
		builder.add(AquiferTags.Blocks.FARMLAND_LIKE, "Farmland Like");
		
		builder.add(AquiferTags.Blocks.SUPPORTS_NETHER_WART, "Supports Nether Wart");
		
		builder.add(AquiferTags.Blocks.SUPPORTS_AZALEA, "Supports Azalea Bush");
		
		builder.add(AquiferTags.Blocks.SUPPORTS_NETHER_PLANT, "Supports Nether Plant");
		builder.add(AquiferTags.Blocks.SUPPORTS_NETHER_FUNGUS, "Supports Nether Fungus");
		
		builder.add(AquiferTags.Blocks.SUPPORTS_WITHER_ROSE, "Supports Wither Rose");
		
		builder.add(AquiferTags.Blocks.SUPPORTS_SUGAR_CANE, "Supports Sugar Cane");
		builder.add(AquiferTags.Blocks.SUPPORTS_SUGAR_CANE_ADJACENTLY, "Supports Sugar Cane Adjacently");
		
		builder.add(AquiferTags.Blocks.SUPPORTS_PROPAGULE, "Supports Propagule");
		builder.add(AquiferTags.Blocks.SUPPORTS_PROPAGULE_ABOVE, "Supports Propagule Above");
		
		builder.add(AquiferTags.Blocks.SUPPORTS_COCOA, "Supports Cocoa");
		
		builder.add(AquiferTags.Blocks.SUPPORTS_CHORUS, "Supports Chorus");
		
		builder.add(AquiferTags.Blocks.SUPPORTS_CACTUS, "Supports Cactus");
		
		builder.add(AquiferTags.Blocks.DOES_NOT_SUPPORT_SEAGRASS, "Does Not Support Seagrass");
		builder.add(AquiferTags.Blocks.DOES_NOT_SUPPORT_KELP, "Does Not Support Kelp");
		
		builder.add(AquiferTags.Blocks.SUPPORTS_LILY_PAD, "Supports Lily Pad");
		
		builder.add(AquiferTags.Blocks.SUPPORTS_FROGSPAWN, "Supports Frogspawn");
		
		builder.add(AquiferTags.Blocks.CANNOT_CONNECT_TO, "Cannot Connect To");
		
		builder.add(AquiferTags.Blocks.SUPPORTS_END_CRYSTAL, "Supports End Crystal");
		
		builder.add(AquiferTags.Blocks.SIGNAL_FIRE_BASE_BLOCKS, "Signal Fire Base Blocks");
		
		builder.add(AquiferTags.Blocks.CONDUIT_ACTIVATING_BLOCKS, "Conduit Activating Blocks");
		
		builder.add(AquiferTags.Blocks.SUPPORTS_BUBBLE_COLUMN_UP, "Supports Upwards Bubble Column");
		builder.add(AquiferTags.Blocks.SUPPORTS_BUBBLE_COLUMN_DOWN, "Supports Downwards Bubble Column");
		
		builder.add(AquiferTags.Blocks.SHEARS_MINEABLE, "Shears Mineable");
		builder.add(AquiferTags.Blocks.SHEARS_MINEABLE_FAST, "Shears Mineable (Fast)");
		builder.add(AquiferTags.Blocks.SHEARS_MINEABLE_MEDIUM, "Shears Mineable (Medium)");
		builder.add(AquiferTags.Blocks.SHEARS_MINEABLE_SLOW, "Shears Mineable (Slow)");
		
		builder.add(AquiferTags.Blocks.INFESTABLE, "Infestable");
		builder.add(AquiferTags.Blocks.INFESTED, "Infested");
		
		builder.add(AquiferTags.Blocks.Conventional.WOODEN_TRAPPED_CHESTS, "Wooden Trapped Chests");
		builder.add(AquiferTags.Blocks.Conventional.WOODEN_NON_TRAPPED_CHESTS, "Wooden Non-Trapped Chests");
		builder.add(AquiferTags.Blocks.Conventional.NON_TRAPPED_CHESTS, "Non-Trapped Chests");
		
		builder.add(AquiferTags.Items.WOODEN_WALLS, "Wooden Walls");
		builder.add(AquiferTags.Items.WOODEN_FENCE_GATES, "Wooden Fence Gates");
		builder.add(AquiferTags.Items.WOODEN_CHESTS, "Wooden Chests");
		builder.add(AquiferTags.Items.WOODEN_BARRELS, "Wooden Barrels");
		builder.add(AquiferTags.Items.WOODEN_LADDERS, "Wooden Ladders");
		builder.add(AquiferTags.Items.WOODEN_SIGNS, "Wooden Signs");
		builder.add(AquiferTags.Items.WOODEN_HANGING_SIGNS, "Wooden Hanging Signs");
		
		builder.add(AquiferTags.Items.LEAF_SLABS, "Leaf Slabs");
		builder.add(AquiferTags.Items.LEAF_STAIRS, "Leaf Stairs");
		builder.add(AquiferTags.Items.LEAF_WALLS, "Leaf Walls");
		builder.add(AquiferTags.Items.LEAF_CARPETS, "Leaf Carpets");
		
		builder.add(AquiferTags.Items.LADDERS, "Ladders");
		
		builder.add(AquiferTags.Items.BARS, "Bars");
		
		builder.add(AquiferTags.Items.CHAINS, "Chains");
		
		builder.add(AquiferTags.Items.STICKS, "Sticks");
		
		builder.add(AquiferTags.Items.BOOKSHELVES, "Bookshelves");
		
		builder.add(AquiferTags.Items.CRAFTING_TABLES, "Crafting Tables");
		
		builder.add(AquiferTags.Items.LOOMS, "Looms");
		
		builder.add(AquiferTags.Items.CARTOGRAPHY_TABLES, "Cartography Tables");
		
		builder.add(AquiferTags.Items.FLETCHING_TABLES, "Fletching Tables");
		
		builder.add(AquiferTags.Items.SMITHING_TABLES, "Smithing Tables");
		
		builder.add(AquiferTags.Items.TRIPWIRE_HOOKS, "Tripwire Hooks");
		
		builder.add(AquiferTags.Items.CRAFTED_BEEHIVES, "Crafted Beehives");
		
		builder.add(AquiferTags.Items.LECTERNS, "Lecterns");
		
		builder.add(AquiferTags.Items.COMPOSTERS, "Composters");
		
		builder.add(AquiferTags.Items.CAULDRONS, "Cauldrons");
		
		builder.add(AquiferTags.Items.ENCHANTING_TABLES, "Enchanting Tables");
		
		builder.add(AquiferTags.Items.FURNACES, "Furnaces");
		
		builder.add(AquiferTags.Items.SMOKERS, "Smokers");
		
		builder.add(AquiferTags.Items.BLAST_FURNACES, "Blast Furnaces");
		
		builder.add(AquiferTags.Items.STONECUTTERS, "Stonecutters");
		
		builder.add(AquiferTags.Items.GRINDSTONES, "Grindstones");
		
		builder.add(AquiferTags.Items.CHISELED_BOOKSHELVES, "Chiseled Bookshelves");
		
		builder.add(AquiferTags.Items.PRESSURE_PLATES, "Pressure Plates");
		
		builder.add(AquiferTags.Items.FIRE_PROOF, "Fire Proof");
		builder.add(AquiferTags.Items.EXPLOSION_PROOF, "Explosion Proof");
		builder.add(AquiferTags.Items.CACTUS_PROOF, "Cactus Proof");
		builder.add(AquiferTags.Items.VOID_PROOF, "Void Proof");
		builder.add(AquiferTags.Items.DESPAWN_PROOF, "Despawn Proof");
		
		builder.add(AquiferTags.Items.WOOD_TOOL_MATERIALS, "Wooden Tool Materials");
		builder.add(AquiferTags.Items.IRON_TOOL_MATERIALS, "Iron Tool Materials");
		builder.add(AquiferTags.Items.DIAMOND_TOOL_MATERIALS, "Diamond Tool Materials");
		builder.add(AquiferTags.Items.GOLD_TOOL_MATERIALS, "Golden Tool Materials");
		builder.add(AquiferTags.Items.NETHERITE_TOOL_MATERIALS, "Netherite Tool Materials");
		
		builder.add(AquiferTags.Items.LEATHER_ARMOR_MATERIALS, "Leather Armor Materials");
		builder.add(AquiferTags.Items.CHAIN_ARMOR_MATERIALS, "Chainmail Armor Materials");
		builder.add(AquiferTags.Items.IRON_ARMOR_MATERIALS, "Iron Armor Materials");
		builder.add(AquiferTags.Items.DIAMOND_ARMOR_MATERIALS, "Diamond Armor Materials");
		builder.add(AquiferTags.Items.GOLD_ARMOR_MATERIALS, "Golden Armor Materials");
		builder.add(AquiferTags.Items.NETHERITE_ARMOR_MATERIALS, "Netherite Armor Materials");
		
		builder.add(AquiferTags.Items.TURTLE_ARMOR_MATERIALS, "Turtle Armor Materials");
		builder.add(AquiferTags.Items.ARMADILLO_ARMOR_MATERIALS, "Armadillo Armor Materials");
		
		builder.add(AquiferTags.Items.ELYTRA_REPAIR_MATERIALS, "Elytra Repair Materials");
		
		builder.add(AquiferTags.Items.RESPAWN_ANCHOR_CHARGE_ITEMS, "Respawn Anchor Charge Items");
		
		builder.add(AquiferTags.Items.ENCHANTMENT_PAYMENT_ITEMS, "Enchantment Payment Items");
		
		builder.add(AquiferTags.Items.POWDER_SNOW_WALKABLE_EQUIPMENT, "Powder Snow Walkable Equipment");
		
		builder.add(AquiferTags.Items.INFESTABLE, "Infestable");
		builder.add(AquiferTags.Items.INFESTED, "Infested");
		
		builder.add(AquiferTags.Items.WAX_ITEMS, "Wax Items");
		
		builder.add(AquiferTags.Items.WOODEN_TOOLS, "Wooden Tools");
		builder.add(AquiferTags.Items.STONE_TOOLS, "Stone Tools");
		builder.add(AquiferTags.Items.IRON_TOOLS, "Iron Tools");
		builder.add(AquiferTags.Items.DIAMOND_TOOLS, "Diamond Tools");
		builder.add(AquiferTags.Items.GOLD_TOOLS, "Golden Tools");
		builder.add(AquiferTags.Items.NETHERITE_TOOLS, "Netherite Tools");
		
		builder.add(AquiferTags.Items.LEATHER_ARMORS, "Leather Armors");
		builder.add(AquiferTags.Items.CHAIN_ARMORS, "Chainmail Armors");
		builder.add(AquiferTags.Items.IRON_ARMORS, "Iron Armors");
		builder.add(AquiferTags.Items.DIAMOND_ARMORS, "Diamond Armors");
		builder.add(AquiferTags.Items.GOLD_ARMORS, "Golden Armors");
		builder.add(AquiferTags.Items.NETHERITE_ARMORS, "Netherite Armors");
		builder.add(AquiferTags.Items.TURTLE_ARMORS, "Turtle Armors");
		
		builder.add(AquiferTags.Items.HORSE_ARMORS, "Horse Armors");
		builder.add(AquiferTags.Items.WOLF_ARMORS, "Wolf Armors");
		
		builder.add(AquiferTags.Items.BANNER_PATTERNS, "Banner Patterns");
		
		builder.add(AquiferTags.Items.Conventional.FLINTS, "Flints");
		
		builder.add(AquiferTags.Items.Conventional.WOODEN_TRAPPED_CHESTS, "Wooden Trapped Chests");
		builder.add(AquiferTags.Items.Conventional.WOODEN_NON_TRAPPED_CHESTS, "Wooden Non-Trapped Chests");
		builder.add(AquiferTags.Items.Conventional.NON_TRAPPED_CHESTS, "Non-Trapped Chests");
		
		builder.add(AquiferTags.Fluids.SUPPORTS_SUGAR_CANE_ADJACENTLY, "Supports Sugar Cane Adjacently");
		
		builder.add(AquiferTags.Fluids.SUPPORTS_LILY_PAD, "Supports Lily Pad");
		builder.add(AquiferTags.Fluids.SUPPORTS_FROGSPAWN, "Supports Frogspawn");
		
		builder.add(AquiferTags.EntityTypes.CAN_SPAWN_ON_LEAVES, "Can Spawn On Leaves");
		
		builder.add(AquiferTags.EntityTypes.SPEED_IMMUNE, "Speed Immune");
		builder.add(AquiferTags.EntityTypes.SLOWNESS_IMMUNE, "Slowness Immune");
		builder.add(AquiferTags.EntityTypes.HASTE_IMMUNE, "Haste Immune");
		builder.add(AquiferTags.EntityTypes.MINING_FATIGUE_IMMUNE, "Mining Fatigue Immune");
		builder.add(AquiferTags.EntityTypes.STRENGTH_IMMUNE, "Strength Immune");
		builder.add(AquiferTags.EntityTypes.INSTANT_HEALTH_IMMUNE, "Instant Health Immune");
		builder.add(AquiferTags.EntityTypes.INSTANT_DAMAGE_IMMUNE, "Instant Damage Immune");
		builder.add(AquiferTags.EntityTypes.JUMP_BOOST_IMMUNE, "Jump Boost Immune");
		builder.add(AquiferTags.EntityTypes.NAUSEA_IMMUNE, "Nausea Immune");
		builder.add(AquiferTags.EntityTypes.REGENERATION_IMMUNE, "Regeneration Immune");
		builder.add(AquiferTags.EntityTypes.RESISTANCE_IMMUNE, "Resistance Immune");
		builder.add(AquiferTags.EntityTypes.FIRE_RESISTANCE_IMMUNE, "Fire Resistance Immune");
		builder.add(AquiferTags.EntityTypes.WATER_BREATHING_IMMUNE, "Water Breathing Immune");
		builder.add(AquiferTags.EntityTypes.INVISIBILITY_IMMUNE, "Invisibility Immune");
		builder.add(AquiferTags.EntityTypes.BLINDNESS_IMMUNE, "Blindness Immune");
		builder.add(AquiferTags.EntityTypes.NIGHT_VISION_IMMUNE, "Night Vision Immune");
		builder.add(AquiferTags.EntityTypes.HUNGER_IMMUNE, "Hunger Immune");
		builder.add(AquiferTags.EntityTypes.WEAKNESS_IMMUNE, "Weakness Immune");
		builder.add(AquiferTags.EntityTypes.POISON_IMMUNE, "Poison Immune");
		builder.add(AquiferTags.EntityTypes.WITHER_IMMUNE, "Wither Immune");
		builder.add(AquiferTags.EntityTypes.HEALTH_BOOST_IMMUNE, "Health Boost Immune");
		builder.add(AquiferTags.EntityTypes.ABSORPTION_IMMUNE, "Absoprtion Immune");
		builder.add(AquiferTags.EntityTypes.SATURATION_IMMUNE, "Saturation Immune");
		builder.add(AquiferTags.EntityTypes.GLOWING_IMMUNE, "Glowing Immune");
		builder.add(AquiferTags.EntityTypes.LEVITATION_IMMUNE, "Levitation Immune");
		builder.add(AquiferTags.EntityTypes.LUCK_IMMUNE, "Luck Immune");
		builder.add(AquiferTags.EntityTypes.UNLUCK_IMMUNE, "Bad Luck Immune");
		builder.add(AquiferTags.EntityTypes.SLOW_FALLING_IMMUNE, "Slow Falling Immune");
		builder.add(AquiferTags.EntityTypes.CONDUIT_POWER_IMMUNE, "Conduit Power Immune");
		builder.add(AquiferTags.EntityTypes.DOLPHINS_GRACE_IMMUNE, "Dolphin's Grace Immune");
		builder.add(AquiferTags.EntityTypes.BAD_OMEN_IMMUNE, "Bad Omen Immune");
		builder.add(AquiferTags.EntityTypes.HERO_OF_THE_VILLAGE_IMMUNE, "Hero of the Village Immune");
		builder.add(AquiferTags.EntityTypes.DARKNESS_IMMUNE, "Darkness Immune");
		builder.add(AquiferTags.EntityTypes.TRIAL_OMEN_IMMUNE, "Trial Omen Immune");
		builder.add(AquiferTags.EntityTypes.RAID_OMEN_IMMUNE, "Raid Omen Immune");
		builder.add(AquiferTags.EntityTypes.WIND_CHARGED_IMMUNE, "Wind Charged Immune");
		builder.add(AquiferTags.EntityTypes.WEAVING_IMMUNE, "Weaving Immune");
		builder.add(AquiferTags.EntityTypes.OOZING_IMMUNE, "Oozing Immune");
		builder.add(AquiferTags.EntityTypes.INFESTED_IMMUNE, "Infested Immune");
	}
}