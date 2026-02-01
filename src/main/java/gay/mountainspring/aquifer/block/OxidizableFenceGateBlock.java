package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.Oxidizable;
import net.minecraft.block.WoodType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class OxidizableFenceGateBlock extends AquiferFenceGateBlock implements Oxidizable {
	public static final MapCodec<FenceGateBlock> CODEC = RecordCodecBuilder.mapCodec(
		instance -> instance.group(Oxidizable.OxidationLevel.CODEC.fieldOf("weathering_state").forGetter(block -> ((OxidizableFenceGateBlock) block).getDegradationLevel()),
				WoodType.CODEC.fieldOf("wood_type").forGetter(WoodTyped::aquifer$getWoodType), createSettingsCodec())
			.apply(instance, OxidizableFenceGateBlock::new)
	);
	
	private final Oxidizable.OxidationLevel oxidationLevel;
	
	@Override
	public MapCodec<FenceGateBlock> getCodec() {
		return CODEC;
	}
	
	public OxidizableFenceGateBlock(Oxidizable.OxidationLevel oxidationLevel, WoodType type, Settings settings) {
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