package gay.mountainspring.aquifer.item;

import gay.mountainspring.aquifer.Aquifer;
import gay.mountainspring.aquifer.item.component.AquiferComponentTypes;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class AquiferArmorItem extends ArmorItem {
	public AquiferArmorItem(SoundEvent breakSound, RegistryEntry<ArmorMaterial> material, Type type, Settings settings) {
		super(material, type, addComponents(settings, breakSound, material));
	}
	
	public AquiferArmorItem(RegistryEntry<ArmorMaterial> material, Type type, Settings settings) {
		this(SoundEvents.ENTITY_ITEM_BREAK, material, type, settings);
	}
	
	private static Settings addComponents(Settings settings, SoundEvent breakSound, RegistryEntry<ArmorMaterial> material) {
		try {
			settings.component(AquiferComponentTypes.ENCHANTABILITY, material.value().enchantability());
		} catch (Exception e) {
			Aquifer.LOGGER.error(String.format("Uninitialized armor material entry found: {}, enchantability component not created", material), e);
		}
		return settings.component(AquiferComponentTypes.BREAK_SOUND, Registries.SOUND_EVENT.getEntry(breakSound));
	}
}