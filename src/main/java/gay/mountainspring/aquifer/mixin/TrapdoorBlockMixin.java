package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import gay.mountainspring.aquifer.block.BlockSetTyped;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.TrapdoorBlock;

@Mixin(TrapdoorBlock.class)
public class TrapdoorBlockMixin implements BlockSetTyped {
	@Override
	public BlockSetType aquifer$getBlockSetType() {
		return blockSetType;
	}
	
	@Shadow
	BlockSetType blockSetType;
}