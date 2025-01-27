package us.lsi.alg.monedas.manual;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import us.lsi.alg.monedas.DatosMonedas;
import us.lsi.alg.monedas.MonedasVertex;
import us.lsi.alg.monedas.SolucionMonedas;
import us.lsi.common.String2;

public class MonedaPD {
	
	public static record Spm(Integer a,Integer weight) implements Comparable<Spm> {
		
		public static Spm of(Integer a, Integer weight) {
			return new Spm(a, weight);
		}
		
		@Override
		public int compareTo(Spm sp) {
			return this.weight.compareTo(sp.weight);
		}
	}
	
	public static MonedaPD of() {
		return new MonedaPD();
	}
	
	private Integer maxValue;
	private MonedasVertex start;
	public Map<MonedasVertex,Spm> memory;
	private SolucionMonedas solucion;
	private Long time;
	
	private MonedaPD() {
		super();
	}
	
	public Long time() {
		return time;
	}

	public SolucionMonedas pd(Integer initialValue,Integer maxValue,SolucionMonedas s) {
		this.time = System.nanoTime();
		this.maxValue = maxValue;
		this.solucion = s;
		this.start = MonedasVertex.first(initialValue);
		this.memory = new HashMap<>();
		pd(start,0,memory);
		this.time = System.nanoTime() - this.time;
		return this.solucion();
	}
	
	private Spm pd(MonedasVertex vertex,Integer accumulateValue, Map<MonedasVertex,Spm> memory) {
		Spm r=null;
		if(memory.containsKey(vertex)) {
			r = memory.get(vertex);
		} else if(vertex.index() == DatosMonedas.n) {
			if (vertex.goalHasSolution()) {
				r = Spm.of(null, 0);
				memory.put(vertex, r);
				if (this.maxValue == null || accumulateValue > this.maxValue) this.maxValue = accumulateValue;
			} else {
				memory.put(vertex,null);
			}
		} else {
			List<Spm> soluciones = new ArrayList<>();
			for(Integer a:vertex.actions()) {	
				Double cota = accumulateValue + MonedasHeuristica.cota(vertex,a);
				if(this.maxValue != null && cota <= this.maxValue) continue;	
				Integer ac = accumulateValue+a*DatosMonedas.peso(vertex.index());
				Spm s = pd(vertex.neighbor(a),ac,memory);
				if(s!=null) {
					Spm sp = Spm.of(a,s.weight()+a*DatosMonedas.peso(vertex.index()));
					soluciones.add(sp);
				}
			}
			if (!soluciones.isEmpty()) {
				r = soluciones.stream().filter(s -> s != null).max(Comparator.naturalOrder()).orElse(null);
				memory.put(vertex, r);
			}
		}
		return r;
	}
	
	public SolucionMonedas solucion(){
		List<Integer> acciones = new ArrayList<>();
		MonedasVertex v = this.start;
		Spm s = this.memory.get(v);
		if(s==null && this.solucion!=null) return this.solucion;
		if(s==null) return null;
		while(s.a() != null) {
			acciones.add(s.a());
			v = v.neighbor(s.a());	
			s = this.memory.get(v);
		}
		return SolucionMonedas.of(acciones);
	}
	
	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		DatosMonedas.datosIniciales("ficheros/monedas/monedas2.txt");
		Integer valorInicial = 401;
		String2.toConsole("%s",DatosMonedas.n);
		String2.toConsole("%s",DatosMonedas.monedas);
		MonedasVertex v1 = MonedasVertex.first(valorInicial);
		SolucionMonedas s1 = MonedasHeuristica.solucionVoraz(v1);
		SolucionMonedas s2 = MonedasHeuristica.solucionVoraz2(v1);
		MonedaPD a = MonedaPD.of();
//		a.pd(DatosMonedas.valorInicial,s!=null?s.peso():null,s);	
//		System.out.println(a.time());
//		System.out.println(a.solucion());
		String2.toConsole("1");
		a.pd(valorInicial,s2.peso(),s1);	
		String2.toConsole("2");
		System.out.println(a.time());
		System.out.println(a.solucion());
	}

	

}
