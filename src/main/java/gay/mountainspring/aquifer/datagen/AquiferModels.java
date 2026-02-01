package gay.mountainspring.aquifer.datagen;

import java.util.Optional;

import gay.mountainspring.aquifer.Aquifer;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.TextureKey;
import net.minecraft.util.Identifier;

public class AquiferModels {
	private AquiferModels() {}
	
	public static final Model TERRAIN_SLAB = block("terrain_slab", TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.TOP);
	public static final Model TERRAIN_SLAB_TOP = block("terrain_slab_top", "_top", TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.TOP);
	public static final Model TERRAIN_SLAB_DOUBLE = block("terrain_slab_double", "_double", TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.TOP);
	
	public static final Model ORIENTABLE_SLAB = block("orientable_slab", "_oriented", TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.TOP);
	public static final Model ORIENTABLE_SLAB_TOP = block("orientable_slab_top", "_oriented_top", TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.TOP);
	public static final Model ORIENTABLE_SLAB_INSIDE = block("orientable_slab_inside", "_oriented", TextureKey.BOTTOM, TextureKey.INSIDE, TextureKey.SIDE, TextureKey.TOP);
	public static final Model ORIENTABLE_SLAB_TOP_INSIDE = block("orientable_slab_top_inside", "_oriented_top", TextureKey.BOTTOM, TextureKey.INSIDE, TextureKey.SIDE, TextureKey.TOP);
	
	public static final Model LEAVES_SLAB = block("leaves_slab", TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.TOP);
	public static final Model LEAVES_SLAB_TOP = block("leaves_slab_top", "_top", TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.TOP);
	
	public static final Model STAIRS_INSIDE = block("stairs_inside", TextureKey.BOTTOM, TextureKey.INSIDE, TextureKey.SIDE, TextureKey.TOP);
	public static final Model STAIRS_FLIPPED = block("stairs_flipped", "_flipped", TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.TOP);
	public static final Model INNER_STAIRS_FLIPPED = block("inner_stairs_flipped", "_inner_flipped", TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.TOP);
	public static final Model OUTER_STAIRS_FLIPPED = block("outer_stairs_flipped", "_outer_flipped", TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.TOP);
	
	public static final Model TERRAIN_STAIRS = block("terrain_stairs", TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.TOP);
	public static final Model TERRAIN_STAIRS_FLIPPED = block("terrain_stairs_flipped", "_flipped", TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.TOP);
	public static final Model TERRAIN_INNER_STAIRS = block("terrain_inner_stairs", "_inner", TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.TOP);
	public static final Model TERRAIN_INNER_STAIRS_FLIPPED = block("terrain_inner_stairs_flipped", "_inner_flipped", TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.TOP);
	public static final Model TERRAIN_OUTER_STAIRS = block("terrain_outer_stairs", "_outer", TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.TOP);
	public static final Model TERRAIN_OUTER_STAIRS_FLIPPED = block("terrain_outer_stairs_flipped", "_outer_flipped", TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.TOP);
	
	public static final Model ORIENTED_STAIRS_FRONT = block("oriented_stairs_front", "_oriented_front", TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.TOP);
	public static final Model ORIENTED_STAIRS_SIDE = block("oriented_stairs_side", "_oriented_side", TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.TOP);
	public static final Model ORIENTED_STAIRS_FRONT_INSIDE = block("oriented_stairs_front_inside", "_oriented_front", TextureKey.BOTTOM, TextureKey.INSIDE, TextureKey.SIDE, TextureKey.TOP);
	public static final Model ORIENTED_STAIRS_SIDE_INSIDE = block("oriented_stairs_side_inside", "_oriented_side", TextureKey.BOTTOM, TextureKey.INSIDE, TextureKey.SIDE, TextureKey.TOP);
	public static final Model INNER_STAIRS_MIRRORED = block("inner_stairs_mirrored", "_inner_mirrored", TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.TOP);
	public static final Model INNER_STAIRS_INSIDE = block("inner_stairs_inside", "_inner", TextureKey.BOTTOM, TextureKey.INSIDE, TextureKey.SIDE, TextureKey.TOP);
	public static final Model INNER_STAIRS_INSIDE_MIRRORED = block("inner_stairs_inside", "_inner_mirrored", TextureKey.BOTTOM, TextureKey.INSIDE, TextureKey.SIDE, TextureKey.TOP);
	public static final Model OUTER_STAIRS_INSIDE = block("outer_stairs_inside", "_outer", TextureKey.BOTTOM, TextureKey.INSIDE, TextureKey.SIDE, TextureKey.TOP);
	public static final Model ORIENTED_OUTER_STAIRS = block("oriented_outer_stairs", "_oriented", TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.TOP);
	public static final Model ORIENTED_OUTER_STAIRS_MIRRORED = block("oriented_outer_stairs_mirrored", "_oriented_mirrored", TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.TOP);
	public static final Model ORIENTED_OUTER_STAIRS_INSIDE = block("oriented_outer_stairs_inside", "_oriented", TextureKey.BOTTOM, TextureKey.INSIDE, TextureKey.SIDE, TextureKey.TOP);
	public static final Model ORIENTED_OUTER_STAIRS_INSIDE_MIRRORED = block("oriented_outer_stairs_inside_mirrored", "_oriented_mirrored", TextureKey.BOTTOM, TextureKey.INSIDE, TextureKey.SIDE, TextureKey.TOP);
	
