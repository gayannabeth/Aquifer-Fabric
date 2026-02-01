package gay.mountainspring.aquifer.util.function;

@FunctionalInterface
public interface ToFloatFunction<T> {
	float applyAsFloat(T t);
}