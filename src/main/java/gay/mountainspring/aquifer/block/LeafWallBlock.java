package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;

import gay.mountainspring.aquifer.tag.AquiferTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.PaneBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.block.enums.WallShape;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.ParticleUtil;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.property.Property;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class LeafWallBlock extends WallBlock {
	public static final MapCodec<WallBlock> CODEC = createCodec(LeafWallBlock::new);
	private static final VoxelShape TALL_POST_SHAPE = Block.createCuboidShape(7.0, 0.0, 7.0, 9.0, 16.0, 9.0);
	private static final VoxelShape TALL_NORTH_SHAPE = Block.createCuboidShape(7.0, 0.0, 0.0, 9.0, 16.0, 9.0);
	private static final VoxelShape TALL_SOUTH_SHAPE = Block.createCuboidShape(7.0, 0.0, 7.0, 9.0, 16.0, 16.0);
	private static final VoxelShape TALL_WEST_SHAPE = Block.createCuboidShape(0.0, 0.0, 7.0, 9.0, 16.0, 9.0);
	private static final VoxelShape TALL_EAST_SHAPE = Block.createCuboidShape(7.0, 0.0, 7.0, 16.0, 16.0, 9.0);
	
	@Override
	public MapCodec<WallBlock> getCodec() {
		return CODEC;
	}
	
	public LeafWallBlock(Settings settings) {
		super(settings);
	}
	
	@Override
	public VoxelShape getSidesShape(BlockState state, BlockView world, BlockPos pos) {
		return VoxelShapes.empty();
	}
	
	@Override
	public int getOpacity(BlockState state, BlockView world, BlockPos pos) {
		return 1;
	}
	
	@Override
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
		if (world.hasRain(pos.up()) && random.nextInt(15) == 1) {
			BlockPos down = pos.down();
			BlockState belowState = world.getBlockState(down);
			if (!belowState.isOpaque() || !belowState.isSideSolidFullSquare(world, pos, Direction.UP)) {
				ParticleUtil.spawnParticle(world, pos, random, ParticleTypes.DRIPPING_WATER);
			}
		}
	}
	
	/*
	 * copied from WallBlock
	 */
	
	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		WorldView worldView = ctx.getWorld();
		BlockPos blockPos = ctx.getBlockPos();
		FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
		BlockPos blockPos2 = blockPos.north();
		BlockPos blockPos3 = blockPos.east();
		BlockPos blockPos4 = blockPos.south();
		BlockPos blockPos5 = blockPos.west();
		BlockPos blockPos6 = blockPos.up();
		BlockState blockState = worldView.getBlockState(blockPos2);
		BlockState blockState2 = worldView.getBlockState(blockPos3);
		BlockState blockState3 = worldView.getBlockState(blockPos4);
		BlockState blockState4 = worldView.getBlockState(blockPos5);
		BlockState blockState5 = worldView.getBlockState(blockPos6);
		boolean bl = this.shouldConnectTo(blockState, blockState.isSideSolidFullSquare(worldView, blockPos2, Direction.SOUTH), Direction.SOUTH);
		boolean bl2 = this.shouldConnectTo(blockState2, blockState2.isSideSolidFullSquare(worldView, blockPos3, Direction.WEST), Direction.WEST);
		boolean bl3 = this.shouldConnectTo(blockState3, blockState3.isSideSolidFullSquare(worldView, blockPos4, Direction.NORTH), Direction.NORTH);
		boolean bl4 = this.shouldConnectTo(blockState4, blockState4.isSideSolidFullSquare(worldView, blockPos5, Direction.EAST), Direction.EAST);
		BlockState blockState6 = this.getDefaultState().with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
		return this.getStateWith(worldView, blockState6, blockPos6, blockState5, bl, bl2, bl3, bl4);
	}

	@Override
	protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		if ((Boolean)state.get(WATERLOGGED)) {
			world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
		}

		if (direction == Direction.DOWN) {
			return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
		} else {
			return direction == Direction.UP
				? this.getStateAt(world, state, neighborPos, neighborState)
				: this.getStateWithNeighbor(world, pos, state, neighborPos, neighborState, direction);
		}
	}
	
	private boolean shouldConnectTo(BlockState state, boolean faceFullSquare, Direction side) {
		Block block = state.getBlock();
		boolean bl = block instanceof FenceGateBlock && FenceGateBlock.canWallConnect(state, side);
		return state.isIn(BlockTags.WALLS) || !cannotConnect(state) && faceFullSquare || block instanceof PaneBlock || bl;
	}
	
	public static boolean cannotConnect(BlockState state) {
		return state.isIn(AquiferTags.Blocks.CANNOT_CONNECT_TO) && !(state.isIn(AquiferTags.Blocks.LEAF_SLABS) || state.isIn(AquiferTags.Blocks.LEAF_STAIRS) || state.isIn(AquiferTags.Blocks.LEAF_WALLS) || state.isIn(BlockTags.LEAVES));
	}
	
	private static boolean isConnected(BlockState state, Property<WallShape> property) {
		return state.get(property) != WallShape.NONE;
	}
	
	private static boolean shouldUseTallShape(VoxelShape aboveShape, VoxelShape tallShape) {
		return !VoxelShapes.matchesAnywhere(tallShape, aboveShape, BooleanBiFunction.ONLY_FIRST);
	}
	
	private BlockState getStateAt(WorldView world, BlockState state, BlockPos pos, BlockState aboveState) {
		boolean bl = isConnected(state, NORTH_SHAPE);
		boolean bl2 = isConnected(state, EAST_SHAPE);
		boolean bl3 = isConnected(state, SOUTH_SHAPE);
		boolean bl4 = isConnected(state, WEST_SHAPE);
		return this.getStateWith(world, state, pos, aboveState, bl, bl2, bl3, bl4);
	}
	
	private BlockState getStateWithNeighbor(WorldView world, BlockPos pos, BlockState state, BlockPos neighborPos, BlockState neighborState, Direction direction) {
		Direction direction2 = direction.getOpposite();
		boolean bl = direction == Direction.NORTH
			? this.shouldConnectTo(neighborState, neighborState.isSideSolidFullSquare(world, neighborPos, direction2), direction2)
			: isConnected(state, NORTH_SHAPE);
		boolean bl2 = direction == Direction.EAST
			? this.shouldConnectTo(neighborState, neighborState.isSideSolidFullSquare(world, neighborPos, direction2), direction2)
			: isConnected(state, EAST_SHAPE);
		boolean bl3 = direction == Direction.SOUTH
			? this.shouldConnectTo(neighborState, neighborState.isSideSolidFullSquare(world, neighborPos, direction2), direction2)
			: isConnected(state, SOUTH_SHAPE);
		boolean bl4 = direction == Direction.WEST
			? this.shouldConnectTo(neighborState, neighborState.isSideSolidFullSquare(world, neighborPos, direction2), direction2)
			: isConnected(state, WEST_SHAPE);
		BlockPos blockPos = pos.up();
		BlockState blockState = world.getBlockState(blockPos);
		return this.getStateWith(world, state, blockPos, blockState, bl, bl2, bl3, bl4);
	}
	
	private BlockState getStateWith(WorldView world, BlockState state, BlockPos pos, BlockState aboveState, boolean north, boolean east, boolean south, boolean west) {
		VoxelShape voxelShape = aboveState.getCollisionShape(world, pos).getFace(Direction.DOWN);
		BlockState blockState = this.getStateWith(state, north, east, south, west, voxelShape);
		return blockState.with(UP, this.shouldHavePost(blockState, aboveState, voxelShape));
	}
	
	private boolean shouldHavePost(BlockState state, BlockState aboveState, VoxelShape aboveShape) {
		boolean bl = aboveState.getBlock() instanceof WallBlock && (Boolean)aboveState.get(UP);
		if (bl) {
			return true;
		} else {
			WallShape wallShape = state.get(NORTH_SHAPE);
			WallShape wallShape2 = state.get(SOUTH_SHAPE);
			WallShape wallShape3 = state.get(EAST_SHAPE);
			WallShape wallShape4 = state.get(WEST_SHAPE);
			boolean bl2 = wallShape2 == WallShape.NONE;
			boolean bl3 = wallShape4 == WallShape.NONE;
			boolean bl4 = wallShape3 == WallShape.NONE;
			boolean bl5 = wallShape == WallShape.NONE;
			boolean bl6 = bl5 && bl2 && bl3 && bl4 || bl5 != bl2 || bl3 != bl4;
			if (bl6) {
				return true;
			} else {
				boolean bl7 = wallShape == WallShape.TALL && wallShape2 == WallShape.TALL || wallShape3 == WallShape.TALL && wallShape4 == WallShape.TALL;
				return bl7 ? false : aboveState.isIn(BlockTags.WALL_POST_OVERRIDE) || shouldUseTallShape(aboveShape, TALL_POST_SHAPE);
			}
		}
	}

	private BlockState getStateWith(BlockState state, boolean north, boolean east, boolean south, boolean west, VoxelShape aboveShape) {
		return state.with(NORTH_SHAPE, this.getWallShape(north, aboveShape, TALL_NORTH_SHAPE))
			.with(EAST_SHAPE, this.getWallShape(east, aboveShape, TALL_EAST_SHAPE))
			.with(SOUTH_SHAPE, this.getWallShape(south, aboveShape, TALL_SOUTH_SHAPE))
			.with(WEST_SHAPE, this.getWallShape(west, aboveShape, TALL_WEST_SHAPE));
	}

	private WallShape getWallShape(boolean connected, VoxelShape aboveShape, VoxelShape tallShape) {
		if (connected) {
			return shouldUseTallShape(aboveShape, tallShape) ? WallShape.TALL : WallShape.LOW;
		} else {
			return WallShape.NONE;
		}
	}
}