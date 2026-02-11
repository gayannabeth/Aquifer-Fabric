package gay.mountainspring.aquifer.datagen;

import java.util.List;
import java.util.function.Function;

import com.mojang.datafixers.util.Pair;

import gay.mountainspring.aquifer.util.BooleanPair;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.block.PaneBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.block.enums.BlockFace;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.block.enums.StairShape;
import net.minecraft.block.enums.WallShape;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.BlockStateSupplier;
import net.minecraft.data.client.BlockStateVariant;
import net.minecraft.data.client.BlockStateVariantMap;
import net.minecraft.data.client.ModelIds;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.MultipartBlockStateSupplier;
import net.minecraft.data.client.TextureKey;
import net.minecraft.data.client.TextureMap;
import net.minecraft.data.client.TexturedModel;
import net.minecraft.data.client.VariantSettings;
import net.minecraft.data.client.VariantsBlockStateSupplier;
import net.minecraft.data.client.When;
import net.minecraft.item.Items;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

public class AquiferBlockStates {
	private AquiferBlockStates() {}
	
	public static void registerSingleton(BlockStateModelGenerator gen, Block block, Identifier model) {
		gen.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(block, model));
		gen.registerParentedItemModel(block, model);
	}
	
	public static void registerNorthDefaultHorizontalRotated(BlockStateModelGenerator gen, Block block, Identifier model) {
		gen.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(block, model).coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates()));
		gen.registerParentedItemModel(block, model);
	}
	
	public static void registerSouthDefaultHorizontalRotated(BlockStateModelGenerator gen, Block block, Identifier model) {
		gen.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(block, model).coordinate(BlockStateModelGenerator.createSouthDefaultHorizontalRotationStates()));
		gen.registerParentedItemModel(block, model);
	}
	
	public static void registerParentedAxisRotated(BlockStateModelGenerator gen, Block modelSource, Block child) {
		Identifier model = ModelIds.getBlockModelId(modelSource);
		
		gen.blockStateCollector.accept(BlockStateModelGenerator.createAxisRotatedBlockState(child, model));
		gen.registerParentedItemModel(child, model);
	}
	
	public static void registerParentedAxisRotatedWithHorizontal(BlockStateModelGenerator gen, Block modelSource, Block child) {
		Identifier model = ModelIds.getBlockModelId(modelSource);
		Identifier modelHorizontal = ModelIds.getBlockSubModelId(modelSource, "_horizontal");
		
		gen.blockStateCollector.accept(BlockStateModelGenerator.createAxisRotatedBlockState(child, model, modelHorizontal));
		gen.registerParentedItemModel(child, model);
	}
	
	public static void registerParentedAxisRotatedWithAllAxisModels(BlockStateModelGenerator gen, Block modelSource, Block child) {
		Identifier modelX = ModelIds.getBlockSubModelId(modelSource, "_x");
		Identifier modelY = ModelIds.getBlockSubModelId(modelSource, "_y");
		Identifier modelZ = ModelIds.getBlockSubModelId(modelSource, "_z");
		
		gen.blockStateCollector.accept(VariantsBlockStateSupplier.create(child).coordinate(BlockStateVariantMap.create(Properties.AXIS)
				.register(Direction.Axis.X, BlockStateVariant.create().put(VariantSettings.MODEL, modelX))
				.register(Direction.Axis.Y, BlockStateVariant.create().put(VariantSettings.MODEL, modelY))
				.register(Direction.Axis.Z, BlockStateVariant.create().put(VariantSettings.MODEL, modelZ))));
		gen.registerParentedItemModel(child, modelY);
	}
	
	public static void registerSlab(BlockStateModelGenerator gen, Block slab, Block textureSource) {
		registerSlab(gen, slab, TextureMap.all(textureSource), ModelIds.getBlockModelId(textureSource));
	}
	
	public static void registerSlab(BlockStateModelGenerator gen, Block slab, TextureMap texutres, Identifier doubleModel) {
		registerSlab(gen, slab,
				Models.SLAB.upload(slab, texutres, gen.modelCollector),
				Models.SLAB_TOP.upload(slab, texutres, gen.modelCollector), doubleModel);
	}
	
	public static void registerSlab(BlockStateModelGenerator gen, Block slab, Identifier bottomModel, Identifier topModel, Identifier doubleModel) {
		gen.blockStateCollector.accept(BlockStateModelGenerator.createSlabBlockState(slab, bottomModel, topModel, doubleModel));
		gen.registerParentedItemModel(slab, bottomModel);
	}
	
	public static void registerBoolPropertySlab(BlockStateModelGenerator gen, Block slab, BooleanProperty property, Block textureSource) {
		registerBoolPropertySlab(gen, slab, property, TextureMap.all(textureSource), ModelIds.getBlockModelId(textureSource));
	}
	
	public static void registerBoolPropertySlab(BlockStateModelGenerator gen, Block slab, BooleanProperty property, Block textureSource, String trueTextureSuffix) {
		registerBoolPropertySlab(gen, slab, property, TextureMap.all(textureSource), TextureMap.all(TextureMap.getSubId(textureSource, trueTextureSuffix)), ModelIds.getBlockModelId(textureSource), ModelIds.getBlockSubModelId(textureSource, trueTextureSuffix));
	}
	
	public static void registerBoolPropertySlab(BlockStateModelGenerator gen, Block slab, BooleanProperty property, TextureMap textures, Identifier doubleModel) {
		Identifier bottomModel = Models.SLAB.upload(slab, textures, gen.modelCollector);
		Identifier topModel = Models.SLAB_TOP.upload(slab, textures, gen.modelCollector);
		registerBoolPropertySlab(gen, slab, property, bottomModel, bottomModel, topModel, topModel, doubleModel, doubleModel);
	}
	
	public static void registerBoolPropertySlab(BlockStateModelGenerator gen, Block slab, BooleanProperty property, TextureMap falseTextures, TextureMap trueTextures, Identifier doubleFalseModel, Identifier doubleTrueModel) {
		registerBoolPropertySlab(gen, slab, property,
				Models.SLAB.upload(slab, falseTextures, gen.modelCollector),
				Models.SLAB.upload(slab, "_on", trueTextures, gen.modelCollector),
				Models.SLAB_TOP.upload(slab, falseTextures, gen.modelCollector),
				Models.SLAB_TOP.upload(slab, "_on", trueTextures, gen.modelCollector),
				doubleFalseModel,
				doubleTrueModel);
	}
	
	public static void registerBoolPropertySlab(BlockStateModelGenerator gen, Block slab, BooleanProperty property, Identifier bottomFalseModel, Identifier bottomTrueModel, Identifier topFalseModel, Identifier topTrueModel, Identifier doubleFalseModel, Identifier doubleTrueModel) {
		gen.blockStateCollector.accept(createBoolPropertySlabBlockState(slab, property, bottomFalseModel, bottomTrueModel, topFalseModel, topTrueModel, doubleFalseModel, doubleTrueModel));
		gen.registerParentedItemModel(slab, bottomFalseModel);
	}
	
	public static void registerTerrainSlab(BlockStateModelGenerator gen, Block slab, Block textureSource, Block bottomTextureSource) {
		TextureMap textures = TextureMap.sideAndTop(textureSource).put(TextureKey.BOTTOM, TextureMap.getId(bottomTextureSource));
		registerSlab(gen, slab,
				AquiferModels.TERRAIN_SLAB.upload(slab, textures, gen.modelCollector),
				AquiferModels.TERRAIN_SLAB_TOP.upload(slab, textures, gen.modelCollector),
				AquiferModels.TERRAIN_SLAB_DOUBLE.upload(slab, textures, gen.modelCollector));
	}
	
	public static void registerSnowyTerrainSlab(BlockStateModelGenerator gen, Block slab, Block textureSource, Block bottomTextureSource, Block snowyModelSource) {
		registerSnowyTerrainSlab(gen, slab, textureSource, bottomTextureSource, ModelIds.getBlockSubModelId(snowyModelSource, "_snowy"), ModelIds.getBlockSubModelId(snowyModelSource, "_snowy_top"), ModelIds.getBlockSubModelId(snowyModelSource, "_snowy_double"));
	}
	
	public static void registerSnowyTerrainSlab(BlockStateModelGenerator gen, Block slab, Block textureSource, Block bottomTextureSource, Identifier bottomSnowyModel, Identifier topSnowyModel, Identifier doubleSnowyModel) {
		TextureMap textures = TextureMap.sideAndTop(textureSource).put(TextureKey.BOTTOM, TextureMap.getId(bottomTextureSource));
		registerBoolPropertySlab(gen, slab, Properties.SNOWY,
				AquiferModels.TERRAIN_SLAB.upload(slab, textures, gen.modelCollector),
				bottomSnowyModel,
				AquiferModels.TERRAIN_SLAB_TOP.upload(slab, textures, gen.modelCollector),
				topSnowyModel,
				AquiferModels.TERRAIN_SLAB_DOUBLE.upload(slab, textures, gen.modelCollector),
				doubleSnowyModel);
	}
	
	public static void registerOrientableSlab(BlockStateModelGenerator gen, Block slab, Block textureSource) {
		registerOrientableSlab(gen, slab, textureSource, TextureMap::sideAndEndForTop);
	}
	
	public static void registerOrientableSlab(BlockStateModelGenerator gen, Block slab, Block textureSource, Function<Block, TextureMap> texturesFactory) {
		registerOrientableSlab(gen, slab, texturesFactory.apply(textureSource), ModelIds.getBlockModelId(textureSource));
	}
	
	public static void registerOrientableSlab(BlockStateModelGenerator gen, Block slab, TextureMap textures, Identifier doubleModel) {
		registerOrientableSlab(gen, slab, textures, doubleModel, false);
	}
	
	public static void registerOrientableSlab(BlockStateModelGenerator gen, Block slab, TextureMap textures, Identifier doubleModel, boolean inside) {
		gen.blockStateCollector.accept(createOrientableSlabBlockState(slab,
				Models.SLAB.upload(slab, textures, gen.modelCollector),
				(inside ? AquiferModels.ORIENTABLE_SLAB_INSIDE : AquiferModels.ORIENTABLE_SLAB).upload(slab, textures, gen.modelCollector),
				Models.SLAB_TOP.upload(slab, textures, gen.modelCollector),
				(inside ? AquiferModels.ORIENTABLE_SLAB_TOP_INSIDE : AquiferModels.ORIENTABLE_SLAB_TOP).upload(slab, textures, gen.modelCollector),
				doubleModel));
		gen.registerParentedItemModel(slab, ModelIds.getBlockModelId(slab));
	}
	
	public static void registerOrientableSlab(BlockStateModelGenerator gen, Block slab, TextureMap textures, Identifier doubleModel, Identifier doubleHorizontalModel, boolean inside) {
		gen.blockStateCollector.accept(createOrientableSlabBlockState(slab,
				Models.SLAB.upload(slab, textures, gen.modelCollector),
				(inside ? AquiferModels.ORIENTABLE_SLAB_INSIDE : AquiferModels.ORIENTABLE_SLAB).upload(slab, textures, gen.modelCollector),
				Models.SLAB_TOP.upload(slab, textures, gen.modelCollector),
				(inside ? AquiferModels.ORIENTABLE_SLAB_TOP_INSIDE : AquiferModels.ORIENTABLE_SLAB_TOP).upload(slab, textures, gen.modelCollector),
				doubleModel,
				doubleHorizontalModel));
		gen.registerParentedItemModel(slab, ModelIds.getBlockModelId(slab));
	}
	
	public static void registerOrientableSlab(BlockStateModelGenerator gen, Block slab, TextureMap textures, Identifier doubleXModel, Identifier doubleYModel, Identifier doubleZModel, boolean inside) {
		gen.blockStateCollector.accept(createOrientableSlabBlockState(slab,
				Models.SLAB.upload(slab, textures, gen.modelCollector),
				(inside ? AquiferModels.ORIENTABLE_SLAB_INSIDE : AquiferModels.ORIENTABLE_SLAB).upload(slab, textures, gen.modelCollector),
				Models.SLAB_TOP.upload(slab, textures, gen.modelCollector),
				(inside ? AquiferModels.ORIENTABLE_SLAB_TOP_INSIDE : AquiferModels.ORIENTABLE_SLAB_TOP).upload(slab, textures, gen.modelCollector),
				doubleXModel,
				doubleYModel,
				doubleZModel));
		gen.registerParentedItemModel(slab, ModelIds.getBlockModelId(slab));
	}
	
	public static void registerLeavesSlab(BlockStateModelGenerator gen, Block slab, Block textureSource) {
		registerLeavesSlab(gen, slab, TextureMap.all(textureSource), ModelIds.getBlockModelId(textureSource));
	}
	
	public static void registerLeavesSlab(BlockStateModelGenerator gen, Block slab, TextureMap textures, Identifier doubleModel) {
		registerSlab(gen, slab,
				AquiferModels.LEAVES_SLAB.upload(slab, textures, gen.modelCollector),
				AquiferModels.LEAVES_SLAB_TOP.upload(slab, textures, gen.modelCollector), doubleModel);
	}
	
	public static void registerParentedSlab(BlockStateModelGenerator gen, Block modelSource, Block child, Block doubleModelSource) {
		registerSlab(gen, child,
				ModelIds.getBlockModelId(modelSource),
				ModelIds.getBlockSubModelId(modelSource, "_top"),
				ModelIds.getBlockModelId(doubleModelSource));
	}
	
	public static void registerStairs(BlockStateModelGenerator gen, Block stairs, Block textureSource) {
		registerStairs(gen, stairs, TextureMap.all(textureSource));
	}
	
	public static void registerStairs(BlockStateModelGenerator gen, Block stairs, TextureMap textures) {
		registerStairs(gen, stairs,
				Models.INNER_STAIRS.upload(stairs, textures, gen.modelCollector),
				Models.STAIRS.upload(stairs, textures, gen.modelCollector),
				Models.OUTER_STAIRS.upload(stairs, textures, gen.modelCollector));
	}
	
	public static void registerStairs(BlockStateModelGenerator gen, Block stairs, Identifier innerModel, Identifier normalModel, Identifier outerModel) {
		gen.blockStateCollector.accept(BlockStateModelGenerator.createStairsBlockState(stairs, innerModel, normalModel, outerModel));
		gen.registerParentedItemModel(stairs, normalModel);
	}
	
	public static void registerBoolPropertyStairs(BlockStateModelGenerator gen, Block stairs, BooleanProperty property, Block textureSource) {
		registerBoolPropertyStairs(gen, stairs, property, TextureMap.all(textureSource));
	}
	
	public static void registerBoolPropertyStairs(BlockStateModelGenerator gen, Block stairs, BooleanProperty property, Block textureSource, String trueTextureSuffix) {
		registerBoolPropertyStairs(gen, stairs, property, TextureMap.all(textureSource), TextureMap.all(TextureMap.getSubId(textureSource, trueTextureSuffix)));
	}
	
	public static void registerBoolPropertyStairs(BlockStateModelGenerator gen, Block stairs, BooleanProperty property, TextureMap textures) {
		Identifier innerModel = Models.INNER_STAIRS.upload(stairs, textures, gen.modelCollector);
		Identifier model = Models.STAIRS.upload(stairs, textures, gen.modelCollector);
		Identifier outerModel = Models.OUTER_STAIRS.upload(stairs, textures, gen.modelCollector);
		registerBoolPropertyStairs(gen, stairs, property, innerModel, innerModel, model, model, outerModel, outerModel);
	}
	
	public static void registerBoolPropertyStairs(BlockStateModelGenerator gen, Block stairs, BooleanProperty property, TextureMap falseTextures, TextureMap trueTextures) {
		registerBoolPropertyStairs(gen, stairs, property,
				Models.INNER_STAIRS.upload(stairs, falseTextures, gen.modelCollector),
				Models.INNER_STAIRS.upload(stairs, "_on", trueTextures, gen.modelCollector),
				Models.STAIRS.upload(stairs, falseTextures, gen.modelCollector),
				Models.STAIRS.upload(stairs, "_on", trueTextures, gen.modelCollector),
				Models.OUTER_STAIRS.upload(stairs, falseTextures, gen.modelCollector),
				Models.OUTER_STAIRS.upload(stairs, "_on", trueTextures, gen.modelCollector));
	}
	
	public static void registerBoolPropertyStairs(BlockStateModelGenerator gen, Block stairs, BooleanProperty property, Identifier innerFalseModel, Identifier innerTrueModel, Identifier falseModel, Identifier trueModel, Identifier outerFalseModel, Identifier outerTrueModel) {
		gen.blockStateCollector.accept(createBoolPropertyStairsBlockState(stairs, property, innerFalseModel, innerTrueModel, falseModel, trueModel, outerFalseModel, outerTrueModel));
		gen.registerParentedItemModel(stairs, falseModel);
	}
	
	public static void registerFlippedStairs(BlockStateModelGenerator gen, Block stairs, TextureMap textures) {
		registerFlippedStairs(gen, stairs,
				Models.INNER_STAIRS.upload(stairs, textures, gen.modelCollector),
				AquiferModels.INNER_STAIRS_FLIPPED.upload(stairs, textures, gen.modelCollector),
				Models.STAIRS.upload(stairs, textures, gen.modelCollector),
				AquiferModels.STAIRS_FLIPPED.upload(stairs, textures, gen.modelCollector),
				Models.OUTER_STAIRS.upload(stairs, textures, gen.modelCollector),
				AquiferModels.OUTER_STAIRS_FLIPPED.upload(stairs, textures, gen.modelCollector));
	}
	
	public static void registerFlippedStairs(BlockStateModelGenerator gen, Block stairs, Identifier innerModel, Identifier innerFlippedModel, Identifier model, Identifier flippedModel, Identifier outerModel, Identifier outerFlippedModel) {
		gen.blockStateCollector.accept(createFlippedStairsBlockState(stairs, innerModel, innerFlippedModel, model, flippedModel, outerModel, outerFlippedModel));
		gen.registerParentedItemModel(stairs, model);
	}
	
	public static void registerBoolPropertyFlippedStairs(BlockStateModelGenerator gen, Block stairs, BooleanProperty property, TextureMap falseTextures, TextureMap trueTextures) {
		registerBoolPropertyFlippedStairs(gen, stairs, property,
				Models.INNER_STAIRS.upload(stairs, falseTextures, gen.modelCollector),
				Models.INNER_STAIRS.upload(stairs, "_on", trueTextures, gen.modelCollector),
				AquiferModels.INNER_STAIRS_FLIPPED.upload(stairs, falseTextures, gen.modelCollector),
				AquiferModels.INNER_STAIRS_FLIPPED.upload(stairs, "_on", trueTextures, gen.modelCollector),
				Models.STAIRS.upload(stairs, falseTextures, gen.modelCollector),
				Models.STAIRS.upload(stairs, "_on", trueTextures, gen.modelCollector),
				AquiferModels.STAIRS_FLIPPED.upload(stairs, falseTextures, gen.modelCollector),
				AquiferModels.STAIRS_FLIPPED.upload(stairs, "_on", trueTextures, gen.modelCollector),
				Models.OUTER_STAIRS.upload(stairs, falseTextures, gen.modelCollector),
				Models.OUTER_STAIRS.upload(stairs, "_on", trueTextures, gen.modelCollector),
				AquiferModels.OUTER_STAIRS_FLIPPED.upload(stairs, falseTextures, gen.modelCollector),
				AquiferModels.OUTER_STAIRS_FLIPPED.upload(stairs, "_on", trueTextures, gen.modelCollector));
	}
	
	public static void registerBoolPropertyFlippedStairs(BlockStateModelGenerator gen, Block stairs, BooleanProperty property, Identifier innerFalseModel, Identifier innerTrueModel, Identifier innerFlippedFalseModel, Identifier innerFlippedTrueModel, Identifier falseModel, Identifier trueModel, Identifier flippedFalseModel, Identifier flippedTrueModel, Identifier outerFalseModel, Identifier outerTrueModel, Identifier outerFlippedFalseModel, Identifier outerFlippedTrueModel) {
		gen.blockStateCollector.accept(createBoolPropertyFlippedStairsBlockState(stairs, property, innerFalseModel, innerTrueModel, innerFlippedFalseModel, innerFlippedTrueModel, falseModel, trueModel, flippedFalseModel, flippedTrueModel, outerFalseModel, outerTrueModel, outerFlippedFalseModel, outerFlippedTrueModel));
		gen.registerParentedItemModel(stairs, falseModel);
	}
	
	public static void registerTerrainStairs(BlockStateModelGenerator gen, Block stairs, Block textureSource, Block bottomTextureSource) {
		TextureMap textures = TextureMap.sideAndTop(textureSource).put(TextureKey.BOTTOM, TextureMap.getId(bottomTextureSource));
		registerFlippedStairs(gen, stairs,
				AquiferModels.TERRAIN_INNER_STAIRS.upload(stairs, textures, gen.modelCollector),
				AquiferModels.TERRAIN_INNER_STAIRS_FLIPPED.upload(stairs, textures, gen.modelCollector),
				AquiferModels.TERRAIN_STAIRS.upload(stairs, textures, gen.modelCollector),
				AquiferModels.TERRAIN_STAIRS_FLIPPED.upload(stairs, textures, gen.modelCollector),
				AquiferModels.TERRAIN_OUTER_STAIRS.upload(stairs, textures, gen.modelCollector),
				AquiferModels.TERRAIN_OUTER_STAIRS_FLIPPED.upload(stairs, textures, gen.modelCollector));
	}
	
	public static void registerSnowyTerrainStairs(BlockStateModelGenerator gen, Block stairs, Block textureSource, Block bottomTextureSource, Block snowyModelSource) {
		registerSnowyTerrainStairs(gen, stairs, textureSource, bottomTextureSource, ModelIds.getBlockSubModelId(snowyModelSource, "_snowy_inner"), ModelIds.getBlockSubModelId(snowyModelSource, "_snowy_inner_flipped"), ModelIds.getBlockSubModelId(snowyModelSource, "_snowy"), ModelIds.getBlockSubModelId(snowyModelSource, "_snowy_flipped"), ModelIds.getBlockSubModelId(snowyModelSource, "_snowy_outer"), ModelIds.getBlockSubModelId(snowyModelSource, "_snowy_outer_flipped"));
	}
	
	public static void registerSnowyTerrainStairs(BlockStateModelGenerator gen, Block stairs, Block textureSource, Block bottomTextureSource, Identifier innerSnowyModel, Identifier innerFlippedSnowyModel, Identifier snowyModel, Identifier flippedSnowyModel, Identifier outerSnowyModel, Identifier outerFlippedSnowyModel) {
		TextureMap textures = TextureMap.sideAndTop(textureSource).put(TextureKey.BOTTOM, TextureMap.getId(bottomTextureSource));
		registerBoolPropertyFlippedStairs(gen, stairs, Properties.SNOWY,
				AquiferModels.TERRAIN_INNER_STAIRS.upload(stairs, textures, gen.modelCollector),
				innerSnowyModel,
				AquiferModels.TERRAIN_INNER_STAIRS_FLIPPED.upload(stairs, textures, gen.modelCollector),
				innerFlippedSnowyModel,
				AquiferModels.TERRAIN_STAIRS.upload(stairs, textures, gen.modelCollector),
				snowyModel,
				AquiferModels.TERRAIN_STAIRS_FLIPPED.upload(stairs, textures, gen.modelCollector),
				flippedSnowyModel,
				AquiferModels.TERRAIN_OUTER_STAIRS.upload(stairs, textures, gen.modelCollector),
				outerSnowyModel,
				AquiferModels.TERRAIN_OUTER_STAIRS_FLIPPED.upload(stairs, textures, gen.modelCollector),
				outerFlippedSnowyModel);
	}
	
	public static void registerOrientableStairs(BlockStateModelGenerator gen, Block stairs, Block textureSource) {
		registerOrientableStairs(gen, stairs, textureSource, TextureMap::sideAndEndForTop);
	}
	
	public static void registerOrientableStairs(BlockStateModelGenerator gen, Block stairs, Block textureSource, Function<Block, TextureMap> texturesFactory) {
		registerOrientableStairs(gen, stairs, texturesFactory.apply(textureSource));
	}
	
	public static void registerOrientableStairs(BlockStateModelGenerator gen, Block stairs, TextureMap textures) {
		registerOrientableStairs(gen, stairs, textures, false);
	}
	
	public static void registerOrientableStairs(BlockStateModelGenerator gen, Block stairs, TextureMap textures, boolean inside) {
		gen.blockStateCollector.accept(createOrientableStairsBlockState(stairs,
				(inside ? AquiferModels.INNER_STAIRS_INSIDE : Models.INNER_STAIRS).upload(stairs, textures, gen.modelCollector),
				(inside ? AquiferModels.INNER_STAIRS_INSIDE_MIRRORED : AquiferModels.INNER_STAIRS_MIRRORED).upload(stairs, textures, gen.modelCollector),
				(inside ? AquiferModels.STAIRS_INSIDE : Models.STAIRS).upload(stairs, textures, gen.modelCollector),
				(inside ? AquiferModels.ORIENTED_STAIRS_FRONT_INSIDE : AquiferModels.ORIENTED_STAIRS_FRONT).upload(stairs, textures, gen.modelCollector),
				(inside ? AquiferModels.ORIENTED_STAIRS_SIDE_INSIDE : AquiferModels.ORIENTED_STAIRS_SIDE).upload(stairs, textures, gen.modelCollector),
				(inside ? AquiferModels.OUTER_STAIRS_INSIDE : Models.OUTER_STAIRS).upload(stairs, textures, gen.modelCollector),
				(inside ? AquiferModels.ORIENTED_OUTER_STAIRS_INSIDE : AquiferModels.ORIENTED_OUTER_STAIRS).upload(stairs, textures, gen.modelCollector),
				(inside ? AquiferModels.ORIENTED_OUTER_STAIRS_INSIDE_MIRRORED : AquiferModels.ORIENTED_OUTER_STAIRS_MIRRORED).upload(stairs, textures, gen.modelCollector)));
		gen.registerParentedItemModel(stairs, ModelIds.getBlockModelId(stairs));
	}
	
	public static void registerLeavesStairs(BlockStateModelGenerator gen, Block stairs, Block textureSource) {
		registerLeavesStairs(gen, stairs, TextureMap.all(textureSource));
	}
	
	public static void registerLeavesStairs(BlockStateModelGenerator gen, Block stairs, TextureMap textures) {
		registerStairs(gen, stairs,
				AquiferModels.LEAVES_INNER_STAIRS.upload(stairs, textures, gen.modelCollector),
				AquiferModels.LEAVES_STAIRS.upload(stairs, textures, gen.modelCollector),
				AquiferModels.LEAVES_OUTER_STAIRS.upload(stairs, textures, gen.modelCollector));
	}
	
	public static void registerTransparentStairs(BlockStateModelGenerator gen, Block stairs, Block textureSource) {
		registerTransparentStairs(gen, stairs, TextureMap.all(textureSource));
	}
	
	public static void registerTransparentStairs(BlockStateModelGenerator gen, Block stairs, TextureMap textures) {
		registerStairs(gen, stairs,
				AquiferModels.TRANS_INNER_STAIRS.upload(stairs, textures, gen.modelCollector),
				AquiferModels.TRANS_STAIRS.upload(stairs, textures, gen.modelCollector),
				AquiferModels.TRANS_OUTER_STAIRS.upload(stairs, textures, gen.modelCollector));
	}
	
	public static void registerParentedStairs(BlockStateModelGenerator gen, Block modelSource, Block child) {
		registerStairs(gen, child,
				ModelIds.getBlockSubModelId(modelSource, "_inner"),
				ModelIds.getBlockModelId(modelSource),
				ModelIds.getBlockSubModelId(modelSource, "_outer"));
	}
	
	public static void registerWall(BlockStateModelGenerator gen, Block wall, Block textureSource) {
		registerWall(gen, wall, TextureMap.all(textureSource));
	}
	
	public static void registerWall(BlockStateModelGenerator gen, Block wall, TextureMap textures) {
		registerWall(gen, wall,
				Models.TEMPLATE_WALL_POST.upload(wall, textures, gen.modelCollector),
				Models.TEMPLATE_WALL_SIDE.upload(wall, textures, gen.modelCollector),
				Models.TEMPLATE_WALL_SIDE_TALL.upload(wall, textures, gen.modelCollector),
				Models.WALL_INVENTORY.upload(wall, textures, gen.modelCollector));
	}
	
	public static void registerWallBottomTop(BlockStateModelGenerator gen, Block wall, TextureMap textures) {
		registerWall(gen, wall,
				AquiferModels.TEMPLATE_WALL_POST_BOTTOM_TOP.upload(wall, textures, gen.modelCollector),
				AquiferModels.TEMPLATE_WALL_SIDE_BOTTOM_TOP.upload(wall, textures, gen.modelCollector),
				AquiferModels.TEMPLATE_WALL_SIDE_TALL_BOTTOM_TOP.upload(wall, textures, gen.modelCollector),
				AquiferModels.WALL_BOTTOM_TOP_INVENTORY.upload(wall, textures, gen.modelCollector));
	}
	
	public static void registerWallColumn(BlockStateModelGenerator gen, Block wall, TextureMap textures) {
		registerWall(gen, wall,
				AquiferModels.TEMPLATE_WALL_POST_COLUMN.upload(wall, textures, gen.modelCollector),
				AquiferModels.TEMPLATE_WALL_SIDE_COLUMN.upload(wall, textures, gen.modelCollector),
				AquiferModels.TEMPLATE_WALL_SIDE_TALL_COLUMN.upload(wall, textures, gen.modelCollector),
				AquiferModels.WALL_COLUMN_INVENTORY.upload(wall, textures, gen.modelCollector));
	}
	
	public static void registerWallCentralColumn(BlockStateModelGenerator gen, Block wall, TextureMap textures) {
		registerWall(gen, wall,
				AquiferModels.TEMPLATE_WALL_POST_COLUMN.upload(wall, textures, gen.modelCollector),
				Models.TEMPLATE_WALL_SIDE.upload(wall, textures, gen.modelCollector),
				Models.TEMPLATE_WALL_SIDE_TALL.upload(wall, textures, gen.modelCollector),
				AquiferModels.WALL_CENTRAL_COLUMN_INVENTORY.upload(wall, textures, gen.modelCollector));
	}
	
	public static void registerWall(BlockStateModelGenerator gen, Block wall, Identifier postModel, Identifier sideModel, Identifier sideTallModel, Identifier inventoryModel) {
		gen.blockStateCollector.accept(BlockStateModelGenerator.createWallBlockState(wall, postModel, sideModel, sideTallModel));
		gen.registerParentedItemModel(wall, inventoryModel);
	}
	
	public static void registerBoolPropertyWall(BlockStateModelGenerator gen, Block wall, BooleanProperty property, Block textureSource, String trueTextureSuffix) {
		registerBoolPropertyWall(gen, wall, property, TextureMap.all(textureSource), TextureMap.all(TextureMap.getSubId(textureSource, trueTextureSuffix)));
	}
	
	public static void registerBoolPropertyWall(BlockStateModelGenerator gen, Block wall, BooleanProperty property, TextureMap falseTextures, TextureMap trueTextures) {
		registerBoolPropertyWall(gen, wall, property,
				Models.TEMPLATE_WALL_POST.upload(wall, falseTextures, gen.modelCollector),
				Models.TEMPLATE_WALL_POST.upload(wall, "_on", trueTextures, gen.modelCollector),
				Models.TEMPLATE_WALL_SIDE.upload(wall, falseTextures, gen.modelCollector),
				Models.TEMPLATE_WALL_SIDE.upload(wall, "_on", trueTextures, gen.modelCollector),
				Models.TEMPLATE_WALL_SIDE_TALL.upload(wall, falseTextures, gen.modelCollector),
				Models.TEMPLATE_WALL_SIDE_TALL.upload(wall, "_on", trueTextures, gen.modelCollector),
				Models.WALL_INVENTORY.upload(wall, falseTextures, gen.modelCollector));
	}
	
	public static void registerBoolPropertyWallBottomTop(BlockStateModelGenerator gen, Block wall, BooleanProperty property, TextureMap falseTextures, TextureMap trueTextures) {
		registerBoolPropertyWall(gen, wall, property,
				AquiferModels.TEMPLATE_WALL_POST_BOTTOM_TOP.upload(wall, falseTextures, gen.modelCollector),
				AquiferModels.TEMPLATE_WALL_POST_BOTTOM_TOP.upload(wall, "_on", trueTextures, gen.modelCollector),
				AquiferModels.TEMPLATE_WALL_SIDE_BOTTOM_TOP.upload(wall, falseTextures, gen.modelCollector),
				AquiferModels.TEMPLATE_WALL_SIDE_BOTTOM_TOP.upload(wall, "_on", trueTextures, gen.modelCollector),
				AquiferModels.TEMPLATE_WALL_SIDE_TALL_BOTTOM_TOP.upload(wall, falseTextures, gen.modelCollector),
				AquiferModels.TEMPLATE_WALL_SIDE_TALL_BOTTOM_TOP.upload(wall, "_on", trueTextures, gen.modelCollector),
				AquiferModels.WALL_BOTTOM_TOP_INVENTORY.upload(wall, falseTextures, gen.modelCollector));
	}
	
	public static void registerBoolPropertyWallColumn(BlockStateModelGenerator gen, Block wall, BooleanProperty property, TextureMap falseTextures, TextureMap trueTextures) {
		registerBoolPropertyWall(gen, wall, property,
				AquiferModels.TEMPLATE_WALL_POST_COLUMN.upload(wall, falseTextures, gen.modelCollector),
				AquiferModels.TEMPLATE_WALL_POST_COLUMN.upload(wall, "_on", trueTextures, gen.modelCollector),
				AquiferModels.TEMPLATE_WALL_SIDE_COLUMN.upload(wall, falseTextures, gen.modelCollector),
				AquiferModels.TEMPLATE_WALL_SIDE_COLUMN.upload(wall, "_on", trueTextures, gen.modelCollector),
				AquiferModels.TEMPLATE_WALL_SIDE_TALL_COLUMN.upload(wall, falseTextures, gen.modelCollector),
				AquiferModels.TEMPLATE_WALL_SIDE_TALL_COLUMN.upload(wall, "_on", trueTextures, gen.modelCollector),
				AquiferModels.WALL_COLUMN_INVENTORY.upload(wall, falseTextures, gen.modelCollector));
	}
	
	public static void registerBoolPropertyWallCentralColumn(BlockStateModelGenerator gen, Block wall, BooleanProperty property, TextureMap falseTextures, TextureMap trueTextures) {
		registerBoolPropertyWall(gen, wall, property,
				AquiferModels.TEMPLATE_WALL_POST_COLUMN.upload(wall, falseTextures, gen.modelCollector),
				AquiferModels.TEMPLATE_WALL_POST_COLUMN.upload(wall, "_on", trueTextures, gen.modelCollector),
				Models.TEMPLATE_WALL_SIDE.upload(wall, falseTextures, gen.modelCollector),
				Models.TEMPLATE_WALL_SIDE.upload(wall, "_on", trueTextures, gen.modelCollector),
				Models.TEMPLATE_WALL_SIDE_TALL.upload(wall, falseTextures, gen.modelCollector),
				Models.TEMPLATE_WALL_SIDE_TALL.upload(wall, "_on", trueTextures, gen.modelCollector),
				AquiferModels.WALL_CENTRAL_COLUMN_INVENTORY.upload(wall, falseTextures, gen.modelCollector));
	}
	
	public static void registerBoolPropertyWall(BlockStateModelGenerator gen, Block wall, BooleanProperty property, Identifier postFalseModel, Identifier postTrueModel, Identifier sideFalseModel, Identifier sideTrueModel, Identifier sideTallFalseModel, Identifier sideTallTrueModel, Identifier inventoryModel) {
		gen.blockStateCollector.accept(createBoolPropertyWallBlockState(wall, property, postFalseModel, postTrueModel, sideFalseModel, sideTrueModel, sideTallFalseModel, sideTallTrueModel));
		gen.registerParentedItemModel(wall, inventoryModel);
	}
	
	public static void registerTerrainWall(BlockStateModelGenerator gen, Block wall, Block textureSource, Block bottomTextureSource) {
		TextureMap textures = TextureMap.sideAndTop(textureSource).put(TextureKey.BOTTOM, TextureMap.getId(bottomTextureSource));
		registerWall(gen, wall,
				AquiferModels.TEMPLATE_TERRAIN_WALL_POST.upload(wall, textures, gen.modelCollector),
				AquiferModels.TEMPLATE_TERRAIN_WALL_SIDE.upload(wall, textures, gen.modelCollector),
				AquiferModels.TEMPLATE_TERRAIN_WALL_SIDE_TALL.upload(wall, textures, gen.modelCollector),
				AquiferModels.TERRAIN_WALL_INVENTORY.upload(wall, textures, gen.modelCollector));
	}
	
	public static void registerSnowyTerrainWall(BlockStateModelGenerator gen, Block wall, Block textureSource, Block bottomTextureSource, Block snowyModelSource) {
		registerSnowyTerrainWall(gen, wall, textureSource, bottomTextureSource, ModelIds.getBlockSubModelId(snowyModelSource, "_snowy_post"), ModelIds.getBlockSubModelId(snowyModelSource, "_snowy_side"), ModelIds.getBlockSubModelId(snowyModelSource, "_snowy_side_tall"));
	}
	
	public static void registerSnowyTerrainWall(BlockStateModelGenerator gen, Block wall, Block textureSource, Block bottomTextureSource, Identifier snowyPostModel, Identifier snowySideModel, Identifier snowySideTallModel) {
		TextureMap textures = TextureMap.sideAndTop(textureSource).put(TextureKey.BOTTOM, TextureMap.getId(bottomTextureSource));
		registerBoolPropertyWall(gen, wall, Properties.SNOWY,
				AquiferModels.TEMPLATE_TERRAIN_WALL_POST.upload(wall, textures, gen.modelCollector),
				snowyPostModel,
				AquiferModels.TEMPLATE_TERRAIN_WALL_SIDE.upload(wall, textures, gen.modelCollector),
				snowySideModel,
				AquiferModels.TEMPLATE_TERRAIN_WALL_SIDE_TALL.upload(wall, textures, gen.modelCollector),
				snowySideTallModel,
				AquiferModels.TERRAIN_WALL_INVENTORY.upload(wall, textures, gen.modelCollector));
	}
	
	public static void registerLeavesWall(BlockStateModelGenerator gen, Block wall, Block textureSource) {
		registerLeavesWall(gen, wall, TextureMap.all(textureSource));
	}
	
	public static void registerLeavesWall(BlockStateModelGenerator gen, Block wall, TextureMap textures) {
		registerWall(gen, wall,
				AquiferModels.TEMPLATE_LEAVES_WALL_POST.upload(wall, textures, gen.modelCollector),
				AquiferModels.TEMPLATE_LEAVES_WALL_SIDE.upload(wall, textures, gen.modelCollector),
				AquiferModels.TEMPLATE_LEAVES_WALL_SIDE_TALL.upload(wall, textures, gen.modelCollector),
				AquiferModels.LEAVES_WALL_INVENTORY.upload(wall, textures, gen.modelCollector));
	}
	
	public static void registerParentedWall(BlockStateModelGenerator gen, Block modelSource, Block child) {
		registerWall(gen, child,
				ModelIds.getBlockSubModelId(modelSource, "_post"),
				ModelIds.getBlockSubModelId(modelSource, "_side"),
				ModelIds.getBlockSubModelId(modelSource, "_side_tall"),
				ModelIds.getBlockSubModelId(modelSource, "_inventory"));
	}
	
	public static void registerTransparentWall(BlockStateModelGenerator gen, Block wall, Block textureSource) {
		registerTransparentWall(gen, wall, TextureMap.all(textureSource));
	}
	
	public static void registerTransparentWall(BlockStateModelGenerator gen, Block wall, TextureMap textures) {
		registerWall(gen, wall,
				AquiferModels.TEMPLATE_TRANS_WALL_POST.upload(wall, textures, gen.modelCollector),
				AquiferModels.TEMPLATE_TRANS_WALL_NOPOST.upload(wall, textures, gen.modelCollector),
				AquiferModels.TEMPLATE_TRANS_WALL_NOPOST_TALL.upload(wall, textures, gen.modelCollector),
				AquiferModels.TEMPLATE_TRANS_WALL_NOSIDE.upload(wall, textures, gen.modelCollector),
				AquiferModels.TEMPLATE_TRANS_WALL_NOSIDE_NOPOST.upload(wall, textures, gen.modelCollector),
				AquiferModels.TEMPLATE_TRANS_WALL_NOSIDE_NOPOST_TALL.upload(wall, textures, gen.modelCollector),
				AquiferModels.TEMPLATE_TRANS_WALL_SIDE.upload(wall, textures, gen.modelCollector),
				AquiferModels.TEMPLATE_TRANS_WALL_SIDE_NOPOST.upload(wall, textures, gen.modelCollector),
				AquiferModels.TEMPLATE_TRANS_WALL_SIDE_TALL.upload(wall, textures, gen.modelCollector),
				AquiferModels.TEMPLATE_TRANS_WALL_SIDE_TALL_NOPOST.upload(wall, textures, gen.modelCollector),
				AquiferModels.TRANS_WALL_INVENTORY.upload(wall, textures, gen.modelCollector));
	}
	
	public static void registerWall(BlockStateModelGenerator gen, Block wall, Identifier postModel, Identifier nopostModel, Identifier nopostTallModel, Identifier nosideModel, Identifier nosideNopostModel, Identifier nosideNopostTallModel, Identifier sideModel, Identifier sideNopostModel, Identifier sideTallModel, Identifier sideTallNopostModel, Identifier inventoryModel) {
		gen.blockStateCollector.accept(createWallBlockState(wall, postModel, nopostModel, nopostTallModel, nosideModel, nosideNopostModel, nosideNopostTallModel, sideModel, sideNopostModel, sideTallModel, sideTallNopostModel));
		gen.registerParentedItemModel(wall, inventoryModel);
	}
	
	public static void registerParentedTransparentWall(BlockStateModelGenerator gen, Block modelSource, Block child) {
		registerWall(gen, child,
				ModelIds.getBlockSubModelId(modelSource, "_post"),
				ModelIds.getBlockSubModelId(modelSource, "_nopost"),
				ModelIds.getBlockSubModelId(modelSource, "_nopost_tall"),
				ModelIds.getBlockSubModelId(modelSource, "_noside"),
				ModelIds.getBlockSubModelId(modelSource, "_noside_nopost"),
				ModelIds.getBlockSubModelId(modelSource, "_noside_nopost_tall"),
				ModelIds.getBlockSubModelId(modelSource, "_side"),
				ModelIds.getBlockSubModelId(modelSource, "_side_nopost"),
				ModelIds.getBlockSubModelId(modelSource, "_side_tall"),
				ModelIds.getBlockSubModelId(modelSource, "_side_tall_nopost"),
				ModelIds.getBlockSubModelId(modelSource, "_inventory"));
	}
	
	public static void registerFence(BlockStateModelGenerator gen, Block fence, Block textureSource) {
		registerFence(gen, fence, TextureMap.all(textureSource));
	}
	
	public static void registerFence(BlockStateModelGenerator gen, Block fence, TextureMap textures) {
		registerFence(gen, fence,
				Models.FENCE_POST.upload(fence, textures, gen.modelCollector),
				Models.FENCE_SIDE.upload(fence, textures, gen.modelCollector),
				Models.FENCE_INVENTORY.upload(fence, textures, gen.modelCollector));
	}
	
	public static void registerFence(BlockStateModelGenerator gen, Block fence, Identifier postModel, Identifier sideModel, Identifier inventoryModel) {
		gen.blockStateCollector.accept(BlockStateModelGenerator.createFenceBlockState(fence, postModel, sideModel));
		gen.registerParentedItemModel(fence, inventoryModel);
	}
	
	public static void registerParentedFence(BlockStateModelGenerator gen, Block modelSource, Block child) {
		registerFence(gen, child,
				ModelIds.getBlockSubModelId(modelSource, "_post"),
				ModelIds.getBlockSubModelId(modelSource, "_side"),
				ModelIds.getBlockSubModelId(modelSource, "_inventory"));
	}
	
	public static void registerFenceGate(BlockStateModelGenerator gen, Block fenceGate, Block textureSource) {
		registerFenceGate(gen, fenceGate, TextureMap.all(textureSource));
	}
	
	public static void registerFenceGate(BlockStateModelGenerator gen, Block fenceGate, TextureMap textures) {
		registerFenceGate(gen, fenceGate,
				Models.TEMPLATE_FENCE_GATE.upload(fenceGate, textures, gen.modelCollector),
				Models.TEMPLATE_FENCE_GATE_OPEN.upload(fenceGate, textures, gen.modelCollector),
				Models.TEMPLATE_FENCE_GATE_WALL.upload(fenceGate, textures, gen.modelCollector),
				Models.TEMPLATE_FENCE_GATE_WALL_OPEN.upload(fenceGate, textures, gen.modelCollector));
	}
	
	public static void registerFenceGate(BlockStateModelGenerator gen, Block fenceGate, Identifier closedModel, Identifier openModel, Identifier closedWallModel, Identifier openWallModel) {
		gen.blockStateCollector.accept(BlockStateModelGenerator.createFenceGateBlockState(fenceGate, openModel, closedModel, openWallModel, closedWallModel, true));
		gen.registerParentedItemModel(fenceGate, closedModel);
	}
	
	public static void registerParentedFenceGate(BlockStateModelGenerator gen, Block modelSource, Block child) {
		registerFenceGate(gen, child,
				ModelIds.getBlockModelId(modelSource),
				ModelIds.getBlockSubModelId(modelSource, "_open"),
				ModelIds.getBlockSubModelId(modelSource, "_wall"),
				ModelIds.getBlockSubModelId(modelSource, "_wall_open"));
	}
	
	public static void registerCarpet(BlockStateModelGenerator gen, Block carpet, Block textureSource) {
		registerCarpet(gen, carpet, TextureMap.wool(textureSource));
	}
	
	public static void registerCarpet(BlockStateModelGenerator gen, Block carpet, TextureMap textures) {
		registerSingleton(gen, carpet, Models.CARPET.upload(carpet, textures, gen.modelCollector));
	}
	
	public static void registerLeavesCarpet(BlockStateModelGenerator gen, Block carpet, Block textureSource) {
		registerLeavesCarpet(gen, carpet, TextureMap.all(textureSource));
	}
	
	public static void registerLeavesCarpet(BlockStateModelGenerator gen, Block carpet, TextureMap textures) {
		registerSingleton(gen, carpet, AquiferModels.LEAVES_CARPET.upload(carpet, textures, gen.modelCollector));
	}
	
	public static void registerLadder(BlockStateModelGenerator gen, Block ladder) {
		registerLadder(gen, ladder, TextureMap.texture(ladder));
	}
	
	public static void registerLadder(BlockStateModelGenerator gen, Block ladder, TextureMap textures) {
		gen.blockStateCollector.accept(VariantsBlockStateSupplier.create(ladder, BlockStateVariant.create().put(VariantSettings.MODEL, AquiferModels.LADDER.upload(ladder, textures, gen.modelCollector))).coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates()));
		gen.registerItemModel(ladder);
	}
	
	public static void registerParentedLadder(BlockStateModelGenerator gen, Block modelSource, Block child) {
		gen.blockStateCollector.accept(VariantsBlockStateSupplier.create(child, BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockModelId(modelSource))).coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates()));
		gen.registerParentedItemModel(child, ModelIds.getItemModelId(modelSource.asItem()));
	}
	
	public static void registerOakLadder(BlockStateModelGenerator gen, Block ladder) {
		registerParentedLadder(gen, Blocks.LADDER, ladder);
	}
	
	public static void registerAnvil(BlockStateModelGenerator gen, Block anvil) {
		registerSouthDefaultHorizontalRotated(gen, anvil, AquiferModels.TEMPLATE_ANVIL.upload(anvil, AquiferTextureMaps.anvil(anvil), gen.modelCollector));
	}
	
	public static void registerAnvil(BlockStateModelGenerator gen, Block anvil, Block bodyTextureSource) {
		registerSouthDefaultHorizontalRotated(gen, anvil, AquiferModels.TEMPLATE_ANVIL.upload(anvil, AquiferTextureMaps.anvil(anvil, bodyTextureSource), gen.modelCollector));
	}
	
	public static void registerBars(BlockStateModelGenerator gen, Block bars) {
		registerBars(gen, bars, TextureMap.all(bars));
	}
	
	public static void registerBars(BlockStateModelGenerator gen, Block bars, TextureMap textures) {
		registerBars(gen, bars,
				AquiferModels.TEMPLATE_BARS_POST.upload(bars, textures, gen.modelCollector),
				AquiferModels.TEMPLATE_BARS_POST_ENDS.upload(bars, textures, gen.modelCollector),
				AquiferModels.TEMPLATE_BARS_SIDE.upload(bars, textures, gen.modelCollector),
				AquiferModels.TEMPLATE_BARS_SIDE_ALT.upload(bars, textures, gen.modelCollector),
				AquiferModels.TEMPLATE_BARS_CAP.upload(bars, textures, gen.modelCollector),
				AquiferModels.TEMPLATE_BARS_CAP_ALT.upload(bars, textures, gen.modelCollector));
	}
	
	public static void registerBars(BlockStateModelGenerator gen, Block bars, Identifier postModel, Identifier postEndsModel, Identifier sideModel, Identifier sideAltModel, Identifier capModel, Identifier capAltModel) {
		gen.blockStateCollector.accept(createBarsBlockState(bars, postModel, postEndsModel, sideModel, sideAltModel, capModel, capAltModel));
		gen.registerItemModel(bars);
	}
	
	public static void registerParentedBars(BlockStateModelGenerator gen, Block modelSource, Block child) {
		gen.blockStateCollector.accept(createBarsBlockState(child,
				ModelIds.getBlockSubModelId(modelSource, "_post"),
				ModelIds.getBlockSubModelId(modelSource, "_post_ends"),
				ModelIds.getBlockSubModelId(modelSource, "_side"),
				ModelIds.getBlockSubModelId(modelSource, "_side_alt"),
				ModelIds.getBlockSubModelId(modelSource, "_cap"),
				ModelIds.getBlockSubModelId(modelSource, "_cap_alt")));
		gen.registerParentedItemModel(child, ModelIds.getItemModelId(modelSource.asItem()));
	}
	
	public static void registerPressurePlate(BlockStateModelGenerator gen, Block pressurePlate, Block textureSource) {
		registerPressurePlate(gen, pressurePlate, TextureMap.all(textureSource));
	}
	
	public static void registerPressurePlate(BlockStateModelGenerator gen, Block pressurePlate, TextureMap textures) {
		registerPressurePlate(gen, pressurePlate,
				Models.PRESSURE_PLATE_UP.upload(pressurePlate, textures, gen.modelCollector),
				Models.PRESSURE_PLATE_DOWN.upload(pressurePlate, textures, gen.modelCollector));
	}
	
	public static void registerPressurePlate(BlockStateModelGenerator gen, Block pressurePlate, Identifier upModel, Identifier downModel) {
		gen.blockStateCollector.accept(BlockStateModelGenerator.createPressurePlateBlockState(pressurePlate, upModel, downModel));
		gen.registerParentedItemModel(pressurePlate, upModel);
	}
	
	public static void registerParentedPressurePlate(BlockStateModelGenerator gen, Block modelSource, Block child) {
		registerPressurePlate(gen, child,
				ModelIds.getBlockModelId(modelSource),
				ModelIds.getBlockSubModelId(modelSource, "_down"));
	}
	
	public static void registerButton(BlockStateModelGenerator gen, Block button, Block textureSource) {
		registerButton(gen, button, TextureMap.all(textureSource));
	}
	
	public static void registerButton(BlockStateModelGenerator gen, Block button, TextureMap textures) {
		registerButton(gen, button,
				Models.BUTTON.upload(button, textures, gen.modelCollector),
				Models.BUTTON_PRESSED.upload(button, textures, gen.modelCollector),
				Models.BUTTON_INVENTORY.upload(button, textures, gen.modelCollector));
	}
	
	public static void registerButton(BlockStateModelGenerator gen, Block button, Identifier model, Identifier pressedModel, Identifier inventoryModel) {
		gen.blockStateCollector.accept(BlockStateModelGenerator.createButtonBlockState(button, model, pressedModel));
		gen.registerParentedItemModel(button, inventoryModel);
	}
	
	public static void registerParentedButton(BlockStateModelGenerator gen, Block modelSource, Block child) {
		registerButton(gen, child,
				ModelIds.getBlockModelId(modelSource),
				ModelIds.getBlockSubModelId(modelSource, "_pressed"),
				ModelIds.getBlockSubModelId(modelSource, "_inventory"));
	}
	
	public static void registerCauldrons(BlockStateModelGenerator gen, Block empty, Block water, Block lava, Block powderSnow) {
		registerEmptyCauldron(gen, empty);
		registerWaterCauldron(gen, water, empty);
		registerLavaCauldron(gen, lava, empty);
		registerPowderSnowCauldron(gen, powderSnow, empty);
	}
	
	public static void registerParentedCauldrons(BlockStateModelGenerator gen, Block emptySource, Block waterSource, Block lavaSource, Block powderSnowSource, Block emptyChild, Block waterChild, Block lavaChild, Block powderSnowChild) {
		gen.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(emptyChild, ModelIds.getBlockModelId(emptySource)));
		gen.registerParentedItemModel(emptyChild.asItem(), ModelIds.getItemModelId(emptySource.asItem()));
		gen.blockStateCollector.accept(createLeveledCauldronBlockState(waterChild,
				ModelIds.getBlockSubModelId(waterSource, "_level1"),
				ModelIds.getBlockSubModelId(waterSource, "_level2"),
				ModelIds.getBlockModelId(waterSource)));
		gen.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(lavaChild, ModelIds.getBlockModelId(lavaSource)));
		gen.blockStateCollector.accept(createLeveledCauldronBlockState(powderSnowChild,
				ModelIds.getBlockSubModelId(powderSnowSource, "_level1"),
				ModelIds.getBlockSubModelId(powderSnowSource, "_level2"),
				ModelIds.getBlockModelId(powderSnowSource)));
	}
	
	public static void registerEmptyCauldron(BlockStateModelGenerator gen, Block cauldron) {
		TextureMap textures = AquiferTextureMaps.cauldron(cauldron);
		Identifier model = AquiferModels.TEMPLATE_CAULDRON_EMPTY.upload(cauldron, textures, gen.modelCollector);
		gen.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(cauldron, model));
		gen.registerItemModel(cauldron.asItem());
	}
	
	public static void registerWaterCauldron(BlockStateModelGenerator gen, Block cauldron, Block empty) {
		registerLeveledCauldron(gen, cauldron, AquiferTextureMaps.cauldron(empty, Blocks.WATER, "_still"));
	}
	
	public static void registerLavaCauldron(BlockStateModelGenerator gen, Block cauldron, Block empty) {
		TextureMap textures = AquiferTextureMaps.cauldron(empty, Blocks.LAVA, "_still");
		Identifier model = AquiferModels.TEMPLATE_CAULDRON_FULL.upload(cauldron, textures, gen.modelCollector);
		gen.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(cauldron, model));
	}
	
	public static void registerPowderSnowCauldron(BlockStateModelGenerator gen, Block cauldron, Block empty) {
		registerLeveledCauldron(gen, cauldron, AquiferTextureMaps.cauldron(empty, Blocks.POWDER_SNOW));
	}
	
	public static void registerLeveledCauldron(BlockStateModelGenerator gen, Block cauldron, TextureMap textures) {
		gen.blockStateCollector.accept(createLeveledCauldronBlockState(cauldron,
				AquiferModels.TEMPLATE_CAULDRON_LEVEL1.upload(cauldron, textures, gen.modelCollector),
				AquiferModels.TEMPLATE_CAULDRON_LEVEL2.upload(cauldron, textures, gen.modelCollector),
				AquiferModels.TEMPLATE_CAULDRON_FULL.upload(cauldron, textures, gen.modelCollector)));
	}
	
	public static void registerChain(BlockStateModelGenerator gen, Block chain) {
		TextureMap textures = TextureMap.texture(chain);
		Identifier model = AquiferModels.TEMPLATE_CHAIN.upload(chain, textures, gen.modelCollector);
		gen.registerAxisRotated(chain, model);
		gen.registerItemModel(chain.asItem());
	}
	
	public static void registerChiseledBookshelf(BlockStateModelGenerator gen, Block bookshelf) {
		TextureMap emptyTexture = TextureMap.texture(TextureMap.getSubId(bookshelf, "_empty"));
		TextureMap occupiedTexture = TextureMap.texture(TextureMap.getSubId(bookshelf, "_occupied"));
		gen.blockStateCollector.accept(createChiseledBookshelfBlockState(bookshelf,
				AquiferModels.TEMPLATE_CHISELED_BOOKSHELF.upload(bookshelf, TextureMap.sideAndTop(bookshelf), gen.modelCollector),
				Models.TEMPLATE_CHISELED_BOOKSHELF_SLOT_TOP_LEFT.upload(bookshelf, "_empty", emptyTexture, gen.modelCollector),
				Models.TEMPLATE_CHISELED_BOOKSHELF_SLOT_TOP_MID.upload(bookshelf, "_empty", emptyTexture, gen.modelCollector),
				Models.TEMPLATE_CHISELED_BOOKSHELF_SLOT_TOP_RIGHT.upload(bookshelf, "_empty", emptyTexture, gen.modelCollector),
				Models.TEMPLATE_CHISELED_BOOKSHELF_SLOT_BOTTOM_LEFT.upload(bookshelf, "_empty", emptyTexture, gen.modelCollector),
				Models.TEMPLATE_CHISELED_BOOKSHELF_SLOT_BOTTOM_MID.upload(bookshelf, "_empty", emptyTexture, gen.modelCollector),
				Models.TEMPLATE_CHISELED_BOOKSHELF_SLOT_BOTTOM_RIGHT.upload(bookshelf, "_empty", emptyTexture, gen.modelCollector),
				Models.TEMPLATE_CHISELED_BOOKSHELF_SLOT_TOP_LEFT.upload(bookshelf, "_occupied", occupiedTexture, gen.modelCollector),
				Models.TEMPLATE_CHISELED_BOOKSHELF_SLOT_TOP_MID.upload(bookshelf, "_occupied", occupiedTexture, gen.modelCollector),
				Models.TEMPLATE_CHISELED_BOOKSHELF_SLOT_TOP_RIGHT.upload(bookshelf, "_occupied", occupiedTexture, gen.modelCollector),
				Models.TEMPLATE_CHISELED_BOOKSHELF_SLOT_BOTTOM_LEFT.upload(bookshelf, "_occupied", occupiedTexture, gen.modelCollector),
				Models.TEMPLATE_CHISELED_BOOKSHELF_SLOT_BOTTOM_MID.upload(bookshelf, "_occupied", occupiedTexture, gen.modelCollector),
				Models.TEMPLATE_CHISELED_BOOKSHELF_SLOT_BOTTOM_RIGHT.upload(bookshelf, "_occupied", occupiedTexture, gen.modelCollector)));
		gen.registerParentedItemModel(bookshelf, Models.ORIENTABLE.upload(bookshelf, "_inventory", new TextureMap().put(TextureKey.FRONT, TextureMap.getSubId(bookshelf, "_empty")).put(TextureKey.SIDE, TextureMap.getSubId(bookshelf, "_side")).put(TextureKey.TOP, TextureMap.getSubId(bookshelf, "_top")), gen.modelCollector));
	}
	
	public static void registerOakChiseledBookshelf(BlockStateModelGenerator gen, Block bookshelf) {
		gen.blockStateCollector.accept(createChiseledBookshelfBlockState(bookshelf,
				ModelIds.getBlockModelId(Blocks.CHISELED_BOOKSHELF),
				ModelIds.getBlockSubModelId(Blocks.CHISELED_BOOKSHELF, "_empty_slot_top_left"),
				ModelIds.getBlockSubModelId(Blocks.CHISELED_BOOKSHELF, "_empty_slot_top_mid"),
				ModelIds.getBlockSubModelId(Blocks.CHISELED_BOOKSHELF, "_empty_slot_top_right"),
				ModelIds.getBlockSubModelId(Blocks.CHISELED_BOOKSHELF, "_empty_slot_bottom_left"),
				ModelIds.getBlockSubModelId(Blocks.CHISELED_BOOKSHELF, "_empty_slot_bottom_mid"),
				ModelIds.getBlockSubModelId(Blocks.CHISELED_BOOKSHELF, "_empty_slot_bottom_right"),
				ModelIds.getBlockSubModelId(Blocks.CHISELED_BOOKSHELF, "_occupied_slot_top_left"),
				ModelIds.getBlockSubModelId(Blocks.CHISELED_BOOKSHELF, "_occupied_slot_top_mid"),
				ModelIds.getBlockSubModelId(Blocks.CHISELED_BOOKSHELF, "_occupied_slot_top_right"),
				ModelIds.getBlockSubModelId(Blocks.CHISELED_BOOKSHELF, "_occupied_slot_bottom_left"),
				ModelIds.getBlockSubModelId(Blocks.CHISELED_BOOKSHELF, "_occupied_slot_bottom_mid"),
				ModelIds.getBlockSubModelId(Blocks.CHISELED_BOOKSHELF, "_occupied_slot_bottom_right")));
		gen.registerParentedItemModel(bookshelf, ModelIds.getBlockSubModelId(Blocks.CHISELED_BOOKSHELF, "_inventory"));
	}
	
	public static void registerComposter(BlockStateModelGenerator gen, Block composter) {
		TextureMap textures = AquiferTextureMaps.composter(composter);
		Identifier model = AquiferModels.TEMPLATE_COMPOSTER.upload(composter, textures, gen.modelCollector);
		gen.blockStateCollector.accept(createComposterBlockState(composter, model));
		gen.registerParentedItemModel(composter, model);
	}
	
	public static void registerGrindstone(BlockStateModelGenerator gen, Block grindstone) {
		TextureMap textures = TextureMap.sideAndTop(grindstone);
		Identifier model = AquiferModels.TEMPLATE_GRINDSTONE.upload(grindstone, textures, gen.modelCollector);
		gen.blockStateCollector.accept(createGrindstoneBlockState(grindstone, model));
		gen.registerParentedItemModel(grindstone, model);
	}
	
	public static void registerLectern(BlockStateModelGenerator gen, Block lectern, Block bottom) {
		TextureMap textures = AquiferTextureMaps.lectern(lectern, bottom);
		Identifier model = AquiferModels.TEMPLATE_LECTERN.upload(lectern, textures, gen.modelCollector);
		registerNorthDefaultHorizontalRotated(gen, lectern, model);
	}
	
	public static void registerOakLectern(BlockStateModelGenerator gen, Block lectern) {
		registerNorthDefaultHorizontalRotated(gen, lectern, ModelIds.getBlockModelId(Blocks.LECTERN));
	}
	
	public static void registerStonecutter(BlockStateModelGenerator gen, Block stonecutter) {
		registerStonecutter(gen, stonecutter, TextureMap.sideTopBottom(stonecutter));
	}
	
	public static void registerStonecutter(BlockStateModelGenerator gen, Block stonecutter, TextureMap textures) {
		registerNorthDefaultHorizontalRotated(gen, stonecutter, AquiferModels.TEMPLATE_STONECUTTER.upload(stonecutter, textures, gen.modelCollector));
	}
	
	public static void registerBookshelf(BlockStateModelGenerator gen, Block bookshelf, Block planks) {
		registerSingleton(gen, bookshelf, Models.CUBE_COLUMN.upload(bookshelf, TextureMap.sideEnd(TextureMap.getId(bookshelf), TextureMap.getId(planks)), gen.modelCollector));
	}
	
	public static void registerOakBookshelf(BlockStateModelGenerator gen, Block bookshelf) {
		gen.registerParented(Blocks.BOOKSHELF, bookshelf);
	}
	
	public static void registerBarrel(BlockStateModelGenerator gen, Block barrel) {
		registerBarrel(gen, barrel,
				TexturedModel.CUBE_BOTTOM_TOP.upload(barrel, gen.modelCollector),
				TexturedModel.CUBE_BOTTOM_TOP.get(barrel).textures(textures -> textures.put(TextureKey.TOP, TextureMap.getSubId(barrel, "_top_open"))).upload(barrel, "_open", gen.modelCollector));
	}
	
	public static void registerSpruceBarrel(BlockStateModelGenerator gen, Block barrel) {
		registerBarrel(gen, barrel,
				ModelIds.getBlockModelId(Blocks.BARREL),
				ModelIds.getBlockSubModelId(Blocks.BARREL, "_open"));
	}
	
	public static void registerBarrel(BlockStateModelGenerator gen, Block barrel, Identifier model, Identifier openModel) {
		gen.blockStateCollector.accept(VariantsBlockStateSupplier.create(barrel)
				.coordinate(gen.createUpDefaultFacingVariantMap())
				.coordinate(BlockStateVariantMap.create(Properties.OPEN)
						.register(open -> BlockStateVariant.create()
								.put(VariantSettings.MODEL, open ? openModel : model))));
		gen.registerParentedItemModel(barrel, model);
	}
	
	public static void registerCraftingTable(BlockStateModelGenerator gen, Block craftingTable, Block planks) {
		TextureMap textures = TextureMap.frontSideWithCustomBottom(craftingTable, planks);
		registerSingleton(gen, craftingTable, Models.CUBE.upload(craftingTable, textures, gen.modelCollector));
	}
	
	public static void registerOakCraftingTable(BlockStateModelGenerator gen, Block craftingTable) {
		gen.registerParented(Blocks.CRAFTING_TABLE, craftingTable);
	}
	
	public static void registerLoom(BlockStateModelGenerator gen, Block loom) {
		gen.registerNorthDefaultHorizontalRotated(loom, TexturedModel.ORIENTABLE_WITH_BOTTOM);
		gen.registerParentedItemModel(loom, ModelIds.getBlockModelId(loom));
	}
	
	public static void registerOakLoom(BlockStateModelGenerator gen, Block loom) {
		registerNorthDefaultHorizontalRotated(gen, loom, ModelIds.getBlockModelId(Blocks.LOOM));
	}
	
	public static void registerCartographyTable(BlockStateModelGenerator gen, Block cartographyTable, Block planks) {
		Identifier side3 = TextureMap.getSubId(cartographyTable, "_side3");
		TextureMap textures = new TextureMap()
				.put(TextureKey.PARTICLE, side3)
				.put(TextureKey.DOWN, TextureMap.getId(planks))
				.put(TextureKey.UP, TextureMap.getSubId(cartographyTable, "_top"))
				.put(TextureKey.NORTH, side3)
				.put(TextureKey.EAST, side3)
				.put(TextureKey.SOUTH, TextureMap.getSubId(cartographyTable, "_side1"))
				.put(TextureKey.WEST, TextureMap.getSubId(cartographyTable, "_side2"));
		registerSingleton(gen, cartographyTable, Models.CUBE.upload(cartographyTable, textures, gen.modelCollector));
	}
	
	public static void registerDarkOakCartographyTable(BlockStateModelGenerator gen, Block cartographyTable) {
		gen.registerParented(Blocks.CARTOGRAPHY_TABLE, cartographyTable);
	}
	
	public static void registerFletchingTable(BlockStateModelGenerator gen, Block fletchingTable, Block planks) {
		TextureMap textures = TextureMap.frontTopSide(fletchingTable, planks);
		registerSingleton(gen, fletchingTable, Models.CUBE.upload(fletchingTable, textures, gen.modelCollector));
	}
	
	public static void registerBirchFletchingTable(BlockStateModelGenerator gen, Block fletchingTable) {
		gen.registerParented(Blocks.FLETCHING_TABLE, fletchingTable);
	}
	
	public static void registerSmithingTable(BlockStateModelGenerator gen, Block smithingTable) {
		Identifier front = TextureMap.getSubId(smithingTable, "_front");
		Identifier side = TextureMap.getSubId(smithingTable, "_side");
		TextureMap textures = new TextureMap()
				.put(TextureKey.PARTICLE, front)
				.put(TextureKey.DOWN, TextureMap.getSubId(smithingTable, "_bottom"))
				.put(TextureKey.UP, TextureMap.getSubId(Blocks.SMITHING_TABLE, "_top"))
				.put(TextureKey.NORTH, front)
				.put(TextureKey.SOUTH, front)
				.put(TextureKey.EAST, side)
				.put(TextureKey.WEST, side);
		registerSingleton(gen, smithingTable, Models.CUBE.upload(smithingTable, textures, gen.modelCollector));
	}
	
	public static void registerTripwireHook(BlockStateModelGenerator gen, Block tripwireHook, Block planks) {
		TextureMap textures = TextureMap.all(planks);
		Identifier model = AquiferModels.TEMPLATE_TRIPWIRE_HOOK.upload(tripwireHook, textures, gen.modelCollector);
		Identifier onModel = AquiferModels.TEMPLATE_TRIPWIRE_HOOK_ON.upload(tripwireHook, textures, gen.modelCollector);
		Identifier attachedModel = AquiferModels.TEMPLATE_TRIPWIRE_HOOK_ATTACHED.upload(tripwireHook, textures, gen.modelCollector);
		Identifier attachedOnModel = AquiferModels.TEMPLATE_TRIPWIRE_HOOK_ATTACHED_ON.upload(tripwireHook, textures, gen.modelCollector);
		gen.blockStateCollector.accept(createTripwireHookBlockState(tripwireHook,
				model,
				onModel,
				attachedModel,
				attachedOnModel));
		gen.registerItemModel(tripwireHook);
	}
	
	public static void registerOakTripwireHook(BlockStateModelGenerator gen, Block tripwireHook) {
		gen.blockStateCollector.accept(createTripwireHookBlockState(tripwireHook,
				ModelIds.getBlockModelId(Blocks.TRIPWIRE_HOOK),
				ModelIds.getBlockSubModelId(Blocks.TRIPWIRE_HOOK, "_on"),
				ModelIds.getBlockSubModelId(Blocks.TRIPWIRE_HOOK, "_attached"),
				ModelIds.getBlockSubModelId(Blocks.TRIPWIRE_HOOK, "_attached_on")));
		gen.registerParentedItemModel(tripwireHook.asItem(), ModelIds.getItemModelId(Items.TRIPWIRE_HOOK));
	}
	
	public static void registerBeehive(BlockStateModelGenerator gen, Block beehive) {
		registerBeehive(gen, beehive, TextureMap::sideFrontEnd);
	}
	
	public static void registerBeehive(BlockStateModelGenerator gen, Block beehive, Function<Block, TextureMap> texturesFactory) {
		TextureMap textures = texturesFactory.apply(beehive).inherit(TextureKey.SIDE, TextureKey.PARTICLE);
		TextureMap honeyTextures = textures.copyAndAdd(TextureKey.FRONT, TextureMap.getSubId(beehive, "_front_honey"));
		registerBeehive(gen, beehive,
				Models.ORIENTABLE_WITH_BOTTOM.upload(beehive, textures, gen.modelCollector),
				Models.ORIENTABLE_WITH_BOTTOM.upload(beehive, "_honey", honeyTextures, gen.modelCollector));
	}
	
	public static void registerOakBeehive(BlockStateModelGenerator gen, Block beehive) {
		registerBeehive(gen, beehive, ModelIds.getBlockModelId(Blocks.BEEHIVE), ModelIds.getBlockSubModelId(Blocks.BEEHIVE, "_honey"));
	}
	
	public static void registerBeehive(BlockStateModelGenerator gen, Block beehive, Identifier model, Identifier honeyModel) {
		gen.blockStateCollector.accept(VariantsBlockStateSupplier.create(beehive)
				.coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates())
				.coordinate(BlockStateModelGenerator.createValueFencedModelMap(Properties.HONEY_LEVEL, 5, honeyModel, model)));
		gen.registerParentedItemModel(beehive, model);
	}
	
	public static void registerFurnace(BlockStateModelGenerator gen, Block furnace) {
		gen.registerCooker(furnace, TexturedModel.ORIENTABLE);
		gen.registerParentedItemModel(furnace, ModelIds.getBlockModelId(furnace));
	}
	
	public static void registerSmoker(BlockStateModelGenerator gen, Block smoker) {
		gen.registerCooker(smoker, TexturedModel.ORIENTABLE_WITH_BOTTOM);
		gen.registerParentedItemModel(smoker, ModelIds.getBlockModelId(smoker));
	}
	
	public static void registerSigns(BlockStateModelGenerator gen, Block sign, Block wallSign, Block particleSource) {
		registerSigns(gen, sign, wallSign, TextureMap.getId(particleSource));
	}
	
	public static void registerSigns(BlockStateModelGenerator gen, Block sign, Block wallSign, Identifier particleTexture) {
		Identifier model = Models.PARTICLE.upload(sign, TextureMap.particle(particleTexture), gen.modelCollector);
		gen.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(sign, model));
		gen.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(wallSign, model));
		gen.registerItemModel(sign.asItem());
	}
	
	public static void registerParentedSign(BlockStateModelGenerator gen, Block modelSource, Block child, Block childWall) {
		Identifier model = ModelIds.getBlockModelId(modelSource);
		gen.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(child, model));
		gen.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(childWall, model));
		gen.registerParentedItemModel(child.asItem(), ModelIds.getItemModelId(modelSource.asItem()));
	}
	
	public static void registerParentedLantern(BlockStateModelGenerator gen, Block modelSource, Block child) {
		Identifier model = ModelIds.getBlockModelId(modelSource);
		Identifier hangingModel = ModelIds.getBlockSubModelId(modelSource, "_hanging");
		gen.blockStateCollector.accept(VariantsBlockStateSupplier.create(child).coordinate(BlockStateModelGenerator.createBooleanModelMap(Properties.HANGING, hangingModel, model)));
		gen.registerParentedItemModel(child.asItem(), ModelIds.getItemModelId(modelSource.asItem()));
	}
	
	public static BlockStateSupplier createBoolPropertySlabBlockState(Block slab, BooleanProperty property, Identifier bottomFalseModel, Identifier bottomTrueModel, Identifier topFalseModel, Identifier topTrueModel, Identifier doubleFalseModel, Identifier doubleTrueModel) {
		return VariantsBlockStateSupplier.create(slab)
				.coordinate(BlockStateVariantMap.create(property, Properties.SLAB_TYPE)
						.register(false, SlabType.BOTTOM, BlockStateVariant.create().put(VariantSettings.MODEL, bottomFalseModel))
						.register(false, SlabType.TOP, BlockStateVariant.create().put(VariantSettings.MODEL, topFalseModel))
						.register(false, SlabType.DOUBLE, BlockStateVariant.create().put(VariantSettings.MODEL, doubleFalseModel))
						.register(true, SlabType.BOTTOM, BlockStateVariant.create().put(VariantSettings.MODEL, bottomTrueModel))
						.register(true, SlabType.TOP, BlockStateVariant.create().put(VariantSettings.MODEL, topTrueModel))
						.register(true, SlabType.DOUBLE, BlockStateVariant.create().put(VariantSettings.MODEL, doubleTrueModel)));
	}
	
	public static BlockStateSupplier createOrientableSlabBlockState(Block slab, Identifier bottomModel, Identifier bottomSidewaysModel, Identifier topModel, Identifier topSidewaysModel, Identifier doubleModel) {
		return createOrientableSlabBlockState(slab,
				bottomModel,
				bottomSidewaysModel,
				topModel,
				topSidewaysModel,
				BlockStateVariant.create()
				.put(VariantSettings.MODEL, doubleModel)
				.put(VariantSettings.X, VariantSettings.Rotation.R90)
				.put(VariantSettings.Y, VariantSettings.Rotation.R90),
				BlockStateVariant.create()
				.put(VariantSettings.MODEL, doubleModel),
				BlockStateVariant.create()
				.put(VariantSettings.MODEL, doubleModel)
				.put(VariantSettings.X, VariantSettings.Rotation.R90));
	}
	
	public static BlockStateSupplier createOrientableSlabBlockState(Block slab, Identifier bottomModel, Identifier bottomSidewaysModel, Identifier topModel, Identifier topSidewaysModel, Identifier doubleModel, Identifier doubleSidewaysModel) {
		return createOrientableSlabBlockState(slab,
				bottomModel,
				bottomSidewaysModel,
				topModel,
				topSidewaysModel,
				BlockStateVariant.create()
				.put(VariantSettings.MODEL, doubleSidewaysModel)
				.put(VariantSettings.X, VariantSettings.Rotation.R90)
				.put(VariantSettings.Y, VariantSettings.Rotation.R90),
				BlockStateVariant.create()
				.put(VariantSettings.MODEL, doubleModel),
				BlockStateVariant.create()
				.put(VariantSettings.MODEL, doubleSidewaysModel)
				.put(VariantSettings.X, VariantSettings.Rotation.R90));
	}
	
	public static BlockStateSupplier createOrientableSlabBlockState(Block slab, Identifier bottomModel, Identifier bottomSidewaysModel, Identifier topModel, Identifier topSidewaysModel, Identifier doubleXModel, Identifier doubleYModel, Identifier doubleZModel) {
		return createOrientableSlabBlockState(slab,
				bottomModel,
				bottomSidewaysModel,
				topModel,
				topSidewaysModel,
				BlockStateVariant.create()
				.put(VariantSettings.MODEL, doubleXModel),
				BlockStateVariant.create()
				.put(VariantSettings.MODEL, doubleYModel),
				BlockStateVariant.create()
				.put(VariantSettings.MODEL, doubleZModel));
	}
	
	private static BlockStateSupplier createOrientableSlabBlockState(Block slab, Identifier bottomModel, Identifier bottomSidewaysModel, Identifier topModel, Identifier topSidewaysModel, BlockStateVariant doubleXVariant, BlockStateVariant doubleYVariant, BlockStateVariant doubleZVariant) {
		return VariantsBlockStateSupplier.create(slab)
				.coordinate(BlockStateVariantMap.create(Properties.AXIS, Properties.SLAB_TYPE)
						.register(Direction.Axis.X,
								SlabType.BOTTOM,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, bottomSidewaysModel)
								.put(VariantSettings.Y, VariantSettings.Rotation.R90))
						.register(Direction.Axis.X,
								SlabType.DOUBLE,
								doubleXVariant)
						.register(Direction.Axis.X,
								SlabType.TOP,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, topSidewaysModel)
								.put(VariantSettings.Y, VariantSettings.Rotation.R90))
						.register(Direction.Axis.Y,
								SlabType.BOTTOM,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, bottomModel))
						.register(Direction.Axis.Y,
								SlabType.DOUBLE,
								doubleYVariant)
						.register(Direction.Axis.Y,
								SlabType.TOP,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, topModel))
						.register(Direction.Axis.Z,
								SlabType.BOTTOM,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, bottomSidewaysModel))
						.register(Direction.Axis.Z,
								SlabType.DOUBLE,
								doubleZVariant)
						.register(Direction.Axis.Z,
								SlabType.TOP,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, topSidewaysModel)));
	}
	
	public static BlockStateSupplier createBoolPropertyStairsBlockState(Block stairs, BooleanProperty property, Identifier innerFalseModel, Identifier innerTrueModel, Identifier regularFalseModel, Identifier regularTrueModel, Identifier outerFalseModel, Identifier outerTrueModel) {
		return VariantsBlockStateSupplier.create(stairs)
				.coordinate(BlockStateVariantMap.create(property, Properties.HORIZONTAL_FACING, Properties.BLOCK_HALF, Properties.STAIR_SHAPE)
						.register(false, Direction.EAST, BlockHalf.BOTTOM, StairShape.INNER_LEFT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, innerFalseModel)
								.put(VariantSettings.Y, VariantSettings.Rotation.R270)
								.put(VariantSettings.UVLOCK, true))
						.register(false, Direction.EAST, BlockHalf.BOTTOM, StairShape.INNER_RIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, innerFalseModel))
						.register(false, Direction.EAST, BlockHalf.BOTTOM, StairShape.OUTER_LEFT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, outerFalseModel)
								.put(VariantSettings.Y, VariantSettings.Rotation.R270)
								.put(VariantSettings.UVLOCK, true))
						.register(false, Direction.EAST, BlockHalf.BOTTOM, StairShape.OUTER_RIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, outerFalseModel))
						.register(false, Direction.EAST, BlockHalf.BOTTOM, StairShape.STRAIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, regularFalseModel))
						.register(false, Direction.EAST, BlockHalf.TOP, StairShape.INNER_LEFT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, innerFalseModel)
								.put(VariantSettings.X, VariantSettings.Rotation.R180)
								.put(VariantSettings.UVLOCK, true))
						.register(false, Direction.EAST, BlockHalf.TOP, StairShape.INNER_RIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, innerFalseModel)
								.put(VariantSettings.X, VariantSettings.Rotation.R180)
								.put(VariantSettings.Y, VariantSettings.Rotation.R90)
								.put(VariantSettings.UVLOCK, true))
						.register(false, Direction.EAST, BlockHalf.TOP, StairShape.OUTER_LEFT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, outerFalseModel)
								.put(VariantSettings.X, VariantSettings.Rotation.R180)
								.put(VariantSettings.UVLOCK, true))
						.register(false, Direction.EAST, BlockHalf.TOP, StairShape.OUTER_RIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, outerFalseModel)
								.put(VariantSettings.X, VariantSettings.Rotation.R180)
								.put(VariantSettings.Y, VariantSettings.Rotation.R90)
								.put(VariantSettings.UVLOCK, true))
						.register(false, Direction.EAST, BlockHalf.TOP, StairShape.STRAIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, regularFalseModel)
								.put(VariantSettings.X, VariantSettings.Rotation.R180)
								.put(VariantSettings.UVLOCK, true))
						.register(false, Direction.NORTH, BlockHalf.BOTTOM, StairShape.INNER_LEFT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, innerFalseModel)
								.put(VariantSettings.Y, VariantSettings.Rotation.R180)
								.put(VariantSettings.UVLOCK, true))
						.register(false, Direction.NORTH, BlockHalf.BOTTOM, StairShape.INNER_RIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, innerFalseModel)
								.put(VariantSettings.Y, VariantSettings.Rotation.R270)
								.put(VariantSettings.UVLOCK, true))
						.register(false, Direction.NORTH, BlockHalf.BOTTOM, StairShape.OUTER_LEFT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, outerFalseModel)
								.put(VariantSettings.Y, VariantSettings.Rotation.R180)
								.put(VariantSettings.UVLOCK, true))
						.register(false, Direction.NORTH, BlockHalf.BOTTOM, StairShape.OUTER_RIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, outerFalseModel)
								.put(VariantSettings.Y, VariantSettings.Rotation.R270)
								.put(VariantSettings.UVLOCK, true))
						.register(false, Direction.NORTH, BlockHalf.BOTTOM, StairShape.STRAIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, regularFalseModel)
								.put(VariantSettings.Y, VariantSettings.Rotation.R270)
								.put(VariantSettings.UVLOCK, true))
						.register(false, Direction.NORTH, BlockHalf.TOP, StairShape.INNER_LEFT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, innerFalseModel)
								.put(VariantSettings.X, VariantSettings.Rotation.R180)
								.put(VariantSettings.Y, VariantSettings.Rotation.R270)
								.put(VariantSettings.UVLOCK, true))
						.register(false, Direction.NORTH, BlockHalf.TOP, StairShape.INNER_RIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, innerFalseModel)
								.put(VariantSettings.X, VariantSettings.Rotation.R180)
								.put(VariantSettings.UVLOCK, true))
						.register(false, Direction.NORTH, BlockHalf.TOP, StairShape.OUTER_LEFT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, outerFalseModel)
								.put(VariantSettings.X, VariantSettings.Rotation.R180)
								.put(VariantSettings.Y, VariantSettings.Rotation.R270)
								.put(VariantSettings.UVLOCK, true))
						.register(false, Direction.NORTH, BlockHalf.TOP, StairShape.OUTER_RIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, outerFalseModel)
								.put(VariantSettings.X, VariantSettings.Rotation.R180)
								.put(VariantSettings.UVLOCK, true))
						.register(false, Direction.NORTH, BlockHalf.TOP, StairShape.STRAIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, regularFalseModel)
								.put(VariantSettings.X, VariantSettings.Rotation.R180)
								.put(VariantSettings.Y, VariantSettings.Rotation.R270)
								.put(VariantSettings.UVLOCK, true))
						.register(false, Direction.SOUTH, BlockHalf.BOTTOM, StairShape.INNER_LEFT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, innerFalseModel))
						.register(false, Direction.SOUTH, BlockHalf.BOTTOM, StairShape.INNER_RIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, innerFalseModel)
								.put(VariantSettings.Y, VariantSettings.Rotation.R90)
								.put(VariantSettings.UVLOCK, true))
						.register(false, Direction.SOUTH, BlockHalf.BOTTOM, StairShape.OUTER_LEFT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, outerFalseModel))
						.register(false, Direction.SOUTH, BlockHalf.BOTTOM, StairShape.OUTER_RIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, outerFalseModel)
								.put(VariantSettings.Y, VariantSettings.Rotation.R90)
								.put(VariantSettings.UVLOCK, true))
						.register(false, Direction.SOUTH, BlockHalf.BOTTOM, StairShape.STRAIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, regularFalseModel)
								.put(VariantSettings.Y, VariantSettings.Rotation.R90)
								.put(VariantSettings.UVLOCK, true))
						.register(false, Direction.SOUTH, BlockHalf.TOP, StairShape.INNER_LEFT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, innerFalseModel)
								.put(VariantSettings.X, VariantSettings.Rotation.R180)
								.put(VariantSettings.Y, VariantSettings.Rotation.R90)
								.put(VariantSettings.UVLOCK, true))
						.register(false, Direction.SOUTH, BlockHalf.TOP, StairShape.INNER_RIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, innerFalseModel)
								.put(VariantSettings.X, VariantSettings.Rotation.R180)
								.put(VariantSettings.Y, VariantSettings.Rotation.R180)
								.put(VariantSettings.UVLOCK, true))
						.register(false, Direction.SOUTH, BlockHalf.TOP, StairShape.OUTER_LEFT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, outerFalseModel)
								.put(VariantSettings.X, VariantSettings.Rotation.R180)
								.put(VariantSettings.Y, VariantSettings.Rotation.R90)
								.put(VariantSettings.UVLOCK, true))
						.register(false, Direction.SOUTH, BlockHalf.TOP, StairShape.OUTER_RIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, outerFalseModel)
								.put(VariantSettings.X, VariantSettings.Rotation.R180)
								.put(VariantSettings.Y, VariantSettings.Rotation.R180)
								.put(VariantSettings.UVLOCK, true))
						.register(false, Direction.SOUTH, BlockHalf.TOP, StairShape.STRAIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, regularFalseModel)
								.put(VariantSettings.X, VariantSettings.Rotation.R180)
								.put(VariantSettings.Y, VariantSettings.Rotation.R90)
								.put(VariantSettings.UVLOCK, true))
						.register(false, Direction.WEST, BlockHalf.BOTTOM, StairShape.INNER_LEFT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, innerFalseModel)
								.put(VariantSettings.Y, VariantSettings.Rotation.R90)
								.put(VariantSettings.UVLOCK, true))
						.register(false, Direction.WEST, BlockHalf.BOTTOM, StairShape.INNER_RIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, innerFalseModel)
								.put(VariantSettings.Y, VariantSettings.Rotation.R180)
								.put(VariantSettings.UVLOCK, true))
						.register(false, Direction.WEST, BlockHalf.BOTTOM, StairShape.OUTER_LEFT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, outerFalseModel)
								.put(VariantSettings.Y, VariantSettings.Rotation.R90)
								.put(VariantSettings.UVLOCK, true))
						.register(false, Direction.WEST, BlockHalf.BOTTOM, StairShape.OUTER_RIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, outerFalseModel)
								.put(VariantSettings.Y, VariantSettings.Rotation.R180)
								.put(VariantSettings.UVLOCK, true))
						.register(false, Direction.WEST, BlockHalf.BOTTOM, StairShape.STRAIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, regularFalseModel)
								.put(VariantSettings.Y, VariantSettings.Rotation.R180)
								.put(VariantSettings.UVLOCK, true))
						.register(false, Direction.WEST, BlockHalf.TOP, StairShape.INNER_LEFT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, innerFalseModel)
								.put(VariantSettings.X, VariantSettings.Rotation.R180)
								.put(VariantSettings.Y, VariantSettings.Rotation.R180)
								.put(VariantSettings.UVLOCK, true))
						.register(false, Direction.WEST, BlockHalf.TOP, StairShape.INNER_RIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, innerFalseModel)
								.put(VariantSettings.X, VariantSettings.Rotation.R180)
								.put(VariantSettings.Y, VariantSettings.Rotation.R270)
								.put(VariantSettings.UVLOCK, true))
						.register(false, Direction.WEST, BlockHalf.TOP, StairShape.OUTER_LEFT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, outerFalseModel)
								.put(VariantSettings.X, VariantSettings.Rotation.R180)
								.put(VariantSettings.Y, VariantSettings.Rotation.R180)
								.put(VariantSettings.UVLOCK, true))
						.register(false, Direction.WEST, BlockHalf.TOP, StairShape.OUTER_RIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, outerFalseModel)
								.put(VariantSettings.X, VariantSettings.Rotation.R180)
								.put(VariantSettings.Y, VariantSettings.Rotation.R270)
								.put(VariantSettings.UVLOCK, true))
						.register(false, Direction.WEST, BlockHalf.TOP, StairShape.STRAIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, regularFalseModel)
								.put(VariantSettings.X, VariantSettings.Rotation.R180)
								.put(VariantSettings.Y, VariantSettings.Rotation.R180)
								.put(VariantSettings.UVLOCK, true))
						.register(true, Direction.EAST, BlockHalf.BOTTOM, StairShape.INNER_LEFT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, innerTrueModel)
								.put(VariantSettings.Y, VariantSettings.Rotation.R270)
								.put(VariantSettings.UVLOCK, true))
						.register(true, Direction.EAST, BlockHalf.BOTTOM, StairShape.INNER_RIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, innerTrueModel))
						.register(true, Direction.EAST, BlockHalf.BOTTOM, StairShape.OUTER_LEFT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, outerTrueModel)
								.put(VariantSettings.Y, VariantSettings.Rotation.R270)
								.put(VariantSettings.UVLOCK, true))
						.register(true, Direction.EAST, BlockHalf.BOTTOM, StairShape.OUTER_RIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, outerTrueModel))
						.register(true, Direction.EAST, BlockHalf.BOTTOM, StairShape.STRAIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, regularTrueModel))
						.register(true, Direction.EAST, BlockHalf.TOP, StairShape.INNER_LEFT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, innerTrueModel)
								.put(VariantSettings.X, VariantSettings.Rotation.R180)
								.put(VariantSettings.UVLOCK, true))
						.register(true, Direction.EAST, BlockHalf.TOP, StairShape.INNER_RIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, innerTrueModel)
								.put(VariantSettings.X, VariantSettings.Rotation.R180)
								.put(VariantSettings.Y, VariantSettings.Rotation.R90)
								.put(VariantSettings.UVLOCK, true))
						.register(true, Direction.EAST, BlockHalf.TOP, StairShape.OUTER_LEFT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, outerTrueModel)
								.put(VariantSettings.X, VariantSettings.Rotation.R180)
								.put(VariantSettings.UVLOCK, true))
						.register(true, Direction.EAST, BlockHalf.TOP, StairShape.OUTER_RIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, outerTrueModel)
								.put(VariantSettings.X, VariantSettings.Rotation.R180)
								.put(VariantSettings.Y, VariantSettings.Rotation.R90)
								.put(VariantSettings.UVLOCK, true))
						.register(true, Direction.EAST, BlockHalf.TOP, StairShape.STRAIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, regularTrueModel)
								.put(VariantSettings.X, VariantSettings.Rotation.R180)
								.put(VariantSettings.UVLOCK, true))
						.register(true, Direction.NORTH, BlockHalf.BOTTOM, StairShape.INNER_LEFT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, innerTrueModel)
								.put(VariantSettings.Y, VariantSettings.Rotation.R180)
								.put(VariantSettings.UVLOCK, true))
						.register(true, Direction.NORTH, BlockHalf.BOTTOM, StairShape.INNER_RIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, innerTrueModel)
								.put(VariantSettings.Y, VariantSettings.Rotation.R270)
								.put(VariantSettings.UVLOCK, true))
						.register(true, Direction.NORTH, BlockHalf.BOTTOM, StairShape.OUTER_LEFT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, outerTrueModel)
								.put(VariantSettings.Y, VariantSettings.Rotation.R180)
								.put(VariantSettings.UVLOCK, true))
						.register(true, Direction.NORTH, BlockHalf.BOTTOM, StairShape.OUTER_RIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, outerTrueModel)
								.put(VariantSettings.Y, VariantSettings.Rotation.R270)
								.put(VariantSettings.UVLOCK, true))
						.register(true, Direction.NORTH, BlockHalf.BOTTOM, StairShape.STRAIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, regularTrueModel)
								.put(VariantSettings.Y, VariantSettings.Rotation.R270)
								.put(VariantSettings.UVLOCK, true))
						.register(true, Direction.NORTH, BlockHalf.TOP, StairShape.INNER_LEFT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, innerTrueModel)
								.put(VariantSettings.X, VariantSettings.Rotation.R180)
								.put(VariantSettings.Y, VariantSettings.Rotation.R270)
								.put(VariantSettings.UVLOCK, true))
						.register(true, Direction.NORTH, BlockHalf.TOP, StairShape.INNER_RIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, innerTrueModel)
								.put(VariantSettings.X, VariantSettings.Rotation.R180)
								.put(VariantSettings.UVLOCK, true))
						.register(true, Direction.NORTH, BlockHalf.TOP, StairShape.OUTER_LEFT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, outerTrueModel)
								.put(VariantSettings.X, VariantSettings.Rotation.R180)
								.put(VariantSettings.Y, VariantSettings.Rotation.R270)
								.put(VariantSettings.UVLOCK, true))
						.register(true, Direction.NORTH, BlockHalf.TOP, StairShape.OUTER_RIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, outerTrueModel)
								.put(VariantSettings.X, VariantSettings.Rotation.R180)
								.put(VariantSettings.UVLOCK, true))
						.register(true, Direction.NORTH, BlockHalf.TOP, StairShape.STRAIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, regularTrueModel)
								.put(VariantSettings.X, VariantSettings.Rotation.R180)
								.put(VariantSettings.Y, VariantSettings.Rotation.R270)
								.put(VariantSettings.UVLOCK, true))
						.register(true, Direction.SOUTH, BlockHalf.BOTTOM, StairShape.INNER_LEFT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, innerTrueModel))
						.register(true, Direction.SOUTH, BlockHalf.BOTTOM, StairShape.INNER_RIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, innerTrueModel)
								.put(VariantSettings.Y, VariantSettings.Rotation.R90)
								.put(VariantSettings.UVLOCK, true))
						.register(true, Direction.SOUTH, BlockHalf.BOTTOM, StairShape.OUTER_LEFT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, outerTrueModel))
						.register(true, Direction.SOUTH, BlockHalf.BOTTOM, StairShape.OUTER_RIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, outerTrueModel)
								.put(VariantSettings.Y, VariantSettings.Rotation.R90)
								.put(VariantSettings.UVLOCK, true))
						.register(true, Direction.SOUTH, BlockHalf.BOTTOM, StairShape.STRAIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, regularTrueModel)
								.put(VariantSettings.Y, VariantSettings.Rotation.R90)
								.put(VariantSettings.UVLOCK, true))
						.register(true, Direction.SOUTH, BlockHalf.TOP, StairShape.INNER_LEFT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, innerTrueModel)
								.put(VariantSettings.X, VariantSettings.Rotation.R180)
								.put(VariantSettings.Y, VariantSettings.Rotation.R90)
								.put(VariantSettings.UVLOCK, true))
						.register(true, Direction.SOUTH, BlockHalf.TOP, StairShape.INNER_RIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, innerTrueModel)
								.put(VariantSettings.X, VariantSettings.Rotation.R180)
								.put(VariantSettings.Y, VariantSettings.Rotation.R180)
								.put(VariantSettings.UVLOCK, true))
						.register(true, Direction.SOUTH, BlockHalf.TOP, StairShape.OUTER_LEFT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, outerTrueModel)
								.put(VariantSettings.X, VariantSettings.Rotation.R180)
								.put(VariantSettings.Y, VariantSettings.Rotation.R90)
								.put(VariantSettings.UVLOCK, true))
						.register(true, Direction.SOUTH, BlockHalf.TOP, StairShape.OUTER_RIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, outerTrueModel)
								.put(VariantSettings.X, VariantSettings.Rotation.R180)
								.put(VariantSettings.Y, VariantSettings.Rotation.R180)
								.put(VariantSettings.UVLOCK, true))
						.register(true, Direction.SOUTH, BlockHalf.TOP, StairShape.STRAIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, regularTrueModel)
								.put(VariantSettings.X, VariantSettings.Rotation.R180)
								.put(VariantSettings.Y, VariantSettings.Rotation.R90)
								.put(VariantSettings.UVLOCK, true))
						.register(true, Direction.WEST, BlockHalf.BOTTOM, StairShape.INNER_LEFT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, innerTrueModel)
								.put(VariantSettings.Y, VariantSettings.Rotation.R90)
								.put(VariantSettings.UVLOCK, true))
						.register(true, Direction.WEST, BlockHalf.BOTTOM, StairShape.INNER_RIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, innerTrueModel)
								.put(VariantSettings.Y, VariantSettings.Rotation.R180)
								.put(VariantSettings.UVLOCK, true))
						.register(true, Direction.WEST, BlockHalf.BOTTOM, StairShape.OUTER_LEFT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, outerTrueModel)
								.put(VariantSettings.Y, VariantSettings.Rotation.R90)
								.put(VariantSettings.UVLOCK, true))
						.register(true, Direction.WEST, BlockHalf.BOTTOM, StairShape.OUTER_RIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, outerTrueModel)
								.put(VariantSettings.Y, VariantSettings.Rotation.R180)
								.put(VariantSettings.UVLOCK, true))
						.register(true, Direction.WEST, BlockHalf.BOTTOM, StairShape.STRAIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, regularTrueModel)
								.put(VariantSettings.Y, VariantSettings.Rotation.R180)
								.put(VariantSettings.UVLOCK, true))
						.register(true, Direction.WEST, BlockHalf.TOP, StairShape.INNER_LEFT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, innerTrueModel)
								.put(VariantSettings.X, VariantSettings.Rotation.R180)
								.put(VariantSettings.Y, VariantSettings.Rotation.R180)
								.put(VariantSettings.UVLOCK, true))
						.register(true, Direction.WEST, BlockHalf.TOP, StairShape.INNER_RIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, innerTrueModel)
								.put(VariantSettings.X, VariantSettings.Rotation.R180)
								.put(VariantSettings.Y, VariantSettings.Rotation.R270)
								.put(VariantSettings.UVLOCK, true))
						.register(true, Direction.WEST, BlockHalf.TOP, StairShape.OUTER_LEFT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, outerTrueModel)
								.put(VariantSettings.X, VariantSettings.Rotation.R180)
								.put(VariantSettings.Y, VariantSettings.Rotation.R180)
								.put(VariantSettings.UVLOCK, true))
						.register(true, Direction.WEST, BlockHalf.TOP, StairShape.OUTER_RIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, outerTrueModel)
								.put(VariantSettings.X, VariantSettings.Rotation.R180)
								.put(VariantSettings.Y, VariantSettings.Rotation.R270)
								.put(VariantSettings.UVLOCK, true))
						.register(true, Direction.WEST, BlockHalf.TOP, StairShape.STRAIGHT,
								BlockStateVariant.create()
								.put(VariantSettings.MODEL, regularTrueModel)
								.put(VariantSettings.X, VariantSettings.Rotation.R180)
								.put(VariantSettings.Y, VariantSettings.Rotation.R180)
								.put(VariantSettings.UVLOCK, true)));
	}
	
	public static BlockStateSupplier createFlippedStairsBlockState(Block stairsBlock, Identifier innerModel, Identifier innerFlippedModel, Identifier regularModel, Identifier regularFlippedModel, Identifier outerModel, Identifier outerFlippedModel) {
		return VariantsBlockStateSupplier.create(stairsBlock)
			.coordinate(
				BlockStateVariantMap.create(Properties.HORIZONTAL_FACING, Properties.BLOCK_HALF, Properties.STAIR_SHAPE)
					.register(Direction.EAST, BlockHalf.BOTTOM, StairShape.STRAIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, regularModel))
					.register(
						Direction.WEST,
						BlockHalf.BOTTOM,
						StairShape.STRAIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, regularModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R180)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						Direction.SOUTH,
						BlockHalf.BOTTOM,
						StairShape.STRAIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, regularModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R90)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						Direction.NORTH,
						BlockHalf.BOTTOM,
						StairShape.STRAIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, regularModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R270)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(Direction.EAST, BlockHalf.BOTTOM, StairShape.OUTER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModel))
					.register(
						Direction.WEST,
						BlockHalf.BOTTOM,
						StairShape.OUTER_RIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, outerModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R180)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						Direction.SOUTH,
						BlockHalf.BOTTOM,
						StairShape.OUTER_RIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, outerModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R90)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						Direction.NORTH,
						BlockHalf.BOTTOM,
						StairShape.OUTER_RIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, outerModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R270)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						Direction.EAST,
						BlockHalf.BOTTOM,
						StairShape.OUTER_LEFT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, outerModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R270)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						Direction.WEST,
						BlockHalf.BOTTOM,
						StairShape.OUTER_LEFT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, outerModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R90)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(Direction.SOUTH, BlockHalf.BOTTOM, StairShape.OUTER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModel))
					.register(
						Direction.NORTH,
						BlockHalf.BOTTOM,
						StairShape.OUTER_LEFT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, outerModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R180)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(Direction.EAST, BlockHalf.BOTTOM, StairShape.INNER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModel))
					.register(
						Direction.WEST,
						BlockHalf.BOTTOM,
						StairShape.INNER_RIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, innerModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R180)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						Direction.SOUTH,
						BlockHalf.BOTTOM,
						StairShape.INNER_RIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, innerModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R90)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						Direction.NORTH,
						BlockHalf.BOTTOM,
						StairShape.INNER_RIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, innerModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R270)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						Direction.EAST,
						BlockHalf.BOTTOM,
						StairShape.INNER_LEFT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, innerModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R270)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						Direction.WEST,
						BlockHalf.BOTTOM,
						StairShape.INNER_LEFT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, innerModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R90)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(Direction.SOUTH, BlockHalf.BOTTOM, StairShape.INNER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModel))
					.register(
						Direction.NORTH,
						BlockHalf.BOTTOM,
						StairShape.INNER_LEFT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, innerModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R180)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						Direction.EAST,
						BlockHalf.TOP,
						StairShape.STRAIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, regularFlippedModel)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						Direction.WEST,
						BlockHalf.TOP,
						StairShape.STRAIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, regularFlippedModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R180)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						Direction.SOUTH,
						BlockHalf.TOP,
						StairShape.STRAIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, regularFlippedModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R90)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						Direction.NORTH,
						BlockHalf.TOP,
						StairShape.STRAIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, regularFlippedModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R270)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						Direction.EAST,
						BlockHalf.TOP,
						StairShape.OUTER_RIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, outerFlippedModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R90)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						Direction.WEST,
						BlockHalf.TOP,
						StairShape.OUTER_RIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, outerFlippedModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R270)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						Direction.SOUTH,
						BlockHalf.TOP,
						StairShape.OUTER_RIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, outerFlippedModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R180)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						Direction.NORTH,
						BlockHalf.TOP,
						StairShape.OUTER_RIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, outerFlippedModel)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						Direction.EAST,
						BlockHalf.TOP,
						StairShape.OUTER_LEFT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, outerFlippedModel)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						Direction.WEST,
						BlockHalf.TOP,
						StairShape.OUTER_LEFT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, outerFlippedModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R180)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						Direction.SOUTH,
						BlockHalf.TOP,
						StairShape.OUTER_LEFT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, outerFlippedModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R90)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						Direction.NORTH,
						BlockHalf.TOP,
						StairShape.OUTER_LEFT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, outerFlippedModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R270)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						Direction.EAST,
						BlockHalf.TOP,
						StairShape.INNER_RIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, innerFlippedModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R90)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						Direction.WEST,
						BlockHalf.TOP,
						StairShape.INNER_RIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, innerFlippedModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R270)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						Direction.SOUTH,
						BlockHalf.TOP,
						StairShape.INNER_RIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, innerFlippedModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R180)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						Direction.NORTH,
						BlockHalf.TOP,
						StairShape.INNER_RIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, innerFlippedModel)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						Direction.EAST,
						BlockHalf.TOP,
						StairShape.INNER_LEFT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, innerFlippedModel)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						Direction.WEST,
						BlockHalf.TOP,
						StairShape.INNER_LEFT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, innerFlippedModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R180)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						Direction.SOUTH,
						BlockHalf.TOP,
						StairShape.INNER_LEFT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, innerFlippedModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R90)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						Direction.NORTH,
						BlockHalf.TOP,
						StairShape.INNER_LEFT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, innerFlippedModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R270)
							.put(VariantSettings.UVLOCK, true)
					)
			);
	}
	
	public static BlockStateSupplier createBoolPropertyFlippedStairsBlockState(Block stairsBlock, BooleanProperty property, Identifier innerModel, Identifier innerSnowyModel, Identifier innerFlippedModel, Identifier innerFlippedSnowyModel, Identifier regularModel, Identifier regularSnowyModel, Identifier regularFlippedModel, Identifier regularFlippedSnowyModel, Identifier outerModel, Identifier outerSnowyModel, Identifier outerFlippedModel, Identifier outerFlippedSnowyModel) {
		return VariantsBlockStateSupplier.create(stairsBlock)
			.coordinate(
				BlockStateVariantMap.create(property, Properties.HORIZONTAL_FACING, Properties.BLOCK_HALF, Properties.STAIR_SHAPE)
					.register(false, Direction.EAST, BlockHalf.BOTTOM, StairShape.STRAIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, regularModel))
					.register(
						false,
						Direction.WEST,
						BlockHalf.BOTTOM,
						StairShape.STRAIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, regularModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R180)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						false,
						Direction.SOUTH,
						BlockHalf.BOTTOM,
						StairShape.STRAIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, regularModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R90)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						false,
						Direction.NORTH,
						BlockHalf.BOTTOM,
						StairShape.STRAIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, regularModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R270)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(false, Direction.EAST, BlockHalf.BOTTOM, StairShape.OUTER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModel))
					.register(
						false,
						Direction.WEST,
						BlockHalf.BOTTOM,
						StairShape.OUTER_RIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, outerModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R180)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						false,
						Direction.SOUTH,
						BlockHalf.BOTTOM,
						StairShape.OUTER_RIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, outerModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R90)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						false,
						Direction.NORTH,
						BlockHalf.BOTTOM,
						StairShape.OUTER_RIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, outerModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R270)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						false,
						Direction.EAST,
						BlockHalf.BOTTOM,
						StairShape.OUTER_LEFT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, outerModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R270)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						false,
						Direction.WEST,
						BlockHalf.BOTTOM,
						StairShape.OUTER_LEFT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, outerModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R90)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(false, Direction.SOUTH, BlockHalf.BOTTOM, StairShape.OUTER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModel))
					.register(
						false,
						Direction.NORTH,
						BlockHalf.BOTTOM,
						StairShape.OUTER_LEFT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, outerModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R180)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(false, Direction.EAST, BlockHalf.BOTTOM, StairShape.INNER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModel))
					.register(
						false,
						Direction.WEST,
						BlockHalf.BOTTOM,
						StairShape.INNER_RIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, innerModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R180)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						false,
						Direction.SOUTH,
						BlockHalf.BOTTOM,
						StairShape.INNER_RIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, innerModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R90)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						false,
						Direction.NORTH,
						BlockHalf.BOTTOM,
						StairShape.INNER_RIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, innerModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R270)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						false,
						Direction.EAST,
						BlockHalf.BOTTOM,
						StairShape.INNER_LEFT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, innerModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R270)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						false,
						Direction.WEST,
						BlockHalf.BOTTOM,
						StairShape.INNER_LEFT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, innerModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R90)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(false, Direction.SOUTH, BlockHalf.BOTTOM, StairShape.INNER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModel))
					.register(
						false,
						Direction.NORTH,
						BlockHalf.BOTTOM,
						StairShape.INNER_LEFT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, innerModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R180)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						false,
						Direction.EAST,
						BlockHalf.TOP,
						StairShape.STRAIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, regularFlippedModel)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						false,
						Direction.WEST,
						BlockHalf.TOP,
						StairShape.STRAIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, regularFlippedModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R180)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						false,
						Direction.SOUTH,
						BlockHalf.TOP,
						StairShape.STRAIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, regularFlippedModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R90)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						false,
						Direction.NORTH,
						BlockHalf.TOP,
						StairShape.STRAIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, regularFlippedModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R270)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						false,
						Direction.EAST,
						BlockHalf.TOP,
						StairShape.OUTER_RIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, outerFlippedModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R90)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						false,
						Direction.WEST,
						BlockHalf.TOP,
						StairShape.OUTER_RIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, outerFlippedModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R270)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						false,
						Direction.SOUTH,
						BlockHalf.TOP,
						StairShape.OUTER_RIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, outerFlippedModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R180)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						false,
						Direction.NORTH,
						BlockHalf.TOP,
						StairShape.OUTER_RIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, outerFlippedModel)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						false,
						Direction.EAST,
						BlockHalf.TOP,
						StairShape.OUTER_LEFT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, outerFlippedModel)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						false,
						Direction.WEST,
						BlockHalf.TOP,
						StairShape.OUTER_LEFT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, outerFlippedModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R180)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						false,
						Direction.SOUTH,
						BlockHalf.TOP,
						StairShape.OUTER_LEFT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, outerFlippedModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R90)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						false,
						Direction.NORTH,
						BlockHalf.TOP,
						StairShape.OUTER_LEFT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, outerFlippedModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R270)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						false,
						Direction.EAST,
						BlockHalf.TOP,
						StairShape.INNER_RIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, innerFlippedModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R90)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						false,
						Direction.WEST,
						BlockHalf.TOP,
						StairShape.INNER_RIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, innerFlippedModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R270)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						false,
						Direction.SOUTH,
						BlockHalf.TOP,
						StairShape.INNER_RIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, innerFlippedModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R180)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						false,
						Direction.NORTH,
						BlockHalf.TOP,
						StairShape.INNER_RIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, innerFlippedModel)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						false,
						Direction.EAST,
						BlockHalf.TOP,
						StairShape.INNER_LEFT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, innerFlippedModel)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						false,
						Direction.WEST,
						BlockHalf.TOP,
						StairShape.INNER_LEFT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, innerFlippedModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R180)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						false,
						Direction.SOUTH,
						BlockHalf.TOP,
						StairShape.INNER_LEFT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, innerFlippedModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R90)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						false,
						Direction.NORTH,
						BlockHalf.TOP,
						StairShape.INNER_LEFT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, innerFlippedModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R270)
							.put(VariantSettings.UVLOCK, true)
					)
					
					.register(true, Direction.EAST, BlockHalf.BOTTOM, StairShape.STRAIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, regularSnowyModel))
					.register(
						true,
						Direction.WEST,
						BlockHalf.BOTTOM,
						StairShape.STRAIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, regularSnowyModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R180)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						true,
						Direction.SOUTH,
						BlockHalf.BOTTOM,
						StairShape.STRAIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, regularSnowyModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R90)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						true,
						Direction.NORTH,
						BlockHalf.BOTTOM,
						StairShape.STRAIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, regularSnowyModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R270)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(true, Direction.EAST, BlockHalf.BOTTOM, StairShape.OUTER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, outerSnowyModel))
					.register(
						true,
						Direction.WEST,
						BlockHalf.BOTTOM,
						StairShape.OUTER_RIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, outerSnowyModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R180)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						true,
						Direction.SOUTH,
						BlockHalf.BOTTOM,
						StairShape.OUTER_RIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, outerSnowyModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R90)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						true,
						Direction.NORTH,
						BlockHalf.BOTTOM,
						StairShape.OUTER_RIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, outerSnowyModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R270)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						true,
						Direction.EAST,
						BlockHalf.BOTTOM,
						StairShape.OUTER_LEFT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, outerSnowyModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R270)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						true,
						Direction.WEST,
						BlockHalf.BOTTOM,
						StairShape.OUTER_LEFT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, outerSnowyModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R90)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(true, Direction.SOUTH, BlockHalf.BOTTOM, StairShape.OUTER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, outerSnowyModel))
					.register(
						true,
						Direction.NORTH,
						BlockHalf.BOTTOM,
						StairShape.OUTER_LEFT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, outerSnowyModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R180)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(true, Direction.EAST, BlockHalf.BOTTOM, StairShape.INNER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, innerSnowyModel))
					.register(
						true,
						Direction.WEST,
						BlockHalf.BOTTOM,
						StairShape.INNER_RIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, innerSnowyModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R180)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						true,
						Direction.SOUTH,
						BlockHalf.BOTTOM,
						StairShape.INNER_RIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, innerSnowyModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R90)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						true,
						Direction.NORTH,
						BlockHalf.BOTTOM,
						StairShape.INNER_RIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, innerSnowyModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R270)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						true,
						Direction.EAST,
						BlockHalf.BOTTOM,
						StairShape.INNER_LEFT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, innerSnowyModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R270)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						true,
						Direction.WEST,
						BlockHalf.BOTTOM,
						StairShape.INNER_LEFT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, innerSnowyModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R90)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(true, Direction.SOUTH, BlockHalf.BOTTOM, StairShape.INNER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, innerSnowyModel))
					.register(
						true,
						Direction.NORTH,
						BlockHalf.BOTTOM,
						StairShape.INNER_LEFT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, innerSnowyModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R180)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						true,
						Direction.EAST,
						BlockHalf.TOP,
						StairShape.STRAIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, regularFlippedSnowyModel)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						true,
						Direction.WEST,
						BlockHalf.TOP,
						StairShape.STRAIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, regularFlippedSnowyModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R180)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						true,
						Direction.SOUTH,
						BlockHalf.TOP,
						StairShape.STRAIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, regularFlippedSnowyModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R90)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						true,
						Direction.NORTH,
						BlockHalf.TOP,
						StairShape.STRAIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, regularFlippedSnowyModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R270)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						true,
						Direction.EAST,
						BlockHalf.TOP,
						StairShape.OUTER_RIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, outerFlippedSnowyModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R90)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						true,
						Direction.WEST,
						BlockHalf.TOP,
						StairShape.OUTER_RIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, outerFlippedSnowyModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R270)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						true,
						Direction.SOUTH,
						BlockHalf.TOP,
						StairShape.OUTER_RIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, outerFlippedSnowyModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R180)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						true,
						Direction.NORTH,
						BlockHalf.TOP,
						StairShape.OUTER_RIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, outerFlippedSnowyModel)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						true,
						Direction.EAST,
						BlockHalf.TOP,
						StairShape.OUTER_LEFT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, outerFlippedSnowyModel)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						true,
						Direction.WEST,
						BlockHalf.TOP,
						StairShape.OUTER_LEFT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, outerFlippedSnowyModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R180)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						true,
						Direction.SOUTH,
						BlockHalf.TOP,
						StairShape.OUTER_LEFT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, outerFlippedSnowyModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R90)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						true,
						Direction.NORTH,
						BlockHalf.TOP,
						StairShape.OUTER_LEFT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, outerFlippedSnowyModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R270)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						true,
						Direction.EAST,
						BlockHalf.TOP,
						StairShape.INNER_RIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, innerFlippedSnowyModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R90)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						true,
						Direction.WEST,
						BlockHalf.TOP,
						StairShape.INNER_RIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, innerFlippedSnowyModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R270)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						true,
						Direction.SOUTH,
						BlockHalf.TOP,
						StairShape.INNER_RIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, innerFlippedSnowyModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R180)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						true,
						Direction.NORTH,
						BlockHalf.TOP,
						StairShape.INNER_RIGHT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, innerFlippedSnowyModel)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						true,
						Direction.EAST,
						BlockHalf.TOP,
						StairShape.INNER_LEFT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, innerFlippedSnowyModel)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						true,
						Direction.WEST,
						BlockHalf.TOP,
						StairShape.INNER_LEFT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, innerFlippedSnowyModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R180)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						true,
						Direction.SOUTH,
						BlockHalf.TOP,
						StairShape.INNER_LEFT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, innerFlippedSnowyModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R90)
							.put(VariantSettings.UVLOCK, true)
					)
					.register(
						true,
						Direction.NORTH,
						BlockHalf.TOP,
						StairShape.INNER_LEFT,
						BlockStateVariant.create()
							.put(VariantSettings.MODEL, innerFlippedSnowyModel)
							.put(VariantSettings.Y, VariantSettings.Rotation.R270)
							.put(VariantSettings.UVLOCK, true)
					)
			);
	}
	
	public static BlockStateSupplier createOrientableStairsBlockState(Block block, Identifier innerModel, Identifier innerMirroredModel, Identifier regularModel, Identifier regularFrontModel, Identifier regularSidewaysModel, Identifier outerModel, Identifier outerOrientedModel, Identifier outerOrientedMirroredModel) {
		return VariantsBlockStateSupplier.create(block)
				.coordinate(BlockStateVariantMap.create(Properties.AXIS, Properties.HORIZONTAL_FACING, Properties.BLOCK_HALF, Properties.STAIR_SHAPE)
						//east x
						.register(Direction.Axis.X, Direction.EAST, BlockHalf.BOTTOM, StairShape.INNER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, innerMirroredModel).put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R270))
						.register(Direction.Axis.X, Direction.EAST, BlockHalf.BOTTOM, StairShape.INNER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModel).put(VariantSettings.X, VariantSettings.Rotation.R270).put(VariantSettings.Y, VariantSettings.Rotation.R270))
						.register(Direction.Axis.X, Direction.EAST, BlockHalf.BOTTOM, StairShape.OUTER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, outerOrientedMirroredModel))
						.register(Direction.Axis.X, Direction.EAST, BlockHalf.BOTTOM, StairShape.OUTER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, outerOrientedModel))
						.register(Direction.Axis.X, Direction.EAST, BlockHalf.BOTTOM, StairShape.STRAIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, regularFrontModel))
						.register(Direction.Axis.X, Direction.EAST, BlockHalf.TOP, StairShape.INNER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModel).put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R270))
						.register(Direction.Axis.X, Direction.EAST, BlockHalf.TOP, StairShape.INNER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, innerMirroredModel).put(VariantSettings.X, VariantSettings.Rotation.R270).put(VariantSettings.Y, VariantSettings.Rotation.R270))
						.register(Direction.Axis.X, Direction.EAST, BlockHalf.TOP, StairShape.OUTER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, outerOrientedModel).put(VariantSettings.X, VariantSettings.Rotation.R180))
						.register(Direction.Axis.X, Direction.EAST, BlockHalf.TOP, StairShape.OUTER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, outerOrientedMirroredModel).put(VariantSettings.X, VariantSettings.Rotation.R180))
						.register(Direction.Axis.X, Direction.EAST, BlockHalf.TOP, StairShape.STRAIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, regularFrontModel).put(VariantSettings.X, VariantSettings.Rotation.R180))
						//north x
						.register(Direction.Axis.X, Direction.NORTH, BlockHalf.BOTTOM, StairShape.INNER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModel).put(VariantSettings.X, VariantSettings.Rotation.R270).put(VariantSettings.Y, VariantSettings.Rotation.R270))
						.register(Direction.Axis.X, Direction.NORTH, BlockHalf.BOTTOM, StairShape.INNER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, innerMirroredModel).put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R270))
						.register(Direction.Axis.X, Direction.NORTH, BlockHalf.BOTTOM, StairShape.OUTER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, outerOrientedModel).put(VariantSettings.Y, VariantSettings.Rotation.R180))
						.register(Direction.Axis.X, Direction.NORTH, BlockHalf.BOTTOM, StairShape.OUTER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, outerOrientedMirroredModel))
						.register(Direction.Axis.X, Direction.NORTH, BlockHalf.BOTTOM, StairShape.STRAIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, regularSidewaysModel).put(VariantSettings.Y, VariantSettings.Rotation.R270))
						.register(Direction.Axis.X, Direction.NORTH, BlockHalf.TOP, StairShape.INNER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, innerMirroredModel).put(VariantSettings.X, VariantSettings.Rotation.R270).put(VariantSettings.Y, VariantSettings.Rotation.R270))
						.register(Direction.Axis.X, Direction.NORTH, BlockHalf.TOP, StairShape.INNER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModel).put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R270))
						.register(Direction.Axis.X, Direction.NORTH, BlockHalf.TOP, StairShape.OUTER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, outerOrientedMirroredModel).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R180))
						.register(Direction.Axis.X, Direction.NORTH, BlockHalf.TOP, StairShape.OUTER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, outerOrientedModel).put(VariantSettings.X, VariantSettings.Rotation.R180))
						.register(Direction.Axis.X, Direction.NORTH, BlockHalf.TOP, StairShape.STRAIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, regularSidewaysModel).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R270))
						//south x
						.register(Direction.Axis.X, Direction.SOUTH, BlockHalf.BOTTOM, StairShape.INNER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModel).put(VariantSettings.X, VariantSettings.Rotation.R270).put(VariantSettings.Y, VariantSettings.Rotation.R90))
						.register(Direction.Axis.X, Direction.SOUTH, BlockHalf.BOTTOM, StairShape.INNER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, innerMirroredModel).put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R90))
						.register(Direction.Axis.X, Direction.SOUTH, BlockHalf.BOTTOM, StairShape.OUTER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, outerOrientedModel))
						.register(Direction.Axis.X, Direction.SOUTH, BlockHalf.BOTTOM, StairShape.OUTER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, outerOrientedMirroredModel).put(VariantSettings.Y, VariantSettings.Rotation.R180))
						.register(Direction.Axis.X, Direction.SOUTH, BlockHalf.BOTTOM, StairShape.STRAIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, regularSidewaysModel).put(VariantSettings.Y, VariantSettings.Rotation.R90))
						.register(Direction.Axis.X, Direction.SOUTH, BlockHalf.TOP, StairShape.INNER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, innerMirroredModel).put(VariantSettings.X, VariantSettings.Rotation.R270).put(VariantSettings.Y, VariantSettings.Rotation.R90))
						.register(Direction.Axis.X, Direction.SOUTH, BlockHalf.TOP, StairShape.INNER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModel).put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R90))
						.register(Direction.Axis.X, Direction.SOUTH, BlockHalf.TOP, StairShape.OUTER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, outerOrientedMirroredModel).put(VariantSettings.X, VariantSettings.Rotation.R180))
						.register(Direction.Axis.X, Direction.SOUTH, BlockHalf.TOP, StairShape.OUTER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, outerOrientedModel).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R180))
						.register(Direction.Axis.X, Direction.SOUTH, BlockHalf.TOP, StairShape.STRAIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, regularSidewaysModel).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R90))
						//west x
						.register(Direction.Axis.X, Direction.WEST, BlockHalf.BOTTOM, StairShape.INNER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, innerMirroredModel).put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R90))
						.register(Direction.Axis.X, Direction.WEST, BlockHalf.BOTTOM, StairShape.INNER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModel).put(VariantSettings.X, VariantSettings.Rotation.R270).put(VariantSettings.Y, VariantSettings.Rotation.R90))
						.register(Direction.Axis.X, Direction.WEST, BlockHalf.BOTTOM, StairShape.OUTER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, outerOrientedMirroredModel).put(VariantSettings.Y, VariantSettings.Rotation.R180))
						.register(Direction.Axis.X, Direction.WEST, BlockHalf.BOTTOM, StairShape.OUTER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, outerOrientedModel).put(VariantSettings.Y, VariantSettings.Rotation.R180))
						.register(Direction.Axis.X, Direction.WEST, BlockHalf.BOTTOM, StairShape.STRAIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, regularFrontModel).put(VariantSettings.Y, VariantSettings.Rotation.R180))
						.register(Direction.Axis.X, Direction.WEST, BlockHalf.TOP, StairShape.INNER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModel).put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R90))
						.register(Direction.Axis.X, Direction.WEST, BlockHalf.TOP, StairShape.INNER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, innerMirroredModel).put(VariantSettings.X, VariantSettings.Rotation.R270).put(VariantSettings.Y, VariantSettings.Rotation.R90))
						.register(Direction.Axis.X, Direction.WEST, BlockHalf.TOP, StairShape.OUTER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, outerOrientedModel).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R180))
						.register(Direction.Axis.X, Direction.WEST, BlockHalf.TOP, StairShape.OUTER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, outerOrientedMirroredModel).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R180))
						.register(Direction.Axis.X, Direction.WEST, BlockHalf.TOP, StairShape.STRAIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, regularFrontModel).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R180))
						//east y
						.register(Direction.Axis.Y, Direction.EAST, BlockHalf.BOTTOM, StairShape.INNER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, innerMirroredModel))
						.register(Direction.Axis.Y, Direction.EAST, BlockHalf.BOTTOM, StairShape.INNER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModel))
						.register(Direction.Axis.Y, Direction.EAST, BlockHalf.BOTTOM, StairShape.OUTER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModel).put(VariantSettings.Y, VariantSettings.Rotation.R270))
						.register(Direction.Axis.Y, Direction.EAST, BlockHalf.BOTTOM, StairShape.OUTER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModel))
						.register(Direction.Axis.Y, Direction.EAST, BlockHalf.BOTTOM, StairShape.STRAIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, regularModel))
						.register(Direction.Axis.Y, Direction.EAST, BlockHalf.TOP, StairShape.INNER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModel).put(VariantSettings.X, VariantSettings.Rotation.R180))
						.register(Direction.Axis.Y, Direction.EAST, BlockHalf.TOP, StairShape.INNER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, innerMirroredModel).put(VariantSettings.X, VariantSettings.Rotation.R180))
						.register(Direction.Axis.Y, Direction.EAST, BlockHalf.TOP, StairShape.OUTER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModel).put(VariantSettings.X, VariantSettings.Rotation.R180))
						.register(Direction.Axis.Y, Direction.EAST, BlockHalf.TOP, StairShape.OUTER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModel).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R90))
						.register(Direction.Axis.Y, Direction.EAST, BlockHalf.TOP, StairShape.STRAIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, regularFrontModel).put(VariantSettings.X, VariantSettings.Rotation.R180))
						//north y
						.register(Direction.Axis.Y, Direction.NORTH, BlockHalf.BOTTOM, StairShape.INNER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, innerMirroredModel).put(VariantSettings.Y, VariantSettings.Rotation.R270))
						.register(Direction.Axis.Y, Direction.NORTH, BlockHalf.BOTTOM, StairShape.INNER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModel).put(VariantSettings.Y, VariantSettings.Rotation.R270))
						.register(Direction.Axis.Y, Direction.NORTH, BlockHalf.BOTTOM, StairShape.OUTER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModel).put(VariantSettings.Y, VariantSettings.Rotation.R180))
						.register(Direction.Axis.Y, Direction.NORTH, BlockHalf.BOTTOM, StairShape.OUTER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModel).put(VariantSettings.Y, VariantSettings.Rotation.R270))
						.register(Direction.Axis.Y, Direction.NORTH, BlockHalf.BOTTOM, StairShape.STRAIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, regularModel).put(VariantSettings.Y, VariantSettings.Rotation.R270))
						.register(Direction.Axis.Y, Direction.NORTH, BlockHalf.TOP, StairShape.INNER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModel).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R270))
						.register(Direction.Axis.Y, Direction.NORTH, BlockHalf.TOP, StairShape.INNER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, innerMirroredModel).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R270))
						.register(Direction.Axis.Y, Direction.NORTH, BlockHalf.TOP, StairShape.OUTER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModel).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R270))
						.register(Direction.Axis.Y, Direction.NORTH, BlockHalf.TOP, StairShape.OUTER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModel).put(VariantSettings.X, VariantSettings.Rotation.R180))
						.register(Direction.Axis.Y, Direction.NORTH, BlockHalf.TOP, StairShape.STRAIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, regularFrontModel).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R270))
						//south y
						.register(Direction.Axis.Y, Direction.SOUTH, BlockHalf.BOTTOM, StairShape.INNER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, innerMirroredModel).put(VariantSettings.Y, VariantSettings.Rotation.R90))
						.register(Direction.Axis.Y, Direction.SOUTH, BlockHalf.BOTTOM, StairShape.INNER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModel).put(VariantSettings.Y, VariantSettings.Rotation.R90))
						.register(Direction.Axis.Y, Direction.SOUTH, BlockHalf.BOTTOM, StairShape.OUTER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModel))
						.register(Direction.Axis.Y, Direction.SOUTH, BlockHalf.BOTTOM, StairShape.OUTER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModel).put(VariantSettings.Y, VariantSettings.Rotation.R90))
						.register(Direction.Axis.Y, Direction.SOUTH, BlockHalf.BOTTOM, StairShape.STRAIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, regularModel).put(VariantSettings.Y, VariantSettings.Rotation.R90))
						.register(Direction.Axis.Y, Direction.SOUTH, BlockHalf.TOP, StairShape.INNER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModel).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R90))
						.register(Direction.Axis.Y, Direction.SOUTH, BlockHalf.TOP, StairShape.INNER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, innerMirroredModel).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R90))
						.register(Direction.Axis.Y, Direction.SOUTH, BlockHalf.TOP, StairShape.OUTER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModel).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R90))
						.register(Direction.Axis.Y, Direction.SOUTH, BlockHalf.TOP, StairShape.OUTER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModel).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R180))
						.register(Direction.Axis.Y, Direction.SOUTH, BlockHalf.TOP, StairShape.STRAIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, regularFrontModel).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R90))
						//west y
						.register(Direction.Axis.Y, Direction.WEST, BlockHalf.BOTTOM, StairShape.INNER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, innerMirroredModel).put(VariantSettings.Y, VariantSettings.Rotation.R180))
						.register(Direction.Axis.Y, Direction.WEST, BlockHalf.BOTTOM, StairShape.INNER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModel).put(VariantSettings.Y, VariantSettings.Rotation.R180))
						.register(Direction.Axis.Y, Direction.WEST, BlockHalf.BOTTOM, StairShape.OUTER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModel).put(VariantSettings.Y, VariantSettings.Rotation.R90))
						.register(Direction.Axis.Y, Direction.WEST, BlockHalf.BOTTOM, StairShape.OUTER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModel).put(VariantSettings.Y, VariantSettings.Rotation.R180))
						.register(Direction.Axis.Y, Direction.WEST, BlockHalf.BOTTOM, StairShape.STRAIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, regularModel).put(VariantSettings.Y, VariantSettings.Rotation.R180))
						.register(Direction.Axis.Y, Direction.WEST, BlockHalf.TOP, StairShape.INNER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModel).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R180))
						.register(Direction.Axis.Y, Direction.WEST, BlockHalf.TOP, StairShape.INNER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, innerMirroredModel).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R180))
						.register(Direction.Axis.Y, Direction.WEST, BlockHalf.TOP, StairShape.OUTER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModel).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R180))
						.register(Direction.Axis.Y, Direction.WEST, BlockHalf.TOP, StairShape.OUTER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, outerModel).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R270))
						.register(Direction.Axis.Y, Direction.WEST, BlockHalf.TOP, StairShape.STRAIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, regularFrontModel).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R180))
						//east z
						.register(Direction.Axis.Z, Direction.EAST, BlockHalf.BOTTOM, StairShape.INNER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModel).put(VariantSettings.X, VariantSettings.Rotation.R270))
						.register(Direction.Axis.Z, Direction.EAST, BlockHalf.BOTTOM, StairShape.INNER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, innerMirroredModel).put(VariantSettings.X, VariantSettings.Rotation.R90))
						.register(Direction.Axis.Z, Direction.EAST, BlockHalf.BOTTOM, StairShape.OUTER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, outerOrientedModel).put(VariantSettings.Y, VariantSettings.Rotation.R270))
						.register(Direction.Axis.Z, Direction.EAST, BlockHalf.BOTTOM, StairShape.OUTER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, outerOrientedMirroredModel).put(VariantSettings.Y, VariantSettings.Rotation.R90))
						.register(Direction.Axis.Z, Direction.EAST, BlockHalf.BOTTOM, StairShape.STRAIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, regularSidewaysModel))
						.register(Direction.Axis.Z, Direction.EAST, BlockHalf.TOP, StairShape.INNER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, innerMirroredModel).put(VariantSettings.X, VariantSettings.Rotation.R270))
						.register(Direction.Axis.Z, Direction.EAST, BlockHalf.TOP, StairShape.INNER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModel).put(VariantSettings.X, VariantSettings.Rotation.R90))
						.register(Direction.Axis.Z, Direction.EAST, BlockHalf.TOP, StairShape.OUTER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, outerOrientedMirroredModel).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R270))
						.register(Direction.Axis.Z, Direction.EAST, BlockHalf.TOP, StairShape.OUTER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, outerOrientedModel).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R90))
						.register(Direction.Axis.Z, Direction.EAST, BlockHalf.TOP, StairShape.STRAIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, regularSidewaysModel).put(VariantSettings.X, VariantSettings.Rotation.R180))
						//north z
						.register(Direction.Axis.Z, Direction.NORTH, BlockHalf.BOTTOM, StairShape.INNER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, innerMirroredModel).put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R180))
						.register(Direction.Axis.Z, Direction.NORTH, BlockHalf.BOTTOM, StairShape.INNER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModel).put(VariantSettings.X, VariantSettings.Rotation.R270))
						.register(Direction.Axis.Z, Direction.NORTH, BlockHalf.BOTTOM, StairShape.OUTER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, outerOrientedMirroredModel).put(VariantSettings.Y, VariantSettings.Rotation.R270))
						.register(Direction.Axis.Z, Direction.NORTH, BlockHalf.BOTTOM, StairShape.OUTER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, outerOrientedModel).put(VariantSettings.Y, VariantSettings.Rotation.R270))
						.register(Direction.Axis.Z, Direction.NORTH, BlockHalf.BOTTOM, StairShape.STRAIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, regularFrontModel).put(VariantSettings.Y, VariantSettings.Rotation.R270))
						.register(Direction.Axis.Z, Direction.NORTH, BlockHalf.TOP, StairShape.INNER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModel).put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R180))
						.register(Direction.Axis.Z, Direction.NORTH, BlockHalf.TOP, StairShape.INNER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, innerMirroredModel).put(VariantSettings.X, VariantSettings.Rotation.R270))
						.register(Direction.Axis.Z, Direction.NORTH, BlockHalf.TOP, StairShape.OUTER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, outerOrientedModel).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R270))
						.register(Direction.Axis.Z, Direction.NORTH, BlockHalf.TOP, StairShape.OUTER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, outerOrientedMirroredModel).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R270))
						.register(Direction.Axis.Z, Direction.NORTH, BlockHalf.TOP, StairShape.STRAIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, regularFrontModel).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R270))
						//south z
						.register(Direction.Axis.Z, Direction.SOUTH, BlockHalf.BOTTOM, StairShape.INNER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, innerMirroredModel).put(VariantSettings.X, VariantSettings.Rotation.R90))
						.register(Direction.Axis.Z, Direction.SOUTH, BlockHalf.BOTTOM, StairShape.INNER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModel).put(VariantSettings.X, VariantSettings.Rotation.R270).put(VariantSettings.Y, VariantSettings.Rotation.R180))
						.register(Direction.Axis.Z, Direction.SOUTH, BlockHalf.BOTTOM, StairShape.OUTER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, outerOrientedMirroredModel).put(VariantSettings.Y, VariantSettings.Rotation.R90))
						.register(Direction.Axis.Z, Direction.SOUTH, BlockHalf.BOTTOM, StairShape.OUTER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, outerOrientedModel).put(VariantSettings.Y, VariantSettings.Rotation.R90))
						.register(Direction.Axis.Z, Direction.SOUTH, BlockHalf.BOTTOM, StairShape.STRAIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, regularFrontModel).put(VariantSettings.Y, VariantSettings.Rotation.R90))
						.register(Direction.Axis.Z, Direction.SOUTH, BlockHalf.TOP, StairShape.INNER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModel).put(VariantSettings.X, VariantSettings.Rotation.R90))
						.register(Direction.Axis.Z, Direction.SOUTH, BlockHalf.TOP, StairShape.INNER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, innerMirroredModel).put(VariantSettings.X, VariantSettings.Rotation.R270).put(VariantSettings.Y, VariantSettings.Rotation.R180))
						.register(Direction.Axis.Z, Direction.SOUTH, BlockHalf.TOP, StairShape.OUTER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, outerOrientedModel).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R90))
						.register(Direction.Axis.Z, Direction.SOUTH, BlockHalf.TOP, StairShape.OUTER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, outerOrientedMirroredModel).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R90))
						.register(Direction.Axis.Z, Direction.SOUTH, BlockHalf.TOP, StairShape.STRAIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, regularFrontModel).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R90))
						//west z
						.register(Direction.Axis.Z, Direction.WEST, BlockHalf.BOTTOM, StairShape.INNER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModel).put(VariantSettings.X, VariantSettings.Rotation.R270).put(VariantSettings.Y, VariantSettings.Rotation.R180))
						.register(Direction.Axis.Z, Direction.WEST, BlockHalf.BOTTOM, StairShape.INNER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, innerMirroredModel).put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R180))
						.register(Direction.Axis.Z, Direction.WEST, BlockHalf.BOTTOM, StairShape.OUTER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, outerOrientedModel).put(VariantSettings.Y, VariantSettings.Rotation.R90))
						.register(Direction.Axis.Z, Direction.WEST, BlockHalf.BOTTOM, StairShape.OUTER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, outerOrientedMirroredModel).put(VariantSettings.Y, VariantSettings.Rotation.R270))
						.register(Direction.Axis.Z, Direction.WEST, BlockHalf.BOTTOM, StairShape.STRAIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, regularSidewaysModel).put(VariantSettings.Y, VariantSettings.Rotation.R180))
						.register(Direction.Axis.Z, Direction.WEST, BlockHalf.TOP, StairShape.INNER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, innerMirroredModel).put(VariantSettings.X, VariantSettings.Rotation.R270).put(VariantSettings.Y, VariantSettings.Rotation.R180))
						.register(Direction.Axis.Z, Direction.WEST, BlockHalf.TOP, StairShape.INNER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, innerModel).put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R180))
						.register(Direction.Axis.Z, Direction.WEST, BlockHalf.TOP, StairShape.OUTER_LEFT, BlockStateVariant.create().put(VariantSettings.MODEL, outerOrientedMirroredModel).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R90))
						.register(Direction.Axis.Z, Direction.WEST, BlockHalf.TOP, StairShape.OUTER_RIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, outerOrientedModel).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R270))
						.register(Direction.Axis.Z, Direction.WEST, BlockHalf.TOP, StairShape.STRAIGHT, BlockStateVariant.create().put(VariantSettings.MODEL, regularSidewaysModel).put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R180)));
	}
	
	public static BlockStateSupplier createBoolPropertyWallBlockState(Block wall, BooleanProperty property, Identifier postFalseModel, Identifier postTrueModel, Identifier sideFalseModel, Identifier sideTrueModel, Identifier sideTallFalseModel, Identifier sideTallTrueModel) {
		return MultipartBlockStateSupplier.create(wall)
				.with(When.create().set(Properties.UP, true).set(property, false), BlockStateVariant.create().put(VariantSettings.MODEL, postFalseModel))
				.with(When.create().set(Properties.UP, true).set(property, true), BlockStateVariant.create().put(VariantSettings.MODEL, postTrueModel))
				.with(When.create().set(Properties.NORTH_WALL_SHAPE, WallShape.LOW).set(property, false), BlockStateVariant.create().put(VariantSettings.MODEL, sideFalseModel))
				.with(When.create().set(Properties.NORTH_WALL_SHAPE, WallShape.LOW).set(property, true), BlockStateVariant.create().put(VariantSettings.MODEL, sideTrueModel))
				.with(When.create().set(Properties.EAST_WALL_SHAPE, WallShape.LOW).set(property, false), BlockStateVariant.create().put(VariantSettings.MODEL, sideFalseModel).put(VariantSettings.Y, VariantSettings.Rotation.R90))
				.with(When.create().set(Properties.EAST_WALL_SHAPE, WallShape.LOW).set(property, true), BlockStateVariant.create().put(VariantSettings.MODEL, sideTrueModel).put(VariantSettings.Y, VariantSettings.Rotation.R90))
				.with(When.create().set(Properties.SOUTH_WALL_SHAPE, WallShape.LOW).set(property, false), BlockStateVariant.create().put(VariantSettings.MODEL, sideFalseModel).put(VariantSettings.Y, VariantSettings.Rotation.R180))
				.with(When.create().set(Properties.SOUTH_WALL_SHAPE, WallShape.LOW).set(property, true), BlockStateVariant.create().put(VariantSettings.MODEL, sideTrueModel).put(VariantSettings.Y, VariantSettings.Rotation.R180))
				.with(When.create().set(Properties.WEST_WALL_SHAPE, WallShape.LOW).set(property, false), BlockStateVariant.create().put(VariantSettings.MODEL, sideFalseModel).put(VariantSettings.Y, VariantSettings.Rotation.R270))
				.with(When.create().set(Properties.WEST_WALL_SHAPE, WallShape.LOW).set(property, true), BlockStateVariant.create().put(VariantSettings.MODEL, sideTrueModel).put(VariantSettings.Y, VariantSettings.Rotation.R270))
				.with(When.create().set(Properties.NORTH_WALL_SHAPE, WallShape.TALL).set(property, false), BlockStateVariant.create().put(VariantSettings.MODEL, sideTallFalseModel))
				.with(When.create().set(Properties.NORTH_WALL_SHAPE, WallShape.TALL).set(property, true), BlockStateVariant.create().put(VariantSettings.MODEL, sideTallTrueModel))
				.with(When.create().set(Properties.EAST_WALL_SHAPE, WallShape.TALL).set(property, false), BlockStateVariant.create().put(VariantSettings.MODEL, sideTallFalseModel).put(VariantSettings.Y, VariantSettings.Rotation.R90))
				.with(When.create().set(Properties.EAST_WALL_SHAPE, WallShape.TALL).set(property, true), BlockStateVariant.create().put(VariantSettings.MODEL, sideTallTrueModel).put(VariantSettings.Y, VariantSettings.Rotation.R90))
				.with(When.create().set(Properties.SOUTH_WALL_SHAPE, WallShape.TALL).set(property, false), BlockStateVariant.create().put(VariantSettings.MODEL, sideTallFalseModel).put(VariantSettings.Y, VariantSettings.Rotation.R180))
				.with(When.create().set(Properties.SOUTH_WALL_SHAPE, WallShape.TALL).set(property, true), BlockStateVariant.create().put(VariantSettings.MODEL, sideTallTrueModel).put(VariantSettings.Y, VariantSettings.Rotation.R180))
				.with(When.create().set(Properties.WEST_WALL_SHAPE, WallShape.TALL).set(property, false), BlockStateVariant.create().put(VariantSettings.MODEL, sideTallFalseModel).put(VariantSettings.Y, VariantSettings.Rotation.R270))
				.with(When.create().set(Properties.WEST_WALL_SHAPE, WallShape.TALL).set(property, true), BlockStateVariant.create().put(VariantSettings.MODEL, sideTallTrueModel).put(VariantSettings.Y, VariantSettings.Rotation.R270));
	}
	
	public static BlockStateSupplier createWallBlockState(Block wall, Identifier postModel, Identifier nopostModel, Identifier nopostTallModel, Identifier nosideModel, Identifier nosideNopostModel, Identifier nosideNopostTallModel, Identifier sideModel, Identifier sideNopostModel, Identifier sideTallModel, Identifier sideTallNopostModel) {
		return MultipartBlockStateSupplier.create(wall)
		/*post:*/
		.with(When.create().set(WallBlock.UP, true), BlockStateVariant.create().put(VariantSettings.MODEL, postModel).put(VariantSettings.UVLOCK, true))
		.with(When.allOf(When.create().set(WallBlock.UP, false), When.anyOf(When.create().set(WallBlock.NORTH_SHAPE, WallShape.TALL), When.create().set(WallBlock.EAST_SHAPE, WallShape.TALL), When.create().set(WallBlock.SOUTH_SHAPE, WallShape.TALL), When.create().set(WallBlock.WEST_SHAPE, WallShape.TALL))), BlockStateVariant.create().put(VariantSettings.MODEL, nopostTallModel).put(VariantSettings.UVLOCK, true))
		.with(When.allOf(When.create().set(WallBlock.UP, false), When.allOf(When.create().setNegated(WallBlock.NORTH_SHAPE, WallShape.TALL), When.create().setNegated(WallBlock.EAST_SHAPE, WallShape.TALL), When.create().setNegated(WallBlock.SOUTH_SHAPE, WallShape.TALL), When.create().setNegated(WallBlock.WEST_SHAPE, WallShape.TALL))), BlockStateVariant.create().put(VariantSettings.MODEL, nopostModel).put(VariantSettings.UVLOCK, true))
		/*north*/
		.with(When.allOf(When.create().set(WallBlock.UP, true), When.create().set(WallBlock.NORTH_SHAPE, WallShape.NONE)), BlockStateVariant.create().put(VariantSettings.MODEL, nosideModel).put(VariantSettings.Y, VariantSettings.Rotation.R0).put(VariantSettings.UVLOCK, true))
		.with(When.allOf(When.create().set(WallBlock.UP, false), When.create().set(WallBlock.NORTH_SHAPE, WallShape.NONE), When.anyOf(When.create().set(WallBlock.EAST_SHAPE, WallShape.TALL), When.create().set(WallBlock.SOUTH_SHAPE, WallShape.TALL), When.create().set(WallBlock.WEST_SHAPE, WallShape.TALL))), BlockStateVariant.create().put(VariantSettings.MODEL, nosideNopostTallModel).put(VariantSettings.Y, VariantSettings.Rotation.R0).put(VariantSettings.UVLOCK, true))
		.with(When.allOf(When.create().set(WallBlock.UP, false), When.create().set(WallBlock.NORTH_SHAPE, WallShape.NONE), When.allOf(When.create().setNegated(WallBlock.EAST_SHAPE, WallShape.TALL), When.create().setNegated(WallBlock.SOUTH_SHAPE, WallShape.TALL), When.create().setNegated(WallBlock.WEST_SHAPE, WallShape.TALL))), BlockStateVariant.create().put(VariantSettings.MODEL, nosideNopostModel).put(VariantSettings.Y, VariantSettings.Rotation.R0).put(VariantSettings.UVLOCK, true))
		.with(When.allOf(When.create().set(WallBlock.UP, true), When.create().set(WallBlock.NORTH_SHAPE, WallShape.LOW)), BlockStateVariant.create().put(VariantSettings.MODEL, sideModel).put(VariantSettings.Y, VariantSettings.Rotation.R0).put(VariantSettings.UVLOCK, true))
		.with(When.allOf(When.create().set(WallBlock.UP, false), When.create().set(WallBlock.NORTH_SHAPE, WallShape.LOW)), BlockStateVariant.create().put(VariantSettings.MODEL, sideNopostModel).put(VariantSettings.Y, VariantSettings.Rotation.R0).put(VariantSettings.UVLOCK, true))
		.with(When.allOf(When.create().set(WallBlock.UP, true), When.create().set(WallBlock.NORTH_SHAPE, WallShape.TALL)), BlockStateVariant.create().put(VariantSettings.MODEL, sideTallModel).put(VariantSettings.Y, VariantSettings.Rotation.R0).put(VariantSettings.UVLOCK, true))
		.with(When.allOf(When.create().set(WallBlock.UP, false), When.create().set(WallBlock.NORTH_SHAPE, WallShape.TALL)), BlockStateVariant.create().put(VariantSettings.MODEL, sideTallNopostModel).put(VariantSettings.Y, VariantSettings.Rotation.R0).put(VariantSettings.UVLOCK, true))
		/*east:*/
		.with(When.allOf(When.create().set(WallBlock.UP, true), When.create().set(WallBlock.EAST_SHAPE, WallShape.NONE)), BlockStateVariant.create().put(VariantSettings.MODEL, nosideModel).put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, true))
		.with(When.allOf(When.create().set(WallBlock.UP, false), When.create().set(WallBlock.EAST_SHAPE, WallShape.NONE), When.anyOf(When.create().set(WallBlock.NORTH_SHAPE, WallShape.TALL), When.create().set(WallBlock.SOUTH_SHAPE, WallShape.TALL), When.create().set(WallBlock.WEST_SHAPE, WallShape.TALL))), BlockStateVariant.create().put(VariantSettings.MODEL, nosideNopostTallModel).put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, true))
		.with(When.allOf(When.create().set(WallBlock.UP, false), When.create().set(WallBlock.EAST_SHAPE, WallShape.NONE), When.allOf(When.create().setNegated(WallBlock.NORTH_SHAPE, WallShape.TALL), When.create().setNegated(WallBlock.SOUTH_SHAPE, WallShape.TALL), When.create().setNegated(WallBlock.WEST_SHAPE, WallShape.TALL))), BlockStateVariant.create().put(VariantSettings.MODEL, nosideNopostModel).put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, true))
		.with(When.allOf(When.create().set(WallBlock.UP, true), When.create().set(WallBlock.EAST_SHAPE, WallShape.LOW)), BlockStateVariant.create().put(VariantSettings.MODEL, sideModel).put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, true))
		.with(When.allOf(When.create().set(WallBlock.UP, false), When.create().set(WallBlock.EAST_SHAPE, WallShape.LOW)), BlockStateVariant.create().put(VariantSettings.MODEL, sideNopostModel).put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, true))
		.with(When.allOf(When.create().set(WallBlock.UP, true), When.create().set(WallBlock.EAST_SHAPE, WallShape.TALL)), BlockStateVariant.create().put(VariantSettings.MODEL, sideTallModel).put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, true))
		.with(When.allOf(When.create().set(WallBlock.UP, false), When.create().set(WallBlock.EAST_SHAPE, WallShape.TALL)), BlockStateVariant.create().put(VariantSettings.MODEL, sideTallNopostModel).put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, true))
		/*south:*/
		.with(When.allOf(When.create().set(WallBlock.UP, true), When.create().set(WallBlock.SOUTH_SHAPE, WallShape.NONE)), BlockStateVariant.create().put(VariantSettings.MODEL, nosideModel).put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, true))
		.with(When.allOf(When.create().set(WallBlock.UP, false), When.create().set(WallBlock.SOUTH_SHAPE, WallShape.NONE), When.anyOf(When.create().set(WallBlock.NORTH_SHAPE, WallShape.TALL), When.create().set(WallBlock.EAST_SHAPE, WallShape.TALL), When.create().set(WallBlock.WEST_SHAPE, WallShape.TALL))), BlockStateVariant.create().put(VariantSettings.MODEL, nosideNopostTallModel).put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, true))
		.with(When.allOf(When.create().set(WallBlock.UP, false), When.create().set(WallBlock.SOUTH_SHAPE, WallShape.NONE), When.allOf(When.create().setNegated(WallBlock.NORTH_SHAPE, WallShape.TALL), When.create().setNegated(WallBlock.EAST_SHAPE, WallShape.TALL), When.create().setNegated(WallBlock.WEST_SHAPE, WallShape.TALL))), BlockStateVariant.create().put(VariantSettings.MODEL, nosideNopostModel).put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, true))
		.with(When.allOf(When.create().set(WallBlock.UP, true), When.create().set(WallBlock.SOUTH_SHAPE, WallShape.LOW)), BlockStateVariant.create().put(VariantSettings.MODEL, sideModel).put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, true))
		.with(When.allOf(When.create().set(WallBlock.UP, false), When.create().set(WallBlock.SOUTH_SHAPE, WallShape.LOW)), BlockStateVariant.create().put(VariantSettings.MODEL, sideNopostModel).put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, true))
		.with(When.allOf(When.create().set(WallBlock.UP, true), When.create().set(WallBlock.SOUTH_SHAPE, WallShape.TALL)), BlockStateVariant.create().put(VariantSettings.MODEL, sideTallModel).put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, true))
		.with(When.allOf(When.create().set(WallBlock.UP, false), When.create().set(WallBlock.SOUTH_SHAPE, WallShape.TALL)), BlockStateVariant.create().put(VariantSettings.MODEL, sideTallNopostModel).put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, true))
		/*west:*/
		.with(When.allOf(When.create().set(WallBlock.UP, true), When.create().set(WallBlock.WEST_SHAPE, WallShape.NONE)), BlockStateVariant.create().put(VariantSettings.MODEL, nosideModel).put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, true))
		.with(When.allOf(When.create().set(WallBlock.UP, false), When.create().set(WallBlock.WEST_SHAPE, WallShape.NONE), When.anyOf(When.create().set(WallBlock.NORTH_SHAPE, WallShape.TALL), When.create().set(WallBlock.EAST_SHAPE, WallShape.TALL), When.create().set(WallBlock.SOUTH_SHAPE, WallShape.TALL))), BlockStateVariant.create().put(VariantSettings.MODEL, nosideNopostTallModel).put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, true))
		.with(When.allOf(When.create().set(WallBlock.UP, false), When.create().set(WallBlock.WEST_SHAPE, WallShape.NONE), When.allOf(When.create().setNegated(WallBlock.NORTH_SHAPE, WallShape.TALL), When.create().setNegated(WallBlock.EAST_SHAPE, WallShape.TALL), When.create().setNegated(WallBlock.SOUTH_SHAPE, WallShape.TALL))), BlockStateVariant.create().put(VariantSettings.MODEL, nosideNopostModel).put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, true))
		.with(When.allOf(When.create().set(WallBlock.UP, true), When.create().set(WallBlock.WEST_SHAPE, WallShape.LOW)), BlockStateVariant.create().put(VariantSettings.MODEL, sideModel).put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, true))
		.with(When.allOf(When.create().set(WallBlock.UP, false), When.create().set(WallBlock.WEST_SHAPE, WallShape.LOW)), BlockStateVariant.create().put(VariantSettings.MODEL, sideNopostModel).put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, true))
		.with(When.allOf(When.create().set(WallBlock.UP, true), When.create().set(WallBlock.WEST_SHAPE, WallShape.TALL)), BlockStateVariant.create().put(VariantSettings.MODEL, sideTallModel).put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, true))
		.with(When.allOf(When.create().set(WallBlock.UP, false), When.create().set(WallBlock.WEST_SHAPE, WallShape.TALL)), BlockStateVariant.create().put(VariantSettings.MODEL, sideTallNopostModel).put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, true));
	}
	
	public static BlockStateSupplier createBarsBlockState(Block bars, Identifier postModel, Identifier postEndsModel, Identifier sideModel, Identifier sideAltModel, Identifier capModel, Identifier capAltModel) {
		return MultipartBlockStateSupplier.create(bars)
				.with(BlockStateVariant.create().put(VariantSettings.MODEL, postEndsModel))
				.with(When.allOf(When.create().set(PaneBlock.EAST, false), When.create().set(PaneBlock.NORTH, false), When.create().set(PaneBlock.SOUTH, false), When.create().set(PaneBlock.WEST, false)), BlockStateVariant.create().put(VariantSettings.MODEL, postModel))
				.with(When.allOf(When.create().set(PaneBlock.EAST, false), When.create().set(PaneBlock.NORTH, true), When.create().set(PaneBlock.SOUTH, false), When.create().set(PaneBlock.WEST, false)), BlockStateVariant.create().put(VariantSettings.MODEL, capModel))
				.with(When.allOf(When.create().set(PaneBlock.EAST, true), When.create().set(PaneBlock.NORTH, false), When.create().set(PaneBlock.SOUTH, false), When.create().set(PaneBlock.WEST, false)), BlockStateVariant.create().put(VariantSettings.MODEL, capModel).put(VariantSettings.Y, VariantSettings.Rotation.R90))
				.with(When.allOf(When.create().set(PaneBlock.EAST, false), When.create().set(PaneBlock.NORTH, false), When.create().set(PaneBlock.SOUTH, true), When.create().set(PaneBlock.WEST, false)), BlockStateVariant.create().put(VariantSettings.MODEL, capAltModel))
				.with(When.allOf(When.create().set(PaneBlock.EAST, false), When.create().set(PaneBlock.NORTH, false), When.create().set(PaneBlock.SOUTH, false), When.create().set(PaneBlock.WEST, true)), BlockStateVariant.create().put(VariantSettings.MODEL, capAltModel).put(VariantSettings.Y, VariantSettings.Rotation.R90))
				.with(When.create().set(PaneBlock.NORTH, true), BlockStateVariant.create().put(VariantSettings.MODEL, sideModel))
				.with(When.create().set(PaneBlock.EAST, true), BlockStateVariant.create().put(VariantSettings.MODEL, sideModel).put(VariantSettings.Y, VariantSettings.Rotation.R90))
				.with(When.create().set(PaneBlock.SOUTH, true), BlockStateVariant.create().put(VariantSettings.MODEL, sideAltModel))
				.with(When.create().set(PaneBlock.WEST, true), BlockStateVariant.create().put(VariantSettings.MODEL, sideAltModel).put(VariantSettings.Y, VariantSettings.Rotation.R90));
	}
	
	public static BlockStateSupplier createLeveledCauldronBlockState(Block cauldron, Identifier model1, Identifier model2, Identifier model3) {
		return VariantsBlockStateSupplier.create(cauldron)
				.coordinate(BlockStateVariantMap.create(LeveledCauldronBlock.LEVEL)
						.register(1, BlockStateVariant.create().put(VariantSettings.MODEL, model1))
						.register(2, BlockStateVariant.create().put(VariantSettings.MODEL, model2))
						.register(3, BlockStateVariant.create().put(VariantSettings.MODEL, model3)));
	}
	
	public static BlockStateSupplier createComposterBlockState(Block composter, Identifier baseModel) {
		Identifier[] models = {ModelIds.getBlockSubModelId(Blocks.COMPOSTER, "_contents1"),
				ModelIds.getBlockSubModelId(Blocks.COMPOSTER, "_contents2"),
				ModelIds.getBlockSubModelId(Blocks.COMPOSTER, "_contents3"),
				ModelIds.getBlockSubModelId(Blocks.COMPOSTER, "_contents4"),
				ModelIds.getBlockSubModelId(Blocks.COMPOSTER, "_contents5"),
				ModelIds.getBlockSubModelId(Blocks.COMPOSTER, "_contents6"),
				ModelIds.getBlockSubModelId(Blocks.COMPOSTER, "_contents7"),
				ModelIds.getBlockSubModelId(Blocks.COMPOSTER, "_contents_ready")};
		
		MultipartBlockStateSupplier supplier =  MultipartBlockStateSupplier.create(composter)
				.with(BlockStateVariant.create().put(VariantSettings.MODEL, baseModel));
		
		for (int i = 1; i < 9; i++) {
			supplier = supplier.with(When.create().set(Properties.LEVEL_8, i), BlockStateVariant.create().put(VariantSettings.MODEL, models[i-1]));
		}
		
		return supplier;
	}
	
	public static BlockStateSupplier createGrindstoneBlockState(Block grindstone, Identifier model) {
		return VariantsBlockStateSupplier.create(grindstone, BlockStateVariant.create().put(VariantSettings.MODEL, model))
		.coordinate(BlockStateVariantMap.create(Properties.BLOCK_FACE, Properties.HORIZONTAL_FACING)
				.register(BlockFace.FLOOR, Direction.NORTH, BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R0))
				.register(BlockFace.FLOOR, Direction.EAST, BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R90))
				.register(BlockFace.FLOOR, Direction.SOUTH, BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R180))
				.register(BlockFace.FLOOR, Direction.WEST, BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R270))
				.register(BlockFace.WALL, Direction.NORTH, BlockStateVariant.create().put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R0))
				.register(BlockFace.WALL, Direction.EAST, BlockStateVariant.create().put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R90))
				.register(BlockFace.WALL, Direction.SOUTH, BlockStateVariant.create().put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R180))
				.register(BlockFace.WALL, Direction.WEST, BlockStateVariant.create().put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R270))
				.register(BlockFace.CEILING, Direction.SOUTH, BlockStateVariant.create().put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R0))
				.register(BlockFace.CEILING, Direction.WEST, BlockStateVariant.create().put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R90))
				.register(BlockFace.CEILING, Direction.NORTH, BlockStateVariant.create().put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R180))
				.register(BlockFace.CEILING, Direction.EAST, BlockStateVariant.create().put(VariantSettings.X, VariantSettings.Rotation.R180).put(VariantSettings.Y, VariantSettings.Rotation.R270)));
	}
	
	public static BlockStateSupplier createTripwireHookBlockState(Block tripwireHook, Identifier model, Identifier onModel, Identifier attachedModel, Identifier attachedOnModel) {
		return VariantsBlockStateSupplier.create(tripwireHook)
				.coordinate(BlockStateVariantMap.create(Properties.ATTACHED, Properties.POWERED)
						.register((attached, powered) -> BlockStateVariant.create()
								.put(VariantSettings.MODEL, switch (BooleanPair.of(attached, powered) ) {
									case FALSEFALSE -> model;
									case FALSETRUE -> onModel;
									case TRUEFALSE -> attachedModel;
									case TRUETRUE -> attachedOnModel;
								})))
				.coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates());
	}
	
	public static BlockStateSupplier createChiseledBookshelfBlockState(Block chiseledBookshelf, Identifier model, Identifier empty1Model, Identifier empty2Model, Identifier empty3Model, Identifier empty4Model, Identifier empty5Model, Identifier empty6Model, Identifier occupied1Model, Identifier occupied2Model, Identifier occupied3Model, Identifier occupied4Model, Identifier occupied5Model, Identifier occupied6Model) {
		MultipartBlockStateSupplier blockStateSupplier = MultipartBlockStateSupplier.create(chiseledBookshelf);
		Identifier[] emptyModels = {
			empty1Model,
			empty2Model,
			empty3Model,
			empty4Model,
			empty5Model,
			empty6Model
		};
		Identifier[] occupiedModels = {
			occupied1Model,
			occupied2Model,
			occupied3Model,
			occupied4Model,
			occupied5Model,
			occupied6Model
		};
		List.of(
				Pair.of(Direction.NORTH, VariantSettings.Rotation.R0),
				Pair.of(Direction.EAST, VariantSettings.Rotation.R90),
				Pair.of(Direction.SOUTH, VariantSettings.Rotation.R180),
				Pair.of(Direction.WEST, VariantSettings.Rotation.R270)
			)
			.forEach(
				pair -> {
					Direction dir = pair.getFirst();
					VariantSettings.Rotation rot = pair.getSecond();
					When.PropertyCondition propertyCondition = When.create().set(Properties.HORIZONTAL_FACING, dir);
					blockStateSupplier.with(propertyCondition, BlockStateVariant.create().put(VariantSettings.MODEL, model).put(VariantSettings.Y, rot).put(VariantSettings.UVLOCK, true));
					supplyChiseledBookshelfModels(blockStateSupplier, propertyCondition, rot, emptyModels, occupiedModels);
				}
			);
		
		return blockStateSupplier;
	}
	
	private static void supplyChiseledBookshelfModels(MultipartBlockStateSupplier blockStateSupplier, When.PropertyCondition propertyCondition, VariantSettings.Rotation rotation, Identifier[] emptyModels, Identifier[] occupiedModels) {
		List.of(
				Pair.of(Properties.SLOT_0_OCCUPIED, Pair.of(emptyModels[0], occupiedModels[0])),
				Pair.of(Properties.SLOT_1_OCCUPIED, Pair.of(emptyModels[1], occupiedModels[1])),
				Pair.of(Properties.SLOT_2_OCCUPIED, Pair.of(emptyModels[2], occupiedModels[2])),
				Pair.of(Properties.SLOT_3_OCCUPIED, Pair.of(emptyModels[3], occupiedModels[3])),
				Pair.of(Properties.SLOT_4_OCCUPIED, Pair.of(emptyModels[4], occupiedModels[4])),
				Pair.of(Properties.SLOT_5_OCCUPIED, Pair.of(emptyModels[5], occupiedModels[5]))
			)
			.forEach(
				pair -> {
					BooleanProperty property = pair.getFirst();
					var models = pair.getSecond();
					supplyChiseledBookshelfModel(blockStateSupplier, propertyCondition, rotation, property, models.getFirst(), false);
					supplyChiseledBookshelfModel(blockStateSupplier, propertyCondition, rotation, property, models.getSecond(), true);
				}
			);
	}
	
	private static void supplyChiseledBookshelfModel(MultipartBlockStateSupplier supplier, When.PropertyCondition facingCondition, VariantSettings.Rotation rotation, BooleanProperty property, Identifier model, boolean occupied) {
		supplier.with(When.allOf(facingCondition, When.create().set(property, occupied)), BlockStateVariant.create().put(VariantSettings.MODEL, model).put(VariantSettings.Y, rotation));
	}
}