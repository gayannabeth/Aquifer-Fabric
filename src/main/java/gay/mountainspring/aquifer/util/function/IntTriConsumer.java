package gay.mountainspring.aquifer.util.function;

import java.util.Objects;

@FunctionalInterface
public interface IntTriConsumer {
	void accept(int b1, int b2, int b3);
	
	default IntTriConsumer andThen(IntTriConsumer after) {
		Objects.requireNonNull(after);
		return (b1, b2, b3) -> {
			this.accept(b1, b2, b3);
			after.accept(b1, b2, b3);
		};
	}
}