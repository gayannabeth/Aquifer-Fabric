package gay.mountainspring.aquifer.block;

import org.jetbrains.annotations.Nullable;

import gay.mountainspring.aquifer.block.cauldron.CauldronGroup;
import gay.mountainspring.aquifer.util.BlockUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.block.cauldron.CauldronBehavior.CauldronBehaviorMap;
import net.minecraft.fluid.Fluid;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.event.GameEvent;

public abstract class AquiferLeveledCauldronBlock extends AbstractAquiferCauldronBlock implements AquiferLeveledCauldronExtensions {
	@Nullable protected final Biome.Precipitation precipitation;
	
	public AquiferLeveledCauldronBlock(Biome.Precipitation precipitation, CauldronGroup group, Settings settings, CauldronBehaviorMap behaviorMap) {
		super(group, settings, behaviorMap);
		this.precipitation = precipitation;
	}
	
	public AquiferLeveledCauldronBlock(CauldronGroup group, Settings settings, CauldronBehavior.CauldronBehaviorMap behaviorMap) {
		this(null, group, settings, behaviorMap);
	}

	@Override
	public boolean isFull(BlockState state) {
		return state.get(this.aquifer$getLevelProperty()) == this.aquifer$getMaxLevel();
	}
	
	@Override
	protected void appendProperties(Builder<Block, BlockState> builder) {
		builder.add(this.aquifer$getLevelProperty());
	}
	
	@Override
	protected int getComparatorOutput(BlockState state, World world, BlockPos pos) {
		return state.get(this.aquifer$getLevelProperty());
	}
	
	@Override
	protected void fillFromDripstone(BlockState state, World world, BlockPos pos, Fluid fluid) {
		if (!this.isFull(state) && BlockUtil.shouldDripInto(fluid, this.aquifer$getContentsType())) {
			BlockState blockState = state.with(this.aquifer$getLevelProperty(), state.get(this.aquifer$getLevelProperty()) + 1);
			world.setBlockState(pos, blockState);
			world.playSoundAtBlockCenter(pos, BlockUtil.getSoundForDrippingFluid(fluid), SoundCategory.BLOCKS, 1.0f, 1.0f, false);
			world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(blockState));
		}
	}
	
	@Override
	protected boolean canBeFilledByDripstone(Fluid fluid) {
		return BlockUtil.shouldDripInto(fluid, this.aquifer$getContentsType());
	}
	
	@Override
	public Biome.Precipitation aquifer$getPrecipitation() {
		return this.precipitation;
	}
	
	@Override
	public void aquifer$decrementFluidLevel(BlockState state, World world, BlockPos pos) {
		int i = state.get(this.aquifer$getLevelProperty()) - 1;
		BlockState blockState = i == 0 ? this.group.getEmpty().getDefaultState() : state.with(this.aquifer$getLevelProperty(), i);
		world.setBlockState(pos, blockState);
		world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(blockState));
	}
	
	@Override
	public boolean aquifer$incrementFluidLevel(BlockState state, World world, BlockPos pos) {
		int i = state.get(this.aquifer$getLevelProperty());
		
		if (i == this.aquifer$getMaxLevel()) {
			return false;
		} else {
			BlockState blockState = i == 0 ? this.group.getEmpty().getDefaultState() : state.with(this.aquifer$getLevelProperty(), i + 1);
			world.setBlockState(pos, blockState);
			world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(blockState));
			return true;
		}
	}
	
	@Override
	public void precipitationTick(BlockState state, World world, BlockPos pos, Biome.Precipitation precipitation) {
		if (this.precipitation != null && AquiferCauldronBlock.canFillWithPrecipitation(world, precipitation) && (Integer)state.get(this.aquifer$getLevelProperty()) != this.aquifer$getMaxLevel() && BlockUtil.isPrecipitationValidForContents(this.aquifer$getContentsType(), precipitation)) {
			BlockState blockState = state.cycle(this.aquifer$getLevelProperty());
			world.setBlockState(pos, blockState);
			world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(blockState));
		}
	}
	
	@Override
	protected double getFluidHeight(BlockState state) {
		return (6.0D + (state.get(this.aquifer$getLevelProperty()) * (9.0D / this.aquifer$getMaxLevel()))) / 16.0D;
	}
	
	@Override
	public abstract IntProperty aquifer$getLevelProperty();
	
	@Override
	public abstract int aquifer$getMaxLevel();
}
