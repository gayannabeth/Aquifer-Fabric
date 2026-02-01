package gay.mountainspring.aquifer.util;

import java.util.function.Predicate;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.ComponentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemGroupUtil {
	private ItemGroupUtil() {}
	
	public static void remove(FabricItemGroupEntries entries, Item item) {
		remove(entries, stack -> stack.isOf(item));
	}
	
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
	
	public static void remove(FabricItemGroupEntries entries, Predicate<ItemStack> predicate) {
		entries.getDisplayStacks().removeIf(predicate);
	}
}