package us.lsi.graphs.alg;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import org.jgrapht.GraphPath;
import org.jgrapht.graph.GraphWalk;

import us.lsi.common.List2;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.path.EGraphPath;
import us.lsi.streams.Stream2;
import us.lsi.graphs.alg.GreedyOnGraph.Gog;

public class GreedyOnGraph<V,E> implements  Iterator<Gog<V,E>>, Iterable<Gog<V,E>> {
	
	public static <V,E> GraphPath<V,E> getPath(EGraph<V,E> graph) {
		V v = graph.startVertex();
		List<V> vertices = new ArrayList<>();
		vertices.add(v);
		Double weight = 0.;
		while(!graph.goal().test(v)) {
			E edge = graph.greedyEdge().apply(v);
			weight += graph.getEdgeWeight(edge);
			v = graph.oppositeVertex(edge, v);
			vertices.add(v);
		}
		return new GraphWalk<V,E>(graph,vertices,weight);
	}
	
	public static <V,E> GreedyOnGraph<V,E> of(EGraph<V,E> graph,Function<V,E> greedyEdge) {
		return new GreedyOnGraph<V,E>(graph, greedyEdge);
	}
	
	public static <V,E> GreedyOnGraph<V,E> of(EGraph<V,E> graph) {
		return new GreedyOnGraph<V,E>(graph,graph.greedyEdge());
	}
	
	public static <V,E> GreedyOnGraph<V,E> random(EGraph<V,E> graph) {
		Function<V,E> nextEdge = v -> graph.edgesListOf(v).isEmpty() ? null: 
			List2.randomUnitary(graph.edgesListOf(v)).get(0);
		return new GreedyOnGraph<V,E>(graph,nextEdge);
	}
	
	public static record Gog<V,E>(V vertex, E edge) {
		public static  <V,E> Gog<V,E> of(V vertex, E edge){
			return new Gog<V,E>(vertex,edge);
		}
	}
	
	private EGraph<V,E> graph;
	private V state;
	private E edge;
	private Function<V,E> greedyEdge;

	private GreedyOnGraph(EGraph<V,E> graph,Function<V,E> greedyEdge) {
		super();
		this.graph = graph;
		this.state = graph.startVertex();
		this.greedyEdge = greedyEdge;
	}

	public Stream<V> stream() {
		return this.streamPair().map(p->p.vertex());
	}
	
	public Stream<E> streamEdges() {
		return this.streamPair().map(p->p.edge()).filter(e->e!=null);
	}
	
	public Stream<Gog<V,E>> streamPair() {
		return Stream2.of(this);
	}
	
	public GraphPath<V,E> path(){
		EGraphPath<V,E> path = this.graph.initialPath();
		this.streamEdges().forEach(e->path.add(e));
		return path;
	}
	
	public Optional<GraphPath<V,E>> search(){	
		GraphPath<V,E> r = path();
		if(!r.getVertexList().isEmpty()) {
			V last = r.getEndVertex();
			if(this.graph.goalHasSolution().test(last)) return Optional.of(r);
			else return Optional.empty();
		} else return Optional.empty();
	}
	
	public <S> Optional<S> search(Function<GraphPath<V,E>,S> f) {
		Optional<GraphPath<V, E>> p = search();
		return p.map(f);
	}
	
	
	public Boolean isSolution(GraphPath<V,E> gp) {
		V last = gp.getEndVertex();
		return graph.goal().test(last);
	}
	
	public Optional<V> last() {
		return Stream2.findLast(this.stream());
	}
	
	public GreedyOnGraph<V,E> copy() {
		return of(this.graph,this.greedyEdge);
	}
	
	@Override
	public Iterator<Gog<V,E>> iterator() {
		return this;
	}

	@Override
	public boolean hasNext() {
		return state != null && !this.graph.edgesListOf(state).isEmpty() && !this.graph.goal().test(state) ;
	}

	@Override
	public Gog<V,E> next() {
		V old = state;
		edge= this.greedyEdge.apply(state);
		if(edge !=null) this.state = graph.oppositeVertex(edge, old);
		else this.state = null;
		return Gog.of(old, edge);
	}

}
