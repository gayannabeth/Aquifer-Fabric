package gay.mountainspring.aquifer.util.function;

import java.util.Objects;

@FunctionalInterface
public interface ShortTriPredicate {
	boolean test(short b1, short b2, short b3);
	
	default ShortTriPredicate and(ShortTriPredicate other) {
		Objects.requireNonNull(other);
		return (b1, b2, b3) -> this.test(b1, b2, b3) && other.test(b1, b2, b3);
	}
	
	default ShortTriPredicate negate() {
		return (b1, b2, b3) -> !this.test(b1, b2, b3);
	}
	
	default ShortTriPredicate or(ShortTriPredicate other) {
		Objects.requireNonNull(other);
		return (b1, b2, b3) -> this.test(b1, b2, b3) || other.test(b1, b2, b3);
	}
}