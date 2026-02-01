package gay.mountainspring.aquifer.util.function;

@FunctionalInterface
public interface DoubleToByteFunction {
	byte applyAsByte(double d);
}