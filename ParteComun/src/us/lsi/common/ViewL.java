package us.lsi.common;

import java.util.List;

public record ViewL<D,E>(E e, List<D> elems) {
	public static <D,E> ViewL<D,E> of(E e, List<D> elems){
		return new ViewL<D,E>(e,elems);
	}
}
