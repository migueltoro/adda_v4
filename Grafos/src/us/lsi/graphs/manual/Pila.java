package us.lsi.graphs.manual;

public class Pila<E> extends AgregadoLineal<E>{
	
	public static <E> Pila<E> of() {
		return new Pila<>();
	}

	private Pila() {
		super();
	}

	@Override
	public void add(E e) {
		this.elements.add(0, e);
	}

}
