package us.lsi.alg.pack;

import java.util.HashMap;
import java.util.Map;

import us.lsi.graphs.virtual.VirtualVertex;

public interface PackVertex extends VirtualVertex<PackVertex,PackEdge,Integer>{

	Integer nc();
	
	Integer index();
	
	Map<Integer, Integer> carga();

	Integer cMax();

	Integer cMin();
	
	public static PackVertex first() { 
		return PackVertexI.of(0, new HashMap<>()); 
	}
}