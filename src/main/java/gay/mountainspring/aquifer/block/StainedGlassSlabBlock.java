package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.Stainable;
import net.minecraft.util.DyeColor;

public class StainedGlassSlabBlock extends TransparentSlabBlock implements Stainable {
	public static final MapCodec<StainedGlassSlabBlock> CODEC = RecordCodecBuilder.mapCodec(
		instance -> instance.group(
			DyeColor.CODEC.fieldOf("color").forGetter(StainedGlassSlabBlock::getColor),
			createSettingsCodec()
		).apply(instance, StainedGlassSlabBlock::new)
	);
	
	private final DyeColor color;
	
	@Override
	public MapCodec<? extends StainedGlassSlabBlock> getCodec() {
		return CODEC;
	}
	
	public StainedGlassSlabBlock(DyeColor color, Settings settings) {
		super(settings);
		this.color = color;
	}
	
	@Override
	public DyeColor getColor() {
		return this.color;
	}
}