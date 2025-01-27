package us.lsi.basictypes;

import java.util.List;

import us.lsi.common.List2;
import us.lsi.math.Math2;

public class HashTable<K, V> {
	
	

	public static <K, V> HashTable<K, V> empty() {
		return new HashTable<K, V>();
	}

	private int groupsNumber;
	private int size;
	private int capacityData;
	private AList<Integer> groups;
	private AList<EntryData<K,V>> data;
	private Integer firstFreeData = 0;
	
	private static double loadFactorReference = 0.75;
	
	private HashTable() {
		super();
		initialParameters(13);
	}
	
	private static Integer nextPrime(int prime) {
		return Math2.siguientePrimo(prime);
	}
	
	private void initialParameters(int groupsNumber){
		this.firstFreeData = 0;
		this.size = 0;
		this.groupsNumber = groupsNumber;
		this.capacityData = (int)(this.groupsNumber*loadFactorReference+1);
		this.groups = AList.of(this.groupsNumber);
		this.data = AList.of(this.capacityData);						
		for(int i = 0; i < groupsNumber; i++){
			groups.add(-1);
		}
		for(int i = 0; i < capacityData; i++){
			data.add(EntryData.create(i+1));
		}
		data.get(this.capacityData-1).next = -1;
	}
	
	private int group(K key){
		return key.hashCode()%this.groupsNumber;
	}
	
	private void rehash(){
		if((((double)this.size)/this.groupsNumber) >= 0.75) {
			AList<EntryData<K,V>> oldData = data;
			initialParameters(nextPrime(2*this.groupsNumber));
			for(int i=0;i<oldData.size();i++) {
				EntryData<K,V> e = oldData.get(i);
				if(e.key == null) continue;
				this.put(e.key, e.value);
			}
		}
	}
	
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	private EntryData<K,V> findEntry(K key){
		EntryData<K,V> r = null;
		int g = group(key);
		int next = groups.get(g);
		while(next >= 0) {
			r = data.get(next);
			if(r.key.equals(key)) break;
			next = r.next;
		}
		return r;
	}
	
	public V get(K key){
		V r = null;
		EntryData<K,V> e = findEntry(key);
		if(e!=null) r = e.value;
		return r;
	}
	
	
	public V put(K key, V value){
		rehash();
		EntryData<K,V> r = findEntry(key);
		if(r==null) {
			r = data.get(this.firstFreeData);
			int g = group(key);
			int oldfirstInGrup= groups.get(g);
			groups.set(g, this.firstFreeData);
			this.firstFreeData = r.next;
			r.next=oldfirstInGrup;
			r.key = key;
			this.size = this.size +1;
		}
		r.value = value;		
		return value;
	}
	
	
	private void freeEntryData(int group, int beforeIndex, int indexData) {
		EntryData<K,V> e = data.get(indexData);
		if(beforeIndex < 0) {			
			groups.set(group, e.next);			
		} else {
			data.get(beforeIndex).next = e.next;
		}
		e.next = this.firstFreeData;
		this.firstFreeData = indexData;
	}
	
	
	public V remove(K key){
		V r = null;
		EntryData<K,V> e = null;
		int g = group(key);
		int before = -1;
		int next = groups.get(g);
		while(next >= 0) {
			e = data.get(next);
			if(e.key.equals(key)) {
				r = e.value;
				e.key = null;
				e.value = null;
				this.size = this.size -1;
				freeEntryData(g,before,next);
				break;
			}
			before = next;
			next = e.next;
		}
		return r;
	}
	
	public List<EntryTable<K,V>> entryList(){
		List<EntryTable<K,V>> r = List2.empty();
		for(int i =0;i<this.capacityData;i++) {
			EntryData<K,V> e = data.get(i);
			if(e.key == null) continue;
			r.add(EntryTable.create(e.key, e.value));
		}
		return r;
	}
	
	public String toString(){
		boolean first = true;
		String r = "{";
		for(int i =0;i<this.capacityData;i++) {
			EntryData<K,V> e = data.get(i);
			if(e.key == null) continue;
			if(first) first = false;
			else r = r+",";
			r = r+String.format("(%s,%s)",e.key,e.value);
		}
		return r+"}";
	}
	
	public static class EntryData<K,V> {
		public static <K,V> EntryData<K,V> create(Integer next){
			return new EntryData<>(null,null,next);
		}
		public static <K,V> EntryData<K,V> create(K key, V value, Integer next){
			return new EntryData<>(key,value,next);
		}
		K key;
		V value;
		Integer next;
		public EntryData(K key, V value, Integer next) {
			super();
			this.key = key;
			this.value = value;
			this.next = next;
		}
		public K key() {
			return key;
		}
		public V value() {
			return value;
		}
		public Integer next() {
			return next;
		}
		public String toString(){
			return "("+key+","+value+","+next+")";
		}
	}
	
	public static class EntryTable<K,V> {
		public static <K,V> EntryTable<K,V> create(K key, V value){
			return new EntryTable<>(key,value);
		}
		K key;
		V value;
		public EntryTable(K key, V value) {
			super();
			this.key = key;
			this.value = value;
		}
		
		public K key() {
			return key;
		}

		public V value() {
			return value;
		}
		public String toString(){
			return "("+key+","+value+")";
		}
	}
}
