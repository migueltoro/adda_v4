package us.lsi.reinas_test;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import us.lsi.common.String2;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.mochila_test.DataMochila;
import us.lsi.solve_test.AuxGrammar2;

public class TestAllInValues {
	
	public static void test0() {
		Integer n = 5;
		for(int y= 0;y<n;y++)
			for(int x= 0;x<n;x++)
				String2.toConsole("y=%d,x=%d,y-x=%d,y+x=%d",y,x,y-x,y+x);
	}
	
	private static Integer index(String s, Integer i) {
		Integer r = 0;
		String[] p = s.split("_");
		if (p.length > i) {
			r = Integer.parseInt(p[i]);
		}
		return r;
	}
	
	public static void test1(String file) throws IOException {
		AuxGrammar2.generate(DataMochila.class,"ficheros/"+file+".lsi","ficheros/"+file+".lp");
		GurobiSolution s = GurobiLp.solveSolution("ficheros/"+file+".lp");
		System.out.println("\n\n\n\n");
		System.out.println(String.format("Objetivo : %.2f",s.objVal));
		System.out.println("\n\n");
		Set<Double> s1 = new HashSet<>();
		Set<Double> s2 = new HashSet<>();
		System.out.println(s.values.keySet()
				.stream()
				.filter(e->!e.contains("$"))
//				.filter(e->(e.contains("$") && s.values.get(e)>0) || !e.contains("$"))
				.sorted(Comparator.comparing(e->s.values.get(e)))
				.peek(e->s1.add(s.values.get(e)+index(e,1)))
				.peek(e->s2.add(s.values.get(e)-index(e,1)))
				.map(e->String.format("%4s == %4.0f == %4.0f== %4.0f",e,s.values.get(e),s.values.get(e)+index(e,1),s.values.get(e)-index(e,1)))
				.collect(Collectors.joining("\n")));
		String2.toConsole("%d,%d",s1.size(),s2.size());
	}
	
	public static void test2(String file) throws IOException {
		AuxGrammar2.generate(DataMochila.class,"ficheros/"+file+".lsi","ficheros/"+file+".lp");
	}

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.of("en", "US"));
		Long a = System.nanoTime();
		test2("reinas_3");
		Long b = System.nanoTime();
		String2.toConsole("%d",b-a);
	}

}
