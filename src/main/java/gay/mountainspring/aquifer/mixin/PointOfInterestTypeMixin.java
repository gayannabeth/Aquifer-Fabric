package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gay.mountainspring.aquifer.util.BlockUtil;
import net.minecraft.block.BlockState;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.poi.PointOfInterestType;

@Mixin(PointOfInterestType.class)
public abstract class PointOfInterestTypeMixin {
	@Inject(at = @At("HEAD"), method = "contains(Lnet/minecraft/block/BlockState;)Z", cancellable = true)
	private void containsInjected(BlockState state, CallbackInfoReturnable<Boolean> info) {
		PointOfInterestType type = (PointOfInterestType) (Object) this;
		RegistryKey<PointOfInterestType> key = Registries.POINT_OF_INTEREST_TYPE.getKey(type).get();
		if (BlockUtil.poiSupportedBlocks().containsKey(key) && BlockUtil.poiSupportedBlocks().get(key).contains(state))
			info.setReturnValue(true);
	}
}