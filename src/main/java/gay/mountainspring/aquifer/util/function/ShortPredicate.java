package gay.mountainspring.aquifer.util.function;

import java.util.Objects;

@FunctionalInterface
public interface ShortPredicate {
	boolean test(short s);
	
	default ShortPredicate and(ShortPredicate other) {
		Objects.requireNonNull(other);
		return s -> this.test(s) && other.test(s);
	}
	
	default ShortPredicate negate() {
		return s -> !this.test(s);
	}
	
	default ShortPredicate or(ShortPredicate other) {
		Objects.requireNonNull(other);
		return s -> this.test(s) || other.test(s);
	}
}