package us.lsi.pli.reparto;

import java.io.IOException;
import java.util.Locale;
import java.util.Set;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.SimpleEdge;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class RepartoPLI {
	
	public static void reparto() throws IOException {
		DatosReparto.iniDatos("data/reparto2.txt");
		AuxGrammar.generate(DatosReparto.class,"modelos/reparto.lsi","ficheros/reparto.lp");
		GurobiSolution solution = GurobiLp.gurobi("ficheros/reparto.lp");
		Locale.setDefault(Locale.of("en", "US"));
		Double b  = DatosReparto.getBeneficios();
		System.out.println(solution.toString((s,d)->!s.contains("$")));
		System.out.println("Beneficio final = "+ (b-solution.objVal));
		Set<SimpleEdge<Integer>> se = DatosReparto.aristas(solution.values);
		System.out.println("Aristas = "+DatosReparto.aristas(solution.values));
		System.out.println(DatosReparto.getEdgeWeight(2, 1));
		
		GraphColors.toDot(DatosReparto.g2,"ficheros/reparto.gv",
				x->x.toString(),
				e->String.format("%.2f",e.weight()),
				v->GraphColors.color(Color.black),
				e->GraphColors.colorIf(Color.red,se.contains(e)));
	}

	public static void main(String[] args) throws IOException {
		reparto();
	}

}
