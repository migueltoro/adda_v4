package us.lsi.alg.monedas.manual;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import us.lsi.alg.monedas.DatosMonedas;
import us.lsi.alg.monedas.MonedasVertex;
import us.lsi.alg.monedas.SolucionMonedas;
import us.lsi.common.String2;

public class MonedasHeuristica {
	
	public static Double heuristic(Integer index, Double valorRestante, Integer lastIndex) {
		Double p = 0.;
		if(index==lastIndex)return 0.;
		while (valorRestante> 0 && index < lastIndex) {
			Double a = valorRestante / DatosMonedas.valor(index);
			p = p + a * DatosMonedas.peso(index);
			valorRestante = valorRestante - a * DatosMonedas.valor(index);
			index = index + 1;
		}
		return p;
	}
	
	public static Double heuristic2(Integer index, Double valorRestante, Integer lastIndex) {
		Double p = 0.;
		if (index == lastIndex) return 0.;
		Double a = valorRestante / DatosMonedas.valor(index);
		p = p + a * DatosMonedas.peso(index);
		return p;
	}

	public static Double cota(MonedasVertex vertex, Integer a) {
		MonedasVertex nv = vertex.neighbor(a);
		return a*DatosMonedas.peso(vertex.index())+
				heuristic(nv.index(),nv.valorRestante().doubleValue(),DatosMonedas.n);
	}
	
	public static SolucionMonedas solucionVoraz(MonedasVertex v1) {	
		List<Integer> acciones = new ArrayList<>();
		Integer valorRestante = v1.valorRestante();
		Integer index = 0;
		while(valorRestante > 0 && index < DatosMonedas.n) {
			Integer a = valorRestante / DatosMonedas.valor(index);
			acciones.add(a);
			valorRestante = valorRestante - a * DatosMonedas.valor(v1.index());
			index++;
		}
		if(valorRestante == 0) return SolucionMonedas.of(acciones);
		else return null;
	}
	
	public static SolucionMonedas solucionVoraz2(MonedasVertex v1) {	
		SolucionMonedas s = null;
		for(Integer i=0;true;i++) {
			s = solucionVoraz(MonedasVertex.first(v1.valorRestante()-i));
			if(s!=null) break;
		}
		return s;
	}

	
	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		DatosMonedas.datosIniciales("ficheros/monedas2.txt");
		Integer valorInicial = 401;
		String2.toConsole("%s",DatosMonedas.n);
		String2.toConsole("%s",DatosMonedas.monedas);
		MonedasVertex v1 = MonedasVertex.first(valorInicial);
		SolucionMonedas s = MonedasHeuristica.solucionVoraz2(v1);
		Double h = MonedasHeuristica.heuristic(v1.index(),v1.valorRestante().doubleValue(),DatosMonedas.n);
		Double h2 = MonedasHeuristica.heuristic2(v1.index(),v1.valorRestante().doubleValue(),DatosMonedas.n);
		String2.toConsole("%s",s);
		String2.toConsole("%s,%s,%s",s!=null?s.peso():null,h,h2);
	}
	
}
