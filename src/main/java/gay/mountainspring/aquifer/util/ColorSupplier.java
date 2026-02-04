package gay.mountainspring.aquifer.util;

import java.util.function.IntSupplier;

import net.minecraft.util.math.ColorHelper;

public interface ColorSupplier extends IntSupplier {
	int getColor();
	
	default int getColorFullAlpha() {
		return ColorHelper.Argb.fullAlpha(this.getColor());
	}
	
	@Deprecated
	@Override
	default int getAsInt() {
		return this.getColor();
	}
}