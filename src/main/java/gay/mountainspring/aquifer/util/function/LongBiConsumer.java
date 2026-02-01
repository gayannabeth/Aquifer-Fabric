package gay.mountainspring.aquifer.util.function;

import java.util.Objects;

@FunctionalInterface
public interface LongBiConsumer {
	void accept(long b1, long b2);
	
	default LongBiConsumer andThen(LongBiConsumer after) {
		Objects.requireNonNull(after);
		return (b1, b2) -> {
			this.accept(b1, b2);
			after.accept(b1, b2);
		};
	}
}