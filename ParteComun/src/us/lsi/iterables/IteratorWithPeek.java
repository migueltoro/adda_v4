package us.lsi.iterables;

import java.util.Iterator;

public class IteratorWithPeek<E> implements Iterator<E>, Iterable<E> {
	
	public static <E> IteratorWithPeek<E> of(Iterator<E> iterator) {
		return new IteratorWithPeek<>(iterator);
	}
	
	private Iterator<E> iterator;
	private E next;

	private IteratorWithPeek(Iterator<E> iterator) {
		super();
		this.iterator = iterator;
		this.next = iterator.hasNext() ? iterator.next() : null;
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
		E old = this.next;
		this.next = this.iterator.hasNext() ? this.iterator.next() : null;
		return old;
	}
	
	public E peek() {
		return this.next;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
