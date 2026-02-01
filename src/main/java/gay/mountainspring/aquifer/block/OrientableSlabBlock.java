package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class OrientableSlabBlock extends SlabBlock {
	public static final EnumProperty<Direction.Axis> AXIS = Properties.AXIS;
	
	public static final MapCodec<OrientableSlabBlock> CODEC = createCodec(OrientableSlabBlock::new);
	
	@Override
	public MapCodec<? extends OrientableSlabBlock> getCodec() {
		return CODEC;
	}
	
	public OrientableSlabBlock(Settings settings) {
		super(settings);
		this.setDefaultState(this.getDefaultState().with(AXIS, Direction.Axis.Y));
	}
	
	@Override
	protected void appendProperties(Builder<Block, BlockState> builder) {
		super.appendProperties(builder);
		builder.add(AXIS);
	}
	
	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		BlockPos pos = ctx.getBlockPos();
		World world = ctx.getWorld();
		BlockState state = world.getBlockState(pos);
		return state.isOf(this) ? super.getPlacementState(ctx) : super.getPlacementState(ctx).with(AXIS, ctx.getSide().getAxis());
	}
}