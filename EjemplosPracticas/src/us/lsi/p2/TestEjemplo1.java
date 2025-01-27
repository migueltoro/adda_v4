package us.lsi.p2;

import java.util.ArrayList;
import java.util.List;

import us.lsi.common.Files2;
import us.lsi.common.Pair;
import us.lsi.common.Preconditions;
import us.lsi.tiposrecursivos.BinaryTree;

public class TestEjemplo1 {
	
	private static List<Character> stringListToCharList(String s) {
		
		String letras = s.replace(",", "").replace("[", "").replace("]","");
		List<Character> res = new ArrayList<>(letras.length());
		for(int i=0; i<letras.length(); i++) 
			res.add(letras.charAt(i));
		return res;
	}
	
	public static void main(String[] args) {
		
		String file = "ficheros/p2/ejemplo1.txt";
		
		List<Pair<BinaryTree<Character>,List<Character>>> inputs =
			Files2.streamFromFile(file).map(linea -> {
				String[] aux = linea.split("#");
				Preconditions.checkArgument(aux.length==2);
				return Pair.of(BinaryTree.parse(aux[0], s -> s.charAt(0)), stringListToCharList(aux[1]));
				}
			).toList();
		
		
		System.out.println("\n.....................................................................................................");
		System.out.println("EJEMPLO 1");
		System.out.println(".....................................................................................................\n");

		inputs.stream().forEach(par -> {
			
			BinaryTree<Character> tree = par.first();
			List<Character> chars = par.second();
			
			System.out.println("Arbol: "+tree+
					"\tSecuencia: "+chars+"\t["+
					Ejemplo1.solucion_recursiva(tree,chars)+"]");
			
		}
		);
	}
	
}

