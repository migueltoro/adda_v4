package us.lsi.iterables;

import java.util.Iterator;
import java.util.List;

public class ListIterator<E> implements Iterator<E>, Iterable<E> {
	private Integer i;
	private List<E> ls;
	public ListIterator(List<E> ls) {
		this.i = 0;
		this.ls = ls;	
	}	
	@Override
	public Iterator<E> iterator() {
		return new ListIterator<>(ls);
	}
	@Override
	public boolean hasNext() { 
		return i < ls.size(); 
	}
	@Override
	public E next() {
		E e = ls.get(i);
		i = i+1;
		return e;
	}
}

