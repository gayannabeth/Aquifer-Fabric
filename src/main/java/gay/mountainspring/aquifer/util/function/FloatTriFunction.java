package gay.mountainspring.aquifer.util.function;

@FunctionalInterface
public interface FloatTriFunction<R> {
	R apply(float b1, float b2, float b3);
}