package gay.mountainspring.aquifer.loot.function;

import com.mojang.serialization.MapCodec;

import gay.mountainspring.aquifer.Aquifer;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.loot.function.LootFunctionType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class AquiferLootFunctionTypes {
	private AquiferLootFunctionTypes() {}
	
	public static void init() {}
	
	public static final LootFunctionType<SetCustomPotionEffectsLootFunction> SET_CUSTOM_POTION_EFFECTS = register("set_custom_potion_effects", SetCustomPotionEffectsLootFunction.CODEC);
	public static final LootFunctionType<SetPotionColorLootFunction> SET_POTION_COLOR_LOOT_FUNCTION = register("set_potion_color", SetPotionColorLootFunction.CODEC);
	public static final LootFunctionType<SetUnbreakableLootFunction> SET_UNBREAKABLE = register("set_unbreakable", SetUnbreakableLootFunction.CODEC);
	public static final LootFunctionType<SetDyedColorLootFunction> SET_DYED_COLOR = register("set_dyed_color", SetDyedColorLootFunction.CODEC);
	public static final LootFunctionType<SetAxolotlVariantLootFunction> SET_AXOLOTL_VARIANT = register("set_axolotl_variant", SetAxolotlVariantLootFunction.CODEC);
	public static final LootFunctionType<SetTropicalFishVariantLootFunction> SET_TROPICAL_FISH_VARIANT = register("set_tropical_fish_variant", SetTropicalFishVariantLootFunction.CODEC);
	public static final LootFunctionType<SetShieldColorLootFunction> SET_SHIELD_COLOR = register("set_shield_color", SetShieldColorLootFunction.CODEC);
	public static final LootFunctionType<SequenceLootFunction> SEQUENCE = register("sequence", SequenceLootFunction.CODEC);
	public static final LootFunctionType<SelectorLootFunction> SELECTOR = register("selector", SelectorLootFunction.CODEC);
	
	public static <T extends LootFunction> LootFunctionType<T> register(String name, MapCodec<T> codec) {
		return Registry.register(Registries.LOOT_FUNCTION_TYPE, Identifier.of(Aquifer.MOD_ID, name), new LootFunctionType<>(codec));
	}
}