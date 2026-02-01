package gay.mountainspring.aquifer.util.function;

@FunctionalInterface
public interface DoubleTriFunction<R> {
	R apply(double b1, double b2, double b3);
}