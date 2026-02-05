package gay.mountainspring.aquifer.util;

import java.util.HashMap;
import java.util.function.Function;

import org.apache.commons.lang3.function.TriConsumer;

import net.minecraft.util.Pair;


public class ToPairHashMap<K, V1, V2> extends HashMap<K, Pair<V1, V2>> {
	private static final long serialVersionUID = 8710646094604411979L;
	
	public Pair<V1, V2> put(K key, V1 v1, V2 v2) {
		return this.put(key, new Pair<>(v1, v2));
	}
	
	public Pair<V1, V2> computeIfAbsent(K key, Function<K, V1> mappingFunction1, Function<K, V2> mappingFunction2) {
		return this.computeIfAbsent(key, k -> new Pair<>(mappingFunction1.apply(k), mappingFunction2.apply(k)));
	}
	
	public V1 getLeft(Object key) {
		return this.get(key).getLeft();
	}
	
	public V2 getRight(Object key) {
		return this.get(key).getRight();
	}
	
	public V1 getLeftOrDefault(Object key, V1 defaultValue) {
		return this.containsKey(key) ? this.getLeft(key) : defaultValue;
	}
	
	public V2 getRightOrDefault(Object key, V2 defaultValue) {
		return this.containsKey(key) ? this.getRight(key) : defaultValue;
	}
	
	public Pair<V1, V2> putIfAbsent(K key, V1 v1, V2 v2) {
		return this.putIfAbsent(key, new Pair<>(v1, v2));
	}
	
	public void forEach(TriConsumer<K, V1, V2> consumer) {
		this.forEach((key, pair) -> consumer.accept(key, pair.getLeft(), pair.getRight()));
	}
}