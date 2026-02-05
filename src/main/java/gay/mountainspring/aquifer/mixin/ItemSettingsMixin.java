package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gay.mountainspring.aquifer.item.AquiferItemSettings;
import gay.mountainspring.aquifer.item.component.AquiferComponentTypes;
import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;

@Mixin(Item.Settings.class)
public abstract class ItemSettingsMixin implements AquiferItemSettings, FabricItem.Settings {
	@Inject(at = @At("TAIL"), method = "recipeRemainder(Lnet/minecraft/item/Item;)Lnet/minecraft/item/Item$Settings;", cancellable = true)
	private void recipeRemainderInjected(Item recipeRemainder, CallbackInfoReturnable<Item.Settings> info) {
		info.setReturnValue(((Item.Settings) (Object) this).component(AquiferComponentTypes.RECIPE_REMAINDER, Registries.ITEM.getEntry(recipeRemainder)));
	}
}