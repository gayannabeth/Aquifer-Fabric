package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gay.mountainspring.aquifer.config.AquiferConfig;
import gay.mountainspring.aquifer.tag.AquiferTags;
import gay.mountainspring.aquifer.util.TagHandlingLevel;
import net.minecraft.block.BlockState;
import net.minecraft.block.KelpBlock;

@Mixin(KelpBlock.class)
public abstract class KelpBlockMixin {
	@Inject(at = @At("HEAD"), method="canAttachTo(Lnet/minecraft/block/BlockState;)Z", cancellable = true)
	private void canAttachToInjected(BlockState state, CallbackInfoReturnable<Boolean> info) {
		if (AquiferConfig.getInstance().getTagHandlingLevel() != TagHandlingLevel.DISABLED) {
			if (state.isIn(AquiferTags.Blocks.KELP_MAY_NOT_PLACE_ON))
				info.setReturnValue(false);
			else if (AquiferConfig.getInstance().getTagHandlingLevel() == TagHandlingLevel.STRICT)
				info.setReturnValue(true);
		}
	}
}