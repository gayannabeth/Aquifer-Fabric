package gay.mountainspring.aquifer.util.function;

@FunctionalInterface
public interface ByteBinaryOperator {
	byte applyAsByte(byte left, byte right);
}