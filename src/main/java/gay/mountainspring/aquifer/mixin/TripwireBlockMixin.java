package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gay.mountainspring.aquifer.tag.AquiferTags;
import gay.mountainspring.aquifer.util.TripwireUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TripwireBlock;
import net.minecraft.block.TripwireHookBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

@Mixin(TripwireBlock.class)
public abstract class TripwireBlockMixin {
	@Inject(at = @At("TAIL"), method = "shouldConnectTo(Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/Direction;)Z", cancellable = true)
	private void shouldConnectToInjected(BlockState state, Direction dir, CallbackInfoReturnable<Boolean> info) {
		if (state.isIn(AquiferTags.Blocks.TRIPWIRE_HOOKS) && state.getProperties().contains(TripwireHookBlock.FACING) && state.get(TripwireHookBlock.FACING) == dir.getOpposite()) {
			info.setReturnValue(true);
		}
	}
	
	@Inject(at = @At("HEAD"), method = "update(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)V", cancellable = true)
	private void updateInjected(World world, BlockPos pos, BlockState state, CallbackInfo info) {
		for (Direction dir : new Direction[] {Direction.SOUTH, Direction.WEST}) {
			for (int i = 1; i < 42; i++) {
				BlockPos pos2 = pos.offset(dir, i);
				BlockState state2 = world.getBlockState(pos2);
				if (state2.isIn(AquiferTags.Blocks.TRIPWIRE_HOOKS)) {
					if (state2.get(TripwireHookBlock.FACING) == dir.getOpposite()) {
						TripwireUtil.update(world, pos2, state2, false, true, i, state);
					}
					break;
				}
				
				if (!state2.isOf(Blocks.TRIPWIRE)) {
					break;
				}
			}
		}
		info.cancel();
	}
}