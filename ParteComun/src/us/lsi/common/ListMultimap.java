package us.lsi.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;


public class ListMultimap<K, V>  {
	
	public static <K,V> ListMultimap<K,V> empty(){
		return new ListMultimap<>();
	}
	
	public static <K,V> ListMultimap<K,V> of(Map<K,List<V>> map){
		return new ListMultimap<>(map);
	}

	private Map<K,List<V>> map;

	private ListMultimap() {
		super();
		this.map = new HashMap<K, List<V>>();
	}
	
	private ListMultimap(Map<K,List<V>> map) {
		super();
		this.map = map;
	}

	public Map<K, List<V>> asMap() {
		return map;
	}
	
	public Map<K, V> asMap(BinaryOperator<V> op) {
		Map<K, V> r = new HashMap<>();
		this.keySet().stream().forEach(k->r.put(k,this.get(k).stream().reduce(op).orElse(null)));
		return r;
	}

	public void clear() {
		map.clear();
	}

	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	public boolean containsEntry(Object key, Object value) {
		return map.containsKey(key) && map.get(key).contains(value);
	}
	
	public boolean containsValue(Object v) {
		return this.values().contains(v);
	}


	public boolean equals(Object object) {
		return map.equals(object);
	}


	public List<V> get(K key) {
		return map.get(key);
	}

	public int hashCode() {
		return map.hashCode();
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	public Set<K> keySet() {
		return map.keySet();
	}

	public boolean put(K key, V value) {
		if(!map.containsKey(key)) map.put(key, new ArrayList<>());
		return map.get(key).add(value);
	}

	public int size() {
		return map.size();
	}

	public String toString() {
		return map.toString();
	}
	
	public ListMultimap<K,V> copy() {
		Map<K,List<V>> r = new HashMap<>();
		this.keySet().forEach(k->r.put(k,List2.copy(this.get(k))));
		return ListMultimap.of(r);
	}
	
	public static <K,R> ListMultimap<K,R> add(ListMultimap<K,R> m1, ListMultimap<K,R> m2) {
		Map<K,List<R>> r = m1.copy().asMap();
		Map<K,List<R>> r2 = m1.asMap();
		r.keySet().forEach(k->r.put(k,List2.union(r.getOrDefault(k,List2.empty()),r2.getOrDefault(k,List2.empty()))));	
		return ListMultimap.of(r);
	}

	public Set<V> values() {
		return map.keySet()
				.stream()
				.flatMap(x->map.get(x).stream())
				.collect(Collectors.toSet());
	}
	
}
