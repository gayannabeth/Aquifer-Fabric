package gay.mountainspring.aquifer.util;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import gay.mountainspring.aquifer.mixin.AbstractBlockAccessor;
import gay.mountainspring.aquifer.mixin.PointOfInterestTypesAccessor;
import gay.mountainspring.aquifer.tag.AquiferTags;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.AnvilBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.ShortPlantBlock;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.WoodType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.poi.PointOfInterestType;

public class BlockUtil {
	private BlockUtil() {}
	
	public static void init () {}
	
	private static final Map<RegistryKey<PointOfInterestType>, Set<BlockState>> POI_SUPPORTED_BLOCKS = Maps.newHashMap();
	
	private static final Map<Block, Block> BLOCK_STRIPPING_MAP = Maps.newHashMap();
	
	private static final Map<Block, Block> BLOCK_FLATTENING_MAP = Maps.newHashMap();
	
	private static final BiMap<ShortPlantBlock, TallPlantBlock> SHORT_TO_TALL_PLANTS = HashBiMap.create();
	
	private static final Map<Block, Function<BlockState, BlockState>> ANVIL_DAMAGE_MAP = Maps.newHashMap();
	
	/**
	 * 
	 * @return An {@link ImmutableMap} copy of the {@link PointOfInterestType} Blocks {@link Map}
	 */
	public static Map<RegistryKey<PointOfInterestType>, Set<BlockState>> poiSupportedBlocks() {
		return ImmutableMap.copyOf(POI_SUPPORTED_BLOCKS);
	}
	
	/**
	 * 
	 * @return An {@link ImmutableMap} copy of the stripping {@link Map}
	 */
	public static Map<Block, Block> blockStrippingMap() {
		return ImmutableMap.copyOf(BLOCK_STRIPPING_MAP);
	}
	
	/**
	 * 
	 * @return An {@link ImmutableMap} copy of the flattening {@link Map}
	 */
	public static Map<Block, Block> blockFlatteningMap() {
		return ImmutableMap.copyOf(BLOCK_FLATTENING_MAP);
	}
	
	/**
	 * 
	 * @return An {@link ImmutableBiMap} copy of the short to tall plants {@link BiMap}
	 */
	public static BiMap<ShortPlantBlock, TallPlantBlock> shortToTallPlantsMap() {
		return ImmutableBiMap.copyOf(SHORT_TO_TALL_PLANTS);
	}
	
	/**
	 * 
	 * @return An {@link ImmutableMap} copy the anvil damage {@link Map}
	 */
	public static Map<Block, Function<BlockState, BlockState>> anvilDamageMap() {
		return ImmutableMap.copyOf(ANVIL_DAMAGE_MAP);
	}
	
	/**
	 * Adds a pair of blocks that can be stripped from one to another with an axe.
	 * This exists because default Fabric stripping registry only works for {@link PillarBlock}s
	 * @param unstripped the unstripped block variant
	 * @param stripped the stripped block variant
	 */
	public static void addStrippingBlockPair(Block unstripped, Block stripped) {
		Objects.requireNonNull(unstripped);
		Objects.requireNonNull(stripped);
		
		BLOCK_STRIPPING_MAP.put(unstripped, stripped);
	}
	
	/**
	 * Adds a pair of blocks that can be flattened from one to another with a shovel.
	 * This exists because default Fabric flattening registry does not preserve block states
	 * @param unflattened the unflattened block variant
	 * @param flattened the flattened block variant
	 */
	public static void addFlatteningBlockPair(Block unflattened, Block flattened) {
		Objects.requireNonNull(unflattened);
		Objects.requireNonNull(flattened);
		
		BLOCK_FLATTENING_MAP.put(unflattened, flattened);
	}
	
	/**
	 * Adds a pair of plant blocks, one short, one tall, for use in bonemealing the short to tall. Reversible for mods that want that functionality.
	 * @param shortPlant the short plant
	 * @param tallPlant the tall plant
	 */
	public static void addShortAndTallPlantPair(Block shortPlant, Block tallPlant) {
		if (shortPlant instanceof ShortPlantBlock sPlant) {
			if (tallPlant instanceof TallPlantBlock tPlant) {
				addShortAndTallPlantPair(sPlant, tPlant);
			} else {
				throw new IllegalArgumentException(String.format("{} is not a tall plant block!", tallPlant));
			}
		} else {
			throw new IllegalArgumentException(String.format("{} is not a short plant block!", shortPlant));
		}
	}
	
	/**
	 * Adds a pair of plant blocks, one short, one tall, for use in bonemealing the short to tall. Reversible for mods that want that functionality.
	 * @param shortPlant the short plant
	 * @param tallPlant the tall plant
	 */
	public static void addShortAndTallPlantPair(ShortPlantBlock shortPlant, TallPlantBlock tallPlant) {
		Objects.requireNonNull(shortPlant);
		Objects.requireNonNull(tallPlant);
		
		if (SHORT_TO_TALL_PLANTS.containsKey(shortPlant))
			throw new IllegalArgumentException(String.format("{} is already a register short plant!", shortPlant));
		if (SHORT_TO_TALL_PLANTS.containsValue(tallPlant))
				throw new IllegalArgumentException(String.format("{} is already a register tall plant!", tallPlant));
		
		SHORT_TO_TALL_PLANTS.put(shortPlant, tallPlant);
	}
	
