package us.lsi.alg.colorgraphs.manual2;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import us.lsi.alg.colorgraphs.ColorVertex;
import us.lsi.alg.colorgraphs.DatosColor;
import us.lsi.alg.colorgraphs.SolucionColor;

public class GreedyColor {
	
	public static Integer valorVoraz(ColorVertex v1) {
		ColorVertex v = v1;
		while (v.index() < DatosColor.n) {
			Integer a = v.greedyAction();
			v = v.neighbor(a);
		}
		return v.nc();
	}
	
	public static ColorVertex verticeVoraz(ColorVertex v1) {
		ColorVertex v = v1;
		while (v.index() < DatosColor.n) {
			Integer a = v.greedyAction();
			v = v.neighbor(a);
		}
		return v;
	}
	
	public static SolucionColor solucionVoraz(ColorVertex v1) {
		List<Integer> acciones = new ArrayList<>();
		ColorVertex v = v1;
		while (v.index() < DatosColor.n) {
			Integer a = v.greedyAction();
			acciones.add(a);
			v = v.neighbor(a);
		}
		return SolucionColor.of(acciones);
	}

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		
		DatosColor.data(9,"ficheros/andalucia/andalucia.txt");
		
		System.out.println("Voraz = "+verticeVoraz(ColorVertex.first()));
		
		System.out.println(solucionVoraz(ColorVertex.first()));

	}

}
