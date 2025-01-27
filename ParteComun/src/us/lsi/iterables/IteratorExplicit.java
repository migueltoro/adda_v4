package us.lsi.iterables;

import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class IteratorExplicit<T,E> implements Iterator<E>,Iterable<E> {

	private T t;
	private Predicate<T> hn;
	private UnaryOperator<T> nx;
	private Function<T,E> nx1;
	
	
	
	public IteratorExplicit(T t0, Predicate<T> hn, UnaryOperator<T> nx, Function<T, E> nx1) {
		super();
		this.t = t0;
		this.hn = hn;
		this.nx = nx;
		this.nx1 = nx1;
	}


	@Override
	public Iterator<E> iterator() {
		return this;
	}


	@Override
	public boolean hasNext() {
		return hn.test(t);
	}


	@Override
	public E next() {
		E e = nx1.apply(t);
		this.t = nx.apply(t);
		return e;
	}
	
	
}
