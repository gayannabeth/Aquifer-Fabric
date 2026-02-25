# Aquifer-Fabric
Aquifer is a library mod containing shared code for other Mountain Spring mods as well as other useful features.

Aquifer is intended to be used with other mods, however using it standalone is ok. The mod contains several tags to help extend and modify various hardcoded behaviors, some (but not all) of which were added in Minecraft 26.1.
## Tags Added
All added tags use the namespace `aquifer`.
### Blocks
#### `bars`
For 'bars' blocks.
#### `chains`
For chains.
#### `ladders`
For ladders. This tag is also added to the `minecraft:climbable` tag.
#### `bookshelves`
For bookshelves. This tag is also added to the `minecraft:enchantment_power_provider` tag. Blocks in this tag are also flammable.
#### `crafting_tables`
For crafting tables. Any custom crafting table blocks must be added to this tag for the gui to function properly.
#### `looms`
For looms. Any custom loom blocks must be added to this tag for the gui to function properly.
#### `cartography_tables`
For cartography tables. Any custom cartography table blocks must be added to this tag for the gui to function properly.
#### `fletching_tables`
For fletching tables. Since there is no gui, this tag doesn't do much yet.
#### `smithing_tables`
For smithing tables. Any custom smithing table blocks must be added to this tag for the gui to function properly.
#### `tripwire_hooks`
For tripwire hooks. Any custom tripwire hook blocks must be added to this tag to function properly.
#### `crafted_beehives`
For beehives created via crafting. Does not include the `minecraft:bee_nest` block that generates on trees.
#### `lecterns`
For lecterns.
#### `composters`
For composters.
#### `enchating_tables`
For enchating tables. Any custom enchanting table blocks must be added to this tag for the gui to function properly.
#### `furnaces`
For furnaces.
#### `smokers`
For smokers.
#### `blast_furnaces`
For blast furnaces.
#### `stonecutters`
For stonecutters. Any custom stonecutter blocks must be added to this tag for the gui to function properly.
#### `grindstones`
For grindstones. Any custom grindstone blocks must be added to this tag for the gui to function properly.
#### `chiseled_bookshelves`
For chiseled bookshlves.
#### `wooden_walls`
For wall blocks made out of wood.
#### `wooden_fence_gates`
For fence gates made out of wood.
#### `wooden_chests`
For chests made out of wood.
#### `wooden_barrels`
For barrels made out of wood.
#### `wooden_ladders`
For ladder made out of wood.
#### `all_wooden_signs`
For *all* signs made out of wood.
#### `wooden_signs`
For non-hanging signs made out of wood.
#### `wooden_standing_signs`
For standing signs made out of wood.
#### `wooden_wall_signs`
For signs on a the side of a block made out of wood.
#### `wooden_hanging_signs`
For all hanging signs made out of wood.
#### `wooden_ceiling_hanging_signs`
For signs hanging from a block above made out of wood.
#### `wooden_wall_hanging_signs`
For signs hanging from the side of a block made out of wood.
#### `leaf_slabs`
For slabs made out of leaves.
#### `leaf_stairs`
For stairs made out of leaves.
#### `leaf_walls`
For walls made out of leaves.
#### `leaf_carpets`
For carpets made out of leaves.
#### `completes_wax_on_advancement`
A helper tag for any blocks that should trigger the `minecraft:husbandry/wax_on` advancement when right clicked with `minecraft:honeycomb` or other items in the `aquifer:wax_items` tag (see below).
#### `completes_wax_off_advancement`
A helper tag for any blocks that should trigger the `minecraft:husbandry/wax_off` advancement when right clicked with any axe.
#### `dirt_like`
For any blocks that function similar to blocks in the `minecraft:dirt` block tag, but for whatever reason should not be included in that tag. Blocks in this tag support plants that blocks in the `minecraft:dirt` tag support.
#### `farmland_like`
For any block that function similar to `minecraft:farmland`. Blocks in this tag support crops.
#### `supports_nether_wart`
Backport of the tag by the same name from 26.1.
#### `supports_azalea`
Backport of the tag by the same name from 26.1.
#### `supports_nether_plant`
Backport of three tags in one from 26.1: `minecraft:supports_crimson_roots`, `minecraft:supports_warped_roots`, and `minecraft:supports_nether_sprouts`.
#### `supports_nether_fungus`
Backport of two tags in one from 26.1: `minecraft:supports_crimson_fungus` and `minecraft:supports_warped_fungus`.
#### `supports_wither_rose`
Backport of the tag by the same name from 26.1.
#### `supports_sugar_cane`
Backport of the tag by the same name from 26.1.
#### `supports_sugar_cane_adjacently`
Backport of the tag by the same name from 26.1.
#### `supports_propagule`
Backport of the `minecraft:supports_mangrove_propagule` tag from 26.1.
#### `supports_propagule_above`
Backport of the `minecraft:supports_hanging_mangrove_propagule` tag from 26.1.
#### `supports_cocoa`
Backport of the tag by the same name from 26.1.
#### `supports_chorus`
Single tag for block that, besides `minecraft:chorus_plant`, should support chorus plants and flowers. Sort of a backport of `minecraft:supports_chorus_flower` and `minecraft:supports_chorus_plant` from 26.1.
#### `supports_cactus`
Backport of the tag by the same name from 26.1.
#### `does_not_support_seagrass`
Backport of the `minecraft:cannot_support_seagrass` tag from 26.1.
#### `does_not_support_kelp`
Backport of the `minecraft:cannot_support_kelp` tag from 26.1.
#### `supports_lily_pad`
Backport of the tag by the same name from 26.1.
#### `supports_frogspawn`
Backport of the tag by the same name from 26.1.
#### `cannot_connect_to`
Tag for blocks with full faces that fences, walls, glass panes, and similar horizontally connecting blocks should not connect to.
#### `supports_end_crystal`
Tag for blocks that end crystal can be placed on top of.
#### `signal_fire_base_blocks`
Tag for blocks that cause the smoke from a campfire to go higher.
#### `conduit_activating_blocks`
Tag for blocks that power conduits.
#### `supports_bubble_column/up`
Backport of the `minecraft:enables_bubble_column_push_up` tag from 26.1.
#### `supports_bubble_column/down`
Backport of the `minecraft:enables_bubble_column_drag_down` tag from 26.1.
#### `mineable/shears`
Mineable tag for blocks that can be mined efficiently with shears.
#### `mineable/shears/fast`
Mineable tag for non-instant mined blocks that get mined at the fastest speed by shears, such as leaves.
#### `mineable/shears/medium`
Mineable tag for non-instant mined blocks that get mined at the middle speed by shears, such as wool.
#### `mineable/shears/slow`
Mineable tag for non-instant mined blocks that get mined at the slowest speed by shears, such as vines and glow lichen.
#### `infestable`
Blocks that silverfish can hide in.
#### `infested`
Block that silverfish have hidden in.
### Items
#### Copied from block tags:
`bars`
`chains`
`ladders`
`bookshelves` (can smelt 1.5 items)
`crafting_tables` (can smelt 1.5 items)
`looms` (can smelt 1.5 items)
`cartography_tables` (can smelt 1.5 items)
`fletching_tables` (can smelt 1.5 items)
`smithing_tables` (can smelt 1.5 items)
`tripwire_hooks`
`crafted_beehives`
`lecterns` (can smelt 1.5 items)
`composters` (can smelt 1.5 items)
`enchanting_tables`
`furnaces`
`smokers`
`blast_furnaces`
`stonecutters`
`grindstones`
`chiseled_bookshelves`
`wooden_walls` (can smelt 1.5 items)
`wooden_fence_gates` (can smelt 1.5 items; the vanilla `minecraft:fence_gates` tag is no longer used for furnace fuel)
`wooden_chests` (can smelt 1.5 items)
`wooden_barrels` (can smelt 1.5 items)
`wooden_ladders` (can smelt 1.5 items)
`leaf_slabs`
`leaf_stairs`
`leaf_walls`
`leaf_carpets`
`infestable`
`infested`
#### `wooden_signs`
Partially copied from the block tag, can smelt 1.5 items.
#### `wooden_hanging_signs`
Partially copied from the block tag, can smelt 4 items.
#### `sticks`
For sticks. Can smelt half an item.
#### `cauldrons`
For cauldrons.
#### `pressure_plates`
Item version of the vanilla `minecraft:pressure_plates` block tag.
#### `damage_proof/fire`
Items in this tag cannot be destroyed with fire or lava.
#### `damage_proof/explosion`
Items in this tag cannot be destroyed by explosions.
#### `damage_proof/cactus`
Items in this tag cannot be destroyed by a cactus.
#### `damage_proof/void`
Items in this tag cannot be destroyed by the void, and will be teleported to the build limit when they reached the void.
#### `damage_proof/despawn`
Items in this tag cannot despawn.
#### `wood_tool_materials`
Items in this tag can be used to repair wooden tools.
#### `iron_tool_materials`
Items in this tag can be used to repair iron tools.
#### `diamond_tool_materials`
Items in this tag can be used to repair diamond tools.
#### `gold_tool_materials`
Items in this tag can be used to repair golden tools.
#### `netherite_tool_materials`
Items in this tag can be used to repair netherite tools.
#### `leather_armor_materials`
Items in this tag can be used to repair leather armor.
#### `chain_armor_materials`
Items in this tag can be used to repair chain armor.
#### `iron_armor_materials`
Items in this tag can be used to repair iron armor.
#### `diamond_armor_materials`
Items in this tag can be used to repair diamond armor.
#### `gold_armor_materials`
Items in this tag can be used to repair golden armor.
#### `netherite_armor_materials`
Items in this tag can be used to repair netherite armor.
#### `turtle_armor_materials`
Items in this tag can be used to repair armor made from turtle scutes.
#### `armadillo_armor_materials`
Items in this tag can be used to repair armor made from armadillo scutes.
#### `elytra_repair_materials`
Items in this tag can be used to repair elytra.
#### `respawn_anchor_charge_items`
Items in this tag can be used to charge a respawn anchor.
#### `enchantment_payment_items`
Items in this tag can be used like lapis lazuli to fuel enchanting.
#### `powder_snow_walkable_equipment`
For items that can be worn to walk on powder snow, like leather boots.
#### `wax_items`
For items that can be used to wax copper blocks.
#### `wooden_tools`
For tools made of wood.
#### `stone_tools`
For tools made of stone.
#### `iron_tools`
For tools made of iron.
#### `diamond_tools`
For tools made of diamond.
#### `gold_tools`
For tools made of gold.
#### `netherite_tools`
For tools made of netherite.
#### `leather_armors`
For armor made of leather.
#### `chain_armors`
For armor made of chain.
#### `iron_armors`
For armor made of iron.
#### `diamond_armors`
For armor made of diamond.
#### `gold_armors`
For armor made of gold.
#### `netherite_armors`
For armor made of netherite.
#### `turtle_armors`
For armor made of turtle scutes.
#### `horse_armors`
For armor worn by horses.
#### `wolf_armors`
For armor worn by wolves.
#### `banner_patterns`
For items that can be used in a loom to apply special banner patterns.
### Fluids
#### `supports_sugar_cane_adjacently`
Backport of the tag by the same name from 26.1.
#### `supports_lily_pad`
Backport of the tag by the same name from 26.1.
#### `supports_frogspawn`
Backport of the tag by the same name from 26.1.
### Entity Types
#### `can_spawn_on_leaves`
For mobs that can naturally spawn on leaves.
#### `effect_immune/{effect}`
For mobs that are immune from a specific vanilla status effect. The way Aquifer implements actually fixes a bug where if a mob was immune to infested, it could not be immune to oozing or if a mob as immune to either infested or oozing, it could not be immune to poison and regeneration.
## Components
Aquifer also adds a few additional component types for items. All component types use the `aquifer namespace`.
### `recipe_remainder`
For items left behind in a crafting table after crafting an item, like how crafting a cake leaves empty buckets in place of milk buckets.
### `eat_sound`
The sound an item makes while being eaten.
### `drink_sound`
The sound an item makes while being drunk.
### `break_sound`
The sound an item makes when broken.
### `enchantability`
High enchantability on an item correlates to more, higher level, and more rare enchantments from an enchanting table. This is a vanilla value that is not normally customizable. This value has no effect on anvils.
## Item modifiers
All item modifier types use the `aquifer` namespace.
### `selector`
Uses a number to select from a list of item modifiers.
### `sequence`
Contains a list of item modifiers that can all share the same conditions.
### `set_axolotl_variant`
Sets the variant of an axolotl in a bucket.
### `set_custom_potion_effects`
Sets the custom potion effects on a potion item. Each effect can have a randomized duration and amplifier.
### `set_dyed_color`
Sets the hex color of an item ie leather armor.
### `set_potion_color`
Sets the color of a potion item.
### `set_shield_color`
Sets the background color of a shield.
### `set_tropical_fish_variant`
Sets the variants of tropical fish in a bucket.
### `set_unbreakable`
Makes an item unbreakable.
## Loot Table Number Providers
All number provider types use the `aquifer` namespace.
### `block_state_property`
Get a number based on a property in a block state. Can be used to drop the correct number of candles.
For boolean block state properties, this provider returns 0 for false, 1 for true.
For integer block state properties, this provider returns the value of the property itself.
For other types of block state properties, this provider will return the ordinal of value of the enum type the property uses.
An optional fallback value (with a default value of 0) can be given in case something goes wrong or the property does not exist in the given block state.
### `entity_property`
Gets a number based on an entity property.
An option fallback value (with a default value of 0) can be given in case an entity is not found or the entity does not have the specified property.