	public static void addAnvilTrio(Block undamaged, Block slightlyDamaged, Block veryDamaged) {
		if (!(undamaged instanceof AnvilBlock && slightlyDamaged instanceof AnvilBlock && veryDamaged instanceof AnvilBlock)) {
			throw new IllegalArgumentException(String.format("One of %s, %s, and %s is not an anvil!", undamaged, slightlyDamaged, veryDamaged));
		}
		
		ANVIL_DAMAGE_MAP.put(undamaged, state -> slightlyDamaged.getDefaultState().with(AnvilBlock.FACING, state.get(AnvilBlock.FACING)));
		ANVIL_DAMAGE_MAP.put(slightlyDamaged, state -> veryDamaged.getDefaultState().with(AnvilBlock.FACING, state.get(AnvilBlock.FACING)));
		ANVIL_DAMAGE_MAP.put(veryDamaged, state -> null);
	}
	
	public static void addUnbreakableAnvil(Block anvil) {
		if (!(anvil instanceof AnvilBlock)) {
			throw new IllegalArgumentException(String.format("%s is not an anvil!", anvil));
		}
		
		ANVIL_DAMAGE_MAP.put(anvil, state -> state);
	}
	
	/**
	 * Adds custom block states from a set of blocks to support existing {@link PointOfInterestType}s
	 * @param key the {@link RegistryKey} of the {@link PointOfInterestType}
	 * @param blocks an array of {@link Block}s to add
	 */
	public static void addBlocksToPOI(RegistryKey<PointOfInterestType> key, Block... blocks) {
		addBlocksToPOI(key, ImmutableSet.copyOf(blocks));
	}
	
	/**
	 * Adds custom block states from a set of blocks to support existing {@link PointOfInterestType}s
	 * @param key the {@link RegistryKey} of the {@link PointOfInterestType}
	 * @param blocks a {@link Set} containing the {@link Block}s to be added
	 */
	public static void addBlocksToPOI(RegistryKey<PointOfInterestType> key, Set<Block> blocks) {
		addBlockStatesToPOI(key, blocks
				.stream()
				.flatMap(block -> block.getStateManager().getStates().stream())
				.collect(ImmutableSet.toImmutableSet()));
	}
	
	/**
	 * Adds custom block states to support existing {@link PointOfInterestType}s
	 * @param key the {@link RegistryKey} of the {@link PointOfInterestType}
	 * @param states an array of {@link BlockState}s to add
	 */
	public static void addBlockStatesToPOI(RegistryKey<PointOfInterestType> key, BlockState... states) {
		addBlockStatesToPOI(key, ImmutableSet.copyOf(states));
	}
	
	/**
	 * Adds custom block states to support existing {@link PointOfInterestType}s
	 * @param key the {@link RegistryKey} of the {@link PointOfInterestType}
	 * @param states a {@link Set} containing the {@link BlockState}s to be added
	 */
	public static void addBlockStatesToPOI(RegistryKey<PointOfInterestType> key, Set<BlockState> states) {
		if (!POI_SUPPORTED_BLOCKS.containsKey(key)) {
			POI_SUPPORTED_BLOCKS.put(key, Sets.newHashSet());
		}
		
		POI_SUPPORTED_BLOCKS.get(key).addAll(states);
		
		for (BlockState state : states) {
			if (!PointOfInterestTypesAccessor.getStateToType().containsKey(state)) {
				PointOfInterestTypesAccessor.getStateToType().put(state, Registries.POINT_OF_INTEREST_TYPE.entryOf(key));
			}
		}
	}
	
	/**
	 * Changes a block's sounds to a custom {@link BlockSoundGroup}
	 * If you want blocks you add that copy the given block's settings to have the same sounds, you should run this method before creating the custom blocks
	 * All vanilla blocks you wish to change must be changed individually, even if they copy another block's settings
	 * @param block the {@link Block} to change the sounds of
	 * @param soundGroup the {@link BlockSoundGroup} to change to
	 */
	public static void setBlockSoundGroup(Block block, BlockSoundGroup soundGroup) {
		((AbstractBlockAccessor) block).setSoundGroup(soundGroup);
		block.getSettings().sounds(soundGroup);
	}
	
	public static void makeHaveChestVariant(WoodType type) {
		if (type != null) HAS_CHEST_VARIANT.add(type);
	}
	
	private static final Set<WoodType> HAS_CHEST_VARIANT = Sets.newHashSet();
	
	public static boolean hasChestVariant(WoodType type) {
		return type != null && HAS_CHEST_VARIANT.contains(type);
	}
	
	static {
		FlammableBlockRegistry reg = FlammableBlockRegistry.getDefaultInstance();
		reg.add(AquiferTags.Blocks.WOODEN_FENCE_GATES, 5, 20);
		reg.add(AquiferTags.Blocks.LEAF_CARPETS, 30, 60);
		reg.add(AquiferTags.Blocks.LEAF_SLABS, 30, 60);
		reg.add(AquiferTags.Blocks.LEAF_STAIRS, 30, 60);
		reg.add(AquiferTags.Blocks.LEAF_WALLS, 30, 60);
		reg.add(AquiferTags.Blocks.BOOKSHELVES, 30, 20);
		reg.add(AquiferTags.Blocks.LECTERNS, 30, 20);
		reg.add(AquiferTags.Blocks.COMPOSTERS, 5, 20);
		reg.add(AquiferTags.Blocks.CRAFTED_BEEHIVES, 5, 20);
	}
}