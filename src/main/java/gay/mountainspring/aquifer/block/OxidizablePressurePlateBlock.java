package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.BlockSetType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Oxidizable;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class OxidizablePressurePlateBlock extends PressurePlateBlock implements Oxidizable {
	public static final MapCodec<PressurePlateBlock> CODEC = RecordCodecBuilder.mapCodec(
		instance -> instance.group(Oxidizable.OxidationLevel.CODEC.fieldOf("weathering_state").forGetter(block -> ((OxidizablePressurePlateBlock) block).getDegradationLevel()),
				BlockSetType.CODEC.fieldOf("block_set_type").forGetter(BlockSetTyped::aquifer$getBlockSetType), createSettingsCodec())
			.apply(instance, OxidizablePressurePlateBlock::new)
	);
	
	private final Oxidizable.OxidationLevel oxidationLevel;
	
	@Override
	public MapCodec<PressurePlateBlock> getCodec() {
		return CODEC;
	}
	
	public OxidizablePressurePlateBlock(Oxidizable.OxidationLevel oxidationLevel, BlockSetType type, Settings settings) {
		super(type, settings);
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