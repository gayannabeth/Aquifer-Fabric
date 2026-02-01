package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;

import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HotFloorSlabBlock extends SlabBlock {
	public static final MapCodec<HotFloorSlabBlock> CODEC = createCodec(HotFloorSlabBlock::new);
	
	@Override
	public MapCodec<? extends HotFloorSlabBlock> getCodec() {
		return CODEC;
	}
	
	public HotFloorSlabBlock(Settings settings) {
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