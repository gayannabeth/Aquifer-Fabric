package gay.mountainspring.aquifer.util.function;

import java.util.Objects;

@FunctionalInterface
public interface TriConsumer<T, U, V> {
	void accept(T t, U u, V v);
	
	default TriConsumer<T, U, V> andThen(TriConsumer<? super T, ? super U, ? super V> other) {
		Objects.requireNonNull(other);
		return (t, u, v) -> {
			accept(t, u, v);
			other.accept(t, u, v);
		};
	}
}