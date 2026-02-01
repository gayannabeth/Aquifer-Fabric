package gay.mountainspring.aquifer.loot.provider.number;

import com.mojang.serialization.MapCodec;

import gay.mountainspring.aquifer.Aquifer;
import net.minecraft.loot.provider.number.LootNumberProvider;
import net.minecraft.loot.provider.number.LootNumberProviderType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class AquiferLootNumberProviderTypes {
	private AquiferLootNumberProviderTypes() {}
	
	public static void init() {}
	
	public static final LootNumberProviderType BLOCK_STATE_PROPERTY = register("block_state_property", BlockStatePropertyLootNumberProvider.CODEC);
	public static final LootNumberProviderType ENTITY_PROPERTY = register("entity_property", EntityPropertyLootNumberProvider.CODEC);
	public static final LootNumberProviderType EXPRESSION = register("expression", ExpressionLootNumberProvider.CODEC);
	
	public static LootNumberProviderType register(String name, MapCodec<? extends LootNumberProvider> codec) {
		return Registry.register(Registries.LOOT_NUMBER_PROVIDER_TYPE, Identifier.of(Aquifer.MOD_ID, name), new LootNumberProviderType(codec));
	}
}