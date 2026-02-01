package gay.mountainspring.aquifer.util.function;

@FunctionalInterface
public interface ObjFloatConsumer<T> {
	void accept(T t, float f);
}