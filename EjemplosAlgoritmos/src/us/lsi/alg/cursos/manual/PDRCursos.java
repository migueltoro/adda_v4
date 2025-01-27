package us.lsi.alg.cursos.manual;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import us.lsi.alg.cursos.CursosVertexI;
import us.lsi.alg.cursos.DatosCursos;
import us.lsi.alg.cursos.SolucionCursos;

public class PDRCursos {
	
public static record Spm(Integer a,Integer weight) implements Comparable<Spm> {
		
		public static Spm of(Integer a, Integer weight) {
			return new Spm(a, weight);
		}
		
		@Override
		public int compareTo(Spm sp) {
			return this.weight.compareTo(sp.weight);
		}
	}
	
	public static PDRCursos of() {
		return new PDRCursos();
	}
	
	private Integer minValue;
	private CursosVertexI start;
	private Map<CursosVertexI,Spm> memory;
	private SolucionCursos solucion;
	private Long time;
	
	private PDRCursos() {
		super();
	}
	
	public Long time() {
		return time;
	}
	
	public SolucionCursos pd(Integer minValue, SolucionCursos s) {
		this.time = System.nanoTime();
		this.minValue = minValue;
		this.start = CursosVertexI.first();
		this.solucion = s;
		this.memory = new HashMap<>();
		pd(start,0,memory);
		SolucionCursos r = this.solucion();
		this.time = System.nanoTime() - this.time;
		return r;
	}
	
	private Spm pd(CursosVertexI vertex,Integer accumulateValue, Map<CursosVertexI,Spm> memory) {
		Spm r=null;
		if(memory.containsKey(vertex)) {
			r = memory.get(vertex);
		} else if(vertex.index() == DatosCursos.n && 
				vertex.remaining().isEmpty() &&
				vertex.centers().size() <= DatosCursos.maxCentros) {
			r = Spm.of(null,0);
			memory.put(vertex,r);
			if(this.minValue == null || accumulateValue < this.minValue) this.minValue = accumulateValue;
		} else {
			List<Spm> soluciones = new ArrayList<>();
			for(Integer a:vertex.actions()) {	
				Double cota = accumulateValue + GreedyCursos.cota(vertex,a);
				if(this.minValue != null && cota >= this.minValue) continue;				
				Spm s = pd(vertex.neighbor(a),(accumulateValue+a*DatosCursos.getCoste(vertex.index()).intValue()),memory);
				if(s!=null) {
					Spm sp = Spm.of(a,(s.weight()+a*DatosCursos.getCoste(vertex.index()).intValue()));
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
	
	public SolucionCursos solucion(){
		List<Integer> acciones = new ArrayList<>();
		CursosVertexI v = CursosVertexI.first();
		Spm s = this.memory.get(v);
		if(s == null) return this.solucion;
		while(s.a() != null) {
			acciones.add(s.a());
			v = v.neighbor(s.a());	
			s = this.memory.get(v);
		}
		return SolucionCursos.of(acciones);
	}
	
	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		DatosCursos.iniDatos("ficheros/cursos/cursos3.txt");
		CursosVertexI v1 = CursosVertexI.first();
		SolucionCursos sv = GreedyCursos.solucionVoraz(v1);
		System.out.println(sv);
		PDRCursos a = PDRCursos.of();
		SolucionCursos s = a.pd(null,null);
		System.out.println(a.time() + "  === \n"+s);
		s = a.pd(sv.precio().intValue(),s);
		System.out.println(a.time() + "  === \n"+s);	
	}


}
