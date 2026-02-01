package gay.mountainspring.aquifer.util.function;

@FunctionalInterface
public interface FloatBiFunction<R> {
	R apply(float b1, float b2);
}