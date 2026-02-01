package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;

public class OrientableStairsBlock extends StairsBlock {
	public static final MapCodec<OrientableStairsBlock> CODEC = RecordCodecBuilder.mapCodec(
		instance -> instance.group(
			BlockState.CODEC.fieldOf("base_state").forGetter(block -> block.baseBlockState),
			createSettingsCodec()
		).apply(instance, OrientableStairsBlock::new)
	);
	
	@Override
	public MapCodec<? extends OrientableStairsBlock> getCodec() {
		return CODEC;
	}
	
	public static final EnumProperty<Direction.Axis> AXIS = Properties.AXIS;
	
	public OrientableStairsBlock(BlockState baseBlockState, Settings settings) {
		super(baseBlockState, settings);
		this.setDefaultState(this.getDefaultState().with(AXIS, Direction.Axis.Y));
	}
	
	@Override
	protected void appendProperties(Builder<Block, BlockState> builder) {
		super.appendProperties(builder);
		builder.add(AXIS);
	}
	
	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return super.getPlacementState(ctx).with(AXIS, ctx.getSide().getAxis());
	}
}