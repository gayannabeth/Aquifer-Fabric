package gay.mountainspring.aquifer.item.component;

import java.util.function.UnaryOperator;

import gay.mountainspring.aquifer.Aquifer;
import net.minecraft.component.ComponentType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;

public class AquiferComponentTypes {
	private AquiferComponentTypes() {}
	
	public static void init() {}
	
	//Recipe Remainder item component, replaces/overrides the hardcoded recipe remainder in items
	public static final ComponentType<RegistryEntry<Item>> RECIPE_REMAINDER = register("recipe_remainder", builder -> builder.codec(Registries.ITEM.getEntryCodec()).cache());
	public static final ComponentType<RegistryEntry<SoundEvent>> EAT_SOUND = register("eat_sound", builder -> builder.codec(SoundEvent.ENTRY_CODEC).packetCodec(SoundEvent.ENTRY_PACKET_CODEC).cache());
	public static final ComponentType<RegistryEntry<SoundEvent>> DRINK_SOUND = register("drink_sound", builder -> builder.codec(SoundEvent.ENTRY_CODEC).packetCodec(SoundEvent.ENTRY_PACKET_CODEC).cache());
	public static final ComponentType<RegistryEntry<SoundEvent>> BREAK_SOUND = register("break_sound", builder -> builder.codec(SoundEvent.ENTRY_CODEC).packetCodec(SoundEvent.ENTRY_PACKET_CODEC).cache());
	public static final ComponentType<Integer> ENCHANTABILITY = register("enchantability", builder -> builder.codec(Codecs.NONNEGATIVE_INT).packetCodec(PacketCodecs.VAR_INT));
	
	public static final RegistryEntry<SoundEvent> DEFAULT_EAT_SOUND = Registries.SOUND_EVENT.getEntry(SoundEvents.ENTITY_GENERIC_EAT);
	public static final RegistryEntry<SoundEvent> DEFAULT_DRINK_SOUND = Registries.SOUND_EVENT.getEntry(SoundEvents.ENTITY_GENERIC_DRINK);
	public static final RegistryEntry<SoundEvent> DEFAULT_BREAK_SOUND = Registries.SOUND_EVENT.getEntry(SoundEvents.ENTITY_ITEM_BREAK);
	public static final RegistryEntry<Item> DEFAULT_RECIPE_REMAINDER = Registries.ITEM.getEntry(Items.AIR);
	
	private static <T> ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
		return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(Aquifer.MOD_ID, name), builderOperator.apply(ComponentType.builder()).build());
	}
}