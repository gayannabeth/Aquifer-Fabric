package gay.mountainspring.aquifer.block;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class TransparentColumnBlock extends TranslucentColumnBlock {
	public static final MapCodec<TransparentColumnBlock> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(
					Codec.INT.fieldOf("radius").forGetter(block -> block.radius),
					createSettingsCodec())
			.apply(instance, TransparentColumnBlock::new));
	
	@Override
	public MapCodec<? extends TransparentColumnBlock> getCodec() {
		return CODEC;
	}
	
	public TransparentColumnBlock(int radius, Settings settings) {
		super(radius, settings);
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