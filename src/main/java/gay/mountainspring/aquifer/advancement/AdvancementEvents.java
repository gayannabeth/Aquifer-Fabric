package gay.mountainspring.aquifer.advancement;

import gay.mountainspring.aquifer.resource.ReloadableResourceSource;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.advancement.Advancement;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

public class AdvancementEvents {
	private AdvancementEvents() {}
	
	public static final Event<Modify> MODIFY = EventFactory.createArrayBacked(Modify.class, listeners -> (id, advancementBuilder, source, registries) -> {
		for (Modify listener : listeners) {
			listener.modifyAdvancement(id, advancementBuilder, source, registries);
		}
	});
	
	@FunctionalInterface
	public interface Modify {
		void modifyAdvancement(Identifier id, Advancement.Builder advancementBuilder, ReloadableResourceSource advancementSource, RegistryWrapper.WrapperLookup registries);
	}
}