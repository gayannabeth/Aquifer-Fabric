package gay.mountainspring.aquifer.util.function;

import java.util.Objects;

@FunctionalInterface
public interface ByteBiConsumer {
	void accept(byte b1, byte b2);
	
	default ByteBiConsumer andThen(ByteBiConsumer after) {
		Objects.requireNonNull(after);
		return (b1, b2) -> {
			this.accept(b1, b2);
			after.accept(b1, b2);
		};
	}
}