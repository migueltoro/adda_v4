package us.lsi.graphs.virtual;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.graph.GraphWalk;

public class ESimpleVirtualGraphG<V, E> extends ESimpleVirtualGraph<VirtualVertexG<V,E>,VirtualEdgeG<V,E>> {
	
	private Graph<V,E> graph;
	
	public ESimpleVirtualGraphG(EGraphBuilderVirtualG<V,E> builder) {
		super(builder);
		this.graph = builder.graph();
	}
	
	public Graph<V, E> graph() {
		return graph;
	}
	
	
	public GraphPath<V,E> path(GraphPath<VirtualVertexG<V,E>,VirtualEdgeG<V,E>> p) {
		return new GraphWalk<V, E>(graph, 
				p.getStartVertex().vertex(), 
				p.getEndVertex().vertex(), 
				p.getVertexList().stream().map(v->v.vertex()).toList(), 
				p.getEdgeList().stream().map(e->e.action()).toList(),
				p.getWeight());
	}

}
