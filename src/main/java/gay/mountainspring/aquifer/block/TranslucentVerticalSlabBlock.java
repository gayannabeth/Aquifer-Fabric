package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShapes;

public class TranslucentVerticalSlabBlock extends VerticalSlabBlock {
	public static final MapCodec<TranslucentVerticalSlabBlock> CODEC = createCodec(TranslucentVerticalSlabBlock::new);
	
	@Override
	public MapCodec<? extends TranslucentVerticalSlabBlock> getCodec() {
		return CODEC;
	}
	
	public TranslucentVerticalSlabBlock(Settings settings) {
		super(settings);
	}
	
	@Override
	public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
		return this == stateFrom.getBlock() && VoxelShapes.isSideCovered(state.getCullingFace(null, null, direction), stateFrom.getCullingFace(null, null, direction.getOpposite()), direction);
	}
}