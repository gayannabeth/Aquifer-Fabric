package gay.mountainspring.aquifer.util;

import java.util.Optional;

import org.jetbrains.annotations.Nullable;

import com.google.common.base.MoreObjects;

import gay.mountainspring.aquifer.tag.AquiferTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TripwireBlock;
import net.minecraft.block.TripwireHookBlock;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

//mostly just here so modded tripwire hooks don't spontaneously morph into vanilla tripwire hooks. I stg Mojank never thinks of modding
public class TripwireUtil {
	public static void update(World world, BlockPos pos, BlockState state, boolean bl, boolean bl2, int i, @Nullable BlockState blockState) {
		Optional<Direction> optional = state.getOrEmpty(TripwireHookBlock.FACING);
		if (optional.isPresent()) {
			Direction direction = (Direction)optional.get();
			boolean bl3 = (Boolean)state.getOrEmpty(TripwireHookBlock.ATTACHED).orElse(false);
			boolean bl4 = (Boolean)state.getOrEmpty(TripwireHookBlock.POWERED).orElse(false);
			Block block = state.getBlock();
			boolean bl5 = !bl;
			boolean bl6 = false;
			int j = 0;
			BlockState[] blockStates = new BlockState[42];

			for (int k = 1; k < 42; k++) {
				BlockPos blockPos = pos.offset(direction, k);
				BlockState blockState2 = world.getBlockState(blockPos);
				if (blockState2.isIn(AquiferTags.Blocks.TRIPWIRE_HOOKS)) {
					if (blockState2.get(TripwireHookBlock.FACING) == direction.getOpposite()) {
						j = k;
					}
					break;
				}

				if (!blockState2.isOf(Blocks.TRIPWIRE) && k != i) {
					blockStates[k] = null;
					bl5 = false;
				} else {
					if (k == i) {
						blockState2 = MoreObjects.firstNonNull(blockState, blockState2);
					}

					boolean bl7 = !(Boolean)blockState2.get(TripwireBlock.DISARMED);
					boolean bl8 = (Boolean)blockState2.get(TripwireBlock.POWERED);
					bl6 |= bl7 && bl8;
					blockStates[k] = blockState2;
					if (k == i) {
						world.scheduleBlockTick(pos, block, 10);
						bl5 &= bl7;
					}
				}
			}

			bl5 &= j > 1;
			bl6 &= bl5;
			BlockState blockState3 = block.getDefaultState().withIfExists(TripwireHookBlock.ATTACHED, bl5).withIfExists(TripwireHookBlock.POWERED, bl6);
			if (j > 0) {
				BlockPos blockPosx = pos.offset(direction, j);
				Direction direction2 = direction.getOpposite();
				world.setBlockState(blockPosx, blockState3.with(TripwireHookBlock.FACING, direction2), Block.NOTIFY_ALL);
				updateNeighborsOnAxis(block, world, blockPosx, direction2);
				playSound(world, blockPosx, bl5, bl6, bl3, bl4);
			}

			playSound(world, pos, bl5, bl6, bl3, bl4);
			if (!bl) {
				world.setBlockState(pos, blockState3.with(TripwireHookBlock.FACING, direction), Block.NOTIFY_ALL);
				if (bl2) {
					updateNeighborsOnAxis(block, world, pos, direction);
				}
			}

			if (bl3 != bl5) {
				for (int l = 1; l < j; l++) {
					BlockPos blockPos2 = pos.offset(direction, l);
					BlockState blockState4 = blockStates[l];
					if (blockState4 != null) {
						world.setBlockState(blockPos2, blockState4.withIfExists(TripwireHookBlock.ATTACHED, bl5), Block.NOTIFY_ALL);
						if (!world.getBlockState(blockPos2).isAir()) {
						}
					}
				}
			}
		}
	}
	
	private static void playSound(World world, BlockPos pos, boolean attached, boolean on, boolean detached, boolean off) {
		if (on && !off) {
			world.playSound(null, pos, SoundEvents.BLOCK_TRIPWIRE_CLICK_ON, SoundCategory.BLOCKS, 0.4F, 0.6F);
			world.emitGameEvent(null, GameEvent.BLOCK_ACTIVATE, pos);
		} else if (!on && off) {
			world.playSound(null, pos, SoundEvents.BLOCK_TRIPWIRE_CLICK_OFF, SoundCategory.BLOCKS, 0.4F, 0.5F);
			world.emitGameEvent(null, GameEvent.BLOCK_DEACTIVATE, pos);
		} else if (attached && !detached) {
			world.playSound(null, pos, SoundEvents.BLOCK_TRIPWIRE_ATTACH, SoundCategory.BLOCKS, 0.4F, 0.7F);
			world.emitGameEvent(null, GameEvent.BLOCK_ATTACH, pos);
		} else if (!attached && detached) {
			world.playSound(null, pos, SoundEvents.BLOCK_TRIPWIRE_DETACH, SoundCategory.BLOCKS, 0.4F, 1.2F / (world.random.nextFloat() * 0.2F + 0.9F));
			world.emitGameEvent(null, GameEvent.BLOCK_DETACH, pos);
		}
	}

	private static void updateNeighborsOnAxis(Block block, World world, BlockPos pos, Direction direction) {
		world.updateNeighborsAlways(pos, block);
		world.updateNeighborsAlways(pos.offset(direction.getOpposite()), block);
	}
}