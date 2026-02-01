package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import gay.mountainspring.aquifer.block.AquiferLeveledCauldronExtensions;
import net.minecraft.block.AbstractCauldronBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.block.cauldron.CauldronBehavior.CauldronBehaviorMap;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Precipitation;
import net.minecraft.world.event.GameEvent;

@Mixin(LeveledCauldronBlock.class)
public abstract class LeveledCauldronBlockMixin extends AbstractCauldronBlock implements AquiferLeveledCauldronExtensions {
	private LeveledCauldronBlockMixin(Settings settings, CauldronBehaviorMap behaviorMap) {super(settings, behaviorMap);}
	
	@Override
	public Precipitation aquifer$getPrecipitation() {
		return precipitation;
	}
	
	@Override
	public void aquifer$decrementFluidLevel(BlockState state, World world, BlockPos pos) {
		int i = state.get(Properties.LEVEL_3) - 1;
		BlockState blockState = i == 0 ? this.aquifer$getGroup().getEmpty().getDefaultState() : state.with(Properties.LEVEL_3, i);
		world.setBlockState(pos, blockState);
		world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(blockState));
	}
	
	@Override
	public boolean aquifer$incrementFluidLevel(BlockState state, World world, BlockPos pos) {
		int i = state.get(Properties.LEVEL_3);
		
		if (i == 3) {
			return false;
		} else {
			BlockState blockState = i == 0 ? this.aquifer$getGroup().getEmpty().getDefaultState() : state.with(Properties.LEVEL_3, i + 1);
			world.setBlockState(pos, blockState);
			world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(blockState));
			return true;
		}
	}
	
	@Shadow
	Biome.Precipitation precipitation;
}