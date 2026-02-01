package gay.mountainspring.aquifer.util.function;

import java.util.Objects;

@FunctionalInterface
public interface FloatBiPredicate {
	boolean test(float b1, float b2);
	
	default FloatBiPredicate and(FloatBiPredicate other) {
		Objects.requireNonNull(other);
		return  (b1, b2) -> this.test(b1, b2) && other.test(b1, b2);
	}
	
	default FloatBiPredicate negate() {
		return (b1, b2) -> !this.test(b1, b2);
	}
	
	default FloatBiPredicate or(FloatBiPredicate other) {
		Objects.requireNonNull(other);
		return (b1, b2) -> this.test(b1, b2) || other.test(b1, b2);
	}
}