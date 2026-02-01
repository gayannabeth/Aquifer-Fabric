package gay.mountainspring.aquifer.util.function;

import java.util.Objects;

@FunctionalInterface
public interface ShortBiPredicate {
	boolean test(short b1, short b2);
	
	default ShortBiPredicate and(ShortBiPredicate other) {
		Objects.requireNonNull(other);
		return  (b1, b2) -> this.test(b1, b2) && other.test(b1, b2);
	}
	
	default ShortBiPredicate negate() {
		return (b1, b2) -> !this.test(b1, b2);
	}
	
	default ShortBiPredicate or(ShortBiPredicate other) {
		Objects.requireNonNull(other);
		return (b1, b2) -> this.test(b1, b2) || other.test(b1, b2);
	}
}