package gay.mountainspring.aquifer.util.function;

@FunctionalInterface
public interface ToFloatTriFunction<T, U, V> {
	float applyAsFloat(T t, U u, V v);
}