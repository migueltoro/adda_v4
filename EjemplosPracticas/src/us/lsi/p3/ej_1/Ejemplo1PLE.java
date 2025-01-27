package us.lsi.p3.ej_1;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;


public class Ejemplo1PLE {
	public static Integer suma;
	public static List<Integer> elementos;
	
	public static Integer getSuma() {
		return suma;
	}
	
	public static Integer getNumElementos() {
		return elementos.size();
	}
	
	public static Integer getElemento(Integer i) {
		return elementos.get(i);
	}
	
	public static Integer getMultiplicidad(Integer i) {
		return suma / elementos.get(i);
	}
	
	public static void ejemplo1_model() throws IOException {
		DatosMulticonjunto.iniDatos("ficheros/p3/ejemplo1_1.txt");
//		DatosMulticonjunto.iniDatos("ficheros/Ejemplo1DatosEntrada2.txt");

		suma = DatosMulticonjunto.getSuma();
		elementos = DatosMulticonjunto.getListaNumeros();
		
		//si cambia el fichero de datos de entrada, cambiar tambien el nº del .lp para no sobreescribirlo
//		AuxGrammar.generate(Ejemplo1PLE.class,"lsi_models/Ejemplo1.lsi","gurobi_models/Ejemplo1-2.lp");
		AuxGrammar.generate(Ejemplo1PLE.class,"modelos/ejemplo1.lsi","gurobi_models/Ejemplo1-2.lp");
		GurobiSolution solution = GurobiLp.gurobi("gurobi_models/Ejemplo1-2.lp");
		Locale.setDefault(Locale.of("en", "US"));
		System.out.println(solution.toString((s,d)->d>0.));
	}
	
	public static void main(String[] args) throws IOException {	
		ejemplo1_model();
	}

}

