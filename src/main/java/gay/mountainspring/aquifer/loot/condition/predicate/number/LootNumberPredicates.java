package gay.mountainspring.aquifer.loot.condition.predicate.number;

import gay.mountainspring.aquifer.Aquifer;
import gay.mountainspring.aquifer.registry.AquiferRegistryKeys;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class LootNumberPredicates {
	private LootNumberPredicates() {}
	
	public static void init() {}
	
	public static final RegistryKey<LootNumberPredicate> EQUAL = create("equal");
	public static final RegistryKey<LootNumberPredicate> NOT_EQUAL = create("not_equal");
	public static final RegistryKey<LootNumberPredicate> LESS_THAN = create("less_than");
	public static final RegistryKey<LootNumberPredicate> GREATER_THAN = create("greater_than");
	public static final RegistryKey<LootNumberPredicate> LESS_THAN_OR_EQUAL = create("less_than_or_equal");
	public static final RegistryKey<LootNumberPredicate> GREATER_THAN_OR_EQUAL = create("greater_than_or_equal");
	public static final RegistryKey<LootNumberPredicate> IS_FACTOR_OF = create("is_factor_of");
	public static final RegistryKey<LootNumberPredicate> IS_EVEN = create("is_even");
	public static final RegistryKey<LootNumberPredicate> IS_ODD = create("is_odd");
	
	private static RegistryKey<LootNumberPredicate> create(String name) {
		return RegistryKey.of(AquiferRegistryKeys.LOOT_NUMBER_PREDICATE, Identifier.of(Aquifer.MOD_ID, name));
	}
}