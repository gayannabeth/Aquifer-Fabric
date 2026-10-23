package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gay.mountainspring.aquifer.item.component.AquiferComponentTypes;
import gay.mountainspring.aquifer.tag.AquiferTags;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.DamageTypeTags;

@Mixin(Entity.class)
public abstract class EntityMixin {
	@Inject(at = @At("HEAD"), method = "isInvulnerableTo(Lnet/minecraft/entity/damage/DamageSource;)Z", cancellable = true)
	private void isInvulnerableToInjected(DamageSource damageSource, CallbackInfoReturnable<Boolean> info) {
		if (((Object) this) instanceof ItemEntity itemEntity) {
			ItemStack stack = itemEntity.getStack();
			
			if (damageSource.isIn(DamageTypeTags.IS_FIRE) && (stack.isIn(AquiferTags.Items.FIRE_PROOF) || stack.contains(DataComponentTypes.FIRE_RESISTANT) )) {
				info.setReturnValue(true);
			} else if (damageSource.isIn(DamageTypeTags.IS_EXPLOSION) && (stack.isIn(AquiferTags.Items.EXPLOSION_PROOF) || stack.contains(AquiferComponentTypes.BLAST_RESISTANT))) {
				info.setReturnValue(true);
			} else if (damageSource.isOf(DamageTypes.CACTUS) && (stack.isIn(AquiferTags.Items.CACTUS_PROOF) || stack.contains(AquiferComponentTypes.CACTUS_RESISTANT))) {
				info.setReturnValue(true);
			} else if (damageSource.isOf(DamageTypes.OUT_OF_WORLD) && (stack.isIn(AquiferTags.Items.VOID_PROOF) || stack.contains(AquiferComponentTypes.AVOIDS_VOID))) {
				info.setReturnValue(true);
			}
		}
	}
	
	@Inject(at = @At("HEAD"), method = "tickInVoid()V", cancellable = true)
	private void tickInVoidInjected(CallbackInfo info) {
		if (((Object) this) instanceof ItemEntity itemEntity) {
			if (itemEntity.getStack().isIn(AquiferTags.Items.VOID_PROOF)) {
				int top = itemEntity.getWorld().getHeight();
				itemEntity.requestTeleport(itemEntity.getX(), top, itemEntity.getZ());
				info.cancel();
			}
		}
	}
}