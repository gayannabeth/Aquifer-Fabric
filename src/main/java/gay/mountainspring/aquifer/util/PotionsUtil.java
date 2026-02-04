package gay.mountainspring.aquifer.util;

import java.util.Optional;
import java.util.OptionalInt;

import net.minecraft.component.type.PotionContentsComponent;

public class PotionsUtil {
	private PotionsUtil() {}
	
	/**
	 * Creates a new {@link PotionContentsComponent} with the potion and effects from an existing component and a new color
	 * @param component the component to use the potion and effect from
	 * @param color the new color
	 * @return the {@link PotionContentsComponent} with the new color
	 */
	public static PotionContentsComponent withColor(PotionContentsComponent component, Optional<Integer> color) {
		return new PotionContentsComponent(component.potion(), color, component.customEffects());
	}
	
	/**
	 * Creates a new {@link PotionContentsComponent} with the potion and effects from an existing component and a new color
	 * @param component the component to use the potion and effect from
	 * @param color the new color
	 * @return the {@link PotionContentsComponent} with the new color
	 */
	public static PotionContentsComponent withColor(PotionContentsComponent component, int color) {
		return withColor(component, Optional.of(color));
	}
	
	/**
	 * Creates a new {@link PotionContentsComponent} with the potion and effects from an existing component and a new color
	 * @param component the component to use the potion and effect from
	 * @param color the new color
	 * @return the {@link PotionContentsComponent} with the new color
	 */
	public static PotionContentsComponent withColor(PotionContentsComponent component, OptionalInt color) {
		return withColor(component, color.isPresent() ? Optional.of(color.getAsInt()) : Optional.empty());
	}
}