package gay.mountainspring.aquifer.client;

import java.util.Map;

import com.google.common.collect.Maps;

import gay.mountainspring.aquifer.Aquifer;
import gay.mountainspring.aquifer.block.entity.AquiferBlockEntityTypes;
import gay.mountainspring.aquifer.block.entity.TypedChestBlockEntity;
import gay.mountainspring.aquifer.block.entity.TypedTrappedChestBlockEntity;
import gay.mountainspring.aquifer.client.render.block.entity.TypedChestBlockEntityRenderer;
import gay.mountainspring.aquifer.config.AquiferConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.block.AbstractChestBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.math.BlockPos;

public class AquiferClient implements ClientModInitializer {
	private static final Map<ItemConvertible, BlockEntity> RENDER_CHEST_TYPED = Maps.newHashMap();
	
	@Override
	public void onInitializeClient() {
		TypedChestBlockEntityRenderer.refreshSpriteMaps();
		BlockEntityRendererFactories.register(AquiferBlockEntityTypes.TYPED_CHEST, TypedChestBlockEntityRenderer::new);
		BlockEntityRendererFactories.register(AquiferBlockEntityTypes.TYPED_TRAPPED_CHEST, TypedChestBlockEntityRenderer::new);
		
		ClientLifecycleEvents.CLIENT_STARTED.register(client -> Aquifer.validateCauldronGroups());
		
		ClientLifecycleEvents.CLIENT_STOPPING.register(client -> AquiferConfig.save());
	}
	
	public static void registerTypedChestItemRenderer(ItemConvertible chestItem) {
		RENDER_CHEST_TYPED.computeIfAbsent(chestItem, item -> {
			Block chestBlock = Blocks.CHEST;
			boolean isTrapped = false;
			if (item instanceof AbstractChestBlock abstractChestBlock) {
				chestBlock = abstractChestBlock;
				isTrapped = abstractChestBlock.aquifer$isTrapped();
			} else if (item instanceof BlockItem blockItem) {
				if (blockItem.getBlock() instanceof AbstractChestBlock abstractChestBlock) {
					chestBlock = abstractChestBlock;
					isTrapped = abstractChestBlock.aquifer$isTrapped();
				}
			}
			
			return isTrapped ? new TypedTrappedChestBlockEntity(BlockPos.ORIGIN, chestBlock.getDefaultState()) : new TypedChestBlockEntity(BlockPos.ORIGIN, chestBlock.getDefaultState());
		});
		
		BuiltinItemRendererRegistry.INSTANCE.register(chestItem, (stack, mode, matrices, vertexConsumers, light, overlay) -> {
			MinecraftClient.getInstance().getBlockEntityRenderDispatcher().renderEntity(RENDER_CHEST_TYPED.get(chestItem), matrices, vertexConsumers, light, overlay);
		});
	}
}