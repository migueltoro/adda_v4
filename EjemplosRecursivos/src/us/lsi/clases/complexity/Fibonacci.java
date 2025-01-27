package us.lsi.clases.complexity;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.fitting.WeightedObservedPoint;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.fraction.BigFractionField;
import org.apache.commons.math3.util.BigRealField;
import org.apache.commons.math3.util.Decimal64;
import org.apache.commons.math3.util.Decimal64Field;

import us.lsi.common.Arrays2;
import us.lsi.common.Matrix;
import us.lsi.common.String2;
import us.lsi.curvefitting.DataFile;
import us.lsi.curvefitting.Exponential;
import us.lsi.curvefitting.Fit;
import us.lsi.curvefitting.GenData;
import us.lsi.curvefitting.PowerLog;
import us.lsi.graphics.CanvasPlot;
import us.lsi.graphics.MatPlotLib;

public class Fibonacci {
	
	/**
	 * Calculo de la funcion de Fibonacci definida por:
	
	 * f(n) = f(n-1)+f(n-2) si n &ge; 0
	 * 
	 * @field Un campo
	 * @return El valor de f(n) calculado de forma recursiva sin memoria en el campo field
	 */
	public static <E extends FieldElement<E>> E fib(Integer n,Field<E> field){
		E r;
		if(n==0) {
			r = field.getZero();
		} else if(n==1) {
			r = field.getOne();
		} else {
			r = fib(n-1,field).add(fib(n-2,field));
		}
		return r;
	}
	
	/**
	 * Calculo de la funcion de Fibonacci definida por:
	
	 * f(n) = f(n-1)+f(n-2) si n &ge; 0
	 * 
	 * @field Un campo
	 * @return El valor de f(n) calculado de forma recursiva sin memoria en el campo field
	 */
	public static <E extends FieldElement<E>> E fibM(Integer n,Field<E> field){
		Map<Integer,E> m = new HashMap<>();
		return fibM(n,field,m);
	}
	
	private static <E extends FieldElement<E>> E fibM(Integer n,Field<E> field,Map<Integer,E> m){
		E r;
		if(m.containsKey(n)) {
			r = m.get(n);
		} else if(n==0) {
			r = field.getZero();
			m.put(n,r);
		} else if(n==1) {
			r = field.getOne();
			m.put(n,r);
		} else {
			r = fibM(n-1,field,m).add(fibM(n-2,field,m));
			m.put(n,r);
		}
		return r;
	}
	
	/**
	 * Calculo de la funcion de Fibonacci definida por:
	
	 * f(n) = f(n-1)+f(n-2) si n &ge; 0
	 * 
	 * @field Un campo
	 * @return El valor de f(n) calculado de forma iterativa en el campo field
	 */
	public static <E extends FieldElement<E>> E fibLin(Integer n,Field<E> field){
		Map<Integer,E> m = new HashMap<>();
		for(int i = 0;i<=n;i++) {
			E r;
			if(i==0) r = field.getZero();
			else if(i==1) r = field.getOne();
			else r = m.get(i-1).add(m.get(i-2));
			m.put(i, r);
			m.remove(i-3);
		}
		return m.get(n);
	}

	public static <E extends FieldElement<E>> E fibMatrix(Integer n,Field<E> field){
		E[] bb = Arrays2.newArray(field.getOne(),field.getOne(),field.getOne(),field.getZero());
		E[] cl = Arrays2.newArray(field.getOne(),field.getZero());
		Matrix<E> base = Matrix.of(2,2,bb);
		Matrix<E> colum = Matrix.of(2,1,cl);
		Matrix<E> m = Matrix.pow(base, n);
		Matrix<E> r = Matrix.multiply(m,colum);
		return r.get(1, 0);
	}
	
	public static void test1() {
		Integer n = 30;
		Long t0 = System.nanoTime();
		BigFraction r1 = fib(n,BigFractionField.getInstance());
		Long t1 = System.nanoTime();
		BigFraction r2 = fibLin(n,BigFractionField.getInstance());
		Long t2 = System.nanoTime();
		BigFraction r3 = fibM(n,BigFractionField.getInstance());
		Long t3 = System.nanoTime();
		BigFraction r4 = fibMatrix(n,BigFractionField.getInstance());
		Long t4 = System.nanoTime();
		String2.toConsole("%d == %s\n%d == %s\n%d == %s\n%d == %s",t1-t0,r1,t2-t1,r2,t3-t2,r3,t4-t3,r4);
		List<Long> ls = List.of(t1-t0,t2-t1,t3-t2,t4-t3);
		Double m = ls.stream().min(Comparator.naturalOrder()).get().doubleValue();
		List<Double> lsd = ls.stream().map(x->x/m).toList();
		List<String> lss = List.of("fib","fibLin","fibM","fibMatrix");
		System.out.println(lss);
		System.out.println(ls);
		System.out.println(lsd);
	}
	
