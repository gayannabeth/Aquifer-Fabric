package gay.mountainspring.aquifer.util;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import gay.mountainspring.aquifer.tag.AquiferTags;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.entry.RegistryEntry;

public class RepairUtil {
	private RepairUtil() {}
	
	public static void init () {}
	
	public static final Map<RegistryEntry<ArmorMaterial>, Ingredient> ARMOR_REPAIR_INGREDIENTS;
	public static final Map<ToolMaterial, Ingredient> TOOL_REPAIR_INGREDIENTS;
	public static final Ingredient ELYTRA_REPAIR_INGREDIENT = Ingredient.fromTag(AquiferTags.Items.ELYTRA_REPAIR_MATERIALS);
	
	static {
		ARMOR_REPAIR_INGREDIENTS = ImmutableMap.<RegistryEntry<ArmorMaterial>, Ingredient>builder()
				.put(ArmorMaterials.LEATHER, Ingredient.fromTag(AquiferTags.Items.LEATHER_ARMOR_MATERIALS))
				.put(ArmorMaterials.CHAIN, Ingredient.fromTag(AquiferTags.Items.CHAIN_ARMOR_MATERIALS))
				.put(ArmorMaterials.IRON, Ingredient.fromTag(AquiferTags.Items.IRON_ARMOR_MATERIALS))
				.put(ArmorMaterials.DIAMOND, Ingredient.fromTag(AquiferTags.Items.DIAMOND_ARMOR_MATERIALS))
				.put(ArmorMaterials.GOLD, Ingredient.fromTag(AquiferTags.Items.GOLD_ARMOR_MATERIALS))
				.put(ArmorMaterials.NETHERITE, Ingredient.fromTag(AquiferTags.Items.NETHERITE_ARMOR_MATERIALS))
				.put(ArmorMaterials.TURTLE, Ingredient.fromTag(AquiferTags.Items.TURTLE_ARMOR_MATERIALS))
				.put(ArmorMaterials.ARMADILLO, Ingredient.fromTag(AquiferTags.Items.ARMADILLO_ARMOR_MATERIALS))
				.build();
		TOOL_REPAIR_INGREDIENTS = ImmutableMap.<ToolMaterial, Ingredient>builder()
				.put(ToolMaterials.WOOD, Ingredient.fromTag(AquiferTags.Items.WOOD_TOOL_MATERIALS))
				.put(ToolMaterials.IRON, Ingredient.fromTag(AquiferTags.Items.IRON_TOOL_MATERIALS))
				.put(ToolMaterials.DIAMOND, Ingredient.fromTag(AquiferTags.Items.DIAMOND_TOOL_MATERIALS))
				.put(ToolMaterials.GOLD, Ingredient.fromTag(AquiferTags.Items.GOLD_TOOL_MATERIALS))
				.put(ToolMaterials.NETHERITE, Ingredient.fromTag(AquiferTags.Items.NETHERITE_TOOL_MATERIALS))
				.build();
	}
}