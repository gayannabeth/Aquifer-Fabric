package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gay.mountainspring.aquifer.block.BlockWithStairShape;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.enums.StairShape;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

@Mixin(StairsBlock.class)
public abstract class StairsBlockMixin {
	@Inject(at = @At("HEAD"), method = "getStairShape(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/enums/StairShape;", cancellable = true)
	private static void getStairShapeInjected(BlockState state, BlockView world, BlockPos pos, CallbackInfoReturnable<StairShape> info) {
		info.setReturnValue(BlockWithStairShape.getStairShape(state, world, pos));
	}
}