package us.lsi.alg.reinas.manual;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import us.lsi.alg.reinas.ReinasVertex;
import us.lsi.alg.reinas.ReinasVertexI;
import us.lsi.alg.reinas.SolucionReinas;
import us.lsi.common.List2;

public class ReinasBTRandom {
	
	public static ReinasBTRandom of(Integer nr) {
		return new ReinasBTRandom(nr);
	}
		
	private ReinasVertex start;
	private StateReinas estado;
	private Integer numeroDeSoluciones;
	private Set<SolucionReinas> soluciones;
	private Integer threshold;
	private Integer iteracionesMax;
	private Integer iteraciones;
	private Long time;
	
	private ReinasBTRandom(Integer nr) {
		super();
		ReinasVertexI.n = nr;
	}
	
	public Long time() {
		return time;
	}
	
	public void btr(ReinasVertex start,Integer numeroDeSoluciones,Integer threshold,Integer iteracionesMax) {
		this.time = System.nanoTime();
		this.start = start;
		this.estado = StateReinas.of(start);
		this.soluciones = new HashSet<>();
		this.threshold = threshold;
		this.iteracionesMax = iteracionesMax;
		this.iteraciones = 0;
		this.numeroDeSoluciones = numeroDeSoluciones;
		btr();
		this.time = System.nanoTime() - this.time;
	}

	private void btr() {
		do {
			if(this.iteraciones >= this.iteracionesMax) break;
			this.start = ReinasVertex.first();
			this.estado = StateReinas.of(this.start);
			this.soluciones = new HashSet<>();
			this.bt();
			this.iteraciones++;
		}while(this.soluciones.size()<this.numeroDeSoluciones);
	}
	
	private void bt() {
		if(this.estado.vertice().index() == ReinasVertex.n()) {
			SolucionReinas s = this.estado.solucion();
			if(s != null) this.soluciones.add(s);
		} else {
			List<Integer> alternativas = this.estado.vertice().actions();
			if(this.estado.vertice().size() > this.threshold) {
				alternativas = List2.randomUnitary(alternativas);
			}
			for(Integer a:alternativas) {	
				this.estado.forward(a);
				this.bt();  
				this.estado.back(a);
			}
		}
	}
	
	public Set<SolucionReinas> soluciones() {
		return this.soluciones;
	}
	
	public Integer iteraciones() {
		return iteraciones;
	}

	public static void main(String[] args) {
		ReinasBTRandom a = ReinasBTRandom.of(120);
		a.btr(ReinasVertex.first(),3,15,1000);	
		System.out.println("Time = "+a.time());
		System.out.println("Iteraciones = "+a.iteraciones());
		System.out.println(a.soluciones().size());
		System.out.println(a.soluciones().stream().map(s->s.toString()).collect(Collectors.joining("\n")));
	}

	

}
