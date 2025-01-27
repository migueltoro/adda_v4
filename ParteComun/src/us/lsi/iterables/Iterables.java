package us.lsi.iterables;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import us.lsi.common.Enumerate;
import us.lsi.common.Pair;
import us.lsi.common.Preconditions;
import us.lsi.common.View1;

public class Iterables {
	
	/**
	 * @param iterable Un iterable 
	 * @return Un iterador con todos los pares que formar el producto cartesiano de los elementos recorridos por el iterable 
	 */
	public static <E> Iterable<Pair<E,E>> cartesianProduct(Iterable<E> iterable) {
		return IteratorCartesianProduct.of(iterable);
	}
	
	/**
	 * @param iterableA Un iterable 
	 * @param iterableB Un iterable 
	 * @return Un iterador con todos los pares que formar el producto cartesiano de los elementos recorridos por el iterable 
	 */
	public static <A,B> Iterable<Pair<A,B>> cartesianProduct(Iterable<A> iterableA, Iterable<B> iterableB) {
		return IteratorCartesianProduct.of(iterableA,iterableB);
	}
	
	/**
	 * @param iterator Un iterador
	 * @return Un iterador con todos los pares que formar el producto cartesiano de los elementos recorridos por el iterador 
	 */
	public static <E> Iterable<Pair<E,E>> consecutivePairs(Iterable<E> iterator) {
		return IteratorConsecutivePairs.of(iterator);
	}

	/**
	 * @param iteratorA Un iterador
	 * @param iteratorB Un iterador
	 * @return Un iteradpr que recorre los pares formados en cremallera con los elementos del iteratorA y el iteratorB
	 */
	public static <A, B> Iterable<Pair<A, B>> zip(Iterable<A> iteratorA, Iterable<B> iteratorB){
		return IteratorZip.of(iteratorA, iteratorB);		
	}
	
	/**
	 * @param iterator Un iterador
	 * @return Un iterador con todos los pares los elementos del iterador de entrada y su ppsici�n empezando por cero
	 */
	public static <E> Iterable<Enumerate<E>> enumerate(Iterable<E> iterator) {
		return IteratorEnumerate.of(iterator);
	}
	
	public static <E> Iterable<E> fusionOrdered(Iterable<E> iteratorA, Iterable<E> iteratorB, Comparator<E> cmp) {
		return IteratorFusionOrdered.of(iteratorA,iteratorB,cmp);
	}
	
	public static <E> Iterable<E> distinct(Iterable<E> iterator) {
		return IteratorDistinct.of(iterator);
	}
	
	public static <E> Iterable<E> sorted(Iterable<E> iterator, Comparator<E> cmp) {
		List<E> ls = Iterables.toList(iterator);
		Collections.sort(ls, cmp);
		return ls;
	}
	
	/**
	 * @param file Un fichero
	 * @return Un iterable que recorre las l�neas del fichero
	 */
	public static Iterable<String> file(String file) {
		return new IteratorFile(file);	
	}
	
	/**
	 * @param e Una cadena de entrada
	 * @param delim Un delimimitador o una expresi�n regular expresando un conjunto de delimitadores
	 * @return Un iterador que recorre las partes de e delimitadas por elementos en delim
	 */
	public static Iterable<String> split(String e, String delim){
		String[] r = e.split(delim);
		Iterable<String> r2 = Arrays.asList(r);
		Iterator<String> r3 = IteratorMap.of(r2,x->x.trim()) ;
		return IteratorFilter.of(r3,x->x.length() > 0) ;
	}
	
	public static <E,R> Iterable<R> flatMap(Iterable<E> iterator, Function<E,Iterable<R>> fmap){
		return new IteratorFlatMap<E,R>(iterator.iterator(),fmap);
	}
	
	public static <E,R> Iterable<R> map(Iterable<E> iterator, Function<E, R> fmap) {
		return new IteratorMap<E,R>(iterator.iterator(), fmap);
	}
	
	public static <E> Iterable<E> filter(Iterable<E> iterator, Predicate<E> p){
		return new IteratorFilter<>(iterator.iterator(),p);
	}
	
	public static <T> Iterable<T> explicit(T t0, Predicate<T> hn, UnaryOperator<T> nx){
		return new IteratorExplicit<>(t0,hn,nx,x->x);
	}
	
