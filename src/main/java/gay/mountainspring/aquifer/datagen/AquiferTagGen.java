package gay.mountainspring.aquifer.datagen;

import java.util.concurrent.CompletableFuture;

import gay.mountainspring.aquifer.tag.AquiferTags;
import net.fabricmc.fabric.api.block.v1.BlockFunctionalityTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalFluidTags;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.registry.tag.ItemTags;

public class AquiferTagGen {
	public static class BlocksGen extends FabricTagProvider<Block> {
		public BlocksGen(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
			super(output, RegistryKeys.BLOCK, registriesFuture);
		}
		
		@Override
		protected void configure(WrapperLookup wrapperLookup) {
			this.getOrCreateTagBuilder(AquiferTags.Blocks.WOODEN_WALLS);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.WOODEN_FENCE_GATES)
			.add(Blocks.OAK_FENCE_GATE,
					Blocks.SPRUCE_FENCE_GATE,
					Blocks.BIRCH_FENCE_GATE,
					Blocks.JUNGLE_FENCE_GATE,
					Blocks.ACACIA_FENCE_GATE,
					Blocks.DARK_OAK_FENCE_GATE,
					Blocks.MANGROVE_FENCE_GATE,
					Blocks.CHERRY_FENCE_GATE,
					Blocks.BAMBOO_FENCE_GATE,
					Blocks.CRIMSON_FENCE_GATE,
					Blocks.WARPED_FENCE_GATE);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.WOODEN_CHESTS)
			.add(Blocks.CHEST,
					Blocks.TRAPPED_CHEST);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.WOODEN_BARRELS)
			.add(Blocks.BARREL);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.WOODEN_LADDERS)
			.add(Blocks.LADDER);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.WOODEN_STANDING_SIGNS)
			.add(Blocks.OAK_SIGN,
					Blocks.SPRUCE_SIGN,
					Blocks.BIRCH_SIGN,
					Blocks.JUNGLE_SIGN,
					Blocks.ACACIA_SIGN,
					Blocks.DARK_OAK_SIGN,
					Blocks.MANGROVE_SIGN,
					Blocks.CHERRY_SIGN,
					Blocks.BAMBOO_SIGN,
					Blocks.CRIMSON_SIGN,
					Blocks.WARPED_SIGN);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.WOODEN_WALL_SIGNS)
			.add(Blocks.OAK_WALL_SIGN,
					Blocks.SPRUCE_WALL_SIGN,
					Blocks.BIRCH_WALL_SIGN,
					Blocks.JUNGLE_WALL_SIGN,
					Blocks.ACACIA_WALL_SIGN,
					Blocks.DARK_OAK_WALL_SIGN,
					Blocks.MANGROVE_WALL_SIGN,
					Blocks.CHERRY_WALL_SIGN,
					Blocks.BAMBOO_WALL_SIGN,
					Blocks.CRIMSON_WALL_SIGN,
					Blocks.WARPED_WALL_SIGN);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.WOODEN_SIGNS)
			.addTag(AquiferTags.Blocks.WOODEN_STANDING_SIGNS)
			.addTag(AquiferTags.Blocks.WOODEN_WALL_SIGNS);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.WOODEN_CEILING_HANGING_SIGNS)
			.add(Blocks.OAK_HANGING_SIGN,
					Blocks.SPRUCE_HANGING_SIGN,
					Blocks.BIRCH_HANGING_SIGN,
					Blocks.JUNGLE_HANGING_SIGN,
					Blocks.ACACIA_HANGING_SIGN,
					Blocks.DARK_OAK_HANGING_SIGN,
					Blocks.MANGROVE_HANGING_SIGN,
					Blocks.CHERRY_HANGING_SIGN,
					Blocks.BAMBOO_HANGING_SIGN,
					Blocks.CRIMSON_HANGING_SIGN,
					Blocks.WARPED_HANGING_SIGN);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.WOODEN_WALL_HANGING_SIGNS)
			.add(Blocks.OAK_WALL_HANGING_SIGN,
					Blocks.SPRUCE_WALL_HANGING_SIGN,
					Blocks.BIRCH_WALL_HANGING_SIGN,
					Blocks.JUNGLE_WALL_HANGING_SIGN,
					Blocks.ACACIA_WALL_HANGING_SIGN,
					Blocks.DARK_OAK_WALL_HANGING_SIGN,
					Blocks.MANGROVE_WALL_HANGING_SIGN,
					Blocks.CHERRY_WALL_HANGING_SIGN,
					Blocks.BAMBOO_WALL_HANGING_SIGN,
					Blocks.CRIMSON_WALL_HANGING_SIGN,
					Blocks.WARPED_WALL_HANGING_SIGN);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.WOODEN_HANGING_SIGNS)
			.addTag(AquiferTags.Blocks.WOODEN_CEILING_HANGING_SIGNS)
			.addTag(AquiferTags.Blocks.WOODEN_WALL_HANGING_SIGNS);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.ALL_WOODEN_SIGNS)
			.addTag(AquiferTags.Blocks.WOODEN_SIGNS)
			.addTag(AquiferTags.Blocks.WOODEN_HANGING_SIGNS);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.LEAF_SLABS);
			this.getOrCreateTagBuilder(AquiferTags.Blocks.LEAF_STAIRS);
			this.getOrCreateTagBuilder(AquiferTags.Blocks.LEAF_WALLS);
			this.getOrCreateTagBuilder(AquiferTags.Blocks.LEAF_CARPETS);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.LADDERS)
			.addTag(AquiferTags.Blocks.WOODEN_LADDERS);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.BARS)
			.add(Blocks.IRON_BARS);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.CHAINS)
			.add(Blocks.CHAIN);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.BOOKSHELVES)
			.add(Blocks.BOOKSHELF);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.CRAFTING_TABLES)
			.add(Blocks.CRAFTING_TABLE);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.LOOMS)
			.add(Blocks.LOOM);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.CARTOGRAPHY_TABLES)
			.add(Blocks.CARTOGRAPHY_TABLE);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.FLETCHING_TABLES)
			.add(Blocks.FLETCHING_TABLE);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.SMITHING_TABLES)
			.add(Blocks.SMITHING_TABLE);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.TRIPWIRE_HOOKS)
			.add(Blocks.TRIPWIRE_HOOK);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.CRAFTED_BEEHIVES)
			.add(Blocks.BEEHIVE);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.LECTERNS)
			.add(Blocks.LECTERN);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.COMPOSTERS)
			.add(Blocks.COMPOSTER);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.ENCHANTING_TABLES)
			.add(Blocks.ENCHANTING_TABLE);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.FURNACES)
			.add(Blocks.FURNACE);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.SMOKERS)
			.add(Blocks.SMOKER);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.BLAST_FURNACES)
			.add(Blocks.BLAST_FURNACE);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.STONECUTTERS)
			.add(Blocks.STONECUTTER);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.GRINDSTONES)
			.add(Blocks.GRINDSTONE);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.CHISELED_BOOKSHELVES)
			.add(Blocks.CHISELED_BOOKSHELF);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.COMPLETES_WAX_ON_ADVANCEMENT)
			.add(Blocks.CHISELED_COPPER,
					Blocks.COPPER_BLOCK,
					Blocks.COPPER_BULB,
					Blocks.COPPER_DOOR,
					Blocks.COPPER_GRATE,
					Blocks.COPPER_TRAPDOOR,
					Blocks.CUT_COPPER,
					Blocks.CUT_COPPER_SLAB,
					Blocks.CUT_COPPER_STAIRS,
					Blocks.EXPOSED_CHISELED_COPPER,
					Blocks.EXPOSED_COPPER,
					Blocks.EXPOSED_COPPER_BULB,
					Blocks.EXPOSED_COPPER_DOOR,
					Blocks.EXPOSED_COPPER_GRATE,
					Blocks.EXPOSED_COPPER_TRAPDOOR,
					Blocks.EXPOSED_CUT_COPPER,
					Blocks.EXPOSED_CUT_COPPER_SLAB,
					Blocks.EXPOSED_CUT_COPPER_STAIRS,
					Blocks.WEATHERED_CHISELED_COPPER,
					Blocks.WEATHERED_COPPER,
					Blocks.WEATHERED_COPPER_BULB,
					Blocks.WEATHERED_COPPER_DOOR,
					Blocks.WEATHERED_COPPER_GRATE,
					Blocks.WEATHERED_COPPER_TRAPDOOR,
					Blocks.WEATHERED_CUT_COPPER,
					Blocks.WEATHERED_CUT_COPPER_SLAB,
					Blocks.WEATHERED_CUT_COPPER_STAIRS,
					Blocks.OXIDIZED_CHISELED_COPPER,
					Blocks.OXIDIZED_COPPER,
					Blocks.OXIDIZED_COPPER_BULB,
					Blocks.OXIDIZED_COPPER_DOOR,
					Blocks.OXIDIZED_COPPER_GRATE,
					Blocks.OXIDIZED_COPPER_TRAPDOOR,
					Blocks.OXIDIZED_CUT_COPPER,
					Blocks.OXIDIZED_CUT_COPPER_SLAB,
					Blocks.OXIDIZED_CUT_COPPER_STAIRS);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.COMPLETES_WAX_OFF_ADVANCEMENT)
			.add(Blocks.WAXED_CHISELED_COPPER,
					Blocks.WAXED_COPPER_BLOCK,
					Blocks.WAXED_COPPER_BULB,
					Blocks.WAXED_COPPER_DOOR,
					Blocks.WAXED_COPPER_GRATE,
					Blocks.WAXED_COPPER_TRAPDOOR,
					Blocks.WAXED_CUT_COPPER,
					Blocks.WAXED_CUT_COPPER_SLAB,
					Blocks.WAXED_CUT_COPPER_STAIRS,
					Blocks.WAXED_EXPOSED_CHISELED_COPPER,
					Blocks.WAXED_EXPOSED_COPPER,
					Blocks.WAXED_EXPOSED_COPPER_BULB,
					Blocks.WAXED_EXPOSED_COPPER_DOOR,
					Blocks.WAXED_EXPOSED_COPPER_GRATE,
					Blocks.WAXED_EXPOSED_COPPER_TRAPDOOR,
					Blocks.WAXED_EXPOSED_CUT_COPPER,
					Blocks.WAXED_EXPOSED_CUT_COPPER_SLAB,
					Blocks.WAXED_EXPOSED_CUT_COPPER_STAIRS,
					Blocks.WAXED_WEATHERED_CHISELED_COPPER,
					Blocks.WAXED_WEATHERED_COPPER,
					Blocks.WAXED_WEATHERED_COPPER_BULB,
					Blocks.WAXED_WEATHERED_COPPER_DOOR,
					Blocks.WAXED_WEATHERED_COPPER_GRATE,
					Blocks.WAXED_WEATHERED_COPPER_TRAPDOOR,
					Blocks.WAXED_WEATHERED_CUT_COPPER,
					Blocks.WAXED_WEATHERED_CUT_COPPER_SLAB,
					Blocks.WAXED_WEATHERED_CUT_COPPER_STAIRS,
					Blocks.WAXED_OXIDIZED_CHISELED_COPPER,
					Blocks.WAXED_OXIDIZED_COPPER,
					Blocks.WAXED_OXIDIZED_COPPER_BULB,
					Blocks.WAXED_OXIDIZED_COPPER_DOOR,
					Blocks.WAXED_OXIDIZED_COPPER_GRATE,
					Blocks.WAXED_OXIDIZED_COPPER_TRAPDOOR,
					Blocks.WAXED_OXIDIZED_CUT_COPPER,
					Blocks.WAXED_OXIDIZED_CUT_COPPER_SLAB,
					Blocks.WAXED_OXIDIZED_CUT_COPPER_STAIRS);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.DIRT_LIKE)
			.forceAddTag(BlockTags.DIRT);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.FARMLAND_LIKE)
			.add(Blocks.FARMLAND);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.NETHER_WART_GROWABLE)
			.add(Blocks.SOUL_SAND);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.AZALEA_BUSH_MAY_PLACE_ON)
			.add(Blocks.CLAY)
			.addTag(AquiferTags.Blocks.DIRT_LIKE)
			.addTag(AquiferTags.Blocks.FARMLAND_LIKE);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.NETHER_PLANT_MAY_PLACE_ON)
			.add(Blocks.SOUL_SOIL)
			.addTag(AquiferTags.Blocks.DIRT_LIKE)
			.addTag(AquiferTags.Blocks.FARMLAND_LIKE)
			.forceAddTag(BlockTags.NYLIUM);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.NETHER_FUNGUS_MAY_PLACE_ON)
			.add(Blocks.MYCELIUM)
			.addTag(AquiferTags.Blocks.NETHER_PLANT_MAY_PLACE_ON);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.WITHER_ROSE_MAY_PLACE_ON)
			.add(Blocks.NETHERRACK,
					Blocks.SOUL_SAND,
					Blocks.SOUL_SOIL)
			.addTag(AquiferTags.Blocks.DIRT_LIKE)
			.addTag(AquiferTags.Blocks.FARMLAND_LIKE)
			.forceAddTag(ConventionalBlockTags.NETHERRACKS);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.SUGAR_CANE_MAY_GROW_ON)
			.addTag(AquiferTags.Blocks.DIRT_LIKE)
			.forceAddTag(BlockTags.SAND)
			.forceAddTag(ConventionalBlockTags.SANDS);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.SUGAR_CANE_MAY_GROW_BESIDE)
			.add(Blocks.FROSTED_ICE);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.PROPAGULE_MAY_PLANT_ON_TOP)
			.add(Blocks.CLAY)
			.addTag(AquiferTags.Blocks.DIRT_LIKE)
			.addTag(AquiferTags.Blocks.FARMLAND_LIKE);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.PROPAGULE_MAY_GROW_UNDER)
			.add(Blocks.MANGROVE_LEAVES);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.COCOA_MAY_GROW_ON)
			.forceAddTag(BlockTags.JUNGLE_LOGS);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.CHORUS_MAY_GROW_ON)
			.add(Blocks.END_STONE)
			.forceAddTag(ConventionalBlockTags.END_STONES);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.CACTUS_MAY_GROW_ON)
			.add(Blocks.CACTUS)
			.forceAddTag(BlockTags.SAND)
			.forceAddTag(ConventionalBlockTags.SANDS);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.KELP_MAY_NOT_PLACE_ON)
			.add(Blocks.MAGMA_BLOCK);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.SEAGRASS_MAY_NOT_PLACE_ON)
			.add(Blocks.MAGMA_BLOCK);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.LILY_PAD_MAY_PLACE_ON)
			.add(Blocks.ICE,
					Blocks.FROSTED_ICE,
					Blocks.WATER);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.FROGSPAWN_MAY_PLACE_ON)
			.add(Blocks.WATER);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.CANNOT_CONNECT_TO)
			.add(Blocks.BARRIER,
					Blocks.CARVED_PUMPKIN,
					Blocks.JACK_O_LANTERN,
					Blocks.MELON,
					Blocks.PUMPKIN)
			.forceAddTag(ConventionalBlockTags.PUMPKINS)
			.forceAddTag(BlockTags.LEAVES)
			.forceAddTag(BlockTags.SHULKER_BOXES)
			.addTag(AquiferTags.Blocks.LEAF_SLABS)
			.addTag(AquiferTags.Blocks.LEAF_STAIRS);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.END_CRYSTAL_MAY_PLACE_ON)
			.add(Blocks.OBSIDIAN,
					Blocks.BEDROCK)
			.forceAddTag(ConventionalBlockTags.NORMAL_OBSIDIANS);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.SIGNAL_FIRE_BASE_BLOCKS)
			.add(Blocks.HAY_BLOCK);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.CONDUIT_ACTIVATING_BLOCKS)
			.add(Blocks.PRISMARINE,
					Blocks.PRISMARINE_BRICKS,
					Blocks.DARK_PRISMARINE,
					Blocks.SEA_LANTERN);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.UPWARDS_BUBBLE_COLUMN_SOURCE_BLOCKS)
			.add(Blocks.SOUL_SAND);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.DOWNWARDS_BUBBLE_COLUMN_SOURCE_BLOCKS)
			.add(Blocks.MAGMA_BLOCK);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.SHEARS_MINEABLE_FAST)
			.forceAddTag(BlockTags.LEAVES)
			.addTag(AquiferTags.Blocks.LEAF_CARPETS)
			.addTag(AquiferTags.Blocks.LEAF_SLABS)
			.addTag(AquiferTags.Blocks.LEAF_STAIRS)
			.addTag(AquiferTags.Blocks.LEAF_WALLS);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.SHEARS_MINEABLE_MEDIUM)
			.forceAddTag(BlockTags.WOOL);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.SHEARS_MINEABLE_SLOW)
			.add(Blocks.VINE,
					Blocks.GLOW_LICHEN);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.SHEARS_MINEABLE)
			.add(Blocks.COBWEB,
					Blocks.SHORT_GRASS,
					Blocks.FERN,
					Blocks.DEAD_BUSH,
					Blocks.HANGING_ROOTS,
					Blocks.TRIPWIRE)
			.addTag(AquiferTags.Blocks.SHEARS_MINEABLE_FAST)
			.addTag(AquiferTags.Blocks.SHEARS_MINEABLE_MEDIUM)
			.addTag(AquiferTags.Blocks.SHEARS_MINEABLE_SLOW);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.INFESTABLE)
			.add(Blocks.STONE,
					Blocks.COBBLESTONE,
					Blocks.STONE_BRICKS,
					Blocks.CRACKED_STONE_BRICKS,
					Blocks.MOSSY_STONE_BRICKS,
					Blocks.CHISELED_STONE_BRICKS,
					Blocks.DEEPSLATE);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.INFESTED)
			.add(Blocks.INFESTED_STONE,
					Blocks.INFESTED_COBBLESTONE,
					Blocks.INFESTED_STONE_BRICKS,
					Blocks.INFESTED_CRACKED_STONE_BRICKS,
					Blocks.INFESTED_MOSSY_STONE_BRICKS,
					Blocks.INFESTED_CHISELED_STONE_BRICKS,
					Blocks.INFESTED_DEEPSLATE);
			
			this.getOrCreateTagBuilder(BlockTags.SLABS)
			.addTag(AquiferTags.Blocks.LEAF_SLABS);
			
			this.getOrCreateTagBuilder(BlockTags.STAIRS)
			.addTag(AquiferTags.Blocks.LEAF_STAIRS);
			
			this.getOrCreateTagBuilder(BlockTags.WALLS)
			.addTag(AquiferTags.Blocks.WOODEN_WALLS)
			.addTag(AquiferTags.Blocks.LEAF_WALLS);
			
			this.getOrCreateTagBuilder(BlockTags.FENCE_GATES)
			.addTag(AquiferTags.Blocks.WOODEN_FENCE_GATES);
			
			this.getOrCreateTagBuilder(BlockTags.STANDING_SIGNS)
			.addTag(AquiferTags.Blocks.WOODEN_STANDING_SIGNS);
			
			this.getOrCreateTagBuilder(BlockTags.WALL_SIGNS)
			.addTag(AquiferTags.Blocks.WOODEN_WALL_SIGNS);
			
			this.getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS)
			.addTag(AquiferTags.Blocks.WOODEN_CEILING_HANGING_SIGNS);
			
			this.getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS)
			.addTag(AquiferTags.Blocks.WOODEN_WALL_HANGING_SIGNS);
			
			this.getOrCreateTagBuilder(BlockTags.CLIMBABLE)
			.addTag(AquiferTags.Blocks.LADDERS);
			
			this.getOrCreateTagBuilder(BlockTags.COMBINATION_STEP_SOUND_BLOCKS)
			.addTag(AquiferTags.Blocks.LEAF_CARPETS);
			
			this.getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
			.addTag(AquiferTags.Blocks.WOODEN_WALLS)
			.addTag(AquiferTags.Blocks.WOODEN_FENCE_GATES)
			.addTag(AquiferTags.Blocks.WOODEN_CHESTS)
			.addTag(AquiferTags.Blocks.WOODEN_BARRELS)
			.addTag(AquiferTags.Blocks.WOODEN_LADDERS)
			.addTag(AquiferTags.Blocks.CRAFTED_BEEHIVES);
			
			this.getOrCreateTagBuilder(BlockTags.HOE_MINEABLE)
			.addTag(AquiferTags.Blocks.LEAF_SLABS)
			.addTag(AquiferTags.Blocks.LEAF_STAIRS)
			.addTag(AquiferTags.Blocks.LEAF_WALLS)
			.addTag(AquiferTags.Blocks.LEAF_CARPETS);
			
			this.getOrCreateTagBuilder(BlockTags.SWORD_EFFICIENT)
			.addTag(AquiferTags.Blocks.LEAF_CARPETS)
			.addTag(AquiferTags.Blocks.LEAF_SLABS)
			.addTag(AquiferTags.Blocks.LEAF_STAIRS)
			.addTag(AquiferTags.Blocks.LEAF_WALLS);
			
			this.getOrCreateTagBuilder(BlockTags.ENCHANTMENT_POWER_PROVIDER)
			.addTag(AquiferTags.Blocks.BOOKSHELVES);
			
			this.getOrCreateTagBuilder(BlockTags.BAMBOO_PLANTABLE_ON)
			.addTag(AquiferTags.Blocks.DIRT_LIKE);
			
			this.getOrCreateTagBuilder(BlockTags.BIG_DRIPLEAF_PLACEABLE)
			.addTag(AquiferTags.Blocks.DIRT_LIKE);
			
			this.getOrCreateTagBuilder(BlockTags.SNIFFER_DIGGABLE_BLOCK)
			.addTag(AquiferTags.Blocks.DIRT_LIKE);
			
			this.getOrCreateTagBuilder(BlockTags.BEEHIVES)
			.addTag(AquiferTags.Blocks.CRAFTED_BEEHIVES);
			
			this.getOrCreateTagBuilder(ConventionalBlockTags.WOODEN_BARRELS)
			.addTag(AquiferTags.Blocks.WOODEN_BARRELS);
			
			this.getOrCreateTagBuilder(ConventionalBlockTags.WOODEN_CHESTS)
			.addTag(AquiferTags.Blocks.WOODEN_CHESTS);
			
			this.getOrCreateTagBuilder(ConventionalBlockTags.WOODEN_FENCE_GATES)
			.addTag(AquiferTags.Blocks.WOODEN_FENCE_GATES);
			
			this.getOrCreateTagBuilder(ConventionalBlockTags.VILLAGER_JOB_SITES)
			.addTag(AquiferTags.Blocks.LOOMS)
			.addTag(AquiferTags.Blocks.CARTOGRAPHY_TABLES)
			.addTag(AquiferTags.Blocks.FLETCHING_TABLES)
			.addTag(AquiferTags.Blocks.SMITHING_TABLES)
			.addTag(AquiferTags.Blocks.LECTERNS)
			.addTag(AquiferTags.Blocks.COMPOSTERS)
			.addTag(AquiferTags.Blocks.SMOKERS)
			.addTag(AquiferTags.Blocks.BLAST_FURNACES)
			.addTag(AquiferTags.Blocks.STONECUTTERS)
			.addTag(AquiferTags.Blocks.GRINDSTONES);
			
			this.getOrCreateTagBuilder(ConventionalBlockTags.PLAYER_WORKSTATIONS_CRAFTING_TABLES)
			.addTag(AquiferTags.Blocks.CRAFTING_TABLES);
			
			this.getOrCreateTagBuilder(ConventionalBlockTags.PLAYER_WORKSTATIONS_FURNACES)
			.addTag(AquiferTags.Blocks.FURNACES);
			
			this.getOrCreateTagBuilder(BlockTags.INFINIBURN_OVERWORLD)
			.forceAddTag(ConventionalBlockTags.NETHERRACKS);
			
			this.getOrCreateTagBuilder(BlockTags.ENCHANTMENT_POWER_PROVIDER)
			.forceAddTag(ConventionalBlockTags.BOOKSHELVES);
			
			this.getOrCreateTagBuilder(BlockTags.BEACON_BASE_BLOCKS)
			.forceAddTag(ConventionalBlockTags.STORAGE_BLOCKS_DIAMOND)
			.forceAddTag(ConventionalBlockTags.STORAGE_BLOCKS_EMERALD)
			.forceAddTag(ConventionalBlockTags.STORAGE_BLOCKS_GOLD)
			.forceAddTag(ConventionalBlockTags.STORAGE_BLOCKS_IRON)
			.forceAddTag(ConventionalBlockTags.STORAGE_BLOCKS_NETHERITE);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.CHESTS_WOODEN_TRAPPED)
			.add(Blocks.TRAPPED_CHEST);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.CHESTS_WOODEN_NOT_TRAPPED)
			.add(Blocks.CHEST);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.CHESTS_NOT_TRAPPED)
			.addTag(AquiferTags.Blocks.CHESTS_WOODEN_NOT_TRAPPED);
			
			this.getOrCreateTagBuilder(ConventionalBlockTags.CHESTS)
			.addTag(AquiferTags.Blocks.CHESTS_NOT_TRAPPED);
			
			this.getOrCreateTagBuilder(ConventionalBlockTags.TRAPPED_CHESTS)
			.addTag(AquiferTags.Blocks.CHESTS_WOODEN_TRAPPED);
			
			this.getOrCreateTagBuilder(ConventionalBlockTags.WOODEN_CHESTS)
			.addTag(AquiferTags.Blocks.CHESTS_WOODEN_NOT_TRAPPED)
			.addTag(AquiferTags.Blocks.CHESTS_WOODEN_TRAPPED);
			
			this.getOrCreateTagBuilder(BlockFunctionalityTags.CAN_CLIMB_TRAPDOOR_ABOVE)
			.addTag(AquiferTags.Blocks.LADDERS);
		}
	}
	
	public static class ItemsGen extends FabricTagProvider<Item> {
		public ItemsGen(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
			super(output, RegistryKeys.ITEM, registriesFuture);
		}
		
		@Override
		protected void configure(WrapperLookup wrapperLookup) {
			this.getOrCreateTagBuilder(AquiferTags.Items.WOODEN_WALLS);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.WOODEN_FENCE_GATES)
			.add(Items.OAK_FENCE_GATE,
					Items.SPRUCE_FENCE_GATE,
					Items.BIRCH_FENCE_GATE,
					Items.JUNGLE_FENCE_GATE,
					Items.ACACIA_FENCE_GATE,
					Items.DARK_OAK_FENCE_GATE,
					Items.MANGROVE_FENCE_GATE,
					Items.CHERRY_FENCE_GATE,
					Items.BAMBOO_FENCE_GATE,
					Items.CRIMSON_FENCE_GATE,
					Items.WARPED_FENCE_GATE);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.WOODEN_CHESTS)
			.add(Items.CHEST,
					Items.TRAPPED_CHEST);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.WOODEN_BARRELS)
			.add(Items.BARREL);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.WOODEN_LADDERS)
			.add(Items.LADDER);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.WOODEN_SIGNS)
			.add(Items.OAK_SIGN,
					Items.SPRUCE_SIGN,
					Items.BIRCH_SIGN,
					Items.JUNGLE_SIGN,
					Items.ACACIA_SIGN,
					Items.DARK_OAK_SIGN,
					Items.MANGROVE_SIGN,
					Items.CHERRY_SIGN,
					Items.BAMBOO_SIGN,
					Items.CRIMSON_SIGN,
					Items.WARPED_SIGN);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.WOODEN_HANGING_SIGNS)
			.add(Items.OAK_HANGING_SIGN,
					Items.SPRUCE_HANGING_SIGN,
					Items.BIRCH_HANGING_SIGN,
					Items.JUNGLE_HANGING_SIGN,
					Items.ACACIA_HANGING_SIGN,
					Items.DARK_OAK_HANGING_SIGN,
					Items.MANGROVE_HANGING_SIGN,
					Items.CHERRY_HANGING_SIGN,
					Items.BAMBOO_HANGING_SIGN,
					Items.CRIMSON_HANGING_SIGN,
					Items.WARPED_HANGING_SIGN);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.LEAF_SLABS);
			this.getOrCreateTagBuilder(AquiferTags.Items.LEAF_STAIRS);
			this.getOrCreateTagBuilder(AquiferTags.Items.LEAF_WALLS);
			this.getOrCreateTagBuilder(AquiferTags.Items.LEAF_CARPETS);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.LADDERS)
			.addTag(AquiferTags.Items.WOODEN_LADDERS);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.BARS)
			.add(Items.IRON_BARS);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.CHAINS)
			.add(Items.CHAIN);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.STICKS)
			.add(Items.STICK);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.BOOKSHELVES)
			.add(Items.BOOKSHELF);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.CRAFTING_TABLES)
			.add(Items.CRAFTING_TABLE);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.LOOMS)
			.add(Items.LOOM);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.CARTOGRAPHY_TABLES)
			.add(Items.CARTOGRAPHY_TABLE);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.FLETCHING_TABLES)
			.add(Items.FLETCHING_TABLE);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.SMITHING_TABLES)
			.add(Items.SMITHING_TABLE);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.TRIPWIRE_HOOKS)
			.add(Items.TRIPWIRE_HOOK);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.CRAFTED_BEEHIVES)
			.add(Items.BEEHIVE);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.LECTERNS)
			.add(Items.LECTERN);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.COMPOSTERS)
			.add(Items.COMPOSTER);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.CAULDRONS)
			.add(Items.CAULDRON);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.ENCHANTING_TABLES)
			.add(Items.ENCHANTING_TABLE);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.FURNACES)
			.add(Items.FURNACE);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.SMOKERS)
			.add(Items.SMOKER);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.BLAST_FURNACES)
			.add(Items.BLAST_FURNACE);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.STONECUTTERS)
			.add(Items.STONECUTTER);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.GRINDSTONES)
			.add(Items.GRINDSTONE);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.CHISELED_BOOKSHELVES)
			.add(Items.CHISELED_BOOKSHELF);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.PRESSURE_PLATES)
			.add(Items.HEAVY_WEIGHTED_PRESSURE_PLATE,
					Items.LIGHT_WEIGHTED_PRESSURE_PLATE,
					Items.STONE_PRESSURE_PLATE,
					Items.POLISHED_BLACKSTONE_PRESSURE_PLATE)
			.forceAddTag(ItemTags.WOODEN_PRESSURE_PLATES);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.WOOD_TOOL_MATERIALS)
			.forceAddTag(ItemTags.PLANKS);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.IRON_TOOL_MATERIALS)
			.add(Items.IRON_INGOT)
			.forceAddTag(ConventionalItemTags.IRON_INGOTS);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.DIAMOND_TOOL_MATERIALS)
			.add(Items.DIAMOND)
			.forceAddTag(ConventionalItemTags.DIAMOND_GEMS);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.GOLD_TOOL_MATERIALS)
			.add(Items.GOLD_INGOT)
			.forceAddTag(ConventionalItemTags.GOLD_INGOTS);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.NETHERITE_TOOL_MATERIALS)
			.add(Items.NETHERITE_INGOT)
			.forceAddTag(ConventionalItemTags.NETHERITE_INGOTS);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.LEATHER_ARMOR_MATERIALS)
			.add(Items.LEATHER)
			.forceAddTag(ConventionalItemTags.LEATHERS);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.CHAIN_ARMOR_MATERIALS)
			.add(Items.IRON_INGOT)
			.forceAddTag(ConventionalItemTags.IRON_INGOTS);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.IRON_ARMOR_MATERIALS)
			.add(Items.IRON_INGOT)
			.forceAddTag(ConventionalItemTags.IRON_INGOTS);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.DIAMOND_ARMOR_MATERIALS)
			.add(Items.DIAMOND)
			.forceAddTag(ConventionalItemTags.DIAMOND_GEMS);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.GOLD_ARMOR_MATERIALS)
			.add(Items.GOLD_INGOT)
			.forceAddTag(ConventionalItemTags.GOLD_INGOTS);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.NETHERITE_ARMOR_MATERIALS)
			.add(Items.NETHERITE_INGOT)
			.forceAddTag(ConventionalItemTags.NETHERITE_INGOTS);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.TURTLE_ARMOR_MATERIALS)
			.add(Items.TURTLE_SCUTE);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.ARMADILLO_ARMOR_MATERIALS)
			.add(Items.ARMADILLO_SCUTE);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.ELYTRA_REPAIR_MATERIALS)
			.add(Items.PHANTOM_MEMBRANE);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.RESPAWN_ANCHOR_CHARGE_ITEMS)
			.add(Items.GLOWSTONE);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.ENCHANTMENT_PAYMENT_ITEMS)
			.add(Items.LAPIS_LAZULI)
			.forceAddTag(ConventionalItemTags.LAPIS_GEMS);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.POWDER_SNOW_WALKABLE_EQUIPMENT)
			.add(Items.LEATHER_BOOTS);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.INFESTABLE)
			.add(Items.STONE,
					Items.COBBLESTONE,
					Items.STONE_BRICKS,
					Items.CRACKED_STONE_BRICKS,
					Items.MOSSY_STONE_BRICKS,
					Items.CHISELED_STONE_BRICKS,
					Items.DEEPSLATE);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.INFESTED)
			.add(Items.INFESTED_STONE,
					Items.INFESTED_COBBLESTONE,
					Items.INFESTED_STONE_BRICKS,
					Items.INFESTED_CRACKED_STONE_BRICKS,
					Items.INFESTED_MOSSY_STONE_BRICKS,
					Items.INFESTED_CHISELED_STONE_BRICKS,
					Items.INFESTED_DEEPSLATE);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.WAXABLES)
			.add(Items.HONEYCOMB);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.WOODEN_TOOLS)
			.add(Items.WOODEN_AXE,
					Items.WOODEN_HOE,
					Items.WOODEN_PICKAXE,
					Items.WOODEN_SHOVEL,
					Items.WOODEN_SWORD);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.STONE_TOOLS)
			.add(Items.STONE_AXE,
					Items.STONE_HOE,
					Items.STONE_PICKAXE,
					Items.STONE_SHOVEL,
					Items.STONE_SWORD);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.IRON_TOOLS)
			.add(Items.IRON_AXE,
					Items.IRON_HOE,
					Items.IRON_PICKAXE,
					Items.IRON_SHOVEL,
					Items.IRON_SWORD);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.DIAMOND_TOOLS)
			.add(Items.DIAMOND_AXE,
					Items.DIAMOND_HOE,
					Items.DIAMOND_PICKAXE,
					Items.DIAMOND_SHOVEL,
					Items.DIAMOND_SWORD);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.GOLD_TOOLS)
			.add(Items.GOLDEN_AXE,
					Items.GOLDEN_HOE,
					Items.GOLDEN_PICKAXE,
					Items.GOLDEN_SHOVEL,
					Items.GOLDEN_SWORD);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.NETHERITE_TOOLS)
			.add(Items.NETHERITE_AXE,
					Items.NETHERITE_HOE,
					Items.NETHERITE_PICKAXE,
					Items.NETHERITE_SHOVEL,
					Items.NETHERITE_SWORD);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.LEATHER_ARMORS)
			.add(Items.LEATHER_BOOTS,
					Items.LEATHER_CHESTPLATE,
					Items.LEATHER_HELMET,
					Items.LEATHER_HORSE_ARMOR,
					Items.LEATHER_LEGGINGS);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.CHAIN_ARMORS)
			.add(Items.CHAINMAIL_BOOTS,
					Items.CHAINMAIL_CHESTPLATE,
					Items.CHAINMAIL_HELMET,
					Items.CHAINMAIL_LEGGINGS);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.IRON_ARMORS)
			.add(Items.IRON_BOOTS,
					Items.IRON_CHESTPLATE,
					Items.IRON_HELMET,
					Items.IRON_HORSE_ARMOR,
					Items.IRON_LEGGINGS);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.DIAMOND_ARMORS)
			.add(Items.DIAMOND_BOOTS,
					Items.DIAMOND_CHESTPLATE,
					Items.DIAMOND_HELMET,
					Items.DIAMOND_HORSE_ARMOR,
					Items.DIAMOND_LEGGINGS);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.GOLD_ARMORS)
			.add(Items.GOLDEN_BOOTS,
					Items.GOLDEN_CHESTPLATE,
					Items.GOLDEN_HELMET,
					Items.GOLDEN_HORSE_ARMOR,
					Items.GOLDEN_LEGGINGS);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.NETHERITE_ARMORS)
			.add(Items.NETHERITE_BOOTS,
					Items.NETHERITE_CHESTPLATE,
					Items.NETHERITE_HELMET,
					Items.NETHERITE_LEGGINGS);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.TURTLE_ARMORS)
			.add(Items.TURTLE_HELMET);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.HORSE_ARMORS)
			.add(Items.LEATHER_HORSE_ARMOR,
					Items.IRON_HORSE_ARMOR,
					Items.DIAMOND_HORSE_ARMOR,
					Items.GOLDEN_HORSE_ARMOR);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.WOLF_ARMORS)
			.add(Items.WOLF_ARMOR);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.FIRE_PROOF)
			.add(Items.ANCIENT_DEBRIS,
					Items.NETHERITE_BLOCK,
					Items.NETHERITE_INGOT,
					Items.NETHERITE_SCRAP)
			.addTag(AquiferTags.Items.NETHERITE_TOOLS)
			.addTag(AquiferTags.Items.NETHERITE_ARMORS)
			.forceAddTag(ConventionalItemTags.NETHERITE_INGOTS)
			.forceAddTag(ConventionalItemTags.NETHERITE_SCRAP_ORES)
			.forceAddTag(ConventionalItemTags.STORAGE_BLOCKS_NETHERITE);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.EXPLOSION_PROOF)
			.add(Items.NETHER_STAR)
			.forceAddTag(ConventionalItemTags.NETHER_STARS);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.CACTUS_PROOF);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.VOID_PROOF);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.DESPAWN_PROOF);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.BANNER_PATTERNS)
			.add(Items.CREEPER_BANNER_PATTERN,
					Items.FLOW_BANNER_PATTERN,
					Items.FLOWER_BANNER_PATTERN,
					Items.GLOBE_BANNER_PATTERN,
					Items.GUSTER_BANNER_PATTERN,
					Items.MOJANG_BANNER_PATTERN,
					Items.PIGLIN_BANNER_PATTERN,
					Items.SKULL_BANNER_PATTERN);
			
			this.getOrCreateTagBuilder(ItemTags.SLABS)
			.addTag(AquiferTags.Items.LEAF_SLABS);
			
			this.getOrCreateTagBuilder(ItemTags.STAIRS)
			.addTag(AquiferTags.Items.LEAF_STAIRS);
			
			this.getOrCreateTagBuilder(ItemTags.WALLS)
			.addTag(AquiferTags.Items.WOODEN_WALLS)
			.addTag(AquiferTags.Items.LEAF_WALLS);
			
			this.getOrCreateTagBuilder(ItemTags.FENCE_GATES)
			.addTag(AquiferTags.Items.WOODEN_FENCE_GATES);
			
			this.getOrCreateTagBuilder(ItemTags.SIGNS)
			.addTag(AquiferTags.Items.WOODEN_SIGNS);
			
			this.getOrCreateTagBuilder(ItemTags.HANGING_SIGNS)
			.addTag(AquiferTags.Items.WOODEN_HANGING_SIGNS);
			
			this.getOrCreateTagBuilder(ConventionalItemTags.WOODEN_BARRELS)
			.addTag(AquiferTags.Items.WOODEN_BARRELS);
			
			this.getOrCreateTagBuilder(ConventionalItemTags.WOODEN_CHESTS)
			.addTag(AquiferTags.Items.WOODEN_CHESTS);
			
			this.getOrCreateTagBuilder(ConventionalItemTags.WOODEN_FENCE_GATES)
			.addTag(AquiferTags.Items.WOODEN_FENCE_GATES);
			
			this.getOrCreateTagBuilder(ConventionalItemTags.WOODEN_RODS)
			.addTag(AquiferTags.Items.STICKS);
			
			this.getOrCreateTagBuilder(ConventionalItemTags.VILLAGER_JOB_SITES)
			.addTag(AquiferTags.Items.LOOMS)
			.addTag(AquiferTags.Items.CARTOGRAPHY_TABLES)
			.addTag(AquiferTags.Items.FLETCHING_TABLES)
			.addTag(AquiferTags.Items.SMITHING_TABLES)
			.addTag(AquiferTags.Items.LECTERNS)
			.addTag(AquiferTags.Items.COMPOSTERS)
			.addTag(AquiferTags.Items.SMOKERS)
			.addTag(AquiferTags.Items.BLAST_FURNACES)
			.addTag(AquiferTags.Items.STONECUTTERS)
			.addTag(AquiferTags.Items.GRINDSTONES);
			
			this.getOrCreateTagBuilder(ConventionalItemTags.PLAYER_WORKSTATIONS_CRAFTING_TABLES)
			.addTag(AquiferTags.Items.CRAFTING_TABLES);
			
			this.getOrCreateTagBuilder(ConventionalItemTags.PLAYER_WORKSTATIONS_FURNACES)
			.addTag(AquiferTags.Items.FURNACES);
			
			this.getOrCreateTagBuilder(ItemTags.STONE_CRAFTING_MATERIALS)
			.forceAddTag(ConventionalItemTags.NORMAL_COBBLESTONES)
			.forceAddTag(ConventionalItemTags.DEEPSLATE_COBBLESTONES);
			
			this.getOrCreateTagBuilder(ItemTags.STONE_TOOL_MATERIALS)
			.forceAddTag(ConventionalItemTags.NORMAL_COBBLESTONES)
			.forceAddTag(ConventionalItemTags.DEEPSLATE_COBBLESTONES);
			
			this.getOrCreateTagBuilder(ItemTags.BEACON_PAYMENT_ITEMS)
			.forceAddTag(ConventionalItemTags.DIAMOND_GEMS)
			.forceAddTag(ConventionalItemTags.EMERALD_GEMS)
			.forceAddTag(ConventionalItemTags.GOLD_INGOTS)
			.forceAddTag(ConventionalItemTags.IRON_INGOTS)
			.forceAddTag(ConventionalItemTags.NETHERITE_INGOTS);
			
			this.getOrCreateTagBuilder(ItemTags.FREEZE_IMMUNE_WEARABLES)
			.addTag(AquiferTags.Items.LEATHER_ARMORS);
			
			this.getOrCreateTagBuilder(ItemTags.DYEABLE)
			.addTag(AquiferTags.Items.LEATHER_ARMORS);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.CHESTS_WOODEN_TRAPPED)
			.add(Items.TRAPPED_CHEST);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.CHESTS_WOODEN_NOT_TRAPPED)
			.add(Items.CHEST);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.CHESTS_NOT_TRAPPED)
			.addTag(AquiferTags.Items.CHESTS_WOODEN_NOT_TRAPPED);
			
			this.getOrCreateTagBuilder(ConventionalItemTags.CHESTS)
			.addTag(AquiferTags.Items.CHESTS_NOT_TRAPPED);
			
			this.getOrCreateTagBuilder(ConventionalItemTags.TRAPPED_CHESTS)
			.addTag(AquiferTags.Items.CHESTS_WOODEN_TRAPPED);
			
			this.getOrCreateTagBuilder(ConventionalItemTags.WOODEN_CHESTS)
			.addTag(AquiferTags.Items.CHESTS_WOODEN_NOT_TRAPPED)
			.addTag(AquiferTags.Items.CHESTS_WOODEN_TRAPPED);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.FLINTS)
			.add(Items.FLINT);
		}
	}
	
	public static class FluidsGen extends FabricTagProvider<Fluid> {
		public FluidsGen(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
			super(output, RegistryKeys.FLUID, registriesFuture);
		}
		
		@Override
		protected void configure(WrapperLookup wrapperLookup) {
			this.getOrCreateTagBuilder(AquiferTags.Fluids.SUGAR_CANE_MAY_GROW_BESIDE)
			.forceAddTag(FluidTags.WATER)
			.forceAddTag(ConventionalFluidTags.WATER);
			
			this.getOrCreateTagBuilder(AquiferTags.Fluids.LILY_PAD_MAY_PLACE_ON)
			.add(Fluids.WATER);
			
			this.getOrCreateTagBuilder(AquiferTags.Fluids.FROGSPAWN_MAY_PLACE_ON)
			.add(Fluids.WATER);
		}
	}
	
	public static class EntityTypesGen extends FabricTagProvider<EntityType<?>> {
		public EntityTypesGen(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
			super(output, RegistryKeys.ENTITY_TYPE, registriesFuture);
		}
		
		@Override
		protected void configure(WrapperLookup wrapperLookup) {
			this.getOrCreateTagBuilder(AquiferTags.EntityTypes.CAN_SPAWN_ON_LEAVES)
			.add(EntityType.OCELOT,
					EntityType.PARROT);
			
			this.getOrCreateTagBuilder(AquiferTags.EntityTypes.SPEED_IMMUNE);
			this.getOrCreateTagBuilder(AquiferTags.EntityTypes.SLOWNESS_IMMUNE);
			this.getOrCreateTagBuilder(AquiferTags.EntityTypes.HASTE_IMMUNE);
			this.getOrCreateTagBuilder(AquiferTags.EntityTypes.MINING_FATIGUE_IMMUNE);
			this.getOrCreateTagBuilder(AquiferTags.EntityTypes.STRENGTH_IMMUNE);
			this.getOrCreateTagBuilder(AquiferTags.EntityTypes.INSTANT_HEALTH_IMMUNE);
			this.getOrCreateTagBuilder(AquiferTags.EntityTypes.INSTANT_DAMAGE_IMMUNE);
			this.getOrCreateTagBuilder(AquiferTags.EntityTypes.JUMP_BOOST_IMMUNE);
			this.getOrCreateTagBuilder(AquiferTags.EntityTypes.NAUSEA_IMMUNE);
			this.getOrCreateTagBuilder(AquiferTags.EntityTypes.REGENERATION_IMMUNE)
			.forceAddTag(EntityTypeTags.IGNORES_POISON_AND_REGEN);
			this.getOrCreateTagBuilder(AquiferTags.EntityTypes.RESISTANCE_IMMUNE);
			this.getOrCreateTagBuilder(AquiferTags.EntityTypes.FIRE_RESISTANCE_IMMUNE);
			this.getOrCreateTagBuilder(AquiferTags.EntityTypes.WATER_BREATHING_IMMUNE);
			this.getOrCreateTagBuilder(AquiferTags.EntityTypes.INVISIBILITY_IMMUNE);
			this.getOrCreateTagBuilder(AquiferTags.EntityTypes.BLINDNESS_IMMUNE);
			this.getOrCreateTagBuilder(AquiferTags.EntityTypes.NIGHT_VISION_IMMUNE);
			this.getOrCreateTagBuilder(AquiferTags.EntityTypes.HUNGER_IMMUNE);
			this.getOrCreateTagBuilder(AquiferTags.EntityTypes.WEAKNESS_IMMUNE);
			this.getOrCreateTagBuilder(AquiferTags.EntityTypes.POISON_IMMUNE)
			.add(EntityType.CAVE_SPIDER,
					EntityType.SPIDER)
			.forceAddTag(EntityTypeTags.IGNORES_POISON_AND_REGEN);
			this.getOrCreateTagBuilder(AquiferTags.EntityTypes.WITHER_IMMUNE)
			.add(EntityType.WITHER,
					EntityType.WITHER_SKELETON);
			this.getOrCreateTagBuilder(AquiferTags.EntityTypes.HEALTH_BOOST_IMMUNE);
			this.getOrCreateTagBuilder(AquiferTags.EntityTypes.ABSORPTION_IMMUNE);
			this.getOrCreateTagBuilder(AquiferTags.EntityTypes.SATURATION_IMMUNE);
			this.getOrCreateTagBuilder(AquiferTags.EntityTypes.GLOWING_IMMUNE);
			this.getOrCreateTagBuilder(AquiferTags.EntityTypes.LEVITATION_IMMUNE);
			this.getOrCreateTagBuilder(AquiferTags.EntityTypes.LUCK_IMMUNE);
			this.getOrCreateTagBuilder(AquiferTags.EntityTypes.UNLUCK_IMMUNE);
			this.getOrCreateTagBuilder(AquiferTags.EntityTypes.SLOW_FALLING_IMMUNE);
			this.getOrCreateTagBuilder(AquiferTags.EntityTypes.CONDUIT_POWER_IMMUNE);
			this.getOrCreateTagBuilder(AquiferTags.EntityTypes.DOLPHINS_GRACE_IMMUNE);
			this.getOrCreateTagBuilder(AquiferTags.EntityTypes.BAD_OMEN_IMMUNE);
			this.getOrCreateTagBuilder(AquiferTags.EntityTypes.HERO_OF_THE_VILLAGE_IMMUNE);
			this.getOrCreateTagBuilder(AquiferTags.EntityTypes.DARKNESS_IMMUNE);
			this.getOrCreateTagBuilder(AquiferTags.EntityTypes.TRIAL_OMEN_IMMUNE);
			this.getOrCreateTagBuilder(AquiferTags.EntityTypes.RAID_OMEN_IMMUNE);
			this.getOrCreateTagBuilder(AquiferTags.EntityTypes.WIND_CHARGED_IMMUNE);
			this.getOrCreateTagBuilder(AquiferTags.EntityTypes.WEAVING_IMMUNE);
			this.getOrCreateTagBuilder(AquiferTags.EntityTypes.OOZING_IMMUNE)
			.forceAddTag(EntityTypeTags.IMMUNE_TO_OOZING);
			this.getOrCreateTagBuilder(AquiferTags.EntityTypes.INFESTED_IMMUNE)
			.forceAddTag(EntityTypeTags.IMMUNE_TO_INFESTED);
		}
	}
}