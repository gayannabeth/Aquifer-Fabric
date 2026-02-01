package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;

import gay.mountainspring.aquifer.block.cauldron.CauldronContentsType;
import gay.mountainspring.aquifer.block.cauldron.CauldronGroup;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.world.biome.Biome;

public class AquiferWaterCauldronBlock extends AquiferLeveledCauldronBlock {
	public static final MapCodec<AquiferWaterCauldronBlock> CODEC = createCodec(AquiferWaterCauldronBlock::new);
	
	@Override
	protected MapCodec<? extends AquiferWaterCauldronBlock> getCodec() {
		return CODEC;
	}
	
	public AquiferWaterCauldronBlock(CauldronGroup group, Settings settings) {
		super(Biome.Precipitation.RAIN, group, settings, CauldronBehavior.WATER_CAULDRON_BEHAVIOR);
		group.setWater(this);
	}
	
	@Override
	public CauldronContentsType aquifer$getContentsType() {
		return CauldronContentsType.WATER;
	}
}