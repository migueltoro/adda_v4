package us.lsi.p1;


import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import org.apache.commons.math3.fitting.WeightedObservedPoint;

import us.lsi.clase.potencia.Potencia;
import us.lsi.common.Pair;
import us.lsi.curvefitting.DataFile;
import us.lsi.curvefitting.Fit;
import us.lsi.curvefitting.GenData;
import us.lsi.curvefitting.PowerLog;
import us.lsi.graphics.MatPlotLib;



public class TestEjemplo4 {
	
	private static Integer nMin = 100; // n mínimo para el cálculo de potencia
	private static Integer nMax = 100000; // n máximo para el cálculo de potencia
	private static Integer razon = 3330; // incremento en los valores de n del cálculo de potencia
	private static Integer nIter = 50; // número de iteraciones para cada medición de tiempo
	private static Integer nIterWarmup = 10000; // número de iteraciones para warmup

	private static Double a = 3.;
	
	public static void genData (Consumer<Integer> algorithm, String file) {
		Function<Integer,Long> f1 = GenData.time(algorithm);
		GenData.tiemposEjecucionAritmetica(f1,file,nMin,nMax,razon,nIter,nIterWarmup);

	}
	
	public static void show(Fit pl, String file, String label) {
		List<WeightedObservedPoint> data = DataFile.points(file);
		pl.fit(data);
		MatPlotLib.show(file, pl.getFunction(), String.format("%s = %s",label,pl.getExpression()));
	}
	
	
	         
	public static void showCombined() {
		MatPlotLib.showCombined("Tiempos",
				List.of("ficheros_generados/p1/potRecursiva.txt","ficheros_generados/p1/potIterativa.txt","ficheros_generados/p1/potLog.txt"), 
				List.of("Recursiva","Iterativa","Log"));
	}
	

	public static void main(String[] args) {
		genData(t -> Ejemplo4.potenciaR(a,t),"ficheros_generados/p1/potRecursiva.txt");
		genData(t -> Ejemplo4.potenciaIter(a,t),"ficheros_generados/p1/potIterativa.txt");
		genData(t -> Potencia.pot(a.longValue(),t),"ficheros_generados/p1/potLog.txt");
		
		
		show(PowerLog.of(List.of(Pair.of(2, 0.),Pair.of(3, 0.))), "ficheros_generados/p1/potRecursiva.txt","Recursiva");
		show(PowerLog.of(List.of(Pair.of(2, 0.),Pair.of(3, 0.))),"ficheros_generados/p1/potIterativa.txt","Iterativa");
		show(PowerLog.of(List.of(Pair.of(1, 0.),Pair.of(2, 1.),Pair.of(3, 0.))),"ficheros_generados/p1/potLog.txt","Log");
		
		showCombined();
	}



}
