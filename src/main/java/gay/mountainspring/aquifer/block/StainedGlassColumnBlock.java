package gay.mountainspring.aquifer.block;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.Stainable;
import net.minecraft.util.DyeColor;

public class StainedGlassColumnBlock extends TransparentColumnBlock implements Stainable {
	public static final MapCodec<StainedGlassColumnBlock> CODEC = RecordCodecBuilder.mapCodec(
		instance -> instance.group(
			DyeColor.CODEC.fieldOf("color").forGetter(StainedGlassColumnBlock::getColor),
			Codec.INT.fieldOf("radius").forGetter(block -> block.radius),
			createSettingsCodec()
		).apply(instance, StainedGlassColumnBlock::new)
	);
	
	private final DyeColor color;
	
	@Override
	public MapCodec<? extends StainedGlassColumnBlock> getCodec() {
		return CODEC;
	}
	
	public StainedGlassColumnBlock(DyeColor color, int radius, Settings settings) {
		super(radius, settings);
		this.color = color;
	}
	
	@Override
	public DyeColor getColor() {
		return this.color;
	}
}