package us.lsi.clases.trees;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import us.lsi.colors.GraphColors;
import us.lsi.common.List2;
import us.lsi.common.String2;
import us.lsi.tiposrecursivos.AVLTree;
import us.lsi.tiposrecursivos.BEmpty;
import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.BLeaf;
import us.lsi.tiposrecursivos.BTree;
import us.lsi.tiposrecursivos.BinaryTrees; 



public class BinaryTreesTest {
	
	public static <E> Integer size(BinaryTree<E> tree) {
		return switch (tree) {
		case BEmpty()  -> 0;
		case BLeaf(var lb)  -> 1;
		case BTree(var lb,var lt, var rt)  -> 1 + size(lt) + size(rt);
		};
	}

	
	public static <E> BinaryTree<E> copy(BinaryTree<E> tree) {
		return switch(tree) {
		case BEmpty() -> BinaryTree.empty(); 
		case BLeaf(var lb) -> BinaryTree.leaf(lb); 
		case BTree(var lb,var lt, var rt) -> BinaryTree.binary(lb, copy(lt), copy(rt));	
		};	
	}
		
	public static <E> BinaryTree<E> reverseCopy(BinaryTree<E> tree) {
		return switch(tree) {
		case BEmpty() -> BinaryTree.empty(); 
		case BLeaf(var lb) -> BinaryTree.leaf(lb); 
		case BTree(var lb,var lt, var rt)  -> BinaryTree.binary(lb, reverseCopy(rt), reverseCopy(lt));
		};	
	}
	
	public static <E> List<E> toListPostOrden(BinaryTree<E> tree) {
		return switch(tree) {
		case BEmpty() -> new ArrayList<>(); 
		case BLeaf(var lb) -> {
			List<E> r = new ArrayList<>();
			r.add(lb);
			yield r;
		}
		case BTree(var lb,var lt, var rt) -> { 
			List<E> r = toListPostOrden(lt);
			r.addAll(toListPostOrden(rt));
			r.add(lb);	
			yield r;
		}
		};
	}

	public static Integer sumIfPredicate(BinaryTree<Integer> tree, Predicate<Integer> predicate) {
		return switch (tree) {
		case BEmpty() -> 0;
		case BLeaf(var lb) -> predicate.test(lb) ? lb : 0;
		case BTree(var lb,var lt, var rt) -> (predicate.test(lb) ? lb: 0) + 
			sumIfPredicate(lt, predicate) + sumIfPredicate(rt, predicate);
		};
	}
	
	public static Integer sumIfPredicate2(BinaryTree<Integer> tree, Predicate<Integer> predicate) {
		return tree.byDepth()
		.map(t -> t.optionalLabel().orElse(0))
		.filter(predicate)
		.mapToInt(lb -> lb).sum();
	}

	public static Boolean sumaEtiquetas(BinaryTree<Integer> tree) {
		return tree.byDepth()
				.filter(e->e instanceof BTree<Integer> t && !t.left().isEmpty() && !t.right().isEmpty())
				.allMatch(e->e instanceof BTree<Integer> t && 
						t.label().equals(t.left().optionalLabel().get()+t.right().optionalLabel().get()));
	}
	
	/**
	 * Verifica si en un árbol binario existe una lista de caracteres que sea un camino 
	 * desde la raíz a una hoja no vacía.
	 *
	 * @param tree el árbol binario a verificar
	 * @param ls la lista de caracteres a buscar
	 * @return true si la lista de caracteres existe en el árbol, false en caso contrario
	 */
	
	public static Boolean existeLista(BinaryTree<Character> tree, List<Character> ls) {
		Integer n = ls.size();
		return switch (tree) {
		case BEmpty() when ls.isEmpty() -> true;
		case BLeaf(var lb) when !ls.isEmpty() -> ls.get(0).equals(lb);
		case BTree(var lb,BinaryTree<Character> lt, BinaryTree<Character> rt) when !ls.isEmpty() -> ls.get(0).equals(lb)
				&& (existeLista(lt, ls.subList(1, n)) || existeLista(rt, ls.subList(1, n)));
		};
	}
	
