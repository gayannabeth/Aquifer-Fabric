package gay.mountainspring.aquifer.util.function;

import java.util.Objects;

@FunctionalInterface
public interface ByteBiPredicate {
	boolean test(byte b1, byte b2);
	
	default ByteBiPredicate and(ByteBiPredicate other) {
		Objects.requireNonNull(other);
		return  (b1, b2) -> this.test(b1, b2) && other.test(b1, b2);
	}
	
	default ByteBiPredicate negate() {
		return (b1, b2) -> !this.test(b1, b2);
	}
	
	default ByteBiPredicate or(ByteBiPredicate other) {
		Objects.requireNonNull(other);
		return (b1, b2) -> this.test(b1, b2) || other.test(b1, b2);
	}
}