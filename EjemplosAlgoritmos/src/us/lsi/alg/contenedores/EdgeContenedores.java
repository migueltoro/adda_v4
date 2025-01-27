package us.lsi.alg.contenedores;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record EdgeContenedores(VertexContenedores source, VertexContenedores target, Integer action, Double weight) 
	implements SimpleEdgeAction<VertexContenedores,Integer> {

		public static EdgeContenedores of(VertexContenedores v1, VertexContenedores v2, Integer a) {
			double peso = (v1.contenedoresCompletos().size()<v2.contenedoresCompletos().size()?1.:0.);
			return new EdgeContenedores(v1, v2, a, peso);
		}	
}
