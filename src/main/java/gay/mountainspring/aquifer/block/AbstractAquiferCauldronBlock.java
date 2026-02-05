package gay.mountainspring.aquifer.block;

import java.util.function.BiFunction;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import gay.mountainspring.aquifer.block.cauldron.CauldronContentsType;
import gay.mountainspring.aquifer.block.cauldron.CauldronGroup;
import gay.mountainspring.aquifer.registry.AquiferRegistries;
import net.minecraft.block.AbstractCauldronBlock;
import net.minecraft.block.cauldron.CauldronBehavior.CauldronBehaviorMap;

public abstract class AbstractAquiferCauldronBlock extends AbstractCauldronBlock {
	protected final CauldronGroup group;
	
	public AbstractAquiferCauldronBlock(CauldronGroup group, Settings settings, CauldronBehaviorMap behaviorMap) {
		super(settings, behaviorMap);
		this.group = group;
		group.set(this.aquifer$getContentsType(), this);
	}
	
	public CauldronGroup getGroup() {
		return this.group;
	}
	
	@Override
	public CauldronGroup aquifer$getGroup() {
		return this.getGroup();
	}
	
	@Override
	public abstract CauldronContentsType aquifer$getContentsType();
	
	@Override
	protected abstract MapCodec<? extends AbstractAquiferCauldronBlock> getCodec();
	
	public static final <T extends AbstractAquiferCauldronBlock> MapCodec<T> createCodec(BiFunction<CauldronGroup, Settings, T> function) {
		return RecordCodecBuilder.mapCodec(
				instance -> instance.group(AquiferRegistries.CAULDRON_GROUP.getCodec().fieldOf("group").forGetter(AbstractAquiferCauldronBlock::getGroup), createSettingsCodec())
				.apply(instance, function));
	}
}