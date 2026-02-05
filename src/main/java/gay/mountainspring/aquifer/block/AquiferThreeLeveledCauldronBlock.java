package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;

import gay.mountainspring.aquifer.block.cauldron.CauldronGroup;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.block.cauldron.CauldronBehavior.CauldronBehaviorMap;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.world.biome.Biome;

public abstract class AquiferThreeLeveledCauldronBlock extends AquiferLeveledCauldronBlock {
	public static final IntProperty LEVEL = Properties.LEVEL_3;
	
	public AquiferThreeLeveledCauldronBlock(Biome.Precipitation precipitation, CauldronGroup group, Settings settings, CauldronBehaviorMap behaviorMap) {
		super(precipitation, group, settings, behaviorMap);
		this.setDefaultState(this.stateManager.getDefaultState().with(LEVEL, 1));
	}
	
	public AquiferThreeLeveledCauldronBlock(CauldronGroup group, Settings settings, CauldronBehavior.CauldronBehaviorMap behaviorMap) {
		this(null, group, settings, behaviorMap);
	}

	@Override
	protected abstract MapCodec<? extends AquiferThreeLeveledCauldronBlock> getCodec();
	
	@Override
	public IntProperty aquifer$getLevelProperty() {
		return LEVEL;
	}
	
	@Override
	public int aquifer$getMaxLevel() {
		return 3;
	}
}