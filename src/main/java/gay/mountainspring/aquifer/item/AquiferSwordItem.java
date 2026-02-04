package gay.mountainspring.aquifer.item;

import gay.mountainspring.aquifer.item.component.AquiferComponentTypes;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

/**
 * Extension of the vanilla SwordItem class that adds default item break and enchantability components
 */
public class AquiferSwordItem extends SwordItem {
	public AquiferSwordItem(SoundEvent breakSound, ToolMaterial toolMaterial, Settings settings) {
		super(toolMaterial, settings.component(AquiferComponentTypes.BREAK_SOUND, Registries.SOUND_EVENT.getEntry(breakSound)).component(AquiferComponentTypes.ENCHANTABILITY, toolMaterial.getEnchantability()));
	}
	
	public AquiferSwordItem(ToolMaterial toolMaterial, Settings settings) {
		this(SoundEvents.ENTITY_ITEM_BREAK, toolMaterial, settings);
	}
}