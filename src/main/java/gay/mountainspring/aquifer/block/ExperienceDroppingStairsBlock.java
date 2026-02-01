package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;

public class ExperienceDroppingStairsBlock extends StairsBlock {
	public static final MapCodec<ExperienceDroppingStairsBlock> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(IntProvider.createValidatingCodec(0, 10).fieldOf("experience").forGetter(block -> block.experienceDropped),
					BlockState.CODEC.fieldOf("base_state").forGetter(block -> block.baseBlockState),
					createSettingsCodec())
			.apply(instance, ExperienceDroppingStairsBlock::new));
	
	protected final IntProvider experienceDropped;
	
	@Override
	public MapCodec<? extends ExperienceDroppingStairsBlock> getCodec() {
		return CODEC;
	}
	
	public ExperienceDroppingStairsBlock(IntProvider experienceDropped, BlockState baseBlockState, Settings settings) {
		super(baseBlockState, settings);
		this.experienceDropped = experienceDropped;
	}
	
	public ExperienceDroppingStairsBlock(BlockState baseBlockState, Settings settings) {
		this(ConstantIntProvider.create(0), baseBlockState, settings);
	}
	
	@Override
	protected void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack tool, boolean dropExperience) {
		super.onStacksDropped(state, world, pos, tool, dropExperience);
		if (dropExperience) {
			this.dropExperienceWhenMined(world, pos, tool, this.experienceDropped);
		}
	}
}