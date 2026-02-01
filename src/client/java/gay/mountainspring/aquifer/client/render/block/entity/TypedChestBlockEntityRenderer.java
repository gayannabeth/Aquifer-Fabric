package gay.mountainspring.aquifer.client.render.block.entity;

import java.util.Calendar;
import java.util.Map;

import com.google.common.collect.Maps;

import gay.mountainspring.aquifer.block.WoodTyped;
import gay.mountainspring.aquifer.util.BlockUtil;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.block.AbstractChestBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.DoubleBlockProperties.PropertyRetriever;
import net.minecraft.block.DoubleBlockProperties.PropertySource;
import net.minecraft.block.WoodType;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.entity.LidOpenable;
import net.minecraft.block.enums.ChestType;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.block.entity.LightmapCoordinatesRetriever;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.World;

public class TypedChestBlockEntityRenderer<T extends BlockEntity & LidOpenable> implements BlockEntityRenderer<T> {
	private static final Map<WoodType, SpriteIdentifier> SPRITE_MAP = createSpriteMap(false, ChestType.SINGLE);
	private static final Map<WoodType, SpriteIdentifier> LEFT_SPRITE_MAP = createSpriteMap(false, ChestType.LEFT);
	private static final Map<WoodType, SpriteIdentifier> RIGHT_SPRITE_MAP = createSpriteMap(false, ChestType.RIGHT);
	private static final Map<WoodType, SpriteIdentifier> TRAPPED_SPRITE_MAP = createSpriteMap(true, ChestType.SINGLE);
	private static final Map<WoodType, SpriteIdentifier> LEFT_TRAPPED_SPRITE_MAP = createSpriteMap(true, ChestType.LEFT);
	private static final Map<WoodType, SpriteIdentifier> RIGHT_TRAPPED_SPRITE_MAP = createSpriteMap(true, ChestType.RIGHT);
	
	private final ModelPart singleChestLid;
	private final ModelPart singleChestBase;
	private final ModelPart singleChestLatch;
	private final ModelPart doubleChestLeftLid;
	private final ModelPart doubleChestLeftBase;
	private final ModelPart doubleChestLeftLatch;
	private final ModelPart doubleChestRightLid;
	private final ModelPart doubleChestRightBase;
	private final ModelPart doubleChestRightLatch;
	private boolean christmas = false;
	
	public TypedChestBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
		Calendar calendar = Calendar.getInstance();
		if (calendar.get(2) + 1 == 12 && calendar.get(5) >= 24 && calendar.get(5) <= 26) {
			this.christmas = true;
		}
		
