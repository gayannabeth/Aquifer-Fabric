package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gay.mountainspring.aquifer.util.RepairUtil;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;

@Mixin(ArmorItem.class)
public abstract class ArmorItemMixin extends Item {
	private ArmorItemMixin(Settings settings) {super(settings);}
	
	@Inject(at = @At("HEAD"), method = "canRepair(Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;)Z", cancellable = true)
	private void canRepairInjected(ItemStack stack, ItemStack ingredient, CallbackInfoReturnable<Boolean> info) {
		RegistryEntry<ArmorMaterial> material = ((ArmorItem) (Object) this).getMaterial();
		
		if (RepairUtil.ARMOR_REPAIR_INGREDIENTS.containsKey(material) && RepairUtil.ARMOR_REPAIR_INGREDIENTS.get(material).test(stack)) info.setReturnValue(true);
	}
}