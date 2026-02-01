package gay.mountainspring.aquifer.util.function;

@FunctionalInterface
public interface FloatBinaryOperator {
	float applyAsFloat(float left, float right);
}