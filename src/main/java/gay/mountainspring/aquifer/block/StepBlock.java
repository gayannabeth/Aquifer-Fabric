package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.StairShape;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;

public class StepBlock extends BlockWithStairShape implements Waterloggable {
	public static final MapCodec<StepBlock> CODEC = createCodec(StepBlock::new);
	
	public static final VoxelShape SHAPE_BOTTOM_NW = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 8.0D, 8.0D, 8.0D);
	public static final VoxelShape SHAPE_BOTTOM_NE = Block.createCuboidShape(8.0D, 0.0D, 0.0D, 16.0D, 8.0D, 8.0D);
	public static final VoxelShape SHAPE_BOTTOM_SW = Block.createCuboidShape(0.0D, 0.0D, 8.0D, 8.0D, 8.0D, 16.0D);
	public static final VoxelShape SHAPE_BOTTOM_SE = Block.createCuboidShape(8.0D, 0.0D, 8.0D, 16.0D, 8.0D, 16.0D);
	public static final VoxelShape SHAPE_TOP_NW = Block.createCuboidShape(0.0D, 8.0D, 0.0D, 8.0D, 16.0D, 8.0D);
	public static final VoxelShape SHAPE_TOP_NE = Block.createCuboidShape(8.0D, 8.0D, 0.0D, 16.0D, 16.0D, 8.0D);
	public static final VoxelShape SHAPE_TOP_SW = Block.createCuboidShape(0.0D, 8.0D, 8.0D, 8.0D, 16.0D, 16.0D);
	public static final VoxelShape SHAPE_TOP_SE = Block.createCuboidShape(8.0D, 8.0D, 8.0D, 16.0D, 16.0D, 16.0D);
	
	public static final VoxelShape SHAPE_BOTTOM_N = VoxelShapes.union(SHAPE_BOTTOM_NW, SHAPE_BOTTOM_NE);
	public static final VoxelShape SHAPE_BOTTOM_S = VoxelShapes.union(SHAPE_BOTTOM_SW, SHAPE_BOTTOM_SE);
	public static final VoxelShape SHAPE_BOTTOM_W = VoxelShapes.union(SHAPE_BOTTOM_NW, SHAPE_BOTTOM_SW);
	public static final VoxelShape SHAPE_BOTTOM_E = VoxelShapes.union(SHAPE_BOTTOM_NE, SHAPE_BOTTOM_SE);
	public static final VoxelShape SHAPE_TOP_N = VoxelShapes.union(SHAPE_TOP_NW, SHAPE_TOP_NE);
	public static final VoxelShape SHAPE_TOP_S = VoxelShapes.union(SHAPE_TOP_SW, SHAPE_TOP_SE);
	public static final VoxelShape SHAPE_TOP_W = VoxelShapes.union(SHAPE_TOP_NW, SHAPE_TOP_SW);
	public static final VoxelShape SHAPE_TOP_E = VoxelShapes.union(SHAPE_TOP_NE, SHAPE_TOP_SE);
	
	public static final VoxelShape SHAPE_BOTTOM_INNER_NW = VoxelShapes.union(SHAPE_BOTTOM_NW, SHAPE_BOTTOM_NE, SHAPE_BOTTOM_SW);
	public static final VoxelShape SHAPE_BOTTOM_INNER_NE = VoxelShapes.union(SHAPE_BOTTOM_NW, SHAPE_BOTTOM_NE, SHAPE_BOTTOM_SE);
	public static final VoxelShape SHAPE_BOTTOM_INNER_SW = VoxelShapes.union(SHAPE_BOTTOM_NW, SHAPE_BOTTOM_SW, SHAPE_BOTTOM_SE);
	public static final VoxelShape SHAPE_BOTTOM_INNER_SE = VoxelShapes.union(SHAPE_BOTTOM_NE, SHAPE_BOTTOM_SW, SHAPE_BOTTOM_SE);
	public static final VoxelShape SHAPE_TOP_INNER_NW = VoxelShapes.union(SHAPE_TOP_NW, SHAPE_TOP_NE, SHAPE_TOP_SW);
	public static final VoxelShape SHAPE_TOP_INNER_NE = VoxelShapes.union(SHAPE_TOP_NW, SHAPE_TOP_NE, SHAPE_TOP_SE);
	public static final VoxelShape SHAPE_TOP_INNER_SW = VoxelShapes.union(SHAPE_TOP_NW, SHAPE_TOP_SW, SHAPE_TOP_SE);
	public static final VoxelShape SHAPE_TOP_INNER_SE = VoxelShapes.union(SHAPE_TOP_NE, SHAPE_TOP_SW, SHAPE_TOP_SE);
	
	@Override
	protected MapCodec<? extends StepBlock> getCodec() {
		return CODEC;
	}
	
	public StepBlock(Settings settings) {
		super(settings);
		this.setDefaultState(this.stateManager.getDefaultState()
				.with(FACING, Direction.NORTH)
				.with(HALF, BlockHalf.BOTTOM)
				.with(SHAPE, StairShape.STRAIGHT)
				.with(WATERLOGGED, false));
	}
	
	@Override
	protected boolean hasSidedTransparency(BlockState state) {
		return true;
	}
	
	@Override
	protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return switch(state.get(FACING)) {
			case NORTH -> getShape(state.get(SHAPE), state.get(HALF),
					SHAPE_BOTTOM_INNER_NW,
					SHAPE_TOP_INNER_NW,
					SHAPE_BOTTOM_INNER_NE,
					SHAPE_TOP_INNER_NE,
					SHAPE_BOTTOM_NW,
					SHAPE_TOP_NW,
					SHAPE_BOTTOM_NE,
					SHAPE_TOP_NE,
					SHAPE_BOTTOM_N,
					SHAPE_TOP_N);
			case SOUTH -> getShape(state.get(SHAPE), state.get(HALF),
					SHAPE_BOTTOM_INNER_SE,
					SHAPE_TOP_INNER_SE,
					SHAPE_BOTTOM_INNER_SW,
					SHAPE_TOP_INNER_SW,
					SHAPE_BOTTOM_SE,
					SHAPE_TOP_SE,
					SHAPE_BOTTOM_SW,
					SHAPE_TOP_SW,
					SHAPE_BOTTOM_S,
					SHAPE_TOP_S);
			case WEST -> getShape(state.get(SHAPE), state.get(HALF),
					SHAPE_BOTTOM_INNER_SW,
					SHAPE_TOP_INNER_SW,
					SHAPE_BOTTOM_INNER_NW,
					SHAPE_TOP_INNER_NW,
					SHAPE_BOTTOM_SW,
					SHAPE_TOP_SW,
					SHAPE_BOTTOM_NW,
					SHAPE_TOP_NW,
					SHAPE_BOTTOM_W,
					SHAPE_TOP_W);
			case EAST -> getShape(state.get(SHAPE), state.get(HALF),
					SHAPE_BOTTOM_INNER_NE,
					SHAPE_TOP_INNER_NE,
					SHAPE_BOTTOM_INNER_SE,
					SHAPE_TOP_INNER_SE,
					SHAPE_BOTTOM_NE,
					SHAPE_TOP_NE,
					SHAPE_BOTTOM_SE,
					SHAPE_TOP_SE,
					SHAPE_BOTTOM_E,
					SHAPE_TOP_E);
			default -> super.getOutlineShape(state, world, pos, context);
		};
	}
	
	private VoxelShape getShape(StairShape shape, BlockHalf half, VoxelShape innerBL, VoxelShape innerTL, VoxelShape innerBR, VoxelShape innerTR, VoxelShape outerBL, VoxelShape outerTL, VoxelShape outerBR, VoxelShape outerTR, VoxelShape straightB, VoxelShape straightT) {
		return switch (shape) {
			case INNER_LEFT -> getShape(half, innerBL, innerTL);
			case INNER_RIGHT -> getShape(half, innerBR, innerTR);
			case OUTER_LEFT -> getShape(half, outerBL, outerTL);
			case OUTER_RIGHT -> getShape(half, outerBR, outerTR);
			case STRAIGHT -> getShape(half, straightB, straightT);
		};
	}
	
	private VoxelShape getShape(BlockHalf half, VoxelShape bottom, VoxelShape top) {
		return switch (half) {
			case BOTTOM -> bottom;
			case TOP -> top;
		};
	}
	
	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		Direction direction = ctx.getSide();
		BlockPos blockPos = ctx.getBlockPos();
		FluidState fluidState = ctx.getWorld().getFluidState(blockPos);
		BlockState blockState = this.getDefaultState()
			.with(FACING, ctx.getHorizontalPlayerFacing())
			.with(HALF, direction != Direction.DOWN && (direction == Direction.UP || !(ctx.getHitPos().y - blockPos.getY() > 0.5)) ? BlockHalf.BOTTOM : BlockHalf.TOP)
			.with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
		return blockState.with(SHAPE, getStairShape(blockState, ctx.getWorld(), blockPos));
	}
	
	@Override
	protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		if ((Boolean)state.get(WATERLOGGED)) {
			world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
		}
		
		return direction.getAxis().isHorizontal()
			? state.with(SHAPE, getStairShape(state, world, pos))
			: super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
	}
	
	@Override
	protected boolean shouldInvertHalf() {
		return true;
	}
	
	@Override
	protected void appendProperties(Builder<Block, BlockState> builder) {
		builder.add(FACING, HALF, SHAPE, WATERLOGGED);
	}
	
	@Override
	protected FluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
	}
	
	@Override
	protected boolean canPathfindThrough(BlockState state, NavigationType type) {
		return false;
	}
}
