package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gay.mountainspring.aquifer.tag.AquiferTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.LilyPadBlock;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

@Mixin(LilyPadBlock.class)
public abstract class LilyPadBlockMixin {
	@Inject(at = @At("HEAD"), method = "canPlantOnTop(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)Z", cancellable = true)
	private void canPlantOnTopInjected(BlockState floor, BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> info) {
		FluidState fluidState = world.getFluidState(pos.up());
		FluidState floorFluid = floor.getFluidState();
		if ((floorFluid.isIn(AquiferTags.Fluids.SUPPORTS_LILY_PAD) || floor.isIn(AquiferTags.Blocks.SUPPORTS_LILY_PAD)) && fluidState.isOf(Fluids.EMPTY))
				info.setReturnValue(true);
	}
}