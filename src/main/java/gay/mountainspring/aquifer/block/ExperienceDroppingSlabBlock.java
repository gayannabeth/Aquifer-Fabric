package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;

public class ExperienceDroppingSlabBlock extends SlabBlock {
	public static final MapCodec<ExperienceDroppingSlabBlock> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(IntProvider.createValidatingCodec(0, 10).fieldOf("experience").forGetter(block -> block.experienceDropped),
					IntProvider.createValidatingCodec(0, 10).fieldOf("half_experience").forGetter(block -> block.halfExperienceDropped),
					createSettingsCodec())
			.apply(instance, ExperienceDroppingSlabBlock::new));
	
	protected final IntProvider experienceDropped;
	protected final IntProvider halfExperienceDropped;
	
	@Override
	public MapCodec<? extends ExperienceDroppingSlabBlock> getCodec() {
		return CODEC;
	}
	
	public ExperienceDroppingSlabBlock(IntProvider experienceDropped, IntProvider halfExperienceDropped, Settings settings) {
		super(settings);
		this.experienceDropped = experienceDropped;
		this.halfExperienceDropped = halfExperienceDropped;
	}
	
	public ExperienceDroppingSlabBlock(IntProvider experienceDropped, Settings settings) {
		this(experienceDropped, experienceDropped, settings);
	}
	
	public ExperienceDroppingSlabBlock(Settings settings) {
		this(ConstantIntProvider.create(0), ConstantIntProvider.create(0), settings);
	}
	
	@Override
	protected void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack tool, boolean dropExperience) {
		super.onStacksDropped(state, world, pos, tool, dropExperience);
		if (dropExperience) {
			this.dropExperienceWhenMined(world, pos, tool, state.get(TYPE) == SlabType.DOUBLE ? this.experienceDropped : this.halfExperienceDropped);
		}
	}
}