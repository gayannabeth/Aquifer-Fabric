package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gay.mountainspring.aquifer.util.BlockUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ShovelItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

@Mixin(ShovelItem.class)
public class ShovelItemMixin {
	@Inject(at = @At("HEAD"), method = "useOnBlock(Lnet/minecraft/item/ItemUsageContext;)Lnet/minecraft/util/ActionResult;", cancellable = true)
	private void useOnBlockInjected(ItemUsageContext context, CallbackInfoReturnable<ActionResult> info) {
		if (context.getSide() != Direction.DOWN) {
			PlayerEntity player = context.getPlayer();
			World world = context.getWorld();
			BlockPos pos = context.getBlockPos();
			BlockState fromState = world.getBlockState(pos);
			Block block = BlockUtil.blockFlatteningMap().get(world.getBlockState(pos).getBlock());
			if (block != null) {
				BlockState toState = block.getStateWithProperties(fromState);
				if (world.getBlockState(pos.up()).isAir()) {
					world.playSound(player, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0f, 1.0f);
					if (!world.isClient) {
						world.setBlockState(pos, toState, Block.NOTIFY_ALL_AND_REDRAW);
						world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, toState));
						if (player != null) {
							context.getStack().damage(1, player, LivingEntity.getSlotForHand(context.getHand()));
						}
					}
					info.setReturnValue(ActionResult.success(world.isClient));
				}
			}
		}
	}
}