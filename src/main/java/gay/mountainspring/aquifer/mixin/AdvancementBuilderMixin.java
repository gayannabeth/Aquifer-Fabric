package gay.mountainspring.aquifer.mixin;

import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import gay.mountainspring.aquifer.advancement.AquiferAdvancementBuilder;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.Advancement.Builder;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementDisplay;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.function.LazyContainer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@Mixin(Advancement.Builder.class)
public class AdvancementBuilderMixin implements AquiferAdvancementBuilder {
	@Shadow
	AdvancementRewards rewards;
	
	@Shadow
	Optional<AdvancementRequirements> requirements;
	
	@Shadow
	Optional<AdvancementDisplay> display;
	
	@Shadow
	private ImmutableMap.Builder<String, AdvancementCriterion<?>> criteria;
	
	@Shadow
	private AdvancementRequirements.CriterionMerger merger;
	
	@Override
	public Builder addRequirement(String name, AdvancementCriterion<?> criterion) {
		if (this.criteria.build().containsKey(name))
			throw new IllegalArgumentException(String.format("Criterion with name %s already exists!", name));
		
		this.criteria.put(name, criterion);
		if (this.requirements.isPresent()) {
			List<List<String>> list = Lists.newArrayList();
			list.addAll(this.requirements.get().requirements());
			list.add(Lists.newArrayList(name));
			((Advancement.Builder) (Object) this).requirements(new AdvancementRequirements(list));
		}
		return (Advancement.Builder) (Object) this;
	}
	
	@Override
	public Builder addAlternativeRequirement(String name, AdvancementCriterion<?> criterion) {
		if (this.criteria.build().containsKey(name))
			throw new IllegalArgumentException(String.format("Criterion with name %s already exists!", name));
		
		this.criteria.put(name, criterion);
		
		if (this.requirements.isPresent()) {
			List<List<String>> list = Lists.newArrayList();
			for (List<String> sublist : this.requirements.get().requirements()) {
				list.add(Lists.newArrayList(sublist));
			}
			if (list.size() > 0) {
				list.get(0).add(name);
			} else {
				list.add(Lists.newArrayList(name));
			}
			
			((Advancement.Builder) (Object) this).requirements(new AdvancementRequirements(list));
		} else if (this.merger != AdvancementRequirements.CriterionMerger.OR) {
			((Advancement.Builder) (Object) this).requirements(this.merger.create(this.criteria.build().keySet()));
			List<List<String>> list = Lists.newArrayList();
			for (List<String> sublist : this.requirements.get().requirements()) {
				list.add(Lists.newArrayList(sublist));
			}
			if (list.size() > 0) {
				list.get(0).add(name);
			} else {
				list.add(Lists.newArrayList(name));
			}
			
			((Advancement.Builder) (Object) this).requirements(new AdvancementRequirements(list));
		}
		
		return (Advancement.Builder) (Object) this;
	}
	
	@Override
	public Builder addAlternativeRequirement(String name, AdvancementCriterion<?> criterion, String alternativeOf) {
		if (this.criteria.build().containsKey(name))
			throw new IllegalArgumentException(String.format("Criterion with name %s already exists!", name));
		
		this.criteria.put(name, criterion);
		
		if (this.requirements.isPresent()) {
			List<List<String>> list = Lists.newArrayList();
			for (List<String> sublist : this.requirements.get().requirements()) {
				list.add(Lists.newArrayList(sublist));
			}
			if (list.size() > 0) {
				boolean found = false;
				for (List<String> sublist : list) {
					if (sublist.contains(alternativeOf)) {
						sublist.add(name);
						found = true;
						break;
					}
				}
				if (!found)
					list.add(Lists.newArrayList(name));
			} else {
				list.add(Lists.newArrayList(name));
			}
			
			((Advancement.Builder) (Object) this).requirements(new AdvancementRequirements(list));
		} else if (this.merger != AdvancementRequirements.CriterionMerger.OR) {
			((Advancement.Builder) (Object) this).requirements(this.merger.create(this.criteria.build().keySet()));
			List<List<String>> list = Lists.newArrayList();
			for (List<String> sublist : this.requirements.get().requirements()) {
				list.add(Lists.newArrayList(sublist));
			}
			if (list.size() > 0) {
				boolean found = false;
				for (List<String> sublist : list) {
					if (sublist.contains(alternativeOf)) {
						sublist.add(name);
						found = true;
						break;
					}
				}
				if (!found)
					list.add(Lists.newArrayList(name));
			} else {
				list.add(Lists.newArrayList(name));
			}
			
			((Advancement.Builder) (Object) this).requirements(new AdvancementRequirements(list));
		}
		
		return (Advancement.Builder) (Object) this;
	}
	
