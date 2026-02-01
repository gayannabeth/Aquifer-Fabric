package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.block.LadderBlock;
import net.minecraft.block.Oxidizable;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class OxidizableLadderBlock extends LadderBlock implements Oxidizable {
	public static final MapCodec<LadderBlock> CODEC = RecordCodecBuilder.mapCodec(
		instance -> instance.group(Oxidizable.OxidationLevel.CODEC.fieldOf("weathering_state").forGetter(block -> ((OxidizableLadderBlock) block).getDegradationLevel()), createSettingsCodec())
			.apply(instance, OxidizableLadderBlock::new)
	);
	
	private final Oxidizable.OxidationLevel oxidationLevel;
	
	@Override
	public MapCodec<LadderBlock> getCodec() {
		return CODEC;
	}
	
	public OxidizableLadderBlock(Oxidizable.OxidationLevel oxidationLevel, Settings settings) {
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
}