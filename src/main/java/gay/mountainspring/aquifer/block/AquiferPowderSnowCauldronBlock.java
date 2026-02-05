package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;

import gay.mountainspring.aquifer.block.cauldron.CauldronContentsType;
import gay.mountainspring.aquifer.block.cauldron.CauldronGroup;
import net.minecraft.block.BlockState;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class AquiferPowderSnowCauldronBlock extends AquiferThreeLeveledCauldronBlock {
	public static final MapCodec<AquiferPowderSnowCauldronBlock> CODEC = createCodec(AquiferPowderSnowCauldronBlock::new);
	
	@Override
	protected MapCodec<? extends AquiferPowderSnowCauldronBlock> getCodec() {
		return CODEC;
	}
	
	public AquiferPowderSnowCauldronBlock(CauldronGroup group, Settings settings) {
		super(Biome.Precipitation.SNOW, group, settings, CauldronBehavior.POWDER_SNOW_CAULDRON_BEHAVIOR);
		group.setPowderSnow(this);
	}
	
	@Override
	public CauldronContentsType aquifer$getContentsType() {
		return CauldronContentsType.POWDER_SNOW;
	}
	
	@Override
	protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		if (!world.isClient && entity.isOnFire() && this.isEntityTouchingFluid(state, pos, entity)) {
			entity.extinguish();
			if (entity.canModifyAt(world, pos)) {
				this.onFireCollision(state, world, pos);
			}
		}
	}
	
	private void onFireCollision(BlockState state, World world, BlockPos pos) {
		this.aquifer$decrementFluidLevel(this.group.getWaterStateForLevel(state.get(LEVEL)), world, pos);
	}
}