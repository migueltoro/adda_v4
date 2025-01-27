package us.lsi.pli.pack;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import us.lsi.common.Files2;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class PackPLI {
	
	public static List<Integer> volumenesObjetos;
	public static Integer n;
	public static Integer volumenContenedor;
	public static Integer m; 
	
	public static void data(String file, Integer volumenContenedor, Integer m) {
		PackPLI.volumenesObjetos = Files2.streamFromFile(file).map(e->Integer.parseInt(e)).collect(Collectors.toList());
		Collections.sort(PackPLI.volumenesObjetos,Comparator.reverseOrder());
		PackPLI.n = PackPLI.volumenesObjetos.size();
		PackPLI.volumenContenedor = volumenContenedor;
		PackPLI.m = m;
	}
	
	public static Integer getN() {
		return PackPLI.n;	
	}
	
	public static Integer getM() {
		return PackPLI.m;	
	}
	
	public static Integer volumenObjeto(Integer i) {
		return PackPLI.volumenesObjetos.get(i);
	}
	
	public static Integer volumenContenedor() {
		return PackPLI.volumenContenedor;
	}
	
	public static void pack_model() throws IOException {
		PackPLI.data("data/pack.txt",12,18);
		AuxGrammar.generate(PackPLI.class,"modelos/pack.lsi","ficheros/pack.lp");
		GurobiSolution solution = GurobiLp.gurobi("ficheros/pack.lp");
		Locale.setDefault(Locale.of("en", "US"));
		System.out.println(solution.toString((s,d)->d>0.));
	}

	public static void main(String[] args) throws IOException {
		pack_model();
	}

}
