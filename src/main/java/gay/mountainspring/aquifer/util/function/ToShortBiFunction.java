package gay.mountainspring.aquifer.util.function;

@FunctionalInterface
public interface ToShortBiFunction<T, U> {
	short applyAsShort(T t, U u);
}