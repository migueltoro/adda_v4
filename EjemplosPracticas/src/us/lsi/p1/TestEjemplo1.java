package us.lsi.p1;

import java.util.List;
import java.util.function.Function;

import us.lsi.common.Files2;
import us.lsi.geometria.Punto2D;

public class TestEjemplo1 {
	public static void main(String[] args) {
		Function<String,Punto2D> parsePunto = s -> {
			String s2 [] = s.split(",");
			return Punto2D.of(Double.valueOf(s2[0]), Double.valueOf(s2[1]));
		};
		
		
		List<Punto2D> ls = Files2
				.streamFromFile("ficheros/p1/ejemplo1.txt")
				.map(parsePunto)
				.toList();
		
		
		System.out.println("1) Solucion Funcional:\n" + Ejemplo1.solucionFuncional(ls));
		System.out.println("2) Solucion Iterativa:\n" + Ejemplo1.solucionIterativa(ls));
		System.out.println("3) Solucion Rec. Final:\n" + Ejemplo1.solucionRecursivaFinal(ls));
		System.out.println(".............................................................................................................................");


	}

}
