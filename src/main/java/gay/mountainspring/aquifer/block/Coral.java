package gay.mountainspring.aquifer.block;

import net.minecraft.block.BlockState;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

public interface Coral {
	default BlockState aquifer$getDeadState(BlockState state, BlockView world, BlockPos pos) {
		return state;
	}
	
	default boolean aquifer$isInWater(BlockState state, BlockView world, BlockPos pos) {
		if (state.getBlock().getStateManager().getProperties().contains(Properties.WATERLOGGED)) {
			return true;
		}
		
		for (Direction direction : Direction.values()) {
			if (world.getFluidState(pos.offset(direction)).isIn(FluidTags.WATER)) {
				return true;
			}
		}
		
		return false;
	}
}