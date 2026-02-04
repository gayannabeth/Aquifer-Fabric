package gay.mountainspring.aquifer.util;

import java.util.function.Predicate;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.ComponentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemGroupUtil {
	private ItemGroupUtil() {}
	
	/**
	 * Removes an item from and item group
	 * @param entries the item group entries to remove the item from
	 * @param item the item to remove
	 */
	public static void remove(FabricItemGroupEntries entries, Item item) {
		remove(entries, stack -> stack.isOf(item));
	}
	
	/**
	 * Removes an item matching the specified component map from an item group
	 * @param entries the item group entries to remove the item from
	 * @param item the item to remove
	 * @param componentsToMatch the components that must match for the item to be removed
	 */
	public static void remove(FabricItemGroupEntries entries, Item item, ComponentMap componentsToMatch) {
		remove(entries, stack -> {
			for (ComponentType<?> type : componentsToMatch.getTypes()) {
				if (stack.getComponents().contains(type)) {
					if (!componentsToMatch.get(type).equals(stack.get(type))) {
						return false;
					}
				} else {
					return false;
				}
			}
			return stack.isOf(item);
		});
	}
	
	/**
	 * Removes any item matching the given predicate from an item group
	 * @param entries the item group entries to remove the item from
	 * @param predicate the predicate to test
	 */
	public static void remove(FabricItemGroupEntries entries, Predicate<ItemStack> predicate) {
		entries.getDisplayStacks().removeIf(predicate);
	}
}