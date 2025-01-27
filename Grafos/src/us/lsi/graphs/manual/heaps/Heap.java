package us.lsi.graphs.manual.heaps;

import java.util.Comparator;
import java.util.List;

public interface Heap<V, P> {
	
	public static <V,P extends Comparable<P>> Heap<V,P> of() {
		switch(HeapInfo.heapType) {
		case BinaryHeap: return new BinaryHeap<V, P>(Comparator.naturalOrder());
		case Fibonacci: return new FibonacciHeap<V, P>(Comparator.naturalOrder());
		case OrderedList: return new OrderedList<V, P>(Comparator.naturalOrder());
		default: return new FibonacciHeap<V, P>(Comparator.naturalOrder());
		}
	}
	
	public static <V, P> Heap<V, P> of(Comparator<P> comparator) {
		switch(HeapInfo.heapType) {
		case BinaryHeap: return new BinaryHeap<V, P>(comparator);
		case Fibonacci: return new FibonacciHeap<V, P>(comparator);
		case OrderedList: return new OrderedList<V, P>(comparator);
		default: return new FibonacciHeap<V, P>(comparator);
		}
	}

	void clear();

	boolean isEmpty();

	Integer size();

	void add(V value, P priority);

	void decrease(V value, P priority);

	V remove();

	void addAll(List<V> values, List<P> priorities);

	List<V> removeAll();

}