package us.lsi.alg.colorgraphs.manual;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.lsi.alg.colorgraphs.ColorVertexI;
import us.lsi.alg.colorgraphs.DatosColor;
import us.lsi.alg.colorgraphs.SolucionColor;

public class PDRColor {
	
	public static record Spm(Integer a,Integer weight) implements Comparable<Spm> {
		
		public static Spm of(Integer a, Integer weight) {
			return new Spm(a, weight);
		}
		
		@Override
		public int compareTo(Spm sp) {
			return this.weight.compareTo(sp.weight);
		}
	}
	
	public static PDRColor of() {
		return new PDRColor();
	}
	
	private Integer minValue;
	private SolucionColor solucion;
	private ColorVertexI start;
	private Map<ColorVertexI,Spm> memory;
	private Long time;
	
	private PDRColor() {
		super();
	}

	public Long time() {
		return time;
	}
	
	public void  pdr(ColorVertexI vertex,Integer minValue, SolucionColor s) {
		this.time = System.nanoTime();
		this.minValue = minValue;
		this.solucion = s;
		this.start = vertex;
		this.memory = new HashMap<>();
		pdr(start,memory);
		this.time = System.nanoTime() - this.time;
	}
	
	public Spm pdr(ColorVertexI vertex, Map<ColorVertexI,Spm> memory) {
		Spm r= null;
		if(memory.containsKey(vertex)) {
			r = memory.get(vertex);
		} else if(vertex.index() == DatosColor.n) {
			r = Spm.of(null,vertex.nc());
			memory.put(vertex,r);
			Integer accumulateValue = vertex.nc();
			if(this.minValue == null || accumulateValue > this.minValue) this.minValue = accumulateValue;
		} else {
			List<Spm> soluciones = new ArrayList<>();
			for(Integer a:vertex.actions()) {	
				Integer cota = vertex.neighbor(a).nc();
				if(this.minValue!= null && cota >= this.minValue) continue;				
				Spm s = pdr(vertex.neighbor(a),memory);
				if(s!=null) {
					Spm sp = Spm.of(a,s.weight());
					soluciones.add(sp);
				}
			}
			if(!soluciones.isEmpty()) {
				r = soluciones.stream().min(Comparator.naturalOrder()).orElse(null);
			 	memory.put(vertex,r);
			}
		}
		return r;
	}
	
	public SolucionColor solucion(){
		List<Integer> acciones = new ArrayList<>();
		ColorVertexI v = this.start;
		Spm s = this.memory.get(v);
		if(s==null) return this.solucion;
		while(s.a() != null) {
			acciones.add(s.a());
			v = v.neighbor(s.a());	
			s = this.memory.get(v);
		}
		return SolucionColor.of(acciones);
	}

	public static void main(String[] args) {
		
		DatosColor.data(4,"ficheros/andalucia.txt");
		
		PDRColor a = PDRColor.of();
		
		SolucionColor sv = GreedyColor.solucionVoraz(ColorVertexI.first());
		
		a.pdr(ColorVertexI.first(),sv.nc(),sv);
		System.out.println(a.time());
		a.pdr(ColorVertexI.first(),null,null);
		System.out.println(a.time());
		System.out.println("PDR = "+ a.solucion());
	}

	

}
