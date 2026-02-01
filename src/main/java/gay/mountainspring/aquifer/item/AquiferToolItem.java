package gay.mountainspring.aquifer.item;

import gay.mountainspring.aquifer.item.component.AquiferComponentTypes;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class AquiferToolItem extends ToolItem {
	public AquiferToolItem(SoundEvent breakSound, ToolMaterial material, Settings settings) {
		super(material, settings.component(AquiferComponentTypes.BREAK_SOUND, Registries.SOUND_EVENT.getEntry(breakSound)).component(AquiferComponentTypes.ENCHANTABILITY, material.getEnchantability()));
	}
	
	public AquiferToolItem(ToolMaterial material, Settings settings) {
		this(SoundEvents.ENTITY_ITEM_BREAK, material, settings);
	}
}