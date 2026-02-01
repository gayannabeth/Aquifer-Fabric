package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gay.mountainspring.aquifer.config.AquiferConfig;
import gay.mountainspring.aquifer.tag.AquiferTags;
import gay.mountainspring.aquifer.util.TagHandlingLevel;
import net.minecraft.block.BlockState;
import net.minecraft.block.CactusBlock;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;

@Mixin(CactusBlock.class)
public abstract class CactusBlockMixin {
	@SuppressWarnings("deprecation")
	@Inject(at = @At("HEAD"), method = "canPlaceAt(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/WorldView;Lnet/minecraft/util/math/BlockPos;)Z", cancellable = true)
	private void canPlaceAtInjected(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> info) {
		if (AquiferConfig.getInstance().getTagHandlingLevel() != TagHandlingLevel.DISABLED) {
			for (Direction direction : Direction.Type.HORIZONTAL) {
				BlockState blockState = world.getBlockState(pos.offset(direction));
				if (blockState.isSolid() || world.getFluidState(pos.offset(direction)).isIn(FluidTags.LAVA)) {
					if (AquiferConfig.getInstance().getTagHandlingLevel() == TagHandlingLevel.STRICT) info.setReturnValue(false);
					return;
				}
			}
			
			if (world.getBlockState(pos.down()).isIn(AquiferTags.Blocks.CACTUS_MAY_GROW_ON) && !world.getBlockState(pos.up()).isLiquid()) {
				info.setReturnValue(true);
			} else if (AquiferConfig.getInstance().getTagHandlingLevel() == TagHandlingLevel.STRICT) {
				info.setReturnValue(false);
			}
		}
	}
}