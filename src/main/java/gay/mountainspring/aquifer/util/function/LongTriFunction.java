package gay.mountainspring.aquifer.util.function;

@FunctionalInterface
public interface LongTriFunction<R> {
	R apply(long b1, long b2, long b3);
}