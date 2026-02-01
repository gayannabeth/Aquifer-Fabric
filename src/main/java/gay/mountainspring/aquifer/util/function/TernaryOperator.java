package gay.mountainspring.aquifer.util.function;

import org.apache.commons.lang3.function.TriFunction;

@FunctionalInterface
public interface TernaryOperator<T> extends TriFunction<T, T, T, T> {}