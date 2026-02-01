package gay.mountainspring.aquifer.util.function;

@FunctionalInterface
public interface ToLongTriFunction<T, U, V> {
	long applyAsLong(T t, U u, V v);
}