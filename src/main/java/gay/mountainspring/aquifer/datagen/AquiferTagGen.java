package gay.mountainspring.aquifer.datagen;

import java.util.concurrent.CompletableFuture;

import gay.mountainspring.aquifer.tag.AquiferTags;
import net.fabricmc.fabric.api.block.v1.BlockFunctionalityTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.BlockTagProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.EntityTypeTagProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.FluidTagProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.ItemTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalFluidTags;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.registry.tag.ItemTags;

public class AquiferTagGen {
	public static class BlocksGen extends BlockTagProvider {
		public BlocksGen(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
			super(output, registriesFuture);
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
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.SUPPORTS_NETHER_WART)
			.add(Blocks.SOUL_SAND);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.SUPPORTS_AZALEA)
			.add(Blocks.CLAY)
			.addTag(AquiferTags.Blocks.DIRT_LIKE)
			.addTag(AquiferTags.Blocks.FARMLAND_LIKE);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.SUPPORTS_NETHER_PLANT)
			.add(Blocks.SOUL_SOIL)
			.addTag(AquiferTags.Blocks.DIRT_LIKE)
			.addTag(AquiferTags.Blocks.FARMLAND_LIKE)
			.forceAddTag(BlockTags.NYLIUM);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.SUPPORTS_NETHER_FUNGUS)
			.add(Blocks.MYCELIUM)
			.addTag(AquiferTags.Blocks.SUPPORTS_NETHER_PLANT);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.SUPPORTS_WITHER_ROSE)
			.add(Blocks.NETHERRACK,
					Blocks.SOUL_SAND,
					Blocks.SOUL_SOIL)
			.addTag(AquiferTags.Blocks.DIRT_LIKE)
			.addTag(AquiferTags.Blocks.FARMLAND_LIKE)
			.forceAddTag(ConventionalBlockTags.NETHERRACKS);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.SUPPORTS_SUGAR_CANE)
			.addTag(AquiferTags.Blocks.DIRT_LIKE)
			.forceAddTag(BlockTags.SAND)
			.forceAddTag(ConventionalBlockTags.SANDS);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.SUPPORTS_SUGAR_CANE_ADJACENTLY)
			.add(Blocks.FROSTED_ICE);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.SUPPORTS_PROPAGULE)
			.add(Blocks.CLAY)
			.addTag(AquiferTags.Blocks.DIRT_LIKE)
			.addTag(AquiferTags.Blocks.FARMLAND_LIKE);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.SUPPORTS_PROPAGULE_ABOVE)
			.add(Blocks.MANGROVE_LEAVES);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.SUPPORTS_COCOA)
			.forceAddTag(BlockTags.JUNGLE_LOGS);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.SUPPORTS_CHORUS)
			.add(Blocks.END_STONE)
			.forceAddTag(ConventionalBlockTags.END_STONES);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.SUPPORTS_CACTUS)
			.add(Blocks.CACTUS)
			.forceAddTag(BlockTags.SAND)
			.forceAddTag(ConventionalBlockTags.SANDS);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.DOES_NOT_SUPPORT_KELP)
			.add(Blocks.MAGMA_BLOCK);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.DOES_NOT_SUPPORT_SEAGRASS)
			.add(Blocks.MAGMA_BLOCK);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.SUPPORTS_LILY_PAD)
			.add(Blocks.ICE,
					Blocks.FROSTED_ICE,
					Blocks.WATER);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.SUPPORTS_FROGSPAWN)
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
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.SUPPORTS_END_CRYSTAL)
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
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.SUPPORTS_BUBBLE_COLUMN_UP)
			.add(Blocks.SOUL_SAND);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.SUPPORTS_BUBBLE_COLUMN_DOWN)
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
			
			this.getOrCreateTagBuilder(ConventionalBlockTags.CHAINS)
			.addTag(AquiferTags.Blocks.CHAINS);
			
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
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.Conventional.WOODEN_TRAPPED_CHESTS)
			.add(Blocks.TRAPPED_CHEST);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.Conventional.WOODEN_NON_TRAPPED_CHESTS)
			.add(Blocks.CHEST);
			
			this.getOrCreateTagBuilder(AquiferTags.Blocks.Conventional.NON_TRAPPED_CHESTS)
			.addTag(AquiferTags.Blocks.Conventional.WOODEN_NON_TRAPPED_CHESTS);
			
			this.getOrCreateTagBuilder(ConventionalBlockTags.CHESTS)
			.addTag(AquiferTags.Blocks.Conventional.NON_TRAPPED_CHESTS);
			
			this.getOrCreateTagBuilder(ConventionalBlockTags.TRAPPED_CHESTS)
			.addTag(AquiferTags.Blocks.Conventional.WOODEN_TRAPPED_CHESTS);
			
			this.getOrCreateTagBuilder(ConventionalBlockTags.WOODEN_CHESTS)
			.addTag(AquiferTags.Blocks.Conventional.WOODEN_NON_TRAPPED_CHESTS)
			.addTag(AquiferTags.Blocks.Conventional.WOODEN_TRAPPED_CHESTS);
			
			this.getOrCreateTagBuilder(BlockFunctionalityTags.CAN_CLIMB_TRAPDOOR_ABOVE)
			.addTag(AquiferTags.Blocks.LADDERS);
		}
	}
	
	public static class ItemsGen extends ItemTagProvider {
		public ItemsGen(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture, BlockTagProvider blockTagProvider) {
			super(output, registriesFuture, blockTagProvider);
		}
		
		@Override
		protected void configure(WrapperLookup wrapperLookup) {
			this.copy(AquiferTags.Blocks.WOODEN_WALLS, AquiferTags.Items.WOODEN_WALLS);
			
			this.copy(AquiferTags.Blocks.WOODEN_FENCE_GATES, AquiferTags.Items.WOODEN_FENCE_GATES);
			
			this.copy(AquiferTags.Blocks.WOODEN_CHESTS, AquiferTags.Items.WOODEN_CHESTS);
			
			this.copy(AquiferTags.Blocks.WOODEN_BARRELS, AquiferTags.Items.WOODEN_BARRELS);
			
			this.copy(AquiferTags.Blocks.WOODEN_LADDERS, AquiferTags.Items.WOODEN_LADDERS);
			
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
			
			this.copy(AquiferTags.Blocks.LEAF_SLABS, AquiferTags.Items.LEAF_SLABS);
			this.copy(AquiferTags.Blocks.LEAF_STAIRS, AquiferTags.Items.LEAF_STAIRS);
			this.copy(AquiferTags.Blocks.LEAF_WALLS, AquiferTags.Items.LEAF_WALLS);
			this.copy(AquiferTags.Blocks.LEAF_CARPETS, AquiferTags.Items.LEAF_CARPETS);
			
			this.copy(AquiferTags.Blocks.LADDERS, AquiferTags.Items.LADDERS);
			
			this.copy(AquiferTags.Blocks.BARS, AquiferTags.Items.BARS);
			
			this.copy(AquiferTags.Blocks.CHAINS, AquiferTags.Items.CHAINS);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.STICKS)
			.add(Items.STICK);
			
			this.copy(AquiferTags.Blocks.BOOKSHELVES, AquiferTags.Items.BOOKSHELVES);
			
			this.copy(AquiferTags.Blocks.CRAFTING_TABLES, AquiferTags.Items.CRAFTING_TABLES);
			
			this.copy(AquiferTags.Blocks.LOOMS, AquiferTags.Items.LOOMS);
			
			this.copy(AquiferTags.Blocks.CARTOGRAPHY_TABLES, AquiferTags.Items.CARTOGRAPHY_TABLES);
			
			this.copy(AquiferTags.Blocks.FLETCHING_TABLES, AquiferTags.Items.FLETCHING_TABLES);
			
			this.copy(AquiferTags.Blocks.SMITHING_TABLES, AquiferTags.Items.SMITHING_TABLES);
			
			this.copy(AquiferTags.Blocks.TRIPWIRE_HOOKS, AquiferTags.Items.TRIPWIRE_HOOKS);
			
			this.copy(AquiferTags.Blocks.CRAFTED_BEEHIVES, AquiferTags.Items.CRAFTED_BEEHIVES);
			
			this.copy(AquiferTags.Blocks.LECTERNS, AquiferTags.Items.LECTERNS);
			
			this.copy(AquiferTags.Blocks.COMPOSTERS, AquiferTags.Items.COMPOSTERS);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.CAULDRONS)
			.add(Items.CAULDRON);
			
			this.copy(AquiferTags.Blocks.ENCHANTING_TABLES, AquiferTags.Items.ENCHANTING_TABLES);
			
			this.copy(AquiferTags.Blocks.FURNACES, AquiferTags.Items.FURNACES);
			
			this.copy(AquiferTags.Blocks.SMOKERS, AquiferTags.Items.SMOKERS);
			
			this.copy(AquiferTags.Blocks.BLAST_FURNACES, AquiferTags.Items.BLAST_FURNACES);
			
			this.copy(AquiferTags.Blocks.STONECUTTERS, AquiferTags.Items.STONECUTTERS);
			
			this.copy(AquiferTags.Blocks.GRINDSTONES, AquiferTags.Items.GRINDSTONES);
			
			this.copy(AquiferTags.Blocks.CHISELED_BOOKSHELVES, AquiferTags.Items.CHISELED_BOOKSHELVES);
			
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
			
			this.copy(AquiferTags.Blocks.INFESTABLE, AquiferTags.Items.INFESTABLE);
			
			this.copy(AquiferTags.Blocks.INFESTED, AquiferTags.Items.INFESTED);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.WAX_ITEMS)
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
			
			this.copy(BlockTags.SLABS, ItemTags.SLABS);
			
			this.copy(BlockTags.STAIRS, ItemTags.STAIRS);
			
			this.copy(BlockTags.WALLS, ItemTags.WALLS);
			
			this.copy(BlockTags.FENCE_GATES, ItemTags.FENCE_GATES);
			
			this.getOrCreateTagBuilder(ItemTags.SIGNS)
			.addTag(AquiferTags.Items.WOODEN_SIGNS);
			
			this.getOrCreateTagBuilder(ItemTags.HANGING_SIGNS)
			.addTag(AquiferTags.Items.WOODEN_HANGING_SIGNS);
			
			this.copy(ConventionalBlockTags.WOODEN_BARRELS, ConventionalItemTags.WOODEN_BARRELS);
			
			this.copy(ConventionalBlockTags.WOODEN_CHESTS, ConventionalItemTags.WOODEN_CHESTS);
			
			this.copy(ConventionalBlockTags.WOODEN_FENCE_GATES, ConventionalItemTags.WOODEN_FENCE_GATES);
			
			this.getOrCreateTagBuilder(ConventionalItemTags.WOODEN_RODS)
			.addTag(AquiferTags.Items.STICKS);
			
			this.copy(ConventionalBlockTags.VILLAGER_JOB_SITES, ConventionalItemTags.VILLAGER_JOB_SITES);
			
			this.copy(ConventionalBlockTags.PLAYER_WORKSTATIONS_CRAFTING_TABLES, ConventionalItemTags.PLAYER_WORKSTATIONS_CRAFTING_TABLES);
			
			this.copy(ConventionalBlockTags.PLAYER_WORKSTATIONS_FURNACES, ConventionalItemTags.PLAYER_WORKSTATIONS_FURNACES);
			
			this.copy(ConventionalBlockTags.CHAINS, ConventionalItemTags.CHAINS);
			
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
			
			this.copy(AquiferTags.Blocks.Conventional.WOODEN_TRAPPED_CHESTS, AquiferTags.Items.Conventional.WOODEN_TRAPPED_CHESTS);
			
			this.copy(AquiferTags.Blocks.Conventional.WOODEN_NON_TRAPPED_CHESTS, AquiferTags.Items.Conventional.WOODEN_NON_TRAPPED_CHESTS);
			
			this.copy(AquiferTags.Blocks.Conventional.NON_TRAPPED_CHESTS, AquiferTags.Items.Conventional.NON_TRAPPED_CHESTS);
			
			this.copy(ConventionalBlockTags.CHESTS, ConventionalItemTags.CHESTS);
			
			this.copy(ConventionalBlockTags.TRAPPED_CHESTS, ConventionalItemTags.TRAPPED_CHESTS);
			
			this.copy(ConventionalBlockTags.WOODEN_CHESTS, ConventionalItemTags.WOODEN_CHESTS);
			
			this.getOrCreateTagBuilder(AquiferTags.Items.Conventional.FLINTS)
			.add(Items.FLINT);
		}
	}
	
	public static class FluidsGen extends FluidTagProvider {
		public FluidsGen(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
			super(output, registriesFuture);
		}
		
		@Override
		protected void configure(WrapperLookup wrapperLookup) {
			this.getOrCreateTagBuilder(AquiferTags.Fluids.SUPPORTS_SUGAR_CANE_ADJACENTLY)
			.forceAddTag(FluidTags.WATER)
			.forceAddTag(ConventionalFluidTags.WATER);
			
			this.getOrCreateTagBuilder(AquiferTags.Fluids.SUPPORTS_LILY_PAD)
			.add(Fluids.WATER);
			
			this.getOrCreateTagBuilder(AquiferTags.Fluids.SUPPORTS_FROGSPAWN)
			.add(Fluids.WATER);
		}
	}
	
	public static class EntityTypesGen extends EntityTypeTagProvider {
		public EntityTypesGen(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
			super(output, registriesFuture);
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