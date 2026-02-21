package gay.mountainspring.aquifer.mixin;

import java.util.Map;
import java.util.function.Predicate;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gay.mountainspring.aquifer.tag.AquiferTags;
import gay.mountainspring.aquifer.util.ItemUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;

@Mixin(Entity.class)
public abstract class EntityMixin {
	@Inject(at = @At("HEAD"), method = "isInvulnerableTo(Lnet/minecraft/entity/damage/DamageSource;)Z", cancellable = true)
	private void isInvulnerableToInjected(DamageSource damageSource, CallbackInfoReturnable<Boolean> info) {
		if (((Object) this) instanceof ItemEntity itemEntity) {
			ItemStack stack = itemEntity.getStack();
			
			Map<TagKey<Item>, Predicate<DamageSource>> map = ItemUtil.itemTagDamageHandlers();
			
			for (TagKey<Item> tag : map.keySet()) {
				if (stack.isIn(tag) && map.get(tag).test(damageSource)) {
					info.setReturnValue(true);
					break;
				}
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