	public static final Model LEAVES_STAIRS = block("leaves_stairs", TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.TOP);
	public static final Model LEAVES_INNER_STAIRS = block("leaves_inner_stairs", "_inner", TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.TOP);
	public static final Model LEAVES_OUTER_STAIRS = block("leaves_outer_stairs", "_outer", TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.TOP);
	
	public static final Model TRANS_STAIRS = block("trans_stairs", TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.TOP);
	public static final Model TRANS_INNER_STAIRS = block("trans_inner_stairs", "_inner", TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.TOP);
	public static final Model TRANS_OUTER_STAIRS = block("trans_outer_stairs", "_outer", TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.TOP);
	
	public static final Model TEMPLATE_WALL_POST_BOTTOM_TOP = block("template_wall_post_bottom_top", "_post", TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.TOP);
	public static final Model TEMPLATE_WALL_SIDE_BOTTOM_TOP = block("template_wall_side_bottom_top", "_side", TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.TOP);
	public static final Model TEMPLATE_WALL_SIDE_TALL_BOTTOM_TOP = block("template_wall_side_tall_bottom_top", "_side_tall", TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.TOP);
	public static final Model WALL_BOTTOM_TOP_INVENTORY = block("wall_bottom_top_inventory", "_inventory", TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.TOP);
	public static final Model TEMPLATE_WALL_POST_COLUMN = block("template_wall_post_column", "_post", TextureKey.END, TextureKey.SIDE);
	public static final Model TEMPLATE_WALL_SIDE_COLUMN = block("template_wall_side_column", "_side", TextureKey.END, TextureKey.SIDE);
	public static final Model TEMPLATE_WALL_SIDE_TALL_COLUMN = block("template_wall_side_tall_column", "_side_tall", TextureKey.END, TextureKey.SIDE);
	public static final Model WALL_COLUMN_INVENTORY = block("wall_column_inventory", "_inventory", TextureKey.END, TextureKey.SIDE);
	public static final Model WALL_CENTRAL_COLUMN_INVENTORY = block("wall_central_column_inventory", "_inventory", TextureKey.END, TextureKey.SIDE);
	
	public static final Model TEMPLATE_TERRAIN_WALL_POST = block("template_terrain_wall_post", "_post", TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.TOP);
	public static final Model TEMPLATE_TERRAIN_WALL_SIDE = block("template_terrain_wall_side", "_side", TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.TOP);
	public static final Model TEMPLATE_TERRAIN_WALL_SIDE_TALL = block("template_terrain_wall_side_tall", "_side_tall", TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.TOP);
	public static final Model TERRAIN_WALL_INVENTORY = block("terrain_wall_inventory", "_inventory", TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.TOP);
	
	public static final Model TEMPLATE_LEAVES_WALL_POST = block("template_leaves_wall_post", "_post", TextureKey.WALL);
	public static final Model TEMPLATE_LEAVES_WALL_SIDE = block("template_leaves_wall_side", "_side", TextureKey.WALL);
	public static final Model TEMPLATE_LEAVES_WALL_SIDE_TALL = block("template_leaves_wall_side_tall", "_side_tall", TextureKey.WALL);
	public static final Model LEAVES_WALL_INVENTORY = block("leaves_wall_inventory", "_inventory", TextureKey.WALL);
	
	public static final Model TEMPLATE_TRANS_WALL_POST = block("template_trans_wall_post", "_post", TextureKey.SIDE, TextureKey.TOP, TextureKey.BOTTOM);
	public static final Model TEMPLATE_TRANS_WALL_NOPOST = block("template_trans_wall_nopost", "_nopost", TextureKey.SIDE, TextureKey.TOP, TextureKey.BOTTOM);
	public static final Model TEMPLATE_TRANS_WALL_NOPOST_TALL = block("template_trans_wall_nopost_tall", "_nopost_tall", TextureKey.SIDE, TextureKey.TOP, TextureKey.BOTTOM);
	public static final Model TEMPLATE_TRANS_WALL_NOSIDE = block("template_trans_wall_noside", "_noside", TextureKey.SIDE);
	public static final Model TEMPLATE_TRANS_WALL_NOSIDE_NOPOST = block("template_trans_wall_noside_nopost", "_noside_nopost", TextureKey.SIDE);
	public static final Model TEMPLATE_TRANS_WALL_NOSIDE_NOPOST_TALL = block("template_trans_wall_noside_nopost_tall", "_noside_nopost_tall", TextureKey.SIDE);
	public static final Model TEMPLATE_TRANS_WALL_SIDE = block("template_trans_wall_side", "_side", TextureKey.SIDE, TextureKey.TOP, TextureKey.BOTTOM);
	public static final Model TEMPLATE_TRANS_WALL_SIDE_TALL = block("template_trans_wall_side_tall", "_side_tall", TextureKey.SIDE, TextureKey.TOP, TextureKey.BOTTOM);
	public static final Model TEMPLATE_TRANS_WALL_SIDE_NOPOST = block("template_trans_wall_side_nopost", "_side_nopost", TextureKey.SIDE, TextureKey.TOP, TextureKey.BOTTOM);
	public static final Model TEMPLATE_TRANS_WALL_SIDE_TALL_NOPOST = block("template_trans_wall_side_tall_nopost", "_side_tall_nopost", TextureKey.SIDE, TextureKey.TOP, TextureKey.BOTTOM);
	public static final Model TRANS_WALL_INVENTORY = block("trans_wall_inventory", "_inventory", TextureKey.SIDE, TextureKey.TOP, TextureKey.BOTTOM);
	
