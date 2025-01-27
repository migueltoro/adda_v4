package us.lsi.alg.equipo;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record EquipoEdge(EquipoVertex source, EquipoVertex target, Integer action, Double weight) implements SimpleEdgeAction<EquipoVertex, Integer> {

	public static EquipoEdge of(EquipoVertex v1, EquipoVertex v2, Integer a) {
		Double weight = DatosEquipo.getR(a,v1.index());
		return new EquipoEdge(v1, v2, a, weight);
	}

	public String toString() {
		return String.format("(%d,%d,%d,%.2f)", source.index(), target.index(), action, weight);
	}

}
