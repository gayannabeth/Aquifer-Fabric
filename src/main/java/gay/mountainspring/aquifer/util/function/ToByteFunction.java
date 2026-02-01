package gay.mountainspring.aquifer.util.function;

@FunctionalInterface
public interface ToByteFunction<T> {
	byte applyAsByte(T t);
}