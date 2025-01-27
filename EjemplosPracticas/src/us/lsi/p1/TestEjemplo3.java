package us.lsi.p1;

import java.util.List;
import java.util.function.Function;

import us.lsi.common.Files2;
import us.lsi.common.IntPair;

public class TestEjemplo3 {
	public static void main(String[] args) {
		
		Function<String,IntPair> stringToIntPair = s -> 
		{
			String[] s2 = s.split(",");
			return IntPair.of(Integer.parseInt(s2[0]),Integer.parseInt(s2[1]));
		};
		
		List<IntPair> lista = Files2.streamFromFile("ficheros/p1/ejemplo3.txt")
		.map(stringToIntPair)
		.toList();
		
		lista.forEach(pair -> {
			System.out.println("Recursiva: " + Ejemplo3.solucionRecursivaSinMemoria(pair.first(),pair.second()));
			System.out.println("Recursiva con memoria: " + Ejemplo3.solucionRecursivaConMemoria(pair.first(),pair.second()));
			System.out.println("Iterativa: " + Ejemplo3.solucionIterativa(pair.first(),pair.second()));
			
		});

	}


}
