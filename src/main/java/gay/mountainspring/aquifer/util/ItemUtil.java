package gay.mountainspring.aquifer.util;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import gay.mountainspring.aquifer.item.component.AquiferComponentTypes;
import gay.mountainspring.aquifer.mixin.ComponentMapBuilderInvoker;
import gay.mountainspring.aquifer.mixin.ItemAccessor;
import gay.mountainspring.aquifer.tag.AquiferTags;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BookItem;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.Item;
import net.minecraft.item.MaceItem;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.item.ShearsItem;
import net.minecraft.item.ToolItem;
import net.minecraft.item.TridentItem;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Rarity;

public class ItemUtil {
	private ItemUtil() {}
	
	public static void init () {
		addItemTagDamageHandler(AquiferTags.Items.FIRE_PROOF, DamageTypeTags.IS_FIRE);
		addItemTagDamageHandler(AquiferTags.Items.EXPLOSION_PROOF, DamageTypeTags.IS_EXPLOSION);
		addItemTagDamageHandler(AquiferTags.Items.CACTUS_PROOF, DamageTypes.CACTUS);
		
		DefaultItemComponentEvents.MODIFY.register(context -> {
			context.modify(item -> item.getDefaultStack().getComponents().contains(DataComponentTypes.FOOD), (builder, item) -> {
				builder.getOrCreate(AquiferComponentTypes.DRINK_SOUND, () -> AquiferComponentTypes.DEFAULT_DRINK_SOUND);
				builder.getOrCreate(AquiferComponentTypes.EAT_SOUND, () -> AquiferComponentTypes.DEFAULT_EAT_SOUND);
			});
			context.modify(item -> item instanceof ShearsItem, (builder, item) -> {
				ToolComponent component = builder.getOrDefault(DataComponentTypes.TOOL, ShearsItem.createToolComponent());
				List<ToolComponent.Rule> rules = component.rules().stream().collect(Collectors.toList());
				float f = component.defaultMiningSpeed();
				int i = component.damagePerBlock();
				rules.add(ToolComponent.Rule.of(AquiferTags.Blocks.SHEARS_MINEABLE_FAST, 15.0f));
				rules.add(ToolComponent.Rule.of(AquiferTags.Blocks.SHEARS_MINEABLE_MEDIUM, 5.0f));
				rules.add(ToolComponent.Rule.of(AquiferTags.Blocks.SHEARS_MINEABLE_SLOW, 2.0f));
				builder.add(DataComponentTypes.TOOL, new ToolComponent(ImmutableList.copyOf(rules), f, i));
				builder.add(AquiferComponentTypes.BREAK_SOUND, AquiferComponentTypes.DEFAULT_BREAK_SOUND);
			});
			context.modify(item -> item instanceof ArmorItem, (builder, item) -> {
				builder.getOrCreate(AquiferComponentTypes.BREAK_SOUND, () -> AquiferComponentTypes.DEFAULT_BREAK_SOUND);
				builder.getOrCreate(AquiferComponentTypes.ENCHANTABILITY, () -> ((ArmorItem) item).getMaterial().value().enchantability());
			});
			context.modify(item -> item instanceof ToolItem, (builder, item) -> {
				builder.getOrCreate(AquiferComponentTypes.BREAK_SOUND, () -> AquiferComponentTypes.DEFAULT_BREAK_SOUND);
				builder.getOrCreate(AquiferComponentTypes.ENCHANTABILITY, () -> ((ToolItem) item).getMaterial().getEnchantability());
			});
			context.modify(item -> item instanceof BookItem || item instanceof FishingRodItem || item instanceof MaceItem || item instanceof RangedWeaponItem || item instanceof TridentItem, (builder, item) -> {
				builder.getOrCreate(AquiferComponentTypes.ENCHANTABILITY, () -> item.getEnchantability());
			});
		});
		
		FuelRegistry reg = FuelRegistry.INSTANCE;
		
		reg.add(AquiferTags.Items.BOOKSHELVES, 300);
		reg.add(AquiferTags.Items.CARTOGRAPHY_TABLES, 300);
		reg.add(AquiferTags.Items.COMPOSTERS, 300);
		reg.add(AquiferTags.Items.CRAFTING_TABLES, 300);
		reg.add(AquiferTags.Items.FLETCHING_TABLES, 300);
		reg.add(AquiferTags.Items.LECTERNS, 300);
		reg.add(AquiferTags.Items.LOOMS, 300);
		reg.add(AquiferTags.Items.SMITHING_TABLES, 300);
		reg.add(AquiferTags.Items.STICKS, 100);
		reg.add(AquiferTags.Items.WOODEN_BARRELS, 300);
		reg.add(AquiferTags.Items.WOODEN_CHESTS, 300);
		reg.add(AquiferTags.Items.WOODEN_FENCE_GATES, 300);
		reg.remove(ItemTags.FENCE_GATES);
		reg.add(AquiferTags.Items.WOODEN_HANGING_SIGNS, 800);
		reg.remove(ItemTags.HANGING_SIGNS);
		reg.add(AquiferTags.Items.WOODEN_LADDERS, 300);
		reg.add(AquiferTags.Items.WOODEN_SIGNS, 200);
		reg.remove(ItemTags.SIGNS);
		reg.add(AquiferTags.Items.WOODEN_WALLS, 300);
	}
	
