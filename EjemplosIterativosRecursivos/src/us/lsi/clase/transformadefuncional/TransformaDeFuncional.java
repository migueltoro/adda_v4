package us.lsi.clase.transformadefuncional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TransformaDeFuncional {
	
	/**
	 * Genera un mapa donde las claves son las longitudes de las cadenas (menos 2) 
	 * y los valores son listas de cadenas.
	 * Cada cadena es una representación de números en una secuencia específica.
	 *
	 * @param a el entero inicial de la secuencia que debe ser positivo
	 * @param b el entero límite superior de la secuencia que debe ser mayor o igual que a
	 * @return un mapa con las longitudes de las cadenas como claves y listas de cadenas como valores
	 */
	
	public static Map<Integer, List<String>> exam(Integer a, Integer b) {
		assert a > 0 && b >= a: String.format("a = %d, b = %d", a, b);
		return Stream.iterate(a, c -> c <= b, d -> d + a + d%b)
				.filter(c -> c%10 != b%10)
				.map(d -> "(" + d + ")")
				.collect(Collectors.groupingBy(c -> c.length()-2));
	}
	
	
	public static Map<Integer, List<String>> examI(Integer a, Integer b) {
        assert a > 0 && b >= a : String.format("a = %d, b = %d", a, b);

        Map<Integer, List<String>> result = new HashMap<>();
        for (int i = a; i <= b; i += a + i % b) {
            if (i % 10 != b % 10) {
                String str = "(" + i + ")";
                Integer key = str.length() - 2;
                result.putIfAbsent(key, new ArrayList<>());
                result.get(key).add(str);
            }
        }
        return result;
    }
	

	public static Map<Integer, List<String>> examR(Integer a, Integer b) {
        assert a > 0 && b >= a : String.format("a = %d, b = %d", a, b);
        Map<Integer, List<String>> result = new HashMap<>();
        return examR(a, b, a, result);
    }

    private static Map<Integer, List<String>> examR(Integer a, Integer b, Integer current, Map<Integer, List<String>> result) {
        if (current > b) {
            return result;
        }

        if (current % 10 != b % 10) {
            String str = "(" + current + ")";
            Integer key = str.length() - 2;
            result.putIfAbsent(key, new ArrayList<>());
            result.get(key).add(str);
        }

        return examR(a, b, current + a + current % b, result);
    }


	public static void main(String[] args) {
		Integer a = 17; 
		Integer b = 20001;
		System.out.println(exam(a,b));
		System.out.println(examI(a,b));
		System.out.println(examR(a,b));
	}

}
