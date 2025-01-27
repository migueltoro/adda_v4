package us.lsi.common;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import us.lsi.math.Math2;

public class List2 {
	
	
	public static <E> List<E> parse(String[] tokens, Function<String,E> f_map) {
		return Arrays.stream(tokens)
		.filter(e->e!=null && e.length()>0)
		.map(e->f_map.apply(e.trim())).collect(Collectors.toList());
	}
	
	public static <E> List<E> parse(String s, String sep, Function<String,E> f_map) {
		return Arrays.stream(s.split("["+sep+"]"))
		.filter(e->e!=null && e.length()>0)
		.map(e->f_map.apply(e.trim())).collect(Collectors.toList());
	}
	
	/**
	 * @pre La lista no est� vac�a
	 * @param <E> Tipo de los elementos
	 * @param ls Una lista
	 * @return Su primer elemento
	 */
	public static <E> E first(List<E> ls){
		Preconditions.checkArgument(!ls.isEmpty(), "La lista no puede estar vac�a");
		return ls.get(0);
	}
	
	/**
	 * @pre La lista no est� vac�a
	 * @param <E> Tipo de los elementos
	 * @param ls Una lista
	 * @return Su �ltimo elemento
	 */
	public static <E> E last(List<E> ls){
		Preconditions.checkArgument(!ls.isEmpty(), "La lista no puede estar vac�a");
		int n = ls.size();
		return ls.get(n-1);
	}
	
	/**
	 * @param <E> tipo de los elementos de la lista
	 * @param ls Una lista
	 * @pre La lista no puede estar vac�a
	 * @return Una copia de la lista con el �ltimo elemnto eliminado
	 */
	public static <E> List<E> removeLast(List<E> ls){
		Preconditions.checkNotNull(ls);
		Preconditions.checkArgument(!ls.isEmpty(), "La lista no puede estar vac�a");
		List<E> cp = new ArrayList<>(ls);
		int last = cp.size()-1;
		cp.remove(last);
		return cp;
	}
	
	
	/**
	 * @param <E> tipo de los elementos de la lista
	 * @param ls Una lista
	 * @return Una copia de la lista con el elemento a�adido en �ltimo lugar
	 */
	public static <E> List<E> addLast(List<E> ls, E e){
		Preconditions.checkNotNull(ls);
		Preconditions.checkNotNull(e);
		List<E> cp = new ArrayList<>(ls);
		cp.add(e);
		return cp;
	}
	
	/**
	 * @param <E> tipo de los elementos de la lista
	 * @param ls Una lista
	 * @param e Un elemento
	 * @return Una copia de la lista con el elemento a�adido en �ltimo primer lugar
	 */
	public static <E> List<E> addFirst(List<E> ls, E e){
		Preconditions.checkNotNull(ls);
		Preconditions.checkNotNull(e);
		List<E> cp = new ArrayList<>(ls);
		cp.add(0,e);
		return cp;
	}
	
	/**
	 * @param <E> tipo de los elementos de la lista
	 * @param ls Una lista
	 * @param e Un elemento
	 * @param index Un �ndice
	 * @return Una copia de la lista con el elemento cambiado en la posicion index
	 */
	public static <E> List<E> setElement(List<E> ls, int index, E e){
		Preconditions.checkNotNull(ls);
		Preconditions.checkNotNull(e);
		List<E> cp = new ArrayList<>(ls);
		cp.set(index,e);
		return cp;
	}
	
	
	
	/**
	 * @param <E> Tipo de los elementos
	 * @param lis Una lista 
	 * @return Un array equivalente a la lista
	 */
	@SuppressWarnings("unchecked")
	public static <E> E[] toArray(List<E> lis){
		Preconditions.checkArgument(lis!=null && !lis.isEmpty(), "La lista no puede ser vac�a ni null");	
		Class<E> type = (Class<E>) lis.get(0).getClass();
		return lis.stream().toArray((int x)->(E[])Array.newInstance(type, x));
	}
	
	

	/**
	 * @param <E> Tipo de los elementos
	 * @param lista Una lista
	 * @param i Un �ndice de la lista
	 * @param j Un �ndice de la lista
	 * @post Quedan intercambiadas las casillas de �ndices i y j en la lista
	 * @pre Tanto i como j deben ser �ndices de la lista
	 */
	public static <E> void intercambia(List<E> lista, int i, int j){
		int size = lista.size();
		Preconditions.checkPositionIndex(i, size);
		Preconditions.checkPositionIndex(j, size);
		E aux;
		aux = lista.get(i);
		lista.set(i, lista.get(j));
		lista.set(j, aux);
	}

