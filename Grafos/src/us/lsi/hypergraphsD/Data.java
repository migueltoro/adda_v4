package us.lsi.hypergraphsD;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.ArrowHead;
import us.lsi.colors.GraphColors.Color;
import us.lsi.colors.GraphColors.Shape;
import us.lsi.common.SetMultimap;
import us.lsi.common.Union;


public class Data<V,E> {	
	
	private static Object datos = null;
	
	@SuppressWarnings("unchecked")
	public static <V,E> Data<V,E> of() {
		if(datos == null) datos = new Data<>();
		return (Data<V, E>) datos;
	}
	
	public static enum DpType{Max, Min}
	public static DpType type = DpType.Min;
	private Comparator<Sp<E>> cmp;
	public Map<V,Sp<E>> memory;
	public SetMultimap<V,Sp<E>> memoryAll;
	
	private Data() {
		Comparator<Sp<E>> cmp = Data.type.equals(Data.DpType.Min) ? 
				Comparator.naturalOrder() : Comparator.reverseOrder();
		this.cmp = cmp;
		this.memory = new HashMap<>();
		this.memoryAll = SetMultimap.empty();
	}
	
	public Comparator<Sp<E>> order(){
		return cmp;
	}
	
	public Boolean solved(V vertex) {
		return Data.<V,E>of().memory.containsKey(vertex);
	}
	public Sp<E> sp(V vertex) {
		return Data.<V,E>of().memory.get(vertex);
	}
	public void setSp(V vertex, Sp<E> sp) {
		Data.<V,E>of().memory.put(vertex,sp);
	}
	public void setAllSp(V vertex,Sp<E> data) {
		Data.<V,E>of().memoryAll.put(vertex,data);
	}
	
	public static <V extends HyperVertexD<V, E, A, ?>, E extends HyperEdgeD<V, E, A, ?>, A> 
		SimpleDirectedGraph<Union<V, E>, DefaultEdge> graph(SetMultimap<V, Sp<E>> vertices) {

		SimpleDirectedGraph<Union<V, E>, DefaultEdge> graph = new SimpleDirectedGraph<Union<V, E>, DefaultEdge>(null,
				() -> new DefaultEdge(), true);

		for (V v : vertices.keySet()) {
			graph.addVertex(Union.ofA(v));
		}
		for (V v : vertices.keySet()) {
			if (!v.isBaseCase()) {
				for (Sp<E> sp : vertices.get(v)) {
					if (sp != null) {
						E e = sp.edge();
						Union<V, E> source = Union.ofA(e.source());
						List<Union<V, E>> targets = e.targets().stream().map(x -> Union.<V, E>ofA(x)).toList();
						Union<V, E> ve = Union.ofB(e);
						graph.addVertex(ve);
						graph.addEdge(source, ve);
						for (Union<V, E> t : targets) {
							graph.addVertex(t);
							graph.addEdge(ve, t);
						}
					}
				}
			}
		}
		return graph;
	}
	
	public static <V,E> String stv(Union<V,E> un) {
		if(un.isA()) return un.a().toString();
		else return un.b().toString();
	}
	
	public static <V,E> String ste(Union<V,E> un) {
		if(un.isA()) return un.a().toString();
		else return un.b().toString();
	}
	
	public static <V extends HyperVertexD<V, E, A, ?>, E extends HyperEdgeD<V,E,A,?>, A> 
		void toDotHypergraph(SimpleDirectedGraph<Union<V,E>, DefaultEdge> g, String file, V initial) {

		Set<Union<V,E>> vt = initial.graphTree().allVertices().stream()
				.map(v->Union.<V,E>ofA(v))
				.collect(Collectors.toSet());
		
		Set<Union<V,E>> et = initial.graphTree().allEdges().stream()
				.map(v->Union.<V,E>ofB(v))
				.collect(Collectors.toSet());
		
		Predicate<DefaultEdge> pd = e->et.contains(g.getEdgeSource(e)) || et.contains(g.getEdgeTarget(e));

		GraphColors.toDot(g, file, 
				x -> stv(x),
				x -> g.getEdgeSource(x).isA() ? g.getEdgeTarget(x).b().toString() : "",
				x -> GraphColors.all(GraphColors.shapeIf(Shape.point, x.isB()),
						GraphColors.colorIf(Color.red, vt.contains(x))),
				e -> GraphColors.all(GraphColors.arrowHead(ArrowHead.none),
						GraphColors.colorIf(Color.red, pd.test(e))));
	}
	
	public static <V extends HyperVertexD<V, E, A, ?>, E extends HyperEdgeD<V,E,A,?>,A> 
		void toDotAndOr(SimpleDirectedGraph<Union<V,E>, DefaultEdge> g,
			String file, V initial) {

		Set<Union<V,E>> vt = initial.graphTree().allVertices().stream()
				.map(v->Union.<V,E>ofA(v))
				.collect(Collectors.toSet());
		
		Set<Union<V,E>> et = initial.graphTree().allEdges().stream()
				.map(v->Union.<V,E>ofB(v))
				.collect(Collectors.toSet());
		
		Predicate<DefaultEdge> pd = e->et.contains(g.getEdgeSource(e)) || et.contains(g.getEdgeTarget(e));
				
		GraphColors.toDot(g, file, 
				x -> stv(x),
				x -> "",
				x -> GraphColors.all(GraphColors.shapeIf(Shape.box, x.isA()),
						GraphColors.colorIf(Color.red, vt.contains(x) || et.contains(x))),
				e -> GraphColors.all(GraphColors.arrowHead(ArrowHead.none),
						GraphColors.colorIf(Color.red, pd.test(e))));
	}
	
}
