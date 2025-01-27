package us.lsi.p1;

import java.util.HashMap;
import java.util.Map;

import us.lsi.common.IntPair;

public class Ejemplo3 {

	public static Integer solucionRecursivaSinMemoria(Integer a, Integer b) { // Definicion del enunciado
		Integer r = null;
		if (a < 2 || b < 2) {
			r = a*a + b;
		} else {
			r = solucionRecursivaSinMemoria(a/2, b-1) + solucionRecursivaSinMemoria(a/3, b-2);
		}
		return r;
	}

	public static Integer solucionRecursivaConMemoria(Integer a, Integer b) {
		return gRecConMem(a, b, new HashMap<>());
	}
	private static Integer gRecConMem(Integer a, Integer b, Map<IntPair, Integer> m) {
		Integer r = null;
		IntPair key = IntPair.of(a, b);
		if(m.containsKey(key)) {
			r = m.get(key);
		} else {
			if (a < 2 || b < 2) {
				r = a*a + b;
			} else {
				r = gRecConMem(a/2, b-1, m) + gRecConMem(a/3, b-2, m);
			}
			m.put(IntPair.of(a, b), r);
		}
		return r;
	}

	public static Integer solucionIterativa(Integer a, Integer b) {
		Map<IntPair, Integer> map = new HashMap<>();
		
		Integer v = null;
		for (int i = 0; i <= a; i++) {
			for (int j = 0; j <= b; j++) {
				if (i < 2 || j < 2) {
					v = i*i + j;
				} else {
					v = map.get(IntPair.of(i/2, j-1)) + map.get(IntPair.of(i/3, j-2));
				}
				map.put(IntPair.of(i, j), v);
			}
		}
		return map.get(IntPair.of(a, b));
	}

}

