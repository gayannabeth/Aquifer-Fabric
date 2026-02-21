package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gay.mountainspring.aquifer.tag.AquiferTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChorusPlantBlock;
import net.minecraft.block.ConnectingBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

@Mixin(ChorusPlantBlock.class)
public abstract class ChorusPlantBlockMixin {
	@Inject(at = @At("TAIL"), method = "withConnectionProperties(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)Lnet/minecraft/block/BlockState;", cancellable = true)
	private static void withConnectionPropertiesInjected(BlockView world, BlockPos pos, BlockState state, CallbackInfoReturnable<BlockState> info) {
		if (world.getBlockState(pos.down()).isIn(AquiferTags.Blocks.SUPPORTS_CHORUS))
			info.setReturnValue(info.getReturnValue().with(ConnectingBlock.DOWN, true));
	}
	
	@Inject(at = @At("HEAD"), method = "getStateForNeighborUpdate(Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/Direction;Lnet/minecraft/block/BlockState;Lnet/minecraft/world/WorldAccess;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/BlockState;", cancellable = true)
	private void getStateForNeighborUpdateInjected(BlockState state, Direction dir, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos, CallbackInfoReturnable<BlockState> info) {
		if (dir == Direction.DOWN && neighborState.isIn(AquiferTags.Blocks.SUPPORTS_CHORUS))
			info.setReturnValue(state.with(ConnectingBlock.FACING_PROPERTIES.get(dir), true));
	}
	
	@Inject(at = @At("HEAD"), method = "canPlaceAt(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/WorldView;Lnet/minecraft/util/math/BlockPos;)Z", cancellable = true)
	private void canPlaceAtInjected(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> info) {
		BlockState stateBelow = world.getBlockState(pos.down());
		boolean flag = !world.getBlockState(pos.up()).isAir() && !stateBelow.isAir();
		
		for (Direction dir : Direction.Type.HORIZONTAL) {
			BlockPos posBeside = pos.offset(dir);
			BlockState stateBeside = world.getBlockState(posBeside);
			if (stateBeside.isOf(state.getBlock())) {
				if (flag) {
					info.setReturnValue(false);
					return;
				} else {
					BlockState stateBelowBeside = world.getBlockState(posBeside.down());
					if (stateBelowBeside.isOf(state.getBlock()) || stateBelowBeside.isIn(AquiferTags.Blocks.SUPPORTS_CHORUS)) {
						info.setReturnValue(true);
						return;
					}
				}
			}
		}
		
		if (stateBelow.isOf(state.getBlock()) || stateBelow.isIn(AquiferTags.Blocks.SUPPORTS_CHORUS)) {
			info.setReturnValue(true);
		}
	}
}