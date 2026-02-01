package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;

import gay.mountainspring.aquifer.block.AquiferChestBlock;
import net.minecraft.block.AbstractChestBlock;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.EnderChestBlock;
import net.minecraft.block.TrappedChestBlock;

@Mixin(AbstractChestBlock.class)
public abstract class AbstractChestBlockMixin extends BlockWithEntity implements AquiferChestBlock {
	private AbstractChestBlockMixin(Settings settings) {super(settings);} //dummy constructor
	
	@Override
	public boolean aquifer$isTrapped() {
		return ((Object) this) instanceof TrappedChestBlock;
	}
	
	@Override
	public boolean aquifer$canBeDouble() {
		return !(((Object) this) instanceof EnderChestBlock);
	}
}