	private static final Map<TagKey<Item>, Predicate<DamageSource>> ITEM_TAG_DAMAGE_HANDLERS = Maps.newHashMap();
	
	/**
	 * 
	 * @return an immutable copy of the {@link Map} mapping item tags to an item entity damage handler
	 */
	public static Map<TagKey<Item>, Predicate<DamageSource>> itemTagDamageHandlers() {
		return ImmutableMap.copyOf(ITEM_TAG_DAMAGE_HANDLERS);
	}
	
	/**
	 * Adds a handler for item entity damaging for the given item tag
	 * @param tag the {@link TagKey} of the tag to handle damage for
	 * @param predicate a predicate to test on the damage source
	 */
	public static void addItemTagDamageHandler(TagKey<Item> tag, Predicate<DamageSource> predicate) {
		ITEM_TAG_DAMAGE_HANDLERS.putIfAbsent(tag, predicate);
	}
	
	/**
	 * Adds a handler that returns true when the damage source is of the given damage type tag
	 * @param itemTag the {@link TagKey} of the tag to handle damage for
	 * @param damageTag the {@link TagKey} of the {@link DamageType} to set the tag immune to
	 */
	public static void addItemTagDamageHandler(TagKey<Item> itemTag, TagKey<DamageType> damageTag) {
		addItemTagDamageHandler(itemTag, damageSource -> damageSource.isIn(damageTag));
	}
	
	/**
	 * Adds a handler that returns true when the damage source is of the given damage type
	 * @param tag the {@link TagKey} of the tag to handle damage for
	 * @param damageType the {@link RegistryKey} of the {@link DamageType} to set the tag immune to
	 */
	public static void addItemTagDamageHandler(TagKey<Item> tag, RegistryKey<DamageType> damageType) {
		addItemTagDamageHandler(tag, damageSource -> damageSource.isOf(damageType));
	}
	
	public static void setItemRarity(Item item, Rarity rarity) {
		setComponent(item, DataComponentTypes.RARITY, rarity);
	}
	
	public static void setItemMaxCount(Item item, int count) {
		if (!item.getComponents().contains(DataComponentTypes.MAX_DAMAGE)) {
			setComponent(item, DataComponentTypes.MAX_STACK_SIZE, count);
		}
	}
	
	public static void setItemMaxDamage(Item item, int damage) {
		setComponents(item, ComponentMapBuilderInvoker.newComponentMapBuilder()
				.add(DataComponentTypes.MAX_DAMAGE, damage)
				.add(DataComponentTypes.MAX_STACK_SIZE, 1)
				.add(DataComponentTypes.DAMAGE, 0)
				.build());
	}
	
	public static void setItemRecipeRemainder(Item item, @Nullable Item recipeRemainder) {
		((ItemAccessor) item).setRecipeRemainder(recipeRemainder);
	}
	
	public static void setItemFoodComponent(Item item, @Nullable FoodComponent foodComponent) {
		setComponent(item, DataComponentTypes.FOOD, foodComponent);
	}
	
	public static <T> void setComponent(Item item, ComponentType<T> type, T value) {
		setComponents(item, ComponentMapBuilderInvoker.newComponentMapBuilder().add(type, value).build());
	}
	
	public static void setComponents(Item item, ComponentMap components) {
		DefaultItemComponentEvents.MODIFY.register(context -> {
			context.modify(item, builder -> {
				builder.addAll(components);
			});
		});
	}
 }