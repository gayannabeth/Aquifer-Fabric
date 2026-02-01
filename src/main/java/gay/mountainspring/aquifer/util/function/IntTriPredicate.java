package gay.mountainspring.aquifer.util.function;

import java.util.Objects;

@FunctionalInterface
public interface IntTriPredicate {
	boolean test(int b1, int b2, int b3);
	
	default IntTriPredicate and(IntTriPredicate other) {
		Objects.requireNonNull(other);
		return (b1, b2, b3) -> this.test(b1, b2, b3) && other.test(b1, b2, b3);
	}
	
	default IntTriPredicate negate() {
		return (b1, b2, b3) -> !this.test(b1, b2, b3);
	}
	
	default IntTriPredicate or(IntTriPredicate other) {
		Objects.requireNonNull(other);
		return (b1, b2, b3) -> this.test(b1, b2, b3) || other.test(b1, b2, b3);
	}
}