package us.lsi.alg.pack;


import java.util.function.Predicate;

public class Heuristica {
	
	public static Double ncD(PackVertex v1) {
		Double s = 0.;
		for(int i = v1.index(); i < Data.n ;i++) {
			s += Data.volumenObjeto(i);
		}
		for(int i = 0; i < v1.nc() ; i++) {
			s -= Data.volumenContenedor- v1.carga().get(i);
		}
		return s/Data.volumenContenedor;
	}
	
	public static Double heuristic(PackVertex v1, Predicate<PackVertex> goal, PackVertex v2) {
		return (double)(PackVertexI.n-v1.index());
//		return ncD(v1);
	}

}
