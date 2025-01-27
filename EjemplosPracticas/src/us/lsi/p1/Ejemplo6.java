package us.lsi.p1;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

public class Ejemplo6 {
	/*
	PI1 - Ejemplo 6

	Analizar los tiempos de ejecución de las búsquedas lineal y binaria, 
	usando en los casos que correspondan listas ordenadas o desordenadas.
	*/

	public static <E> Integer busquedaLineal(List<E> ls, E elem) {
		Integer n = ls.size();
		Integer i = 0;
		Boolean b = false;
		while (i < n && !b) {
			if (ls.get(i).equals(elem)) {
				b = true;
			} else {
				i++;
			}
		}
		return b?i:-1;
	}
	
	public static <E> Integer busquedaLinealR(List<E> ls, E elem) {
		return busquedaLinealR(0,ls,elem);
	}
	
	private static <E> Integer busquedaLinealR(Integer i, List<E> ls, E elem) {
	 	Integer b = -1;
	 	Integer n = ls.size();
		if(n-i> 0){
			if (ls.get(i).equals(elem)) b = i;
	    		else b = busquedaLinealR(i+1,ls,elem);
		}
		return b;
	}

	public static <E> Integer busquedaLinealFun(List<E> ls, E elem) {
		return IntStream.range(0, ls.size())
				.map(i-> elem.equals(ls.get(i))?i:-1)
				.filter(i-> i>-1)
				.findFirst().orElse(-1);
	}

	public static <E extends Comparable<? super E>> Integer busquedaLinealOrdI(List<E> ls, E elem) {
		return busquedaLinealOrdI(ls, elem, Comparator.naturalOrder());
	}
	
	public static <E> Integer busquedaLinealOrdI(List<E> ls, E elem, Comparator<E> cmp) {
		Integer r = -1;
		Integer i = 0;
		Boolean fin = false;
		Iterator<E> iterator = ls.iterator();
		while (iterator.hasNext() && !fin) {
			E e = iterator.next();
	      	if (cmp.compare(elem, e) == 0) {
	      		r = i;
	      		fin = true;
	      	} else if(cmp.compare(elem, e) < 0) {
	      		r = -1;
	      		fin = true;
	      	}
	   		else i++;
		}
		return r;
	}

	public static <E extends Comparable<? super E>> Integer busquedaLinealOrdI2(List<E> ls, E elem) {
		return busquedaLinealOrdI2(ls, elem, Comparator.naturalOrder());
	}
	
	public static <E> Integer busquedaLinealOrdI2(List<E> ls, E elem, Comparator<E> cmp) {
		Integer r = -1;
		Integer i = 0;
		Boolean fin = false;
		Iterator<E> iterator = ls.iterator();
		while (iterator.hasNext() && !fin) {
			E e = iterator.next();
	      	if (cmp.compare(elem, e) <= 0) {
	      		if (cmp.compare(elem, e) == 0)
	      			r = i;
	      		else
	      			r = -1;
	      		fin = true;
	      	}
	   		else i++;
		}
		return r;
	}

	public static <E extends Comparable<? super E>> Integer busquedaLinealOrdI3(List<E> ls, E elem) {
		return busquedaLinealOrdI2(ls, elem, Comparator.naturalOrder());
	}
	
	public static <E> Integer busquedaLinealOrdI3(List<E> ls, E elem, Comparator<E> cmp) {
		Integer r = -1;
		Integer i = 0;
		Boolean fin = false;
		Iterator<E> iterator = ls.iterator();
		while (iterator.hasNext() && !fin) {
			E e = iterator.next();
			if (cmp.compare(elem, e) > 0) 
				i++;
			else {
				if (cmp.compare(elem, e) == 0)
					r = i;
				else
					r = -1;
	      		fin = true;
	      	}
		}
		return r;
	}

		
	
	public static <E extends Comparable<? super E>> Integer busquedaLinealOrdR(List<E> ls, E elem) {
		return busquedaLinealOrdR(ls, elem, Comparator.naturalOrder());
	}
	
	public static <E> Integer busquedaLinealOrdR(List<E> ls, E elem, Comparator<E> comparator) {
		return busquedaLinealOrdR(0,ls.iterator(),elem,comparator);
	}
	
	private static <E> Integer busquedaLinealOrdR(Integer i, Iterator<E> iterator, E elem, Comparator<E> c) {
		Integer r = -1;
		if(iterator.hasNext()){
			E e = iterator.next();
	      	if (c.compare(elem, e) == 0) r = i;
			else if(c.compare(elem, e) < 0) r = -1;
	   		else r = busquedaLinealOrdR(i+1,iterator,elem,c);
		}
		return r;
	}

	
	public static <E extends Comparable<? super E>> Integer busquedaBinaria(List<E> ls, E elem) {
		return busquedaBinaria(ls, elem, Comparator.naturalOrder());
	}
	
	private static <E> Integer busquedaBinaria(List<E> ls, E elem, Comparator<E> comparator) {
		Integer i = 0;
		Integer j = ls.size();
		Integer k = (i+j)/2;
		Integer r = -1;
		while (j-i> 0 && r==-1) {
			if (((Comparator<? super E>)comparator).compare(elem, ls.get(k)) == 0) r = k;
			else if (((Comparator<? super E>)comparator).compare(elem, ls.get(k)) < 0) {
				j = k;
				k = (i+j)/2;
			} else {
				i = k+1;
				k = (i+j)/2;
			}
		}
		return r;
	}
	
	public static <E extends Comparable<? super E>> Integer busquedaBinariaR(List<E> ls, E elem) {
		return busquedaBinariaR(ls, elem, Comparator.naturalOrder());
	}
	
	public static <E> Integer busquedaBinariaR(List<E> ls, E elem, Comparator<E> cmp) {
		Integer n = ls.size();
		return busquedaBinariaR(0,n,ls,elem,cmp);
	}
	
	private static <E> Integer busquedaBinariaR(Integer i, Integer j, List<E> ls, E elem, Comparator<E> cmp) {
	 	Integer r = -1;
		if(j-i> 0){
			Integer k = (j+i)/2;
			if (cmp.compare(elem, ls.get(k)) == 0) r = k;
			else if (cmp.compare(elem, ls.get(k)) < 0) 
				r = busquedaBinariaR(i,k,ls,elem,cmp);
			else r = busquedaBinariaR(k+1,j,ls,elem,cmp); 
		}
		return r;
	}
	
}

