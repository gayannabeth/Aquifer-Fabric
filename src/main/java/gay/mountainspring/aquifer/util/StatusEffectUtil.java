package gay.mountainspring.aquifer.util;

import java.util.Map;

import com.google.common.collect.Maps;
import com.ibm.icu.impl.locale.XCldrStub.ImmutableMap;

import gay.mountainspring.aquifer.tag.AquiferTags;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.entry.RegistryEntry;
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
		registerEffectImmuneTag(StatusEffects.SPEED, AquiferTags.EntityTypes.SPEED_IMMUNE);
		registerEffectImmuneTag(StatusEffects.SLOWNESS, AquiferTags.EntityTypes.SLOWNESS_IMMUNE);
		registerEffectImmuneTag(StatusEffects.HASTE, AquiferTags.EntityTypes.HASTE_IMMUNE);
		registerEffectImmuneTag(StatusEffects.MINING_FATIGUE, AquiferTags.EntityTypes.MINING_FATIGUE_IMMUNE);
		registerEffectImmuneTag(StatusEffects.STRENGTH, AquiferTags.EntityTypes.STRENGTH_IMMUNE);
		registerEffectImmuneTag(StatusEffects.INSTANT_HEALTH, AquiferTags.EntityTypes.INSTANT_HEALTH_IMMUNE);
		registerEffectImmuneTag(StatusEffects.INSTANT_DAMAGE, AquiferTags.EntityTypes.INSTANT_DAMAGE_IMMUNE);
		registerEffectImmuneTag(StatusEffects.JUMP_BOOST, AquiferTags.EntityTypes.JUMP_BOOST_IMMUNE);
		registerEffectImmuneTag(StatusEffects.NAUSEA, AquiferTags.EntityTypes.NAUSEA_IMMUNE);
		registerEffectImmuneTag(StatusEffects.REGENERATION, AquiferTags.EntityTypes.REGENERATION_IMMUNE);
		registerEffectImmuneTag(StatusEffects.RESISTANCE, AquiferTags.EntityTypes.RESISTANCE_IMMUNE);
		registerEffectImmuneTag(StatusEffects.FIRE_RESISTANCE, AquiferTags.EntityTypes.FIRE_RESISTANCE_IMMUNE);
		registerEffectImmuneTag(StatusEffects.WATER_BREATHING, AquiferTags.EntityTypes.WATER_BREATHING_IMMUNE);
		registerEffectImmuneTag(StatusEffects.INVISIBILITY, AquiferTags.EntityTypes.INVISIBILITY_IMMUNE);
		registerEffectImmuneTag(StatusEffects.BLINDNESS, AquiferTags.EntityTypes.BLINDNESS_IMMUNE);
		registerEffectImmuneTag(StatusEffects.NIGHT_VISION, AquiferTags.EntityTypes.NIGHT_VISION_IMMUNE);
		registerEffectImmuneTag(StatusEffects.HUNGER, AquiferTags.EntityTypes.HUNGER_IMMUNE);
		registerEffectImmuneTag(StatusEffects.WEAKNESS, AquiferTags.EntityTypes.WEAKNESS_IMMUNE);
		registerEffectImmuneTag(StatusEffects.POISON, AquiferTags.EntityTypes.POISON_IMMUNE);
		registerEffectImmuneTag(StatusEffects.WITHER, AquiferTags.EntityTypes.WITHER_IMMUNE);
		registerEffectImmuneTag(StatusEffects.HEALTH_BOOST, AquiferTags.EntityTypes.HEALTH_BOOST_IMMUNE);
		registerEffectImmuneTag(StatusEffects.ABSORPTION, AquiferTags.EntityTypes.ABSORPTION_IMMUNE);
		registerEffectImmuneTag(StatusEffects.SATURATION, AquiferTags.EntityTypes.SATURATION_IMMUNE);
		registerEffectImmuneTag(StatusEffects.GLOWING, AquiferTags.EntityTypes.GLOWING_IMMUNE);
		registerEffectImmuneTag(StatusEffects.LEVITATION, AquiferTags.EntityTypes.LEVITATION_IMMUNE);
		registerEffectImmuneTag(StatusEffects.LUCK, AquiferTags.EntityTypes.LUCK_IMMUNE);
		registerEffectImmuneTag(StatusEffects.UNLUCK, AquiferTags.EntityTypes.UNLUCK_IMMUNE);
		registerEffectImmuneTag(StatusEffects.SLOW_FALLING, AquiferTags.EntityTypes.SLOW_FALLING_IMMUNE);
		registerEffectImmuneTag(StatusEffects.CONDUIT_POWER, AquiferTags.EntityTypes.CONDUIT_POWER_IMMUNE);
		registerEffectImmuneTag(StatusEffects.DOLPHINS_GRACE, AquiferTags.EntityTypes.DOLPHINS_GRACE_IMMUNE);
		registerEffectImmuneTag(StatusEffects.BAD_OMEN, AquiferTags.EntityTypes.BAD_OMEN_IMMUNE);
		registerEffectImmuneTag(StatusEffects.HERO_OF_THE_VILLAGE, AquiferTags.EntityTypes.HERO_OF_THE_VILLAGE_IMMUNE);
		registerEffectImmuneTag(StatusEffects.DARKNESS, AquiferTags.EntityTypes.DARKNESS_IMMUNE);
		registerEffectImmuneTag(StatusEffects.TRIAL_OMEN, AquiferTags.EntityTypes.TRIAL_OMEN_IMMUNE);
		registerEffectImmuneTag(StatusEffects.RAID_OMEN, AquiferTags.EntityTypes.RAID_OMEN_IMMUNE);
		registerEffectImmuneTag(StatusEffects.WIND_CHARGED, AquiferTags.EntityTypes.WIND_CHARGED_IMMUNE);
		registerEffectImmuneTag(StatusEffects.WEAVING, AquiferTags.EntityTypes.WEAVING_IMMUNE);
		registerEffectImmuneTag(StatusEffects.OOZING, AquiferTags.EntityTypes.OOZING_IMMUNE);
		registerEffectImmuneTag(StatusEffects.INFESTED, AquiferTags.EntityTypes.INFESTED_IMMUNE);
	}
}