	public static void test2() {
		Integer n = 30;
		Long t0 = System.nanoTime();
		Decimal64 r1 = fib(n,Decimal64Field.getInstance());
		Long t1 = System.nanoTime();
		Decimal64 r2 = fibLin(n,Decimal64Field.getInstance());
		Long t2 = System.nanoTime();
		Decimal64 r3 = fibM(n,Decimal64Field.getInstance());
		Long t3 = System.nanoTime();
		Decimal64 r4 = fibMatrix(n,Decimal64Field.getInstance());
		Long t4 = System.nanoTime();
		String2.toConsole("%d == %s\n%d == %s\n%d == %s\n%d == %s",t1-t0,r1,t2-t1,r2,t3-t2,r3,t4-t3,r4);
		List<Long> ls = List.of(t1-t0,t2-t1,t3-t2,t4-t3);
		Double m = ls.stream().min(Comparator.naturalOrder()).get().doubleValue();
		List<Double> lsd = ls.stream().map(x->x/m).toList();
		List<String> lss = List.of("fib","fibLin","fibM","fibMatrix");
		System.out.println(lss);
		System.out.println(ls);
		System.out.println(lsd);
	}
	
	public static void test3() {
		Locale.setDefault(Locale.of("en", "US"));
		Integer n = 100000;
		Long t0 = System.nanoTime();
		BigDecimal r1 = fibLin(n,BigRealField.getInstance()).bigDecimalValue();
		Long t1 = System.nanoTime();
		BigDecimal r2 = fibMatrix(n,BigRealField.getInstance()).bigDecimalValue();
		Long t2 = System.nanoTime();
		String2.toConsole("%d == %s\n%d == %s",t1-t0,r1,t2-t1,r2);
		Long d1 = t1-t0;
		Long d2 = t2-t1;
		String2.toConsole("Test3 = %g,%g",d1.doubleValue(),d2.doubleValue());
	}
	
	public static void test4() {
		Locale.setDefault(Locale.of("en", "US"));
		Integer n = 100000;
		Long t0 = System.nanoTime();
		BigFraction r1 = Fibonacci.fibLin(n,BigFractionField.getInstance());
		Long t1 = System.nanoTime();
		BigFraction r2 = Fibonacci.fibMatrix(n,BigFractionField.getInstance());	
		Long t2 = System.nanoTime();
		String2.toConsole("%d == %s\n%d == %s",t1-t0,r1,t2-t1,r2);
		Long d1 = t1-t0;
		Long d2 = t2-t1;
		String2.toConsole("Test4 = %g, %g",d1.doubleValue(), d2.doubleValue());
	}
	
	public static void test5() {
		String file = "ficheros/fibonacci.txt";
		List<WeightedObservedPoint> data = DataFile.smoothPoints(file,1.);
		Exponential pl = Exponential.of();
		pl.fit(data);
		System.out.println(pl.getEvaluation().getRMS());
		MatPlotLib.show(file, pl.getFunction(), pl.getExpression());
	}
	
	public static void test6() {
		String file = "ficheros/fibonacciLin.txt";
		Consumer<Integer> c1 = t->Fibonacci.fibLin(t,BigFractionField.getInstance());
		Function<Integer,Long> f1 = GenData.time(c1);
		GenData.tiemposEjecucionAritmetica(f1,file,500,100300,500,10,10);
		file = "ficheros/fibonacciMatrix.txt";
		c1 = t->Fibonacci.fibMatrix(t,BigFractionField.getInstance());
		GenData.tiemposEjecucionAritmetica(f1,file,500,100300,500,10,10);
	}
	
	public static void test7() {
		MatPlotLib.showCombined("Tiempos",
				List.of("ficheros/fibonacciLin.txt","ficheros/fibonacciMatrix.txt"), 
				List.of("Lineal","Matrix"));
		MatPlotLib.showCombined("Tiempos",
				List.of("ficheros/fibonacciLin.txt"), 
				List.of("Lineal"));
		MatPlotLib.showCombined("Tiempos",
				List.of("ficheros/fibonacciMatrix.txt"), 
				List.of("Matrix"));
	}
	
	public static void test8() {
		String file = "ficheros/fibonacciLin.txt";
		List<WeightedObservedPoint> data = DataFile.smoothPoints(file,1.);
		Fit pl = PowerLog.of(List.of());
		pl.fit(data);
		System.out.println(pl.getExpression());
		System.out.println(pl.getEvaluation().getRMS());
//		MatPlotLib.show(file, pl.getFunction(), pl.getExpression());
		file = "ficheros/fibonacciMatrix.txt";
		data = DataFile.smoothPoints(file,1.);
		pl = PowerLog.of(List.of());
		pl.fit(data);
		System.out.println(pl.getExpression());
		System.out.println(pl.getEvaluation().getRMS());
//		MatPlotLib.show(file, pl.getFunction(), pl.getExpression());
	}
	
	public static void test9() {
		CanvasPlot.showCombined("Tiempos",
				List.of("ficheros/fibonacciLin.txt","ficheros/fibonacciMatrix.txt"), 
				List.of("Lineal","Matrix"));
//		CanvasPlot.showCombined("Tiempos",
//				List.of("ficheros/fibonacciLin.txt"), 
//				List.of("Lineal"));
//		CanvasPlot.showCombined("Tiempos",
//				List.of("ficheros/fibonacciMatrix.txt"), 
//				List.of("Matrix"));
	}
	

	public static void main(String[] args) {
//		test5();
//		test6();
		test8();
//		test7();
//		test9();
	}
	

}
