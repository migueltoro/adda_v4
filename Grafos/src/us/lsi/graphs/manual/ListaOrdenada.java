package us.lsi.graphs.manual;

import java.util.Comparator;

public class ListaOrdenada<E> extends AgregadoLineal<E> {

	protected Comparator<E> cmp;

	public static <E> ListaOrdenada<E> of(Comparator<E> comparator) {
		return new ListaOrdenada<>(comparator);
	}

	protected ListaOrdenada(Comparator<E> comparator) {
		super();
		this.cmp = comparator;
	}

	protected int indexOrder(E e) {
		int ln = this.elements.size();
		if (ln == 0)
			return 0;
		int i = 0;
		if (this.isEmpty() || this.cmp.compare(e, this.elements.get(0)) < 0)
			return 0;
		if (this.cmp.compare(this.elements.get(ln - 1), e) <= 0)
			return ln;
		for (i = 0; i < ln; i++) {
			if (this.cmp.compare(this.elements.get(i), e) <= 0 && this.cmp.compare(this.elements.get(i + 1), e) > 0)
			return i + 1;
		}
		return i;
	}

	void add(E e) {
		int i = this.indexOrder(e);
		this.elements.add(i, e);
	}
}
