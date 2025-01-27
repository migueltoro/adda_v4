package us.lsi.iterables;

import java.util.Iterator;

import us.lsi.common.Pair;


public class IteratorCartesianProduct<A,B> implements Iterator<Pair<A,B>>,Iterable<Pair<A,B>>{
	
	public static <A> Iterable<Pair<A,A>> of(Iterable<A> iterableA) {
		Iterable<Pair<A,A>> r = IteratorEmpty.of();
		if(iterableA.iterator().hasNext()) {
			r = new IteratorCartesianProduct<A,A>(iterableA, iterableA);
		}
		return r;
	}
	
	
	public static <A,B> Iterable<Pair<A,B>> of(Iterable<A> iterableA, Iterable<B> iterableB) {
		Iterable<Pair<A,B>> r = IteratorEmpty.of();
		if(iterableA.iterator().hasNext() && iterableB.iterator().hasNext()) {
			r = new IteratorCartesianProduct<A,B>(iterableA, iterableB);
		}
		return r;
	}
	

	private Iterator<A> iteratorA;
	private A actualA;
	private Iterable<B> iterableB;
	private Iterator<B> iteratorB;
	
	private IteratorCartesianProduct(Iterable<A> iterableA, Iterable<B> iterableB) {
		super();
		this.iteratorA = iterableA.iterator();
		this.actualA = this.iteratorA.next();
		this.iterableB = iterableB;
		this.iteratorB = this.iterableB.iterator();
	}

	@Override
	public Iterator<Pair<A,B>> iterator() {
		return this;
	}
	
	@Override
	public boolean hasNext() {
		return this.iteratorA.hasNext() || this.iteratorB.hasNext();
	}
	
	@Override
	public Pair<A,B> next() {
		if(!this.iteratorB.hasNext()) {
			this.iteratorB = this.iterableB.iterator();
			this.actualA = this.iteratorA.next();
		}
		return Pair.of(this.actualA,this.iteratorB.next());
	}
}
