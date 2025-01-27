package us.lsi.alg.cursos;

import java.util.List;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;

import us.lsi.alg.cursos.DatosCursos.Curso;
import us.lsi.common.IntegerSet;
import us.lsi.common.List2;

public class SolucionCursos {
	
	public static SolucionCursos of(List<Integer> ls) {
		return new SolucionCursos(ls);
	}
	
	public static SolucionCursos of(GraphPath<CursosVertex, CursosEdge> path) {
		List<Integer> ls = path.getEdgeList().stream().map(e -> e.action()).toList();
		SolucionCursos res = of(ls);
		return res;
	}
	
	private Double precio;
	private List<Curso> cursos;	
	private IntegerSet centros;
	
	private SolucionCursos(List<Integer> ls) {
		this.precio = 0.;
		this.cursos = List2.empty();
		this.centros = IntegerSet.empty();
		for(int i=0; i<ls.size(); i++) {
			if(ls.get(i)>0) {
				this.precio += DatosCursos.getCoste(i);
				this.cursos.add(DatosCursos.getCurso(i));
				this.centros = this.centros.addF(DatosCursos.getCentro(i));
			}
		}
	}
	
	public Double precio() {
		return precio;
	}

	public List<Curso> cursos() {
		return cursos;
	}
	
	@Override
	public String toString() {
		String s = this.cursos.stream().map(e -> "S"+e.id())
		.collect(Collectors.joining(", ", "Cursos elegidos: {", "}\n"));
		String res = String.format("%sCoste Total: %.1f, numCentros = %d", s, precio, centros.size());
		return String.format("%s", res);
	}
	
}
