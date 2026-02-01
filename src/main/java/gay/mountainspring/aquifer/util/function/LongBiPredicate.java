package gay.mountainspring.aquifer.util.function;

import java.util.Objects;

@FunctionalInterface
public interface LongBiPredicate {
	boolean test(long b1, long b2);
	
	default LongBiPredicate and(LongBiPredicate other) {
		Objects.requireNonNull(other);
		return  (b1, b2) -> this.test(b1, b2) && other.test(b1, b2);
	}
	
	default LongBiPredicate negate() {
		return (b1, b2) -> !this.test(b1, b2);
	}
	
	default LongBiPredicate or(LongBiPredicate other) {
		Objects.requireNonNull(other);
		return (b1, b2) -> this.test(b1, b2) || other.test(b1, b2);
	}
}