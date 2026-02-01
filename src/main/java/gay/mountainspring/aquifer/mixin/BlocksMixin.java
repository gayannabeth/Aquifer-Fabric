package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gay.mountainspring.aquifer.tag.AquiferTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

@Mixin(Blocks.class)
public class BlocksMixin {
	@Inject(at = @At("HEAD"), method = "canSpawnOnLeaves(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/EntityType;)Ljava/lang/Boolean;", cancellable = true)
	private static void canSpawnOnLeavesInjected(BlockState state, BlockView world, BlockPos pos, EntityType<?> entityType, CallbackInfoReturnable<Boolean> info) {
		if (entityType.isIn(AquiferTags.EntityTypes.CAN_SPAWN_ON_LEAVES)) info.setReturnValue(true);
	}
}