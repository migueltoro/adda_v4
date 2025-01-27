package us.lsi.p4.ej_3;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record AlumnosEdge(AlumnosVertex source, AlumnosVertex target, Integer action, Double weight) 
implements SimpleEdgeAction<AlumnosVertex,Integer> {

	public static AlumnosEdge of(AlumnosVertex v1, AlumnosVertex v2, Integer a) {	
		double afinidad = DatosAlumnos.getAfinidad(v1.index(), a);
		return new AlumnosEdge(v1, v2, a, afinidad);
	}

	@Override
	public String toString() {
		return String.format("%d; %.1f", action, weight);
	}

}