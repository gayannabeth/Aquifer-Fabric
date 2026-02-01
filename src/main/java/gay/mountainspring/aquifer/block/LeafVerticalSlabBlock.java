package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;

import net.minecraft.block.BlockState;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.ParticleUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class LeafVerticalSlabBlock extends VerticalSlabBlock {
	public static final MapCodec<LeafVerticalSlabBlock> CODEC = createCodec(LeafVerticalSlabBlock::new);
	
	@Override
	public MapCodec<? extends LeafVerticalSlabBlock> getCodec() {
		return CODEC;
	}
	
	public LeafVerticalSlabBlock(Settings settings) {
		super(settings);
	}
	
	@Override
	public VoxelShape getSidesShape(BlockState state, BlockView world, BlockPos pos) {
		return VoxelShapes.empty();
	}
	
	@Override
	public int getOpacity(BlockState state, BlockView world, BlockPos pos) {
		return 1;
	}
	
	@Override
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
		if (world.hasRain(pos.up()) && random.nextInt(15) == 1) {
			BlockPos down = pos.down();
			BlockState belowState = world.getBlockState(down);
			if (!belowState.isOpaque() || !belowState.isSideSolidFullSquare(world, pos, Direction.UP)) {
				ParticleUtil.spawnParticle(world, pos, random, ParticleTypes.DRIPPING_WATER);
			}
		}
	}
}