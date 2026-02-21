package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gay.mountainspring.aquifer.util.StatusEffectUtil;
import net.minecraft.entity.Attackable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.world.World;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements Attackable {
	private LivingEntityMixin(EntityType<?> type, World world) {super(type, world);}
	
	//this fixes a fucking bug too lmao
	@Inject(at = @At("HEAD"), method = "canHaveStatusEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;)Z", cancellable = true)
	private void canHaveStatusEffectInjected(StatusEffectInstance effect, CallbackInfoReturnable<Boolean> info)  {
		var map = StatusEffectUtil.effectImmuneTags();
		if (map.containsKey(effect.getEffectType()) && this.getType().isIn(map.get(effect.getEffectType())))
			info.setReturnValue(false);
	}
}