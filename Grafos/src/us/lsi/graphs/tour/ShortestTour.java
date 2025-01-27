package us.lsi.graphs.tour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.HamiltonianCycleAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.tour.HeldKarpTSP;
import org.jgrapht.graph.GraphWalk;

import us.lsi.common.Set2;

public class ShortestTour<V, E, G extends Graph<V, E>> {
	
	/**
	 * @param <V> Tipo del vértice
	 * @param <E> Tipo de la arista
	 * @param <G> Tipo del grafo
	 * @param graph Grafo de entrada
	 * @param creator Un creador de grafos
	 * @param edgeCreator Un creador de aristas
	 * @return Un algoritmo para calcular rutas minimas
	 */
	public static <V, E, G extends Graph<V, E>> ShortestTour<V, E, G> of(
			G graph, 
			Supplier<G> creator,
			Supplier<E> edgeCreator) {
		return new ShortestTour<V, E, G>(graph, creator, edgeCreator);
	}
	
	private G graph;
	private Supplier<G> creator;
	private Supplier<E> edgeCreator;

	private ShortestTour(G graph, Supplier<G> creator, Supplier<E> edgeCreator) {
		super();
		this.graph = graph;
		this.creator = creator;
		this.edgeCreator = edgeCreator;
	}
	
	private static <V1,E1> Double walkWeightCalculator(GraphWalk<V1,E1> path) {
		return path.getEdgeList().stream().mapToDouble(x->path.getGraph().getEdgeWeight(x)).sum();
	}


	
	/**
	 * @param startVertex Vertice inicial
	 * @param endVertex Vertice final
	 * @param vertices Vertices por los que debe pasar el camino
	 * @return Camino minimo abierto de statVertex hasta endVertex pasando por el conjunto de vertices indicados
	 */
	public GraphPath<V,E> getTour( 
			V startVertex, 
			V endVertex,
			Set<V> vertices) {
		return getTour(startVertex,endVertex,vertices,false);
	}
	
	/**
	 * @param startVertex Vertice inicial
	 * @param endVertex Vertice final
	 * @param vertices Vertices por los que debe pasar el camino
	 * @param closed Si el camino es cerrado o no
	 * @return Camino minimo de statVertex hasta endVertex pasando por el conjunto de vertices indicados
	 */
	public GraphPath<V,E> getTour( 
			V startVertex, 
			V endVertex,
			Set<V> vertices,
			Boolean closed) {
		
		G r = creator.get();
		Map<VerticesPar<V>,GraphPath<V,E>>  shortestPath = new HashMap<>();
	
		List<V> nVertices = new ArrayList<>();
		nVertices.add(startVertex);
		nVertices.addAll(vertices);
		nVertices.add(endVertex);
		
		nVertices.stream().forEach(x -> r.addVertex(x));
		
		DijkstraShortestPath<V,E> a = new DijkstraShortestPath<V,E>(graph);
		
		for (V v1 : r.vertexSet()) {
			for (V v2 : r.vertexSet()) {
				if (!v1.equals(v2)) {
					if (!r.containsEdge(v1, v2)) {					
						GraphPath<V,E> gp = a.getPath(v1, v2);
						shortestPath.put(VerticesPar.of(v1,v2), gp);
//						Double weight = gp.getWeight();	
//						if(!closed && VerticesPar.of(v1,v2).equals(VerticesPar.of(startVertex,endVertex))) 
//							weight = 0.;;
						E e = edgeCreator.get();
						r.addEdge(v1, v2, e);
					}
				}
			}
		}
		HamiltonianCycleAlgorithm<V,E> a2 = new HeldKarpTSP<>();
		List<V> gp = a2.getTour(r).getVertexList();	
		GraphWalk<V,E> gwa = null;
		for(int i=0; i<gp.size()-1;i++) {
			VerticesPar<V> vp = VerticesPar.of(gp.get(i), gp.get(i+1));
			if(!closed && vp.equals(VerticesPar.of(startVertex,endVertex))) continue;
			GraphPath<V,E> gpt = shortestPath.get(vp);
			GraphWalk<V,E> gw = new GraphWalk<V,E>(graph,gpt.getVertexList(),gpt.getWeight());
			if(gwa == null) {				
				gwa = gw;
			} else {
				if(!gwa.getEndVertex().equals(gw.getStartVertex()))
					gw = gw.reverse();
				gwa = gwa.concat(gw,ShortestTour::walkWeightCalculator);
			}
		}
		return gwa;
	}
	
	public static class VerticesPar<V> {	
		public V a;
		public V b;
		private Set<V> vertices;
		public static <V> VerticesPar<V> of(V a, V b){
			return new VerticesPar<>(a,b);
		}
		public VerticesPar(V a, V b) {
			super();
			this.a = a;
			this.b = b;
			this.vertices = Set2.of(a,b);
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((vertices == null) ? 0 : vertices.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			VerticesPar<?> other = (VerticesPar<?>) obj;
			if (vertices == null) {
				if (other.vertices != null)
					return false;
			} else if (!vertices.equals(other.vertices))
				return false;
			return true;
		}
		
	}
	

}
