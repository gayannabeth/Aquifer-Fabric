package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShapes;

public class TranslucentStepBlock extends StepBlock {
	public static final MapCodec<TranslucentStepBlock> CODEC = createCodec(TranslucentStepBlock::new);
	
	@Override
	public MapCodec<? extends TranslucentStepBlock> getCodec() {
		return CODEC;
	}
	
	public TranslucentStepBlock(Settings settings) {
		super(settings);
	}
	
	@Override
	public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
		return this == stateFrom.getBlock() && VoxelShapes.isSideCovered(state.getCullingFace(null, null, direction), stateFrom.getCullingFace(null, null, direction.getOpposite()), direction);
	}
}