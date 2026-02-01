package gay.mountainspring.aquifer.util.function;

@FunctionalInterface
public interface ToShortTriFunction<T, U, V> {
	short applyAsShort(T t, U u, V v);
}