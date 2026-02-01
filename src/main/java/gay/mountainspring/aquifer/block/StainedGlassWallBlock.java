package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.Stainable;
import net.minecraft.block.WallBlock;
import net.minecraft.util.DyeColor;

public class StainedGlassWallBlock extends TransparentWallBlock implements Stainable {
	public static final MapCodec<WallBlock> CODEC = RecordCodecBuilder.mapCodec(
		instance -> instance.group(
			DyeColor.CODEC.fieldOf("color").forGetter(block -> ((StainedGlassWallBlock)block).getColor()),
			createSettingsCodec()
		).apply(instance, StainedGlassWallBlock::new)
	);
	
	private final DyeColor color;
	
	@Override
	public MapCodec<WallBlock> getCodec() {
		return CODEC;
	}
	
	public StainedGlassWallBlock(DyeColor color, Settings settings) {
		super(settings);
		this.color = color;
	}
	
	@Override
	public DyeColor getColor() {
		return this.color;
	}
}