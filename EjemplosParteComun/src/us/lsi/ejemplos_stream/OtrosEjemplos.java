package us.lsi.ejemplos_stream;


import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.*;
import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import us.lsi.common.List2;
import us.lsi.common.Set2;
import us.lsi.streams.Collectors2;
import us.lsi.streams.Stream2;


/**
 *
 * @author Miguel Toro
 */
public class OtrosEjemplos {

	private static <T> Consumer<T> imprimeEnConsola() {
		return x -> System.out.println(x);
	}

	private static IntConsumer imprimeEnConsolaInt() {
		return x -> System.out.println(x);
	}

	public static void ejemplo1() {

		Double r = DoubleStream.iterate(7.0, x -> x * x).filter(x -> x > 3000)
				.findFirst().getAsDouble();

		System.out.println("r= " + r);

	}

	public static void ejemplo2() {
		IntStream.range(23, 550).forEach(imprimeEnConsolaInt());
	}

	public static void ejemplo3() {
		Stream.concat(IntStream.range(50, 60).boxed(),
				IntStream.range(2, 20).boxed()).forEach(imprimeEnConsola());
	}
	
	public static void ejemplo4() {
		Stream2.consecutivePairs(IntStream.range(50, 60).boxed()).forEach(imprimeEnConsola());
	}

	public static void ejemplo5() {

		IntStream.range(0, 20).boxed().forEach(imprimeEnConsola());

		System.out.println("========");

		IntStream.range(10, 55).boxed().forEach(imprimeEnConsola());

		System.out.println("========");

		Stream2.join(IntStream.range(0, 20).boxed(),
				      IntStream.range(10, 55).boxed(),
					  x -> x % 5, 
					  x -> x % 5)
				.forEach(imprimeEnConsola());

	}

	public static void ejemplo6() {
		List<Long> s0 = List.of(1L, 2L, 3L, 4L, 5L);
		List<Long> s1 = List.of(4L, 10L, 9L, 29L);
		List<Long> s2 = List.of(5L, 15L, 20L, 39L);
		
		
		List<Long> r = List2.concat(s0,s1);
		Stream2.join(r.stream(),s2.stream(), x -> x, x -> x).forEach(imprimeEnConsola());		
	}

	public static void ejemplo7() {
		Set<Integer> s1 = Set2.of(1, 3, 5);
		Set<Integer> s2 = Set2.of(16, 13, 15);
		Stream2.cartesianProduct(s1.stream(),s2.stream()).forEach(imprimeEnConsola());
	}

	public static void ejemplo8() {
		Stream.iterate(3L, x -> 81L - x >= 0, x -> x * x)
			  .forEach(imprimeEnConsola());
	}

	public static void ejemplo9() {
		Stream2.join(
				Stream.of(1L, 2L, 3L, 4L, 9L),
				Stream.of(4L, 10L, 9L, 29L), 
				x -> x, 
				x -> x)
			   .forEach(imprimeEnConsola());
	}

	public static void ejemplo10() {
		Stream.<Integer>iterate(0, x -> x <= 1000, x -> x + 4)
				.forEach(imprimeEnConsola());
	}
	
	public static void ejemplo11() {
		Random r = new Random(System.nanoTime());
		IntStream.range(0, 1000).boxed().map(e->r.nextInt(20))
			.collect(Collectors2.groupingSize(x->x))
			.entrySet().stream()
			.forEach(imprimeEnConsola());
		IntStream.range(0, 1000).boxed().map(e->r.nextInt(20))
		.collect(Collectors2.groupingSizeDistinct(x->x,x->x))
		.entrySet().stream()
		.forEach(imprimeEnConsola());
	}
	
	public static <E> Set<E> union(Integer n, Predicate<Integer> pd, Function<Integer,Set<E>> f){
		return IntStream.range(0, n).boxed().filter(pd).map(f).reduce((s1,s2)->Set2.union(s1,s2)).get();
	}
	
	public static <E> Set<E> intersection(Integer n, Predicate<Integer> pd, Function<Integer,Set<E>> f){
		return IntStream.range(0, n).boxed().filter(pd).map(f).reduce((s1,s2)->Set2.intersection(s1,s2)).get();
	}
	
	public static Boolean allDifferents(Integer n, Predicate<Integer> pd, Function<Integer,Integer> f){
		Integer n1 = IntStream.range(0, n).boxed().filter(pd).map(f).collect(Collectors.toSet()).size();
		Integer n2 = (int)IntStream.range(0, n).boxed().filter(pd).count();
		return n1.equals(n2);
	}
	
	public static <E> Map<Integer,List<E>> grouping(Integer n, Function<Integer,Integer> key, Function<Integer,E> f){
		  return IntStream.range(0,n).boxed().collect(Collectors.groupingBy(key,
			 		Collectors.mapping(f,Collectors.toList()))); 
	}
	
	public static <E> Map<Integer,List<E>> grouping2(Integer n, Function<Integer,Integer> key, Function<Integer,E> f){
		  return IntStream.range(0,n).boxed().collect(Collectors2.groupingList(key, f)); 
	}
	
	public static <E> Map<E,Integer> frecuencias(Integer n, Function<Integer,E> f){
		  return IntStream.range(0,n).boxed().collect(Collectors.groupingBy(f,
			 		Collectors.collectingAndThen(Collectors.counting(),r->r.intValue()))); 
	}
	
	public static <E> Map<E,Integer> frecuencias2(Integer n, Function<Integer,E> f){
		  return IntStream.range(0,n).boxed().collect(Collectors.groupingBy(f,
			 		Collectors.collectingAndThen(Collectors.counting(),r->r.intValue()))); 
	}
	
	public static <E> Map<Integer, E> groupingReduce(Integer n, Function<Integer, Integer> key, Function<Integer, E> f,
			BinaryOperator<E> bo) {
		return IntStream.range(0, n).boxed().collect(Collectors.groupingBy(key,
				Collectors.collectingAndThen(Collectors.mapping(f, Collectors.reducing(bo)), r -> r.get())));
	}
	
	public static <E> Map<Integer, E> groupingReduce2(Integer n, Function<Integer, Integer> key, Function<Integer, E> f,
			BinaryOperator<E> bo) {
		return IntStream.range(0, n).boxed().collect(Collectors2.groupingReduce(key,f,bo));
	}
	
	
	public static <E> Boolean esPermutacion(Integer n, Function<Integer,E> f, Function<Integer,E> v){
		  return frecuencias(n,f).equals(frecuencias(n, v));
	}

	public static void main(String[] args) {
		ejemplo11();
	}
	
}
