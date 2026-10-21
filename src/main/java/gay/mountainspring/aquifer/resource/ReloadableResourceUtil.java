package gay.mountainspring.aquifer.resource;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import net.fabricmc.fabric.impl.loot.LootUtil;
import net.minecraft.resource.Resource;
import net.minecraft.util.Identifier;

public class ReloadableResourceUtil {
	public static final ThreadLocal<Table<Identifier, Identifier, ReloadableResourceSource>> SOURCES = ThreadLocal.withInitial(HashBasedTable::create);
	
	public static ReloadableResourceSource getSource(Resource resource) {
		return ReloadableResourceSource.fromLootTableSource(LootUtil.determineSource(resource));
	}
}