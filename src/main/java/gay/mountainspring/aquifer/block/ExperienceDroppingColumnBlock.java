package gay.mountainspring.aquifer.block;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;

public class ExperienceDroppingColumnBlock extends ColumnBlock {
	public static final MapCodec<ExperienceDroppingColumnBlock> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(IntProvider.createValidatingCodec(0, 10).fieldOf("experience").forGetter(block -> block.experienceDropped),
					Codec.INT.fieldOf("radius").forGetter(block -> block.radius),createSettingsCodec())
			.apply(instance, ExperienceDroppingColumnBlock::new));
	
	protected final IntProvider experienceDropped;
	
	@Override
	public MapCodec<? extends ExperienceDroppingColumnBlock> getCodec() {
		return CODEC;
	}
	
	public ExperienceDroppingColumnBlock(IntProvider experienceDropped, int radius, Settings settings) {
		super(radius, settings);
		this.experienceDropped = experienceDropped;
	}
	
	public ExperienceDroppingColumnBlock(int radius, Settings settings) {
		this(ConstantIntProvider.create(0), radius, settings);
	}
	
	@Override
	protected void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack tool, boolean dropExperience) {
		super.onStacksDropped(state, world, pos, tool, dropExperience);
		if (dropExperience) {
			this.dropExperienceWhenMined(world, pos, tool, this.experienceDropped);
		}
	}
}