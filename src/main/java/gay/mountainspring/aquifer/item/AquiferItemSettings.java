package gay.mountainspring.aquifer.item;

import gay.mountainspring.aquifer.item.component.AquiferComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundEvent;

public interface AquiferItemSettings {
	default Item.Settings aquifer$breakSound(SoundEvent sound) {
		return ((Item.Settings) this).component(AquiferComponentTypes.BREAK_SOUND, Registries.SOUND_EVENT.getEntry(sound));
	}
	
	default Item.Settings aquifer$eatSound(SoundEvent sound) {
		return ((Item.Settings) this).component(AquiferComponentTypes.EAT_SOUND, Registries.SOUND_EVENT.getEntry(sound));
	}
	
	default Item.Settings aquifer$drinkSound(SoundEvent sound) {
		return ((Item.Settings) this).component(AquiferComponentTypes.DRINK_SOUND, Registries.SOUND_EVENT.getEntry(sound));
	}
	
	default Item.Settings aquifer$enchantability(int enchantability) {
		return ((Item.Settings) this).component(AquiferComponentTypes.ENCHANTABILITY, enchantability);
	}
}