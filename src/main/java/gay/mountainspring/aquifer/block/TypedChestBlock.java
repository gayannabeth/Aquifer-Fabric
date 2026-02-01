package gay.mountainspring.aquifer.block;

import java.util.function.Supplier;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import gay.mountainspring.aquifer.block.entity.AquiferBlockEntityTypes;
import gay.mountainspring.aquifer.block.entity.TypedChestBlockEntity;
import gay.mountainspring.aquifer.util.BlockUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.WoodType;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.util.math.BlockPos;

public class TypedChestBlock extends ChestBlock implements WoodTyped {
	public static final MapCodec<TypedChestBlock> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(WoodType.CODEC.fieldOf("wood_type").forGetter(WoodTyped::aquifer$getWoodType), createSettingsCodec())
			.apply(instance, TypedChestBlock::new));
	
	private final WoodType type;
	
	@Override
	public MapCodec<? extends TypedChestBlock> getCodec() {
		return CODEC;
	}
	
	protected TypedChestBlock(WoodType type, Settings settings, Supplier<BlockEntityType<? extends ChestBlockEntity>> entityTypeSupplier) {
		super(settings, entityTypeSupplier);
		this.type = type;
		BlockUtil.makeHaveChestVariant(type);
	}
	
	public TypedChestBlock(WoodType type, Settings settings) {
		this(type, settings, () -> AquiferBlockEntityTypes.TYPED_CHEST);
		AquiferBlockEntityTypes.TYPED_CHEST.addSupportedBlock(this);
	}
	
	@Override
	public WoodType aquifer$getWoodType() {
		return this.type;
	}
	
	@Override
	public boolean aquifer$isTrapped() {
		return false;
	}
	
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new TypedChestBlockEntity(pos, state);
	}
}