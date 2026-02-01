package gay.mountainspring.aquifer.util.function;

import java.util.Objects;

@FunctionalInterface
public interface DoubleBiPredicate {
	boolean test(double b1, double b2);
	
	default DoubleBiPredicate and(DoubleBiPredicate other) {
		Objects.requireNonNull(other);
		return  (b1, b2) -> this.test(b1, b2) && other.test(b1, b2);
	}
	
	default DoubleBiPredicate negate() {
		return (b1, b2) -> !this.test(b1, b2);
	}
	
	default DoubleBiPredicate or(DoubleBiPredicate other) {
		Objects.requireNonNull(other);
		return (b1, b2) -> this.test(b1, b2) || other.test(b1, b2);
	}
}