package gay.mountainspring.aquifer.util;

import java.util.function.IntSupplier;

import net.minecraft.util.math.ColorHelper;

/**
 * Interface for objects which supply an argb color
 */
public interface ColorSupplier extends IntSupplier {
	/**
	 * 
	 * @return an argb color
	 */
	int getColor();
	
	/**
	 * 
	 * @param alpha the alpha value to use
	 * @return the supplied color with a given alpha value
	 */
	default int getWithAlpha(int alpha) {
		return ColorHelper.Argb.withAlpha(alpha, this.getColor());
	}
	
	/**
	 * 
	 * @return the color with an alpha of 0xFF
	 */
	default int getColorFullAlpha() {
		return ColorHelper.Argb.fullAlpha(this.getColor());
	}
	
	/**
	 * 
	 * @return only the red channel of the supplied color
	 */
	default int getRed() {
		return ColorHelper.Argb.getRed(this.getColor());
	}
	
	/**
	 * 
	 * @return only the green channel of the supplied color
	 */
	default int getGreen() {
		return ColorHelper.Argb.getGreen(this.getColor());
	}
	
	/**
	 * 
	 * @return only the blue channel of the supplied color
	 */
	default int getBlue() {
		return ColorHelper.Argb.getBlue(this.getColor());
	}
	
	/**
	 * 
	 * @return only the alpha channel of the supplied color
	 */
	default int getAlpha() {
		return ColorHelper.Argb.getAlpha(this.getColor());
	}
	
	@Deprecated
	@Override
	default int getAsInt() {
		return this.getColor();
	}
}