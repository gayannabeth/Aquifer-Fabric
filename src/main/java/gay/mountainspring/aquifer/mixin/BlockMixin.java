package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gay.mountainspring.aquifer.tag.AquiferTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;


@Mixin(Block.class)
public abstract class BlockMixin {
	@Inject(at = @At("HEAD"), method = "cannotConnect(Lnet/minecraft/block/BlockState;)Z", cancellable = true)
	private static void cannotConnectInjected(BlockState state, CallbackInfoReturnable<Boolean> info) {
		if (state.isIn(AquiferTags.Blocks.CANNOT_CONNECT_TO))
			info.setReturnValue(true);
	}
}