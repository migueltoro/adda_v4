package us.lsi.graphs.alg;

import java.util.Iterator;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import us.lsi.streams.Stream2;

public class Greedy<E> implements  Iterator<E>, Iterable<E> {
	
	public static <E> Greedy<E> of(E start, UnaryOperator<E> next, Predicate<E> goal) {
		return new Greedy<E>(start, next, goal);
	}

	private E state;
	private UnaryOperator<E> next;
	private Predicate<E> goal;

	private Greedy(E start, UnaryOperator<E> next, Predicate<E> goal) {
		super();
		this.state = start;
		this.next = next;
		this.goal = goal;
	}

	public Stream<E> stream() {
		return Stream2.of(this);
	}
	
	public Greedy<E> copy() {
		return of(state, next, goal);
	}
	
	@Override
	public Iterator<E> iterator() {
		return this;
	}

	@Override
	public boolean hasNext() {
		return state != null && !this.goal.test(state);
	}

	@Override
	public E next() {
		E old = state;
		state = this.next.apply(state);
		return old;
	}
	
	public Optional<E> last(){
		return Stream2.findLast(this.stream());
	}
	
}
