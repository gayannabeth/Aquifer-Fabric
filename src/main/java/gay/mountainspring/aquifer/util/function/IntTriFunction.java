package gay.mountainspring.aquifer.util.function;

@FunctionalInterface
public interface IntTriFunction<R> {
	R apply(int b1, int b2, int b3);
}