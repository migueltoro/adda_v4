package us.lsi.p1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import us.lsi.geometria.Punto2D;
import us.lsi.geometria.Cuadrante;

public class Ejemplo1 {

	public static Map<Cuadrante, Double> solucionFuncional(List<Punto2D> ls) { // Del enunciado
		return ls.stream().collect(
				Collectors.groupingBy(Punto2D::cuadrante, Collectors.reducing(0., x -> x.x(), (x, y) -> x + y)));
	}

	public static Map<Cuadrante, Double> solucionIterativa(List<Punto2D> ls) {
		Integer e = 0;
		Map<Cuadrante, Double> ac = new HashMap<>();
		while (e < ls.size()) {
			Punto2D p = ls.get(e);
			Cuadrante key = p.cuadrante();
			if (ac.containsKey(key))
				ac.put(key, ac.get(key) + p.x());
			else
				ac.put(key, p.x());
			e++;
		}
		return ac;

	}

	public static Map<Cuadrante, Double> solucionRecursivaFinal(List<Punto2D> ls) {
		return recFinal(0, new HashMap<>(), ls);
	}
	
	private static Map<Cuadrante, Double> recFinal(Integer e, Map<Cuadrante, Double> ac, List<Punto2D> ls) {
		Map<Cuadrante, Double> r = ac;
		if (e < ls.size()) {
			Punto2D p = ls.get(e);
			Cuadrante key = p.cuadrante();
			if (ac.containsKey(key)) {
				ac.put(key, ac.get(key) + p.x());
			} else {
				ac.put(key, p.x());
			}
			r = recFinal(e+1, ac, ls);
		}
		return r;
	}
	
	
	
}

