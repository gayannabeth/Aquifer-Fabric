package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gay.mountainspring.aquifer.tag.AquiferTags;
import net.minecraft.block.PowderSnowBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;

@Mixin(PowderSnowBlock.class)
public abstract class PowderSnowBlockMixin {
	@Inject(at = @At("HEAD"), method = "canWalkOnPowderSnow(Lnet/minecraft/entity/Entity;)Z", cancellable = true)
	private static void canWalkOnPowderSnowInjected(Entity entity, CallbackInfoReturnable<Boolean> info) {
		if (entity instanceof LivingEntity livingEntity && livingEntity.getEquippedStack(EquipmentSlot.FEET).isIn(AquiferTags.Items.POWDER_SNOW_WALKABLE_EQUIPMENT))
			info.setReturnValue(true);
	}
}