package gay.mountainspring.aquifer.util.function;

@FunctionalInterface
public interface ByteTernaryOperator {
	byte applyAsByte(byte b1, byte b2, byte b3);
}