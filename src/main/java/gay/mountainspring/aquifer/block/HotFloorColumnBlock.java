package gay.mountainspring.aquifer.block;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HotFloorColumnBlock extends ColumnBlock {
	public static final MapCodec<HotFloorColumnBlock> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(
					Codec.INT.fieldOf("radius").forGetter(block -> block.radius),
					createSettingsCodec())
			.apply(instance, HotFloorColumnBlock::new));
	
	@Override
	public MapCodec<? extends HotFloorColumnBlock> getCodec() {
		return CODEC;
	}
	
	public HotFloorColumnBlock(int radius, Settings settings) {
		super(radius, settings);
	}
	
	@Override
	public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
		if (!entity.isSneaking() && entity instanceof LivingEntity) {
			entity.damage(world.getDamageSources().hotFloor(), 1.0f);
		}
		
		super.onSteppedOn(world, pos, state, entity);
	}
}