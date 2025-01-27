package us.lsi.alg.reinas.manual;

import java.util.ArrayList;
import java.util.List;

import us.lsi.alg.reinas.ReinasVertex;
import us.lsi.alg.reinas.SolucionReinas;

public class StateReinas {
	
	public static StateReinas of(ReinasVertex vertice) {
		return new StateReinas(vertice);
	}

	private ReinasVertex vertice;
	private List<ReinasVertex> vertices;
	
	private StateReinas(ReinasVertex vertice) {
		super();
		this.vertice = vertice;
		this.vertices = new ArrayList<>();
		this.vertices.add(vertice);
	}

	void forward(Integer a) {
		ReinasVertex nv = vertice.neighbor(a);
		this.vertices.add(nv);
		this.vertice = nv;
	}
	
	void back(Integer a) {
		this.vertices.remove(this.vertices.size()-1);
		this.vertice = this.vertices.get(this.vertices.size()-1);		
	}
	
	public SolucionReinas solucion() {
		return SolucionReinas.of(this.vertice);
	}

	public ReinasVertex vertice() {
		return vertice;
	}

}
