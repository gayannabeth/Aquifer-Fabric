package gay.mountainspring.aquifer.util.function;

@FunctionalInterface
public interface ObjByteConsumer<T> {
	void accept(T t, byte b);
}