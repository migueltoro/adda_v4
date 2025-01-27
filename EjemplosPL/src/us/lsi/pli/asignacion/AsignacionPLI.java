package us.lsi.pli.asignacion;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import us.lsi.common.Files2;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class AsignacionPLI {
	
	private static Integer n;
	private static Integer m;
	private static Double[][] costes;
	
	public static Double costes(Integer i, Integer j) {
		return costes[i][j];
	}
	
	public static Integer getN() {
		return n;
	}
	
	public static Integer getM() {
		return m;
	}
	
	public static void leeFichero(String f) {
		List<String> lineas = Files2.linesFromFile(f);
		AsignacionPLI.n = Integer.parseInt(lineas.get(0));
		AsignacionPLI.m = Integer.parseInt(lineas.get(1));
		AsignacionPLI.costes = new Double[n][m];
		String[] dat;
		Integer i, j;
		for (int k = 2; k < lineas.size(); k++) {
			dat = lineas.get(k).split(",");
			i = Integer.parseInt(dat[0].trim());
			j = Integer.parseInt(dat[1].trim());
			costes[i][j] = Double.parseDouble(dat[2].trim());
		}
	}
	
	public static void asignacion_model() throws IOException {
		AsignacionPLI.leeFichero("data/asignacionDeTareas_2.txt");
		AuxGrammar.generate(AsignacionPLI.class,"modelos/asignacion.lsi","ficheros/asignacion.lp");
		GurobiSolution solution = GurobiLp.gurobi("ficheros/asignacion.lp");
		Locale.setDefault(Locale.of("en", "US"));
		System.out.println(solution.toString((s,d)->d>0.));
	}


	public static void main(String[] args) throws IOException {		
		asignacion_model();
	}

}
