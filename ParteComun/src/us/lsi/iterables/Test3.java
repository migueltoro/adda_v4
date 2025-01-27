package us.lsi.iterables;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Test3 {

	public static void main(String[] args) {
		List<Set<Integer>> ls1 = List.of(Set.of(23,45),Set.of(),Set.of(23,45),Set.of(23,45),Set.of(23,45),Set.of());
		Iterator<Set<Integer>> h1 = ls1.iterator();
		Iterator<Integer> h2 = IteratorFlatMap.of(h1,x->x);
		h2.forEachRemaining(x->System.out.println(x+","));
		System.out.println(" __________ ");

	}

}
