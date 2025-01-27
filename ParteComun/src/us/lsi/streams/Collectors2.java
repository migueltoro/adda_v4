package us.lsi.streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import us.lsi.common.IntegerSet;
import us.lsi.common.List2;
import us.lsi.common.ListMultimap;
import us.lsi.common.Map2;
import us.lsi.common.Multiset;
import us.lsi.common.MutableType;
import us.lsi.common.Set2;
import us.lsi.common.SetMultimap;

public class Collectors2 {
	
	public static <E,B,R> Collector<E,MutableType<B>,R> of(
			Supplier<B> supplier,
			BiFunction<B,E,B> consumer,
			BinaryOperator<B> combiner,
			Function<B,R> finisher){
		return Collector.of(()->MutableType.of(supplier.get()),
				(b,e)->b.setValue(consumer.apply(b.value(),e)),
				(x,y)->MutableType.of(combiner.apply(x.value(),y.value())),
				x->finisher.apply(x.value()));
	}
	
	
	public static <E> Collector<E,Multiset<E>,Multiset<E>> toMultiset() {
		return Collector.of(
				()->Multiset.empty(), 
				(x,e)->x.add(e), 
				(x,y)->Multiset.add(x, y), 
				x->x);
	}
	
	public static <E> Collector<Integer,Set<Integer>,IntegerSet> toIntegerSet() {
		return Collector.of(
				()->Set2.<Integer>empty(), 
				(x,e)->x.add(e), 
				(x,y)->Set2.union(x,y), 
				x->IntegerSet.of(x));
	}
	
	public static <E> Collector<E,List<E>,List<E>> mergeSort(Comparator<? super E> cmp) {
		return Collector.of(
				()->new ArrayList<>(), 
				(x,e)->List2.insertOrdered(x,e,cmp), 
				(x,y)->List2.mergeOrdered(x,y,cmp), 
				x->x);
	}
	
	public static <E extends Comparable<? super E>> Collector<E,List<E>,List<E>> mergeSort() {
		return mergeSort(Comparator.naturalOrder());
	}
	
	public static <E,K,R> Collector<E,ListMultimap<K,R>,Map<K,List<R>>> groupingList(
			Function<E,K> key,
			Function<E,R> value){
		return groupingList(key,value,rs->rs);
	}
	
	public static <E,K,R,S> Collector<E,ListMultimap<K,R>,Map<K,S>> groupingList(
			Function<E,K> key,
			Function<E,R> value,
			Function<List<R>,S> rs) {
		return Collector.of(
				()->ListMultimap.<K,R>empty(), 
				(x,e)->x.put(key.apply(e),value.apply(e)), 
				(s1,s2)->ListMultimap.add(s1,s2), 
				s->Map2.of(s.asMap(),rs));
	}
	
	public static <E,K,R> Collector<E,SetMultimap<K,R>,Map<K,Set<R>>> groupingSet(
			Function<E,K> key,
			Function<E,R> value) {
		return groupingSet(key,value,rs->rs);
	}
	
	public static <E,K,R,S> Collector<E,SetMultimap<K,R>,Map<K,S>> groupingSet(
			Function<E,K> key,
			Function<E,R> value,
			Function<Set<R>,S> rs) {
		return Collector.of(
				()->SetMultimap.<K,R>empty(), 
				(x,e)->x.put(key.apply(e),value.apply(e)), 
				(s1,s2)->SetMultimap.add(s1,s2), 
				s->Map2.of(s.asMap(),rs));
	}
	
	public static <E,K,R> Collector<E,Map<K,R>,Map<K,R>> groupingReduce(
			Function<E,K> key,
			Function<E,R> value,
			BinaryOperator<R> op) {
		return Collector.of(
				()->new HashMap<>(), 
				(x,e)->{
					K k = key.apply(e);
					R v = value.apply(e);
					if(x.containsKey(k)) x.put(k,op.apply(x.get(k),v));
					else x.put(k,v);							
				},
				(s1,s2)->Map2.merge(s1,s2,op), 
				s->s);
	}
	
	
	public static <E,K> Collector<E,Map<K,Integer>,Map<K,Integer>> groupingSize(
			Function<E,K> key) {
		return Collector.of(
				()->new HashMap<>(), 
				(m,e)->m.put(key.apply(e),m.getOrDefault(e,0)+1), 
				(m1,m2)->Map2.merge(m1,m2,(x,y)->x+y), 
				s->s);
	}
	
	public static <E,K,R> Collector<E,SetMultimap<K,R>,Map<K,Integer>> groupingSizeDistinct(
			Function<E,K> key,
			Function<E,R> value) {
		return Collectors2.<E,K,R,Integer>groupingSet(key,value,s -> s.size());
	}
	
	public static <E> Collector<E,Map<E,Integer>,Multiset<E>> frequencies() {
		return Collector.<E,Map<E,Integer>,Multiset<E>>of(
				()->new HashMap<>(), 
				(m,e)->m.put(e,m.getOrDefault(e,0)+1), 
				(m1,m2)->Map2.merge(m1,m2,(x,y)->x+y), 
				m->Multiset.of(m));
	}
	
}
