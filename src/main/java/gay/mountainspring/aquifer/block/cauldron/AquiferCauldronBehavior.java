package gay.mountainspring.aquifer.block.cauldron;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import gay.mountainspring.aquifer.block.AquiferLeveledCauldronExtensions;
import net.minecraft.block.AbstractCauldronBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BannerPatternsComponent;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class AquiferCauldronBehavior {
	private AquiferCauldronBehavior() {}
	
	public static void init() {}
	
	private static void initBucketBehaviors(Map<Item, CauldronBehavior> map) {
		map.put(Items.WATER_BUCKET, AQUIFER_FILL_WITH_WATER);
		map.put(Items.LAVA_BUCKET, AQUIFER_FILL_WITH_LAVA);
		map.put(Items.POWDER_SNOW_BUCKET, AQUIFER_FILL_WITH_POWDER_SNOW);
	}
	
	public static final CauldronBehavior AQUIFER_FILL_WITH_WATER = (state, world, pos, player, hand, stack) -> fillCauldron(state, world, pos, player, hand, stack, group -> group.getWaterStateForLevel(3), SoundEvents.ITEM_BUCKET_EMPTY);
	
	public static final CauldronBehavior AQUIFER_FILL_WITH_LAVA = (state, world, pos, player, hand, stack) -> fillCauldron(state, world, pos, player, hand, stack, group -> group.getLava().getDefaultState(), SoundEvents.ITEM_BUCKET_EMPTY_LAVA);
	
	public static final CauldronBehavior AQUIFER_FILL_WITH_POWDER_SNOW = (state, world, pos, player, hand, stack) -> fillCauldron(state, world, pos, player, hand, stack, group -> group.getPowderSnowStateForLevel(3), SoundEvents.ITEM_BUCKET_EMPTY_POWDER_SNOW);
	
	public static final CauldronBehavior AQUIFER_EMPTY_WATER = (state, world, pos, player, hand, stack) -> emptyCauldron(state, world, pos, player, hand, stack, new ItemStack(Items.WATER_BUCKET), cauldronState -> cauldronState.get(Properties.LEVEL_3) == 3, group -> group.getEmpty().getDefaultState(), SoundEvents.ITEM_BUCKET_FILL);
	
	public static final CauldronBehavior AQUIFER_EMPTY_LAVA = (state, world, pos, player, hand, stack) -> emptyCauldron(state, world, pos, player, hand, stack, new ItemStack(Items.LAVA_BUCKET), cauldronState -> true, group -> group.getEmpty().getDefaultState(), SoundEvents.ITEM_BUCKET_FILL_LAVA);
	
	public static final CauldronBehavior AQUIFER_EMPTY_POWDER_SNOW = (state, world, pos, player, hand, stack) -> emptyCauldron(state, world, pos, player, hand, stack, new ItemStack(Items.POWDER_SNOW_BUCKET), cauldronState -> cauldronState.get(Properties.LEVEL_3) == 3, group -> group.getEmpty().getDefaultState(), SoundEvents.ITEM_BUCKET_FILL_POWDER_SNOW);
	
	private static CauldronBehavior NON_WATER_POTION_ON_EMPTY_BEHAVIOR = (state, world, pos, player, hand, stack) -> ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
	
	public static void setNonWaterPotionOnEmptyBehavior(CauldronBehavior behavior) {
		NON_WATER_POTION_ON_EMPTY_BEHAVIOR = behavior;
	}
	
	public static final CauldronBehavior AQUIFER_FILL_EMPTY_FROM_POTION = (state, world, pos, player, hand, stack) -> {
		PotionContentsComponent potionContents = stack.get(DataComponentTypes.POTION_CONTENTS);
		if (potionContents != null && state.getBlock() instanceof AbstractCauldronBlock cauldron) {
			if (potionContents.matches(Potions.WATER)) {
				BlockState newState = cauldron.aquifer$getGroup().getWaterStateForLevel(1);
				
				if (!world.isClient) {
					Item item = stack.getItem();
					player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, new ItemStack(Items.GLASS_BOTTLE)));
					player.incrementStat(Stats.USE_CAULDRON);
					player.incrementStat(Stats.USED.getOrCreateStat(item));
					world.setBlockState(pos, newState);
					world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0f, 1.0f);
					world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
				}
				
				return ItemActionResult.success(world.isClient);
			} else {
				return NON_WATER_POTION_ON_EMPTY_BEHAVIOR.interact(state, world, pos, player, hand, stack);
			}
		} else {
			return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
		}
	};
	
	public static final CauldronBehavior AQUIFER_FILL_EXISTING_WITH_WATER_FROM_WATER_BOTTLE = (state, world, pos, player, hand, stack) -> {
		PotionContentsComponent potionContents = stack.get(DataComponentTypes.POTION_CONTENTS);
		if (potionContents != null && potionContents.matches(Potions.WATER) && state.getBlock() instanceof AquiferLeveledCauldronExtensions cauldron) {
			if (!world.isClient) {
				if (cauldron.aquifer$incrementFluidLevel(state, world, pos)) {
					Item item = stack.getItem();
					player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, new ItemStack(Items.GLASS_BOTTLE)));
					player.incrementStat(Stats.USE_CAULDRON);
					player.incrementStat(Stats.USED.getOrCreateStat(item));
					world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
					world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
				} else {
					return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
				}
			}
			
			return ItemActionResult.success(world.isClient);
		} else {
			return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
		}
	};
	
	public static final CauldronBehavior AQUIFER_EMPTY_WATER_WITH_GLASS_BOTTLE = (state, world, pos, player, hand, stack) -> {
		if (!world.isClient) {
			Item item = stack.getItem();
			player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, PotionContentsComponent.createStack(Items.POTION, Potions.WATER)));
			player.incrementStat(Stats.USE_CAULDRON);
			player.incrementStat(Stats.USED.getOrCreateStat(item));
			if (state.getBlock() instanceof AquiferLeveledCauldronExtensions cauldron)
				cauldron.aquifer$decrementFluidLevel(state, world, pos);
			world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
			world.emitGameEvent(null, GameEvent.FLUID_PICKUP, pos);
		}
		
		return ItemActionResult.success(world.isClient);
	};
	
	public static final CauldronBehavior AQUIFER_CLEAN_SHULKER_BOX = (state, world, pos, player, hand, stack) -> {
		Block block = Block.getBlockFromItem(stack.getItem());
		if (!(block instanceof ShulkerBoxBlock)) {
			return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
		} else {
			if (!world.isClient) {
				ItemStack itemStack = stack.copyComponentsToNewStack(Blocks.SHULKER_BOX, 1);
				player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, itemStack, false));
				player.incrementStat(Stats.CLEAN_SHULKER_BOX);
				if (state.getBlock() instanceof AquiferLeveledCauldronExtensions cauldron)
					cauldron.aquifer$decrementFluidLevel(state, world, pos);
			}
			
			return ItemActionResult.success(world.isClient);
		}
	};
	
	public static final CauldronBehavior AQUIFER_CLEAN_BANNER = (state, world, pos, player, hand, stack) -> {
		BannerPatternsComponent bannerPatternsComponent = stack.getOrDefault(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT);
		if (bannerPatternsComponent.layers().isEmpty()) {
			return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
		} else {
			if (!world.isClient) {
				ItemStack itemStack = stack.copyWithCount(1);
				itemStack.set(DataComponentTypes.BANNER_PATTERNS, bannerPatternsComponent.withoutTopLayer());
				player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, itemStack, false));
				player.incrementStat(Stats.CLEAN_BANNER);
				if (state.getBlock() instanceof AquiferLeveledCauldronExtensions cauldron)
					cauldron.aquifer$decrementFluidLevel(state, world, pos);
			}
			
			return ItemActionResult.success(world.isClient);
		}
	};
	
	public static final CauldronBehavior AQUIFER_CLEAN_DYEABLE_ITEM = (state, world, pos, player, hand, stack) -> {
		if (!stack.isIn(ItemTags.DYEABLE)) {
			return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
		} else if (!stack.contains(DataComponentTypes.DYED_COLOR)) {
			return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
		} else {
			if (!world.isClient) {
				stack.remove(DataComponentTypes.DYED_COLOR);
				player.incrementStat(Stats.CLEAN_ARMOR);
				if (state.getBlock() instanceof AquiferLeveledCauldronExtensions cauldron)
					cauldron.aquifer$decrementFluidLevel(state, world, pos);
			}
			
			return ItemActionResult.success(world.isClient);
		}
	};
	
	private static ItemActionResult fillCauldron(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack, Function<CauldronGroup, BlockState> filledCauldronGetter, SoundEvent sound) {
		BlockState filledState = state;
		
		if (state.getBlock() instanceof AbstractCauldronBlock cauldron) {
			filledState = filledCauldronGetter.apply(cauldron.aquifer$getGroup());
		}
		
		if (!world.isClient) {
			Item item = stack.getItem();
			player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, new ItemStack(Items.BUCKET)));
			player.incrementStat(Stats.FILL_CAULDRON);
			player.incrementStat(Stats.USED.getOrCreateStat(item));
			world.setBlockState(pos, filledState);
			world.playSound(null, pos, sound, SoundCategory.BLOCKS, 1.0F, 1.0F);
			world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
		}

		return ItemActionResult.success(world.isClient);
	}
	
	private static ItemActionResult emptyCauldron(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack, ItemStack output, Predicate<BlockState> fullPredicate, Function<CauldronGroup, BlockState> emptyStateGetter, SoundEvent sound) {
		if (!fullPredicate.test(state)) {
			return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
		} else {
			if (!world.isClient) {
				Item item = stack.getItem();
				player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, output));
				player.incrementStat(Stats.USE_CAULDRON);
				player.incrementStat(Stats.USED.getOrCreateStat(item));
				if (state.getBlock() instanceof AbstractCauldronBlock cauldron)
					world.setBlockState(pos, emptyStateGetter.apply(cauldron.aquifer$getGroup()));
				world.playSound(null, pos, sound, SoundCategory.BLOCKS, 1.0F, 1.0F);
				world.emitGameEvent(null, GameEvent.FLUID_PICKUP, pos);
			}
			
			return ItemActionResult.success(world.isClient);
		}
	}
	
	static {
		var emptyMap = CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.map();
		initBucketBehaviors(emptyMap);
		emptyMap.put(Items.POTION, AQUIFER_FILL_EMPTY_FROM_POTION);
		
		var waterMap = CauldronBehavior.WATER_CAULDRON_BEHAVIOR.map();
		initBucketBehaviors(waterMap);
		waterMap.put(Items.BUCKET, AQUIFER_EMPTY_WATER);
		waterMap.put(Items.GLASS_BOTTLE, AQUIFER_EMPTY_WATER_WITH_GLASS_BOTTLE);
		waterMap.put(Items.POTION, AQUIFER_FILL_EXISTING_WITH_WATER_FROM_WATER_BOTTLE);
		waterMap.put(Items.LEATHER_BOOTS, AQUIFER_CLEAN_DYEABLE_ITEM);
		waterMap.put(Items.LEATHER_LEGGINGS, AQUIFER_CLEAN_DYEABLE_ITEM);
		waterMap.put(Items.LEATHER_CHESTPLATE, AQUIFER_CLEAN_DYEABLE_ITEM);
		waterMap.put(Items.LEATHER_HELMET, AQUIFER_CLEAN_DYEABLE_ITEM);
		waterMap.put(Items.LEATHER_HORSE_ARMOR, AQUIFER_CLEAN_DYEABLE_ITEM);
		waterMap.put(Items.WOLF_ARMOR, AQUIFER_CLEAN_DYEABLE_ITEM);
		waterMap.put(Items.WHITE_BANNER, AQUIFER_CLEAN_BANNER);
		waterMap.put(Items.GRAY_BANNER, AQUIFER_CLEAN_BANNER);
		waterMap.put(Items.BLACK_BANNER, AQUIFER_CLEAN_BANNER);
		waterMap.put(Items.BLUE_BANNER, AQUIFER_CLEAN_BANNER);
		waterMap.put(Items.BROWN_BANNER, AQUIFER_CLEAN_BANNER);
		waterMap.put(Items.CYAN_BANNER, AQUIFER_CLEAN_BANNER);
		waterMap.put(Items.GREEN_BANNER, AQUIFER_CLEAN_BANNER);
		waterMap.put(Items.LIGHT_BLUE_BANNER, AQUIFER_CLEAN_BANNER);
		waterMap.put(Items.LIGHT_GRAY_BANNER, AQUIFER_CLEAN_BANNER);
		waterMap.put(Items.LIME_BANNER, AQUIFER_CLEAN_BANNER);
		waterMap.put(Items.MAGENTA_BANNER, AQUIFER_CLEAN_BANNER);
		waterMap.put(Items.ORANGE_BANNER, AQUIFER_CLEAN_BANNER);
		waterMap.put(Items.PINK_BANNER, AQUIFER_CLEAN_BANNER);
		waterMap.put(Items.PURPLE_BANNER, AQUIFER_CLEAN_BANNER);
		waterMap.put(Items.RED_BANNER, AQUIFER_CLEAN_BANNER);
		waterMap.put(Items.YELLOW_BANNER, AQUIFER_CLEAN_BANNER);
		waterMap.put(Items.WHITE_SHULKER_BOX, AQUIFER_CLEAN_SHULKER_BOX);
		waterMap.put(Items.GRAY_SHULKER_BOX, AQUIFER_CLEAN_SHULKER_BOX);
		waterMap.put(Items.BLACK_SHULKER_BOX, AQUIFER_CLEAN_SHULKER_BOX);
		waterMap.put(Items.BLUE_SHULKER_BOX, AQUIFER_CLEAN_SHULKER_BOX);
		waterMap.put(Items.BROWN_SHULKER_BOX, AQUIFER_CLEAN_SHULKER_BOX);
		waterMap.put(Items.CYAN_SHULKER_BOX, AQUIFER_CLEAN_SHULKER_BOX);
		waterMap.put(Items.GREEN_SHULKER_BOX, AQUIFER_CLEAN_SHULKER_BOX);
		waterMap.put(Items.LIGHT_BLUE_SHULKER_BOX, AQUIFER_CLEAN_SHULKER_BOX);
		waterMap.put(Items.LIGHT_GRAY_SHULKER_BOX, AQUIFER_CLEAN_SHULKER_BOX);
		waterMap.put(Items.LIME_SHULKER_BOX, AQUIFER_CLEAN_SHULKER_BOX);
		waterMap.put(Items.MAGENTA_SHULKER_BOX, AQUIFER_CLEAN_SHULKER_BOX);
		waterMap.put(Items.ORANGE_SHULKER_BOX, AQUIFER_CLEAN_SHULKER_BOX);
		waterMap.put(Items.PINK_SHULKER_BOX, AQUIFER_CLEAN_SHULKER_BOX);
		waterMap.put(Items.PURPLE_SHULKER_BOX, AQUIFER_CLEAN_SHULKER_BOX);
		waterMap.put(Items.RED_SHULKER_BOX, AQUIFER_CLEAN_SHULKER_BOX);
		waterMap.put(Items.YELLOW_SHULKER_BOX, AQUIFER_CLEAN_SHULKER_BOX);
		
		var lavaMap = CauldronBehavior.LAVA_CAULDRON_BEHAVIOR.map();
		initBucketBehaviors(lavaMap);
		lavaMap.put(Items.BUCKET, AQUIFER_EMPTY_LAVA);
		
		var powderSnowMap = CauldronBehavior.POWDER_SNOW_CAULDRON_BEHAVIOR.map();
		initBucketBehaviors(powderSnowMap);
		powderSnowMap.put(Items.BUCKET, AQUIFER_EMPTY_POWDER_SNOW);
	}
}