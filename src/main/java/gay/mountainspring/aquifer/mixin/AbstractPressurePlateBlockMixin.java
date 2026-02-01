package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import gay.mountainspring.aquifer.block.BlockSetTyped;
import net.minecraft.block.AbstractPressurePlateBlock;
import net.minecraft.block.BlockSetType;

@Mixin(AbstractPressurePlateBlock.class)
public abstract class AbstractPressurePlateBlockMixin implements BlockSetTyped {
	@Override
	public BlockSetType aquifer$getBlockSetType() {
		return blockSetType;
	}
	
	@Shadow
	BlockSetType blockSetType;
}