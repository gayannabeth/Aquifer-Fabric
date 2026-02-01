package gay.mountainspring.aquifer.util.function;

@FunctionalInterface
public interface DoubleBiFunction<R> {
	R apply(double b1, double b2);
}