package gay.mountainspring.aquifer.util.function;

@FunctionalInterface
public interface ToByteBiFunction<T, U> {
	byte applyAsByte(T t, U u);
}