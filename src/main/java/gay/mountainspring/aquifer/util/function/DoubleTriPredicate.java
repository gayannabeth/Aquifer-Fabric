package gay.mountainspring.aquifer.util.function;

import java.util.Objects;

@FunctionalInterface
public interface DoubleTriPredicate {
	boolean test(double b1, double b2, double b3);
	
	default DoubleTriPredicate and(DoubleTriPredicate other) {
		Objects.requireNonNull(other);
		return (b1, b2, b3) -> this.test(b1, b2, b3) && other.test(b1, b2, b3);
	}
	
	default DoubleTriPredicate negate() {
		return (b1, b2, b3) -> !this.test(b1, b2, b3);
	}
	
	default DoubleTriPredicate or(DoubleTriPredicate other) {
		Objects.requireNonNull(other);
		return (b1, b2, b3) -> this.test(b1, b2, b3) || other.test(b1, b2, b3);
	}
}