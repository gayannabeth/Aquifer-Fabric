package gay.mountainspring.aquifer.util.function;

@FunctionalInterface
public interface ToByteTriFunction<T, U, V> {
	byte applyAsByte(T t, U u, V v);
}