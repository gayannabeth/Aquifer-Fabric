package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.block.Stainable;
import net.minecraft.util.DyeColor;

public class StainedGlassStairsBlock extends TransparentStairsBlock implements Stainable {
	public static final MapCodec<StainedGlassStairsBlock> CODEC = RecordCodecBuilder.mapCodec(
		instance -> instance.group(
			DyeColor.CODEC.fieldOf("color").forGetter(StainedGlassStairsBlock::getColor),
			BlockState.CODEC.fieldOf("base_state").forGetter(block -> block.baseBlockState),
			createSettingsCodec()
		).apply(instance, StainedGlassStairsBlock::new)
	);
	
	private final DyeColor color;
	
	@Override
	public MapCodec<? extends StainedGlassStairsBlock> getCodec() {
		return CODEC;
	}
	
	public StainedGlassStairsBlock(DyeColor color, BlockState baseBlockState, Settings settings) {
		super(baseBlockState, settings);
		this.color = color;
	}
	
	@Override
	public DyeColor getColor() {
		return this.color;
	}
}