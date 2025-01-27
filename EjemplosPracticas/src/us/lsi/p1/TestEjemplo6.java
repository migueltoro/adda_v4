package us.lsi.p1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;

import org.apache.commons.math3.fitting.WeightedObservedPoint;

import us.lsi.common.Pair;
import us.lsi.curvefitting.DataFile;
import us.lsi.curvefitting.Fit;
import us.lsi.curvefitting.GenData;
import us.lsi.curvefitting.PowerLog;
import us.lsi.graphics.MatPlotLib;

public class TestEjemplo6 {
	
	/*
	PI1 - Ejemplo 6

	Analizar los tiempos de ejecución de las búsquedas lineal y binaria, 
	usando en los casos que correspondan listas ordenadas o desordenadas.
	*/

	
	private static Random rr = new Random(System.nanoTime());
	private static List<Integer> list = List.of();
	private static List<String> ficheros = new ArrayList<String>();
	private static List<Fit> curvas = new ArrayList<Fit>();
	
//	private static void preparaDatos() {
//		list = null;
//	}

	public static void generaListaEnteros(Integer t, Boolean ord) {
		List <Integer> ls = new ArrayList<Integer>();
		for (int i=0;i<t;i++) {
			ls.add(rr.nextInt(t*1000));
		}
		if (ord)
			ls.sort(Comparator.naturalOrder());
		list = ls;
	}
	
	private static Integer e;
	
	private static Consumer<Integer> pre_noord = t -> {
		e = rr.nextInt(t*1000);
		if (t != list.size())
			generaListaEnteros(t,false);
	};

	private static Consumer<Integer> pre_ord = t -> {
		e = rr.nextInt(t*1000);
		if (t != list.size())
			generaListaEnteros(t,true);
	};


	
	public static void genDatos() {
		String file = "ficheros_generados/p1/busquedaBinaria.txt";
		ficheros.add(file);
		Function<Integer,Long> f1 = GenData.time(pre_ord,t ->Ejemplo6.busquedaBinaria(list,e));
		GenData.tiemposEjecucionAritmetica(f1,file,50,100000,500,30,t->t<100?1000000:10000);
		file = "ficheros_generados/p1/busquedaLineal_noord.txt";
		ficheros.add(file);
		f1 = GenData.time(pre_noord,t ->Ejemplo6.busquedaLineal(list,e));
		GenData.tiemposEjecucionAritmetica(f1,file,50,100000,500,100,t->(int)(1000-0.005*t));
		file = "ficheros_generados/p1/busquedaLineal_ord.txt";
		ficheros.add(file);
		f1 = GenData.time(pre_ord,t ->Ejemplo6.busquedaLinealOrdI(list,e));
		GenData.tiemposEjecucionAritmetica(f1,file,50,100000,500,100,t->(int)(1000-0.005*t));
//		file = "ficheros_generados/busquedaLineal_noord.txt";
//		ficheros.add(file);
//		f1 = GenData.time(pre_noord,t ->Ejemplo3.busquedaLineal(list,e));
//		GenData.tiemposEjecucion(f1,file,50,100000,500,900,t->(int)(10000-0.05*t));
//		file = "ficheros_generados/busquedaLineal_ord.txt";
//		ficheros.add(file);
//		f1 = GenData.time(pre_ord,t ->Ejemplo3.busquedaLinealOrdI(list,e));
//		GenData.tiemposEjecucion(f1,file,50,100000,500,900,t->(int)(10000-0.05*t));
	}

	
	public static Fit show(String file, String busqueda, List<Pair<Integer,Double>> lp) {
		List<WeightedObservedPoint> data = DataFile.points(file);
		Fit pl = PowerLog.of(lp);
		pl.fit(data);
		System.out.println(pl.getExpression());
		System.out.println(pl.getEvaluation().getRMS());
		MatPlotLib.show(file, pl.getFunction(), busqueda+ " = " + pl.getExpression());
		return pl;
	}
	
	public static void show() {
		curvas.add(show(ficheros.get(0),"bin",List.of(Pair.of(2, 1.),Pair.of(3, 0.))));
		curvas.add(show(ficheros.get(1),"lin no ord",List.of(Pair.of(1, 1.),Pair.of(2, 0.),Pair.of(3, 0.))));
		curvas.add(show(ficheros.get(2),"lin ord",List.of(Pair.of(1, 1.),Pair.of(2, 0.),Pair.of(3, 0.))));
	}
	
	public static void combined(List<Fit> pls) {
		MatPlotLib.showCombined("Tiempos",
				ficheros,				
				pls.stream().map(pl->pl.getFunction()).toList(),
				List.of("Binaria","Lineal_noord","Lineal_ord"));
	}

	
	public static void combined() {
		MatPlotLib.showCombined("Tiempos",
				ficheros, 
				List.of("Binaria","Lineal_noord","Lineal_ord"));
	}
	

	public static void main(String[] args) {
		genDatos();
		show();
		combined();
		combined(curvas);
	}

}

