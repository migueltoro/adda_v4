package us.lsi.clases.complexity;

import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Function;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.fitting.WeightedObservedPoint;
import org.apache.commons.math3.fraction.BigFractionField;

import us.lsi.common.Pair;
import us.lsi.curvefitting.DataFile;
import us.lsi.curvefitting.Fit;
import us.lsi.curvefitting.GenData;
import us.lsi.curvefitting.PowerLog;
import us.lsi.graphics.CanvasPlot;
import us.lsi.graphics.MatPlotLib;

public class Factorial {
	
	public static <E extends FieldElement<E>> E factorialI(Integer n, Field<E> field){
		E r = field.getOne();
		Integer i = n;
		while(i>0) {
			r = r.multiply(i);
			i = i-1;
		}
		return r;
	}
	
	public static <E extends FieldElement<E>> E factorialR(Integer n, Field<E> field){
		return factorialR(n, n, field);
	}
	
	public static <E extends FieldElement<E>> E factorialR(Integer i, Integer n, Field<E> field){
		E r; 
		if (i > 0) {
			r = factorialR(i-1,n,field).multiply(i);
		} else 
			r = field.getOne();
		return r;
	}
	
	public static void test1() {
		String file = "ficheros/factorialI.txt";
		Consumer<Integer> c1 = t->Factorial.factorialI(t,BigFractionField.getInstance());
		Function<Integer,Long> f1 = GenData.time(c1);
		GenData.tiemposEjecucionAritmetica(f1,file,500,50000,500,1,1);
	}
	
	public static void test2() {
		String file = "ficheros/factorialR.txt";
		Consumer<Integer> c1 = t->Factorial.factorialR(t,BigFractionField.getInstance());
		Function<Integer,Long> f1 = GenData.time(c1);
		GenData.tiemposEjecucionAritmetica(f1,file,100,10500,500,1,1);
	}
	
	public static void test3() {
		String file = "ficheros/factorialI.txt";
		List<WeightedObservedPoint> data = DataFile.points(file);
		Fit pl = PowerLog.of(List.of(Pair.of(2, 1.),Pair.of(3, 0.)));
		pl.fit(data);
		System.out.println(pl.getExpression());
		System.out.println(pl.getEvaluation().getRMS());
		MatPlotLib.show(file, pl.getFunction(), pl.getExpression());
	}
	
	public static void test4() {
		String file = "ficheros/factorialR.txt";
		List<WeightedObservedPoint> data = DataFile.points(file);
		Fit pl = PowerLog.of(List.of(Pair.of(2, 1.)),2,0.,9.5,10000000.);
		pl.fit(data);
		System.out.println(pl.getExpression());
		System.out.println(pl.getEvaluation().getRMS());
		MatPlotLib.show(file, pl.getFunction(), pl.getExpression());
	}
	
	public static void test5() {
		MatPlotLib.showCombined("Tiempos",
				List.of("ficheros/factorialI.txt","ficheros/factorialR.txt"), 
				List.of("Iterativo","Recursivo"));
	}
	
	public static void test6() {
		String file = "ficheros/factorialR.txt";
		List<WeightedObservedPoint> data = DataFile.points(file);
		Fit pl = PowerLog.of(List.of());
		pl.fit(data);
		System.out.println(pl.getExpression());
		System.out.println(pl.getEvaluation().getRMS());
		CanvasPlot.show(file, pl.getFunction(), pl.getExpression());
	}
	
	public static void test7() {
		CanvasPlot.showCombined("Tiempos",
				List.of("ficheros/factorialI.txt","ficheros/factorialR.txt"), 
				List.of("Iterativo","Recursivo"));
	}

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "us"));
//		test2();
		test4();
	}

}