	/**
	 * @param <T> Tipo de los elementos
	 * @param ls Un array
	 * @param i Un �ndice de la lista
	 * @param j Un �ndice de la lista
	 * @post Quedan intercambiadas las casillas de �ndices i y j en el array
	 * @pre Tanto i como j deben ser �ndices del array
	 */
	public static <T> void intercambia(T[] ls, int i, int j){
		int size = ls.length;
		Preconditions.checkPositionIndex(i, size);
		Preconditions.checkPositionIndex(j, size);
		T aux = ls[i];
		ls[i] = ls[j];
		ls[j] = aux;
	}
	
	/**
	 * @param <T> Tipo de los elementos
	 * @return Devuelve una lista vac�a
	 */
	public static <T> List<T> empty(){
	    return new ArrayList<T>();
	}
	
	
	/**
	 * @param <T> Tipo de los elementos
	 * @param n N�mero de copias
	 * @param e Elemento a copiar
	 * @return Devuelve una lista formada por n copias de e
	 */
	public static <T> List<T> nCopies(T e, Integer n){
	    List<T> v = new ArrayList<T>();
	    for(int i=0;i<n;i++){
	       v.add(e);
	    }
	    return v;
	}
	
	/**
	 * @pre n &gt; ls.size()
	 * @param <T> Tipo de los elementos
	 * @param n N�mero de copias
	 * @param e Elemento a copiar
	 * @param ls Una lista de entrada
	 * @return Devuelve una lista de tama�o n formada por una copia de ls ampliada con copias de e
	 */
	public static <T> List<T> copy(List<T> ls, T e, int n){
		Preconditions.checkArgument(n>ls.size());
	    List<T> r = new ArrayList<T>(ls);
	    for(int i=r.size();i<n;i++){
	       r.add(e);
	    }
	    return r;
	}
	
	
	/**
	 * @param <T> Tipo de los elementos
	 * @param ls Una lista de entrada
	 * @return Devuelve una lista formada por una copia de ls
	 */
	public static <T> List<T> copy(List<T> ls){
	    List<T> r = new ArrayList<T>(ls);
	    return r;
	}
	
	/**
	 * @param <T> Tipo de los elementos
	 * @param ls Una lista de listas de entrada
	 * @return Devuelve una lista formada por una copia de ls
	 */
	public static <T> List<List<T>> copy2(List<List<T>> ls){
	   List<List<T>> r = new ArrayList<>();
	   for(List<T> c: ls) {
		   List<T> cp = List2.copy(c);
		   r.add(cp);
	   }
	   return r;
	}
	
	/**
	 * @param a Limite inferior
	 * @param b Limite superior
	 * @return Devuelve la lista formada por los enteros de a hasta b en pasos de 1
	 */
	public static List<Integer> rangeList(Integer a, Integer b){
		return IntStream.range(a,b).boxed().collect(Collectors.toList());
	}

	/**
	 * @param a Limite inferior
	 * @param b Limite superior
	 * @param c Paso
	 * @return Devuelve la lista formada por los enteros de a hasta b en pasos de c
	 */
	public static List<Integer> rangeList(Integer a, Integer b, Integer c){
		Integer np = 1+(b-a)/c;
		return IntStream.range(0,np).map(e->a+e*c).filter(e->e<b).boxed().collect(Collectors.toList());
	}
	/**
	 * @param a Limite inferior
	 * @param b Limite superior
	 * @return Devuelve la lista formada por los reales de a hasta b en pasos de 1
	 */
	public static List<Double> rangeList(Double a, Double b){
		Preconditions.checkArgument(a<=b);
		List<Double> s = List2.empty();
		for(double i = a; i<b; i++){
			s.add(i);
		}
		return s;
	}
	/**
	 * @param a Limite inferior
	 * @param b Limite superior
	 * @param c Paso
	 * @return Devuelve la lista formada por los reales de a hasta b en pasos de c
	 */
	public static List<Double> rangeList(Double a, Double b, Double c){
		Preconditions.checkArgument(a<=b  && c>0);
		List<Double> s = List2.empty();
		for(double i = a; i<b; i=i+c){
			s.add(i);
		}
		return s;
	}
	
	
	/**
	 * @param <E> tipo de los elementos
	 * @param elements Una serie de elementos
	 * @return Una lista construida de first m�s los que est�n en elements
	 */
	@SafeVarargs
	public static <E> List<E> of(E... elements){
		return new ArrayList<>(Arrays.asList(elements));
	}
	
