package gay.mountainspring.aquifer.block;

import net.minecraft.block.WoodType;

/**
 * Interface for blocks and other objects that have a {@link WoodType}
 */
public interface WoodTyped {
	public default WoodType aquifer$getWoodType() {
		return null;
	}
}