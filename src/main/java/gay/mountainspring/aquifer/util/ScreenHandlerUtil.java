package gay.mountainspring.aquifer.util;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.screen.ScreenHandlerContext;

public class ScreenHandlerUtil {
	private ScreenHandlerUtil() {}
	
	/**
	 * A replacement for the vanilla {@link net.minecraft.screen.ScreenHandler.canUse} method that checks a tag rather than a single block
	 * @param context the screen handler context
	 * @param player the player
	 * @param blocks the tag to check
	 * @return is the player can use the screen handler
	 */
	public static boolean canUse(ScreenHandlerContext context, PlayerEntity player, TagKey<Block> blocks) {
		return context.get(
			(world, pos) -> !world.getBlockState(pos).isIn(blocks) ? false : player.squaredDistanceTo(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5) <= 64.0, false
		);
	}
}