package gay.mountainspring.aquifer.util.function;

import java.util.Objects;

@FunctionalInterface
public interface LongTriPredicate {
	boolean test(long b1, long b2, long b3);
	
	default LongTriPredicate and(LongTriPredicate other) {
		Objects.requireNonNull(other);
		return (b1, b2, b3) -> this.test(b1, b2, b3) && other.test(b1, b2, b3);
	}
	
	default LongTriPredicate negate() {
		return (b1, b2, b3) -> !this.test(b1, b2, b3);
	}
	
	default LongTriPredicate or(LongTriPredicate other) {
		Objects.requireNonNull(other);
		return (b1, b2, b3) -> this.test(b1, b2, b3) || other.test(b1, b2, b3);
	}
}