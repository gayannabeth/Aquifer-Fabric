package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.ParticleUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class LeafStairsBlock extends StairsBlock {
	public static final MapCodec<LeafStairsBlock> CODEC = RecordCodecBuilder.mapCodec(
		instance -> instance.group(
			BlockState.CODEC.fieldOf("base_state").forGetter(block -> block.baseBlockState),
			createSettingsCodec()
		).apply(instance, LeafStairsBlock::new)
	);
	
	@Override
	public MapCodec<? extends LeafStairsBlock> getCodec() {
		return CODEC;
	}
	
	public LeafStairsBlock(BlockState baseBlockState, Settings settings) {
		super(baseBlockState, settings);
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