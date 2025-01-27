package us.lsi.common;

public class Preconditions {

	/**
	 * Checks that the boolean is true. Use for validating arguments to methods.
	 * @param condition A condition
	 */
	public static void checkArgument(boolean condition){
		if(!condition){
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Checks that the boolean is true. Use for validating arguments to methods.
	 * @param message A message
	 * @param condition A condition
	 */
	public static void checkArgument(boolean condition, String message){
		if(!condition){
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Checks some state of the object, not dependent on the method arguments.  
	 * @param condition A condition
	 */
	public static void checkState(boolean condition){
		if(!condition){
			throw new IllegalArgumentException();
		}
	}
	/**
	 * Checks some state of the object, not dependent on the method arguments. 
	 * @param message Mensaje a imprimir
	 * @param condition A condition
	 */
	public static void checkState(boolean condition, String message){
		if(!condition){
			throw new IllegalArgumentException(message);
		}
	}
	
	/**
	 * Checks that the value is not null. 
	 * Returns the value directly, so you can use checkNotNull(value) inline.
	 * @param <T> El tipo del elemento	
	 * @param reference Parámetro a comprobar
	 * @return El parámetro a comprobar
	 */
	public static <T> T checkNotNull(T reference){
		if(reference == null){
			throw new NullPointerException(String.format("Es nulo %s", reference));
		}
		return reference;
	}
	
	public static <T> T checkNotNull(T reference, String mensaje){
		if(reference == null){
			throw new NullPointerException(mensaje);
		}
		return reference;
	}
		
	/**
	 * Checks that index is a valid element index into a list, string, or array with the specified size. 
	 * An element index may range from 0 inclusive to size exclusive. 
	 * You don't pass the list, string, or array directly; you just pass its size. 
	 * @param index Un índice 
	 * @param size El tamaño de la lista
	 * @return Index El índice del elemento
	 */
	public static int checkElementIndex(int index, int size){
		if(!(index>=0 && index<size)){
			throw new IndexOutOfBoundsException(String.format("Index = %d, size %d", index,size));
		}
		return index;
	}
	
	/**
	 * Checks that index is a valid position index into a list, string, or array with the specified size. 
	 * A position index may range from 0 inclusive to size inclusive. 
	 * You don't pass the list, string, or array directly; you just pass its size. Returns index.
	 * @param index El índice del elemento
	 * @param size El tamaño de la lista
	 * @return Index El índice del elemento
	 */
	public static int checkPositionIndex(int index, int size){
		if(!(index>=0 && index<=size)){
			throw new IndexOutOfBoundsException(String.format("Index = %d, size %d", index,size));
		}
		return index;
	}
	
	
}
