package gay.mountainspring.aquifer.block.entity;

import gay.mountainspring.aquifer.Aquifer;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class AquiferBlockEntityTypes {
	private AquiferBlockEntityTypes() {}
	
	public static void init() {}
	
	public static final BlockEntityType<TypedChestBlockEntity> TYPED_CHEST = register("typed_chest", BlockEntityType.Builder.create(TypedChestBlockEntity::new, Blocks.CHEST));
	public static final BlockEntityType<TypedTrappedChestBlockEntity> TYPED_TRAPPED_CHEST = register("typed_trapped_chest", BlockEntityType.Builder.create(TypedTrappedChestBlockEntity::new, Blocks.TRAPPED_CHEST));
	
	private static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType.Builder<T> builder) {
		return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Aquifer.MOD_ID, name), builder.build());
	}
}