		ModelPart modelPart = ctx.getLayerModelPart(EntityModelLayers.CHEST);
		this.singleChestBase = modelPart.getChild("bottom");
		this.singleChestLid = modelPart.getChild("lid");
		this.singleChestLatch = modelPart.getChild("lock");
		ModelPart modelPart2 = ctx.getLayerModelPart(EntityModelLayers.DOUBLE_CHEST_LEFT);
		this.doubleChestLeftBase = modelPart2.getChild("bottom");
		this.doubleChestLeftLid = modelPart2.getChild("lid");
		this.doubleChestLeftLatch = modelPart2.getChild("lock");
		ModelPart modelPart3 = ctx.getLayerModelPart(EntityModelLayers.DOUBLE_CHEST_RIGHT);
		this.doubleChestRightBase = modelPart3.getChild("bottom");
		this.doubleChestRightLid = modelPart3.getChild("lid");
		this.doubleChestRightLatch = modelPart3.getChild("lock");
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void render(T entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		World world = entity.getWorld();
		boolean worldNonNull = world != null;
		BlockState state = entity.getCachedState();
		ChestType chestType = state.contains(Properties.CHEST_TYPE) ? state.get(Properties.CHEST_TYPE) : ChestType.SINGLE;
		WoodType woodType = null;
		if (state.getBlock() instanceof WoodTyped woodTyped) {
			woodType = woodTyped.aquifer$getWoodType();
		}
		if (state.getBlock() instanceof AbstractChestBlock chestBlock) {
			matrices.push();
			boolean isTrapped = chestBlock.aquifer$isTrapped();
			float yRot = state.get(Properties.HORIZONTAL_FACING).asRotation();
			matrices.translate(0.5D, 0.5D, 0.5D);
			matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-yRot));
			matrices.translate(-0.5D, -0.5D, -0.5D);
			PropertySource<? extends ChestBlockEntity> propertySource = worldNonNull ? chestBlock.getBlockEntitySource(state, world, entity.getPos(), true) : PropertyRetriever::getFallback;
			float lidRot = propertySource.apply(ChestBlock.getAnimationProgressRetriever(entity)).get(tickDelta);
			lidRot = 1.0f - lidRot;
			lidRot = 1.0f - lidRot * lidRot * lidRot;
			int appliedLight = ((Int2IntFunction) propertySource.apply(new LightmapCoordinatesRetriever())).applyAsInt(light);
			SpriteIdentifier spriteID = getSpriteID(woodType, this.christmas, isTrapped, chestType);
			VertexConsumer vertexConsumer = spriteID.getVertexConsumer(vertexConsumers, RenderLayer::getEntityCutout);
			switch (chestType) {
				case LEFT: this.render(matrices, vertexConsumer, doubleChestLeftLid, doubleChestLeftLatch, doubleChestLeftBase, lidRot, appliedLight, overlay); break;
				case RIGHT: this.render(matrices, vertexConsumer, doubleChestRightLid, doubleChestRightLatch, doubleChestRightBase, lidRot, appliedLight, overlay); break;
				case SINGLE:
				default: this.render(matrices, vertexConsumer, singleChestLid, singleChestLatch, singleChestBase, lidRot, appliedLight, overlay); break;
			}
			matrices.pop();
		}
	}
	
	private void render(MatrixStack matrices, VertexConsumer vertices, ModelPart lid, ModelPart latch, ModelPart base, float openFactor, int light, int overlay) {
		lid.pitch = -(openFactor * (float) (Math.PI / 2));
		latch.pitch = lid.pitch;
		lid.render(matrices, vertices, light, overlay);
		latch.render(matrices, vertices, light, overlay);
		base.render(matrices, vertices, light, overlay);
	}
	
	private static SpriteIdentifier getSpriteID(WoodType woodType, boolean christmas, boolean isTrapped, ChestType chestType) {
		if (christmas) {
			return switch(chestType) {
				case SINGLE -> TexturedRenderLayers.CHRISTMAS;
				case LEFT -> TexturedRenderLayers.CHRISTMAS_LEFT;
				case RIGHT -> TexturedRenderLayers.CHRISTMAS_RIGHT;
			};
		} else {
			if (woodType == null) {
				return switch (chestType) {
					case SINGLE -> isTrapped ? TexturedRenderLayers.TRAPPED : TexturedRenderLayers.NORMAL;
					case LEFT -> isTrapped ? TexturedRenderLayers.TRAPPED_LEFT : TexturedRenderLayers.NORMAL_LEFT;
					case RIGHT -> isTrapped ? TexturedRenderLayers.TRAPPED_RIGHT : TexturedRenderLayers.NORMAL_RIGHT;
				};
			}
			
			return (switch (chestType) {
				case SINGLE -> isTrapped ? TRAPPED_SPRITE_MAP : SPRITE_MAP;
				case LEFT -> isTrapped ? LEFT_TRAPPED_SPRITE_MAP : LEFT_SPRITE_MAP;
				case RIGHT -> isTrapped ? RIGHT_TRAPPED_SPRITE_MAP : RIGHT_SPRITE_MAP;
			}).get(woodType);
		}
	}
	
	private static Map<WoodType, SpriteIdentifier> createSpriteMap(boolean isTrapped, ChestType chestType) {
		Map<WoodType, SpriteIdentifier> map = Maps.newHashMap();
		
		WoodType.stream().filter(BlockUtil::hasChestVariant).toList().forEach(type -> map.put(type, createSpriteID(type, isTrapped, chestType)));
		
		return map;
	}
	
	public static void refreshSpriteMaps() {
		WoodType.stream().filter(BlockUtil::hasChestVariant).toList().forEach(type -> {
			SPRITE_MAP.computeIfAbsent(type, woodType -> createSpriteID(woodType, false, ChestType.SINGLE));
			LEFT_SPRITE_MAP.computeIfAbsent(type, woodType -> createSpriteID(woodType, false, ChestType.LEFT));
			RIGHT_SPRITE_MAP.computeIfAbsent(type, woodType -> createSpriteID(woodType, false, ChestType.RIGHT));
			TRAPPED_SPRITE_MAP.computeIfAbsent(type, woodType -> createSpriteID(woodType, true, ChestType.SINGLE));
			LEFT_TRAPPED_SPRITE_MAP.computeIfAbsent(type, woodType -> createSpriteID(woodType, true, ChestType.LEFT));
			RIGHT_TRAPPED_SPRITE_MAP.computeIfAbsent(type, woodType -> createSpriteID(woodType, true, ChestType.RIGHT));
		});
	}
	
	private static SpriteIdentifier createSpriteID(WoodType woodType, boolean isTrapped, ChestType chestType) {
		return new SpriteIdentifier(TexturedRenderLayers.CHEST_ATLAS_TEXTURE, Identifier.of(woodType.name()).withPath(path -> "entity/chest/" + (isTrapped ? "trapped/" : "normal/") + path + (chestType == ChestType.LEFT ? "_left" : chestType == ChestType.RIGHT ? "_right" : "")));
	}
}