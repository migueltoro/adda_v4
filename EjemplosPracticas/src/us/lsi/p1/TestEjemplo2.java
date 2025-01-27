package us.lsi.p1;

import java.util.List;
import java.util.function.Function;

import us.lsi.common.Files2;
import us.lsi.common.IntPair;

public class TestEjemplo2 {

		public static void main(String[] args) {
			
			Function<String,IntPair> stringToIntPair = s -> 
			{
				String[] s2 = s.split(",");
				return IntPair.of(Integer.parseInt(s2[0]),Integer.parseInt(s2[1]));
			};
			
			List<IntPair> lista = Files2.streamFromFile("ficheros/p1/ejemplo2.txt")
			.map(stringToIntPair)
			.toList();
			
			lista.forEach(pair -> {
				System.out.println("Recursiva no final: " + Ejemplo2.solucionRecursivaNoFinal(pair.first(),pair.second()));
				System.out.println("Recursiva final: " + Ejemplo2.solucionRecursivaFinal(pair.first(),pair.second()));
				System.out.println("Funcional: " + Ejemplo2.solucionFuncional(pair.first(),pair.second()));
				
			});

		}


}
