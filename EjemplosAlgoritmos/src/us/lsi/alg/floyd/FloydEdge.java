package us.lsi.alg.floyd;

import java.util.List;
import us.lsi.hypergraphs.SimpleHyperEdge;

public record FloydEdge(FloydVertex source,List<FloydVertex> targets,Boolean action) 
        implements SimpleHyperEdge<FloydVertex,FloydEdge,Boolean>{
	
	public static FloydEdge of(FloydVertex source, List<FloydVertex> targets, Boolean action) {
		return new FloydEdge(source, targets, action);
	}

	public Double weight(List<Double> solutions) {
		Double weight = null;
		if(!action()) weight = solutions.get(0); 
		else weight = solutions.get(0)+solutions.get(1);		
		return weight;
	}
	
}
	
