package gay.mountainspring.aquifer.util;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import gay.mountainspring.aquifer.Aquifer;
import net.minecraft.util.StringIdentifiable;

public enum TagHandlingLevel implements StringIdentifiable {
	DISABLED("disabled"),
	FALLBACK_TO_VANILLA("fallback_to_vanilla"),
	STRICT("strict");
	
	@SuppressWarnings("deprecation")
	public static StringIdentifiable.EnumCodec<TagHandlingLevel> CODEC = StringIdentifiable.createCodec(TagHandlingLevel::values);
	
	public static final Map<String, TagHandlingLevel> NAME_TO_LEVEL = new ImmutableMap.Builder<String, TagHandlingLevel>()
			.put("disabled", DISABLED)
			.put("fallback_to_vanilla", FALLBACK_TO_VANILLA)
			.put("strict", STRICT)
			.build();
	
	private final String name;
	private String translationKey;
	
	private TagHandlingLevel(String name) {
		this.name = name;
	}

	public String getTranslationKey() {
		if (this.translationKey == null) {
			this.translationKey = Aquifer.MOD_ID + ".tag_handling_level." + this.name;
		}
		return this.translationKey;
	}
	
	public String getTooltipTranslationKey() {
		return this.getTranslationKey() + ".tooltip";
	}

	@Override
	public String asString() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}