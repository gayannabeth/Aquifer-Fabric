package gay.mountainspring.aquifer.util;

import java.util.Map;

import com.google.common.collect.Maps;
import com.ibm.icu.impl.locale.XCldrStub.ImmutableMap;

import gay.mountainspring.aquifer.tag.AquiferTags;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.registry.tag.TagKey;

public class StatusEffectUtil {
	public static void init() {}
	
	private static final Map<RegistryEntry<StatusEffect>, TagKey<EntityType<?>>> EFFECT_IMMUNE_TAGS = Maps.newHashMap();
	
	/**
	 * Map a {@link StatusEffect} {@link RegistryEntry} to a {@link EntityType} {@link TagKey} for entities that are immune to the given tag
	 * @param effect the effect the entities are immune to
	 * @param tag the tag for entities immune to this effect
	 */
	public static void registerEffectImmuneTag(RegistryEntry<StatusEffect> effect, TagKey<EntityType<?>> tag) {
		EFFECT_IMMUNE_TAGS.putIfAbsent(effect, tag);
	}
	
	/**
	 * @return An {@link ImmutableMap} Effect Immune {@link EntityType} tags
	 * @return
	 */
	public static Map<RegistryEntry<StatusEffect>, TagKey<EntityType<?>>> effectImmuneTags() {
		return ImmutableMap.copyOf(EFFECT_IMMUNE_TAGS);
	}
	
	static {
		registerEffectImmuneTag(StatusEffects.SPEED, AquiferTags.EntityTypes.IMMUNE_TO_SPEED);
		registerEffectImmuneTag(StatusEffects.SLOWNESS, AquiferTags.EntityTypes.IMMUNE_TO_SLOWNESS);
		registerEffectImmuneTag(StatusEffects.HASTE, AquiferTags.EntityTypes.IMMUNE_TO_HASTE);
		registerEffectImmuneTag(StatusEffects.MINING_FATIGUE, AquiferTags.EntityTypes.IMMUNE_TO_MINING_FATIGUE);
		registerEffectImmuneTag(StatusEffects.STRENGTH, AquiferTags.EntityTypes.IMMUNE_TO_STRENGTH);
		registerEffectImmuneTag(StatusEffects.INSTANT_HEALTH, AquiferTags.EntityTypes.IMMUNE_TO_INSTANT_HEALTH);
		registerEffectImmuneTag(StatusEffects.INSTANT_DAMAGE, AquiferTags.EntityTypes.IMMUNE_TO_INSTANT_DAMAGE);
		registerEffectImmuneTag(StatusEffects.JUMP_BOOST, AquiferTags.EntityTypes.IMMUNE_TO_JUMP_BOOST);
		registerEffectImmuneTag(StatusEffects.NAUSEA, AquiferTags.EntityTypes.IMMUNE_TO_NAUSEA);
		registerEffectImmuneTag(StatusEffects.REGENERATION, AquiferTags.EntityTypes.IMMUNE_TO_REGENERATION);
		registerEffectImmuneTag(StatusEffects.RESISTANCE, AquiferTags.EntityTypes.IMMUNE_TO_RESISTANCE);
		registerEffectImmuneTag(StatusEffects.FIRE_RESISTANCE, AquiferTags.EntityTypes.IMMUNE_TO_FIRE_RESISTANCE);
		registerEffectImmuneTag(StatusEffects.WATER_BREATHING, AquiferTags.EntityTypes.IMMUNE_TO_WATER_BREATHING);
		registerEffectImmuneTag(StatusEffects.INVISIBILITY, AquiferTags.EntityTypes.IMMUNE_TO_INVISIBILITY);
		registerEffectImmuneTag(StatusEffects.BLINDNESS, AquiferTags.EntityTypes.IMMUNE_TO_BLINDNESS);
		registerEffectImmuneTag(StatusEffects.NIGHT_VISION, AquiferTags.EntityTypes.IMMUNE_TO_NIGHT_VISION);
		registerEffectImmuneTag(StatusEffects.HUNGER, AquiferTags.EntityTypes.IMMUNE_TO_HUNGER);
		registerEffectImmuneTag(StatusEffects.WEAKNESS, AquiferTags.EntityTypes.IMMUNE_TO_WEAKNESS);
		registerEffectImmuneTag(StatusEffects.POISON, AquiferTags.EntityTypes.IMMUNE_TO_POISON);
		registerEffectImmuneTag(StatusEffects.WITHER, AquiferTags.EntityTypes.IMMUNE_TO_WITHER);
		registerEffectImmuneTag(StatusEffects.HEALTH_BOOST, AquiferTags.EntityTypes.IMMUNE_TO_HEALTH_BOOST);
		registerEffectImmuneTag(StatusEffects.ABSORPTION, AquiferTags.EntityTypes.IMMUNE_TO_ABSORPTION);
		registerEffectImmuneTag(StatusEffects.SATURATION, AquiferTags.EntityTypes.IMMUNE_TO_SATURATION);
		registerEffectImmuneTag(StatusEffects.GLOWING, AquiferTags.EntityTypes.IMMUNE_TO_GLOWING);
		registerEffectImmuneTag(StatusEffects.LEVITATION, AquiferTags.EntityTypes.IMMUNE_TO_LEVITATION);
		registerEffectImmuneTag(StatusEffects.LUCK, AquiferTags.EntityTypes.IMMUNE_TO_LUCK);
		registerEffectImmuneTag(StatusEffects.UNLUCK, AquiferTags.EntityTypes.IMMUNE_TO_UNLUCK);
		registerEffectImmuneTag(StatusEffects.SLOW_FALLING, AquiferTags.EntityTypes.IMMUNE_TO_SLOW_FALLING);
		registerEffectImmuneTag(StatusEffects.CONDUIT_POWER, AquiferTags.EntityTypes.IMMUNE_TO_CONDUIT_POWER);
		registerEffectImmuneTag(StatusEffects.DOLPHINS_GRACE, AquiferTags.EntityTypes.IMMUNE_TO_DOLPHINS_GRACE);
		registerEffectImmuneTag(StatusEffects.BAD_OMEN, AquiferTags.EntityTypes.IMMUNE_TO_BAD_OMEN);
		registerEffectImmuneTag(StatusEffects.HERO_OF_THE_VILLAGE, AquiferTags.EntityTypes.IMMUNE_TO_HERO_OF_THE_VILLAGE);
		registerEffectImmuneTag(StatusEffects.DARKNESS, AquiferTags.EntityTypes.IMMUNE_TO_DARKNESS);
		registerEffectImmuneTag(StatusEffects.TRIAL_OMEN, AquiferTags.EntityTypes.IMMUNE_TO_TRIAL_OMEN);
		registerEffectImmuneTag(StatusEffects.RAID_OMEN, AquiferTags.EntityTypes.IMMUNE_TO_RAID_OMEN);
		registerEffectImmuneTag(StatusEffects.WIND_CHARGED, AquiferTags.EntityTypes.IMMUNE_TO_WIND_CHARGED);
		registerEffectImmuneTag(StatusEffects.WEAVING, AquiferTags.EntityTypes.IMMUNE_TO_WEAVING);
		registerEffectImmuneTag(StatusEffects.OOZING, EntityTypeTags.IMMUNE_TO_OOZING);
		registerEffectImmuneTag(StatusEffects.INFESTED, EntityTypeTags.IMMUNE_TO_INFESTED);
	}
}