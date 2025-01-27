package us.lsi.reinas_test;

import java.io.IOException;
import java.util.Locale;
import java.util.stream.Collectors;

import us.lsi.common.String2;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.mochila_test.DataMochila;
import us.lsi.solve_test.AuxGrammar2;

public class TestReinas {
	
	public static void reinas(String file) throws IOException {
		AuxGrammar2.generate(DataMochila.class,file,"ficheros/reinas.lp");
		GurobiSolution s = GurobiLp.solveSolution("ficheros/reinas.lp");
		System.out.println("\n\n\n\n");
		System.out.println(String.format("Objetivo : %.2f",s.objVal));
		System.out.println("\n\n");
		System.out.println(s.values.keySet()
				.stream()
				.filter(e->!e.contains("$"))
//				.filter(e->s.values.get(e)>0)
				.map(e->String.format("%s == %.0f == %.1f == %.0f",e,s.values.get(e),s.values.get(e)+1,s.values.get(e)-1))
				.collect(Collectors.joining("\n")));
	}
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.of("en", "US"));
		Long a = System.nanoTime();
		reinas("ficheros/reinas_2.lsi");
		Long b = System.nanoTime();
		String2.toConsole("%d",b-a);
	}

}
