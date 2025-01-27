package us.lsi.alg.investigadores.manual;

import java.util.ArrayList;
import java.util.List;

import us.lsi.alg.investigadores.InvVertex;
import us.lsi.alg.investigadores.SolucionInv;

public class StateInv {

	private InvVertex vertice;
	private Integer valorAcumulado;
	private List<Integer> acciones;
	private List<InvVertex> vertices;
	

	private StateInv(InvVertex vertice, Integer valorAcumulado, List<Integer> acciones,
			List<InvVertex> vertices) {
		super();
		this.vertice = vertice;
		this.valorAcumulado = valorAcumulado;
		this.acciones = acciones;
		this.vertices = vertices;
	}

	public static StateInv of(InvVertex vertex) {
		List<InvVertex> vt = new ArrayList<>();
		vt.add(vertex);
		return new StateInv(vertex, 0, new ArrayList<>(), vt);
	}

	void forward(Integer a) {
		this.acciones.add(a);
		InvVertex vcn = this.vertice().neighbor(a);
		this.vertices.add(vcn);
		this.vertice = vcn;
		this.valorAcumulado = this.vertice.fo();
	}

	void back(Integer a) {
		this.acciones.remove(this.acciones.size() - 1);
		this.vertices.remove(this.vertices.size() - 1);
		this.vertice = this.vertices.get(this.vertices.size() - 1);
		this.valorAcumulado = this.vertice.fo();
	}

	SolucionInv solucion() {
		return SolucionInv.of(InvVertex.first(),this.acciones);
	}

	public InvVertex vertice() {
		return vertice;
	}

	public Integer valorAcumulado() {
		return valorAcumulado;
	}

}
