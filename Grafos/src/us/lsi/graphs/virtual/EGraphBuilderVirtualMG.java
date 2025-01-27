package us.lsi.graphs.virtual;

import java.util.function.Predicate;

import org.jgrapht.graph.DirectedMultigraph;

import us.lsi.common.Preconditions;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class EGraphBuilderVirtualMG<V, E> extends EGraphBuilderVirtual<VirtualVertexMG<V, E>, VirtualEdgeMG<V, E>> {

	private DirectedMultigraph<V,E> graph;

	public EGraphBuilderVirtualMG(DirectedMultigraph<V,E> graph, V startVertex, Predicate<V> goal, PathType pathType, Type type) {	
		super(VirtualVertexMG.of(startVertex, null, graph), pathType, type);
		this.graph = graph;
		Preconditions.checkArgument(graph.getType().isMultigraph(),"El grafo debe ser Multigraph");
	}
	
	public DirectedMultigraph<V, E> graph() {
		return graph;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

