package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.Stainable;
import net.minecraft.util.DyeColor;

public class StainedGlassVerticalSlabBlock extends TransparentVerticalSlabBlock implements Stainable {
	public static final MapCodec<StainedGlassVerticalSlabBlock> CODEC = RecordCodecBuilder.mapCodec(
		instance -> instance.group(
			DyeColor.CODEC.fieldOf("color").forGetter(StainedGlassVerticalSlabBlock::getColor),
			createSettingsCodec()
		).apply(instance, StainedGlassVerticalSlabBlock::new)
	);
	
	private final DyeColor color;
	
	@Override
	public MapCodec<? extends StainedGlassVerticalSlabBlock> getCodec() {
		return CODEC;
	}
	
	public StainedGlassVerticalSlabBlock(DyeColor color, Settings settings) {
		super(settings);
		this.color = color;
	}
	
	@Override
	public DyeColor getColor() {
		return this.color;
	}
}