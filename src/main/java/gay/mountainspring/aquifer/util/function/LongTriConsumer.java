package gay.mountainspring.aquifer.util.function;

import java.util.Objects;

@FunctionalInterface
public interface LongTriConsumer {
	void accept(long b1, long b2, long b3);
	
	default LongTriConsumer andThen(LongTriConsumer after) {
		Objects.requireNonNull(after);
		return (b1, b2, b3) -> {
			this.accept(b1, b2, b3);
			after.accept(b1, b2, b3);
		};
	}
}