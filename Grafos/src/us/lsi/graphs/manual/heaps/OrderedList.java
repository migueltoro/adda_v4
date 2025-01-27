package us.lsi.graphs.manual.heaps;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import us.lsi.common.Preconditions;

public class OrderedList<V, P> implements Heap<V, P> {
	
	private List<V> elements;
	private List<P> priorities;
	private Comparator<P> comparator;
	
	public static <V, P extends Comparable<P>> OrderedList<V, P> of() {
		return new OrderedList<V, P>(Comparator.naturalOrder());
	}
	
	public static <V, P> OrderedList<V, P> of(Comparator<P> comparator) {
		return new OrderedList<V, P>(comparator);
	}
	
	public OrderedList(Comparator<P> comparator) {
		super();
		this.elements = new ArrayList<>();
		this.priorities = new ArrayList<>();
		this.comparator = comparator;
	}

	@Override
	public void clear() {
		 this.elements.clear();
		 this.priorities.clear();
	}

	@Override
	public boolean isEmpty() {
		return this.elements.isEmpty();
	}

	@Override
	public Integer size() {
		return this.elements.size();
	}
	
//  Obtiene el índice del elemento que es menor o igual que e y tal que el siguiente elemento es mayor que e
	private int indexOrder(P priority) {
		int ln = this.elements.size();
		if (this.isEmpty() || this.comparator.compare(priority, this.priorities.get(0)) < 0)
			return 0;
		if (this.comparator.compare(this.priorities.get(ln - 1), priority) <= 0)
			return ln;
		for (int i = 0; i < ln; i++) {
			if (this.comparator.compare(this.priorities.get(i), priority) <= 0
					&& this.comparator.compare(this.priorities.get(i + 1), priority) > 0) {
				return i + 1;
			}
		}
		return -1;
	}

	@Override
	public void add(V e, P priority) {
        int i = this.indexOrder(priority);
        this.elements.add(i,e);
        this.priorities.add(i,priority);
	}

	@Override
	public void decrease(V e, P priority) {
		int index = this.elements.indexOf(e);
		if (comparator.compare(priority,this.priorities.get(index)) < 0) {
		            this.elements.remove(index);
		            this.priorities.remove(index);
		            this.add(e,priority);
		}
	}

	@Override
	public V remove() {
		Preconditions.checkState(!this.isEmpty(), "The heap is empty");
		return this.elements.remove(0);
	}

	@Override
	public void addAll(List<V> values, List<P> priorities) {
		for(int i=0; i<values.size(); i++) 
            this.add(values.get(i),priorities.get(i));
	}

	@Override
	public List<V> removeAll() {
		List<V> ls = new ArrayList<>();
		while (!this.isEmpty()) 
		     ls.add(this.remove());
		return ls;
	}

}