	@Override
	public Builder addRewards(AdvancementRewards.Builder newRewards) {
		AdvancementRewards newRewardsBuilt = newRewards.build();
		AdvancementRewards.Builder builder = new AdvancementRewards.Builder();
		builder.setExperience(this.rewards.experience() + newRewardsBuilt.experience());
		if (newRewardsBuilt.function().isPresent())
			builder.setFunction(newRewardsBuilt.function().get().getId());
		else if (this.rewards.function().isPresent())
			builder.setFunction(this.rewards.function().get().getId());
		for (RegistryKey<LootTable> tableKey : newRewardsBuilt.loot()) {
			builder.addLoot(tableKey);
		}
		for (RegistryKey<LootTable> tableKey : this.rewards.loot()) {
			builder.addLoot(tableKey);
		}
		for (Identifier recipeID : newRewardsBuilt.recipes()) {
			builder.addRecipe(recipeID);
		}
		for (Identifier recipeID : this.rewards.recipes()) {
			builder.addRecipe(recipeID);
		}
		
		return ((Advancement.Builder) (Object) this).rewards(builder);
	}
	
	@Override
	public Builder addRewardXp(int xp) {
		return ((Advancement.Builder) (Object) this).rewards(new AdvancementRewards(xp + this.rewards.experience(), this.rewards.loot(), this.rewards.recipes(), this.rewards.function()));
	}
	
	@Override
	public Builder addRewardXp(int xp, int max) {
		return ((Advancement.Builder) (Object) this).rewards(new AdvancementRewards(Math.min(max, xp + this.rewards.experience()), this.rewards.loot(), this.rewards.recipes(), this.rewards.function()));
	}
	
	@Override
	public Builder setRewardXp(int xp) {
		return ((Advancement.Builder) (Object) this).rewards(new AdvancementRewards(xp, this.rewards.loot(), this.rewards.recipes(), this.rewards.function()));
	}
	
	@Override
	public Builder addLoot(List<RegistryKey<LootTable>> tableKeys) {
		AdvancementRewards.Builder builder = new AdvancementRewards.Builder();
		builder.setExperience(this.rewards.experience());
		for (RegistryKey<LootTable> tableKey : this.rewards.loot()) {
			builder.addLoot(tableKey);
		}
		for (RegistryKey<LootTable> tableKey : tableKeys) {
			builder.addLoot(tableKey);
		}
		for (Identifier recipeID : this.rewards.recipes()) {
			builder.addRecipe(recipeID);
		}
		if (this.rewards.function().isPresent()) {
			builder.setFunction(this.rewards.function().get().getId());
		}
		return ((Advancement.Builder) (Object) this).rewards(builder);
	}
	
	@Override
	public Builder addRecipes(List<Identifier> recipeIDs) {
		AdvancementRewards.Builder builder = new AdvancementRewards.Builder();
		builder.setExperience(this.rewards.experience());
		for (RegistryKey<LootTable> tableKey : this.rewards.loot()) {
			builder.addLoot(tableKey);
		}
		for (Identifier recipeID : this.rewards.recipes()) {
			builder.addRecipe(recipeID);
		}
		for (Identifier recipeID : recipeIDs) {
			builder.addRecipe(recipeID);
		}
		if (this.rewards.function().isPresent()) {
			builder.setFunction(this.rewards.function().get().getId());
		}
		return ((Advancement.Builder) (Object) this).rewards(builder);
	}
	
	@Override
	public Builder setFunction(Identifier functionID) {
		return ((Advancement.Builder) (Object) this).rewards(new AdvancementRewards(this.rewards.experience(), this.rewards.loot(), this.rewards.recipes(), Optional.of(new LazyContainer(functionID))));
	}
	
	@Override
	public Builder setFunctionIfAbsent(Identifier functionID) {
		if (this.rewards.function().isEmpty())
			return this.setFunction(functionID);
		else
			return (Advancement.Builder) (Object) this;
	}
	
	@Override
	public Builder setFrame(AdvancementFrame frame) {
		if (this.display.isEmpty()) {
			throw new IllegalStateException("Display must exist to change the frame!");
		}
		AdvancementDisplay display = this.display.get();
		return ((Advancement.Builder) (Object) this).display(display.getIcon(), display.getTitle(), display.getDescription(), display.getBackground().orElse(null), frame, display.shouldShowToast(), display.shouldAnnounceToChat(), display.isHidden());
	}
	
	@Override
	public Builder setShowToast(boolean showToast) {
		if (this.display.isEmpty()) {
			throw new IllegalStateException("Display must exist to change if toast is shown!");
		}
		AdvancementDisplay display = this.display.get();
		return ((Advancement.Builder) (Object) this).display(display.getIcon(), display.getTitle(), display.getDescription(), display.getBackground().orElse(null), display.getFrame(), showToast, display.shouldAnnounceToChat(), display.isHidden());
	}
	
	@Override
	public Builder setAnnounceToChat(boolean announce) {
		if (this.display.isEmpty()) {
			throw new IllegalStateException("Display must exist to change if advancment is shown in chat!");
		}
		AdvancementDisplay display = this.display.get();
		return ((Advancement.Builder) (Object) this).display(display.getIcon(), display.getTitle(), display.getDescription(), display.getBackground().orElse(null), display.getFrame(), display.shouldShowToast(), announce, display.isHidden());
	}
	
