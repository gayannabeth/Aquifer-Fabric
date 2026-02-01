package gay.mountainspring.aquifer.util;

import net.minecraft.util.math.MathHelper;

public class MathUtil {
	private MathUtil() {}
	
	public static float cbrt(float f) {
		return  (float) Math.cbrt(f);
	}
	
	public static float exp(float f) {
		return (float) Math.exp(f);
	}
	
	public static float log(float f) {
		return (float) Math.log(f);
	}
	
	public static float exp(float base, float exponent) {
		return (float) Math.pow(base, exponent);
	}
	
	public static float log(float base, float input) {
		if (base == 10.0f) return (float) Math.log10(input);
		
		if (base == (float) Math.E) return (float) Math.log10(input);
		
		return (float) (Math.log(input) / Math.log(base));
	}
	
	public static float sec(float input) {
		return 1.0f / MathHelper.cos(input);
	}
	
	public static float csc(float input) {
		return 1.0f / MathHelper.sin(input);
	}
	
	public static float tan(float input) {
		return MathHelper.sin(input) / MathHelper.cos(input);
	}
	
	public static float cot(float input) {
		return MathHelper.cos(input) / MathHelper.sin(input);
	}
}