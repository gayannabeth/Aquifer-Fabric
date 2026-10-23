package gay.mountainspring.aquifer.util;

import java.util.List;
import java.util.stream.Collectors;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableList;

import gay.mountainspring.aquifer.item.component.AquiferComponentTypes;
import gay.mountainspring.aquifer.mixin.ItemAccessor;
import gay.mountainspring.aquifer.tag.AquiferTags;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BookItem;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.MaceItem;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.item.ShearsItem;
import net.minecraft.item.ToolItem;
import net.minecraft.item.TridentItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Unit;

public class ItemUtil {
	private ItemUtil() {}
	
	public static void init() {}
	
	/**
	 * Sets a recipe remainder item for an existing item
	 * @param item the item to set the recipe remainder for
	 * @param recipeRemainder the item to set as the recipe remainder (may be null for no recipe remainder)
	 */
	public static void setItemRecipeRemainder(Item item, @Nullable Item recipeRemainder) {
		((ItemAccessor) item).setRecipeRemainder(recipeRemainder);
	}
	
	static {
		DefaultItemComponentEvents.MODIFY.register(context -> {
			context.modify(item -> item.getDefaultStack().getComponents().contains(DataComponentTypes.FOOD), (builder, item) -> {
				builder.getOrCreate(AquiferComponentTypes.DRINK_SOUND, () -> Registries.SOUND_EVENT.getEntry(item.getDrinkSound()));
				builder.getOrCreate(AquiferComponentTypes.EAT_SOUND, () -> Registries.SOUND_EVENT.getEntry(item.getEatSound()));
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
				builder.add(AquiferComponentTypes.BREAK_SOUND, Registries.SOUND_EVENT.getEntry(item.getBreakSound()));
			});
			context.modify(item -> item instanceof ArmorItem, (builder, item) -> {
				builder.getOrCreate(AquiferComponentTypes.BREAK_SOUND, () -> Registries.SOUND_EVENT.getEntry(item.getBreakSound()));
				builder.getOrCreate(AquiferComponentTypes.ENCHANTABILITY, () -> ((ArmorItem) item).getMaterial().value().enchantability());
			});
			context.modify(item -> item instanceof ToolItem, (builder, item) -> {
				builder.getOrCreate(AquiferComponentTypes.BREAK_SOUND, () -> Registries.SOUND_EVENT.getEntry(item.getBreakSound()));
				builder.getOrCreate(AquiferComponentTypes.ENCHANTABILITY, () -> ((ToolItem) item).getMaterial().getEnchantability());
			});
			context.modify(item -> item instanceof FishingRodItem || item instanceof MaceItem || item instanceof RangedWeaponItem || item instanceof TridentItem, (builder, item) -> {
				builder.getOrCreate(AquiferComponentTypes.ENCHANTABILITY, () -> item.getEnchantability());
				builder.getOrCreate(AquiferComponentTypes.BREAK_SOUND, () -> Registries.SOUND_EVENT.getEntry(item.getBreakSound()));
			});
			context.modify(item -> item instanceof BookItem, (builder, item) -> {
				builder.getOrCreate(AquiferComponentTypes.ENCHANTABILITY, () -> item.getEnchantability());
			});
			context.modify(Items.NETHER_STAR, builder -> builder.getOrCreate(AquiferComponentTypes.BLAST_RESISTANT, () -> Unit.INSTANCE));
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
 }