package gay.mountainspring.aquifer.datagen;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.block.enums.SlabType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.TableBonusLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.ConditionalLootFunction;
import net.minecraft.loot.function.LimitCountLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.operator.BoundedIntUnaryOperator;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.LootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.state.property.Properties;

public abstract class AquiferBlockLootTableProvider extends FabricBlockLootTableProvider {
	public AquiferBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<WrapperLookup> registryLookup) {
		super(dataOutput, registryLookup);
	}
	
	public LootCondition.Builder isDoubleSlab(Block block) {
		return BlockStatePropertyLootCondition.builder(block).properties(StatePredicate.Builder.create().exactMatch(Properties.SLAB_TYPE, SlabType.DOUBLE));
	}
	
	public LootTable.Builder dropsWithSilkTouchOrShears(Block block) {
		return LootTable.builder()
				.pool(LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1.0f))
						.with(ItemEntry.builder(block)
								.conditionally(this.createWithShearsOrSilkTouchCondition())));
	}
	
	public LootTable.Builder drops(Block withSilkTouch, ItemConvertible withoutSilkTouch, LootNumberProvider dropCount, Function<RegistryEntry<Enchantment>, ConditionalLootFunction.Builder<?>> bonusAlgorithm, BoundedIntUnaryOperator dropCountLimiter) {
		var impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
		return LootTable.builder()
				.pool(LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1.0f))
						.with(ItemEntry.builder(withSilkTouch)
								.conditionally(this.createSilkTouchCondition())
								.alternatively(this.applyExplosionDecay(withSilkTouch, ItemEntry.builder(withoutSilkTouch)
										.apply(SetCountLootFunction.builder(dropCount))
										.apply(bonusAlgorithm.apply(impl.getOrThrow(Enchantments.FORTUNE)))
										.apply(LimitCountLootFunction.builder(dropCountLimiter))))));
	}
	
	public LootTable.Builder tableBonusDrops(Block withSilkTouch, ItemConvertible withoutSilkTouch, LootNumberProvider dropCount, float... chances) {
		RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
		return this.dropsWithSilkTouch(withSilkTouch,
				this.addSurvivesExplosionCondition(withSilkTouch, ItemEntry.builder(withoutSilkTouch)
						.apply(SetCountLootFunction.builder(dropCount))
						.conditionally(TableBonusLootCondition.builder(impl.getOrThrow(Enchantments.FORTUNE), chances))
						.alternatively(ItemEntry.builder(withSilkTouch))));
	}
	
	public LootTable.Builder tableBonusDrops(Block withSilkTouch, ItemConvertible withoutSilkTouch, float... chances) {
		RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
		return this.dropsWithSilkTouch(withSilkTouch,
				this.addSurvivesExplosionCondition(withSilkTouch, ItemEntry.builder(withoutSilkTouch)
						.conditionally(TableBonusLootCondition.builder(impl.getOrThrow(Enchantments.FORTUNE), chances))
						.alternatively(ItemEntry.builder(withSilkTouch))));
	}
	
	public LootTable.Builder tableBonusSlabDrops(Block withSilkTouch, ItemConvertible withoutSilkTouch, LootNumberProvider doubleDropCount, LootNumberProvider halfDropCount, float... chances) {
		RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
		return this.slabDropsWithSilkTouch(withSilkTouch,
				this.addSurvivesExplosionCondition(withSilkTouch, ItemEntry.builder(withoutSilkTouch)
						.apply(SetCountLootFunction.builder(doubleDropCount)
								.conditionally(this.isDoubleSlab(withSilkTouch)))
						.apply(SetCountLootFunction.builder(halfDropCount)
								.conditionally(this.isDoubleSlab(withSilkTouch).invert()))
						.conditionally(TableBonusLootCondition.builder(impl.getOrThrow(Enchantments.FORTUNE), chances))
						.alternatively(ItemEntry.builder(withSilkTouch))));
	}
	
	public LootTable.Builder tableBonusSlabDrops(Block withSilkTouch, ItemConvertible withoutSilkTouch, float... chances) {
		RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
		return this.slabDropsWithSilkTouch(withSilkTouch,
				this.addSurvivesExplosionCondition(withSilkTouch, ItemEntry.builder(withoutSilkTouch)
						.apply(LootUtil.setCountLootFunctionBuilder(0.0f, 1.0f)
								.conditionally(this.isDoubleSlab(withSilkTouch).invert()))
						.conditionally(TableBonusLootCondition.builder(impl.getOrThrow(Enchantments.FORTUNE), chances))
						.alternatively(ItemEntry.builder(withSilkTouch))));
	}
	
	public LootTable.Builder slabDropsWithSilkTouch(Block block) {
		return LootTable.builder()
				.pool(LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1.0f))
						.with(ItemEntry.builder(block))
						.apply(LootUtil.setCountLootFunctionBuilder(2.0f)
								.conditionally(this.isDoubleSlab(block)))
						.conditionally(this.createSilkTouchCondition()));
	}
	
	public LootTable.Builder slabDropsWithSilkTouch(Block block, LootPoolEntry.Builder<?> loot) {
		return LootTable.builder()
				.pool(LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1.0f))
						.with(ItemEntry.builder(block)
								.apply(LootUtil.setCountLootFunctionBuilder(2.0f)
										.conditionally(this.isDoubleSlab(block)))
								.conditionally(this.createSilkTouchCondition())
								.alternatively(loot)));
	}
	
	public LootTable.Builder slabDropsWithSilkTouchOrShears(Block block) {
		return LootTable.builder()
				.pool(LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1.0f))
						.with(ItemEntry.builder(block)
								.apply(LootUtil.setCountLootFunctionBuilder(2.0f)
										.conditionally(this.isDoubleSlab(block)))
								.conditionally(this.createWithShearsOrSilkTouchCondition())));
	}
	
	public LootTable.Builder slabDropsNoSilk(Block slab, ItemConvertible drop) {
		return LootTable.builder()
				.pool(LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1.0f))
						.with(ItemEntry.builder(drop)
								.apply(LootUtil.setCountLootFunctionBuilder(2.0f)
										.conditionally(this.isDoubleSlab(slab)))));
	}
	
	public LootTable.Builder slabDrops(Block withSilkTouch, ItemConvertible withoutSilkTouch) {
		return LootTable.builder()
				.pool(this.applyExplosionDecay(withSilkTouch, LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1.0f))
						.with(ItemEntry.builder(withSilkTouch)
								.conditionally(this.createSilkTouchCondition())
								.alternatively(ItemEntry.builder(withoutSilkTouch)))
						.apply(LootUtil.setCountLootFunctionBuilder(2.0f)
								.conditionally(this.isDoubleSlab(withSilkTouch)))));
	}
	
	public LootTable.Builder slabDrops(Block withSilkTouch, ItemConvertible withoutSilkTouch, LootNumberProvider doubleDropCount, LootNumberProvider halfDropCount) {
		return LootTable.builder()
				.pool(LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1.0f))
						.with(ItemEntry.builder(withSilkTouch)
								.apply(LootUtil.setCountLootFunctionBuilder(2.0f)
										.conditionally(this.isDoubleSlab(withSilkTouch)))
								.conditionally(this.createSilkTouchCondition())
								.alternatively(this.applyExplosionDecay(withSilkTouch, ItemEntry.builder(withoutSilkTouch)
										.apply(SetCountLootFunction.builder(doubleDropCount)
												.conditionally(this.isDoubleSlab(withSilkTouch)))
										.apply(SetCountLootFunction.builder(halfDropCount)
												.conditionally(this.isDoubleSlab(withSilkTouch).invert()))))));
	}
	
	public LootTable.Builder slabDrops(Block withSilkTouch, ItemConvertible withoutSilkTouch, LootNumberProvider doubleDropCount, LootNumberProvider halfDropCount, Function<RegistryEntry<Enchantment>, ConditionalLootFunction.Builder<?>> bonusAlgorithm, BoundedIntUnaryOperator doubleDropCountLimiter, BoundedIntUnaryOperator halfDropCountLimiter) {
		var impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
		return LootTable.builder()
				.pool(LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1.0f))
						.with(ItemEntry.builder(withSilkTouch)
								.apply(LootUtil.setCountLootFunctionBuilder(2.0f)
										.conditionally(this.isDoubleSlab(withSilkTouch)))
								.conditionally(this.createSilkTouchCondition())
								.alternatively(this.applyExplosionDecay(withSilkTouch, ItemEntry.builder(withoutSilkTouch)
										.apply(SetCountLootFunction.builder(doubleDropCount)
												.conditionally(this.isDoubleSlab(withSilkTouch)))
										.apply(SetCountLootFunction.builder(halfDropCount)
												.conditionally(this.isDoubleSlab(withSilkTouch).invert()))
										.apply(bonusAlgorithm.apply(impl.getOrThrow(Enchantments.FORTUNE)))
										.apply(LimitCountLootFunction.builder(doubleDropCountLimiter)
												.conditionally(this.isDoubleSlab(withSilkTouch)))
										.apply(LimitCountLootFunction.builder(halfDropCountLimiter)
												.conditionally(this.isDoubleSlab(withSilkTouch).invert()))))));
	}
	
	public LootTable.Builder oreSlabDrops(Block withSilkTouch, ItemConvertible drop) {
		return LootTable.builder()
				.pool(LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1.0f))
						.with(ItemEntry.builder(withSilkTouch)
								.apply(LootUtil.setCountLootFunctionBuilder(2.0f)
										.conditionally(this.isDoubleSlab(withSilkTouch)))
								.conditionally(this.createSilkTouchCondition())
								.alternatively(this.applyExplosionDecay(withSilkTouch, ItemEntry.builder(drop)
										.apply(LootUtil.setCountLootFunctionBuilder(0.0f, 1.0f)
												.conditionally(this.isDoubleSlab(withSilkTouch).invert()))
										.apply(ApplyBonusLootFunction.oreDrops(this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE)))
										.apply(LimitCountLootFunction.builder(BoundedIntUnaryOperator.create(0, 2))
												.conditionally(this.isDoubleSlab(withSilkTouch).invert()))))));
	}
	
	public LootTable.Builder copperOreSlabDrops(Block withSilkTouch) {
		return LootTable.builder()
				.pool(LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1.0f))
						.with(ItemEntry.builder(withSilkTouch)
								.apply(LootUtil.setCountLootFunctionBuilder(2.0f)
										.conditionally(this.isDoubleSlab(withSilkTouch)))
								.conditionally(this.createSilkTouchCondition())
								.alternatively(this.applyExplosionDecay(withSilkTouch, ItemEntry.builder(Items.RAW_COPPER)
										.apply(LootUtil.setCountLootFunctionBuilder(2.0f, 5.0f)
												.conditionally(this.isDoubleSlab(withSilkTouch)))
										.apply(LootUtil.setCountLootFunctionBuilder(1.0f, 2.0f)
												.conditionally(this.isDoubleSlab(withSilkTouch).invert()))
										.apply(ApplyBonusLootFunction.oreDrops(this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE)))))));
	}
	
	public LootTable.Builder lapisOreSlabDrops(Block withSilkTouch) {
		return LootTable.builder()
				.pool(LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1.0f))
						.with(ItemEntry.builder(withSilkTouch)
								.apply(LootUtil.setCountLootFunctionBuilder(2.0f)
										.conditionally(this.isDoubleSlab(withSilkTouch)))
								.conditionally(this.createSilkTouchCondition())
								.alternatively(this.applyExplosionDecay(withSilkTouch, ItemEntry.builder(Items.LAPIS_LAZULI)
										.apply(LootUtil.setCountLootFunctionBuilder(4.0f, 9.0f)
												.conditionally(this.isDoubleSlab(withSilkTouch)))
										.apply(LootUtil.setCountLootFunctionBuilder(2.0f, 4.0f)
												.conditionally(this.isDoubleSlab(withSilkTouch).invert()))
										.apply(ApplyBonusLootFunction.oreDrops(this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE)))))));
	}
	
	public LootTable.Builder redstoneOreSlabDrops(Block withSilkTouch) {
		return LootTable.builder()
				.pool(LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1.0f))
						.with(ItemEntry.builder(withSilkTouch)
								.apply(LootUtil.setCountLootFunctionBuilder(2.0f)
										.conditionally(this.isDoubleSlab(withSilkTouch)))
								.conditionally(this.createSilkTouchCondition())
								.alternatively(this.applyExplosionDecay(withSilkTouch, ItemEntry.builder(Items.REDSTONE)
										.apply(LootUtil.setCountLootFunctionBuilder(4.0f, 5.0f)
												.conditionally(this.isDoubleSlab(withSilkTouch)))
										.apply(LootUtil.setCountLootFunctionBuilder(2.0f, 3.0f)
												.conditionally(this.isDoubleSlab(withSilkTouch).invert()))
										.apply(ApplyBonusLootFunction.uniformBonusCount(this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE)))))));
	}
	
	public LootTable.Builder netherGoldOreDrops(Block withSilkTouch) {
		return LootTable.builder()
				.pool(LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1.0f))
						.with(ItemEntry.builder(withSilkTouch)
								.conditionally(this.createSilkTouchCondition())
								.alternatively(this.applyExplosionDecay(withSilkTouch, ItemEntry.builder(Items.GOLD_NUGGET)
										.apply(LootUtil.setCountLootFunctionBuilder(2.0f, 6.0f))
										.apply(ApplyBonusLootFunction.oreDrops(this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE)))))));
	}
	
	public LootTable.Builder netherGoldOreSlabDrops(Block withSilkTouch) {
		return LootTable.builder()
				.pool(LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1.0f))
						.with(ItemEntry.builder(withSilkTouch)
								.apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0f))
										.conditionally(this.isDoubleSlab(withSilkTouch)))
								.conditionally(this.createSilkTouchCondition())
								.alternatively(this.applyExplosionDecay(withSilkTouch, ItemEntry.builder(Items.GOLD_NUGGET)
										.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 6.0f))
												.conditionally(this.isDoubleSlab(withSilkTouch)))
										.apply(LootUtil.setCountLootFunctionBuilder(1.0f, 3.0f)
												.conditionally(this.isDoubleSlab(withSilkTouch).invert()))
										.apply(ApplyBonusLootFunction.uniformBonusCount(this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE)))))));
	}
	
	public LootTable.Builder bookshelfDrops(Block bookshelf) {
		return LootTable.builder()
				.pool(LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1.0f))
						.with(ItemEntry.builder(bookshelf)
								.conditionally(this.createSilkTouchCondition())
								.alternatively(this.applyExplosionDecay(bookshelf, ItemEntry.builder(bookshelf)
										.apply(LootUtil.setCountLootFunctionBuilder(3.0f))))));
	}
}