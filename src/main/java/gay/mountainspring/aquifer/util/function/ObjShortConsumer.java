package gay.mountainspring.aquifer.util.function;

@FunctionalInterface
public interface ObjShortConsumer<T> {
	void accept(T t, short s);
}