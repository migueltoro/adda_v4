package us.lsi.common;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class SetMultimap<K, V>  {

		
	public static <K, V> SetMultimap<K, V> empty() {
		return new SetMultimap<K, V>();
	}
	
	public static <K, V> SetMultimap<K, V> of(Map<K, Set<V>> m) {
		return new SetMultimap<K, V>(m);
	}

	private Map<K,Set<V>> map;

	private SetMultimap(){
		this.map = new HashMap<>();
	}
		
	private SetMultimap(Map<K,Set<V>> m){
		this.map = m;;
	}

	public Map<K, Set<V>> asMap() {
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


	public Set<V> get(K key) {
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
		if(!map.containsKey(key)) map.put(key, new HashSet<>());
		return map.get(key).add(value);
	}

	public int size() {
		return map.size();
	}

	public String toString() {
		return map.toString();
	}

	public Set<V> values() {
		return map.keySet()
				.stream()
				.flatMap(x->map.get(x).stream())
				.collect(Collectors.toSet());
	}
	
	public SetMultimap<K,V> copy() {
		Map<K,Set<V>> r = new HashMap<>();
		this.keySet().forEach(k->r.put(k,Set2.copy(this.get(k))));
		return SetMultimap.of(r);
	}
	
	public static <K,V> SetMultimap<K,V> add(SetMultimap<K,V> m1, SetMultimap<K,V> m2) {
		Map<K,Set<V>> r = m1.copy().asMap();
		Map<K,Set<V>> r2 = m1.asMap();
		r.keySet().forEach(k->r.put(k,Set2.union(r.getOrDefault(k,Set2.empty()),r2.getOrDefault(k,Set2.empty()))));	
		return SetMultimap.of(r);
	}
	
	public SetMultimap<K,V> add(SetMultimap<K,V> m2) {
		return SetMultimap.add(this, m2);
	}
	
}
