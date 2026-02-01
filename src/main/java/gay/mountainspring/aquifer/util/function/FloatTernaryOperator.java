package gay.mountainspring.aquifer.util.function;

@FunctionalInterface
public interface FloatTernaryOperator {
	float applyAsFloat(float b1, float b2, float b3);
}