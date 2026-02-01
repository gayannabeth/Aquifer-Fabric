package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gay.mountainspring.aquifer.config.AquiferConfig;
import gay.mountainspring.aquifer.tag.AquiferTags;
import gay.mountainspring.aquifer.util.TagHandlingLevel;
import net.minecraft.block.RespawnAnchorBlock;
import net.minecraft.item.ItemStack;

@Mixin(RespawnAnchorBlock.class)
public abstract class RespawnAnchorBlockMixin {
	@Inject(at = @At("HEAD"), method = "isChargeItem(Lnet/minecraft/item/ItemStack;)Z", cancellable = true)
	private static void isChargeItemInjected(ItemStack stack, CallbackInfoReturnable<Boolean> info) {
		if (AquiferConfig.getInstance().getTagHandlingLevel() != TagHandlingLevel.DISABLED) {
			if (stack.isIn(AquiferTags.Items.RESPAWN_ANCHOR_CHARGE_ITEMS))
				info.setReturnValue(true);
			else if (AquiferConfig.getInstance().getTagHandlingLevel() == TagHandlingLevel.STRICT)
				info.setReturnValue(false);
		}
	}
}