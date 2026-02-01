package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;

public class ExperienceDroppingVerticalSlabBlock extends VerticalSlabBlock {
	public static final MapCodec<ExperienceDroppingVerticalSlabBlock> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(IntProvider.createValidatingCodec(0, 10).fieldOf("experience").forGetter(block -> block.experienceDropped), createSettingsCodec())
			.apply(instance, ExperienceDroppingVerticalSlabBlock::new));
	
	protected final IntProvider experienceDropped;
	
	@Override
	public MapCodec<ExperienceDroppingVerticalSlabBlock> getCodec() {
		return CODEC;
	}
	
	public ExperienceDroppingVerticalSlabBlock(IntProvider experienceDropped, Settings settings) {
		super(settings);
		this.experienceDropped = experienceDropped;
	}
	
	public ExperienceDroppingVerticalSlabBlock(Settings settings) {
		this(ConstantIntProvider.create(0), settings);
	}
	
	@Override
	protected void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack tool, boolean dropExperience) {
		super.onStacksDropped(state, world, pos, tool, dropExperience);
		if (dropExperience) {
			this.dropExperienceWhenMined(world, pos, tool, this.experienceDropped);
		}
	}
}