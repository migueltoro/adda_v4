package us.lsi.tiposrecursivos;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author migueltoro
 *
 * @param <E> Tipo de los elementos del �rbol
 * 
 */
public interface AVLTree<E> {
	
	
	/**
	 * @param <E> Tipo de los elementos del &aacute;rbol
	 * @return Un &aacute;rbol binario vac&iacute;o cuyos elementos se ordenar&aacute;n mediante el orden natural de E
	 */
	public static <E extends Comparable<? super E>> AVLTree<E> of() {
		return new AVLTreeI<E>(BinaryTree.empty(), Comparator.naturalOrder());
	}
	
	/**
	 * @param <E> Tipo de los elementos del &aacute;rbol
	 * @param comparator Un orden
	 * @return Un &aacute;rbol binario vac&iacute;o cuyos elementos se ordenar&aacute;n mediante comparator
	 */
	public static <E> AVLTree<E> of(Comparator<E> comparator) {
		return new AVLTreeI<E>(BinaryTree.empty(), comparator);
	}
	

	/**
	 * @return Si est&aacute; vac&iacute;o el &aacute;rbol AVL
	 */
	boolean isEmpty();

	/**
	 * @param e Un elemento
	 * @return Si contiene al elemento
	 */
	boolean contains(E e);
		
	
	/**
	 * @return El elemento m&aacute;s grande del &aacute;rbol
	 */
	Optional<E> lastLabel();
	
	/**
	 * @return El elemento m&aacute;s pequeño del &aacute;rbol
	 */
	Optional<E> firstLabel();
	
	/**
	 * @return El n&uacute;mero de elementos
	 */
	int size();
	
	/**
	 * @return La altura del arbol
	 */
	int height();

	/**
	 * @post El elemento est&aacute; contenido en el &aacute;rbol
	 * @param element Un elemento
	 * @return Verdadero si el elemento no estaba y se ha incluido
	 */
	boolean add(E element);

	/**
	 * @post Todos los elementos est&aacute;n contenidos en el &aacute;rbol
	 * @param elements Una colecci&oacute;n de elementos
	 * @return Si el &aacute;rbol ha cambiado al a�adir los elementos
	 */
	boolean add(Stream<E> elements);

	/**
	 * @post Todos los elementos est&aacute;n contenidos en el &aacute;rbol
	 * @param elements Una colecci&oacute;n de elementos
	 * @return Si el &aacute;rbol ha cambiado al a�adir los elementos
	 */
	boolean add(Collection<E> elements);

	/**
	 * @post Todos los elementos est&aacute;n contenidos en el &aacute;rbol
	 * @param elements Una serie de elementos
	 * @return Si el &aacute;rbol ha cambiado al a�adir los elementos
	 */
	boolean add(@SuppressWarnings("unchecked") E... elements);

	/**
	 * @post El elemento no est&aacute; contenidos en el &aacute;rbol
	 * @param element Un elemento
	 * @return Si el &aacute;rbol ha cambiado al eliminar el elemento
	 */
	boolean remove(E element);

	/**
	 * @post Los elementos no est&aacute;n  en el &aacute;rbol
	 * @param elements Un stream de elementos
	 * @return Si el &aacute;rbol ha cambiado al eliminar los elementos
	 */
	boolean remove(Stream<E> elements);

	/**
	 * @post Los elementos no est&aacute;n  en el &aacute;rbol
	 * @param elements Una colecci&oacute;n de elementos
	 * @return Si el &aacute;rbol ha cambiado al eliminar los elementos
	 */
	boolean remove(Collection<E> elements);

	/**
	 * @post Los elementos no est&aacute;n  en el &aacute;rbol
	 * @param elements Una serie de elementos
	 * @return Si el &aacute;rbol ha cambiado al eliminar los elementos
	 */
	boolean remove(@SuppressWarnings("unchecked") E... elements);

	/**
	 * @return Una copia del &aacute;rbol
	 */
	AVLTree<E> copy();
	
	BinaryTree<E> tree();
}