package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;

public class SnowySlabBlock extends SlabBlock {
	public static final MapCodec<SnowySlabBlock> CODEC = createCodec(SnowySlabBlock::new);
	public static final BooleanProperty SNOWY = Properties.SNOWY;
	
	@Override
	public MapCodec<? extends SnowySlabBlock> getCodec() {
		return CODEC;
	}
	
	public SnowySlabBlock(Settings settings) {
		super(settings);
		this.setDefaultState(this.getDefaultState().with(SNOWY, false));
	}
	
	@Override
	protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		return direction == Direction.UP ? state.with(SNOWY, this.shouldBeSnowy(state, neighborState)) : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
	}
	
	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		BlockState aboveState = ctx.getWorld().getBlockState(ctx.getBlockPos().up());
		BlockState placedState = super.getPlacementState(ctx);
		return placedState.with(SNOWY, this.shouldBeSnowy(placedState, aboveState));
	}
	
	private boolean shouldBeSnowy(BlockState slabState, BlockState aboveState) {
		return slabState.get(TYPE) != SlabType.BOTTOM && isSnow(aboveState);
	}
	
	private static boolean isSnow(BlockState state) {
		return state.isIn(BlockTags.SNOW);
	}
	
	@Override
	protected void appendProperties(Builder<Block, BlockState> builder) {
		super.appendProperties(builder);
		builder.add(SNOWY);
	}
}