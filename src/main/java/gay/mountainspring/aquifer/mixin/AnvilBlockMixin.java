package gay.mountainspring.aquifer.mixin;

import java.util.Map;
import java.util.function.Function;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gay.mountainspring.aquifer.util.BlockUtil;
import net.minecraft.block.AnvilBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;

@Mixin(AnvilBlock.class)
public abstract class AnvilBlockMixin {
	@Inject(at = @At("HEAD"), method = "getLandingState(Lnet/minecraft/block/BlockState;)Lnet/minecraft/block/BlockState;", cancellable = true)
	private static void getLandingStateInjected(BlockState state, CallbackInfoReturnable<BlockState> info) {
		Map<Block, Function<BlockState, BlockState>> map = BlockUtil.anvilDamageMap();
		if (map.containsKey(state.getBlock())) {
			info.setReturnValue(map.get(state.getBlock()).apply(state));
		}
	}
}