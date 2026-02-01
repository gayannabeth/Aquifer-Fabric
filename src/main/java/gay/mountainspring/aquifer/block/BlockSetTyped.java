package gay.mountainspring.aquifer.block;

import net.minecraft.block.BlockSetType;

public interface BlockSetTyped {
	public default BlockSetType aquifer$getBlockSetType() {
		return BlockSetType.STONE;
	}
}