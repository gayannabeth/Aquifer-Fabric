package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gay.mountainspring.aquifer.config.AquiferConfig;
import gay.mountainspring.aquifer.tag.AquiferTags;
import gay.mountainspring.aquifer.util.TagHandlingLevel;
import net.minecraft.block.BlockState;
import net.minecraft.block.FrogspawnBlock;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

@Mixin(FrogspawnBlock.class)
public abstract class FrogspawnBlockMixin {
	@Inject(at = @At("HEAD"), method="canLayAt(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)Z", cancellable=true)
	private static void canLayAtInjected(BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> info) {
		if (AquiferConfig.getInstance().getTagHandlingLevel() != TagHandlingLevel.DISABLED) {
			FluidState fluidState = world.getFluidState(pos);
			FluidState belowFluidState = world.getFluidState(pos.down());
			BlockState belowState = world.getBlockState(pos.down());
			
			if ((belowFluidState.isIn(AquiferTags.Fluids.FROGSPAWN_MAY_PLACE_ON) || belowState.isIn(AquiferTags.Blocks.FROGSPAWN_MAY_PLACE_ON)) && fluidState.isOf(Fluids.EMPTY)) {
				info.setReturnValue(true);
			} else if (AquiferConfig.getInstance().getTagHandlingLevel() == TagHandlingLevel.STRICT) {
				info.setReturnValue(false);
			}
		}
	}
}