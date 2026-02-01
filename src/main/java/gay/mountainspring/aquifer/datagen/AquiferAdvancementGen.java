package gay.mountainspring.aquifer.datagen;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import gay.mountainspring.aquifer.Aquifer;
import gay.mountainspring.aquifer.tag.AquiferTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.ItemCriterion;
import net.minecraft.predicate.BlockPredicate;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

public class AquiferAdvancementGen extends FabricAdvancementProvider {
	public AquiferAdvancementGen(FabricDataOutput output, CompletableFuture<WrapperLookup> registryLookup) {
		super(output, registryLookup);
	}
	
	@Override
	public void generateAdvancement(WrapperLookup registryLookup, Consumer<AdvancementEntry> consumer) {
		consumer.accept(Advancement.Builder.create()
		.criterion("wax_on", ItemCriterion.Conditions.createItemUsedOnBlock(
			LocationPredicate.Builder.create().block(BlockPredicate.Builder.create().tag(AquiferTags.Blocks.COMPLETES_WAX_ON_ADVANCEMENT)),
			ItemPredicate.Builder.create().tag(AquiferTags.Items.WAXABLES)
		))
		.rewards(AdvancementRewards.Builder.function(Identifier.of(Aquifer.MOD_ID, "complete_wax_on")))
		.build(Identifier.of(Aquifer.MOD_ID, "husbandry/wax_on_helper")));
		
		consumer.accept(Advancement.Builder.create()
		.criterion("wax_off", ItemCriterion.Conditions.createItemUsedOnBlock(
			LocationPredicate.Builder.create().block(BlockPredicate.Builder.create().tag(AquiferTags.Blocks.COMPLETES_WAX_OFF_ADVANCEMENT)),
			ItemPredicate.Builder.create().tag(ItemTags.AXES)
		))
		.rewards(AdvancementRewards.Builder.function(Identifier.of(Aquifer.MOD_ID, "complete_wax_off")))
		.build(Identifier.of(Aquifer.MOD_ID, "husbandry/wax_off_helper")));
	}
}