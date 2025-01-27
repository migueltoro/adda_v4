package us.lsi.graphs.manual;

import java.util.ArrayList;
import java.util.List;

public abstract class AgregadoLineal<E> {

	protected List<E> elements;

	protected AgregadoLineal() {
		this.elements = new ArrayList<>();
	}

	abstract void add(E e);

	public E remove() {
		assert !this.elements.isEmpty();
		return this.elements.remove(0);
	}

	public void addAll(List<E> ls) {
		ls.stream().forEach(e -> this.add(e));
	}

	public List<E> removeAll() {
		List<E> ls = new ArrayList<>();
		while (!this.isEmpty()) {
			ls.add(this.remove());
		}
		return ls;
	}

	public int size() {
		return this.elements.size();
	}

	public Boolean isEmpty() {
		return this.elements.isEmpty();
	}

}
