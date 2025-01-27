package us.lsi.iterables;

import java.util.Iterator;

import us.lsi.common.Enumerate;


public class IteratorEnumerate<E> implements Iterator<Enumerate<E>>,Iterable<Enumerate<E>>{
	
	public static <E> Iterable<Enumerate<E>> of(Iterable<E> iterator) {
		return new IteratorEnumerate<E>(iterator.iterator());
	}

	private Iterator<E> iterator;
	private Integer index;
	
	private IteratorEnumerate(Iterator<E> iterator) {
		super();
		this.iterator = iterator;
		this.index = 0;
	}
	
	@Override
	public Iterator<Enumerate<E>> iterator() {
		return this;
	}

	@Override
	public boolean hasNext() {
		return this.iterator.hasNext();
	}

	@Override
	public Enumerate<E> next() {
		Integer oldIndex = this.index;
		this.index = this.index +1;
		return Enumerate.of(oldIndex,this.iterator.next());
	}
	
}
