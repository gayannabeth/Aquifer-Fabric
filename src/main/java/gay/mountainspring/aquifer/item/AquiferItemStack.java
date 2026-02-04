package gay.mountainspring.aquifer.item;

public interface AquiferItemStack {
	/**
	 * ItemStack method to get enchantability, either gets the value from the component or if the component is not present from the item getEnchantability method
	 * @return enchantability
	 */
	default int aquifer$getEnchantability() {
		return 0;
	}
}