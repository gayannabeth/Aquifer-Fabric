package gay.mountainspring.aquifer.block;

import gay.mountainspring.aquifer.block.cauldron.CauldronContentsType;
import gay.mountainspring.aquifer.block.cauldron.CauldronGroup;
import net.minecraft.block.CauldronBlock;
import net.minecraft.block.LavaCauldronBlock;
import net.minecraft.block.LeveledCauldronBlock;

public interface AquiferCauldronExtensions {
	default CauldronGroup aquifer$getGroup() {
		return !(this instanceof AbstractAquiferCauldronBlock) ? CauldronGroup.VANILLA_IRON : null;
	}
	
	default CauldronContentsType aquifer$getContentsType() {
		if (!(this instanceof AbstractAquiferCauldronBlock)) {
			if (this instanceof CauldronBlock) {
				return CauldronContentsType.EMPTY;
			} else if (this instanceof LavaCauldronBlock) {
				return CauldronContentsType.LAVA;
			} else if (this instanceof LeveledCauldronBlock leveledCauldronBlock) {
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