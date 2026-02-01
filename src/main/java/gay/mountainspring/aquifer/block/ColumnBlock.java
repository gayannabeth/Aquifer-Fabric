package gay.mountainspring.aquifer.block;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import gay.mountainspring.aquifer.block.state.AquiferBlockStateProperties;
import gay.mountainspring.aquifer.util.BooleanPair;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class ColumnBlock extends PillarBlock implements Waterloggable {
	public static final MapCodec<ColumnBlock> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(
					Codec.INT.fieldOf("radius").forGetter(block -> block.radius),
					createSettingsCodec())
			.apply(instance, ColumnBlock::new));
	
	public static final BooleanProperty BOTTOM = Properties.BOTTOM;
	public static final BooleanProperty TOP = AquiferBlockStateProperties.TOP;
	public static final Map<Direction.AxisDirection, BooleanProperty> PLATE_PROPERTIES = ImmutableMap.of(Direction.AxisDirection.NEGATIVE, BOTTOM, Direction.AxisDirection.POSITIVE, TOP);
	
	public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
	
	protected final int radius;
	
	public static final VoxelShape BOTTOM_PLATE_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
	public static final VoxelShape TOP_PLATE_SHAPE = Block.createCuboidShape(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	public static final VoxelShape NORTH_PLATE_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D);
	public static final VoxelShape SOUTH_PLATE_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D);
	public static final VoxelShape WEST_PLATE_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 16.0D);
	public static final VoxelShape EAST_PLATE_SHAPE = Block.createCuboidShape(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	
	public static final Int2ObjectMap<VoxelShape> COLUMN_SHAPES_X = new Int2ObjectOpenHashMap<>();
	public static final Int2ObjectMap<VoxelShape> FULL_COLUMN_SHAPES_X = new Int2ObjectOpenHashMap<>();
	public static final Int2ObjectMap<VoxelShape> BOTTOM_COLUMN_SHAPES_X = new Int2ObjectOpenHashMap<>();
	public static final Int2ObjectMap<VoxelShape> TOP_COLUMN_SHAPES_X = new Int2ObjectOpenHashMap<>();
	public static final Int2ObjectMap<VoxelShape> COLUMN_SHAPES_Y = new Int2ObjectOpenHashMap<>();
	public static final Int2ObjectMap<VoxelShape> FULL_COLUMN_SHAPES_Y = new Int2ObjectOpenHashMap<>();
	public static final Int2ObjectMap<VoxelShape> BOTTOM_COLUMN_SHAPES_Y = new Int2ObjectOpenHashMap<>();
	public static final Int2ObjectMap<VoxelShape> TOP_COLUMN_SHAPES_Y = new Int2ObjectOpenHashMap<>();
	public static final Int2ObjectMap<VoxelShape> COLUMN_SHAPES_Z = new Int2ObjectOpenHashMap<>();
	public static final Int2ObjectMap<VoxelShape> FULL_COLUMN_SHAPES_Z = new Int2ObjectOpenHashMap<>();
	public static final Int2ObjectMap<VoxelShape> BOTTOM_COLUMN_SHAPES_Z = new Int2ObjectOpenHashMap<>();
	public static final Int2ObjectMap<VoxelShape> TOP_COLUMN_SHAPES_Z = new Int2ObjectOpenHashMap<>();
	
	@Override
	public MapCodec<? extends ColumnBlock> getCodec() {
		return CODEC;
	}
	
	public ColumnBlock(int radius, Settings settings) {
		super(settings);
		this.radius = radius;
		this.setDefaultState(this.getDefaultState().with(BOTTOM, true).with(TOP, true));
		COLUMN_SHAPES_X.computeIfAbsent(radius, r -> Block.createCuboidShape(0.0D, 8.0D - r, 8.0D - r, 16.0D, 8.0D + r, 8.0D + r));
		COLUMN_SHAPES_Y.computeIfAbsent(radius, r -> Block.createCuboidShape(8.0D - r, 0.0D, 8.0D - r, 8.0D + r, 16.0D, 8.0D + r));
		COLUMN_SHAPES_Z.computeIfAbsent(radius, r -> Block.createCuboidShape(8.0D - r, 8.0D - r, 0.0D, 8.0D + r, 8.0D + r, 16.0D));
		BOTTOM_COLUMN_SHAPES_X.computeIfAbsent(radius, r -> VoxelShapes.union(WEST_PLATE_SHAPE, COLUMN_SHAPES_X.get(r)));
		BOTTOM_COLUMN_SHAPES_Y.computeIfAbsent(radius, r -> VoxelShapes.union(BOTTOM_PLATE_SHAPE, COLUMN_SHAPES_Y.get(r)));
		BOTTOM_COLUMN_SHAPES_Z.computeIfAbsent(radius, r -> VoxelShapes.union(NORTH_PLATE_SHAPE, COLUMN_SHAPES_Z.get(r)));
		TOP_COLUMN_SHAPES_X.computeIfAbsent(radius, r -> VoxelShapes.union(EAST_PLATE_SHAPE, COLUMN_SHAPES_X.get(r)));
		TOP_COLUMN_SHAPES_Y.computeIfAbsent(radius, r -> VoxelShapes.union(TOP_PLATE_SHAPE, COLUMN_SHAPES_Y.get(r)));
		TOP_COLUMN_SHAPES_Z.computeIfAbsent(radius, r -> VoxelShapes.union(SOUTH_PLATE_SHAPE, COLUMN_SHAPES_Z.get(r)));
		FULL_COLUMN_SHAPES_X.computeIfAbsent(radius, r -> VoxelShapes.union(WEST_PLATE_SHAPE, EAST_PLATE_SHAPE, COLUMN_SHAPES_X.get(r)));
		FULL_COLUMN_SHAPES_Y.computeIfAbsent(radius, r -> VoxelShapes.union(BOTTOM_PLATE_SHAPE, TOP_PLATE_SHAPE, COLUMN_SHAPES_Y.get(r)));
		FULL_COLUMN_SHAPES_Z.computeIfAbsent(radius, r -> VoxelShapes.union(NORTH_PLATE_SHAPE, SOUTH_PLATE_SHAPE, COLUMN_SHAPES_Z.get(r)));
	}
	
	@Override
	protected void appendProperties(Builder<Block, BlockState> builder) {
		super.appendProperties(builder);
		builder.add(BOTTOM, TOP, WATERLOGGED);
	}
	
	@Override
	protected boolean canPathfindThrough(BlockState state, NavigationType type) {
		return false;
	}
	
	@Override
	protected FluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
	}
	
	@Override
	protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return switch (BooleanPair.of(state.get(BOTTOM), state.get(TOP))) {
			case FALSEFALSE -> getShape(state.get(AXIS), COLUMN_SHAPES_X, COLUMN_SHAPES_Y, COLUMN_SHAPES_Z);
			case FALSETRUE -> getShape(state.get(AXIS), TOP_COLUMN_SHAPES_X, TOP_COLUMN_SHAPES_Y, TOP_COLUMN_SHAPES_Z);
			case TRUEFALSE -> getShape(state.get(AXIS), BOTTOM_COLUMN_SHAPES_X, BOTTOM_COLUMN_SHAPES_Y, BOTTOM_COLUMN_SHAPES_Z);
			case TRUETRUE -> getShape(state.get(AXIS), FULL_COLUMN_SHAPES_X, FULL_COLUMN_SHAPES_Y, FULL_COLUMN_SHAPES_Z);
		};
	}
	
	private VoxelShape getShape(Direction.Axis axis, Int2ObjectMap<VoxelShape> xShapes, Int2ObjectMap<VoxelShape> yShapes, Int2ObjectMap<VoxelShape> zShapes) {
		return switch (axis) {
			case X ->  xShapes.get(this.radius);
			case Y -> yShapes.get(this.radius);
			case Z -> zShapes.get(this.radius);
		};
	}
	
	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		BlockState state = super.getPlacementState(ctx);
		World world = ctx.getWorld();
		BlockPos pos = ctx.getBlockPos();
		Direction.Axis axis = state.get(AXIS);
		BlockState bottomState = world.getBlockState(pos.offset(Direction.from(axis, Direction.AxisDirection.NEGATIVE)));
		BlockState topState = world.getBlockState(pos.offset(Direction.from(axis, Direction.AxisDirection.POSITIVE)));
		return state
				.with(BOTTOM, !(bottomState.getBlock() instanceof ColumnBlock bottomBlock && bottomBlock.radius == this.radius && bottomState.get(AXIS) == axis))
				.with(TOP, !(topState.getBlock() instanceof ColumnBlock topBlock && topBlock.radius == this.radius && topState.get(AXIS) == axis));
	}
	
	@Override
	protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		if (state.get(WATERLOGGED)) {
			world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
		}
		
		if (direction.getAxis() == state.get(AXIS)) {
			if (neighborState.getBlock() instanceof ColumnBlock block && block.radius == this.radius && state.get(AXIS) == neighborState.get(AXIS)) {
				BooleanProperty prop = PLATE_PROPERTIES.get(direction.getDirection());
				if (state.get(prop)) return state.with(prop, false);
			} else {
				BooleanProperty prop = PLATE_PROPERTIES.get(direction.getDirection());
				if (state.get(prop)) return state.with(prop, true);
			}
		}
		
		return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
	}
	
	@Override
	protected boolean hasSidedTransparency(BlockState state) {
		return true;
	}
	
	@Override
	protected BlockState rotate(BlockState state, BlockRotation rotation) {
		BlockState rotated = super.rotate(state, rotation);
		boolean bottom = state.get(BOTTOM);
		boolean top = state.get(TOP);
		
		switch (rotation) {
			case CLOCKWISE_180:
				return state.get(AXIS) == Direction.Axis.Y ? rotated : rotated.with(BOTTOM, top).with(TOP, bottom);
			case CLOCKWISE_90:
				return state.get(AXIS) == Direction.Axis.Z ? rotated.with(BOTTOM, top).with(TOP, bottom) : rotated;
			case COUNTERCLOCKWISE_90:
				return state.get(AXIS) == Direction.Axis.X ? rotated.with(BOTTOM, top).with(TOP, bottom) : rotated;
			default:
				return rotated;
		}
	}
	
	@Override
	protected BlockState mirror(BlockState state, BlockMirror mirror) {
		boolean bottom = state.get(BOTTOM);
		boolean top = state.get(TOP);
		
		switch (mirror) {
			case LEFT_RIGHT:
				if (state.get(AXIS) == Direction.Axis.Z) {
					return state.with(BOTTOM, top).with(TOP, bottom);
				}
				break;
			case FRONT_BACK:
				if (state.get(AXIS) == Direction.Axis.X) {
					return state.with(BOTTOM, top).with(TOP, bottom);
				}
				break;
			default:
				return super.mirror(state, mirror);
		}
		
		return super.mirror(state, mirror);
	}
}