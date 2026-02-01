package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import gay.mountainspring.aquifer.block.Coral;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CoralBlockBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

@Mixin(CoralBlockBlock.class)
public class CoralBlockBlockMixin implements Coral {
	@Override
	public BlockState aquifer$getDeadState(BlockState state, BlockView world, BlockPos pos) {
		return deadCoralBlock.getStateWithProperties(state);
	}
	
	@Shadow
	Block deadCoralBlock;
}