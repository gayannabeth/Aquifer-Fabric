package gay.mountainspring.aquifer.util;

import net.minecraft.block.AbstractBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.tag.TagKey;

public class ContextPredicates {
	public ContextPredicates() {}
	
	/**
	 * 
	 * @param first a context predicate
	 * @param second another context predicate
	 * @return a context predicate that returns true iff the supplied context predicates BOTH return true
	 */
	public static AbstractBlock.ContextPredicate and(AbstractBlock.ContextPredicate first, AbstractBlock.ContextPredicate second) {
		return (state, world, pos) -> first.test(state, world, pos) && second.test(state, world, pos);
	}
	
	/**
	 * 
	 * @param <T> the type the context predicates depend on
	 * @param first a context predicate
	 * @param second another context predicate
	 * @return a context predicate that returns true iff the supplied context predicates BOTH return true
	 */
	public static <T> AbstractBlock.TypedContextPredicate<T> and(AbstractBlock.TypedContextPredicate<T> first, AbstractBlock.TypedContextPredicate<T> second) {
		return (state, world, pos, type) -> first.test(state, world, pos, type) && second.test(state, world, pos, type);
	}
	
	/**
	 * 
	 * @param predicate a context predicate
	 * @return a context predicate that returns the negation of the supplied context predicate
	 */
	public static AbstractBlock.ContextPredicate negate(AbstractBlock.ContextPredicate predicate) {
		return (state, world, pos) -> !predicate.test(state, world, pos);
	}
	
	/**
	 * 
	 * @param <T> the type the context predicate depends on
	 * @param predicate a context predicate
	 * @return a context predicate that returns the negation of the supplied context predicate
	 */
	public static <T> AbstractBlock.TypedContextPredicate<T> negate(AbstractBlock.TypedContextPredicate<T> predicate) {
		return  (state, world, pos, type) -> !predicate.test(state, world, pos, type);
	}
	
	/**
	 * 
	 * @param first a context predicate
	 * @param second another context predicate
	 * @return a context predicate that returns true iff either supplied context predicate returns true
	 */
	public static AbstractBlock.ContextPredicate or(AbstractBlock.ContextPredicate first, AbstractBlock.ContextPredicate second) {
		return (state, world, pos) -> first.test(state, world, pos) || second.test(state, world, pos);
	}
	
	/**
	 * 
	 * @param <T> the type the context predicates depend on
	 * @param first a context predicate
	 * @param second another context predicate
	 * @return a context predicate that returns true iff either supplied context predicate returns true
	 */
	public static <T> AbstractBlock.TypedContextPredicate<T> or(AbstractBlock.TypedContextPredicate<T> first, AbstractBlock.TypedContextPredicate<T> second) {
		return (state, world, pos, type) -> first.test(state, world, pos, type) || second.test(state, world, pos, type);
	}
	
	/**
	 * @param tag the tag to check
	 * @return a context predicate typed for entity types that check if the entity type is in the supplied tag
	 */
	public static AbstractBlock.TypedContextPredicate<EntityType<?>> isEntityInTag(TagKey<EntityType<?>> tag) {
		return (state, world, pos, type) -> type.isIn(tag);
	}
}