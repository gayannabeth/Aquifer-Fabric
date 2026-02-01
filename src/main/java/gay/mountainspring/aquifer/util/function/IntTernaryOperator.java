package gay.mountainspring.aquifer.util.function;

@FunctionalInterface
public interface IntTernaryOperator {
	int applyAsInt(int b1, int b2, int b3);
}