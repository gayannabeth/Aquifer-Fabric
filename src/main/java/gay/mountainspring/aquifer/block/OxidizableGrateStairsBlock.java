package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.block.Degradable;
import net.minecraft.block.Oxidizable;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class OxidizableGrateStairsBlock extends TranslucentStairsBlock implements Oxidizable {
	public static final MapCodec<OxidizableGrateStairsBlock> CODEC = RecordCodecBuilder.mapCodec(
		instance -> instance.group(
				Oxidizable.OxidationLevel.CODEC.fieldOf("weathering_state").forGetter(Degradable::getDegradationLevel),
				BlockState.CODEC.fieldOf("base_state").forGetter(oxidizableStairsBlock -> oxidizableStairsBlock.baseBlockState),
				createSettingsCodec()
			)
			.apply(instance, OxidizableGrateStairsBlock::new)
	);
	
	private Oxidizable.OxidationLevel oxidationLevel;
	
	@Override
	public MapCodec<? extends OxidizableGrateStairsBlock> getCodec() {
		return CODEC;
	}
	
	public OxidizableGrateStairsBlock(Oxidizable.OxidationLevel oxidationLevel, BlockState baseBlockState, Settings settings) {
		super(baseBlockState, settings);
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