package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gay.mountainspring.aquifer.config.AquiferConfig;
import gay.mountainspring.aquifer.tag.AquiferTags;
import gay.mountainspring.aquifer.util.TagHandlingLevel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.Ownable;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity implements Ownable {
	private ItemEntityMixin(EntityType<?> type, World world) {super(type, world);}
	
	@Inject(at = @At("TAIL"), method = "<init>(Lnet/minecraft/world/World;DDDLnet/minecraft/item/ItemStack;DDD)V")
	private void constructorInjected(World world, double x, double y, double z, ItemStack stack, double xVelocity, double yVelocity, double zVelocity, CallbackInfo info) {
		if (AquiferConfig.getInstance().getTagHandlingLevel() != TagHandlingLevel.DISABLED && stack.isIn(AquiferTags.Items.DESPAWN_PROOF))
			((ItemEntity) (Object) this).setNeverDespawn();
	}
	
	@Inject(at = @At("HEAD"), method = "isFireImmune()Z", cancellable = true)
	private void isFireImmuneInjected(CallbackInfoReturnable<Boolean> info) {
		if (AquiferConfig.getInstance().getTagHandlingLevel() != TagHandlingLevel.DISABLED) {
			if (((ItemEntity) (Object) this).getStack().isIn(AquiferTags.Items.FIRE_PROOF))
				info.setReturnValue(true);
			else if (AquiferConfig.getInstance().getTagHandlingLevel() == TagHandlingLevel.STRICT)
				info.setReturnValue(false);
		}
	}
}