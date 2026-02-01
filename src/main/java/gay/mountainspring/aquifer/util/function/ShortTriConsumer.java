package gay.mountainspring.aquifer.util.function;

import java.util.Objects;

@FunctionalInterface
public interface ShortTriConsumer {
	void accept(short b1, short b2, short b3);
	
	default ShortTriConsumer andThen(ShortTriConsumer after) {
		Objects.requireNonNull(after);
		return (b1, b2, b3) -> {
			this.accept(b1, b2, b3);
			after.accept(b1, b2, b3);
		};
	}
}