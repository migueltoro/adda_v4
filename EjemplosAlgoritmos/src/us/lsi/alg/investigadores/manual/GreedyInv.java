package us.lsi.alg.investigadores.manual;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import us.lsi.alg.investigadores.DatosInv;
import us.lsi.alg.investigadores.InvVertex;
import us.lsi.alg.investigadores.SolucionInv;

public class GreedyInv {
	
	public static Integer valoVoraz(InvVertex v1) {
		InvVertex v = v1;
		while (v.index() < DatosInv.na) {
			Integer a = v.greedyEdge().action();
			v = v.neighbor(a);
		}
		return v.fo();
	}
	
	public static SolucionInv solucionVoraz(InvVertex v1) {
		List<Integer> acciones = new ArrayList<>();
		InvVertex v = v1;
		while (v.index() < DatosInv.na) {
			Integer a = v.greedyEdge().action();
			acciones.add(a);
			v = v.neighbor(a);
		}
		return SolucionInv.of(v1,acciones);
	}

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		
		DatosInv.iniDatos("ficheros/investigadores/inv1.txt");
		
		System.out.println("Voraz = "+valoVoraz(InvVertex.first()));
		
		System.out.println(solucionVoraz(InvVertex.first()));

	}


}