	public static record TT(Integer p, List<Integer> ls) {
		public static TT of(List<Integer> ls) {
			Integer p = ls.stream().reduce(1,(x,y)->x*y);
			return new TT(p,ls);
		}
	}
	
	public static TT maxCamino(BinaryTree<Integer> tree) {
		return maxCamino(tree,List.of());
	}
	
	/**
	 * Encuentra en un árbol binario el camino de la raiz a una hoja no vacía 
	 * con el producto máximo de sus etiquetas.
	 *
	 * @param tree el árbol binario a analizar
	 * @param camino actual desde la raiz a un nodo
	 * @return el camino con el producto máximo
	 */
	public static TT maxCamino(BinaryTree<Integer> tree, List<Integer> camino) {
		return switch(tree) {	
		case BEmpty() -> null;
		case BLeaf(var lb) -> TT.of(List2.addLast(camino,lb));
		case BTree(var lb,var lt, var rt) -> {
			camino = List2.addLast(camino,lb);
			TT left = maxCamino(lt,camino);
			TT right = maxCamino(rt,camino);
			yield Stream.of(left,right).filter(x->x!=null).max(Comparator.comparing(x->x.p())).orElse(null);
		}
		};
	}
	
	/**
	 * Método auxiliar para contar el número de nodos que cumplen con una condición específica en cada nivel 
	 * de un árbol binario.
	 * En este caso la condición es que el arbol tenga dos hijos no vacíos y 
	 * que la suma de las etiquetas de los hijos se igual a la etiqueta del nodo.
	 *
	 * @param tree el árbol binario a analizar
	 * @param ls la lista que almacena el número de nodos que cumplen con la condición en cada nivel
	 * @param level el nivel actual del árbol
	 */
	
	public static Boolean verify(BinaryTree<Integer> tree) {
		return switch (tree) {
		case BTree(var label,var left,var right) when left.isEmpty() || right.isEmpty() -> false;
		case BTree(var label,var left,var right) -> 
			label.equals(left.optionalLabel().get()+right.optionalLabel().get()) ;		
		default -> false;
        };
	}
	
	public static Boolean verify2(BinaryTree<Integer> tree) {
		return switch (tree) {
		case BTree(var label,BEmpty(),var right)  -> false;
		case BTree(var label,var left, BEmpty())  -> false;
		case BTree(Integer label,BLeaf(Integer lbl),BLeaf(Integer lbr)) -> label.equals(lbl+lbr);		
		case BTree(Integer label,BLeaf(Integer lbl),BTree(Integer lbr,var left,var right)) -> label.equals(lbl+lbr);
		case BTree(Integer label,BTree(Integer lbl,var left,var right),BLeaf(Integer lbr)) -> label.equals(lbl+lbr);
		case BTree(Integer label,BTree(Integer lbl,var leftl,var rightl),BTree(Integer lbr,var leftr,var rightr)) -> label.equals(lbl+lbr);
		default -> false;
        };
	}
	
	/**
	 * Cuenta el número de nodos que cumplen con una condición específica en cada nivel de un árbol binario.
	 * En este caso, la condición es que el árbol tenga dos hijos no vacíos y que la suma de las etiquetas 
	 * de los hijos sea igual a la etiqueta del nodo.
	 *
	 * @param tree el árbol binario a analizar
	 * @return una lista con el número de nodos que cumplen con la condición en cada nivel
	 */
	
	public static List<Integer> numVerifyI(BinaryTree<Integer> tree) {
		Integer heigth = tree.height();
		List<Integer> ls = List2.nCopies(0, heigth+1);
        tree.byLevel()
        	.filter(p->verify(p.tree()))
            .forEach(p->ls.set(p.level(),ls.get(p.level())+1));
        return  ls;
    }
	
