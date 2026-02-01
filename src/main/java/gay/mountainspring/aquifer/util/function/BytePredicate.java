package gay.mountainspring.aquifer.util.function;

import java.util.Objects;

@FunctionalInterface
public interface BytePredicate {
	boolean test(byte b);
	
	default BytePredicate and(BytePredicate other) {
		Objects.requireNonNull(other);
		return b -> this.test(b) && other.test(b);
	}
	
	default BytePredicate negate() {
		return b -> !this.test(b);
	}
	
	default BytePredicate or(BytePredicate other) {
		Objects.requireNonNull(other);
		return b -> this.test(b) || other.test(b);
	}
}