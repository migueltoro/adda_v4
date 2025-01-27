package us.lsi.graphs.virtual;

import java.util.function.Function;
import java.util.function.Predicate;

public interface Action<V> {

	V neighbor(V v);

	boolean isApplicable(V v);
	
	String name();

	public static <V> Action<V> of(Integer id, String name, Predicate<V> isApplicable, Function<V, V> neighbor) {
		return new ActionI<V>(id, name, isApplicable, neighbor);
	}

	public record ActionI<V> (Integer id, String name, Predicate<V> isApplicable, Function<V, V> neighbor) implements Action<V> {

		@Override
		public V neighbor(V v) {
			return this.neighbor.apply(v);
		}

		@Override
		public boolean isApplicable(V v) {
			return this.isApplicable.test(v);
		}
		
	}

}