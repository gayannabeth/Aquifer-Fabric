package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.Stainable;
import net.minecraft.util.DyeColor;

public class StainedGlassStepBlock extends TransparentStepBlock implements Stainable {
	public static final MapCodec<StainedGlassStepBlock> CODEC = RecordCodecBuilder.mapCodec(
		instance -> instance.group(
			DyeColor.CODEC.fieldOf("color").forGetter(StainedGlassStepBlock::getColor),
			createSettingsCodec()
		).apply(instance, StainedGlassStepBlock::new)
	);
	
	private final DyeColor color;
	
	@Override
	public MapCodec<? extends StainedGlassStepBlock> getCodec() {
		return CODEC;
	}
	
	public StainedGlassStepBlock(DyeColor color, Settings settings) {
		super(settings);
		this.color = color;
	}
	
	@Override
	public DyeColor getColor() {
		return this.color;
	}
}