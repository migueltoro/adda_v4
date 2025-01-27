package us.lsi.common;

public record Trio<A, B, C>(A first,B second,C third) {

	public static <A, B, C> Trio<A, B, C> of(A first, B second, C third) {
		return new Trio<A, B, C>(first, second, third);
	}

	@Override
	public String toString() {
		return String.format("(%s,%s,%s)",first,second,third);
	}
	
}
