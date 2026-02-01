package gay.mountainspring.aquifer.block;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public interface AquiferLeveledCauldronExtensions {
	default Biome.Precipitation aquifer$getPrecipitation() {
		return Biome.Precipitation.NONE;
	}
	
	default void aquifer$decrementFluidLevel(BlockState state, World world, BlockPos pos) {
		throw new AssertionError();
	}
	
	default boolean aquifer$incrementFluidLevel(BlockState state, World world, BlockPos pos) {
		return false;
	}
}