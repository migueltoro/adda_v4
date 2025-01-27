package us.lsi.p2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import us.lsi.tiposrecursivos.Tree;
import us.lsi.tiposrecursivos.TEmpty;
import us.lsi.tiposrecursivos.TLeaf;
import us.lsi.tiposrecursivos.TNary;

public class Ejemplo2 {
	/*
	PI3 - Ejemplo 5

	Diseñe un algoritmo que dado un árbol n-ario Tree<E> y un predicado sobre E
	devuelva una lista List<Boolean> de forma que el elemento i-ésimo de la lista será “True”
	si todos los elementos del nivel i cumplen el predicado.

	Resolver de forma recursiva
	*/
	

	public static <E> List<Boolean> solucion_recursiva (Tree<E> t, Predicate<E> p){
		List<Boolean> res = new ArrayList<>();
		recursivo (t,p,0,res);
		return res;
	}
	
	
	private static <E> void recursivo(Tree<E> tree, Predicate<E> pred, int nivel, List<Boolean> res) {
		if(res.size() <= nivel) res.add(true);
		switch (tree) {
		case TEmpty() -> {;}
		case TLeaf(var lb) -> {
			Boolean r = pred.test(lb) && res.get(nivel);
			res.set(nivel, r); 
		}
		case TNary(var lb, var chd) -> {
			Boolean r = pred.test(lb) && res.get(nivel);
			res.set(nivel, r);
			chd.forEach(tc -> recursivo(tc, pred, nivel + 1, res));
		}
	}
	
	}	
}

