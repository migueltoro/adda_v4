package us.lsi.graphs.manual;

import java.util.Comparator;

public class ListaOrdenadaSinRepeticion<E> extends ListaOrdenada<E> {

	public static <E> ListaOrdenadaSinRepeticion<E> of(Comparator<E> comparator) {
		return new ListaOrdenadaSinRepeticion<>(comparator);
	}

	protected ListaOrdenadaSinRepeticion(Comparator<E> comparator) {
		super(comparator);
	}

	@Override
	void add(E e) {
		if(this.elements.contains(e))
			return;
		int i = this.indexOrder(e);
		this.elements.add(i, e);
	}
	

}
