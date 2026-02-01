package gay.mountainspring.aquifer.block;

import net.minecraft.block.WoodType;

public interface WoodTyped {
	public default WoodType aquifer$getWoodType() {
		return null;
	}
}