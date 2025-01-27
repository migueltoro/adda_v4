package us.lsi.graphs.examples;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

public class RedSocial {
	
	public static <V> Set<V> sinAmigos(Graph<V,Integer> graph){
		return graph.vertexSet().stream().filter(v->graph.degreeOf(v)==0).collect(Collectors.toSet());
	}
	
	public static <V> List<V> listaMasCorta(Graph<V,Integer> graph, V e1, V e2){
		ShortestPathAlgorithm<V,Integer> a = new DijkstraShortestPath<V,Integer>(graph);
		GraphPath<V,Integer> gp =  a.getPath(e1,e2);
		return gp.getVertexList();
	}

	public static void main(String[] args) {

	}

}
