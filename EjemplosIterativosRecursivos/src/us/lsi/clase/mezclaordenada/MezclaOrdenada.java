package us.lsi.clase.mezclaordenada;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import us.lsi.common.List2;
import us.lsi.iterables.IteratorWithPeek;

public class MezclaOrdenada {
	
	public static <E> List<E> mezclaOrdenada(List<E> ls1, List<E> ls2, Comparator<E> cmp) {
		List<E> ls3 = new ArrayList<>();
		Integer i1, i2;
		Integer n1 = ls1.size();
		Integer n2 = ls2.size();
		i1 = 0;
		i2 = 0;
		while (n1 + n2 - i1 -i2 > 0) {
			E e;
			if (i2 < n2 && i1 < n1) {
				if(cmp.compare(ls1.get(i1), ls2.get(i2)) <= 0) {
                    e = ls1.get(i1);
                    i1 = i1 + 1;
                } else {
                    e = ls2.get(i2);
                    i2 = i2 + 1;
                }
			} else if (i2 < n2) {
				e = ls2.get(i2);
				i2 = i2 + 1;
			} else {
				e = ls1.get(i1);
				i1 = i1 + 1;
			}
			ls3.add(e);
		}
		return ls3;
	}
	
	public static <E> void mezclaOrdenada(Iterable<E> it1, Iterable<E> it2, String fileOut, Comparator<E> cmp)
			throws FileNotFoundException {
		IteratorWithPeek<E> itt1 = IteratorWithPeek.of(it1.iterator());
		IteratorWithPeek<E> itt2 = IteratorWithPeek.of(it2.iterator());
		PrintStream f = new PrintStream(fileOut);
		while(itt1.hasNext() || itt2.hasNext()) {
			E e;
			if (itt1.hasNext() && itt2.hasNext()) {
				e = cmp.compare(itt1.peek(), itt2.peek()) <= 0 ? itt1.next() : itt2.next(); 
			} else if (itt2.hasNext()) {
				e = itt2.next();
			} else {
				e = itt1.next();
			} 
			f.println(e.toString());
		}
		f.close();
	}
	
	public static <E> List<E> mezclaOrdenada(Iterable<E> it1, Iterable<E> it2, Comparator<E> cmp) {
		List<E> ls3 = new ArrayList<>();
		IteratorWithPeek<E> itt1 = IteratorWithPeek.of(it1.iterator());
		IteratorWithPeek<E> itt2 = IteratorWithPeek.of(it2.iterator());
		while (itt1.hasNext() || itt2.hasNext()) {
			E e;
			if (itt1.hasNext() && itt2.hasNext()) {
				e = cmp.compare(itt1.peek(), itt2.peek()) <= 0 ? itt1.next() : itt2.next();
			} else if (itt2.hasNext()) {
				e = itt2.next();
			} else {
				e = itt1.next();
			}
			ls3.add(e);
		}
		return ls3;
	}
	
	public static <E> List<E> mezclaOrdenadaRec(Iterable<E> it1, Iterable<E> it2, Comparator<E> cmp) {
		List<E> ls3 = new ArrayList<>();
		IteratorWithPeek<E> itt1 = IteratorWithPeek.of(it1.iterator());
		IteratorWithPeek<E> itt2 = IteratorWithPeek.of(it2.iterator());
		mezclaOrdenadaRec(ls3, itt1, itt2, cmp);
		return ls3;
	}
	
	private static <E> void mezclaOrdenadaRec(List<E> ls3, IteratorWithPeek<E> itt1, 
			IteratorWithPeek<E> itt2,
			Comparator<E> cmp) {
		E e;
		if (itt1.hasNext() && itt2.hasNext()) {
			e = cmp.compare(itt1.peek(), itt2.peek()) <= 0 ? itt1.next() : itt2.next();
		} else if (itt2.hasNext()) {
			e = itt2.next();
		} else if (itt1.hasNext()) {
			e = itt1.next();
		} else {
			return;
		}
		ls3.add(e);
		mezclaOrdenadaRec(ls3, itt1, itt2, cmp);
	}
	
	/**
	 * Este método mezcla dos streams ordenados en un solo stream ordenado con orden de complejidad O(n)
	 *
	 * @param <E> El tipo de elementos en los streams
	 * @param s1 El primer stream de entrada, que debe estar ordenado
	 * @param s2 El segundo stream de entrada, que debe estar ordenado
	 * @param cmp El comparador para determinar el orden de los elementos
	 * @return Un nuevo stream que contiene todos los elementos de ambos streams de entrada, ordenados de acuerdo al comparador proporcionado
	 */
	
	public static <E> Stream<E> mezclaOrdenada(Stream<E> s1, Stream<E> s2, Comparator<E> cmp) {
		IteratorWithPeek<E> it1 = IteratorWithPeek.of(s1.iterator());
		IteratorWithPeek<E> it2 = IteratorWithPeek.of(s2.iterator());
		Supplier<E> sp = () -> {
			E e;
			if (it1.hasNext() && it2.hasNext()) {
				e = cmp.compare(it1.peek(), it2.peek()) <= 0 ? it1.next() : it2.next(); 
			} else if (it2.hasNext()) {
				e = it2.next();
			} else if (it1.hasNext()){
				e = it1.next();
			} else {
                e = null;
			}
			return e;
		};
		Stream<E> st = Stream.generate(sp).takeWhile(x->x!=null);
		return st;
	}
	
	public static void test1() throws IOException {
		List<Integer> l1 = List2.of(1,3,5,7,9,11);
		List<Integer> l2 = List2.of(0,2,4,10,19,21,23,45);
		List<Integer> l3 = mezclaOrdenada(l2,l1, Comparator.naturalOrder());
		mezclaOrdenada(l1,l2,"ficheros/numeros_sal.txt",Comparator.naturalOrder());
		System.out.println(String.format("5: %s",l3));
		Stream<Integer> l4 = mezclaOrdenada(l1.stream(),l2.stream(),Comparator.naturalOrder());
		System.out.println(String.format("6: %s",l4.toList()));
		List<Integer> l5 = mezclaOrdenadaRec(l1,l2,Comparator.naturalOrder());
		System.out.println(String.format("7: %s",l5));
	}
	
	public static void main(String[] args) throws IOException {
		test1();
	}

}
