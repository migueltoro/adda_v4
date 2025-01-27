package us.lsi.alg.colorgraphs.manual;

import java.util.ArrayList;
import java.util.List;

import us.lsi.alg.colorgraphs.ColorVertexI;
import us.lsi.alg.colorgraphs.SolucionColor;

public class StateColor {
	
	private ColorVertexI vertice;
	private Integer valorAcumulado;
	private List<Integer> acciones;
	private List<ColorVertexI> vertices;
	

	private StateColor(ColorVertexI vertice, Integer valorAcumulado, List<Integer> acciones,
			List<ColorVertexI> vertices) {
		super();
		this.vertice = vertice;
		this.valorAcumulado = valorAcumulado;
		this.acciones = acciones;
		this.vertices = vertices;
	}

	public static StateColor of(ColorVertexI vertex) {
		List<ColorVertexI> vt = new ArrayList<>();
		vt.add(vertex);
		return new StateColor(vertex, 0, new ArrayList<>(), vt);
	}

	void forward(Integer a) {
		this.acciones.add(a);
		ColorVertexI vcn = this.vertice().neighbor(a);
		this.vertices.add(vcn);
		this.vertice = vcn;
		this.valorAcumulado = this.vertice.nc();
	}

	void back(Integer a) {
		this.acciones.remove(this.acciones.size() - 1);
		this.vertices.remove(this.vertices.size() - 1);
		this.vertice = this.vertices.get(this.vertices.size() - 1);
		this.valorAcumulado = this.vertice.nc();
	}

	SolucionColor solucion() {
		return SolucionColor.of(this.acciones);
	}

	public ColorVertexI vertice() {
		return vertice;
	}

	public Integer valorAcumulado() {
		return valorAcumulado;
	}


}
