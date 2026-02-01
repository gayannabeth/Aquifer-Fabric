package gay.mountainspring.aquifer.item;

import java.util.function.Supplier;

import com.google.common.base.Suppliers;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public record AquiferToolMaterial(Identifier name, TagKey<Block> inverseTag, int durability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> ingredientSupplier) implements ToolMaterial {
	public static AquiferToolMaterial create(Identifier name, TagKey<Block> inverseTag, int durability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> ingredientSupplier) {
		return new AquiferToolMaterial(name, inverseTag, durability, miningSpeed, attackDamage, enchantability, Suppliers.memoize(ingredientSupplier::get));
	}
	
	public static AquiferToolMaterial create(Identifier name, TagKey<Block> inverseTag, int durability, float miningSpeed, float attackDamage, int enchantability, Item repairIngredient) {
		return create(name, inverseTag, durability, miningSpeed, attackDamage, enchantability, () -> Ingredient.ofItems(repairIngredient));
	}
	
	public static AquiferToolMaterial create(Identifier name, TagKey<Block> inverseTag, int durability, float miningSpeed, float attackDamage, int enchantability, Item... repairIngredient) {
		return create(name, inverseTag, durability, miningSpeed, attackDamage, enchantability, () -> Ingredient.ofItems(repairIngredient));
	}
	
	public static AquiferToolMaterial create(Identifier name, TagKey<Block> inverseTag, int durability, float miningSpeed, float attackDamage, int enchantability, TagKey<Item> repairIngredient) {
		return create(name, inverseTag, durability, miningSpeed, attackDamage, enchantability, () -> Ingredient.fromTag(repairIngredient));
	}
	
	@Override
	public int getDurability() {
		return this.durability();
	}
	
	@Override
	public float getMiningSpeedMultiplier() {
		return this.miningSpeed();
	}
	
	@Override
	public float getAttackDamage() {
		return this.attackDamage();
	}
	
	@Override
	public TagKey<Block> getInverseTag() {
		return this.inverseTag();
	}
	
	@Override
	public int getEnchantability() {
		return this.enchantability();
	}
	
	@Override
	public Ingredient getRepairIngredient() {
		return this.ingredientSupplier().get();
	}
	
	@Override
	public final String toString() {
		return this.name().toString();
	}
}
