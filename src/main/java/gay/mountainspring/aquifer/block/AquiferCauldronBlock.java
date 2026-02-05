package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;

import gay.mountainspring.aquifer.block.cauldron.CauldronContentsType;
import gay.mountainspring.aquifer.block.cauldron.CauldronGroup;
import gay.mountainspring.aquifer.util.BlockUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.fluid.Fluid;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.event.GameEvent;

public class AquiferCauldronBlock extends AbstractAquiferCauldronBlock {
	public static final MapCodec<AquiferCauldronBlock> CODEC = createCodec(AquiferCauldronBlock::new);	
	
	@Override
	protected MapCodec<? extends AquiferCauldronBlock> getCodec() {
		return CODEC;
	}
	
	public AquiferCauldronBlock(CauldronGroup group, Settings settings) {
		super(group, settings, CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR);
		group.setEmpty(this);
	}

	@Override
	public boolean isFull(BlockState state) {
		return false;
	}
	
	protected static boolean canFillWithPrecipitation(World world, Biome.Precipitation precipitation) {
		return ((precipitation == Biome.Precipitation.RAIN) && (world.getRandom().nextFloat() < 0.05f)) || ((precipitation == Biome.Precipitation.SNOW) && world.getRandom().nextFloat() < 0.1f);
	}
	
	@Override
	public void precipitationTick(BlockState state, World world, BlockPos pos, Biome.Precipitation precipitation) {
		if (canFillWithPrecipitation(world, precipitation)) {
			if (precipitation == Biome.Precipitation.RAIN) {
				world.setBlockState(pos, this.group.getWater().getDefaultState());
				world.emitGameEvent(null, GameEvent.BLOCK_CHANGE, pos);
			} else if (precipitation == Biome.Precipitation.SNOW) {
				world.setBlockState(pos, this.group.getPowderSnow().getDefaultState());
				world.emitGameEvent(null, GameEvent.BLOCK_CHANGE, pos);
			}
		}
	}
	
	@Override
	protected boolean canBeFilledByDripstone(Fluid fluid) {
		return BlockUtil.isDripstoneFluid(fluid);
	}
	
	@Override
	protected void fillFromDripstone(BlockState state, World world, BlockPos pos, Fluid fluid) {
		if (BlockUtil.isDripstoneFluid(fluid)) {
			BlockState blockState  = BlockUtil.getStateForDrippingFluidIntoEmptyCauldron(fluid, this.group);
			world.setBlockState(pos, blockState);
			world.playSoundAtBlockCenter(pos, BlockUtil.getSoundForDrippingFluid(fluid), SoundCategory.BLOCKS, 1.0f, 1.0f, false);
			world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(blockState));
		}
	}
	
	@Override
	public CauldronContentsType aquifer$getContentsType() {
		return CauldronContentsType.EMPTY;
	}
}