package gay.mountainspring.aquifer.util.function;

@FunctionalInterface
public interface ToDoubleTriFunction<T, U, V> {
	double applyAsDouble(T t, U u, V v);
}