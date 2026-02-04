package gay.mountainspring.aquifer.block;

import java.util.function.BiConsumer;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.WoodType;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.explosion.Explosion;

/**
 * exists because vanilla fence gates do not obey BlockSetType rules
 */
public class AquiferFenceGateBlock extends FenceGateBlock {
	public static final MapCodec<FenceGateBlock> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(WoodType.CODEC.fieldOf("wood_type").forGetter(WoodTyped::aquifer$getWoodType), createSettingsCodec())
			.apply(instance, AquiferFenceGateBlock::new));
	
	public AquiferFenceGateBlock(WoodType type, Settings settings) {
		super(type, settings);
	}
	
	@Override
	protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
		if (this.aquifer$getBlockSetType().canOpenByHand()) {
			return super.onUse(state, world, pos, player, hit);
		} else {
			return ActionResult.PASS;
		}
	}
	
	@Override
	protected void onExploded(BlockState state, World world, BlockPos pos, Explosion explosion, BiConsumer<ItemStack, BlockPos> stackMerger) {
		if (this.aquifer$getBlockSetType().canOpenByWindCharge() && explosion.canTriggerBlocks() && !(Boolean)state.get(POWERED)) {
			boolean bl = (Boolean)state.get(OPEN);
			world.setBlockState(pos, state.with(OPEN, !bl));
			world.playSound(null, pos, bl ? this.aquifer$getWoodType().fenceGateClose(): this.aquifer$getWoodType().fenceGateOpen(), SoundCategory.BLOCKS, 1.0F, world.getRandom().nextFloat() * 0.1F + 0.9F);
			world.emitGameEvent(bl ? GameEvent.BLOCK_CLOSE : GameEvent.BLOCK_OPEN, pos, GameEvent.Emitter.of(state));
		}
		
		if (!state.isAir() && explosion.getDestructionType() != Explosion.DestructionType.TRIGGER_BLOCK) {
			Block block = state.getBlock();
			boolean bl = explosion.getCausingEntity() instanceof PlayerEntity;
			if (block.shouldDropItemsOnExplosion(explosion) && world instanceof ServerWorld serverWorld) {
				BlockEntity blockEntity = state.hasBlockEntity() ? world.getBlockEntity(pos) : null;
				LootContextParameterSet.Builder builder = new LootContextParameterSet.Builder(serverWorld)
					.add(LootContextParameters.ORIGIN, Vec3d.ofCenter(pos))
					.add(LootContextParameters.TOOL, ItemStack.EMPTY)
					.addOptional(LootContextParameters.BLOCK_ENTITY, blockEntity)
					.addOptional(LootContextParameters.THIS_ENTITY, explosion.getEntity());
				if (explosion.getDestructionType() == Explosion.DestructionType.DESTROY_WITH_DECAY) {
					builder.add(LootContextParameters.EXPLOSION_RADIUS, explosion.getPower());
				}
				
				state.onStacksDropped(serverWorld, pos, ItemStack.EMPTY, bl);
				state.getDroppedStacks(builder).forEach(stack -> stackMerger.accept(stack, pos));
			}
			
			world.setBlockState(pos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL);
			block.onDestroyedByExplosion(world, pos, explosion);
		}
	}
}