package gay.mountainspring.aquifer.util.function;

@FunctionalInterface
public interface IntBiFunction<R> {
	R apply(int b1, int b2);
}