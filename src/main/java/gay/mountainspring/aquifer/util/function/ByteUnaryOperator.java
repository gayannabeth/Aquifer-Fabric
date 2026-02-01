package gay.mountainspring.aquifer.util.function;

import java.util.Objects;

@FunctionalInterface
public interface ByteUnaryOperator {
	byte applyAsByte(byte b);
	
	default ByteUnaryOperator compose(ByteUnaryOperator before) {
		Objects.requireNonNull(before);
		return b -> applyAsByte(before.applyAsByte(b));
	}
	
	default ByteUnaryOperator andThen(ByteUnaryOperator after) {
		Objects.requireNonNull(after);
		return b -> after.applyAsByte(applyAsByte(b));
	}
	
	static ByteUnaryOperator identity() {
		return b -> b;
	}
}
