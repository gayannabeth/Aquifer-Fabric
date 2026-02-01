package gay.mountainspring.aquifer.util.function;

@FunctionalInterface
public interface LongBiFunction<R> {
	R apply(long b1, long b2);
}