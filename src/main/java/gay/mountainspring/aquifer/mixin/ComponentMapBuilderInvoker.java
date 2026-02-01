package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.component.ComponentMap;

@Mixin(ComponentMap.Builder.class)
public interface ComponentMapBuilderInvoker {
	@Invoker("<init>")
	public static ComponentMap.Builder newComponentMapBuilder() {
		throw new AssertionError();
	}
}