package us.lsi.pli.scl;

import java.io.IOException;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class SclPLI {
	public static Integer T1;
	public static Integer T2;
	public static String C1;
	public static String C2;
	
	public static Integer coincide(Integer i, Integer j) {
		return C1.charAt(i) == C2.charAt(j)?1:0;
	}	
	public static Boolean coincideBool(Integer i, Integer j) {
		return C1.charAt(i) == C2.charAt(j);
	}
	public static Integer getT1() {
		return T1;
	}
	public static Integer getT2() {
		return T2;
	}

	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.of("en", "US"));
			
		C1= "affcddae";
		C2 = "aade2ebbdbaad";		
		//C1= "BBAB";
		//C2 = "ABBA";
		T1 = C1.length();
		T2 = C2.length();
		
		AuxGrammar.generate(SclPLI.class, "modelos/scl.lsi", "ficheros/scl.lp");
		GurobiSolution sol= GurobiLp.gurobi("ficheros/scl.lp");
		System.out.println(sol.toString((k,v)->v > 0.));
		
		printSolucion(sol);
		
	}
	private static void printSolucion(GurobiSolution sol) {
		System.out.println(
			IntStream.range(0, T1).boxed()
			.flatMap(i -> IntStream.range(0, T2).boxed()
					.filter(j -> sol.values.get("x_"+i+"_"+j).intValue() == 1)
					.map(j -> ""+C1.charAt(i)))
			.collect(Collectors.joining())
		);
		
	}
}


