package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.block.Oxidizable;
import net.minecraft.block.WallSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class OxidizableWallSignBlock extends WallSignBlock implements Oxidizable {
	public static final MapCodec<WallSignBlock> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(Oxidizable.OxidationLevel.CODEC.fieldOf("weathering_state").forGetter(block -> ((OxidizableWallSignBlock) block).getDegradationLevel()),
					WoodType.CODEC.fieldOf("wood_type").forGetter(WoodTyped::aquifer$getWoodType),
					createSettingsCodec())
			.apply(instance, OxidizableWallSignBlock::new));
	
	private final Oxidizable.OxidationLevel oxidzationLevel;
	
	@Override
	public MapCodec<WallSignBlock> getCodec() {
		return CODEC;
	}
	
	public OxidizableWallSignBlock(Oxidizable.OxidationLevel oxidationLevel, WoodType woodType, Settings settings) {
		super(woodType, settings);
		this.oxidzationLevel = oxidationLevel;
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
		return this.oxidzationLevel;
	}
}