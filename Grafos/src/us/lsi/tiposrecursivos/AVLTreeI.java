package us.lsi.tiposrecursivos;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;


import us.lsi.common.MutableType;
import us.lsi.common.Preconditions;


/**
 * Un &aacute;rbol binario ordenado y equilibrado
 * 
 * @author Miguel Toro
 *
 * @param <E> El tipo de los elementos del &aacute;rbol 
 * 
 * @inv La implementaci&oacute;n mantiene el invariante: tree() est� ordenado y equilibrado
 */
public class AVLTreeI<E> implements AVLTree<E>  {
	
	private static <E> AVLTreeI<E> of(BinaryTree<E> tree, Comparator<E> comparator) {
		Preconditions.checkNotNull(tree);
		return new AVLTreeI<E>(tree, comparator);
	}
	
	
	protected Comparator<E> comparator;
	protected BinaryTree<E> tree;
	
	
	protected AVLTreeI(BinaryTree<E> tree, Comparator<E> comparator) {
		super();
		this.comparator = comparator;
		this.tree = tree;
	}	

	@Override
	public boolean isEmpty() {
		return tree.isEmpty();
	}
	
	
	@Override
	public boolean contains(E e) {
		return BinaryTrees.containsLabelOrdered(this.tree, e, this.comparator);
	}
	
	/**
	 * @param tree Un arbol binario ordenado
	 * @param element Un elemento
	 * @param cmp Un orden
	 * @return Un &aacute;rbol binario con la etiqueta igual al element o vac&iacute;o
	 */
	
	@Override
	public Optional<E> firstLabel(){
		return BinaryTrees.minLabelOrdered(this.tree, this.comparator);
	}
	
	@Override
	public Optional<E> lastLabel(){
		return BinaryTrees.maxLabelOrdered(this.tree, this.comparator);
	}
	
	@Override
	public int size() {
		return tree.size();
	}

	@Override
	public int height() {
		return tree.height();
	}
	

	@Override
	public boolean add(E element) {
		 BinaryTree<E> r = BinaryTrees.addOrdered(this.tree, element, this.comparator);
		 Boolean s = r != this.tree;
		 this.tree = r;
		 return s;
	}
	
	@Override
	public boolean add(Stream<E> elements) {
		final MutableType<Boolean> r = MutableType.of(false);
		elements.forEach(e->{Boolean s = this.add(e); r.setValue(r.value() || s);});
		return r.value();
	}
	
	
	@Override
	public boolean add(Collection<E> elements) {
		final MutableType<Boolean> r = MutableType.of(false);
		elements.stream().forEach(e->{Boolean s = this.add(e); r.setValue(r.value() || s);});
		return r.value();
	}
	
	
	@Override
	public boolean add(@SuppressWarnings("unchecked") E... elements) {
		final MutableType<Boolean> r = MutableType.of(false);
		Arrays.stream(elements).forEach(e->{Boolean s = this.add(e); r.setValue(r.value() || s);});
		return r.value();
	}
	
	@Override
	public boolean remove(E element) {
		BinaryTree<E> r = BinaryTrees.removeOrdered(this.tree, element, this.comparator);
		Boolean s = r != this.tree;
		this.tree = r;
		return s;
	}
	
	@Override
	public boolean remove(Stream<E> elements) {
		final MutableType<Boolean> r = MutableType.of(false);
		elements.forEach(e->{Boolean s = this.remove(e); r.setValue(r.value() || s);});
		return r.value();
	}
	
	@Override
	public boolean remove(Collection<E> elements) {
		final MutableType<Boolean> r = MutableType.of(false);
		elements.stream().forEach(e->{Boolean s = this.remove(e); r.setValue(r.value() || s);});
		return r.value();
	}

	
	@Override
	public boolean remove(@SuppressWarnings("unchecked") E... elements) {
		final MutableType<Boolean> r = MutableType.of(false);
		Arrays.stream(elements).forEach(e->{Boolean s = this.remove(e); r.setValue(r.value() || s);});
		return r.value();
	}


	/**
	 * @return Una copia del &aacute;rbol
	 */
	@Override
	public AVLTree<E> copy() {
		return of(tree.copy(), comparator);
	}

	@Override
	public String toString() {
		return tree.toString();
	}
	
	@Override
	public BinaryTree<E> tree() {
		return this.tree;
	}
	
	public static void test3() {
		String s = "4(2(1(0,/_),3),7(5(/_,6),10(9(8,/_),11(/_,12))))";
		BinaryTree<Integer> t = BinaryTree.parse(s, e->Integer.parseInt(e));
		System.out.println(t);
		AVLTree<Integer> tree = AVLTreeI.of(t,Comparator.naturalOrder());
		tree.add(13);
	}
	
	public static void main(String[] args) {
		test3();
	}

	

}
