package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;

import gay.mountainspring.aquifer.Aquifer;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class AquiferBlockTypes {
	private AquiferBlockTypes() {}
	
	public static void init () {}
	
	static {
		register("aquifer_cauldron", AquiferCauldronBlock.CODEC);
		register("aquifer_fence_gate", AquiferFenceGateBlock.CODEC);
		register("aquifer_lava_cauldron", AquiferLavaCauldronBlock.CODEC);
		register("aquifer_powder_snow_cauldron", AquiferPowderSnowCauldronBlock.CODEC);
		register("aquifer_short_plant", AquiferShortPlantBlock.CODEC);
		register("aquifer_water_cauldron", AquiferWaterCauldronBlock.CODEC);
		register("column", ColumnBlock.CODEC);
		register("drop_experience_column", ExperienceDroppingColumnBlock.CODEC);
		register("drop_experience_slab", ExperienceDroppingSlabBlock.CODEC);
		register("drop_experience_stairs", ExperienceDroppingStairsBlock.CODEC);
		register("drop_experience_step", ExperienceDroppingStepBlock.CODEC);
		register("drop_experience_vertical_slab", ExperienceDroppingVerticalSlabBlock.CODEC);
		register("drop_experience_wall", ExperienceDroppingWallBlock.CODEC);
		register("hot_floor", HotFloorBlock.CODEC);
		register("hot_floor_column", HotFloorColumnBlock.CODEC);
		register("hot_floor_fence", HotFloorFenceBlock.CODEC);
		register("hot_floor_fence_gate", HotFloorFenceGateBlock.CODEC);
		register("hot_floor_slab", HotFloorSlabBlock.CODEC);
		register("hot_floor_stairs", HotFloorStairsBlock.CODEC);
		register("hot_floor_step", HotFloorStepBlock.CODEC);
		register("hot_floor_vertical_slab", HotFloorVerticalSlabBlock.CODEC);
		register("hot_floor_wall", HotFloorWallBlock.CODEC);
		register("leaf_carpet", LeafCarpetBlock.CODEC);
		register("leaf_column", LeafColumnBlock.CODEC);
		register("leaf_slab", LeafSlabBlock.CODEC);
		register("leaf_stairs", LeafStairsBlock.CODEC);
		register("leaf_step", LeafStepBlock.CODEC);
		register("leaf_vertical_slab", LeafVerticalSlabBlock.CODEC);
		register("leaf_wall", LeafWallBlock.CODEC);
		register("orientable_slab", OrientableSlabBlock.CODEC);
		register("orientable_stairs", OrientableStairsBlock.CODEC);
		register("orientable_step", OrientableStepBlock.CODEC);
		register("orientable_vertical_slab", OrientableVerticalSlabBlock.CODEC);
		register("oxidizable_button", OxidizableButtonBlock.CODEC);
		register("oxidizable_cauldron", OxidizableCauldronBlock.CODEC);
		register("oxidizable_chain", OxidizableChainBlock.CODEC);
		register("oxidizable_column", OxidizableColumnBlock.CODEC);
		register("oxidizable_fence", OxidizableFenceBlock.CODEC);
		register("oxidizable_fence_gate", OxidizableFenceGateBlock.CODEC);
		register("oxidizable_grate_column", OxidizableGrateColumnBlock.CODEC);
		register("oxidizable_grate_slab", OxidizableGrateSlabBlock.CODEC);
		register("oxidizable_grate_stairs", OxidizableGrateStairsBlock.CODEC);
		register("oxidizable_grate_step", OxidizableGrateStepBlock.CODEC);
		register("oxidizable_grate_vertical_slab", OxidizableGrateVerticalSlabBlock.CODEC);
		register("oxidizable_grate_wall", OxidizableGrateWallBlock.CODEC);
		register("oxidizable_hanging_sign", OxidizableHangingSignBlock.CODEC);
		register("oxidizable_ladder", OxidizableLadderBlock.CODEC);
		register("oxidizable_lantern", OxidizableLanternBlock.CODEC);
		register("oxidizable_lava_cauldron", OxidizableLavaCauldronBlock.CODEC);
		register("oxidizable_pane", OxidizablePaneBlock.CODEC);
		register("oxidizable_powder_snow_cauldron", OxidizablePowderSnowCauldronBlock.CODEC);
		register("oxidizable_pressure_plate", OxidizablePressurePlateBlock.CODEC);
		register("oxidizable_sign", OxidizableSignBlock.CODEC);
		register("oxidizable_step", OxidizableStepBlock.CODEC);
		register("oxidizable_vertical_slab", OxidizableVerticalSlabBlock.CODEC);
		register("oxidizable_wall", OxidizableWallBlock.CODEC);
		register("oxidizable_wall_hanging_sing", OxidizableWallHangingSignBlock.CODEC);
		register("oxidizable_wall_sign", OxidizableWallSignBlock.CODEC);
		register("oxidizable_water_cauldron", OxidizableWaterCauldronBlock.CODEC);
		register("snowy_column", SnowyColumnBlock.CODEC);
		register("snowy_slab", SnowySlabBlock.CODEC);
		register("snowy_stairs", SnowyStairsBlock.CODEC);
		register("snowy_step", SnowyStepBlock.CODEC);
		register("snowy_vertical_slab", SnowyVerticalSlabBlock.CODEC);
		register("snowy_wall", SnowyWallBlock.CODEC);
		register("stained_glass_column", StainedGlassColumnBlock.CODEC);
		register("stained_glass_slab", StainedGlassSlabBlock.CODEC);
		register("stained_glass_stairs", StainedGlassStairsBlock.CODEC);
		register("stained_glass_step", StainedGlassStepBlock.CODEC);
		register("stained_glass_vertical_slab", StainedGlassVerticalSlabBlock.CODEC);
		register("stained_glass_wall", StainedGlassWallBlock.CODEC);
		register("step", StepBlock.CODEC);
		register("translucent_column", TranslucentColumnBlock.CODEC);
		register("translucent_slab", TranslucentSlabBlock.CODEC);
		register("translucent_stairs", TranslucentStairsBlock.CODEC);
		register("translucent_step", TranslucentStepBlock.CODEC);
		register("translucent_vertical_slab", TranslucentVerticalSlabBlock.CODEC);
		register("translucent_wall", TranslucentWallBlock.CODEC);
		register("transparent_column", TransparentColumnBlock.CODEC);
		register("transparent_slab", TransparentSlabBlock.CODEC);
		register("transparent_stairs", TransparentStairsBlock.CODEC);
		register("transparent_step", TransparentStepBlock.CODEC);
		register("transparent_vertical_slab", TransparentVerticalSlabBlock.CODEC);
		register("transparent_wall", TransparentWallBlock.CODEC);
		register("typed_chest", TypedChestBlock.CODEC);
		register("typed_trapped_chest", TypedTrappedChestBlock.CODEC);
		register("vertical_slab", VerticalSlabBlock.CODEC);
		register("waxed_pane", WaxedPaneBlock.CODEC);
	}
	
	private static void register(String name, MapCodec<? extends Block> codec) {
		Registry.register(Registries.BLOCK_TYPE, Identifier.of(Aquifer.MOD_ID, name), codec);
	}
}