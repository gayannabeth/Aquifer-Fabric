package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gay.mountainspring.aquifer.tag.AquiferTags;
import gay.mountainspring.aquifer.util.ScreenHandlerUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.EnchantmentScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;

@Mixin(EnchantmentScreenHandler.class)
public abstract class EnchantmentScreenHandlerMixin {
	@Inject(at = @At("HEAD"), method = "canUse(Lnet/minecraft/entity/player/PlayerEntity;)Z", cancellable = true)
	private void canUseInjected(PlayerEntity player, CallbackInfoReturnable<Boolean> info) {
		if (ScreenHandlerUtil.canUse(context, player, AquiferTags.Blocks.ENCHANTING_TABLES))
			info.setReturnValue(true);
	}
	
	@Shadow
	ScreenHandlerContext context;
}