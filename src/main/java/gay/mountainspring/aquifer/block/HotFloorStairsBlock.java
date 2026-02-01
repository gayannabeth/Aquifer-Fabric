package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HotFloorStairsBlock extends StairsBlock {
	public static final MapCodec<HotFloorStairsBlock> CODEC = RecordCodecBuilder.mapCodec(
		instance -> instance.group(
			BlockState.CODEC.fieldOf("base_state").forGetter(block -> block.baseBlockState),
			createSettingsCodec()
		).apply(instance, HotFloorStairsBlock::new)
	);
	
	@Override
	public MapCodec<? extends HotFloorStairsBlock> getCodec() {
		return CODEC;
	}
	
	public HotFloorStairsBlock(BlockState baseBlockState, Settings settings) {
		super(baseBlockState, settings);
	}
	
	@Override
	public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
		if (!entity.isSneaking() && entity instanceof LivingEntity) {
			entity.damage(world.getDamageSources().hotFloor(), 1.0f);
		}
		
		super.onSteppedOn(world, pos, state, entity);
	}
}