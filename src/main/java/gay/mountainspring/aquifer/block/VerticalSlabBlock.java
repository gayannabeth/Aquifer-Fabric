package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
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

public class VerticalSlabBlock extends BlockWithStairShape implements Waterloggable {
	public static final MapCodec<VerticalSlabBlock> CODEC = createCodec(VerticalSlabBlock::new);
	
	public static final VoxelShape SHAPE_NW = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 8.0D);
	public static final VoxelShape SHAPE_NE = Block.createCuboidShape(8.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D);
	public static final VoxelShape SHAPE_SW = Block.createCuboidShape(0.0D, 0.0D, 8.0D, 8.0D, 16.0D, 16.0D);
	public static final VoxelShape SHAPE_SE = Block.createCuboidShape(8.0D, 0.0D, 8.0D, 16.0D, 16.0D, 16.0D);
	
	public static final VoxelShape SHAPE_N = VoxelShapes.union(SHAPE_NW, SHAPE_NE);
	public static final VoxelShape SHAPE_S = VoxelShapes.union(SHAPE_SW, SHAPE_SE);
	public static final VoxelShape SHAPE_W = VoxelShapes.union(SHAPE_NW, SHAPE_SW);
	public static final VoxelShape SHAPE_E = VoxelShapes.union(SHAPE_NE, SHAPE_SE);
	
	public static final VoxelShape SHAPE_INNER_NW = VoxelShapes.union(SHAPE_NW, SHAPE_NE, SHAPE_SW);
	public static final VoxelShape SHAPE_INNER_NE = VoxelShapes.union(SHAPE_NW, SHAPE_NE, SHAPE_SE);
	public static final VoxelShape SHAPE_INNER_SW = VoxelShapes.union(SHAPE_NW, SHAPE_SW, SHAPE_SE);
	public static final VoxelShape SHAPE_INNER_SE = VoxelShapes.union(SHAPE_NE, SHAPE_SW, SHAPE_SE);
	
	@Override
	public MapCodec<? extends VerticalSlabBlock> getCodec() {
		return CODEC;
	}
	
	public VerticalSlabBlock(Settings settings) {
		super(settings);
		this.setDefaultState(this.stateManager.getDefaultState()
				.with(FACING, Direction.NORTH)
				.with(SHAPE, StairShape.STRAIGHT)
				.with(WATERLOGGED, false));
	}
	
	@Override
	protected boolean hasSidedTransparency(BlockState state) {
		return true;
	}
	
	@Override
	protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return switch (state.get(FACING)) {
			case NORTH -> getShape(state.get(SHAPE),
					SHAPE_INNER_NW,
					SHAPE_INNER_NE,
					SHAPE_NW,
					SHAPE_NE,
					SHAPE_N);
			case SOUTH -> getShape(state.get(SHAPE),
					SHAPE_INNER_SE,
					SHAPE_INNER_SW,
					SHAPE_SE,
					SHAPE_SW,
					SHAPE_S);
			case WEST -> getShape(state.get(SHAPE),
					SHAPE_INNER_SW,
					SHAPE_INNER_NW,
					SHAPE_SW,
					SHAPE_NW,
					SHAPE_W);
			case EAST -> getShape(state.get(SHAPE),
					SHAPE_INNER_NE,
					SHAPE_INNER_SE,
					SHAPE_NE,
					SHAPE_SE,
					SHAPE_E);
			default -> super.getOutlineShape(state, world, pos, context);
		};
	}
	
	private static VoxelShape getShape(StairShape shape, VoxelShape innerL, VoxelShape innerR, VoxelShape outerL, VoxelShape outerR, VoxelShape straight) {
		return switch (shape) {
			case INNER_LEFT -> innerL;
			case INNER_RIGHT -> innerR;
			case OUTER_LEFT -> outerL;
			case OUTER_RIGHT -> outerR;
			case STRAIGHT -> straight;
		};
	}
	
	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		BlockPos blockPos = ctx.getBlockPos();
		FluidState fluidState = ctx.getWorld().getFluidState(blockPos);
		BlockState blockState = this.getDefaultState()
				.with(FACING, ctx.getHorizontalPlayerFacing())
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
		return false;
	}
	
	@Override
	protected void appendProperties(Builder<Block, BlockState> builder) {
		builder.add(FACING, SHAPE, WATERLOGGED);
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