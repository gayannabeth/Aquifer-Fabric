package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.util.math.Direction;

public class TranslucentSlabBlock extends SlabBlock {
	public static final MapCodec<TranslucentSlabBlock> CODEC = createCodec(TranslucentSlabBlock::new);
	
	@Override
	public MapCodec<? extends TranslucentSlabBlock> getCodec() {
		return CODEC;
	}
	
	public TranslucentSlabBlock(Settings settings) {
		super(settings);
	}
	
	@Override
	public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
		Block other = stateFrom.getBlock();
		
		if (other == this) {
			SlabType thisType = state.get(TYPE);
			SlabType otherType = state.get(TYPE);
			
			if (otherType == SlabType.DOUBLE && direction.getAxis() != Direction.Axis.Y) {
				return thisType == SlabType.DOUBLE;
			}
			
			if (direction == Direction.UP) {
				return otherType == SlabType.BOTTOM && thisType != SlabType.BOTTOM;
			}
			
			if (direction == Direction.DOWN) {
				return otherType == SlabType.TOP && thisType != SlabType.TOP;
			}
			
			return otherType == thisType || thisType == SlabType.DOUBLE;
		}
		
		return false;
	}
}