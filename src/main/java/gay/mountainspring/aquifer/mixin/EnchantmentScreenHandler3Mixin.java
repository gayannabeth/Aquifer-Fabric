package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gay.mountainspring.aquifer.tag.AquiferTags;
import net.minecraft.item.ItemStack;

@Mixin(targets = "net.minecraft.screen.EnchantmentScreenHandler$3")
public abstract class EnchantmentScreenHandler3Mixin {
	@Inject(at = @At("HEAD"), method = "canInsert(Lnet/minecraft/item/ItemStack;)Z", cancellable = true)
	private void canInsertInjected(ItemStack stack, CallbackInfoReturnable<Boolean> info) {
		if (stack.isIn(AquiferTags.Items.ENCHANTMENT_PAYMENT_ITEMS))
			info.setReturnValue(true);
	}
}