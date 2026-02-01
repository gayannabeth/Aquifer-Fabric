package gay.mountainspring.aquifer.util.function;

import java.util.Objects;

@FunctionalInterface
public interface ByteConsumer {
	void accept(byte b);
	
	default ByteConsumer andThen(ByteConsumer after) {
		Objects.requireNonNull(after);
		return b -> {
			this.accept(b);
			after.accept(b);
		};
	}
}