package gay.mountainspring.aquifer.block;

import java.util.function.Predicate;

import com.mojang.serialization.MapCodec;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.StairShape;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

public abstract class BlockWithStairShape extends Block {
	//Always add these properties
	public static final EnumProperty<StairShape> SHAPE = Properties.STAIR_SHAPE;
	public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
	
	//maybe add these properties
	public static final EnumProperty<BlockHalf> HALF = Properties.BLOCK_HALF;
	public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
	
	@Override
	protected abstract MapCodec<? extends BlockWithStairShape> getCodec();
	
	public BlockWithStairShape(Settings settings) {
		super(settings);
		if (!this.stateManager.getProperties().contains(SHAPE))
			throw new IllegalStateException("Block {} does not contain the \"shape\" property!");
		if (!this.stateManager.getProperties().contains(FACING))
			throw new IllegalStateException("Block {} does not contain the \"facing\" property!");
	}
	
	public static StairShape getStairShape(BlockState state, BlockView world, BlockPos pos) {
		if (!hasStairShape(state)) return StairShape.STRAIGHT;
		
		Direction facing = state.get(FACING);
		BlockState backState = world.getBlockState(pos.offset(facing));
		if (hasStairShape(backState) && halfMatches(state, backState)) {
			Direction backFacing = backState.get(FACING);
			if (backFacing.getAxis() != facing.getAxis() && isDifferentOrientation(state, world, pos, backFacing.getOpposite())) {
				return backFacing == facing.rotateYCounterclockwise() ? StairShape.OUTER_LEFT : StairShape.OUTER_RIGHT;
			}
		}
		
		BlockState frontState = world.getBlockState(pos.offset(facing.getOpposite()));
		if (hasStairShape(frontState) && halfMatches(state, frontState)) {
			Direction frontFacing = frontState.get(FACING);
			if (frontFacing.getAxis() != facing.getAxis() && isDifferentOrientation(state, world, pos, frontFacing.getOpposite())) {
				return frontFacing == facing.rotateYCounterclockwise() ? StairShape.INNER_LEFT : StairShape.INNER_RIGHT;
			}
		}
		
		return StairShape.STRAIGHT;
	}
	
	private static boolean isDifferentOrientation(BlockState state, BlockView world, BlockPos pos, Direction dir) {
		BlockState otherState = world.getBlockState(pos.offset(dir));
		return !hasStairShape(otherState) || !hasStairShape(state) || (otherState.getProperties().contains(FACING) && state.contains(FACING) && (state.get(FACING) != otherState.get(FACING))) || !halfMatches(state, otherState);
	}
	
	private static boolean halfMatches(BlockState state, BlockState otherState) {
		return !state.contains(Properties.BLOCK_HALF) || !otherState.contains(Properties.BLOCK_HALF) || (invertHalfConditionally(state, BlockWithStairShape::shouldInvertHalf) == invertHalfConditionally(otherState, BlockWithStairShape::shouldInvertHalf));
	}
	
	private static BlockHalf invertHalfConditionally(BlockState state, Predicate<BlockState> condition) {
		return condition.test(state) ? invertHalf(state) : state.get(Properties.BLOCK_HALF);
	}
	
	private static BlockHalf invertHalf(BlockState state) {
		return state.get(Properties.BLOCK_HALF) == BlockHalf.BOTTOM ? BlockHalf.TOP : BlockHalf.BOTTOM;
	}
	
	private static boolean shouldInvertHalf(BlockState state) {
		if (state.getBlock() instanceof BlockWithStairShape block) {
			return block.shouldInvertHalf();
		} else {
			return false;
		}
	}
	
	private static boolean hasStairShape(BlockState state) {
		return state.getBlock() instanceof BlockWithStairShape || state.getBlock() instanceof StairsBlock;
	}
	
	protected abstract boolean shouldInvertHalf();
	
	@Override
	protected BlockState rotate(BlockState state, BlockRotation rotation) {
		return state.with(FACING, rotation.rotate(state.get(FACING)));
	}
	
	@SuppressWarnings("incomplete-switch")
	@Override
	protected BlockState mirror(BlockState state, BlockMirror mirror) {
		Direction direction = state.get(FACING);
		StairShape stairShape = state.get(SHAPE);
		switch (mirror) {
			case LEFT_RIGHT:
				if (direction.getAxis() == Direction.Axis.Z) {
					switch (stairShape) {
						case INNER_LEFT:
							return state.rotate(BlockRotation.CLOCKWISE_180).with(SHAPE, StairShape.INNER_RIGHT);
						case INNER_RIGHT:
							return state.rotate(BlockRotation.CLOCKWISE_180).with(SHAPE, StairShape.INNER_LEFT);
						case OUTER_LEFT:
							return state.rotate(BlockRotation.CLOCKWISE_180).with(SHAPE, StairShape.OUTER_RIGHT);
						case OUTER_RIGHT:
							return state.rotate(BlockRotation.CLOCKWISE_180).with(SHAPE, StairShape.OUTER_LEFT);
						default:
							return state.rotate(BlockRotation.CLOCKWISE_180);
					}
				}
				break;
			case FRONT_BACK:
				if (direction.getAxis() == Direction.Axis.X) {
					switch (stairShape) {
						case INNER_LEFT:
							return state.rotate(BlockRotation.CLOCKWISE_180).with(SHAPE, StairShape.INNER_LEFT);
						case INNER_RIGHT:
							return state.rotate(BlockRotation.CLOCKWISE_180).with(SHAPE, StairShape.INNER_RIGHT);
						case OUTER_LEFT:
							return state.rotate(BlockRotation.CLOCKWISE_180).with(SHAPE, StairShape.OUTER_RIGHT);
						case OUTER_RIGHT:
							return state.rotate(BlockRotation.CLOCKWISE_180).with(SHAPE, StairShape.OUTER_LEFT);
						case STRAIGHT:
							return state.rotate(BlockRotation.CLOCKWISE_180);
					}
				}
		}
		
		return super.mirror(state, mirror);
	}
	
	@Override
	protected abstract void appendProperties(Builder<Block, BlockState> builder);
}