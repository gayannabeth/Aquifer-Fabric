package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;

import gay.mountainspring.aquifer.block.cauldron.CauldronContentsType;
import gay.mountainspring.aquifer.block.cauldron.CauldronGroup;
import net.minecraft.block.BlockState;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AquiferLavaCauldronBlock extends AbstractAquiferCauldronBlock {
	public static final MapCodec<AquiferLavaCauldronBlock> CODEC = createCodec(AquiferLavaCauldronBlock::new);
	
	@Override
	protected MapCodec<? extends AquiferLavaCauldronBlock> getCodec() {
		return CODEC;
	}
	
	public AquiferLavaCauldronBlock(CauldronGroup group, Settings settings) {
		super(group, settings, CauldronBehavior.LAVA_CAULDRON_BEHAVIOR);
		group.setLava(this);
	}
	
	@Override
	public boolean isFull(BlockState state) {
		return true;
	}
	
	@Override
	protected double getFluidHeight(BlockState state) {
		return 0.9375;
	}
	
	@Override
	protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		if (this.isEntityTouchingFluid(state, pos, entity)) {
			entity.setOnFireFromLava();
		}
	}
	
	@Override
	protected int getComparatorOutput(BlockState state, World world, BlockPos pos) {
		return 3;
	}
	
	@Override
	public CauldronContentsType aquifer$getContentsType() {
		return CauldronContentsType.LAVA;
	}
}