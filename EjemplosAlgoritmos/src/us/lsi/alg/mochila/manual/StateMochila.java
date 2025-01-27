package us.lsi.alg.mochila.manual;

import java.util.ArrayList;
import java.util.List;

import us.lsi.alg.mochila.MochilaVertexI;
import us.lsi.mochila.datos.DatosMochila;

	
public class StateMochila {
	private MochilaVertexI vertice;
	private Integer valorAcumulado;
	private List<Integer> acciones;
	private List<MochilaVertexI> vertices;

	private StateMochila(MochilaVertexI vertice, Integer valorAcumulado, List<Integer> acciones,
			List<MochilaVertexI> vertices) {
		super();
		this.vertice = vertice;
		this.valorAcumulado = valorAcumulado;
		this.acciones = acciones;
		this.vertices = vertices;
	}

	public static StateMochila of(MochilaVertexI vertex) {
		List<MochilaVertexI> vt = new ArrayList<>();
		vt.add(vertex);
		return new StateMochila(vertex, 0, new ArrayList<>(), vt);
	}

	void forward(Integer a) {
		this.acciones.add(a);
		MochilaVertexI vcn = this.vertice().neighbor(a);
		this.vertices.add(vcn);
		this.valorAcumulado = this.valorAcumulado() + a * DatosMochila.getValor(this.vertice().index());
		this.vertice = vcn;
	}

	void back(Integer a) {
		this.acciones.remove(this.acciones.size() - 1);
		this.vertices.remove(this.vertices.size() - 1);
		this.vertice = this.vertices.get(this.vertices.size() - 1);
		this.valorAcumulado = this.valorAcumulado() - a * DatosMochila.getValor(this.vertice.index());
	}

	public MochilaVertexI vertice() {
		return vertice;
	}

	public Integer valorAcumulado() {
		return valorAcumulado;
	}

	public List<Integer> acciones() {
		return acciones;
	}

}
