package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gay.mountainspring.aquifer.tag.AquiferTags;
import net.minecraft.block.BlockState;
import net.minecraft.screen.SmithingScreenHandler;

@Mixin(SmithingScreenHandler.class)
public abstract class SmithingScreenHandlerMixin {
	@Inject(at = @At("HEAD"), method = "canUse(Lnet/minecraft/block/BlockState;)Z", cancellable = true)
	private void canUseInjected(BlockState state, CallbackInfoReturnable<Boolean> info) {
		if (state.isIn(AquiferTags.Blocks.SMITHING_TABLES))
			info.setReturnValue(true);
	}
}