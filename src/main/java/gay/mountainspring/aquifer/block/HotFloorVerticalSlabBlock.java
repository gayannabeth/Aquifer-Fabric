package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HotFloorVerticalSlabBlock extends VerticalSlabBlock {
	public static final MapCodec<HotFloorVerticalSlabBlock> CODEC = createCodec(HotFloorVerticalSlabBlock::new);
	
	@Override
	public MapCodec<? extends HotFloorVerticalSlabBlock> getCodec() {
		return CODEC;
	}
	
	public HotFloorVerticalSlabBlock(Settings settings) {
		super(settings);
	}
	
	@Override
	public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
		if (!entity.isSneaking() && entity instanceof LivingEntity) {
			entity.damage(world.getDamageSources().hotFloor(), 1.0f);
		}
		
		super.onSteppedOn(world, pos, state, entity);
	}
}