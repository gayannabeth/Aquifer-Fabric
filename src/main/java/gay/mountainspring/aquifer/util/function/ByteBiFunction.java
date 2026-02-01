package gay.mountainspring.aquifer.util.function;

@FunctionalInterface
public interface ByteBiFunction<R> {
	R apply(byte b1, byte b2);
}