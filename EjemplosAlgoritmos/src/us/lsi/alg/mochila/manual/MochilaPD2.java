package us.lsi.alg.mochila.manual;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import us.lsi.alg.mochila.MochilaVertex;
import us.lsi.alg.mochila.SolucionMochila;


import us.lsi.mochila.datos.DatosMochila;

public class MochilaPD2 {
	
	public static record Spm(Integer a,Double weight) implements Comparable<Spm> {
		
		public static Spm of(Integer a, Double weight) {
			return new Spm(a, weight);
		}
		
		@Override
		public int compareTo(Spm sp) {
			return this.weight.compareTo(sp.weight);
		}
	}
	
	public static record ActionSp(Integer a, Spm sp)  {
		
		public static ActionSp of(Integer a, Spm sp) {
			return new ActionSp(a, sp);
		}
		
	}
	
	public static MochilaPD2 of() {
		return new MochilaPD2();
	}
	
	private Integer maxValue;
	private MochilaVertex start;
	private Map<MochilaVertex,Spm> memory;
	private SolucionMochila solucion;
	private Long time;
	
	private MochilaPD2() {
		super();
	}
	
	public Long time() {
		return time;
	}
	
	public SolucionMochila pd(Integer initialCapacity, Integer maxValue, SolucionMochila s) {
		this.time = System.nanoTime();
		this.maxValue = maxValue;
		this.start = MochilaVertex.of(0,initialCapacity);
		this.solucion = s;
		this.memory = new HashMap<>();
		pd(start,0,memory);
		SolucionMochila r = this.solucion();
		this.time = System.nanoTime() - this.time;
		return r;
	}
	
	private Spm pd(MochilaVertex vertex,Integer accumulateValue, Map<MochilaVertex,Spm> memory) {
		Spm r=null;
		if(memory.containsKey(vertex)) {
			r = memory.get(vertex);
			if(this.maxValue == null || (r != null && (accumulateValue+r.weight()) > this.maxValue)) this.maxValue = accumulateValue;
		} else if(vertex.index() == DatosMochila.n) {
			r = Spm.of(null,0.);
			memory.put(vertex,r);
			if(this.maxValue == null || accumulateValue > this.maxValue) this.maxValue = accumulateValue;
		} else {
			List<Spm> soluciones = vertex.actions().stream()
					.filter(a->accumulateValue + Heuristica.cota(vertex,a) > this.maxValue)
					.map(a->ActionSp.of(a,pd(vertex.neighbor(a),accumulateValue+vertex.edge(a).weight().intValue(),memory)))
					.filter(p->p.sp() !=null)
					.map(p->Spm.of(p.a(),p.sp().weight()+vertex.edge(p.a()).weight()))
					.toList();			
			r = soluciones.stream().max(Comparator.naturalOrder()).orElse(null);
			memory.put(vertex, r);			
		}
		return r;
	}
	
	public SolucionMochila solucion(){
		List<Integer> acciones = new ArrayList<>();
		MochilaVertex v = this.start;
		Spm s = this.memory.get(v);
		if(s == null) return this.solucion;
		while(s.a() != null) {
			acciones.add(s.a());
			v = v.neighbor(s.a());	
			s = this.memory.get(v);
		}
		return SolucionMochila.of(acciones);
	}
	
	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		DatosMochila.iniDatos("ficheros/mochila/objetosMochila.txt");
		DatosMochila.capacidadInicial = 78;
		MochilaVertex v1 = MochilaVertex.of(0, DatosMochila.capacidadInicial);
		SolucionMochila s = Heuristica.solucionVoraz(v1);	
		MochilaPD2 a = MochilaPD2.of();
		a.pd(DatosMochila.capacidadInicial,Integer.MIN_VALUE,null);	
		System.out.println(a.time + " === "+  a.solucion());	
		a.pd(DatosMochila.capacidadInicial,s.valor(),s);	
		System.out.println(a.time + " === "+  a.solucion());
	}


}

