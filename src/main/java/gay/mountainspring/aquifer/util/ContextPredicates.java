package gay.mountainspring.aquifer.util;

import net.minecraft.block.AbstractBlock;

public class ContextPredicates {
	public ContextPredicates() {}
	
	public static AbstractBlock.ContextPredicate and(AbstractBlock.ContextPredicate first, AbstractBlock.ContextPredicate second) {
		return (state, world, pos) -> first.test(state, world, pos) && second.test(state, world, pos);
	}
	
	public static <T> AbstractBlock.TypedContextPredicate<T> and(AbstractBlock.TypedContextPredicate<T> first, AbstractBlock.TypedContextPredicate<T> second) {
		return (state, world, pos, type) -> first.test(state, world, pos, type) && second.test(state, world, pos, type);
	}
	
	public static AbstractBlock.ContextPredicate negate(AbstractBlock.ContextPredicate predicate) {
		return (state, world, pos) -> !predicate.test(state, world, pos);
	}
	
	public static <T> AbstractBlock.TypedContextPredicate<T> negate(AbstractBlock.TypedContextPredicate<T> predicate) {
		return  (state, world, pos, type) -> !predicate.test(state, world, pos, type);
	}
	
	public static AbstractBlock.ContextPredicate or(AbstractBlock.ContextPredicate first, AbstractBlock.ContextPredicate second) {
		return (state, world, pos) -> first.test(state, world, pos) || second.test(state, world, pos);
	}
	
	public static <T> AbstractBlock.TypedContextPredicate<T> or(AbstractBlock.TypedContextPredicate<T> first, AbstractBlock.TypedContextPredicate<T> second) {
		return (state, world, pos, type) -> first.test(state, world, pos, type) || second.test(state, world, pos, type);
	}
}