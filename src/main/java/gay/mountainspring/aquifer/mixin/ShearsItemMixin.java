package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gay.mountainspring.aquifer.tag.AquiferTags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(ShearsItem.class)
public abstract class ShearsItemMixin {
	@Inject(at = @At("RETURN"), method = "postMine(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/LivingEntity;)Z", cancellable = true)
	private void postMineInjected(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner, CallbackInfoReturnable<Boolean> info) {
		if (state.isIn(AquiferTags.Blocks.SHEARS_MINEABLE))
			info.setReturnValue(true);
	}
}