	public static <E,T> Iterable<E> explicit(T t0, Predicate<T> hn, UnaryOperator<T> nx, Function<T, E> nx1){
		return new IteratorExplicit<>(t0,hn,nx,nx1);
	}
	
	public static <E> Iterable<E> ofIterator(Iterator<E> it) { 
	    return ()->it;
	}
	
	public static <E> Iterable<E> ofStream(Stream<E> stream) { 
	    return () -> stream.iterator();
	}
	
	/**
	 * @return Un iterador vac�o
	 */
	public static <E> Iterable<E> empty() {
		return IteratorEmpty.of();
	}
	
	
	public static <E> Optional<E> reduce(Iterable<E> iterable, BinaryOperator<E> op) {
		Iterator<E> it = iterable.iterator();
		E b = null;
		while(it.hasNext()) {
			E e = it.next();
			if (b == null) b = e;	
			else b = op.apply(b, e);
		}
		return Optional.ofNullable(b);
	}
	
	public static <E,B> B reduce(Iterable<E> iterable, BiFunction<B,E,B> op, B b0) {
		Iterator<E> it = iterable.iterator();
		B b = b0;
		while(it.hasNext()) {
			E e = it.next();
			b = op.apply(b, e);
		}
		return b;
	}
		
	public static <E,B> B reduce(Iterable<E> iterable, BiFunction<B,E,B> op, B b0, Predicate<B> p) {
		Iterator<E> it = iterable.iterator();
		B b = b0;
		while(it.hasNext() && !p.test(b)) {
			E e = it.next();
			b = op.apply(b, e);
		}
		return b;
	}

	
	public static <E> Optional<E> reduceRight(Iterable<E> iterable, BinaryOperator<E> op) {
		Iterator<E> it = iterable.iterator();
		E b = null;
		b = reduceRight(it, b , op);
		return Optional.ofNullable(b);
	}
	
	private static <E> E reduceRight(Iterator<E> it,E b,BinaryOperator<E> op){
		if(it.hasNext()) {
			E e = it.next();
			b = reduceRight(it,b,op);
			if (b == null) b = e;	
			else b = op.apply(b, e);
		} else {
			b = null;
		}
		return b;
	}
		
	
	
	
	public static <E,B> B reduceRight(Iterable<E> iterator,BiFunction<B,E,B> op, B b0) {
		Iterator<E> it = iterator.iterator();
		B b = reduceRight(it,op,b0);
		return b;
	}
	
	private static <E,B> B reduceRight(Iterator<E> it,BiFunction<B,E,B> op, B b0){
		B b;
		if(it.hasNext()) {
			E e = it.next();
			b = reduceRight(it,op,b0);
			b = op.apply(b, e);
		} else {
			b = b0;
		}
		return b;
	}
	
	
	public static <E,B> B reduceM(Iterable<E> iterator, BiConsumer<B,E> op, Supplier<B> b0) {
		Iterator<E> it = iterator.iterator();
		B b = b0.get();
		while(it.hasNext()) {
			E e = it.next();
			op.accept(b, e);
		}
		return b;
	}
	
	public static <E,B> B reduceRightM(Iterable<E> iterator,BiConsumer<B,E> op, Supplier<B> b0) {
		Iterator<E> it = iterator.iterator();
		B b = reduceRightM(it,op,b0);
		return b;
	}
	
	private static <E,B> B reduceRightM(Iterator<E> it,BiConsumer<B,E> op,Supplier<B> b0){
		B b;
		if(it.hasNext()) {
			E e = it.next();
			b = reduceRightM(it,op,b0);
			op.accept(b, e);
		} else {
			b = b0.get();
		}
		return b;
	}
	
	public static <E> List<E> toList(Iterable<E> iterable) {
		return Iterables.reduceM(iterable,(ls,e) -> ls.add(e),()->new ArrayList<>());
	}
	
	public static <E> List<E> toListRight(Iterable<E> iterable) {
		return Iterables.reduceRightM(iterable,(ls,e) -> ls.add(e),()->new ArrayList<>());
	}
	
	public static <E> Set<E> toSet(Iterable<E> iterable) {
		return Iterables.reduceM(iterable,(st,e) -> st.add(e),()->new HashSet<>());
	}
	
