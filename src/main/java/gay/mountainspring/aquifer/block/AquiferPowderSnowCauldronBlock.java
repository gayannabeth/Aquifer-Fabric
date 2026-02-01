package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;

import gay.mountainspring.aquifer.block.cauldron.CauldronContentsType;
import gay.mountainspring.aquifer.block.cauldron.CauldronGroup;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.world.biome.Biome;

public class AquiferPowderSnowCauldronBlock extends AquiferLeveledCauldronBlock {
	public static final MapCodec<AquiferPowderSnowCauldronBlock> CODEC = createCodec(AquiferPowderSnowCauldronBlock::new);
	
	@Override
	protected MapCodec<? extends AquiferPowderSnowCauldronBlock> getCodec() {
		return CODEC;
	}
	
	public AquiferPowderSnowCauldronBlock(CauldronGroup group, Settings settings) {
		super(Biome.Precipitation.SNOW, group, settings, CauldronBehavior.POWDER_SNOW_CAULDRON_BEHAVIOR);
		group.setPowderSnow(this);
	}
	
	@Override
	public CauldronContentsType aquifer$getContentsType() {
		return CauldronContentsType.POWDER_SNOW;
	}
}