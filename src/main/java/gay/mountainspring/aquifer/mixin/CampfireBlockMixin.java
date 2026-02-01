package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gay.mountainspring.aquifer.config.AquiferConfig;
import gay.mountainspring.aquifer.tag.AquiferTags;
import gay.mountainspring.aquifer.util.TagHandlingLevel;
import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;

@Mixin(CampfireBlock.class)
public abstract class CampfireBlockMixin {
	@Inject(at = @At("HEAD"), method = "isSignalFireBaseBlock(Lnet/minecraft/block/BlockState;)Z", cancellable = true)
	private void isSignalFireBaseBlockInjected(BlockState state, CallbackInfoReturnable<Boolean> info) {
		if (AquiferConfig.getInstance().getTagHandlingLevel() != TagHandlingLevel.DISABLED) {
			if (state.isIn(AquiferTags.Blocks.SIGNAL_FIRE_BASE_BLOCKS))
				info.setReturnValue(true);
			else if (AquiferConfig.getInstance().getTagHandlingLevel() == TagHandlingLevel.STRICT)
				info.setReturnValue(false);
		}
	}
}