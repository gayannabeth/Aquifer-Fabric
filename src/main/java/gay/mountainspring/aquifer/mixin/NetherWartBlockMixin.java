package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gay.mountainspring.aquifer.config.AquiferConfig;
import gay.mountainspring.aquifer.tag.AquiferTags;
import gay.mountainspring.aquifer.util.TagHandlingLevel;
import net.minecraft.block.BlockState;
import net.minecraft.block.NetherWartBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

@Mixin(NetherWartBlock.class)
public abstract class NetherWartBlockMixin {
	@Inject(at = @At("HEAD"), method = "canPlantOnTop(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)Z", cancellable = true)
	private void canPlantOnTopInjected(BlockState floor, BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> info) {
		if (AquiferConfig.getInstance().getTagHandlingLevel() != TagHandlingLevel.DISABLED) {
			if (floor.isIn(AquiferTags.Blocks.NETHER_WART_GROWABLE)) {
				info.setReturnValue(true);
			} else if (AquiferConfig.getInstance().getTagHandlingLevel() == TagHandlingLevel.STRICT) {
				info.setReturnValue(false);
			}
		}
	}
}