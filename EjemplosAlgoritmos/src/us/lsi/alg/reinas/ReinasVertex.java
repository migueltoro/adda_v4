package us.lsi.alg.reinas;

import java.util.List;

import us.lsi.common.IntegerSet;
import us.lsi.common.List2;
import us.lsi.graphs.virtual.SimpleEdgeAction;
import us.lsi.graphs.virtual.VirtualVertex;

public interface ReinasVertex extends 
	VirtualVertex<ReinasVertex,SimpleEdgeAction<ReinasVertex,Integer>,Integer> {
	
	Integer index();
	
	List<Integer> fo();
	
	IntegerSet dpo();
	
	IntegerSet dso();

	Integer errores();

	Integer size();
	
	public static  Integer n() {
		return ReinasVertexI.n;
	}
	
	public static ReinasVertex first() {
		return new ReinasVertexI(0,List2.empty(),IntegerSet.empty(-ReinasVertexI.n,10),IntegerSet.empty());
	}

}