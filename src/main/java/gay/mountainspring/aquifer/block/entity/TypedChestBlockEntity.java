package gay.mountainspring.aquifer.block.entity;

import gay.mountainspring.aquifer.block.WoodTyped;
import net.minecraft.block.BlockState;
import net.minecraft.block.WoodType;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.util.math.BlockPos;

public class TypedChestBlockEntity extends ChestBlockEntity implements WoodTyped {
	protected TypedChestBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
		super(blockEntityType, blockPos, blockState);
	}
	
	public TypedChestBlockEntity(BlockPos blockPos, BlockState blockState) {
		this(AquiferBlockEntityTypes.TYPED_CHEST, blockPos, blockState);
	}
	
	@Override
	public WoodType aquifer$getWoodType() {
		if (this.getCachedState().getBlock() instanceof WoodTyped woodTypedBlock) {
			return woodTypedBlock.aquifer$getWoodType();
		} else {
			return WoodType.OAK;
		}
	}
}