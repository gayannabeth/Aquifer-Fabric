package gay.mountainspring.aquifer.util.function;

import java.util.Objects;

@FunctionalInterface
public interface FloatUnaryOperator {
	float applyAsFloat(float f);
	
	default FloatUnaryOperator compose(FloatUnaryOperator before) {
		Objects.requireNonNull(before);
		return f -> applyAsFloat(before.applyAsFloat(f));
	}
	
	default FloatUnaryOperator andThen(FloatUnaryOperator after) {
		Objects.requireNonNull(after);
		return f -> after.applyAsFloat(applyAsFloat(f));
	}
	
	static FloatUnaryOperator identity() {
		return f -> f;
	}
}