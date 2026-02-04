package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;

import gay.mountainspring.aquifer.util.BlockUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShortPlantBlock;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
/**
 * vanilla short plants are dumb, use this instead, make sure to register a short and tall plant pair with {@link BlockUtil}, otherwise this'll just grow into {@link net.minecraft.block.Blocks.TALL_GRASS}
 */
public class AquiferShortPlantBlock extends ShortPlantBlock {
	public static final MapCodec<ShortPlantBlock> CODEC = createCodec(AquiferShortPlantBlock::new);
	
	@Override
	public MapCodec<ShortPlantBlock> getCodec() {
		return CODEC;
	}
	
	public AquiferShortPlantBlock(Settings settings) {
		super(settings);
	}
	
	@Override
	public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
		var map = BlockUtil.shortToTallPlantsMap();
		if (map.containsKey(this)) {
			var tall = map.get(this);
			if (tall.getDefaultState().canPlaceAt(world, pos)) {
				TallPlantBlock.placeAt(world, tall.getDefaultState(), pos, 2);
			}
		} else {
			super.grow(world, random, pos, state);
		}
	}
}