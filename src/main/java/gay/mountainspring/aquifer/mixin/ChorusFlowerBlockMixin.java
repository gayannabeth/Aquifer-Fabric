package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gay.mountainspring.aquifer.tag.AquiferTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChorusFlowerBlock;
import net.minecraft.block.ChorusPlantBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;

@Mixin(ChorusFlowerBlock.class)
public abstract class ChorusFlowerBlockMixin extends Block {
private ChorusFlowerBlockMixin(Settings settings) {super(settings);}
	
	@Shadow
	private Block plantBlock;
	
	@Inject(at = @At("HEAD"), method = "randomTick(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/random/Random;)V", cancellable = true)
	private void randomTickInjected(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo info) {
		BlockPos posUp = pos.up();
		if (world.isAir(posUp) && posUp.getY() < world.getTopY()) {
			int i = state.get(ChorusFlowerBlock.AGE);
			
			if (i < 5) {
				boolean flag1 = false;
				boolean flag2 = false;
				BlockState stateBelow = world.getBlockState(pos.down());
				if (stateBelow.isIn(AquiferTags.Blocks.SUPPORTS_CHORUS)) {
					flag1 = true;
				} else if (stateBelow.isOf(plantBlock)) {
					int j = 1;
					
					for (int k = 0; k < 4; k ++) {
						BlockState stateBelow2 = world.getBlockState(pos.down(j + 1));
						if (!stateBelow2.isOf(plantBlock)) {
							if (stateBelow2.isIn(AquiferTags.Blocks.SUPPORTS_CHORUS)) {
								flag2 = true;
							}
							break;
						}
						j++;
					}
					
					if (j < 2 || j <= random.nextInt(flag2 ? 5 : 4)) {
						flag1 = true;
					}
				} else if (stateBelow.isAir()) {
					flag1 = true;
				}
				
				if (flag1 && ChorusFlowerBlockAccessor.invokeIsSurroundedByAir(world, posUp, null) && world.isAir(posUp.up())) {
					world.setBlockState(pos, ChorusPlantBlock.withConnectionProperties(world, pos, this.plantBlock.getDefaultState()), Block.NOTIFY_LISTENERS);
					((ChorusFlowerBlockAccessor) (ChorusFlowerBlock) (Object) this).invokeGrow(world, posUp, i);
				} else if (i < 4) {
					int j = random.nextInt(4);
					if (flag2) j++;
					
					boolean flag3 = false;
					
					for (int l = 0; l < j; l++) {
						Direction direction = Direction.Type.HORIZONTAL.random(random);
						BlockPos posBeside = pos.offset(direction);
						if (world.isAir(posBeside) && world.isAir(posBeside.down()) && ChorusFlowerBlockAccessor.invokeIsSurroundedByAir(world, posBeside, direction.getOpposite())) {
							((ChorusFlowerBlockAccessor) (ChorusFlowerBlock) (Object) this).invokeGrow(world, posBeside, i + 1);
							flag3 = true;
						}
					}
					
					if (flag3) {
						world.setBlockState(pos, ChorusPlantBlock.withConnectionProperties(world, pos, this.plantBlock.getDefaultState()), Block.NOTIFY_LISTENERS);
					} else {
						((ChorusFlowerBlockAccessor) (ChorusFlowerBlock) (Object) this).invokeDie(world, pos);
					}
				} else {
					((ChorusFlowerBlockAccessor) (ChorusFlowerBlock) (Object) this).invokeDie(world, pos);
				}
			}
		}
		info.cancel();
	}
	
	@Inject(at = @At("HEAD"), method = "canPlaceAt(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/WorldView;Lnet/minecraft/util/math/BlockPos;)Z", cancellable = true)
	private void canPlaceAtInjected(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> info) {
		if (world.getBlockState(pos.down()).isIn(AquiferTags.Blocks.SUPPORTS_CHORUS))
			info.setReturnValue(true);
	}
}