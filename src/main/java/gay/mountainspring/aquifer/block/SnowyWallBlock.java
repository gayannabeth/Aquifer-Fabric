package gay.mountainspring.aquifer.block;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.MapCodec;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.WallBlock;
import net.minecraft.block.enums.WallShape;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;

public class SnowyWallBlock extends WallBlock {
	public static final MapCodec<WallBlock> CODEC = createCodec(SnowyWallBlock::new);
	public static final BooleanProperty SNOWY = Properties.SNOWY;
	
	private final Map<BlockState, VoxelShape> shapeMap;
	private final Map<BlockState, VoxelShape> collisionShapeMap;
	
	@Override
	public MapCodec<WallBlock> getCodec() {
		return CODEC;
	}
	
	private static VoxelShape getVoxelShape(VoxelShape base, WallShape wallShape, VoxelShape tall, VoxelShape low) {
		if (wallShape == WallShape.TALL) {
			return VoxelShapes.union(base, low);
		} else {
			return wallShape == WallShape.LOW ? VoxelShapes.union(base, tall) : base;
		}
	}
	
	private Map<BlockState, VoxelShape> getShapeMap(float f, float g, float h, float i, float j, float k) {
		float l = 8.0F - f;
		float m = 8.0F + f;
		float n = 8.0F - g;
		float o = 8.0F + g;
		VoxelShape voxelShape = Block.createCuboidShape(l, 0.0, l, m, h, m);
		VoxelShape voxelShape2 = Block.createCuboidShape(n, i, 0.0, o, j, o);
		VoxelShape voxelShape3 = Block.createCuboidShape(n, i, n, o, j, 16.0);
		VoxelShape voxelShape4 = Block.createCuboidShape(0.0, i, n, o, j, o);
		VoxelShape voxelShape5 = Block.createCuboidShape(n, i, n, 16.0, j, o);
		VoxelShape voxelShape6 = Block.createCuboidShape(n, i, 0.0, o, k, o);
		VoxelShape voxelShape7 = Block.createCuboidShape(n, i, n, o, k, 16.0);
		VoxelShape voxelShape8 = Block.createCuboidShape(0.0, i, n, o, k, o);
		VoxelShape voxelShape9 = Block.createCuboidShape(n, i, n, 16.0, k, o);
		ImmutableMap.Builder<BlockState, VoxelShape> builder = ImmutableMap.builder();
		
		for (Boolean boolean_ : UP.getValues()) {
			for (WallShape wallShape : EAST_SHAPE.getValues()) {
				for (WallShape wallShape2 : NORTH_SHAPE.getValues()) {
					for (WallShape wallShape3 : WEST_SHAPE.getValues()) {
						for (WallShape wallShape4 : SOUTH_SHAPE.getValues()) {
							VoxelShape voxelShape10 = VoxelShapes.empty();
							voxelShape10 = getVoxelShape(voxelShape10, wallShape, voxelShape5, voxelShape9);
							voxelShape10 = getVoxelShape(voxelShape10, wallShape3, voxelShape4, voxelShape8);
							voxelShape10 = getVoxelShape(voxelShape10, wallShape2, voxelShape2, voxelShape6);
							voxelShape10 = getVoxelShape(voxelShape10, wallShape4, voxelShape3, voxelShape7);
							if (boolean_) {
								voxelShape10 = VoxelShapes.union(voxelShape10, voxelShape);
							}

							BlockState blockState = this.getDefaultState()
								.with(UP, boolean_)
								.with(EAST_SHAPE, wallShape)
								.with(WEST_SHAPE, wallShape3)
								.with(NORTH_SHAPE, wallShape2)
								.with(SOUTH_SHAPE, wallShape4);
							builder.put(blockState.with(WATERLOGGED, false).with(SNOWY, false), voxelShape10);
							builder.put(blockState.with(WATERLOGGED, true).with(SNOWY, false), voxelShape10);
							builder.put(blockState.with(WATERLOGGED, false).with(SNOWY, true), voxelShape10);
							builder.put(blockState.with(WATERLOGGED, true).with(SNOWY, true), voxelShape10);
						}
					}
				}
			}
		}

		return builder.build();
	}
	
	public SnowyWallBlock(Settings settings) {
		super(settings);
		this.setDefaultState(this.getDefaultState().with(SNOWY, false));

		this.shapeMap = this.getShapeMap(4.0F, 3.0F, 16.0F, 0.0F, 14.0F, 16.0F);
		this.collisionShapeMap = this.getShapeMap(4.0F, 3.0F, 24.0F, 0.0F, 24.0F, 24.0F);
	}
	
	@Override
	protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		return direction == Direction.UP ? super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos).with(SNOWY, isSnow(neighborState)) : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
	}
	
	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		BlockState aboveState = ctx.getWorld().getBlockState(ctx.getBlockPos().up());
		return super.getPlacementState(ctx).with(SNOWY, isSnow(aboveState));
	}
	
	private static boolean isSnow(BlockState state) {
		return state.isIn(BlockTags.SNOW);
	}
	
	@Override
	protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return (VoxelShape)this.shapeMap.get(state);
	}
	
	@Override
	protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return (VoxelShape)this.collisionShapeMap.get(state);
	}
	
	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		super.appendProperties(builder);
		builder.add(SNOWY);
	}
}