package us.lsi.p1;

import java.util.stream.Stream;

public class Ejemplo2 {
	
	public static String solucionRecursivaNoFinal(Integer a, Integer b) { // Definicion del enunciado
		String r =  null;
		if (a < 5 || b < 5) {
			r = String.format("(%d)", a * b);
		} else {
			r = String.format("%d", a + b) + solucionRecursivaNoFinal(a/2, b-2);
		}
		return r;
	}

	public static String solucionRecursivaFinal(Integer a, Integer b) { 
		return recFinal("", a, b);
	}	
	
	private static String recFinal(String ac, Integer a, Integer b) {
		String r =  null;
		if (a < 5 || b < 5) {
			r = ac + String.format("(%d)", a * b);
		} else {
			r = recFinal(ac + String.format("%d", a + b), a/2, b-2);
		}
		return r;
	}
	
	public static String solucionIterativa(Integer a, Integer b) {
		String ac = "";
		while (!(a < 5 || b < 5)) {
			ac = String.format("%s%d", ac, a + b);
			a /= 2;
			b -= 2;
		}
		return ac + String.format("(%d)", a * b);
	}
	
	private static record Tupla(String ac, Integer a, Integer b) {
		public static Tupla of(String ba, Integer a, Integer b) {
			return new Tupla(ba, a, b);
		}
		
		public static Tupla first(Integer a, Integer b) {
			return of("", a, b);
		}
		
		public Tupla next() {
			return of(ac + String.format("%d", a + b), a/2, b-2);
		}
	}
	
	public static String solucionFuncional(Integer a, Integer b) {
		Tupla t = Stream.iterate(Tupla.first(a, b),e -> e.next())
				.filter(e -> e.a()<5 || e.b()<5).findFirst().get();
		
		return  t.ac() + String.format("(%d)", t.a() * t.b());
	}
	
	

}

