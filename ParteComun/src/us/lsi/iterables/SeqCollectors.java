package us.lsi.iterables;

import java.util.function.Predicate;

public class SeqCollectors {
	
	public static <E> SeqCollector<E,Boolean,Boolean> all(Predicate<E> p){
		return SeqCollector.of(()->true, (b,e)->p.test(e), x->x,b->b);
	}
	
	
}