	public static final Model TEMPLATE_CARPET = block("template_carpet", TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.TOP);
	
	public static final Model LEAVES_CARPET = block("leaves_carpet", TextureKey.TEXTURE);
	
	public static final Model LADDER = block("ladder", TextureKey.TEXTURE);
	
	public static final Model TEMPLATE_ANVIL = block("template_anvil", AquiferTextureKeys.BODY, TextureKey.TOP);
	
	public static final Model TEMPLATE_BARS_CAP = block("template_bars_cap", "_cap", AquiferTextureKeys.BARS);
	public static final Model TEMPLATE_BARS_CAP_ALT = block("template_bars_cap_alt", "_cap_alt", AquiferTextureKeys.BARS);
	public static final Model TEMPLATE_BARS_SIDE = block("template_bars_side", "_side", AquiferTextureKeys.BARS);
	public static final Model TEMPLATE_BARS_SIDE_ALT = block("template_bars_side_alt", "_side_alt", AquiferTextureKeys.BARS);
	public static final Model TEMPLATE_BARS_POST = block("template_bars_post", "_post", AquiferTextureKeys.BARS);
	public static final Model TEMPLATE_BARS_POST_ENDS = block("template_bars_post_ends", "_post_ends", AquiferTextureKeys.BARS);
	
	public static final Model TEMPLATE_CAULDRON_EMPTY = block("template_cauldron_empty", TextureKey.BOTTOM, TextureKey.INSIDE, TextureKey.SIDE, TextureKey.TOP);
	public static final Model TEMPLATE_CAULDRON_FULL = block("template_cauldron_full", TextureKey.BOTTOM, TextureKey.CONTENT, TextureKey.INSIDE, TextureKey.SIDE, TextureKey.TOP);
	public static final Model TEMPLATE_CAULDRON_LEVEL1 = block("template_cauldron_level1", "_level1", TextureKey.BOTTOM, TextureKey.CONTENT, TextureKey.INSIDE, TextureKey.SIDE, TextureKey.TOP);
	public static final Model TEMPLATE_CAULDRON_LEVEL2 = block("template_cauldron_level2", "_level2", TextureKey.BOTTOM, TextureKey.CONTENT, TextureKey.INSIDE, TextureKey.SIDE, TextureKey.TOP);
	
	public static final Model TEMPLATE_CHAIN = block("template_chain", TextureKey.TEXTURE);
	
	public static final Model TEMPLATE_CHISELED_BOOKSHELF = block("template_chiseled_bookshelf", TextureKey.SIDE, TextureKey.TOP);
	
	public static final Model TEMPLATE_COMPOSTER = block("template_composter", TextureKey.BOTTOM, TextureKey.INSIDE, TextureKey.SIDE, TextureKey.TOP);
	
	public static final Model TEMPLATE_GRINDSTONE = block("template_grindstone", TextureKey.SIDE, TextureKey.TOP);
	
	public static final Model TEMPLATE_LECTERN = block("template_lectern", AquiferTextureKeys.BASE, TextureKey.BOTTOM, TextureKey.FRONT, TextureKey.SIDE, TextureKey.TOP);
	
	public static final Model TEMPLATE_STONECUTTER = block("template_stoncutter", TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.TOP);
	
	public static final Model TEMPLATE_TRIPWIRE_HOOK = block("template_tripwire_hook", AquiferTextureKeys.BASE);
	public static final Model TEMPLATE_TRIPWIRE_HOOK_ON = block("template_tripwire_hook_on", "_on", AquiferTextureKeys.BASE);
	public static final Model TEMPLATE_TRIPWIRE_HOOK_ATTACHED = block("template_tripwire_hook_attached", "_attached", AquiferTextureKeys.BASE);
	public static final Model TEMPLATE_TRIPWIRE_HOOK_ATTACHED_ON = block("template_tripwire_hook_attached_on", "_attached_on", AquiferTextureKeys.BASE);
	
	private static Model block(String parent, TextureKey... keys) {
		return new Model(Optional.of(Identifier.of(Aquifer.MOD_ID, "block/" + parent)), Optional.empty(), keys);
	}
	
	private static Model block(String parent, String suffix, TextureKey... keys) {
		return new Model(Optional.of(Identifier.of(Aquifer.MOD_ID, "block/" + parent)), Optional.of(suffix), keys);
	}
}