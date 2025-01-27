package us.lsi.p2;

import java.util.List;
import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.BEmpty;
import us.lsi.tiposrecursivos.BLeaf;
import us.lsi.tiposrecursivos.BTree;

public class Ejemplo1 {

	/*
	 * PI3 - Ejemplo 4
	 * 
	 * Implemente una función booleana que, dados un árbol binario de caracteres y
	 * una lista de caracteres, determine si existe un camino en el árbol de la raíz
	 * a una hoja que sea igual a la lista.
	 * 
	 * Resolver de forma recursiva
	 */

	
	public static Boolean solucion_recursiva (BinaryTree<Character> tree,List<Character> list) {
		return recursivo(tree,list,0);	
	}

	
	public static Boolean recursivo(BinaryTree<Character> tree, List<Character> ls, int i) {
		Integer n = ls.size();
		return switch (tree) {
		case BEmpty() -> false;
		case BLeaf(var lb) -> n - i == 1 && ls.get(i).equals(lb);
		case BTree(var lb, var lt, var rt) -> n - i > 0 && ls.get(i).equals(lb)
				&& (recursivo(lt, ls, i+1) || recursivo(rt, ls, i+1));
		};
	}

}

