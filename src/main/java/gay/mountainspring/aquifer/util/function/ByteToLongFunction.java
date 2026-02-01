package gay.mountainspring.aquifer.util.function;

@FunctionalInterface
public interface ByteToLongFunction {
	long applyAsLong(byte b);
}