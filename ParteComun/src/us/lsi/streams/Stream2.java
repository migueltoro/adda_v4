package us.lsi.streams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.SortedMap;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.TreeMap;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import us.lsi.common.Enumerate;
import us.lsi.common.IntPair;
import us.lsi.common.IntQuartet;
import us.lsi.common.IntTrio;
import us.lsi.common.Multiset;
import us.lsi.common.MutableType;
import us.lsi.common.Pair;
import us.lsi.common.Preconditions;
import us.lsi.common.Set2;
import us.lsi.math.Math2;

public class Stream2 {
	
	public static void writeStream(Stream<String> s, String file) {
		Iterable<String> it = ()->s.iterator();
		try {
			Files.write(Paths.get(file),it);
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}
	
	public static <T> Stream<T> of(Spliterator<T> iterator) {
        return StreamSupport.stream(iterator, true);
    }
	
	public static <E> Stream<E> of(Iterable<E> iterable) {
	    return StreamSupport.stream(
	        Spliterators.spliteratorUnknownSize(
	            iterable.iterator(),
	            Spliterator.ORDERED
	        ),
	        false
	    );
	}
	
	public static <E> Stream<E> ofIterator(Iterator<E> iterator) {
	    return StreamSupport.stream(
	        Spliterators.spliteratorUnknownSize(
	            iterator,
	            Spliterator.ORDERED
	        ),
	        false
	    );
	}
	
	public static <E> Optional<E> findLast(Stream<E> stream){
		return stream.reduce((first,second)->second);
	}
	
	public static <E> Boolean allEquals(Stream<E> st) {
		MutableType<E> first = MutableType.of(null);
		return st.peek(e->{if(first.value()==null)first.setValue(e);})
				.allMatch(e->e.equals(first.value()));
	}
	
	public static <E> Boolean allDifferents(Stream<E> st) {
		MutableType<Integer> n = MutableType.of(0);
		Set<E> s = new HashSet<>();
		st.forEach(e->{n.setValue(n.value()+1); s.add(e);});
		return n.value().equals(s.size());
	}
	
	/**
	 * @param leftStream Un stream 
	 * @param rightStream Un stream 
	 * @param <L> El tipo de los elementos de la secuencia
	 * @param <R> El tipo de los elementos de la secuencia
	 * @param <T> El tipo de los elementos de la secuencia resultante
	 * @param combiner Fuci�n de combinaci�n
	 * @return Un stream formado por los elementos obtenidos combinando 
	 * uno a uno los elementos de las secuencias de entrada
	 */	
	public static <L, R, T> Stream<T> zip(Stream<L> leftStream, Stream<R> rightStream, BiFunction<L, R, T> combiner) {
		Spliterator<L> lefts = leftStream.spliterator();
		Spliterator<R> rights = rightStream.spliterator();
		return StreamSupport.stream(Spliterators2.zip(lefts, rights, combiner), leftStream.isParallel() || rightStream.isParallel());
	}
	
	/**
	 * @param stream Un String
	 * @param start Primer indice de la enumrracion
	 * @param <E> El tipo de los elementos de la secuencia
	 * @return Un stream formado por los pares de elementos 
	 * formados por un elemento y el entero que indica su posici�n
	 */
	public static <E> Stream<Enumerate<E>> enumerate(Stream<E> stream, Integer start) {
		Stream<Integer> st = Stream.iterate(start, e -> e + 1);
		return zip(stream, st, (e, n) -> Enumerate.of(n, e));
	}
	
	/**
	 * @param stream Un String
	 * @param <E> El tipo de los elementos de la secuencia
	 * @return Un stream formado por los pares de elementos 
	 * formados por un elemento y el entero que indica su posici�n
	 */

	public static <E> Stream<Enumerate<E>> enumerate(Stream<E> stream) {
		return enumerate(stream, 0);
	}
	
	/**
	 * @param <T> Tipo de los elementos del primer stream
	 * @param <U> Tipo de los elementos del segundo stream
	 * @param s1 Un Stream
	 * @param s2 Un Segundo Stream
	 * @return El resultado de operar los pares posibles de s1 y s2 con la bifunci�n f
	 */
	public static <T, U> Stream<Pair<T,U>> cartesianProduct(Stream<T> s1, Stream<U> s2) {
		List<U> ls = s2.collect(Collectors.toList());
		return s1.flatMap(x->ls.stream().map(y->Pair.of(x,y)));
	}
	
	public static <E> Stream<Pair<E,E>> cartesianProduct(Stream<E> s1) {
		List<E> ls = s1.collect(Collectors.toList());
		return ls.stream().flatMap(x->ls.stream().map(y->Pair.of(x,y)));
	}

	/**
	 * @param <T> Tipo de los elementos del primer stream
	 * @param <U> Tipo de los elementos del segundo stream
	 * @param <R> Tipo de los elementos del stream resultado
	 * @param s1 Una colecci�n
	 * @param s2 Una segunda colecci�n
	 * @param f Una Bifunction que opera un elemento del primer stream con un del segundo para 
	 * dar un resultado
	 * @return El resultado de operar los pares posibles de s1 y s2 con la bifunci�n f
	 */
	public static <T, U> Stream<Pair<T,U>> cartesianProduct(Collection<T> s1, Collection<U> s2) {
		return s1.stream().flatMap(x->s2.stream().map(y->Pair.of(x,y)));
	}

	public static <T> Stream<Pair<T,T>> cartesianProduct(Collection<T> s1) {
		return cartesianProduct(s1,s1);
	}
	
	/**
	 * @param sm Un String
	 * @param <E> El tipo de los elementos de la secuencia
	 * @return Un stream formado por los pares de elementos formados por un elemento y el siguiente en sm
	 */
	public static <E> Stream<Pair<E,E>> consecutivePairs(Stream<E> sm) {
		MutableType<E> rf = MutableType.of(null);
		Stream<Pair<E,E>> r = sm.map(e->Pair.of(rf.setValue(e), e)).filter(p->p.first()!=null);
		return r;
	}
	
	public static Stream<IntPair> allPairs(Integer n, Integer m){
		return allPairs(0,n,0,m);
	}
			
	public static Stream<IntPair> allPairs(Integer n1, Integer n2, Integer m1, Integer m2){
		return IntStream.range(n1, n2).boxed()
				.flatMap(x->IntStream.range(m1, m2).boxed().map(y->IntPair.of(x,y)));
	}
	
	public static Stream<IntTrio> allTrios(Integer n1, Integer n2, Integer m1, Integer m2, Integer r1, Integer r2) {
		return IntStream.range(n1, n2).boxed()
				.flatMap(i -> IntStream.range(m1, m2).boxed()
						            .flatMap(j->IntStream.range(r1, r2).boxed().map(k -> IntTrio.of(i, j, k))));
	}
	
	public static Stream<IntTrio> allTrios(Integer n, Integer m, Integer r) {
		return allTrios(0,n,0,m,0,r);
	}
	
	public static Stream<IntQuartet> allQuartets(Integer n, Integer m, Integer r, Integer s) {
		return allQuartets(0, n, 0, m, 0, r,0, s);
	}
	
	public static Stream<IntQuartet> allQuartets(Integer n1, Integer n2, Integer m1, Integer m2, Integer r1, Integer r2,
			Integer s1, Integer s2) {
		return IntStream.range(n1, n2).boxed()
				.flatMap(i -> IntStream.range(m1, m2).boxed()
						.flatMap(j -> IntStream.range(r1, r2).boxed()
								.flatMap(k -> IntStream.range(s1, s2).boxed().map(s -> IntQuartet.of(i, j, k, s)))));
	}
	
	
	
	/**
	 * @param s Una secuencia de streams
	 * @param <T> El tipo de los elementos de la secuencia
	 * @return Un stream fromado con los par�metros concatenados
	 */
	@SafeVarargs
	public static <T> Stream<T> concat(Stream<T>... s1) {
		return Arrays.stream(s1).flatMap(x->x);
	}
	
	

	/**
	 * @param s1 Un stream
	 * @param s2 Un segundo stream
	 * @param k1 Una funci�n que calcula una clave para los elementos de stream1
	 * @param k2 Una funci�n que calcula una clave para los elementos de stream2
	 * @param <T> El tipo de los elementos de la primera secuencia
	 * @param <U> El tipo de los elementos de la segunda secuencia
	 * @param <K> El tipo de los elementos de la clave
	 * @return Un stream resultado del joint de stream1 y stream2
	 */
	
	public static <T, U, K> Stream<Pair<T,U>> join(
			Stream<T> s1, 
			Stream<U> s2, 
			Function<T, K> k1,
			Function<U, K> k2) {
		Map<K, List<T>> m1 = s1.collect(Collectors2.groupingList(k1, x -> x));
		Map<K, List<U>> m2 = s2.collect(Collectors2.groupingList(k2, x -> x));
		Set<K> sk = Set2.intersection(m1.keySet(),m2.keySet());
		return sk.stream().flatMap(k->Stream2.cartesianProduct(m1.get(k),m2.get(k)));
	}
	
	
	
	/**
	 * @param s1 Una collection
	 * @param s2 Un segundo collection
	 * @param k1 Una funci�n que calcula una clave para los elementos de stream1
	 * @param k2 Una funci�n que calcula una clave para los elementos de stream2
	 * @param fr Una funci�n que calcula un nuevo valor a partir  de uno procedente de stream1 y otro del stream2
	 * @param <T> El tipo de los elementos de la primera secuencia
	 * @param <U> El tipo de los elementos de la segunda secuencia
	 * @param <K> El tipo de los elementos de la clave
	 * @param <R> El tipo de los elementos de la secuencia resultante
	 * @return Un stream resultado del joint de stream1 y stream2
	 */
	
	public static <T, U, K> Stream<Pair<T,U>> join(
			Collection<T> s1, 
			Collection<U> s2, 
			Function<T, K> k1,
			Function<U, K> k2) {
		Map<K, List<T>> m1 = s1.stream().collect(Collectors2.groupingList(k1, x -> x));
		Map<K, List<U>> m2 = s2.stream().collect(Collectors2.groupingList(k2, x -> x));
		Set<K> sk = Set2.intersection(m1.keySet(),m2.keySet());
		return sk.stream().flatMap(k->Stream2.cartesianProduct(m1.get(k),m2.get(k)));
	}
	
	/**
	 * @param a Un entero
	 * @param b Un entero
	 * @param c Un entero
	 * @return Un stream de enteros que forman una progresi�n 
	 * aritm�tica desde a hasta b con raz�n c sin incluir el valor b.
	 */
	public static IntStream range(Integer a, Integer b, Integer c){
		Preconditions.checkArgument(a==b ||(b-a)*c > 0);
		Integer hasta = (b-a)/c;
		if((b-a)%c == 0){
			hasta = hasta - 1;
		} 
		return IntStream.rangeClosed(0, hasta).map(x->a+c*x);
	}
	/**
	 * @param a Un entero
	 * @param b Un entero
	 * @param c Un entero
	 * @return Un stream de enteros que forman una progresi�n 
	 * aritm�tica desde a hasta b con raz�n c incluyendo el valor b se es posible
	 */
	public static IntStream rangeClosed(Integer a, Integer b, Integer c){		
		Preconditions.checkArgument(a==b || (b-a)*c > 0);
		Integer hasta = (b-a)/c;
		return IntStream.rangeClosed(0, hasta).map(x->a+c*x);
	}
	/**
	 * @param a Un entero
	 * @param b Un entero
	 * @param c Un entero
	 * @return Un stream de enteros que forman una progresi�n 
	 * aritm�tica desde a hasta b con raz�n c sin incluir el valor b.
	 */
	public static LongStream range(Long a, Long b, Long c){
		Preconditions.checkArgument((b-a)*c > 0);
		Long hasta = (b-a)/c;
		if((b-a)%c == 0){
			hasta = hasta - 1;
		} 
		return LongStream.rangeClosed(0, hasta).map(x->a+c*x);
	}
	/**
	 * @param a Un entero
	 * @param b Un entero
	 * @param c Un entero
	 * @return Un stream de enteros que forman una progresi�n 
	 * aritm�tica desde a hasta b con raz�n c incluyendo el valor b se es posible
	 */
	public static LongStream rangeClosed(Long a, Long b, Long c){
		Preconditions.checkArgument((b-a)*c > 0);
		Long hasta = (b-a)/c;
		return LongStream.rangeClosed(0, hasta).map(x->a+c*x);
	}
	
	/**
	 * @param <T> Tipo de los elementos del stream
	 * @param st Un stream
	 * @return Un elemento del stream escogido aleatoriamente
	 */
	public static <T> T elementRandom(Stream<T> st){
		List<T> ls = st.collect(Collectors.toList());
		Integer n = ls.size();
		return ls.get(Math2.getEnteroAleatorio(0, n));
	}

	
	public static <T> Multiset<T> toMultiSet(Stream<T> s){
		Multiset<T> m = Multiset.empty();
		s.forEach(x->m.add(x));
		return m;
	}
		
	/**
	 * @return Un stream formado por las l�neas escritas en la consola
	 */
	public static Stream<String> console() {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		return f.lines();
	}
	
	public static Stream<String> file(String file) {
		Stream<String> st = null;
		try {
			st = Files.lines(Path.of(file));
		} catch (IOException e) {
			System.err.println(e);
		}
		return st;
	}

	/**
	 * @param s Un String
	 * @param delim Unos delimitadores
	 * @return Un stream formado por los elementos resultantes de separar el String original
	 * por los delimitadores
	 */
	public static Stream<String> split(String s, String delim) {
		String[] r = s.split(delim);
		return Arrays.stream(r).<String> map(x -> x.trim())
				.filter(x-> x.length() > 0);
	}
	
	public static <E,B,R> R collect(Stream<E> flow, Collector<E,B,R> c) {
		B b = collect(flow.spliterator(),c);
		return c.finisher().apply(b);
	}
	
	private static <E,B,R> B collect(Spliterator<E> flow, Collector<E,B,R> c) {		
		B b;
		Spliterator<E> f2 = flow.trySplit();
		if(f2!=null) {
				B b1 = collect(f2,c);
				B b2 = collect(flow,c);
				b = c.combiner().apply(b1,b2);
		} else {
			b = c.supplier().get();
			Boolean r;
			do {
			   r = flow.tryAdvance(e->c.accumulator().accept(b,e));
			}while(r);
		}
		return b;
	}
	
	public static <E,K> Map<K,E> groupingReduce(Stream<E> st, Function<E,K> key, BinaryOperator<E> op){
		return st.collect(Collectors.groupingBy(key,
				Collectors.collectingAndThen(Collectors.reducing(op),e->e.get())));
	}
	
	public static <E,K,T> Map<K,T> groupingReduce(Stream<E> st, Function<E,K> key,  
			BinaryOperator<T> op, Function<E,T> value){
		return st.collect(Collectors.groupingBy(key,
				Collectors.mapping(value,
						Collectors.collectingAndThen(Collectors.reducing(op),e->e.get()))));
	}
	
	public static <E,K,T> SortedMap<K,T> groupingReduce(Stream<E> st, Function<E,K> key,  
			BinaryOperator<T> op, Function<E,T> value, Comparator<K> order){
		return st.collect(Collectors.groupingBy(key, 
				()->new TreeMap<K,T>(order),
				Collectors.mapping(value,
						Collectors.collectingAndThen(Collectors.reducing(op),e->e.get()))));
	}

}
