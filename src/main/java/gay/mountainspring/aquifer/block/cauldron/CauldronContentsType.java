package gay.mountainspring.aquifer.block.cauldron;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import gay.mountainspring.aquifer.Aquifer;
import gay.mountainspring.aquifer.registry.AquiferRegistries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.Util;

/**
 * An object the represents the contents of cauldrons. For each registered {@link CauldronGroup}, all registered {@link CauldronContentsType}s must have an associated {@link Block} to avoid {@link NullPointerException}s being thrown during gameplay
 */
public final record CauldronContentsType(Identifier name) implements StringIdentifiable {
	public static final MapCodec<CauldronContentsType> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(Identifier.CODEC.fieldOf("name").forGetter(CauldronContentsType::name))
			.apply(instance, CauldronContentsType::new));
	
	public String getTranslationKey() {
		return Util.createTranslationKey("cauldron_contents", this.name());
	}
	
	@Override
	public final String toString() {
		return this.name().toString();
	}

	@Override
	public String asString() {
		return this.name().toString();
	}
	
	public MapCodec<CauldronContentsType> getCodec() {
		return CODEC;
	}
	
	public static final CauldronContentsType EMPTY = create("empty");
	public static final CauldronContentsType WATER = create("water");
	public static final CauldronContentsType LAVA = create("lava");
	public static final CauldronContentsType POWDER_SNOW = create("powder_snow");
	
	public static void init() {}
	
	private static CauldronContentsType create(String name) {
		Identifier id = Identifier.of(Aquifer.MOD_ID, name);
		
		return Registry.register(AquiferRegistries.CAULDRON_CONTENTS_TYPE, id, new CauldronContentsType(id));
	}
}