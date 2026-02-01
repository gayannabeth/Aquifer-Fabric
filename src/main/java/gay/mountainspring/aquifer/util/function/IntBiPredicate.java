package gay.mountainspring.aquifer.util.function;

import java.util.Objects;

@FunctionalInterface
public interface IntBiPredicate {
	boolean test(int b1, int b2);
	
	default IntBiPredicate and(IntBiPredicate other) {
		Objects.requireNonNull(other);
		return  (b1, b2) -> this.test(b1, b2) && other.test(b1, b2);
	}
	
	default IntBiPredicate negate() {
		return (b1, b2) -> !this.test(b1, b2);
	}
	
	default IntBiPredicate or(IntBiPredicate other) {
		Objects.requireNonNull(other);
		return (b1, b2) -> this.test(b1, b2) || other.test(b1, b2);
	}
}