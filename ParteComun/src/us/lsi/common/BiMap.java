package us.lsi.common;

import java.util.Map;

public class BiMap<K, V> {
	
	public static <K, V> BiMap<K, V> empty() {
		return new BiMap<K, V>();
	}


	private static <K, V> BiMap<K, V> of(Map<K, V> map, Map<V, K> inverseMap) {
		return new BiMap<K, V>(map, inverseMap);
	}


	private Map<K,V> map;
	private Map<V,K> inverseMap;
	
	
	private BiMap() {
		super();
		this.map = Map2.empty();
		this.inverseMap = Map2.empty();
	}


	private BiMap(Map<K, V> map, Map<V, K> inverseMap) {
		super();
		this.map = map;
		this.inverseMap = inverseMap;
	}
	
	public V put(K key, V value) {
		this.map.put(key,value);
		if(this.inverseMap.containsKey(value)) {
			throw new IllegalArgumentException(String.format("El valor %s ya estaba en el Bimap", value.toString()));
		}
		this.inverseMap.put(value, key);
		return value;
	}

	public V forcePut(K key, V value) {
		this.map.put(key,value);
		if(this.inverseMap.containsKey(value)) {
			this.map.remove(key);
			this.inverseMap.remove(value);
		}
		this.inverseMap.put(value, key);
		return value;
	}
	
	public BiMap<V,K> inverse(){
		return of(inverseMap,map);
	}
	
	public V get(K key) {
		return map.get(key);
	}
	
	public String toString() {
		return map.toString();
	}
	
	public Map<K,V> asMap(){
		return map;
	}
}