	/**
	 * @param <E> Tipo de los elementos de la lista
	 * @param <U> Tipo de la collecci�n
	 * @param collection Una colecci�n
	 * @return La colecci�n convertida en lista
	 */
	public static <E,U extends Collection<E>> List<E> ofCollection(U collection){
		return collection.stream().collect(Collectors.toList());
	}
	
	/**
	 * @param <E> Tipo de los elementos de la lista
	 * @param ls1 Una lista
	 * @param ls2 Una segunda lista
	 * @return La concatenaci�n d elas dos listas
	 */
	public static <E> List<E> concat(List<E> ls1, List<E> ls2){
		List<E> r = List2.ofCollection(ls1);
		r.addAll(ls2);
		return r;
	}
	
	public static <E> List<E> concatPath(List<E> ls1, List<E> ls2){
		List<E> r = List2.ofCollection(ls1.subList(0,ls1.size()-1));
		r.addAll(ls2);
		return r;
	}
	
	public static <E extends Comparable<? super E>> List<E> mergeOrdered(List<E> l1, List<E> l2){
		return mergeOrdered(l1,l2,Comparator.naturalOrder());
	}
	
	/**
	 * @pre Las listas deben estar ordenadas
	 * @post La lista resultante est� ordenada
	 * @param <E> El tip de los elementos 
	 * @param r1 Una lista ordenada
	 * @param r2 Una lista ordenada
	 * @param cmp U orden
	 * @return Una ordenada que contiene los elementos de ambas listas
	 */
	public static <E> List<E> mergeOrdered(List<E> r1, List<E> r2, Comparator<? super E> cmp){
//		Preconditions.checkState(isOrdered(r1,cmp));
//		Preconditions.checkState(isOrdered(r2,cmp));
		List<E> r3 = List2.empty();
		int k1= 0;
		int k2= 0;
		int k3= 0;
		int j1 = r1.size();
		int j2 = r2.size();
		int j3 = j1+j2;
		while(k3<j3){
			if(k1<j1 && k2<j2){
				if(cmp.compare(r1.get(k1), r2.get(k2))<=0){
					r3.add(r1.get(k1));
					k1++;
					k3++;
				}else{
					r3.add(r2.get(k2));
					k2++;
					k3++;
				}
			}else if(k2==j2){
				r3.add(r1.get(k1));
				k1++;
				k3++;
			}else{
				r3.add(r2.get(k2));
				k2++;
				k3++;
			}
		}
//		Preconditions.checkState(isOrdered(r3,cmp));		
		return r3;
	}
	
	public static <E extends Comparable<? super E>> void insertOrdered(List<E> ls, E e) {
		insertOrdered(ls,e,Comparator.naturalOrder());
	}
	
	/**
	 * @pre La lista est� ordenada
	 * @post La lista resultante est� ordenada y contiene e
	 * @param <E> El tipo de los elementos de la lista
	 * @param ls Una lista ordenada
	 * @param e Un elemento
	 * @param cmp Un  orden
	 */
	
	public static <E> void insertOrdered(List<E> ls, E e, Comparator<? super E> cmp) {
//		Preconditions.checkState(isOrdered(ls, cmp));
		int i = 0;
		while (i < ls.size()) {
			if(Comparator2.isGT(ls.get(i),e, cmp)) break;
			i++;
		}
		ls.add(i, e);
//		Preconditions.checkState(isOrdered(ls, cmp));
	}

	public static <E extends Comparable<? super E>> Boolean isOrdered(List<E> ls) {	
		return IntStream.range(0,ls.size()-1)
				.allMatch(i->ls.get(i).compareTo(ls.get(i+1))<=0);
	}
	
	/**
	 * 
	 * @param <E> El tipo de los elementos de la lista
	 * @param ls Una lista ordenada
	 * @param cmp Un  orden
	 * @return Si la lista est� ordenada con respecto al orden
	 */

	public static <E> Boolean isOrdered(List<E> ls, Comparator<? super E> cmp) {
		return IntStream.range(0,ls.size()-1)
				.allMatch(i->cmp.compare(ls.get(i),ls.get(i+1))<=0);
	}
	
	/**
	 * @param <T> Tipo de los elementos
	 * @param ls Una lista
	 * @return Una lista unitaria escogida aleatoriamente o vac�a si lo es la de entrada
	 */
	
