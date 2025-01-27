package us.lsi.alg.investigadores.manual;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import us.lsi.alg.investigadores.DatosInv;
import us.lsi.alg.investigadores.InvVertex;
import us.lsi.alg.investigadores.SolucionInv;

public class PDRInv {

public static record Spm(Integer a,Integer weight) implements Comparable<Spm> {
		
		public static Spm of(Integer a, Integer weight) {
			return new Spm(a, weight);
		}
		
		@Override
		public int compareTo(Spm sp) {
			return this.weight.compareTo(sp.weight);
		}
	}
	
	public static PDRInv of() {
		return new PDRInv();
	}
	
	private Integer maxValue;
	private SolucionInv solucion;
	private InvVertex start;
	private Map<InvVertex,Spm> memory;
	private Long time;
	
	private PDRInv() {
		super();
	}

	public Long time() {
		return time;
	}
	
	public void  pdr(InvVertex vertex,Integer maxValue, SolucionInv s) {
		this.time = System.nanoTime();
		this.maxValue = maxValue;
		this.solucion = s;
		this.start = vertex;
		this.memory = new HashMap<>();
		pdr(start,memory);
		this.time = System.nanoTime() - this.time;
	}
	
	public Spm pdr(InvVertex vertex, Map<InvVertex, Spm> memory) {
		Spm r = null;
		if (memory.containsKey(vertex)) {
			r = memory.get(vertex);
		} else if (vertex.index() == DatosInv.na) {
			if (IntStream.range(0, DatosInv.m).boxed()
					.allMatch(t -> vertex.esTrabajoAcabado(t) || !vertex.esTrabajoIniciado(t)))
				r = Spm.of(null, vertex.fo());
			else
				r = null;
			memory.put(vertex, r);
			Integer accumulateValue = vertex.fo();
			if (this.maxValue == null || accumulateValue < this.maxValue)
				this.maxValue = accumulateValue;
		} else {
			List<Spm> soluciones = new ArrayList<>();
			for (Integer a : vertex.actions()) {
				Integer cota = vertex.neighbor(a).fo();
				if (this.maxValue != null && cota <= this.maxValue)
					continue;
				Spm s = pdr(vertex.neighbor(a), memory);
				if (s != null) {
					Spm sp = Spm.of(a, s.weight());
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
	
	public SolucionInv solucion(){
		List<Integer> acciones = new ArrayList<>();
		InvVertex v = this.start;
		Spm s = this.memory.get(v);
		if(s==null) return this.solucion;
		while(s.a() != null) {
			acciones.add(s.a());
			v = v.neighbor(s.a());	
			s = this.memory.get(v);
		}
		return SolucionInv.of(this.start,acciones);
	}

	public static void main(String[] args) {
		
		DatosInv.iniDatos("ficheros/investigadores/inv2.txt");
		
		PDRInv a = PDRInv.of();
		
		SolucionInv sv = GreedyInv.solucionVoraz(InvVertex.first());
		System.out.println(sv);
		a.pdr(InvVertex.first(),sv.fo(),sv);
		System.out.println("PDR = "+ a.solucion());
		System.out.println(a.time());
//		a.pdr(InvVertex.first(),null,null);
//		System.out.println(a.time());
//		System.out.println("PDR = "+ a.solucion());
	}

	


}
