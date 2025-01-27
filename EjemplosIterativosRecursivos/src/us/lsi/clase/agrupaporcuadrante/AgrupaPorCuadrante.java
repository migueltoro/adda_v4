package us.lsi.clase.agrupaporcuadrante;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import us.lsi.geometria.Cuadrante;
import us.lsi.geometria.Punto2D;

public class AgrupaPorCuadrante {
	
	/**
	 * This method calculates the sum of x-coordinates of points in each quadrant.
	 * It uses a functional approach with Java Streams to process the list of points.
	 *
	 * @param ls A list of points in 2D space.
	 * @return A map where the keys are the quadrants and the values are the sum of x-coordinates of points in that quadrant.
	 */
	
	public static Map<Cuadrante, Double> solucionFuncional(List<Punto2D> ls) {
		// Del enunciado
		return ls.stream().collect(
				Collectors.groupingBy(Punto2D::cuadrante, Collectors.reducing(0., x -> x.x(), (x, y) -> x + y)));
	}

	public static Map<Cuadrante, Double> solucionImperativa(List<Punto2D> ls) {
		Map<Cuadrante, Double> result = new HashMap<>();
		for (Punto2D punto : ls) {
			Cuadrante cuadrante = punto.cuadrante();
			double x = punto.x();
			result.put(cuadrante, result.getOrDefault(cuadrante, 0.0) + x);
		}
		return result;
	}
	

	public static Map<Cuadrante, Double> solucionRecursiva(List<Punto2D> ls) {
		return solucionRecursivaAux(ls, 0, new HashMap<>());
	}

	private static Map<Cuadrante, Double> solucionRecursivaAux(List<Punto2D> ls, int index,
			Map<Cuadrante, Double> result) {
		if (index == ls.size()) {
			return result;
		} else {
			Punto2D punto = ls.get(index);
			Cuadrante cuadrante = punto.cuadrante();
			double x = punto.x();
			result.put(cuadrante, result.getOrDefault(cuadrante, 0.0) + x);
			return solucionRecursivaAux(ls, index + 1, result);
		}
	}



	public static String fRNF_s(Integer a, Integer b, Integer c, String d) {
		if (a < 3 && b < 3) {
			return c.toString();
		} else if (a < 3) {
			return "~" + fRNF_s(a - 1, b - 1, c + 4, d);
		} else if (b < 3) {
			return "-" + c + "-" + fRNF_s(a - 2, b - 1, c, d + "x");
		} else {
			return "/" + d + "/" + fRNF_s(a - 1, b - 2, c + 1, d);
		}
	}

	public static String fRNF(Integer a, Integer b, Integer c, String d) {
		String res;
		if (a < 3 && b < 3) {
			res = c.toString(); // base
		} else if (a < 3 && b <= 3) {
			res = "~" + fRNF(a - 1, b - 1, c + 4, d); // "~" + acum (1)
		} else if (a >= 3 && b < 3) {
			res = "-" + c + "-" + fRNF(a - 2, b - 1, c, d + "x"); // "-" + c + "-" + acum (2)
		} else {
			res = "/" + d + "/" + fRNF(a - 1, b - 2, c + 1, d); // "/" + d + "/" + acum (3)
		}
		return res;
	}
	

	public static String fRNF_i(Integer a, Integer b, Integer c, String d) {
		StringBuilder result = new StringBuilder();
		boolean done = false;

		while (!done) {
			if (a < 3 && b < 3) {
				result.append(c.toString());
				done = true;
			} else if (a < 3) {
				result.append("~");
				a = a - 1;
				b = b - 1;
				c = c + 4;
			} else if (b < 3) {
				result.append("-").append(c).append("-");
				a = a - 2;
				b = b - 1;
				d = d + "x";
			} else {
				result.append("/").append(d).append("/");
				a = a - 1;
				b = b - 2;
				c = c + 1;
			}
		}

		return result.toString();
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

