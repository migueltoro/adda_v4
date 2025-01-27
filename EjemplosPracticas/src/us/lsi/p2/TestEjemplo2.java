package us.lsi.p2;

import java.util.List;
import java.util.function.Predicate;

import us.lsi.common.Files2;
import us.lsi.math.Math2;
import us.lsi.tiposrecursivos.Tree;

public class TestEjemplo2 {
	private static final Predicate<Integer> PAR = x-> x%2==0;
	private static final Predicate<Integer> PRIMO = x-> Math2.esPrimo(x);

	
	public static void main(String[] args) {
		
		String file = "ficheros/p2/ejemplo2.txt";
		
		List<Tree<Integer>> inputs = Files2
				.streamFromFile(file)
				.map(linea -> Tree.parse(linea,s->Integer.parseInt(s)))
				.toList();
		
		System.out.println("\n.....................................................................................................");
		System.out.println("EJEMPLO 2");
		System.out.println(".....................................................................................................\n");
		
		System.out.println("\nSOLUCION RECURSIVA-PAR:\n");	
		inputs.stream()
		.forEach(x->System.out.println(x+": "+Ejemplo2.solucion_recursiva(x, PAR)));

		System.out.println("\nSOLUCION RECURSIVA-PRIMOS:\n");	
		inputs.stream()
			.forEach(x->System.out.println(x+": "+Ejemplo2.solucion_recursiva(x, PRIMO)));


	}
}

