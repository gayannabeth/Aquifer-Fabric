package gay.mountainspring.aquifer.advancement;

import java.util.List;
import java.util.function.UnaryOperator;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.component.ComponentMap;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@ApiStatus.NonExtendable
public interface AquiferAdvancementBuilder {
	default Advancement.Builder addRequirement(String name, AdvancementCriterion<?> criterion) {
		throw new UnsupportedOperationException("Implemented via mixin");
	}
	
	default Advancement.Builder addAlternativeRequirement(String name, AdvancementCriterion<?> criterion) {
		throw new UnsupportedOperationException("Implemented via mixin");
	}
	
	default Advancement.Builder addAlternativeRequirement(String name, AdvancementCriterion<?> criterion, String alternativeOf) {
		throw new UnsupportedOperationException("Implemented via mixin");
	}
	
	default Advancement.Builder addRewards(AdvancementRewards.Builder newRewards) {
		throw new UnsupportedOperationException("Implemented via mixin");
	}
	
	default Advancement.Builder addRewardXp(int xp) {
		throw new UnsupportedOperationException("Implemented via mixin");
	}
	
	default Advancement.Builder addRewardXp(int xp, int max) {
		throw new UnsupportedOperationException("Implemented via mixin");
	}
	
	default Advancement.Builder setRewardXp(int xp) {
		throw new UnsupportedOperationException("Implemented via mixin");
	}
	
	default Advancement.Builder addLoot(List<RegistryKey<LootTable>> tableKeys) {
		throw new UnsupportedOperationException("Implemented via mixin");
	}
	
	default Advancement.Builder addLoot(RegistryKey<LootTable> tableKey) {
		return this.addLoot(List.of(tableKey));
	}
	
	default Advancement.Builder addRecipes(List<Identifier> recipeIDs) {
		throw new UnsupportedOperationException("Implemented via mixin");
	}
	
	default Advancement.Builder addRecipe(Identifier recipeID) {
		return this.addRecipes(List.of(recipeID));
	}
	
	default Advancement.Builder setFunction(Identifier functionID) {
		throw new UnsupportedOperationException("Implemented via mixin");
	}
	
	default Advancement.Builder setFunctionIfAbsent(Identifier functionID) {
		throw new UnsupportedOperationException("Implemented via mixin");
	}
	
	default Advancement.Builder setFrame(AdvancementFrame frame) {
		throw new UnsupportedOperationException("Implemented via mixin");
	}
	
	default Advancement.Builder setShowToast(boolean showToast) {
		throw new UnsupportedOperationException("Implemented via mixin");
	}
	
	default Advancement.Builder setAnnounceToChat(boolean announce) {
		throw new UnsupportedOperationException("Implemented via mixin");
	}
	
	default Advancement.Builder setHidden(boolean hidden) {
		throw new UnsupportedOperationException("Implemented via mixin");
	}
	
	default Advancement.Builder setIconItem(ItemConvertible item) {
		throw new UnsupportedOperationException("Implemented via mixin");
	}
	
	default Advancement.Builder setIconGlint(boolean glint) {
		throw new UnsupportedOperationException("Implemented via mixin");
	}
	
	default Advancement.Builder setIconComponents(ComponentMap.Builder components) {
		throw new UnsupportedOperationException("Implemented via mixin");
	}
	
	default Advancement.Builder setIcon(ItemStack iconStack) {
		throw new UnsupportedOperationException("Implemented via mixin");
	}
	
	default Advancement.Builder setBackground(@Nullable Identifier background) {
		throw new UnsupportedOperationException("Implemented via mixin");
	}
	
	default Advancement.Builder modifyTitle(UnaryOperator<Text> titleModifier) {
		throw new UnsupportedOperationException("Implemented via mixin");
	}
	
	default Advancement.Builder modifyDescription(UnaryOperator<Text> descriptionModifier) {
		throw new UnsupportedOperationException("Implemented via mixin");
	}
	
	default Advancement.Builder modifyTitleAndDescription(UnaryOperator<Text> titleModifier, UnaryOperator<Text> descriptionModifier) {
		return this.modifyTitle(titleModifier).modifyDescription(descriptionModifier);
	}
	
	default Advancement.Builder setTitle(Text title) {
		throw new UnsupportedOperationException("Implemented via mixin");
	}
	
	default Advancement.Builder setDescription(Text description) {
		throw new UnsupportedOperationException("Implemented via mixin");
	}
	
	default Advancement.Builder setTitleAndDescription(Text title, Text description) {
		return this.setTitle(title).setDescription(description);
	}
}