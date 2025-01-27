package us.lsi.alg.cursos.manual;

import java.util.ArrayList;
import java.util.List;

import us.lsi.alg.cursos.CursosVertexI;
import us.lsi.alg.cursos.DatosCursos;

public class StateCursos {
	
	private CursosVertexI vertice;
	private Double valorAcumulado;
	private List<Integer> acciones;
	private List<CursosVertexI> vertices;

	private StateCursos(CursosVertexI vertice, Double valorAcumulado, List<Integer> acciones,
			List<CursosVertexI> vertices) {
		super();
		this.vertice = vertice;
		this.valorAcumulado = valorAcumulado;
		this.acciones = acciones;
		this.vertices = vertices;
	}

	public static StateCursos of(CursosVertexI vertex) {
		List<CursosVertexI> vt = new ArrayList<>();
		vt.add(vertex);
		return new StateCursos(vertex, 0., new ArrayList<>(), vt);
	}

	void forward(Integer a) {
		this.acciones.add(a);
		CursosVertexI vcn = this.vertice().neighbor(a);
		this.vertices.add(vcn);
		this.valorAcumulado = this.valorAcumulado() + a*DatosCursos.getCoste(this.vertice.index());
		this.vertice = vcn;
	}

	void back(Integer a) {
		this.acciones.remove(this.acciones.size() - 1);
		this.vertices.remove(this.vertices.size() - 1);
		this.vertice = this.vertices.get(this.vertices.size() - 1);
		this.valorAcumulado = this.valorAcumulado() - a*DatosCursos.getCoste(this.vertice.index());
	}

	public CursosVertexI vertice() {
		return vertice;
	}

	public Double valorAcumulado() {
		return valorAcumulado;
	}

	public List<Integer> acciones() {
		return acciones;
	}
}
