package gay.mountainspring.aquifer.block;

import java.util.Optional;

import net.minecraft.block.BlockState;
import net.minecraft.block.FluidDrainable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;

public interface InjectableFluidDrainable extends FluidDrainable {
	@Override
	default ItemStack tryDrainFluid(PlayerEntity player, WorldAccess world, BlockPos pos, BlockState state) {
		throw new AssertionError();
	}
	
	@Override
	default Optional<SoundEvent> getBucketFillSound() {
		throw new AssertionError();
	}
}