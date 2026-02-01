package gay.mountainspring.aquifer.item;

public interface AquiferItemStack {
	default int aquifer$getEnchantability() {
		return 0;
	}
}