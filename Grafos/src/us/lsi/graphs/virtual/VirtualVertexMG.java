package us.lsi.graphs.virtual;

import java.util.List;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DirectedMultigraph;


public record VirtualVertexMG<V,E>(V vertex, E edge, DirectedMultigraph<V,E> graph) implements VirtualVertex<VirtualVertexMG<V,E>,VirtualEdgeMG<V,E>,E> {

	public static <V,E> VirtualVertexMG<V,E> of(V vertex, E edge, DirectedMultigraph<V,E> graph){
		return new VirtualVertexMG<V,E>(vertex, edge, graph);
	}
	
	@Override
	public List<E> actions() {
		return this.graph.outgoingEdgesOf(vertex).stream().toList();
	}

	@Override
	public VirtualVertexMG<V, E> neighbor(E edge) {
		return VirtualVertexMG.of(Graphs.getOppositeVertex(graph, edge, vertex),edge,graph);
	}

	@Override
	public VirtualEdgeMG<V, E> edge(E edge) {
		return VirtualEdgeMG.of(this,neighbor(edge),edge,graph.getEdgeWeight(edge));
	}

	@Override
	public Boolean goal() {
		return this.goal();
	}

	@Override
	public Boolean goalHasSolution() {
		return this.goalHasSolution();
	}
	
	@Override
	public E greedyAction() {
		return this.greedyAction();
	}
	
}
