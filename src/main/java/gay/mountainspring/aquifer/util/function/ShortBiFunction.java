package gay.mountainspring.aquifer.util.function;

@FunctionalInterface
public interface ShortBiFunction<R> {
	R apply(short b1, short b2);
}