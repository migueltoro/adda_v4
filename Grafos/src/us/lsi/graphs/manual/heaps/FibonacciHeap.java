package us.lsi.graphs.manual.heaps;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import us.lsi.common.List2;
import us.lsi.common.Map2;

class FibonacciHeap<V,P> implements Heap<V, P> {
	
	private org.jheaps.tree.FibonacciHeap<P,V> heap;
	private Map<V,org.jheaps.AddressableHeap.Handle<P,V>> tree;
	
	public static <V,P extends Comparable<P>> FibonacciHeap<V,P> of() {
		return new FibonacciHeap<V, P>(Comparator.naturalOrder());
	}
	
	public static <V, P> FibonacciHeap<V, P> of(Comparator<P> comparator) {
		return new FibonacciHeap<V, P>(comparator);
	}
	
	public FibonacciHeap(Comparator<P> comparator) {
		super();
		this.heap = new org.jheaps.tree.FibonacciHeap<P, V>(comparator);
		this.tree = Map2.empty();
	}

	@Override
	public void clear() {
		this.heap.clear();
	}

	@Override
	public boolean isEmpty() {
		return this.heap.isEmpty();
	}
	
	@Override
	public Integer size() {
		return (int) this.heap.size();
	}

	@Override
	public void add(V value,P priority) {
		org.jheaps.AddressableHeap.Handle<P, V> h = this.heap.insert(priority, value);
		this.tree.put(value, h);
	}

	@Override
	public void decrease(V value,P priority) {
		org.jheaps.AddressableHeap.Handle<P, V> h = this.tree.get(value);
		h.decreaseKey(priority);
	}

	@Override
	public V remove() {
		V v = this.heap.deleteMin().getValue();
		this.tree.remove(v);
		return v;
	}
	
	@Override
	public void addAll(List<V> values, List<P> priorities) {
		for (int i = 0; i < values.size(); i++) {
			this.add(values.get(i), priorities.get(i));
		}	
	}
	
	@Override
	public List<V> removeAll() {
		List<V> r = List2.empty();
		while (!this.isEmpty()) {
			r.add(this.remove());
		}
		return r;	
	}
	
	public static void main(String[] args) {
			
	}

}
