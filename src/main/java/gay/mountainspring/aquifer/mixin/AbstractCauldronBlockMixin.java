package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;

import gay.mountainspring.aquifer.block.AbstractAquiferCauldronBlock;
import gay.mountainspring.aquifer.block.AquiferCauldronExtensions;
import gay.mountainspring.aquifer.block.cauldron.CauldronContentsType;
import gay.mountainspring.aquifer.block.cauldron.CauldronGroup;
import net.minecraft.block.AbstractCauldronBlock;
import net.minecraft.block.Block;
import net.minecraft.block.CauldronBlock;
import net.minecraft.block.LavaCauldronBlock;
import net.minecraft.block.LeveledCauldronBlock;

@Mixin(AbstractCauldronBlock.class)
public abstract class AbstractCauldronBlockMixin extends Block implements AquiferCauldronExtensions {
	private AbstractCauldronBlockMixin(Settings settings) {super(settings);}

	@Override
	public CauldronGroup aquifer$getGroup() {
		return CauldronGroup.VANILLA_IRON;
	}
	
	@Override
	public CauldronContentsType aquifer$getContentsType() {
		if (!(((Object) this) instanceof AbstractAquiferCauldronBlock)) {
			if (((Object) this) instanceof CauldronBlock) {
				return CauldronContentsType.EMPTY;
			} else if (((Object) this) instanceof LavaCauldronBlock) {
				return CauldronContentsType.LAVA;
			} else if (((Object) this) instanceof LeveledCauldronBlock leveledCauldronBlock) {
				switch (leveledCauldronBlock.aquifer$getPrecipitation()) {
					case RAIN: return CauldronContentsType.WATER;
					case SNOW: return CauldronContentsType.POWDER_SNOW;
					default: return null;
				}
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
}