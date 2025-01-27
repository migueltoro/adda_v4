package us.lsi.alg.cursos;

import us.lsi.common.IntegerSet;
import us.lsi.graphs.virtual.VirtualVertex;

public interface CursosVertex extends VirtualVertex<CursosVertex, CursosEdge, Integer> {
	
	Integer index();
	
	IntegerSet remaining();
	
	IntegerSet centers();
	
	public static CursosVertex first() {
		return CursosVertexI.first();
	}

}