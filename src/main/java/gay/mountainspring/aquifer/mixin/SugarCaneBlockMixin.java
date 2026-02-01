package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gay.mountainspring.aquifer.config.AquiferConfig;
import gay.mountainspring.aquifer.tag.AquiferTags;
import gay.mountainspring.aquifer.util.TagHandlingLevel;
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
		
		if (AquiferConfig.getInstance().getTagHandlingLevel() != TagHandlingLevel.DISABLED && !stateBelow.isOf(state.getBlock())) {
			if (stateBelow.isIn(AquiferTags.Blocks.SUGAR_CANE_MAY_GROW_ON)) {
				for (Direction dir : Direction.Type.HORIZONTAL) {
					BlockState stateBelowSide = world.getBlockState(posDown.offset(dir));
					FluidState fluidStateBelowSide = world.getFluidState(posDown.offset(dir));
					if (fluidStateBelowSide.isIn(AquiferTags.Fluids.SUGAR_CANE_MAY_GROW_BESIDE) || stateBelowSide.isIn(AquiferTags.Blocks.SUGAR_CANE_MAY_GROW_BESIDE)) {
						info.setReturnValue(true);
						return;
					}
				}
			}
			if (AquiferConfig.getInstance().getTagHandlingLevel() == TagHandlingLevel.STRICT)
				info.setReturnValue(false);
		}
	}
}