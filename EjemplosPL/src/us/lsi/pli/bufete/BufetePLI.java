package us.lsi.pli.bufete;

import java.io.IOException;
import java.util.Locale;
import java.util.stream.Collectors;

import us.lsi.bufete.datos.DatosBufete;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class BufetePLI {
	
	public static Integer getNumAbogados() {
		return DatosBufete.NUM_ABOGADOS;
	}	

	public static Integer getNumCasos() {
		return DatosBufete.NUM_CASOS;
	}
	
	public static Integer getHoras(Integer i, Integer j){
		return DatosBufete.getHoras(i, j);
	}
	
	
	public static void main(String[] args) throws IOException {		
		Locale.setDefault(Locale.of("es", "ES"));
		test("bufete.txt");		
//		test("PI6Ej2DatosEntrada2");
//		test("PI6Ej2DatosEntrada3");
	}

	public static void test(String fichero) throws IOException {
		Locale.setDefault(Locale.of("es", "ES"));
		DatosBufete.iniDatos("ficheros/"+fichero);
		AuxGrammar.generate(BufetePLI.class,"modelos/bufete.lsi","ficheros/bufete.lp");
		GurobiSolution gs = GurobiLp.gurobi("ficheros/bufete.lp");		
		DatosBufete.toConsole();
//		SolucionBufete.create(gs.objVal, gs.values).toConsole();
		System.out.println(gs.values);
		System.out.println(gs.values.entrySet().stream().filter(e->e.getKey().equals("T_0")).collect(Collectors.toList()));
		System.out.println(gs.values.get("T_0"));
	}
	
	
}

