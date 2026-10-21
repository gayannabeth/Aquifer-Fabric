package gay.mountainspring.aquifer.resource;

import com.google.common.collect.BiMap;
import com.google.common.collect.EnumBiMap;

import net.fabricmc.fabric.api.loot.v3.LootTableSource;

/**
 * Identical to {@code LootTableSource} with a more generic name.
 */
public enum ReloadableResourceSource {
	VANILLA(true, LootTableSource.VANILLA),
	MOD(true, LootTableSource.MOD),
	DATA_PACK(false, LootTableSource.DATA_PACK),
	REPLACED(false, LootTableSource.REPLACED);
	
	private static final BiMap<ReloadableResourceSource, LootTableSource> BIMAP = EnumBiMap.create(ReloadableResourceSource.class, LootTableSource.class);
	
	static {
		for (var rrSource : values())
			BIMAP.put(rrSource, rrSource.lootTableSource);
	}
	
	private final boolean isBuiltin;
	private final LootTableSource lootTableSource;
	
	private ReloadableResourceSource(boolean isBuiltin, LootTableSource ltSource) {
		this.isBuiltin = isBuiltin;
		this.lootTableSource = ltSource;
	}
	
	public boolean isBuiltin() {
		return this.isBuiltin;
	}
	
	public LootTableSource lootTableSource() {
		return this.lootTableSource;
	}
	
	public static ReloadableResourceSource fromLootTableSource(LootTableSource ltSource) {
		return BIMAP.inverse().get(ltSource);
	}
	
	public static LootTableSource toLootTableSource(ReloadableResourceSource rrSource) {
		return BIMAP.get(rrSource);
	}
}