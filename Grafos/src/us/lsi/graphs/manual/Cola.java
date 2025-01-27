package us.lsi.graphs.manual;

public class Cola<E> extends AgregadoLineal<E>{
	
	public static <E> Cola<E> of() {
		return new Cola<>();
	}

	private Cola() {
		super();
	}

	@Override
	public void add(E e) {
		this.elements.add(e);
	}

}
