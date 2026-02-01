package gay.mountainspring.aquifer.util.function;

import java.util.Objects;

@FunctionalInterface
public interface FloatConsumer {
	void accept(float f);
	
	default FloatConsumer andThen(FloatConsumer after) {
		Objects.requireNonNull(after);
		return f -> {
			this.accept(f);
			after.accept(f);
		};
	}
}