	public static <T> List<T> randomUnitary(Collection<T> ls){
		List<T> rs = ls.stream().toList();
		List<T> r = List2.empty();
		if(!rs.isEmpty()){
			int e = Math2.getEnteroAleatorio(0, ls.size());
			r.add(rs.get(e));	
		}
		return r;
	}
	
	public static <T> List<T> random(Collection<T> ls, Integer n){
		List<T> rs = ls.stream().collect(Collectors.toList());
		Collections.shuffle(rs, Math2.rnd);
		return rs.subList(0, Math.min(n,rs.size()));
	}

	/**
	 * @param <T> Tipo de los elementos
	 * @param ls Una lista
	 * @return Una lista con la casilla primera intercambiada por la �ltima, 
	 * la segunda por la pen�ltima, etc.
	 */
	public static <T> List<T> reverse(List<T> ls){
		final int n = ls.size();
		return IntStream.range(0,n)
				.mapToObj(i->ls.get(n-1-i))
				.collect(Collectors.toList());
	}
	
	public static <E> List<E> difference(Collection<E> s1,  Collection<E> s2){
		List<E> s = new ArrayList<>(s1);
		s.removeAll(s2);
		return s;
	}
	
	public static <E> List<E> symmetricDifference(Collection<E> s1,  Collection<E> s2){
		List<E> symmetricDiff = new ArrayList<E>(s1);
	    symmetricDiff.addAll(s2);
	    List<E> tmp = new ArrayList<E>(s1);
	    tmp.retainAll(s2);
	    symmetricDiff.removeAll(tmp);
	    return symmetricDiff;
	}
	
	public static <E> List<E> union(Collection<E> s1,  Collection<E> s2){
		List<E> s = new ArrayList<>(s1);
		s.addAll(s2);
		return s;
	}
	
	public static <E> List<E> intersection(Collection<E> s1,  Collection<E> s2){
		List<E> s = new ArrayList<>(s1);
		s.retainAll(s2);
		return s;
	}
	
	/**
	 * @param ls Una lista
	 * @return Una vista de tipo 1
	 */
	public static <E> View1<List<E>,E> view1(List<E> ls){
		int n = ls.size();
		Preconditions.checkArgument(n>0,String.format("La lista debe ser de tama�o mayor que 0 y es %d  ",n));
		return View1.of(ls.get(0),ls.subList(1,ls.size()));
	}
	
	
	/**
	 * @param ls Una lista
	 * @return Una vista de tipo 2e sin solape
	 */
	public static <E> View2E<List<E>,E> view2e(List<E> ls){		
		int n = ls.size();
		Preconditions.checkArgument(n>1,String.format("La lista debe ser de tam�o mayor que 1 y es %d  ",n));
		int k = n/2;
		return View2E.of(ls.get(k),ls.subList(0, k), ls.subList(k,n));
	}
	
	/**
	 * @param ls Una lista
	 * @return Una vista de tipo 2e con solape
	 */
	public static <E> View2E<List<E>,E> view2eOverlap(List<E> ls){		
		int n = ls.size();
		Preconditions.checkArgument(n>1,String.format("La lista debe ser de tam�o mayor que 1 y es %d  ",n));
		int k = n/2;
		return View2E.of(ls.get(k),ls.subList(0, k+1), ls.subList(k,n));
	}
	
	/**
	 * @param ls Una lista
	 * @return Una vista de tipo 2 sin solape
	 */
	public static <E> View2<List<E>> view2(List<E> ls){		
		int n = ls.size();
		Preconditions.checkArgument(n>1,String.format("La lista debe ser de tam�o mayor que 1 y es %d  ",n));
		int k = n/2;
		return View2.of(ls.subList(0, k), ls.subList(k,n));
	}
	
	/**
	 * @param ls Una lista
	 * @return Una vista de tipo 2 con solape
	 */
	public static <E> View2<List<E>> view2Overlap(List<E> ls){		
		int n = ls.size();
		Preconditions.checkArgument(n>1,String.format("La lista debe ser de tam�o mayor que 1 y es %d  ",n));
		int k = n/2;
		return View2.of(ls.subList(0, k+1), ls.subList(k,n));
	}

	/**
	 * @param n Un entero
	 * @param a Un double 
	 * @param b Un segundo double
	 * @return Una lista de tamanyo n con numeros reales en el intervalo a &lt; = r &lt; b 
	 */
	public static List<Double> listDoubleAleatoria(int n, double a, double b){
		List<Double> lista = empty();
		for(int i =0; i<n;i++){
			lista.add(Math2.getDoubleAleatorio(a,b));
		}
		return lista;
	}
	
}

