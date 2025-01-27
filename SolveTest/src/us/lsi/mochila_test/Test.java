package us.lsi.mochila_test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

import us.lsi.common.String2;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;
import us.lsi.solve.AuxGrammar.ListString;
import us.lsi.solve.AuxGrammar.Type;
import us.lsi.solve_test.AuxGrammar2;

public class Test {
	
	public static void mochila() throws IOException {
		DataMochila.iniMochila();
		AuxGrammar2.generate(DataMochila.class,"ficheros/mochila.lsi","ficheros/mochila.lp");
		GurobiSolution s = GurobiLp.solveSolution("ficheros/mochila.lp");
		String2.toConsole(s.toString((k,v)->v>0));
	}
	
	public static void example() throws IOException {
		AuxGrammar2.generate(DataMochila.class,"ficheros/model_3.txt","ficheros/model_3.lp");
		GurobiLp.solve("ficheros/model_3.lp");
	}

	public static void test1() throws NoSuchMethodException, SecurityException {
		DataMochila.iniMochila();
		AuxGrammar.dataClass = DataMochila.class;
		System.out.println(AuxGrammar.asDouble(Double.parseDouble("2.3")));
		System.out.println(AuxGrammar.asInteger(Integer.parseInt("23")));
		System.out.println(AuxGrammar.asInteger(Integer.parseInt("27")));
		System.out.println(AuxGrammar.asDouble(Double.parseDouble("23.5")));
		System.out.println(AuxGrammar.castInteger(Double.parseDouble("23.5")));
		System.out.println(AuxGrammar.asString("23."));
		System.out.println(AuxGrammar.asListString(ListString.of(Arrays.asList("Antonio","Pepe","Juan"))));
		System.out.println(AuxGrammar.div(3,2.));		
		Method m1 = DataMochila.class.getDeclaredMethod("s",Integer.class,Integer.class,Integer.class);
		AuxGrammar.functions.put("s",m1);
		Method m2 = DataMochila.class.getDeclaredMethod("getN");
		AuxGrammar.functions.put("getN",m2);
		System.out.println(AuxGrammar.functions);
		Type types1[] = {Type.INT,Type.INT,Type.INT};
		AuxGrammar.parametersType.put("s",types1);
		Type types2[] = {};
		AuxGrammar.parametersType.put("getN",types2);
		System.out.println(AuxGrammar.parametersType.entrySet().stream()
				.map(e->String.format("%s = %s",e.getKey(),AuxGrammar.toString(e.getValue())))
				.collect(Collectors.joining(",","[","]")));
		Object p1[] = {3,4,5};
		Object r1 = AuxGrammar.result("s",p1);
		System.out.println(r1);
		Object p2[] = {};
		Object r2 = AuxGrammar.result("getN",p2);
		System.out.println(r2);
		System.out.println(AuxGrammar.toString(AuxGrammar.partes("x_0 + -23.567")));
	}
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.of("en", "US"));
		mochila();
	}

}
