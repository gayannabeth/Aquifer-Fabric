package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;

import gay.mountainspring.aquifer.block.cauldron.CauldronContentsType;
import gay.mountainspring.aquifer.block.cauldron.CauldronGroup;
import net.minecraft.block.BlockState;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
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
		return true;
	}
	
	@Override
	protected void fillFromDripstone(BlockState state, World world, BlockPos pos, Fluid fluid) {
		if (fluid == Fluids.WATER) {
			BlockState blockState = this.group.getWater().getDefaultState();
			world.setBlockState(pos, blockState);
			world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(blockState));
			world.syncWorldEvent(WorldEvents.POINTED_DRIPSTONE_DRIPS_WATER_INTO_CAULDRON, pos, 0);
		} else if (fluid == Fluids.LAVA) {
			BlockState blockState = this.group.getLava().getDefaultState();
			world.setBlockState(pos, blockState);
			world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(blockState));
			world.syncWorldEvent(WorldEvents.POINTED_DRIPSTONE_DRIPS_LAVA_INTO_CAULDRON, pos, 0);
		}
	}
	
	@Override
	public CauldronContentsType aquifer$getContentsType() {
		return CauldronContentsType.EMPTY;
	}
}