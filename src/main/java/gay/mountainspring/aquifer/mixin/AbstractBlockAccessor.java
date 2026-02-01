package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.block.AbstractBlock;
import net.minecraft.sound.BlockSoundGroup;

@Mixin(AbstractBlock.class)
public interface AbstractBlockAccessor {
	@Mutable
	@Accessor("soundGroup")
	public void setSoundGroup(BlockSoundGroup soundGroup);
}