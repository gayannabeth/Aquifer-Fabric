package gay.mountainspring.aquifer.block;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;

public class SnowyColumnBlock extends ColumnBlock {
	public static final MapCodec<SnowyColumnBlock> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(
					Codec.INT.fieldOf("radius").forGetter(block -> block.radius),
					createSettingsCodec())
			.apply(instance, SnowyColumnBlock::new));
	public static final BooleanProperty SNOWY = Properties.SNOWY;
	
	@Override
	public MapCodec<? extends SnowyColumnBlock> getCodec() {
		return CODEC;
	}
	
	public SnowyColumnBlock(int radius, Settings settings) {
		super(radius, settings);
		this.setDefaultState(this.getDefaultState().with(SNOWY, false));
	}
	
	@Override
	protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		BlockState state2 = super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
		return direction == Direction.UP ? state2.with(SNOWY, this.shouldBeSnowy(state, neighborState)) : state2;
	}
	
	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		BlockState aboveState = ctx.getWorld().getBlockState(ctx.getBlockPos().up());
		BlockState placedState = super.getPlacementState(ctx);
		return placedState.with(SNOWY, this.shouldBeSnowy(placedState, aboveState));
	}
	
	private boolean shouldBeSnowy(BlockState state, BlockState aboveState) {
		return (state.get(AXIS) == Direction.Axis.Y || state.get(BOTTOM) || state.get(TOP)) && isSnow(aboveState);
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