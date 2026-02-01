package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import gay.mountainspring.aquifer.block.WoodTyped;
import net.minecraft.block.AbstractSignBlock;
import net.minecraft.block.WoodType;

@Mixin(AbstractSignBlock.class)
public class AbstractSignBlockMixin implements WoodTyped {
	@Override
	public WoodType aquifer$getWoodType() {
		return type;
	}
	
	@Shadow
	WoodType type;
}