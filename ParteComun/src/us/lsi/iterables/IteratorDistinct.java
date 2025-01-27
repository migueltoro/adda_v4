package us.lsi.iterables;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class IteratorDistinct<E> implements Iterator<E>,Iterable<E> {
	
	private Iterator<E> it;
	private Set<E> set;
	private E next;
	
	public static <E> IteratorDistinct<E> of(Iterable<E> iterable) {
		return new IteratorDistinct<E>(iterable);
	}
	
	private E netValue() {
		E e = it.next();
		while(it.hasNext() && set.contains(e)) e = it.next();
		return e;
	}

	private IteratorDistinct(Iterable<E> iterable) {
		this.it = iterable.iterator();
		this.set = new HashSet<>();
		this.next = it.hasNext()? netValue():null;
	}
	

	@Override
	public Iterator<E> iterator() {
		return this;
	}

	@Override
	public boolean hasNext() {
		return this.next != null;
	}

	@Override
	public E next() {
		E e = next;
		this.next = netValue();
		return e;
	}


}
