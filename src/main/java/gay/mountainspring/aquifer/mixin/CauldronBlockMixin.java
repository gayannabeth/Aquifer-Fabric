package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gay.mountainspring.aquifer.block.AbstractAquiferCauldronBlock;
import gay.mountainspring.aquifer.block.cauldron.CauldronGroup;
import gay.mountainspring.aquifer.util.BlockUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.CauldronBlock;
import net.minecraft.block.cauldron.CauldronBehavior.CauldronBehaviorMap;
import net.minecraft.fluid.Fluid;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

@Mixin(CauldronBlock.class)
public abstract class CauldronBlockMixin extends AbstractAquiferCauldronBlock {
	private CauldronBlockMixin(CauldronGroup group, Settings settings, CauldronBehaviorMap behaviorMap) {super(group, settings, behaviorMap);}
	
	@Inject(at = @At("HEAD"), method = "canBeFilledByDripstone(Lnet/minecraft/fluid/Fluid;)Z", cancellable = true)
	private void canBeFilledByDripstoneInjected(Fluid fluid, CallbackInfoReturnable<Boolean> info) {
		info.setReturnValue(BlockUtil.isDripstoneFluid(fluid));
	}
	
	@Inject(at = @At("HEAD"), method = "fillFromDripstone(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/fluid/Fluid;)V", cancellable = true)
	private void fillFromDripstoneInjected(BlockState state, World world, BlockPos pos, Fluid fluid, CallbackInfo info) {
		if (BlockUtil.isDripstoneFluid(fluid)) {
			BlockState blockState  = BlockUtil.getStateForDrippingFluidIntoEmptyCauldron(fluid, this.aquifer$getGroup());
			world.setBlockState(pos, blockState);
			world.playSoundAtBlockCenter(pos, BlockUtil.getSoundForDrippingFluid(fluid), SoundCategory.BLOCKS, 1.0f, 1.0f, false);
			world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(blockState));
			info.cancel();
		}
	}
}