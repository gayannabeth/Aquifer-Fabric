package gay.mountainspring.aquifer.util.function;

import java.util.Objects;

@FunctionalInterface
public interface DoubleTriConsumer {
	void accept(double b1, double b2, double b3);
	
	default DoubleTriConsumer andThen(DoubleTriConsumer after) {
		Objects.requireNonNull(after);
		return (b1, b2, b3) -> {
			this.accept(b1, b2, b3);
			after.accept(b1, b2, b3);
		};
	}
}