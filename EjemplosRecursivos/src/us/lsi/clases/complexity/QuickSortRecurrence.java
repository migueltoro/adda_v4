package us.lsi.clases.complexity;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.apache.commons.math3.fitting.WeightedObservedPoint;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation;
import org.apache.commons.math3.random.RandomDataGenerator;

import us.lsi.common.Files2;
import us.lsi.common.Trio;
import us.lsi.curvefitting.DataFile;
import us.lsi.curvefitting.Fit;
import us.lsi.curvefitting.PowerLog;
import us.lsi.graphics.MatPlotLib;

public class QuickSortRecurrence {
	
	/**
	 * Estimación del orden de complejidad de la solución de la recurrencia f(n)=f(m)+f(n-m)+n, m aleatorio en [1,n-2] 
	 * 
	 * @param n
	 * @param file
	 * @param p
	 * @return
	 */
	public static Long quickShortRecurrence(Long n,String file,Predicate<Map.Entry<Long,Long>> p) {
		Map<Long,Long> mem = new HashMap<>();
		Long r = quickShortRecurrence(n, mem);
		System.out.println(mem);
		Stream<String> st = mem.entrySet().stream()
				.sorted(Comparator.comparing(e->e.getKey()))
				.filter(p)
				.map(e->String.format("%d,%d",e.getKey(),e.getValue()));	
		Files2.toFile(st,file);
		return r;
	}
	
	public static Long quickShortRecurrence(Long n, Map<Long,Long> mem) {
		Long r = null;		
		if(mem.containsKey(n))
			r = mem.get(n);
		else if(n<6) {
			r = 1L;
			mem.put(n, r);
		} else {
			Long m = new RandomDataGenerator().nextLong(1, n-2);
			r = quickShortRecurrence(m,mem)+quickShortRecurrence(n-m,mem)+n;
			mem.put(n, r);
		}
		return r;
	}
	
	public static void test1(Long n,String file,Predicate<Map.Entry<Long,Long>> p) {		
		System.out.println(QuickSortRecurrence.quickShortRecurrence(n,file, p));
	}
	
	
	public static void test2(String file) {
		List<WeightedObservedPoint> data = DataFile.points(file);
		Fit pl = PowerLog.of(List.of());
		pl.fit(data);
		System.out.println(pl.getEvaluation().getRMS());
		System.out.println(pl.getExpression());
	}
	
	public static void test3(String file, Trio<Function<Double, Double>, String,Evaluation> f) {
		MatPlotLib.show(file, f.first(), f.second());
	}

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "us"));
		String file = "ficheros/quickSortR.txt";
//		test1(100000000000L,file,e->e.getKey()>0);
		test2(file);
	}


}
