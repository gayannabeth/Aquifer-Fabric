package gay.mountainspring.aquifer.util.function;

@FunctionalInterface
public interface DoubleTernaryOperator {
	double applyAsDouble(double b1, double b2, double b3);
}