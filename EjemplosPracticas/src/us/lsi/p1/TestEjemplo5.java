package us.lsi.p1;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import org.apache.commons.math3.fitting.WeightedObservedPoint;

import us.lsi.common.Pair;
import us.lsi.curvefitting.DataFile;
import us.lsi.curvefitting.Exponential;
import us.lsi.curvefitting.Fit;
import us.lsi.curvefitting.GenData;
import us.lsi.curvefitting.PowerLog;
import us.lsi.graphics.MatPlotLib;

public class TestEjemplo5 {

	private static Integer nMinExp = 5;
	private static Integer nMaxExp = 25; 
	private static Integer razonExp = 3; 
	private static Integer nIterExp = 10; 
	private static Integer nIterWarmupExp = 10;
	
	
	private static Integer nMin = 32;
	private static Integer nMax = 100000; 
	private static Integer razon = 2; 
	private static Integer nIter = 40; 
	private static Integer nIterWarmup = 1000;
	
	

	public static void genDataAritmetico(Consumer<Integer> algorithm,String file,Integer tMin,Integer tMax,Integer razon,Integer numIter,Integer numIterWarmup) {
		Function<Integer,Long> f1 = GenData.time(algorithm);
		GenData.tiemposEjecucionAritmetica(f1,file,tMin,tMax,razon,numIter,numIterWarmup);
	}
	
	public static void genDataGeometrico(Consumer<Integer> algorithm,String file,Integer tMin,Integer tMax,Integer razon,Integer numIter,Integer numIterWarmup) {
		Function<Integer,Long> f1 = GenData.time(algorithm);
		GenData.tiemposEjecucionGeometrica(f1,file,tMin,tMax,razon,numIter,numIterWarmup);
	}
	
	public static void show(Fit pl, String file, String label) {
		List<WeightedObservedPoint> data = DataFile.points(file);
		pl.fit(data);
		MatPlotLib.show(file, pl.getFunction(), String.format("%s = %s",label,pl.getExpression()));
	}

	
	public static void showCombined() {
		MatPlotLib.showCombined("Tiempos",
				List.of("ficheros_generados/p1/fib_r.txt","ficheros_generados/p1/fib_rm.txt","ficheros_generados/p1/fib_pm.txt","ficheros_generados/p1/fib_pm.txt",
						"ficheros_generados/p1/fib_r_d.txt","ficheros_generados/p1/fib_rm_d.txt","ficheros_generados/p1/fib_pm_d.txt","ficheros_generados/p1/fib_pm_d.txt"), 
				List.of("Recursivo_BI","RecursivoMemoria_BI","PotenciaMatriz_BI","Iterativa_BI",
						"Recursivo_Double","RecursivoMemoria_Double","PotenciaMatriz_Double","Iterativa_Double"));
	}
	
	

	public static void main(String[] args) {
		

		// BIG INTEGER: REC SIN MEMORIA, REC CON MEMORIA, ITERATIVO, CON POTENCIA DE MATRICES
		genDataAritmetico(Ejemplo5::fibR,"ficheros_generados/p1/fib_r.txt", nMinExp, nMaxExp, razonExp, nIterExp, nIterWarmupExp);
		genDataGeometrico(Ejemplo5::fibRM,"ficheros_generados/p1/fib_rm.txt",nMin, nMax, razon, nIter, nIterWarmup);
		genDataGeometrico(Ejemplo5::fibIter,"ficheros_generados/p1/fib_iter.txt",nMin, nMax, razon, nIter, nIterWarmup);
		genDataGeometrico(Ejemplo5::fibonacciPotMat,"ficheros_generados/p1/fib_pm.txt",nMin, nMax, razon, nIter, nIterWarmup);
		
		//DOUBLE: REC SIN MEMORIA, REC CON MEMORIA, ITERATIVO, CON POTENCIA DE MATRICES
		genDataAritmetico(Ejemplo5::fibR_Double,"ficheros_generados/p1/fib_r_d.txt",nMinExp,nMaxExp,razonExp,nIterExp,nIterWarmupExp);
		genDataGeometrico(Ejemplo5::fibRM_Double,"ficheros_generados/p1/fib_rm_d.txt",nMin, nMax, razon, nIter, nIterWarmup);
		genDataGeometrico(Ejemplo5::fibIter_Double,"ficheros_generados/p1/fib_iter_d.txt",nMin, nMax, razon, nIter, nIterWarmup);
		genDataGeometrico(Ejemplo5::fibonacciPotMat_Double,"ficheros_generados/p1/fib_pm_d.txt",nMin, nMax, razon, nIter, nIterWarmup);
	
		
		// BIG INTEGER: REC SIN MEMORIA, REC CON MEMORIA, ITERATIVO, CON POTENCIA DE MATRICES
		show(Exponential.of(), "ficheros_generados/p1/fib_r.txt","rec");
		show(PowerLog.of(List.of(Pair.of(2, 0.),Pair.of(3, 0.))), "ficheros_generados/p1/fib_rm.txt","rec_mem");
		show(PowerLog.of(List.of(Pair.of(2, 0.),Pair.of(3, 0.))), "ficheros_generados/p1/fib_iter.txt","iter");
		show(PowerLog.of(List.of(Pair.of(2, 0.),Pair.of(3, 0.))), "ficheros_generados/p1/fib_pm.txt","pm");
				
		//DOUBLE: REC SIN MEMORIA, REC CON MEMORIA, ITERATIVO, CON POTENCIA DE MATRICES
		show(Exponential.of(),"ficheros_generados/p1/fib_r_d.txt","rec_d");
		show(PowerLog.of(List.of(Pair.of(2, 0.),Pair.of(3, 0.))), "ficheros_generados/p1/fib_rm_d.txt","rec_mem_d");
		show(PowerLog.of(List.of(Pair.of(2, 0.),Pair.of(3, 0.))), "ficheros_generados/p1/fib_iter_d.txt","iter_d");
		show(PowerLog.of(List.of(Pair.of(1, 0.), Pair.of(2, 1.),Pair.of(3, 0.))), "ficheros_generados/p1/fib_pm_d.txt","pm_d");
	


		showCombined();
	}


}
