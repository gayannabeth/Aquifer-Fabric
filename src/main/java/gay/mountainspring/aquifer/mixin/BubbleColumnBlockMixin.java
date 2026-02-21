package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gay.mountainspring.aquifer.tag.AquiferTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BubbleColumnBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

@Mixin(BubbleColumnBlock.class)
public abstract class BubbleColumnBlockMixin {
	@Inject(at = @At("HEAD"), method = "getBubbleState(Lnet/minecraft/block/BlockState;)Lnet/minecraft/block/BlockState;", cancellable = true)
	private static void getBubbleStateInjected(BlockState state, CallbackInfoReturnable<BlockState> info) {
		if (state.isIn(AquiferTags.Blocks.SUPPORTS_BUBBLE_COLUMN_UP)) info.setReturnValue(Blocks.BUBBLE_COLUMN.getDefaultState().with(BubbleColumnBlock.DRAG, false));
		else if (state.isIn(AquiferTags.Blocks.SUPPORTS_BUBBLE_COLUMN_DOWN)) info.setReturnValue(Blocks.BUBBLE_COLUMN.getDefaultState().with(BubbleColumnBlock.DRAG, true));
	}
	
	@Inject(at = @At("HEAD"), method = "canPlaceAt(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/WorldView;Lnet/minecraft/util/math/BlockPos;)Z", cancellable = true)
	private void canPlaceAtInjected(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> info) {
		BlockState belowState = world.getBlockState(pos.down());
		if (belowState.isIn(AquiferTags.Blocks.SUPPORTS_BUBBLE_COLUMN_DOWN) || belowState.isIn(AquiferTags.Blocks.SUPPORTS_BUBBLE_COLUMN_UP))
			info.setReturnValue(true);
	}
}