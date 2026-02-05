package gay.mountainspring.aquifer.block;

import net.minecraft.block.BlockState;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
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
	
	default IntProperty aquifer$getLevelProperty() {
		return Properties.LEVEL_3;
	}
	
	default int aquifer$getMaxLevel() {
		return 3;
	}
}