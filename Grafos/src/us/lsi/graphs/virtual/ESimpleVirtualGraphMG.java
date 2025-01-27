package us.lsi.graphs.virtual;


import org.jgrapht.GraphPath;
import org.jgrapht.graph.DirectedMultigraph;
import org.jgrapht.graph.GraphWalk;

public class ESimpleVirtualGraphMG<V, E> extends ESimpleVirtualGraph<VirtualVertexMG<V,E>,VirtualEdgeMG<V,E>> {
	
	private DirectedMultigraph<V,E> graph;
	
	public ESimpleVirtualGraphMG(EGraphBuilderVirtualMG<V, E> builder) {
		super(builder);
		this.graph = builder.graph();
	}

	public DirectedMultigraph<V, E> graph() {
		return graph;
	}
	
	public GraphPath<V,E> path(GraphPath<VirtualVertexMG<V,E>,VirtualEdgeMG<V,E>> p) {
		return new GraphWalk<V, E>(graph, 
				p.getStartVertex().vertex(), 
				p.getEndVertex().vertex(), 
				p.getVertexList().stream().map(v->v.vertex()).toList(), 
				p.getEdgeList().stream().map(e->e.action()).toList(),
				p.getWeight());
	}


}
