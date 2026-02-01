package gay.mountainspring.aquifer.util.function;

import java.util.Objects;

@FunctionalInterface
public interface IntBiConsumer {
	void accept(int b1, int b2);
	
	default IntBiConsumer andThen(IntBiConsumer after) {
		Objects.requireNonNull(after);
		return (b1, b2) -> {
			this.accept(b1, b2);
			after.accept(b1, b2);
		};
	}
}