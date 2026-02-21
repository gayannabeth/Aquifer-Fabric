package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gay.mountainspring.aquifer.util.RepairUtil;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;

@Mixin(ElytraItem.class)
public abstract class ElytraItemMixin {
	@Inject(at = @At("HEAD"), method = "canRepair(Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;)Z", cancellable = true)
	private void canRepairInjected(ItemStack stack, ItemStack ingredient, CallbackInfoReturnable<Boolean> info) {
		if (RepairUtil.ELYTRA_REPAIR_INGREDIENT.test(ingredient))
			info.setReturnValue(true);
	}
}