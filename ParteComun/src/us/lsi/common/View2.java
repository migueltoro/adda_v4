package us.lsi.common;

public record View2<D>(D left, D right) {
	
	public static <D> View2<D> of(D left, D right) {
		return new View2<D>(left, right);
	}

}
