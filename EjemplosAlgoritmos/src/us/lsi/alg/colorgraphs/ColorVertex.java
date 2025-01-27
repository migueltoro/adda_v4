package us.lsi.alg.colorgraphs;

import java.util.HashMap;
import java.util.Map;

import us.lsi.common.IntegerSet;
import us.lsi.graphs.virtual.VirtualVertex;

public interface ColorVertex extends VirtualVertex<ColorVertex,ColorEdge,Integer> {

	Integer index();
	
	Map<Integer,Integer> cav();
	
	IntegerSet ca();

	Integer nc();

	IntegerSet cv();
	
	public static ColorVertex first() {
		return new ColorVertexI(0,new HashMap<>());
	}

}