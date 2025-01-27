package us.lsi.graphs.manual.heaps;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.lsi.common.Preconditions;

public class BinaryHeap<V, P> implements Heap<V, P> {
	
	private static class Datos<P> {
			P priority;
			int index;
			
			public static <P> Datos<P> of(P priority, int index) {
				return new Datos<P>(priority, index);
			}
			
			private Datos(P priority, int index) {
				super();
				this.priority = priority;
				this.index = index;
			}
	}
	
	private List<V> elements;
	private Map<V,Datos<P>> map;
	private Comparator<P> comparator;
	
	public static <V, P extends Comparable<P>> BinaryHeap<V, P> of() {
		return new BinaryHeap<V, P>(Comparator.naturalOrder());
	}
	
	public static <V, P> BinaryHeap<V, P> of(Comparator<P> comparator) {
        return new BinaryHeap<V, P>(comparator);	
	}
	
	public BinaryHeap(Comparator<P> comparator) {
		super();
		this.elements = new ArrayList<>();
		this.map = new HashMap<>();
		this.comparator = comparator;
	}
	
	private int last() {
		return this.elements.size() - 1;
	}
	
	private int parent(int i) {
		return (i - 1) / 2;
	}
	
	private int left(int i) {
		int r = 2 * i + 1;
		return r <= this.last() ? r : -1;
	}
    
	private int right(int i) {
		int r = 2 * i + 2;
		return r <= this.last() ? r : -1;
	}
	
	private P priority(int index) {
		return this.map.get(this.elements.get(index)).priority;
	}
	
	private void swap(int i, int j) {
		V e = this.elements.get(i);
		this.elements.set(i, this.elements.get(j));
		this.elements.set(j, e);
		this.map.get(this.elements.get(i)).index = i;
		this.map.get(this.elements.get(j)).index = j;
	}
	
	private int choose(int i) {
		int left = this.left(i);
		int right = this.right(i);
		if (left == -1 && right == -1) return -1;
		if (right == -1  && this.comparator.compare(this.priority(i),this.priority(left))< 0) return -1;
		if (right == -1) return left;
		if (this.comparator.compare(this.priority(i),this.priority(right)) < 0
				&& this.comparator.compare(this.priority(i),this.priority(left)) < 0) {
			return -1;
		}
		return this.comparator.compare(this.priority(left),this.priority(right)) < 0 ? left : right;
	}
	
	
	private void up(int i) {
		while (i > 0 && this.comparator.compare(this.priority(i), this.priority(this.parent(i))) < 0) {
			this.swap(i, this.parent(i));
			i = this.parent(i);
		}
	}
	
	private void down(int i) {
		while (true) {
			int m = this.choose(i);
			if (m == -1) {
				break;
			}
			this.swap(i, m);
			i = m;
		}
	}
		
	private Integer index(V e) {
		return this.map.get(e).index;
	}
	

	@Override
	public void clear() {
		this.elements.clear();
		this.map.clear();	
	}

	@Override
	public boolean isEmpty() {
		return this.elements.isEmpty();
	}

	@Override
	public Integer size() {
		return this.elements.size();
	}

	@Override
	public void add(V e, P priority) {
		int index = this.elements.size();
		this.elements.add(e);
		Datos<P> d = Datos.of(priority,index);     
        this.map.put(e, d);
        this.up(this.last());
	}

	@Override
	public void decrease(V value, P priority) {
		int i = this.index(value);
		this.map.get(value).priority = priority;
		this.up(i);	
	}

	@Override
	public V remove() {
		Preconditions.checkArgument(!this.isEmpty(), "El agregado está vacío");
		V r = this.elements.get(0);
		this.map.remove(r);
		this.elements.set(0, this.elements.get(this.last()));
		this.elements.remove(this.last());		
		this.down(0);
		return r;
	}

	@Override
	public void addAll(List<V> values, List<P> priorities) {
		for (int i = 0; i < values.size(); i++) {
			this.add(values.get(i), priorities.get(i));
		}	
	}

	@Override
	public List<V> removeAll() {
		List<V> r = new ArrayList<>();
		while (!this.isEmpty()) {
			r.add(this.remove());
		}
		return r;
	}

}
