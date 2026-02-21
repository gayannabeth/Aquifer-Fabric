package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gay.mountainspring.aquifer.tag.AquiferTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.SugarCaneBlock;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;

@Mixin(SugarCaneBlock.class)
public abstract class SugarCaneBlockMixin {
	@Inject(at = @At("HEAD"), method = "canPlaceAt(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/WorldView;Lnet/minecraft/util/math/BlockPos;)Z", cancellable = true)
	private void canPlaceAtInjected(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> info) {
		BlockPos posDown = pos.down();
		BlockState stateBelow = world.getBlockState(posDown);
		
		if (stateBelow.isIn(AquiferTags.Blocks.SUPPORTS_SUGAR_CANE)) {
			for (Direction dir : Direction.Type.HORIZONTAL) {
				BlockState stateBelowSide = world.getBlockState(posDown.offset(dir));
				FluidState fluidStateBelowSide = world.getFluidState(posDown.offset(dir));
				if (fluidStateBelowSide.isIn(AquiferTags.Fluids.SUPPORTS_SUGAR_CANE_ADJACENTLY) || stateBelowSide.isIn(AquiferTags.Blocks.SUPPORTS_SUGAR_CANE_ADJACENTLY)) {
					info.setReturnValue(true);
					return;
				}
			}
		}
	}
}