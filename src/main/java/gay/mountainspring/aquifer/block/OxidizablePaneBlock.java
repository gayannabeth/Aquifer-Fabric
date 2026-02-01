package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Degradable;
import net.minecraft.block.Oxidizable;
import net.minecraft.block.PaneBlock;
import net.minecraft.item.HoneycombItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;

public class OxidizablePaneBlock extends PaneBlock implements Oxidizable {
	public static final MapCodec<OxidizablePaneBlock> CODEC = RecordCodecBuilder.mapCodec(
		instance -> instance.group(Oxidizable.OxidationLevel.CODEC.fieldOf("weathering_state").forGetter(Degradable::getDegradationLevel), createSettingsCodec())
			.apply(instance, OxidizablePaneBlock::new)
	);
	
	private final Oxidizable.OxidationLevel oxidationLevel;
	
	@Override
	public MapCodec<? extends OxidizablePaneBlock> getCodec() {
		return CODEC;
	}
	
	public OxidizablePaneBlock(Oxidizable.OxidationLevel oxidationLevel, Settings settings) {
		super(settings);
		this.oxidationLevel = oxidationLevel;
	}
	
	@Override
	protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		this.tickDegradation(state, world, pos, random);
	}
	
	@Override
	protected boolean hasRandomTicks(BlockState state) {
		return Oxidizable.getIncreasedOxidationBlock(state.getBlock()).isPresent();
	}
	
	@Override
	public OxidationLevel getDegradationLevel() {
		return this.oxidationLevel;
	}
	
	@Override
	protected boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
		Block waxed = HoneycombItem.UNWAXED_TO_WAXED_BLOCKS.get().get(this);
		
		if (waxed != null && stateFrom.isOf(waxed)) {
			if (!direction.getAxis().isHorizontal()) {
				return true;
			}
			
			if (state.get(FACING_PROPERTIES.get(direction)) && stateFrom.getProperties().contains(FACING_PROPERTIES.get(direction.getOpposite())) && stateFrom.get(FACING_PROPERTIES.get(direction.getOpposite()))) {
				return true;
			}
		}
		
		return super.isSideInvisible(state, stateFrom, direction);
	}
}