package us.lsi.hypergraphsD;

import java.util.Comparator;

public record Sp<E>(Double weight, E edge) implements Comparable<Sp<E>> {
	
	public static <E> Sp<E> of(Double weight,E edge) {
		return new Sp<>(weight,edge);
	}
	
	public static <E> Sp<E> of(Double weight) {
		return new Sp<>(weight,null);
	}
	
	public static <E> Comparator<Sp<E>> comparator() {
		return Comparator.naturalOrder();
	}

	@Override
	public int compareTo(Sp<E> sp) {
		return this.weight.compareTo(sp.weight);
	}
}