	public static <E> SortedSet<E> toSortedSet(Iterable<E> iterable, Comparator<E> cmp) {
		return Iterables.reduceM(iterable,(st,e) -> st.add(e),()->new TreeSet<>(cmp));
	}
	
	public static <E> Optional<E> min(Iterable<E> iterable, Comparator<E> cmp){
		return Iterables.reduce(iterable,BinaryOperator.minBy(cmp));
	}
	
	public static <E> Optional<E> max(Iterable<E> iterable, Comparator<E> cmp){
		return Iterables.reduce(iterable,BinaryOperator.maxBy(cmp));
	}
	
	private static class CombineGroupingList<E,K> implements BiConsumer<Map<K,List<E>>,E>{
		public static <E,K> CombineGroupingList<E,K> of(Function<E, K> ky){
			return new CombineGroupingList<E,K>(ky);
		}
		private Function<E,K> key;		
		private CombineGroupingList(Function<E, K> ky) {
			this.key = ky;
		}
		@Override
		public void accept(Map<K, List<E>> m, E e) {
			K key = this.key.apply(e);
			if(m.containsKey(key)) {
				m.get(key).add(e);
			} else {
				List<E> ls = new ArrayList<>();
				ls.add(e);
				m.put(key, ls);
			}			
		}	
	}
	
	public static <E,K> Map<K,List<E>> groupingList(Iterable<E> iterator, Function<E,K> key){
		return Iterables.reduceM(iterator,CombineGroupingList.of(key),()->new HashMap<>());
	}
	
	public static <E,K> Map<K,List<E>> groupingListRight(Iterable<E> iterator, Function<E,K> key){
		return Iterables.reduceRightM(iterator,CombineGroupingList.of(key),()->new HashMap<>());
	}
	
	private static class CombineGroupingSet<E,K> implements BiConsumer<Map<K,Set<E>>,E>{
		public static <E,K> CombineGroupingSet<E,K> of(Function<E, K> ky){
			return new CombineGroupingSet<E,K>(ky);
		}
		private Function<E,K> key;		
		private CombineGroupingSet(Function<E, K> ky) {
			this.key = ky;
		}
		@Override
		public void accept(Map<K, Set<E>> m, E e) {
			K key = this.key.apply(e);
			if(m.containsKey(key)) {
				m.get(key).add(e);
			} else {
				Set<E> ls = new HashSet<>();
				ls.add(e);
				m.put(key, ls);
			}			
		}	
	}
	
	public static <E,K> Map<K,Set<E>> groupingSet(Iterable<E> iterator, Function<E,K> key){
		return Iterables.reduceM(iterator,CombineGroupingSet.of(key),()->new HashMap<>());
	}
	
	
	private static class CombineCounting<E,K> implements BiConsumer<Map<K,Integer>,E>{
		public static <E,K> CombineCounting<E,K> of(Function<E, K> ky){
			return new CombineCounting<E,K>(ky);
		}
		private Function<E,K> key;		
		private CombineCounting(Function<E, K> ky) {
			this.key = ky;
		}
		@Override
		public void accept(Map<K,Integer> m, E e) {
			K key = this.key.apply(e);
			if(m.containsKey(key)) {
				m.put(key,m.get(key)+1);
			} else {
				m.put(key,1);
			}			
		}	
	}
	
	public static <E,K> Map<K,Integer> counting(Iterable<E> iterator, Function<E,K> key){
		return Iterables.reduceM(iterator,CombineCounting.of(key),()->new HashMap<>());
	}
	
	
	private static class CombineAcum<E,K,R> implements BiConsumer<Map<K,R>,E>{
		public static <E,K,R> CombineAcum<E,K,R> of(Function<E, K> ky,BiFunction<R,E,R> f, R r0){
			return new CombineAcum<E,K,R>(ky,f,r0);
		}
		private Function<E,K> key;
		private BiFunction<R,E,R> f; 
		private R r0;
		private CombineAcum(Function<E, K> ky, BiFunction<R,E,R> f, R r0) {
			this.key = ky;
		}
		@Override
		public void accept(Map<K,R> m, E e) {
			K key = this.key.apply(e);
			if(m.containsKey(key)) {
				m.put(key,f.apply(m.get(key), e));
			} else {
				m.put(key,f.apply(r0, e));
			}			
		}	
	}
	