	@Override
	public Builder setHidden(boolean hidden) {
		if (this.display.isEmpty()) {
			throw new IllegalStateException("Display must exist to change if advancement is hidden!");
		}
		AdvancementDisplay display = this.display.get();
		return ((Advancement.Builder) (Object) this).display(display.getIcon(), display.getTitle(), display.getDescription(), display.getBackground().orElse(null), display.getFrame(), display.shouldShowToast(), display.shouldAnnounceToChat(), hidden);
	}
	
	@Override
	public Builder setIconItem(ItemConvertible item) {
		if (this.display.isEmpty()) {
			throw new IllegalStateException("Display must exist to change icon!");
		}
		AdvancementDisplay display = this.display.get();
		return ((Advancement.Builder) (Object) this).display(new ItemStack(item.asItem()), display.getTitle(), display.getDescription(), display.getBackground().orElse(null), display.getFrame(), display.shouldShowToast(), display.shouldAnnounceToChat(), display.isHidden());
	}
	
	@Override
	public Builder setIconGlint(boolean glint) {
		if (this.display.isEmpty()) {
			throw new IllegalStateException("Display must exist to change icon!");
		}
		AdvancementDisplay display = this.display.get();
		ItemStack newIcon = display.getIcon().copy();
		newIcon.set(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, glint);
		return ((Advancement.Builder) (Object) this).display(newIcon, display.getTitle(), display.getDescription(), display.getBackground().orElse(null), display.getFrame(), display.shouldShowToast(), display.shouldAnnounceToChat(), display.isHidden());
	}
	
	@Override
	public Builder setIconComponents(ComponentMap.Builder components) {
		if (this.display.isEmpty()) {
			throw new IllegalStateException("Display must exist to change icon!");
		}
		AdvancementDisplay display = this.display.get();
		ItemStack newIcon = display.getIcon().copy();
		newIcon.applyComponentsFrom(components.build());
		return ((Advancement.Builder) (Object) this).display(newIcon, display.getTitle(), display.getDescription(), display.getBackground().orElse(null), display.getFrame(), display.shouldShowToast(), display.shouldAnnounceToChat(), display.isHidden());
	}
	
	@Override
	public Builder setIcon(ItemStack iconStack) {
		if (this.display.isEmpty()) {
			throw new IllegalStateException("Display must exist to change icon!");
		}
		AdvancementDisplay display = this.display.get();
		return ((Advancement.Builder) (Object) this).display(iconStack, display.getTitle(), display.getDescription(), display.getBackground().orElse(null), display.getFrame(), display.shouldShowToast(), display.shouldAnnounceToChat(), display.isHidden());
	}
	
	@Override
	public Builder setBackground(@Nullable Identifier background) {
		if (this.display.isEmpty()) {
			throw new IllegalStateException("Display must exist to change icon!");
		}
		AdvancementDisplay display = this.display.get();
		return ((Advancement.Builder) (Object) this).display(display.getIcon(), display.getTitle(), display.getDescription(), background, display.getFrame(), display.shouldShowToast(), display.shouldAnnounceToChat(), display.isHidden());
	}
	
	@Override
	public Builder modifyTitle(UnaryOperator<Text> titleModifier) {
		if (this.display.isEmpty()) {
			throw new IllegalStateException("Display must exist to change icon!");
		}
		AdvancementDisplay display = this.display.get();
		return ((Advancement.Builder) (Object) this).display(display.getIcon(), titleModifier.apply(display.getTitle()), display.getDescription(), display.getBackground().orElse(null), display.getFrame(), display.shouldShowToast(), display.shouldAnnounceToChat(), display.isHidden());
	}
	
	@Override
	public Builder modifyDescription(UnaryOperator<Text> descriptionModifier) {
		if (this.display.isEmpty()) {
			throw new IllegalStateException("Display must exist to change icon!");
		}
		AdvancementDisplay display = this.display.get();
		return ((Advancement.Builder) (Object) this).display(display.getIcon(), display.getTitle(), descriptionModifier.apply(display.getDescription()), display.getBackground().orElse(null), display.getFrame(), display.shouldShowToast(), display.shouldAnnounceToChat(), display.isHidden());
	}
	
	@Override
	public Builder setTitle(Text title) {
		if (this.display.isEmpty()) {
			throw new IllegalStateException("Display must exist to change icon!");
		}
		AdvancementDisplay display = this.display.get();
		return ((Advancement.Builder) (Object) this).display(display.getIcon(), title, display.getDescription(), display.getBackground().orElse(null), display.getFrame(), display.shouldShowToast(), display.shouldAnnounceToChat(), display.isHidden());
	}
	
	@Override
	public Builder setDescription(Text description) {
		if (this.display.isEmpty()) {
			throw new IllegalStateException("Display must exist to change icon!");
		}
		AdvancementDisplay display = this.display.get();
		return ((Advancement.Builder) (Object) this).display(display.getIcon(), display.getTitle(), description, display.getBackground().orElse(null), display.getFrame(), display.shouldShowToast(), display.shouldAnnounceToChat(), display.isHidden());
	}
}