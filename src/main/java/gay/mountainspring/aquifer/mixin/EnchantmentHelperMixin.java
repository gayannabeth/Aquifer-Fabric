package gay.mountainspring.aquifer.mixin;

import java.util.List;
import java.util.stream.Stream;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.random.Random;

@Mixin(EnchantmentHelper.class)
public abstract class EnchantmentHelperMixin {
	@Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item;getEnchantability()I", shift = At.Shift.AFTER), method = "calculateRequiredExperienceLevel(Lnet/minecraft/util/math/random/Random;IILnet/minecraft/item/ItemStack;)I", cancellable = true)
	private static void modifyEnchantabilityCREL(Random random, int slotIndex, int bookshelfCount, ItemStack stack, CallbackInfoReturnable<Integer> info, @Local(ordinal = 0) LocalIntRef enchantability) {
		enchantability.set(stack.aquifer$getEnchantability());
	}
	
	@Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item;getEnchantability()I", shift = At.Shift.AFTER), method = "generateEnchantments(Lnet/minecraft/util/math/random/Random;Lnet/minecraft/item/ItemStack;ILjava/util/stream/Stream;)Ljava/util/List;", cancellable = true)
	private static void  modiftyEnchantabilityGE(Random random, ItemStack stack, int level, Stream<RegistryEntry<Enchantment>> possibleEnchantments, CallbackInfoReturnable<List<EnchantmentLevelEntry>> info, @Local(ordinal = 0) LocalIntRef enchantability) {
		enchantability.set(stack.aquifer$getEnchantability());
	}
}