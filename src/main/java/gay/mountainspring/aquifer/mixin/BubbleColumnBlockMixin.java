package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gay.mountainspring.aquifer.config.AquiferConfig;
import gay.mountainspring.aquifer.tag.AquiferTags;
import gay.mountainspring.aquifer.util.TagHandlingLevel;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BubbleColumnBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

@Mixin(BubbleColumnBlock.class)
public abstract class BubbleColumnBlockMixin {
	@Inject(at = @At("HEAD"), method = "getBubbleState(Lnet/minecraft/block/BlockState;)Lnet/minecraft/block/BlockState;", cancellable = true)
	private static void getBubbleStateInjected(BlockState state, CallbackInfoReturnable<BlockState> info) {
		if (AquiferConfig.getInstance().getTagHandlingLevel() != TagHandlingLevel.DISABLED) {
			if (state.isIn(AquiferTags.Blocks.UPWARDS_BUBBLE_COLUMN_SOURCE_BLOCKS)) info.setReturnValue(Blocks.BUBBLE_COLUMN.getDefaultState().with(BubbleColumnBlock.DRAG, false));
			else if (state.isIn(AquiferTags.Blocks.DOWNWARDS_BUBBLE_COLUMN_SOURCE_BLOCKS)) info.setReturnValue(Blocks.BUBBLE_COLUMN.getDefaultState().with(BubbleColumnBlock.DRAG, true));
			else if (AquiferConfig.getInstance().getTagHandlingLevel()  == TagHandlingLevel.STRICT) info.setReturnValue(state.isOf(Blocks.BUBBLE_COLUMN) ? state : Blocks.WATER.getDefaultState());
		}
	}
	
	@Inject(at = @At("HEAD"), method = "canPlaceAt(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/WorldView;Lnet/minecraft/util/math/BlockPos;)Z", cancellable = true)
	private void canPlaceAtInjected(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> info) {
		if (AquiferConfig.getInstance().getTagHandlingLevel() != TagHandlingLevel.DISABLED) {
			BlockState belowState = world.getBlockState(pos.down());
			if (belowState.isIn(AquiferTags.Blocks.DOWNWARDS_BUBBLE_COLUMN_SOURCE_BLOCKS) || belowState.isIn(AquiferTags.Blocks.UPWARDS_BUBBLE_COLUMN_SOURCE_BLOCKS))
				info.setReturnValue(true);
			else if (AquiferConfig.getInstance().getTagHandlingLevel() == TagHandlingLevel.STRICT)
				info.setReturnValue(belowState.isOf(Blocks.BUBBLE_COLUMN));
		}
	}
}