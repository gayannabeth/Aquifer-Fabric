package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import gay.mountainspring.aquifer.block.BlockSetTyped;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.ButtonBlock;

@Mixin(ButtonBlock.class)
public abstract class ButtonBlockMixin implements BlockSetTyped {
	@Override
	public BlockSetType aquifer$getBlockSetType() {
		return blockSetType;
	}
	
	@Shadow
	BlockSetType blockSetType;
}