package gay.mountainspring.aquifer.block;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.Direction;

public class TranslucentColumnBlock extends ColumnBlock {
	public static final MapCodec<TranslucentColumnBlock> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(
					Codec.INT.fieldOf("radius").forGetter(block -> block.radius),
					createSettingsCodec())
			.apply(instance, TranslucentColumnBlock::new));
	
	@Override
	public MapCodec<? extends ColumnBlock> getCodec() {
		return CODEC;
	}
	
	public TranslucentColumnBlock(int radius, Settings settings) {
		super(radius, settings);
	}
	
	@Override
	protected boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
		if (stateFrom.getBlock() == this) {
			if (state.get(AXIS) == stateFrom.get(AXIS)) {
				if (state.get(AXIS) != direction.getAxis()) {
					return ((state.get(TOP) || state.get(BOTTOM)) && state.get(TOP) == stateFrom.get(TOP) && state.get(BOTTOM) == stateFrom.get(BOTTOM)) ? true : super.isSideInvisible(state, stateFrom, direction);
				} else {
					return (state.get(PLATE_PROPERTIES.get(direction.getDirection())) == stateFrom.get(PLATE_PROPERTIES.get(direction.getOpposite().getDirection()))) ? true : super.isSideInvisible(state, stateFrom, direction);
				}
			} else {
				if (state.get(AXIS) != direction.getAxis() && stateFrom.get(AXIS) == direction.getAxis()) {
					return ((state.get(TOP) || state.get(BOTTOM)) && stateFrom.get(PLATE_PROPERTIES.get(direction.getOpposite().getDirection()))) ? true : super.isSideInvisible(state, stateFrom, direction);
				}
			}
		}
		
		return super.isSideInvisible(state, stateFrom, direction);
	}
}