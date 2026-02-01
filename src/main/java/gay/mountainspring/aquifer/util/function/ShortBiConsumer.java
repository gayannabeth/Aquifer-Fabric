package gay.mountainspring.aquifer.util.function;

import java.util.Objects;

@FunctionalInterface
public interface ShortBiConsumer {
	void accept(short b1, short b2);
	
	default ShortBiConsumer andThen(ShortBiConsumer after) {
		Objects.requireNonNull(after);
		return (b1, b2) -> {
			this.accept(b1, b2);
			after.accept(b1, b2);
		};
	}
}