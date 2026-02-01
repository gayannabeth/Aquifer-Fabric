package gay.mountainspring.aquifer.mixin;

import java.util.Map;
import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gay.mountainspring.aquifer.util.BlockUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.AxeItem;

@Mixin(AxeItem.class)
public abstract class AxeItemMixin {
	@Inject(method = "getStrippedState(Lnet/minecraft/block/BlockState;)Ljava/util/Optional;", at = @At("HEAD"), cancellable = true)
	private void getStrippedStateInjected(BlockState state, CallbackInfoReturnable<Optional<BlockState>> info) {
		Map<Block, Block> map = BlockUtil.blockStrippingMap();
		
		if (map.containsKey(state.getBlock())) {
			info.setReturnValue(Optional.ofNullable(map.get(state.getBlock())).map(block -> block.getStateWithProperties(state)));
		}
	}
}