package us.lsi.bufete.datos;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TipoSolucion {
	
	protected TipoSolucion(List<Integer> ls) {
		super();
	}

	protected TipoSolucion(Double vo, Map<String, Double> vbles) {
		super();
	}

	public void toConsole() {
		pintarLinea();
		System.out.println(toString());
		pintarLinea();
	}
	
	private static void pintarLinea() {
		System.out.println(IntStream.range(0, 10).mapToObj(i->"o").collect(Collectors.joining("~~~~·~~~~", "\n", "\n")));		
	}	
}

