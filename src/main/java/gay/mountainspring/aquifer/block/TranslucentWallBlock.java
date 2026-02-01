package gay.mountainspring.aquifer.block;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.MapCodec;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallBlock;
import net.minecraft.block.enums.WallShape;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.Direction;

public class TranslucentWallBlock extends WallBlock {
	static final Map<Direction, EnumProperty<WallShape>> DIRECTION_TO_PROPERTY = ImmutableMap.of(Direction.EAST, EAST_SHAPE, Direction.WEST, WEST_SHAPE, Direction.NORTH, NORTH_SHAPE, Direction.SOUTH, SOUTH_SHAPE);
	
	public static final MapCodec<WallBlock> CODEC = createCodec(TranslucentWallBlock::new);
	
	@Override
	public MapCodec<WallBlock> getCodec() {
		return CODEC;
	}
	
	public TranslucentWallBlock(Settings settings) {
		super(settings);
	}
	
	@Override
	public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
		Block other = stateFrom.getBlock();
		
		if (other == this) {
			WallShape thisShape;
			WallShape otherShape;
			
			switch (direction) {
				case UP:
					return this.isSideInvisibleUp(state, stateFrom);
				case DOWN:
					return this.isSideInvisibleDown(state, stateFrom);
				default:
					thisShape = state.get(DIRECTION_TO_PROPERTY.get(direction));
					otherShape = state.get(DIRECTION_TO_PROPERTY.get(direction.getOpposite()));
					return (otherShape == WallShape.TALL || otherShape == thisShape) && thisShape != WallShape.NONE;
			}
		}
		
		return false;
	}
	
	private boolean isSideInvisibleDown(BlockState state, BlockState stateFrom) {
		WallShape thisNorth = state.get(NORTH_SHAPE);
		WallShape otherNorth = stateFrom.get(NORTH_SHAPE);
		WallShape thisSouth = state.get(SOUTH_SHAPE);
		WallShape otherSouth = stateFrom.get(SOUTH_SHAPE);
		WallShape thisEast = state.get(EAST_SHAPE);
		WallShape otherEast = stateFrom.get(EAST_SHAPE);
		WallShape thisWest = state.get(WEST_SHAPE);
		WallShape otherWest = stateFrom.get(WEST_SHAPE);
		
		boolean northFlag = thisNorth == WallShape.NONE || (thisNorth == WallShape.TALL && otherNorth != WallShape.NONE);
		boolean southFlag = thisSouth == WallShape.NONE || (thisSouth == WallShape.TALL && otherSouth != WallShape.NONE);
		boolean eastFlag = thisEast == WallShape.NONE || (thisEast == WallShape.TALL && otherEast != WallShape.NONE);
		boolean westFlag = thisWest == WallShape.NONE || (thisWest == WallShape.TALL && otherWest != WallShape.NONE);
		
		return northFlag && southFlag && eastFlag && westFlag && (state.get(UP) == stateFrom.get(UP));
	}
	
	private boolean isSideInvisibleUp(BlockState state, BlockState stateFrom) {
		WallShape thisNorth = state.get(NORTH_SHAPE);
		WallShape otherNorth = stateFrom.get(NORTH_SHAPE);
		WallShape thisSouth = state.get(SOUTH_SHAPE);
		WallShape otherSouth = stateFrom.get(SOUTH_SHAPE);
		WallShape thisEast = state.get(EAST_SHAPE);
		WallShape otherEast = stateFrom.get(EAST_SHAPE);
		WallShape thisWest = state.get(WEST_SHAPE);
		WallShape otherWest = stateFrom.get(WEST_SHAPE);
		
		boolean northFlag = thisNorth == WallShape.NONE || otherNorth == WallShape.TALL;
		boolean southFlag = thisSouth == WallShape.NONE || otherSouth == WallShape.TALL;
		boolean eastFlag = thisEast == WallShape.NONE || otherEast == WallShape.TALL;
		boolean westFlag = thisWest == WallShape.NONE || otherWest == WallShape.TALL;
		
		return northFlag && southFlag && eastFlag && westFlag && (state.get(UP) == stateFrom.get(UP));
	}
}