package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;

import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class TransparentStepBlock extends TranslucentStepBlock {
	public static final MapCodec<TransparentStepBlock> CODEC = createCodec(TransparentStepBlock::new);
	
	@Override
	public MapCodec<? extends TransparentStepBlock> getCodec() {
		return CODEC;
	}
	
	public TransparentStepBlock(Settings settings) {
		super(settings);
	}
	
	@Override
	public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return VoxelShapes.empty();
	}
	
	@Override
	public float getAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos) {
		return 1.0f;
	}
	
	@Override
	public boolean isTransparent(BlockState state, BlockView world, BlockPos pos) {
		return true;
	}
}