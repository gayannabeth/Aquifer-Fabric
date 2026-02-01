package gay.mountainspring.aquifer.util.function;

@FunctionalInterface
public interface LongTernaryOperator {
	long applyAsLong(long b1, long b2, long b3);
}