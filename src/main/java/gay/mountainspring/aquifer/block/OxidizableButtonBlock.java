package gay.mountainspring.aquifer.block;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.BlockSetType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ButtonBlock;
import net.minecraft.block.Oxidizable;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class OxidizableButtonBlock extends ButtonBlock implements Oxidizable {
	public static final MapCodec<ButtonBlock> CODEC = RecordCodecBuilder.mapCodec(
		instance -> instance.group(
				Oxidizable.OxidationLevel.CODEC.fieldOf("weathering_state").forGetter(block -> ((OxidizableButtonBlock) block).getDegradationLevel()),
				BlockSetType.CODEC.fieldOf("block_set_type").forGetter(BlockSetTyped::aquifer$getBlockSetType),
				Codec.intRange(1, 1024).fieldOf("ticks_to_stay_pressed").forGetter(block -> ((OxidizableButtonBlock) block).pressTicks),
				createSettingsCodec()
			)
			.apply(instance, OxidizableButtonBlock::new)
	);
	
	private final Oxidizable.OxidationLevel oxidationLevel;
	private final int pressTicks;
	
	@Override
	public MapCodec<ButtonBlock> getCodec() {
		return CODEC;
	}
	
	public OxidizableButtonBlock(Oxidizable.OxidationLevel oxidationLevel, BlockSetType blockSetType, Settings settings) {
		this (oxidationLevel, blockSetType, switch (oxidationLevel) {
			case UNAFFECTED -> 20;
			case EXPOSED -> 25;
			case WEATHERED -> 30;
			case OXIDIZED -> 35;
		}, settings);
	}
	
	public OxidizableButtonBlock(Oxidizable.OxidationLevel oxidationLevel, BlockSetType blockSetType, int pressTicks, Settings settings) {
		super(blockSetType, pressTicks, settings);
		this.oxidationLevel = oxidationLevel;
		this.pressTicks = pressTicks;
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