	/**
	 * Método auxiliar para contar el número de nodos que cumplen con una condición específica en cada nivel de un árbol binario de forma recursiva.
	 * En este caso, la condición es que el árbol tenga dos hijos no vacíos y que la suma de las etiquetas de los hijos sea igual a la etiqueta del nodo.
	 *
	 * @param tree el árbol binario a analizar
	 */
	
	public static List<Integer> numVerifyR(BinaryTree<Integer> tree) {
		List<Integer> ls = new ArrayList<>();
		numVerifyR(tree, ls, 0);
		return ls;
    }
	
	/**
	 * Método auxiliar para contar el número de nodos que cumplen con una condición específica en cada nivel de un árbol binario de forma recursiva.
	 * En este caso, la condición es que el árbol tenga dos hijos no vacíos y que la suma de las etiquetas de los hijos sea igual a la etiqueta del nodo.
	 *
	 * @param tree el árbol binario a analizar
	 * @param ls la lista que almacena el número de nodos que cumplen con la condición en cada nivel
	 * @param level el nivel actual del árbol
	 */
	
	public static void numVerifyR(BinaryTree<Integer> tree, List<Integer> ls, Integer level) {
		if (ls.size() <= level) ls.add(0);
        switch(tree) {
        case BEmpty() -> {}
        case BLeaf(var lb) -> {}
        case BTree(Integer label, BinaryTree<Integer> left, BinaryTree<Integer> right)  -> {
        	if(verify(tree)) ls.set(level, ls.get(level) + 1);
        	numVerifyR(left, ls, level +1);
        	numVerifyR(right, ls, level +1);
        }
        }
    }
	
	public static void test1() {
		BinaryTree<Integer> t = BinaryTree.empty();
		List<Integer> ls = List.of(1,7,2,5,10,9,-1,14,67,67);
		Comparator<Integer> cmp = Comparator.naturalOrder();
		BinaryTree<Integer> tree = BinaryTrees.addOrdered(t,ls,cmp);
		System.out.println(tree.toString());
		System.out.println(tree.isOrdered(Comparator.naturalOrder()));
		Integer e = 10;
		BinaryTree<Integer> tree2 = BinaryTrees.removeOrdered(tree,e,Comparator.naturalOrder());
		System.out.println(tree2.toString());
		System.out.println(tree2.isOrdered(Comparator.naturalOrder()));
		BinaryTree<Integer> tree3 = BinaryTrees.removeOrdered(tree2,9,Comparator.naturalOrder());
		System.out.println(tree3.toString());
		System.out.println(tree3.isOrdered(Comparator.naturalOrder()));
		System.out.println(sumIfPredicate(tree3,x->x%2==0));
		BinaryTree<Integer> tree4 = copy(tree);
		System.out.println(tree.equals(tree4));
	}
	
	public static void test2() {
		BinaryTree<Integer> tree =  BinaryTree.parse("1(2(-1,-4(3,/_)),10(-5(7(/_,-2),4),-6))")
				.map(label->Integer.parseInt(label));
		SimpleDirectedGraph<BinaryTree<Integer>, DefaultEdge> graph = tree.toGraph();
		GraphColors.toDot(graph,"ficheros/tree4.gv",
				v->v.isEmpty()?"_":v.optionalLabel().get().toString(),
				e->"");
		System.out.println(tree);
		String2.toConsole("%s",maxCamino(tree));	
	}
	
	
	public static void test4() {
		String ex = "-43.7(2.1,56.7(-27.3(/_,2),78.2(3,4)))";
		String ex2 = "-43.7(2.1,5(/_,8.(3.,5.)))";
		BinaryTree<String> t7 = BinaryTree.parse(ex);	
		BinaryTree<String> t8 = BinaryTree.parse(ex2);	
		System.out.println(t7);
		System.out.println(t8);
		System.out.println(BinaryTrees.equilibrateType(t7));
		System.out.println(BinaryTrees.equilibrateType(t8));
		BinaryTree<String> t9 = t7.equilibrate();
		GraphColors.toDot(t9,"ficheros/binary_tree_9.gv");
		BinaryTree<String> t10 = t8.equilibrate();
		GraphColors.toDot(t10,"ficheros/binary_tree_10.gv");
		System.out.println(t9);
		System.out.println(t10);
		BinaryTree<Double> t11 =  t7.map(e->Double.parseDouble(e));
		System.out.println(t11);
	}
	
