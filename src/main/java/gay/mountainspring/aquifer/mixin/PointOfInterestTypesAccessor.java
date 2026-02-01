package gay.mountainspring.aquifer.mixin;

import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.block.BlockState;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.poi.PointOfInterestType;
import net.minecraft.world.poi.PointOfInterestTypes;

@Mixin(PointOfInterestTypes.class)
public interface PointOfInterestTypesAccessor {
	@Accessor("POI_STATES_TO_TYPE")
	public static Map<BlockState, RegistryEntry<PointOfInterestType>> getStateToType() {
		throw new AssertionError();
	}
}