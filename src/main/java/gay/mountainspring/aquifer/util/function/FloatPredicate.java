package gay.mountainspring.aquifer.util.function;

import java.util.Objects;

@FunctionalInterface
public interface FloatPredicate {
	boolean test(float f);
	
	default FloatPredicate and(FloatPredicate other) {
		Objects.requireNonNull(other);
		return f -> this.test(f) && other.test(f);
	}
	
	default FloatPredicate negate() {
		return f -> !this.test(f);
	}
	
	default FloatPredicate or(FloatPredicate other) {
		Objects.requireNonNull(other);
		return f -> this.test(f) || other.test(f);
	}
}