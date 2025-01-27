package us.lsi.alg.typ.manual;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import us.lsi.alg.typ.DatosTyP;
import us.lsi.alg.typ.SolucionTyP;
import us.lsi.alg.typ.TyPVertexI;


public class TyPPD {

	public static record Sptp(Integer a,Integer weight) implements Comparable<Sptp> {
		
		public static Sptp of(Integer a, Integer weight) {
			return new Sptp(a, weight);
		}
		
		@Override
		public int compareTo(Sptp sp) {
			return this.weight.compareTo(sp.weight);
		}
	}
	
	public static TyPPD of() {
		return new TyPPD();
	}
	
	
	private TyPPD() {
		super();
	}

	private Integer minValue;
	private SolucionTyP solucion;
	private TyPVertexI start;
	private Map<TyPVertexI,Sptp> memory;
	private Long time;
	
	public Long time() {
		return time;
	}
	
	public SolucionTyP pdr(TyPVertexI start,Integer minValue, SolucionTyP s) {
		this.time = System.nanoTime();
		this.start = start;
		this.solucion = s;
		this.minValue = minValue;
		this.start = TyPVertexI.first();
		this.memory = new HashMap<>();
		pd(start,0,memory);
		this.time = System.nanoTime() - this.time;
		if(memory.get(start) != null) return this.solucion();
		else return this.solucion;
	}
	
	private Sptp pd(TyPVertexI vertex,Integer accumulateValue, Map<TyPVertexI,Sptp> memory) {
		Sptp r=null;
		if(memory.containsKey(vertex)) {
			r = memory.get(vertex);
		} else if(vertex.index() == DatosTyP.n) {
			r = Sptp.of(null,vertex.maxCarga().intValue());
			memory.put(vertex,r);
			if(this.minValue == null || accumulateValue < this.minValue) {
				this.minValue = accumulateValue;
			}
		} else {
			List<Sptp> soluciones = new ArrayList<>();
			for(Integer a:vertex.actions()) {	
				Integer cota = Heuristica.cota(vertex,a);
				if(this.minValue != null && cota >= this.minValue) continue;	
				TyPVertexI vecino = vertex.neighbor(a);
				Sptp s = pd(vecino,vecino.maxCarga().intValue(),memory);
				if(s!=null) {
					Sptp sp = Sptp.of(a,s.weight());
					soluciones.add(sp);
				}
			}
			if (!soluciones.isEmpty()) {
				r = soluciones.stream().min(Comparator.naturalOrder()).orElse(null);
				memory.put(vertex, r);
			}
		}
		return r;
	}
	
	public SolucionTyP solucion(){
		List<Integer> acciones = new ArrayList<>();
		TyPVertexI v = this.start;
		Sptp s = this.memory.get(v);
		while(s.a() != null) {
			acciones.add(s.a());
			v = v.neighbor(s.a());	
			s = this.memory.get(v);
		}
		return SolucionTyP.of(this.start,acciones);
	}
	
	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		DatosTyP.datos("ficheros/tareas.txt",5);
		DatosTyP.toConsole();
		TyPVertexI v1 = TyPVertexI.first();
		SolucionTyP s = Heuristica.solucionVoraz(v1);	
		TyPPD a = TyPPD.of();
		a.pdr(v1,s.getMaxCarga().intValue(),s);
		System.out.println("1 = "+a.time());
		a.pdr(v1,null,null);
		System.out.println("2 = "+a.time());
	}



}
