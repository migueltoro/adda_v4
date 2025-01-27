package us.lsi.iterables;

import java.util.Iterator;

public class IteratorEmpty<E> implements Iterator<E>,Iterable<E> {
	
	public static <E> IteratorEmpty<E> of() {
		return new IteratorEmpty<E>();
	}

	private IteratorEmpty() {}

	@Override
	public Iterator<E> iterator() {
		return this;
	}

	@Override
	public boolean hasNext() {
		return false;
	}

	@Override
	public E next() {
		return null;
	}

}
