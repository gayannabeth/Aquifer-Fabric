package gay.mountainspring.aquifer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;

import gay.mountainspring.aquifer.advancement.AdvancementEvents;
import gay.mountainspring.aquifer.advancement.AdvancementUtil;
import gay.mountainspring.aquifer.resource.ReloadableResourceSource;
import gay.mountainspring.aquifer.resource.ReloadableResourceUtil;
import net.minecraft.advancement.Advancement;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.ServerAdvancementLoader;
import net.minecraft.util.Identifier;

@Mixin(ServerAdvancementLoader.class)
public class ServerAdvancementLoaderMixin {
	@Shadow
	public RegistryWrapper.WrapperLookup registryLookup;
	
	@WrapOperation(method = "method_20723", at = @At(value = "INVOKE", target = "validate(Lnet/minecraft/util/Identifier;Lnet/minecraft/advancement/Advancement;)V"))
	private void modifyAdvancement(ServerAdvancementLoader loader, Identifier id, Advancement advancement, Operation<Void> original) {
		original.call(loader, id, modifyAdvancement(advancement, id, loader.registryLookup));
	}
	
	@Unique
	private static Advancement modifyAdvancement(Advancement advancement, Identifier id, RegistryWrapper.WrapperLookup registries) {
		ReloadableResourceSource source = ReloadableResourceUtil.SOURCES.get().get(RegistryKeys.ADVANCEMENT.getValue(), id);
		if (source == null) source = ReloadableResourceSource.DATA_PACK;
		
		Advancement.Builder advancementBuilder = AdvancementUtil.unbuild(advancement);
		
		AdvancementEvents.MODIFY.invoker().modifyAdvancement(id, advancementBuilder, source, registries);
		
		return advancementBuilder.build(id).value();
	}
}