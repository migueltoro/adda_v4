package us.lsi.common;

import java.util.*;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;



public class Map2 {
	
	public static <K,V> Map<K,V> put(Map<K,V> m, K key,V value){	
		Map<K,V> c = new HashMap<>(m);
		c.put(key, value);
		return c;
	}
	
	public static <K,V> Map<K,V> putIfAbsent(Map<K,V> m, K key,V value){	
		Map<K,V> c = new HashMap<>(m);
		c.putIfAbsent(key, value);
		return c;
	}
		
	public static <K,V> Entry<K,V> entry(K key,V value){	
		return new SimpleEntry<>(key,value);
	}
	public static <K,V> Map<K,V> empty(){
		return new HashMap<>();
	}
	public static <K,V> Map<K,V> of(Map<K,V> r){
		return new HashMap<>(r);
	}
	
	public static <K,V> Map<K,V> of(K key,V value){
		Map<K,V> m = new HashMap<>();
		m.put(key,value);
		return m;
	}
	
	public static <K,V> Map<K,V> of(K key1,V value1,K key2,V value2){
		Map<K,V> m = new HashMap<>();
		m.put(key1,value1);
		m.put(key2,value2);
		return m;
	}
	
	public static <K,V> Map<K,V> of(K key1,V value1,K key2,V value2,K key3,V value3){
		Map<K,V> m = new HashMap<>();
		m.put(key1,value1);
		m.put(key2,value2);
		m.put(key3,value3);
		return m;
	}
	
	/**
	 * @param <K> tipo de las claves
	 * @param <V> tipo de los valores
	 * @param m Un Map
	 * @return Un map inverso asumiendo que los elementos en todos los conjuntos imagen son distintos
	 */
	public static <K,V> Map<V,K> reverseHashMap(Map<K,V> m){
		return m.keySet().stream()
				.collect(Collectors.toMap(k->m.get(k),k->k));
	}
	
	/**
	 * @param <K> tipo de las claves
	 * @param <V> tipo de los valores
	 * @param <R> nuevo tipo de los valores
	 * @param f una funci�n
	 * @param m Un Map
	 * @return Un map cambiando los valores imagen aplicandole una funci�n
	 */
	public static <K,V,R> Map<K,R> of(Map<K,V> m,Function<V,R> f){
		return m.entrySet().stream()
				.map(x->Map2.entry(x.getKey(), f.apply(x.getValue())))
				.collect(Collectors.toMap(x->x.getKey(), x->x.getValue()));
	}
	
	/**
	 * @param <K> tipo de las claves
	 * @param <V> tipo de los valores
	 * @param entries Una serie de pares clave valor
	 * @return Un Map construido con esas claves
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> of(Entry<? extends K, ? extends V>... entries) {
        Map<K, V> result = new HashMap<>(entries.length);

        for (Entry<? extends K, ? extends V> entry : entries)
            if (entry.getValue() != null)
                result.put(entry.getKey(), entry.getValue());

        return result;
    }
	
	public static <K, V> Map<K, V> merge(Map<K,V> m1, Map<K,V> m2) {
		Map<K, V> r = new HashMap<>(m1);
		r.putAll(m2);
		return r;
	}
	
	public static <K, V> Map<K, V> merge(Map<K,V> m1, Map<K,V> m2, BinaryOperator<V> op) {
		BinaryOperator<V> nop = BinaryOperator2.of(op);
		Map<K, V> r = new HashMap<>(m1);
		Set<K> keys = Set2.union(m1.keySet(),m2.keySet());
		keys.stream().forEach(k->r.put(k,nop.apply(m1.getOrDefault(k, null), m2.getOrDefault(k, null))));
		return r;
	}
    
	/**
	 * @param <K> El tipo de las claves 
	 * @param <V> El tipo de los valores 
	 * @param f Una funci�n
	 * @return Un Map cuyo dominio y valores son los de la funci�n. Este Map s�lo tiene disponible el m�todo get.
	 */
	public static <K,V> Map<K, V> of(Function<K,V> f){
		MapOfFunction<K,V> r = new Map2.MapOfFunction<>(f);
		return r;
		
	}

	public static void main(String[] args) {
		
		
	}
	
	private static class MapOfFunction<K,V>  extends HashMap<K,V> implements Map<K,V>{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		Function<? super K, ? extends V> f;
		
		public MapOfFunction(Function<? super K, ? extends V> f) {
			super();
			this.f = f;
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public V get(Object key){
			V r = null;
			try {
				return this.computeIfAbsent((K) key, f);
			} catch (ClassCastException e) {
				r = null;
			}
			return r;
		}

		@Override
		public int size() {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean isEmpty() {
			return false;
		}

		@Override
		public boolean containsKey(Object key) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean containsValue(Object value) {
			throw new UnsupportedOperationException();
		}

		@Override
		public V put(K key, V value) {
			throw new UnsupportedOperationException();
		}

		@Override
		public V remove(Object key) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void putAll(Map<? extends K, ? extends V> m) {
			throw new UnsupportedOperationException();
			
		}

		@Override
		public void clear() {
			throw new UnsupportedOperationException();
			
		}

		@Override
		public Set<K> keySet() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Collection<V> values() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Set<java.util.Map.Entry<K, V>> entrySet() {
			throw new UnsupportedOperationException();
		}	
		
	}
} 
