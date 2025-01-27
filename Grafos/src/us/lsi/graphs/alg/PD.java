package us.lsi.graphs.alg;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.nio.Attribute;
import org.jgrapht.nio.DefaultAttribute;

import us.lsi.colors.GraphColors;
import us.lsi.graphs.SimpleEdge;
import us.lsi.hypergraphs.GraphTree;
import us.lsi.hypergraphs.SimpleHyperEdge;
import us.lsi.hypergraphs.SimpleVirtualHyperGraph;
import us.lsi.hypergraphs.VirtualHyperVertex;


public class PD<V extends VirtualHyperVertex<V,E,A,S>,
			E extends SimpleHyperEdge<V,E,A>,A,S> {
	

	public enum PDType{Min,Max}

	public SimpleVirtualHyperGraph<V,E, A> graph;
	private Comparator<Sp<E>> comparatorSp;
	public Map<V,Sp<E>> solutionsTree;
	private PDType type;
	private V startVertex;
	public Graph<VertexGraph<V,E>,SimpleEdge<VertexGraph<V,E>>> outGraph;
	public Boolean withGraph = false;
	
	PD(SimpleVirtualHyperGraph<V,E, A> graph, PDType type) {
		this.graph = graph;
		this.startVertex = graph.getStartVertex();
		this.type = type;
		if(this.type == PDType.Min) this.comparatorSp = Comparator.naturalOrder();
		if(this.type == PDType.Max) this.comparatorSp = Comparator.<Sp<E>>naturalOrder().reversed();
		this.solutionsTree = new HashMap<>();
	}
	
	public Sp<E> search(){
		if(this.withGraph) outGraph = new SimpleDirectedWeightedGraph<>(null,null);
		return search(this.startVertex);
	}

	public Sp<E> search(V actual) {
		Sp<E> r = null;
		if (this.solutionsTree.containsKey(actual)) {
			r = this.solutionsTree.get(actual);
		} else if (graph.isBaseCase(actual)) {
			Double w = graph.baseCaseWeight(actual);
			if(w!=null) r = Sp.of(w,null);
			else r = null;
			this.solutionsTree.put(actual, r);
		} else {
			List<Sp<E>> sps = new ArrayList<>();
			for (E edge : graph.edgesOf(actual)) {
				List<Sp<E>> spNeighbors = new ArrayList<>();
				List<V> neighbords = graph.getEdgeTargets(edge);
				for (V neighbor : neighbords) {
					Sp<E> nb = search(neighbor);
					if (nb == null) {
						spNeighbors = null;
						break;
					}
					spNeighbors.add(nb);
				}
				Sp<E> spa = null;
				if(spNeighbors != null) {
					List<Double> solutions = spNeighbors.stream().map(sp->sp.weight()).toList();
					spa = Sp.of(graph.getEdgeWeight(edge,solutions), edge);
				}
				sps.add(spa);
				if(this.withGraph) this.completeGraph(actual,edge,neighbords);
			}
			r = sps.stream().filter(s -> s != null).min(this.comparatorSp).orElse(null);
			this.solutionsTree.put(actual, r);
			
		}
		return r;
	}
	
	private void completeGraph(V actual,E edge,List<V> neighbords) {
		VertexGraph<V,E> vg = VertexGraph.ofVertex(actual);
		outGraph.addVertex(vg);
		VertexGraph<V,E> ve = VertexGraph.ofEdge(edge);
		outGraph.addVertex(ve);
	    outGraph.addEdge(vg,ve,SimpleEdge.of(vg,ve,1.));
	    for (V neighbord:neighbords) {
			VertexGraph<V, E> vn = VertexGraph.ofVertex(neighbord);
			outGraph.addVertex(vn);
			outGraph.addEdge(ve, vn, SimpleEdge.of(ve, vn, 1.));
		}
	}

	
	public void toDot(String file,Function<V,String> stringVertex, Function<E,String> stringEdge, Set<V> s) {
		GraphColors.toDot(this.outGraph,
				file,
				v->v.toStringVertex(stringVertex),
				e->VertexGraph.toStringEdge(this.outGraph,e,stringEdge),
				v->v.shapeAndColorVertex(s),
				e->VertexGraph.colorEdge(this.outGraph,e,s)
				);
	}
	
	public SimpleVirtualHyperGraph<V, E, A> getGraph() {
		return graph;
	}
	
	public Map<V, Sp<E>> getSolutionsTree() {
		return solutionsTree;
	}	
	public PDType getType() {
		return type;
	}
	
	public GraphTree<V,E,A,S> searchTree(V vertex){
		return GraphTree.optimalTree(vertex, solutionsTree);
	}
	
	public static <V extends VirtualHyperVertex<V, E, A,S>, E extends SimpleHyperEdge<V, E, A>, A, S> 
		PD<V, E, A, S> dynamicProgrammingSearch(
			SimpleVirtualHyperGraph<V, E, A> graph, 
			PDType type) {
		return new PD<V, E, A, S>(graph, type);
	}

	public record Sp<E>(Double weight, E edge) implements Comparable<Sp<E>> {
		
		public static <E> Sp<E> of(Double weight,E edge) {
			return new Sp<>(weight,edge);
		}
		
		public static <E> Sp<E> of(Double weight) {
			return new Sp<>(weight,null);
		}
		
		public static <E> Comparator<Sp<E>> comparator() {
			return Comparator.naturalOrder();
		}

		@Override
		public int compareTo(Sp<E> sp) {
			return this.weight.compareTo(sp.weight);
		}

	}

	public static record VertexGraph<V,E>(V vertex, E edge, DPTipoVertex tipo) {
		public static enum DPTipoVertex{Vertex,Edge};
		public static <V,E> VertexGraph<V,E> ofVertex(V v){
			return new VertexGraph<>(v, null,DPTipoVertex.Vertex);
		}
		public static <V,E> VertexGraph<V,E> ofEdge(E e){
			return new VertexGraph<>(null, e,DPTipoVertex.Edge);
		}
		
		public String toStringVertex(Function<V,String> stringVertex) {
			return switch(tipo()) {
			case Vertex -> stringVertex.apply(vertex);
			case Edge -> "";
			};
		}
		
		public static <V,E> String toStringEdge(Graph<VertexGraph<V,E>,SimpleEdge<VertexGraph<V,E>>> graph,
				SimpleEdge<VertexGraph<V,E>> edge, Function<E,String> stringEdge) {
			VertexGraph<V,E> source = graph.getEdgeSource(edge);
			VertexGraph<V,E> target = graph.getEdgeTarget(edge);
			String r = "";
			if(source.tipo().equals(DPTipoVertex.Vertex)) {
				r = stringEdge.apply(target.edge());
			}
			return r;
		}
		
		private static <V,E> Boolean optimalEdge(Graph<VertexGraph<V,E>,SimpleEdge<VertexGraph<V,E>>> graph,
				SimpleEdge<VertexGraph<V,E>> edge, Set<V> optimalVertex) {
			VertexGraph<V, E> source = graph.getEdgeSource(edge);
			VertexGraph<V, E> target = graph.getEdgeTarget(edge);
			VertexGraph<V, E> vv = null;;
			if(source.tipo().equals(DPTipoVertex.Edge)) vv = source;				
			if(source.tipo().equals(DPTipoVertex.Vertex)) vv = target;
			final VertexGraph<V, E> vf = vv;
			return graph.edgesOf(vf).stream()
					.map(v->v.otherVertex(vf))
					.map(v->v.vertex())
					.allMatch(v->optimalVertex.contains(v));
		}
		
		public static <V,E> Map<String, Attribute> colorEdge(Graph<VertexGraph<V,E>,SimpleEdge<VertexGraph<V,E>>> graph,
				SimpleEdge<VertexGraph<V,E>> edge, Set<V> optimalVertex) {
			Map<String, Attribute> r = Map.of();	
			if(optimalEdge(graph,edge,optimalVertex)) {
				r = Map.of("color", DefaultAttribute.createAttribute(GraphColors.Color.red.name()),
						   "style", DefaultAttribute.createAttribute(GraphColors.Style.bold.name()));				
			}
			return r;
		}
		
		public Map<String, Attribute> shapeVertex(){
			return switch(tipo()) {
			case Vertex -> Map.of();
			case Edge -> Map.of("shape", DefaultAttribute.createAttribute(GraphColors.Shape.point.name()));
			};
		}

		
		public Map<String, Attribute> colorVertex(Set<V> optimalVertex) {
			return switch(tipo()) {
			case Vertex -> optimalVertex.contains(this.vertex)? 
					Map.of("color", DefaultAttribute.createAttribute(GraphColors.Color.red.name()),
						   "style", DefaultAttribute.createAttribute(GraphColors.Style.bold.name())):
					Map.of();
			case Edge -> Map.of();
			};
		}
	
		public Map<String, Attribute> shapeAndColorVertex(Set<V> optimalVertex) {
			Map<String, Attribute> shape = shapeVertex();
			Map<String, Attribute> color = colorVertex(optimalVertex);
			shape = new HashMap<>(shape);
			shape.putAll(color);
			return shape;
		}
			
	}
	
}