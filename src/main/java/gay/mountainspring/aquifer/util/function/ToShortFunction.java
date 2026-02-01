package gay.mountainspring.aquifer.util.function;

@FunctionalInterface
public interface ToShortFunction<T> {
	short applyAsShort(T t);
}