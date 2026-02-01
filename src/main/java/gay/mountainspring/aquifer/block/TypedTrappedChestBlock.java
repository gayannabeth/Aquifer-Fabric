package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import gay.mountainspring.aquifer.block.entity.AquiferBlockEntityTypes;
import gay.mountainspring.aquifer.block.entity.TypedTrappedChestBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.WoodType;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.stat.Stat;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.BlockView;

public class TypedTrappedChestBlock extends TypedChestBlock {
	public static final MapCodec<TypedTrappedChestBlock> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(WoodType.CODEC.fieldOf("wood_type").forGetter(WoodTyped::aquifer$getWoodType), createSettingsCodec())
			.apply(instance, TypedTrappedChestBlock::new));
	
	@Override
	public MapCodec<TypedTrappedChestBlock> getCodec() {
		return CODEC;
	}
	
	public TypedTrappedChestBlock(WoodType type, Settings settings) {
		super(type, settings, () -> AquiferBlockEntityTypes.TYPED_TRAPPED_CHEST);
		AquiferBlockEntityTypes.TYPED_TRAPPED_CHEST.addSupportedBlock(this);
	}
	
	@Override
	public boolean aquifer$isTrapped() {
		return true;
	}
	
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new TypedTrappedChestBlockEntity(pos, state);
	}
	
	@Override
	protected Stat<Identifier> getOpenStat() {
		return Stats.CUSTOM.getOrCreateStat(Stats.TRIGGER_TRAPPED_CHEST);
	}
	
	@Override
	protected boolean emitsRedstonePower(BlockState state) {
		return true;
	}
	
	@Override
	protected int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
		return MathHelper.clamp(ChestBlockEntity.getPlayersLookingInChestCount(world, pos), 0, 15);
	}
	
	@Override
	protected int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
		return direction == Direction.UP ? state.getWeakRedstonePower(world, pos, direction) : 0;
	}
}