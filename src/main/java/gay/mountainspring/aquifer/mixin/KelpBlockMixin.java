package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gay.mountainspring.aquifer.tag.AquiferTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.KelpBlock;

@Mixin(KelpBlock.class)
public abstract class KelpBlockMixin {
	@Inject(at = @At("HEAD"), method="canAttachTo(Lnet/minecraft/block/BlockState;)Z", cancellable = true)
	private void canAttachToInjected(BlockState state, CallbackInfoReturnable<Boolean> info) {
		if (state.isIn(AquiferTags.Blocks.DOES_NOT_SUPPORT_KELP))
			info.setReturnValue(false);
	}
}