package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;

import gay.mountainspring.aquifer.block.cauldron.CauldronGroup;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.cauldron.CauldronBehavior.CauldronBehaviorMap;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.event.GameEvent;

public abstract class AquiferLeveledCauldronBlock extends AbstractAquiferCauldronBlock implements AquiferLeveledCauldronExtensions {
	public static final IntProperty LEVEL = Properties.LEVEL_3;
	private final Biome.Precipitation precipitation;
	
	public AquiferLeveledCauldronBlock(Biome.Precipitation precipitation, CauldronGroup group, Settings settings, CauldronBehaviorMap behaviorMap) {
		super(group, settings, behaviorMap);
		this.precipitation = precipitation;
		this.setDefaultState(this.stateManager.getDefaultState().with(LEVEL, 1));
	}

	@Override
	protected abstract MapCodec<? extends AquiferLeveledCauldronBlock> getCodec();

	@Override
	public boolean isFull(BlockState state) {
		return state.get(LEVEL) == 3;
	}
	
	@Override
	protected boolean canBeFilledByDripstone(Fluid fluid) {
		return fluid == Fluids.WATER && this.precipitation == Biome.Precipitation.RAIN;
	}
	
	@Override
	protected double getFluidHeight(BlockState state) {
		return (6.0 + state.get(LEVEL) * 3.0) / 16.0;
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
		if (this.precipitation == Biome.Precipitation.SNOW) {
			this.decrementFluidLevel(this.group.getWater().getDefaultState().with(LEVEL, state.get(LEVEL)), world, pos);
		} else {
			this.decrementFluidLevel(state, world, pos);
		}
	}
	
	public void decrementFluidLevel(BlockState state, World world, BlockPos pos) {
		int i = state.get(LEVEL) - 1;
		BlockState blockState = i == 0 ? this.group.getEmpty().getDefaultState() : state.with(LEVEL, i);
		world.setBlockState(pos, blockState);
		world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(blockState));
	}
	
	@Override
	public void aquifer$decrementFluidLevel(BlockState state, World world, BlockPos pos) {
		this.decrementFluidLevel(state, world, pos);
	}
	
	@Override
	public boolean aquifer$incrementFluidLevel(BlockState state, World world, BlockPos pos) {
		int i = state.get(LEVEL);
		
		if (i == 3) {
			return false;
		} else {
			BlockState blockState = i == 0 ? this.group.getEmpty().getDefaultState() : state.with(LEVEL, i + 1);
			world.setBlockState(pos, blockState);
			world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(blockState));
			return true;
		}
	}
	
	@Override
	public void precipitationTick(BlockState state, World world, BlockPos pos, Biome.Precipitation precipitation) {
		if (AquiferCauldronBlock.canFillWithPrecipitation(world, precipitation) && (Integer)state.get(LEVEL) != 3 && precipitation == this.precipitation) {
			BlockState blockState = state.cycle(LEVEL);
			world.setBlockState(pos, blockState);
			world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(blockState));
		}
	}
	
	@Override
	protected int getComparatorOutput(BlockState state, World world, BlockPos pos) {
		return state.get(LEVEL);
	}
	
	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(LEVEL);
	}
	
	@Override
	protected void fillFromDripstone(BlockState state, World world, BlockPos pos, Fluid fluid) {
		if (!this.isFull(state)) {
			BlockState blockState = state.with(LEVEL, state.get(LEVEL) + 1);
			world.setBlockState(pos, blockState);
			world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(blockState));
			world.syncWorldEvent(WorldEvents.POINTED_DRIPSTONE_DRIPS_WATER_INTO_CAULDRON, pos, 0);
		}
	}
	
	@Override
	public Biome.Precipitation aquifer$getPrecipitation() {
		return this.precipitation;
	}
}