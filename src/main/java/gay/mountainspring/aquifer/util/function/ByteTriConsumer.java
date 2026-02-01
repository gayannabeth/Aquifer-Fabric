package gay.mountainspring.aquifer.util.function;

import java.util.Objects;

@FunctionalInterface
public interface ByteTriConsumer {
	void accept(byte b1, byte b2, byte b3);
	
	default ByteTriConsumer andThen(ByteTriConsumer after) {
		Objects.requireNonNull(after);
		return (b1, b2, b3) -> {
			this.accept(b1, b2, b3);
			after.accept(b1, b2, b3);
		};
	}
}