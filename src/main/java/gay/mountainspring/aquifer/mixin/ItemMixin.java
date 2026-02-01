package gay.mountainspring.aquifer.mixin;

import gay.mountainspring.aquifer.item.component.AquiferComponentTypes;
import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.resource.featuretoggle.ToggleableFeature;

public abstract class ItemMixin implements FabricItem, ItemConvertible, ToggleableFeature {
	@Override
	public ItemStack getRecipeRemainder(ItemStack stack) {
		RegistryEntry<Item> entry = stack.get(AquiferComponentTypes.RECIPE_REMAINDER);
		if (entry != null) return entry.value().getDefaultStack();
		else return FabricItem.super.getRecipeRemainder(stack);
	}
}