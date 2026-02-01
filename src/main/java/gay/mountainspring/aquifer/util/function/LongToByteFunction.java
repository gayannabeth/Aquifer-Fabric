package gay.mountainspring.aquifer.util.function;

@FunctionalInterface
public interface LongToByteFunction {
	byte applyAsByte(long l);
}