package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.WoodType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HotFloorFenceGateBlock extends AquiferFenceGateBlock {
	public static final MapCodec<FenceGateBlock> CODEC = RecordCodecBuilder.mapCodec(
		instance -> instance.group(
			WoodType.CODEC.fieldOf("wood_type").forGetter(WoodTyped::aquifer$getWoodType),
			createSettingsCodec()
		).apply(instance, HotFloorFenceGateBlock::new)
	);
	
	@Override
	public MapCodec<FenceGateBlock> getCodec() {
		return CODEC;
	}
	
	public HotFloorFenceGateBlock(WoodType type, Settings settings) {
		super(type, settings);
	}
	
	@Override
	public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
		if (!entity.isSneaking() && entity instanceof LivingEntity) {
			entity.damage(world.getDamageSources().hotFloor(), 1.0f);
		}
		
		super.onSteppedOn(world, pos, state, entity);
	}
}