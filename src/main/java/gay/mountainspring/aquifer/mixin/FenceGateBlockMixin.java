package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import gay.mountainspring.aquifer.block.BlockSetTyped;
import gay.mountainspring.aquifer.block.WoodTyped;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.WoodType;

@Mixin(FenceGateBlock.class)
public class FenceGateBlockMixin implements WoodTyped, BlockSetTyped {
	@Override
	public BlockSetType aquifer$getBlockSetType() {
		return type.setType();
	}
	
	@Override
	public WoodType aquifer$getWoodType() {
		return type;
	}
	
	@Shadow
	WoodType type;
}