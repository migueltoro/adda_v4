package us.lsi.alg.cursos;


import us.lsi.graphs.virtual.SimpleEdgeAction;

public record CursosEdge(CursosVertex source, CursosVertex target, Integer action, 
		Double weight) implements SimpleEdgeAction<CursosVertex,Integer> {

	public static CursosEdge of(CursosVertex s, CursosVertex t, Integer a) {
		return new CursosEdge(s, t, a, a*DatosCursos.getCoste(s.index()));
	}
	
	@Override
	public String toString() {
		return action+"("+weight+")";
	}
}
