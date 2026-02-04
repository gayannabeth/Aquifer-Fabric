package gay.mountainspring.aquifer.item;

import gay.mountainspring.aquifer.item.component.AquiferComponentTypes;
import net.minecraft.block.Block;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

/**
 * Extension of the vanilla MiningToolItem class that adds default item break and enchantability components
 */
public class AquiferMiningToolItem extends MiningToolItem {
	public AquiferMiningToolItem(SoundEvent breakSound, ToolMaterial material, TagKey<Block> effectiveBlocks, Settings settings) {
		super(material, effectiveBlocks, settings.component(AquiferComponentTypes.BREAK_SOUND, Registries.SOUND_EVENT.getEntry(breakSound)).component(AquiferComponentTypes.ENCHANTABILITY, material.getEnchantability()));
	}
	
	public AquiferMiningToolItem(ToolMaterial material, TagKey<Block> effectiveBlocks, Settings settings) {
		this(SoundEvents.ENTITY_ITEM_BREAK, material, effectiveBlocks, settings);
	}
}