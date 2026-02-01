package gay.mountainspring.aquifer.util.function;

import java.util.Objects;

@FunctionalInterface
public interface DoubleBiConsumer {
	void accept(double b1, double b2);
	
	default DoubleBiConsumer andThen(DoubleBiConsumer after) {
		Objects.requireNonNull(after);
		return (b1, b2) -> {
			this.accept(b1, b2);
			after.accept(b1, b2);
		};
	}
}