package us.lsi.alg.typ.manual;

import java.util.ArrayList;
import java.util.List;

import us.lsi.alg.typ.SolucionTyP;
import us.lsi.alg.typ.TyPVertex;
import us.lsi.common.List2;

public class StateTyP {

	TyPVertex vertice;
	Integer valorAcumulado;
	List<Integer> acciones;
	List<TyPVertex> vertices;

	public StateTyP(TyPVertex vertice, Integer valorAcumulado, List<Integer> acciones, List<TyPVertex> vertices) {
		super();
		this.vertice = vertice;
		this.valorAcumulado = valorAcumulado;
		this.acciones = acciones;
		this.vertices = vertices;
	}

	public static StateTyP of(TyPVertex vertex) {
		List<TyPVertex> vt = List2.of(vertex);
		return new StateTyP(vertex, 0, new ArrayList<>(), vt);
	}

	public void forward(Integer a) {
		this.acciones.add(a);
		TyPVertex vcn = this.vertice.neighbor(a);
		this.vertices.add(vcn);
		this.valorAcumulado = vcn.maxCarga().intValue();
		this.vertice = vcn;
	}

	public void back(Integer a) {
		this.acciones.remove(this.acciones.size() - 1);
		this.vertices.remove(this.vertices.size() - 1);
		this.vertice = this.vertices.get(this.vertices.size() - 1);
		this.valorAcumulado = this.vertice.maxCarga().intValue();
	}
	

	public SolucionTyP solucion() {
		return SolucionTyP.of(TyPVertex.first(), this.acciones);
	}

	public TyPVertex vertice() {
		return vertice;
	}
	
}
