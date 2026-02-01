package gay.mountainspring.aquifer.util;

import java.util.Optional;

import net.minecraft.component.type.PotionContentsComponent;

public class PotionsUtil {
	private PotionsUtil() {}
	
	public static PotionContentsComponent withColor(PotionContentsComponent component, Optional<Integer> color) {
		return new PotionContentsComponent(component.potion(), color, component.customEffects());
	}
}