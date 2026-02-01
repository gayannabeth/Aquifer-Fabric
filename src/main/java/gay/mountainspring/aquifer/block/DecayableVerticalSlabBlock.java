package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SnowBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.light.ChunkLightProvider;

public abstract class DecayableVerticalSlabBlock extends VerticalSlabBlock {
	public DecayableVerticalSlabBlock(Settings settings) {
		super(settings);
	}
	
	@Override
	public abstract MapCodec<? extends DecayableVerticalSlabBlock> getCodec();
	
	protected abstract BlockState getDecayState(BlockState state);
	
	private static boolean canSurvive(BlockState state, WorldView world, BlockPos pos) {
		BlockPos blockPos = pos.up();
		BlockState blockState = world.getBlockState(blockPos);
		if (blockState.isOf(Blocks.SNOW) && (Integer)blockState.get(SnowBlock.LAYERS) == 1) {
			return true;
		} else if (blockState.getFluidState().getLevel() == 8) {
			return false;
		} else {
			int i = ChunkLightProvider.getRealisticOpacity(world, state, pos, blockState, blockPos, Direction.UP, blockState.getOpacity(world, blockPos));
			return i < world.getMaxLightLevel();
		}
	}
	
	@Override
	protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		if (!canSurvive(state, world, pos)) {
			world.setBlockState(pos, this.getDecayState(state));
		}
	}
}