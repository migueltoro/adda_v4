package us.lsi.alg.typ.manual;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import us.lsi.alg.typ.DatosTyP;
import us.lsi.alg.typ.SolucionTyP;
import us.lsi.alg.typ.TyPVertex;

public class Heuristica {
	
	public static Integer heuristica(TyPVertex v1) {
		return v1.maxCarga().intValue();
	}
	
	public static Integer cota(TyPVertex v1, Integer a) {
		TyPVertex v2 = v1.neighbor(a);
		return v2.maxCarga().intValue();
	}

	public static Integer valorVoraz(TyPVertex v1) {
		TyPVertex v = v1;
		Integer r = 0;
		while (v.index() < DatosTyP.n) {
			Integer a = v.greedyAction();
			r = v.maxCarga().intValue();
			v = v.neighbor(a);
		}
		return r;
	}
	
	public static SolucionTyP solucionVoraz(TyPVertex v1) {
		TyPVertex v = v1;
		List<Integer> acciones = new ArrayList<>();
		while (v.index() < DatosTyP.n) {
			Integer a = v.greedyAction();
			acciones.add(a);
			v = v.neighbor(a);
		}
		return SolucionTyP.of(v,acciones);
	}
	
	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		DatosTyP.datos("ficheros/tareas.txt",5);
		TyPVertex e1 = TyPVertex.first();
		System.out.println(DatosTyP.tareas);
		Integer c = valorVoraz(e1);
		System.out.println(c);
		System.out.println(solucionVoraz(e1));
		System.out.println(DatosTyP.n+","+DatosTyP.m);
		System.out.println(SolucionTyP.of(e1,Arrays.asList(0, 1, 2, 3, 4, 3, 4, 0, 1, 2, 2, 4)));
	}
}
