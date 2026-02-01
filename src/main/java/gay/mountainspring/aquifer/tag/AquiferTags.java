package gay.mountainspring.aquifer.tag;

import gay.mountainspring.aquifer.Aquifer;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class AquiferTags {
	private AquiferTags() {}
	
	public static <T> TagKey<T> create(RegistryKey<? extends Registry<T>> registryKey, String path) {
		return TagKey.of(registryKey, Identifier.of(Aquifer.MOD_ID, path));
	}
	
	public static <T> TagKey<T> createConventional(RegistryKey<? extends Registry<T>> registryKey, String path) {
		return TagKey.of(registryKey, Identifier.of("c", path));
	}
	
	public static class Blocks {
		private Blocks() {}
		
		public static final TagKey<Block> WOODEN_WALLS = create("wooden_walls");
		public static final TagKey<Block> WOODEN_FENCE_GATES = create("wooden_fence_gates");
		public static final TagKey<Block> WOODEN_CHESTS = create("wooden_chests");
		public static final TagKey<Block> WOODEN_BARRELS = create("wooden_barrels");
		public static final TagKey<Block> WOODEN_LADDERS = create("wooden_ladders");
		public static final TagKey<Block> ALL_WOODEN_SIGNS = create("all_wooden_signs");
		public static final TagKey<Block> WOODEN_SIGNS = create("wooden_signs");
		public static final TagKey<Block> WOODEN_STANDING_SIGNS = create("wooden_standing_signs");
		public static final TagKey<Block> WOODEN_WALL_SIGNS = create("wooden_wall_signs");
		public static final TagKey<Block> WOODEN_HANGING_SIGNS = create("wooden_hanging_signs");
		public static final TagKey<Block> WOODEN_CEILING_HANGING_SIGNS = create("wooden_ceiling_hanging_signs");
		public static final TagKey<Block> WOODEN_WALL_HANGING_SIGNS = create("wooden_wall_hanging_signs");
		
		public static final TagKey<Block> LEAF_SLABS = create("leaf_slabs");
		public static final TagKey<Block> LEAF_STAIRS = create("leaf_stairs");
		public static final TagKey<Block> LEAF_WALLS = create("leaf_walls");
		public static final TagKey<Block> LEAF_CARPETS = create("leaf_carpets");
		
		public static final TagKey<Block> LADDERS = create("ladders");
		
		public static final TagKey<Block> BARS = create("bars");
		
		public static final TagKey<Block> CHAINS = create("chains");
		
		public static final TagKey<Block> BOOKSHELVES = create("bookshelves");
		
		public static final TagKey<Block> CRAFTING_TABLES = create("crafting_tables");
		
		public static final TagKey<Block> LOOMS = create("looms");
		
		public static final TagKey<Block> CARTOGRAPHY_TABLES = create("cartography_tables");
		
		public static final TagKey<Block> FLETCHING_TABLES = create("fletching_tables");
		
		public static final TagKey<Block> SMITHING_TABLES = create("smithing_tables");
		
		public static final TagKey<Block> TRIPWIRE_HOOKS = create("tripwire_hooks");
		
		public static final TagKey<Block> CRAFTED_BEEHIVES = create("crafted_beehives");
		
		public static final TagKey<Block> LECTERNS = create("lecterns");
		
		public static final TagKey<Block> COMPOSTERS = create("composters");
		
		public static final TagKey<Block> ENCHANTING_TABLES = create("enchanting_tables");
		
		public static final TagKey<Block> FURNACES = create("furnaces");
		
		public static final TagKey<Block> SMOKERS = create("smokers");
		
		public static final TagKey<Block> BLAST_FURNACES = create("blast_furnaces");
		
		public static final TagKey<Block> STONECUTTERS = create("stonecutters");
		
		public static final TagKey<Block> GRINDSTONES = create("grindstones");
		
		public static final TagKey<Block> CHISELED_BOOKSHELVES = create("chiseled_bookshelves");
		
		public static final TagKey<Block> COMPLETES_WAX_ON_ADVANCEMENT = create("completes_wax_on_advancement");
		public static final TagKey<Block> COMPLETES_WAX_OFF_ADVANCEMENT = create("completes_wax_off_advancement");
		
		public static final TagKey<Block> DIRT_LIKE = create("dirt_like");
		public static final TagKey<Block> FARMLAND_LIKE = create("farmland_like");
		
		public static final TagKey<Block> NETHER_WART_GROWABLE = create("nether_wart_growable");
		
		public static final TagKey<Block> AZALEA_BUSH_MAY_PLACE_ON = create("azalea_bush_may_place_on");
		
		public static final TagKey<Block> NETHER_PLANT_MAY_PLACE_ON = create("nether_plant_may_place_on");
		public static final TagKey<Block> NETHER_FUNGUS_MAY_PLACE_ON = create("nether_fungus_may_place_on");
		
		public static final TagKey<Block> WITHER_ROSE_MAY_PLACE_ON = create("wither_rose_may_place_on");
		
		public static final TagKey<Block> SUGAR_CANE_MAY_GROW_ON = create("sugar_cane_may_grow_on");
		public static final TagKey<Block> SUGAR_CANE_MAY_GROW_BESIDE = create("sugar_cane_may_grow_beside");
		
		public static final TagKey<Block> PROPAGULE_MAY_PLANT_ON_TOP = create("propagule_may_plant_on_top");
		public static final TagKey<Block> PROPAGULE_MAY_GROW_UNDER = create("propagule_may_grow_under");
		
		public static final TagKey<Block> COCOA_MAY_GROW_ON = create("cocoa_may_grow_on");
		
		public static final TagKey<Block> CHORUS_MAY_GROW_ON = create("chorus_may_grow_on");
		
		public static final TagKey<Block> CACTUS_MAY_GROW_ON = create("cactus_may_grow_on");
		
		public static final TagKey<Block> SEAGRASS_MAY_NOT_PLACE_ON = create("seagrass_may_not_place_on");
		public static final TagKey<Block> KELP_MAY_NOT_PLACE_ON = create("kelp_may_not_place_on");
		
		public static final TagKey<Block> LILY_PAD_MAY_PLACE_ON = create("lily_pad_may_place_on");
		public static final TagKey<Block> FROGSPAWN_MAY_PLACE_ON = create("frogspawn_may_place_on");
		
		public static final TagKey<Block> CANNOT_CONNECT_TO = create("cannot_connect_to");
		
		public static final TagKey<Block> END_CRYSTAL_MAY_PLACE_ON = create("end_crystal_may_place_on");
		
		public static final TagKey<Block> SIGNAL_FIRE_BASE_BLOCKS = create("signal_fire_base_blocks");
		
		public static final TagKey<Block> CONDUIT_ACTIVATING_BLOCKS = create("conduit_activating_blocks");
		
		public static final TagKey<Block> UPWARDS_BUBBLE_COLUMN_SOURCE_BLOCKS = create("bubble_column_source_blocks/up");
		public static final TagKey<Block> DOWNWARDS_BUBBLE_COLUMN_SOURCE_BLOCKS = create("bubble_column_source_blocks/down");
		
		public static final TagKey<Block> SHEARS_MINEABLE = create("mineable/shears");
		public static final TagKey<Block> SHEARS_MINEABLE_SLOW = create("mineable/shears/slow");
		public static final TagKey<Block> SHEARS_MINEABLE_MEDIUM = create("mineable/shears/medium");
		public static final TagKey<Block> SHEARS_MINEABLE_FAST = create("mineable/shears/fast");
		
		public static final TagKey<Block> INFESTABLE = create("infestable");
		public static final TagKey<Block> INFESTED = create("infested");
		
		public static final TagKey<Block> CHESTS_WOODEN_TRAPPED = createConventional("chests/wooden/trapped");
		public static final TagKey<Block> CHESTS_WOODEN_NOT_TRAPPED = createConventional("chests/wooden/not_trapped");
		public static final TagKey<Block> CHESTS_NOT_TRAPPED = createConventional("chests/not_trapped");
		
		public static TagKey<Block> create(String path) {
			return AquiferTags.create(RegistryKeys.BLOCK, path);
		}
		
		public static TagKey<Block> createConventional(String path) {
			return AquiferTags.createConventional(RegistryKeys.BLOCK, path);
		}
	}
	
	public static class Items {
		private Items() {}
		
		public static final TagKey<Item> WOODEN_WALLS = create("wooden_walls");
		public static final TagKey<Item> WOODEN_FENCE_GATES = create("wooden_fence_gates");
		public static final TagKey<Item> WOODEN_CHESTS = create("wooden_chests");
		public static final TagKey<Item> WOODEN_BARRELS = create("wooden_barrels");
		public static final TagKey<Item> WOODEN_LADDERS = create("wooden_ladders");
		public static final TagKey<Item> WOODEN_SIGNS = create("wooden_signs");
		public static final TagKey<Item> WOODEN_HANGING_SIGNS = create("wooden_hanging_signs");
		
		public static final TagKey<Item> LEAF_SLABS = create("leaf_slabs");
		public static final TagKey<Item> LEAF_STAIRS = create("leaf_stairs");
		public static final TagKey<Item> LEAF_WALLS = create("leaf_walls");
		public static final TagKey<Item> LEAF_CARPETS = create("leaf_carpets");
		
		public static final TagKey<Item> LADDERS = create("ladders");
		
		public static final TagKey<Item> BARS = create("bars");
		
		public static final TagKey<Item> CHAINS = create("chains");
		
		public static final TagKey<Item> STICKS = create("sticks");
		
		public static final TagKey<Item> BOOKSHELVES = create("bookshelves");
		
		public static final TagKey<Item> CRAFTING_TABLES = create("crafting_tables");
		
		public static final TagKey<Item> LOOMS = create("looms");
		
		public static final TagKey<Item> CARTOGRAPHY_TABLES = create("cartography_tables");
		
		public static final TagKey<Item> FLETCHING_TABLES = create("fletching_tables");
		
		public static final TagKey<Item> SMITHING_TABLES = create("smithing_tables");
		
		public static final TagKey<Item> TRIPWIRE_HOOKS = create("tripwire_hooks");
		
		public static final TagKey<Item> CRAFTED_BEEHIVES = create("crafted_beehives");
		
		public static final TagKey<Item> LECTERNS = create("lecterns");
		
		public static final TagKey<Item> COMPOSTERS = create("composters");
		
		public static final TagKey<Item> CAULDRONS = create("cauldrons");
		
		public static final TagKey<Item> ENCHANTING_TABLES = create("enchanting_tables");
		
		public static final TagKey<Item> FURNACES = create("furnaces");
		
		public static final TagKey<Item> SMOKERS = create("smokers");
		
		public static final TagKey<Item> BLAST_FURNACES = create("blast_furnaces");
		
		public static final TagKey<Item> STONECUTTERS = create("stonecutters");
		
		public static final TagKey<Item> GRINDSTONES = create("grindstones");
		
		public static final TagKey<Item> CHISELED_BOOKSHELVES = create("chiseled_bookshelves");
		
		public static final TagKey<Item> PRESSURE_PLATES = create("pressure_plates");
		
		public static final TagKey<Item> FIRE_PROOF = create("damage_proof/fire");
		public static final TagKey<Item> EXPLOSION_PROOF = create("damage_proof/explosion");
		public static final TagKey<Item> CACTUS_PROOF = create("damage_proof/cactus");
		public static final TagKey<Item> VOID_PROOF = create("damage_proof/void");
		public static final TagKey<Item> DESPAWN_PROOF = create("damage_proof/despawn");
		
		public static final TagKey<Item> WOOD_TOOL_MATERIALS = create("wood_tool_materials");
		public static final TagKey<Item> IRON_TOOL_MATERIALS = create("iron_tool_materials");
		public static final TagKey<Item> DIAMOND_TOOL_MATERIALS = create("diamond_tool_materials");
		public static final TagKey<Item> GOLD_TOOL_MATERIALS = create("gold_tool_materials");
		public static final TagKey<Item> NETHERITE_TOOL_MATERIALS = create("netherite_tool_materials");
		
		public static final TagKey<Item> LEATHER_ARMOR_MATERIALS = create("leather_armor_materials");
		public static final TagKey<Item> CHAIN_ARMOR_MATERIALS = create("chain_armor_materials");
		public static final TagKey<Item> IRON_ARMOR_MATERIALS = create("iron_armor_materials");
		public static final TagKey<Item> DIAMOND_ARMOR_MATERIALS = create("diamond_armor_materials");
		public static final TagKey<Item> GOLD_ARMOR_MATERIALS = create("gold_armor_materials");
		public static final TagKey<Item> NETHERITE_ARMOR_MATERIALS = create("netherite_armor_materials");
		
		public static final TagKey<Item> TURTLE_ARMOR_MATERIALS = create("turtle_armor_materials");
		public static final TagKey<Item> ARMADILLO_ARMOR_MATERIALS = create("armadillo_armor_materials");
		
		public static final TagKey<Item> ELYTRA_REPAIR_MATERIALS = create("elytra_repair_materials");
		
		public static final TagKey<Item> RESPAWN_ANCHOR_CHARGE_ITEMS = create("respawn_anchor_charge_items");
		
		public static final TagKey<Item> ENCHANTMENT_PAYMENT_ITEMS = create("enchantment_payment_items");
		
		public static final TagKey<Item> POWDER_SNOW_WALKABLE_EQUIPMENT = create("powder_snow_walkable_equipment");
		
		public static final TagKey<Item> INFESTABLE = create("infestable");
		public static final TagKey<Item> INFESTED = create("infested");
		
		public static final TagKey<Item> WAXABLES = create("waxables");
		
		public static final TagKey<Item> WOODEN_TOOLS = create("wooden_tools");
		public static final TagKey<Item> STONE_TOOLS = create("stone_tools");
		public static final TagKey<Item> IRON_TOOLS = create("iron_tools");
		public static final TagKey<Item> DIAMOND_TOOLS = create("diamond_tools");
		public static final TagKey<Item> GOLD_TOOLS = create("gold_tools");
		public static final TagKey<Item> NETHERITE_TOOLS = create("netherite_tools");
		
		public static final TagKey<Item> LEATHER_ARMORS = create("leather_armors");
		public static final TagKey<Item> CHAIN_ARMORS = create("chain_armors");
		public static final TagKey<Item> IRON_ARMORS = create("iron_armors");
		public static final TagKey<Item> DIAMOND_ARMORS = create("diamond_armors");
		public static final TagKey<Item> GOLD_ARMORS = create("gold_armors");
		public static final TagKey<Item> NETHERITE_ARMORS = create("netherite_armors");
		public static final TagKey<Item> TURTLE_ARMORS = create("turtle_armors");
		
		public static final TagKey<Item> HORSE_ARMORS = create("horse_armors");
		public static final TagKey<Item> WOLF_ARMORS = create("wolf_armors");
		
		public static final TagKey<Item> BANNER_PATTERNS = create("banner_patterns");
		
		public static final TagKey<Item> FLINTS = createConventional("flints");
		
		public static final TagKey<Item> CHESTS_WOODEN_TRAPPED = createConventional("chests/wooden/trapped");
		public static final TagKey<Item> CHESTS_WOODEN_NOT_TRAPPED = createConventional("chests/wooden/not_trapped");
		public static final TagKey<Item> CHESTS_NOT_TRAPPED = createConventional("chests/not_trapped");
		
		public static TagKey<Item> create(String path) {
			return AquiferTags.create(RegistryKeys.ITEM, path);
		}
		
		public static TagKey<Item> createConventional(String path) {
			return AquiferTags.createConventional(RegistryKeys.ITEM, path);
		}
	}
	
	public static class Fluids {
		private Fluids() {}
		
		public static final TagKey<Fluid> SUGAR_CANE_MAY_GROW_BESIDE = create("sugar_cane_may_grow_beside");
		
		public static final TagKey<Fluid> LILY_PAD_MAY_PLACE_ON = create("lily_pad_may_place_on");
		public static final TagKey<Fluid> FROGSPAWN_MAY_PLACE_ON = create("frogspawn_may_place_on");
		
		public static TagKey<Fluid> create(String path) {
			return AquiferTags.create(RegistryKeys.FLUID, path);
		}
		
		public static TagKey<Fluid> createConventional(String path) {
			return AquiferTags.createConventional(RegistryKeys.FLUID, path);
		}
	}
	
	public static class EntityTypes {
		private EntityTypes() {}
		
		public static final TagKey<EntityType<?>> CAN_SPAWN_ON_LEAVES = create("can_spawn_on_leaves");
		
		public static final TagKey<EntityType<?>> SPEED_IMMUNE = create("effect_immune/speed");
		public static final TagKey<EntityType<?>> SLOWNESS_IMMUNE = create("effect_immune/slowness");
		public static final TagKey<EntityType<?>> HASTE_IMMUNE = create("effect_immune/haste");
		public static final TagKey<EntityType<?>> MINING_FATIGUE_IMMUNE = create("effect_immune/mining_fatigue");
		public static final TagKey<EntityType<?>> STRENGTH_IMMUNE = create("effect_immune/strength");
		public static final TagKey<EntityType<?>> INSTANT_HEALTH_IMMUNE = create("effect_immune/instant_health");
		public static final TagKey<EntityType<?>> INSTANT_DAMAGE_IMMUNE = create("effect_immune/instant_damage");
		public static final TagKey<EntityType<?>> JUMP_BOOST_IMMUNE = create("effect_immune/jump_boost");
		public static final TagKey<EntityType<?>> NAUSEA_IMMUNE = create("effect_immune/nausea");
		public static final TagKey<EntityType<?>> REGENERATION_IMMUNE = create("effect_immune/regeneration");
		public static final TagKey<EntityType<?>> RESISTANCE_IMMUNE = create("effect_immune/resistance");
		public static final TagKey<EntityType<?>> FIRE_RESISTANCE_IMMUNE = create("effect_immune/fire_resistance");
		public static final TagKey<EntityType<?>> WATER_BREATHING_IMMUNE = create("effect_immune/water_breathing");
		public static final TagKey<EntityType<?>> INVISIBILITY_IMMUNE = create("effect_immune/invisibility");
		public static final TagKey<EntityType<?>> BLINDNESS_IMMUNE = create("effect_immune/blindness");
		public static final TagKey<EntityType<?>> NIGHT_VISION_IMMUNE = create("effect_immune/night_vision");
		public static final TagKey<EntityType<?>> HUNGER_IMMUNE = create("effect_immune/hunger");
		public static final TagKey<EntityType<?>> WEAKNESS_IMMUNE = create("effect_immune/weakness");
		public static final TagKey<EntityType<?>> POISON_IMMUNE = create("effect_immune/poison");
		public static final TagKey<EntityType<?>> WITHER_IMMUNE = create("effect_immune/wither");
		public static final TagKey<EntityType<?>> HEALTH_BOOST_IMMUNE = create("effect_immune/health_boost");
		public static final TagKey<EntityType<?>> ABSORPTION_IMMUNE = create("effect_immune/apsorption");
		public static final TagKey<EntityType<?>> SATURATION_IMMUNE = create("effect_immune/saturation");
		public static final TagKey<EntityType<?>> GLOWING_IMMUNE = create("effect_immune/glowing");
		public static final TagKey<EntityType<?>> LEVITATION_IMMUNE = create("effect_immune/levitation");
		public static final TagKey<EntityType<?>> LUCK_IMMUNE = create("effect_immune/luck");
		public static final TagKey<EntityType<?>> UNLUCK_IMMUNE = create("effect_immune/unluck");
		public static final TagKey<EntityType<?>> SLOW_FALLING_IMMUNE = create("effect_immune/slow_falling");
		public static final TagKey<EntityType<?>> CONDUIT_POWER_IMMUNE = create("effect_immune/conduit_power");
		public static final TagKey<EntityType<?>> DOLPHINS_GRACE_IMMUNE = create("effect_immune/dolphins_grace");
		public static final TagKey<EntityType<?>> BAD_OMEN_IMMUNE = create("effect_immune/bad_omen");
		public static final TagKey<EntityType<?>> HERO_OF_THE_VILLAGE_IMMUNE = create("effect_immune/hero_of_the_village");
		public static final TagKey<EntityType<?>> DARKNESS_IMMUNE = create("effect_immune/darkness");
		public static final TagKey<EntityType<?>> TRIAL_OMEN_IMMUNE = create("effect_immune/trial_omen");
		public static final TagKey<EntityType<?>> RAID_OMEN_IMMUNE = create("effect_immune/raid_omen");
		public static final TagKey<EntityType<?>> WIND_CHARGED_IMMUNE = create("effect_immune/wind_charged");
		public static final TagKey<EntityType<?>> WEAVING_IMMUNE = create("effect_immune/weaving");
		public static final TagKey<EntityType<?>> OOZING_IMMUNE = create("effect_immune/oozing");
		public static final TagKey<EntityType<?>> INFESTED_IMMUNE = create("effect_immune/infested");
		
		public static TagKey<EntityType<?>> create(String path) {
			return AquiferTags.create(RegistryKeys.ENTITY_TYPE, path);
		}
		
		public static TagKey<EntityType<?>> createConventional(String path) {
			return AquiferTags.createConventional(RegistryKeys.ENTITY_TYPE, path);
		}
	}
}