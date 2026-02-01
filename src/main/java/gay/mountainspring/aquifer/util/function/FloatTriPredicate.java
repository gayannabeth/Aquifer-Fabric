package gay.mountainspring.aquifer.util.function;

import java.util.Objects;

@FunctionalInterface
public interface FloatTriPredicate {
	boolean test(float b1, float b2, float b3);
	
	default FloatTriPredicate and(FloatTriPredicate other) {
		Objects.requireNonNull(other);
		return (b1, b2, b3) -> this.test(b1, b2, b3) && other.test(b1, b2, b3);
	}
	
	default FloatTriPredicate negate() {
		return (b1, b2, b3) -> !this.test(b1, b2, b3);
	}
	
	default FloatTriPredicate or(FloatTriPredicate other) {
		Objects.requireNonNull(other);
		return (b1, b2, b3) -> this.test(b1, b2, b3) || other.test(b1, b2, b3);
	}
}