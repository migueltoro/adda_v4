package us.lsi.basictypes;


import java.lang.reflect.Array;
import java.util.Arrays;

import us.lsi.common.Preconditions;


/**
 * Una implementaci�n de un array de tama�o variable
 * 
 * @author Miguel Toro
 *
 * @param <E> Tipo de los elementos
 */
public class AList<E> {
	
	public static <E> AList<E> empty() {
		return new AList<E>();
	}

	public static <E> AList<E> of(int capacity) {
		return new AList<E>(capacity);
	}

	public static <E> AList<E> of(AList<E> a) {
		return new AList<E>(a);
	}

	public static <E> AList<E> of(E[] a) {
		return new AList<E>(a);
	}

	private int capacity;
	private int size;
	private E[] data;
	private final int INITIAL_CAPACITY = 10;
	
	private AList() {
		super();
		this.capacity = INITIAL_CAPACITY;
		this.size = 0;
		this.data = null;
	}
	
	private AList(int capacity) {
		super();
		this.capacity = capacity;
		this.size = 0;
		this.data = null;
	}
	
	private AList(AList<E> a) {
		super();
		this.capacity = a.capacity;
		this.size = a.size();
		this.data = Arrays.copyOf(a.data,a.capacity);
	}
	
	private AList(E[] a) {
		super();
		this.capacity = a.length;
		this.size = capacity;
		this.data = Arrays.copyOf(a, capacity);
	}

    private void grow(){
    	if(size==capacity){
    		E[] oldElements = data;
    		capacity = capacity*2;
    		data = Arrays.copyOf(oldElements, capacity);
    	}
    }
	
    public int size() {
		return size;
	}

    public boolean isEmpty(){
    	return size == 0;
    }
    
	public E get(int index) {
    	Preconditions.checkElementIndex(index, size);
		return data[index];
	}
    
	@SuppressWarnings("unchecked")
	public E set(int index, E e){	
		Preconditions.checkPositionIndex(index,this.size);
		if(this.data == null) this.data = (E[]) Array.newInstance(e.getClass(), capacity);
		if(index == this.size) {
			this.size = this.size +1;
			grow();
		}
		E r = get(index);
		data[index]= e;
		return r;
	}
	
	@SuppressWarnings("unchecked")
	public boolean add(E e) {
		if(this.data == null) this.data = (E[]) Array.newInstance(e.getClass(), capacity);
		grow();
		data[size] = e;
		size++;   	
		return true;
	}
	
	public void add(int index, E e) {
		Preconditions.checkPositionIndex(index, size);
		add(e);
		// size ya ha quedado aumentado
		for(int i = size-1; i > index; i--){
			data[i]= data[i-1];
		}
		data[index]=e;
	}
	
	public E remove(int index) {
		Preconditions.checkElementIndex(index, size);
		E e = data[index];
		for(int i = index; i < size-1; i++){
			data[i]= data[i+1];
		}
		size --;
		return e;
	}
	
	public E[] toArray(){
		E[] r = Arrays.copyOf(this.data, size);
		return r;
	}
	
	public String toString(){
		String s = "{";
		boolean prim = true;
		for(int i=0; i<size; i++){
			if(prim){
				prim = false;
				s = s+data[i];
			}else{
				s = s+","+data[i];
			}
		}
		s = s+"}";
		return s;
	}
}
