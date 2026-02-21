package gay.mountainspring.aquifer.mixin;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gay.mountainspring.aquifer.tag.AquiferTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.ConduitBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(ConduitBlockEntity.class)
public abstract class ConduitBlockEntityMixin {
	@Inject(at = @At("TAIL"), method = "updateActivatingBlocks(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Ljava/util/List;)Z", cancellable = true)
	private static void updateActivatingBlocksInjected(World world, BlockPos pos, List<BlockPos> activatingBlocks, CallbackInfoReturnable<Boolean> info) {
		for (int i = -2; i <= 2; i++) {
			for (int j = -2; j <= 2; j++) {
				for (int kx = -2; kx <= 2; kx++) {
					int l = Math.abs(i);
					int m = Math.abs(j);
					int n = Math.abs(kx);
					if ((l > 1 || m > 1 || n > 1) && (i == 0 && (m == 2 || n == 2) || j == 0 && (l == 2 || n == 2) || kx == 0 && (l == 2 || m == 2))) {
						BlockPos offsetPos = pos.add(i, j, kx);
						BlockState stateToTest = world.getBlockState(offsetPos);
						if (stateToTest.isIn(AquiferTags.Blocks.CONDUIT_ACTIVATING_BLOCKS) && !activatingBlocks.contains(offsetPos)) {
							activatingBlocks.add(offsetPos);
						}
					}
				}
			}
		}
	}
}