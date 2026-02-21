package gay.mountainspring.aquifer.mixin;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gay.mountainspring.aquifer.tag.AquiferTags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.dragon.EnderDragonFight;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.item.EndCrystalItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

@Mixin(EndCrystalItem.class)
public abstract class EndCrystalItemMixin {
	@Inject(at = @At("HEAD"), method = "useOnBlock(Lnet/minecraft/item/ItemUsageContext;)Lnet/minecraft/util/ActionResult;", cancellable = true)
	private void useOnBlockInjected(ItemUsageContext context, CallbackInfoReturnable<ActionResult> info) {
		World world = context.getWorld();
		BlockPos pos = context.getBlockPos();
		BlockState state = world.getBlockState(pos);
		
		if (state.isIn(AquiferTags.Blocks.SUPPORTS_END_CRYSTAL)) {
			BlockPos posUp = pos.up();
			if (world.isAir(posUp)) {
				double d = posUp.getX();
				double e = posUp.getY();
				double f = posUp.getZ();
				List<Entity> otherEntities = world.getOtherEntities(null, new Box(d, e, f, d + 1.0D, e + 1.0D, f + 1.0D));
				if (otherEntities.isEmpty()) {
					if (world instanceof ServerWorld serverWorld) {
						EndCrystalEntity endCrystalEntity = new EndCrystalEntity(world, d + 0.5D, e, f + 0.5D);
						endCrystalEntity.setShowBottom(false);
						world.spawnEntity(endCrystalEntity);
						world.emitGameEvent(context.getPlayer(), GameEvent.ENTITY_PLACE, posUp);
						EnderDragonFight enderDragonFight = serverWorld.getEnderDragonFight();
						if (enderDragonFight != null) {
							enderDragonFight.respawnDragon();
						}
					}
					
					context.getStack().decrement(1);
					info.setReturnValue(ActionResult.success(world.isClient));
				}
			}
		}
	}
}