	public static void test5() {
		String ex = "4(2(1(0,_),3),7(5(_,6),2(9(8,_),11(_,12))))";
		BinaryTree<Integer> t0 = BinaryTree.parse(ex, e->Integer.parseInt(e));
		System.out.println(BinaryTrees.equilibrateType(t0));
		System.out.println(BinaryTrees.isOrdered(t0,Comparator.naturalOrder()));
		BinaryTree<Integer> t1 = BinaryTrees.addOrdered(t0,13,Comparator.naturalOrder());
		System.out.println("t1 = "+t1);
		System.out.println(BinaryTrees.equilibrateType(t1));
		System.out.println(BinaryTrees.isOrdered(t1,Comparator.naturalOrder()));
		GraphColors.toDot(t1,"ficheros/t1.gv");
		BinaryTree<Integer> t2 = switch(t1) {
		case BEmpty<Integer> t -> t;
		case BLeaf<Integer> t  -> t;
		case BTree<Integer> t -> BinaryTree.binary(t.label(),t.left(),t.right().equilibrate());
		};
		System.out.println("t2 = "+t2);
		System.out.println(BinaryTrees.equilibrateType(t2));
		System.out.println(BinaryTrees.isOrdered(t2,Comparator.naturalOrder()));
		GraphColors.toDot(t2,"ficheros/t2.gv");
		String t4 = "10(_,11(_,12(_,13)))";
		BinaryTree<Integer> t42 = BinaryTree.parse(t4, e->Integer.parseInt(e));
		BinaryTree<Integer> t43 = t42.equilibrate();
		System.out.println("t43 = "+t43);
		GraphColors.toDot(t43,"ficheros/t43.gv");
	}
	
	public static void test6() {
		BinaryTree<Integer> tree = BinaryTree.empty();
		for (int i = 0; i < 50 ; i++) {
			tree = BinaryTrees.addOrdered(tree, i, Comparator.naturalOrder());
//			System.out.println(tree.tree().height()+" == "+tree.tree());
		}	
		tree = BinaryTrees.equilibrate(tree);
		GraphColors.toDot(tree,"ficheros/avl_tree.gv");
	}
	
	public static void test7() {
		String ex = "4(2(1(0,/_),3),2(0(/_,6),2(9(8,/_),11(/_,12))))";
		BinaryTree<Integer> t0 = BinaryTree.parse(ex, e->Integer.parseInt(e));
		System.out.println(t0);
		System.out.println(numVerifyI(t0));
		System.out.println(t0.height());
		GraphColors.toDot(t0,"ficheros/t0.gv");
    }
	
	
	public static void testAVLTree1() {
		AVLTree<Integer> tree = AVLTree.of();
		for (int i = 0; i < 500 ; i++) {
			tree.add(i);
		}	
		for (int i = 0; i < 50 ; i++) {
			tree.remove(i);
		}
		for (int i = 600; i > 450 ; i--) {
			tree.remove(i);
		}	
		String2.toConsole(String.format("%d,%d,%d", tree.firstLabel().get(),tree.lastLabel().get(),tree.height()));
		String2.toConsole(String.format("%s",tree.toString()));
		GraphColors.toDot(tree.tree(),"ficheros/avl_tree.gv");
	}
	
	public static void testAVLTree2() {
		AVLTree<Integer> tree = AVLTree.of();
		for (int i = 0; i < 50 ; i++) {
			tree.add(i);
		}
		tree.remove(30);
		GraphColors.toDot(tree.tree(),"ficheros/avl_tree.gv");
	}

	public static void main(String[] args) {
		test7();
    }
	
}
