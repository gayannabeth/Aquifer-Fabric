package gay.mountainspring.aquifer.block.cauldron;

import java.util.Map;

import com.google.common.collect.Maps;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import gay.mountainspring.aquifer.Aquifer;
import gay.mountainspring.aquifer.registry.AquiferRegistries;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.Util;

public final class CauldronGroup implements StringIdentifiable {
	public static final MapCodec<CauldronGroup> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(Identifier.CODEC.fieldOf("name").forGetter(CauldronGroup::getName),
					Codec.simpleMap(AquiferRegistries.CAULDRON_CONTENTS_TYPE.getCodec(), Registries.BLOCK.getCodec(), StringIdentifiable.toKeyable(AquiferRegistries.CAULDRON_CONTENTS_TYPE.stream().toList().toArray(new CauldronContentsType[] {}))).fieldOf("types").forGetter(group -> group.map))
			.apply(instance, CauldronGroup::new));
	
	private final Identifier name;
	private final Map<CauldronContentsType, Block> map;
	
	public static final Identifier IRON_ID = Identifier.of(Aquifer.MOD_ID, "vanilla_iron");
	public static final CauldronGroup VANILLA_IRON = Registry.register(AquiferRegistries.CAULDRON_GROUP, IRON_ID, new CauldronGroup(IRON_ID).setEmpty(Blocks.CAULDRON).setWater(Blocks.WATER_CAULDRON).setPowderSnow(Blocks.POWDER_SNOW_CAULDRON).setLava(Blocks.LAVA_CAULDRON));
	
	public static void init() {}
	
	public MapCodec<CauldronGroup> getCodec() {
		return CODEC;
	}
	
	public CauldronGroup(Identifier name) {
		this.name = name;
		this.map = Maps.newHashMap();
	}
	
	public CauldronGroup(Identifier name, Map<CauldronContentsType, Block> map) {
		this.name = name;
		this.map = Maps.newHashMap(map);
	}
	
	public String getTranslationKey() {
		return Util.createTranslationKey("cauldron_group", this.getName());
	}
	
	public Identifier getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return this.getName().toString();
	}
	
	@Override
	public String asString() {
		return this.getName().toString();
	}
	
	public CauldronGroup set(CauldronContentsType type, Block block) {
		this.map.put(type, block);
		return this;
	}
	
	public CauldronGroup setEmpty(Block block) {
		return this.set(CauldronContentsType.EMPTY, block);
	}
	
	public CauldronGroup setWater(Block block) {
		return this.set(CauldronContentsType.WATER, block);
	}
	
	public CauldronGroup setLava(Block block) {
		return this.set(CauldronContentsType.LAVA, block);
	}
	
	public CauldronGroup setPowderSnow(Block block) {
		return this.set(CauldronContentsType.POWDER_SNOW, block);
	}
	
	public Block get(CauldronContentsType type) {
		return this.map.get(type);
	}
	
	public Block getEmpty() {
		return this.get(CauldronContentsType.EMPTY);
	}
	
	public Block getWater() {
		return this.get(CauldronContentsType.WATER);
	}
	
	public Block getLava() {
		return this.get(CauldronContentsType.LAVA);
	}
	
	public Block getPowderSnow() {
		return this.get(CauldronContentsType.POWDER_SNOW);
	}
	
	public BlockState getWaterStateForLevel(int level) {
		if (level < 0) level = 0;
		if (level > 3) level = 3;
		return level == 0 ? this.getEmpty().getDefaultState() : this.getWater().getDefaultState().with(Properties.LEVEL_3, level);
	}
	
	public BlockState getPowderSnowStateForLevel(int level) {
		if (level < 0) level = 0;
		if (level > 3) level = 3;
		return level == 0 ? this.getEmpty().getDefaultState() : this.getPowderSnow().getDefaultState().with(Properties.LEVEL_3, level);
	}
}