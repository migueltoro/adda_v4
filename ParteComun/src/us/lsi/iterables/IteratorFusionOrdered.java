package us.lsi.iterables;

import java.util.Comparator;
import java.util.Iterator;

public class IteratorFusionOrdered<E> implements Iterator<E>,Iterable<E> {
	
	public static <E> Iterable<E> of(Iterable<E> itA, Iterable<E> itB, Comparator<E> cmp) {
		return new IteratorFusionOrdered<>(itA.iterator(),itB.iterator(),cmp);
	}
	
	private IteratorWithPeek<E> it1;
	private IteratorWithPeek<E> it2;
	private Comparator<E> cmp;
	
	private IteratorFusionOrdered(Iterator<E> itA, Iterator<E> itB, Comparator<E> cmp) {
		super();
		this.it1 = IteratorWithPeek.of(itA);
		this.it2 = IteratorWithPeek.of(itB);
		this.cmp = cmp;
	}
	
	@Override
	public Iterator<E> iterator() {
		return this;
	}
	@Override
	public boolean hasNext() {
		return it1.hasNext() || it2.hasNext();
	}
	
	@Override
	public E next() {
		E e;
		if (it1.hasNext() && it2.hasNext()) {
			e = cmp.compare(it1.peek(), it2.peek()) <= 0 ? it1.next() : it2.next(); 
		} else if (it2.hasNext()) {
			e = it2.next();
		} else {
			e = it1.next();
		}  
		return e;
	}
}
