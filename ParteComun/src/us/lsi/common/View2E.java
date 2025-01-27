package us.lsi.common;

public record View2E<D,E>(E e,D left,D right) {
	
	public static <D, E> View2E<D, E> of(E e, D left, D right) {
		return new View2E<D, E>(e, left, right);
	}

}
