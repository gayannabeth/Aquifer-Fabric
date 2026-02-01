package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gay.mountainspring.aquifer.item.AquiferItemStack;
import gay.mountainspring.aquifer.item.component.AquiferComponentTypes;
import net.fabricmc.fabric.api.item.v1.FabricItemStack;
import net.minecraft.component.ComponentHolder;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements ComponentHolder, FabricItemStack, AquiferItemStack {
	@Inject(at = @At("HEAD"), method = "getDrinkSound()Lnet/minecraft/sound/SoundEvent;", cancellable = true)
	private void getDrinkSoundInjected(CallbackInfoReturnable<SoundEvent> info) {
		RegistryEntry<SoundEvent> entry = this.get(AquiferComponentTypes.DRINK_SOUND);
		if (entry != null)
			info.setReturnValue(entry.value());
	}
	
	@Inject(at = @At("HEAD"), method = "getEatSound()Lnet/minecraft/sound/SoundEvent;", cancellable = true)
	private void getEatSoundInjected(CallbackInfoReturnable<SoundEvent> info) {
		RegistryEntry<SoundEvent> entry = this.get(AquiferComponentTypes.EAT_SOUND);
		if (entry != null)
			info.setReturnValue(entry.value());
	}
	
	@Inject(at = @At("HEAD"), method = "getBreakSound()Lnet/minecraft/sound/SoundEvent;", cancellable = true)
	private void getBreakSoundInjected(CallbackInfoReturnable<SoundEvent> info) {
		RegistryEntry<SoundEvent> entry = this.get(AquiferComponentTypes.BREAK_SOUND);
		if (entry != null)
			info.setReturnValue(entry.value());
	}
	
	@Override
	public int aquifer$getEnchantability() {
		Integer i = this.get(AquiferComponentTypes.ENCHANTABILITY);
		if (i != null) return i;
		else return ((ItemStack) (Object) this).getItem().getEnchantability();
	}
}