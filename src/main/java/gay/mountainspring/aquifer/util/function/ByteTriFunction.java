package gay.mountainspring.aquifer.util.function;

@FunctionalInterface
public interface ByteTriFunction<R> {
	R apply(byte b1, byte b2, byte b3);
}
