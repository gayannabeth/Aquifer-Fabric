package gay.mountainspring.aquifer.util.function;

@FunctionalInterface
public interface ByteFunction<R> {
	R apply(byte b);
}