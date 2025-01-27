package us.lsi.common;

public record View4<D>(D a,D b, D c,D d) {
	
	public static <D> View4<D> of(D a, D b, D c, D d) {
		return new View4<D>(a, b, c, d);
	}

}
