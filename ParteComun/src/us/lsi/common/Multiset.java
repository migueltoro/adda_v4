package us.lsi.common;


import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import us.lsi.streams.Stream2;

public class Multiset<E>  {

	/**
	 * @param <E> Tipo de los elementos del Multiset
	 * @return Un Multiset vac�o
	 */
	public static <E> Multiset<E> empty() {
		return new Multiset<E>();
	}
	
	
	/**
	 * @param m Un multiset
	 * @return Una copia
	 */
	public static <E> Multiset<E> copy(Multiset<E> m) {
		return new Multiset<E>(m.map,m.size);
	}

	/**
	 * @param <E> Tipo de los elementos del Multiset
	 * @param it Un iterable
	 * @return Un Multiset construido a partir del iterable
	 */
	public static <E> Multiset<E> of(Collection<E> it){
		return Stream2.toMultiSet(it.stream());
	}
	
	/**
	 * @param <E> Tipo de los elementos del Multiset
	 * @param m Un Map
	 * @return Un Multiset construido a partir del Map
	 */
	public static <E> Multiset<E> of(Map<E,Integer> m){
		return new Multiset<>(m,m.entrySet().stream().mapToInt(e->m.get(e)).sum());
	}
	
	/**
	 * @param <E> Tipo de los elementos del Multiset
	 * @param m Un Multiset
	 * @return Un Map construido a partir del Multiset
	 */
	public static <E> Map<E,Integer> asMap(Multiset<E> m){
		Map<E,Integer> r = new HashMap<>();
		m.elementSet().stream().forEach(x->r.put(x,m.count(x)));
		return r;
	}
	
	/**
	 * @param <E> Tipo de los elementos del Multiset
	 * @param entries Una secuencia de elementos
	 * @return Un Multiset construidom a partir de la secuencia de elementos
	 */
	public static <E> Multiset<E> of(@SuppressWarnings("unchecked") E... entries){
		Multiset<E> s = Multiset.empty();
		Arrays.asList(entries)
		.stream()
		.forEach(x->s.add(x)); 		
		return s;
	}

	
	private Map<E,Integer> map;
	private Integer size;

	private Multiset() {
		super();
		this.map = new HashMap<>();
		this.size = 0;
	}

	private Multiset(Map<E, Integer> map, Integer size) {
		super();
		this.map = new HashMap<>(map);
		this.size = size;
	}

	public void clear() {
		map.clear();
	}

	public boolean containsKey(Object arg0) {
		return map.containsKey(arg0);
	}

	public boolean containsValue(Object arg0) {
		return map.containsValue(arg0);
	}

	public Set<E> elementSet() {
		return map.keySet();
	}

	public boolean equals(Object m) {
		return map.equals(m);
	}

	public Integer count(Object e) {
		Integer r = 0;
		if(this.map.containsKey(e)) {
		     r = map.get(e);
		}
		return r;
	}

	public int hashCode() {
		return map.hashCode();
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}


	public Integer add(E e, Integer n) {
		Preconditions.checkArgument(n>=0,"No se pueden añadir cantidades negativas");
		Integer r = n;
		if(r>0) {
			r = map.put(e,map.getOrDefault(e,0) + n);
			this.size = this.size + n;
		}
		return r;
	}

	public Integer add(E e) {
		Integer r = map.getOrDefault(e,0) + 1;
		this.size = this.size + 1;
		return map.put(e, r);
	}	
	
	public static <E> Multiset<E> add(Multiset<E> m1, Multiset<E> m2) {
		Set<E> st = Set2.union(m1.elementSet(),m2.elementSet());
		Multiset<E> r = Multiset.empty();
		st.stream().forEach(x->r.add(x,m1.count(x)+m2.count(x)));
		return r;
	}
	
	public Multiset<E> add(Multiset<E> m2) {
		return Multiset.add(this,m2);
	}
	
	public static <E> Multiset<E> difference(Multiset<E> m1, Multiset<E> m2) {
		Set<E> st = Set2.union(m1.elementSet(),m2.elementSet());
		Multiset<E> r = Multiset.empty();
		st.stream().forEach(x->r.add(x,m1.count(x)-m2.count(x)>=0?m1.count(x)-m2.count(x):0));
		return r;
	}
	
	public Multiset<E> difference(Multiset<E> m2) {
		return Multiset.difference(this,m2);
	}
	
	public static <E> Multiset<E> union(Multiset<E> m1, Multiset<E> m2) {
		Set<E> st = Set2.union(m1.elementSet(),m2.elementSet());
		Multiset<E> r = Multiset.empty();
		st.stream().forEach(x->r.add(x,Math.max(m1.count(x),m2.count(x))));
		return r;
	}
	
	public Multiset<E> union(Multiset<E> m2) {
		return Multiset.union(this,m2);
	}
	
	public static <E> Multiset<E> intersection(Multiset<E> m1, Multiset<E> m2) {
		Set<E> st = Set2.union(m1.elementSet(),m2.elementSet());
		Multiset<E> r = Multiset.empty();
		st.stream().forEach(x->r.add(x,Math.min(m1.count(x),m2.count(x))));
		return r;
	}
	
	public Multiset<E> intersection(Multiset<E> m2) {
		return Multiset.union(this,m2);
	}
	
	public static <E> Multiset<E> symmetricDifference(Multiset<E> m1, Multiset<E> m2) {
		Set<E> st = Set2.union(m1.elementSet(),m2.elementSet());
		Multiset<E> r = Multiset.empty();
		st.stream().forEach(x->r.add(x,Math.abs(m1.count(x)-m2.count(x))));
		return r;
	}
	
	public Multiset<E> symmetricDifference(Multiset<E> m2) {
		return Multiset.symmetricDifference(this,m2);
	}
	
	public static <E> Boolean isIncluded(Multiset<E> m1, Multiset<E> m2) {
		Set<E> st = Set2.union(m1.elementSet(),m2.elementSet());
		return st.stream().allMatch(x->m1.count(x) <= m2.count(x));
	}
	
	public Boolean isIncluded(Multiset<E> m2) {
		return Multiset.isIncluded(this,m2);
	}

	public Integer remove(Object e) {
		return map.remove(e);
	}

	public List<Pair<E,Integer>> mostCommon(Integer n) {
		List<Pair<E,Integer>> r = this.map.keySet().stream()			
				.sorted(Comparator.comparing(k->this.map.get(k)).reversed())
				.limit(n)
				.map(k->Pair.of(k,this.map.get(k)))
				.toList();
		return r;
	}
	
	public Integer size() {
		return this.size;
	}

	public String toString() {
		return map
				.keySet()
				.stream()
				.filter(x->this.count(x)>0)
				.map(x->String.format("%s:%d",x,this.count(x)))
				.collect(Collectors.joining(",","{","}"));
	}

}
