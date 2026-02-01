package gay.mountainspring.aquifer.util.function;

@FunctionalInterface
public interface ShortTriFunction<R> {
	R apply(short b1, short b2, short b3);
}