	public static <E,K,R> Map<K,R> counting(Iterable<E> iterator, Function<E,K> key, BiFunction<R,E,R> f, R r0){
		return Iterables.reduceM(iterator,CombineAcum.of(key,f,r0),()->new HashMap<>());
	}
	
	private static class CombineReduce<E,K> implements BiConsumer<Map<K,E>,E>{
		public static <E,K> CombineReduce<E,K> of(Function<E, K> ky,BinaryOperator<E> f){
			return new CombineReduce<E,K>(ky,f);
		}
		private Function<E,K> key;
		private BinaryOperator<E> f; 
		private CombineReduce(Function<E, K> ky, BinaryOperator<E> f) {
			this.key = ky;
		}
		@Override
		public void accept(Map<K,E> m, E e) {
			K key = this.key.apply(e);
			if(m.containsKey(key)) {
				m.put(key,f.apply(m.get(key), e));
			} else {
				m.put(key,e);
			}			
		}	
	}
	
	public static <E,K> Map<K,E> groupingReduce(Iterable<E> iterator, Function<E,K> key, BinaryOperator<E> f){
		return Iterables.reduceM(iterator,CombineReduce.of(key,f),()->new HashMap<>());
	}
	
	public static <E> Optional<E> findFirst(Iterable<E> iterator){
		Iterator<E> it = iterator.iterator();
		E b = null;
		while(it.hasNext()&& b==null){	   
		   b = it.next();	   
		}
		return Optional.ofNullable(b);
	}
	
	public static <E> Optional<E> findLast(Iterable<E> iterator){
		Iterator<E> it = iterator.iterator();
		E b = null;
		while(it.hasNext()){	   
		   b = it.next();	   
		}
		return Optional.ofNullable(b);
	}
	
	public static <E> Boolean all(Iterable<E> iterable, Predicate<E> p){
		Iterator<E> it = iterable.iterator();
		Boolean b = true;
		while(it.hasNext()&& b){	   
		   E e = it.next();	
		   b = p.test(e);
		}
		return b;
	}
	
	public static <E> Boolean any(Iterable<E> iterable, Predicate<E> p){
		Iterator<E> it = iterable.iterator();
		Boolean b = false;
		while(it.hasNext()&& !b){	   
		   E e = it.next();	
		   b = p.test(e);
		}
		return b;
	}
	
	public static <E> Boolean none(Iterable<E> iterable, Predicate<E> p){
		Iterator<E> it = iterable.iterator();
		Boolean b = false;
		while(it.hasNext()&& !b){	   
		   E e = it.next();	
		   b = p.test(e);
		}
		return !b;
	}
	
	public static <E,R> Optional<R> sequentialAlg(Iterable<E> iterable, Predicate<E> p, Function<E,R> r){
		Iterator<E> it = iterable.iterator();
		E b = null;
		while(it.hasNext()&& b==null){	   
		   E e = it.next();	
		   if(Predicate.not(p).test(e)) b = e;		   
		}
		return b==null?Optional.empty():Optional.of(r.apply(b));
	}
	
	public static <E,T,R> Optional<R> sequentialAlg(T t0, Predicate<T> p, UnaryOperator<T> next, Function<T,R> r){
		T t = t0;
		T b = null;
		while(p.test(t) && b==null){	   
		   t = next.apply(t);
		   if(Predicate.not(p).test(t)) b = t;		   
		}
		return b==null?Optional.empty():Optional.of(r.apply(t));
	}
	
	public static <E> String joining(Iterable<String> iterable, String sp, String pf, String sf){
		Iterator<String> it = iterable.iterator();
		String b = pf;
		Boolean esPrimero = true;
		while(it.hasNext()){	   
		   String e = it.next();	
		   if(esPrimero) {
			   esPrimero = false;
			   b = b + e;
		   }
		   else 
			   b = b+sp+e;
		}
		return b+sf;
	}
	
	
	/**
	 * @param iterator Un iterador
	 * @return Una vista del mismo de tipo 1
	 */
	public static <T> View1<Iterator<T>,T> view(Iterator<T> iterator) {
		Preconditions.checkArgument(iterator.hasNext(),"El iterador est� vac�o");
		T e = iterator.next();
		return View1.of(e,iterator);
	}
	
	
}
