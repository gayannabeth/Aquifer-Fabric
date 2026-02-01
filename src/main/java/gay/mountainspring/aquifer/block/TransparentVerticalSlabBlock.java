package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;

import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class TransparentVerticalSlabBlock extends TranslucentVerticalSlabBlock {
	public static final MapCodec<TransparentVerticalSlabBlock> CODEC = createCodec(TransparentVerticalSlabBlock::new);
	
	@Override
	public MapCodec<? extends TransparentVerticalSlabBlock> getCodec() {
		return CODEC;
	}
	
	public TransparentVerticalSlabBlock(Settings settings) {
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