package gay.mountainspring.aquifer.block;

import net.minecraft.block.EnderChestBlock;
import net.minecraft.block.TrappedChestBlock;

public interface AquiferChestBlock {
	public default boolean aquifer$isTrapped() {
		return this instanceof TrappedChestBlock;
	}
	
	public default boolean aquifer$canBeDouble() {
		return !(this instanceof EnderChestBlock);
	}
}