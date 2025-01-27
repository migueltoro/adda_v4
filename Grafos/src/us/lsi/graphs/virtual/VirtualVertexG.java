package us.lsi.graphs.virtual;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;


public record VirtualVertexG<V,E>(V vertex, Graph<V,E> graph) implements VirtualVertex<VirtualVertexG<V,E>,VirtualEdgeG<V,E>,E> {

	public static <V,E> VirtualVertexG<V,E> of(V vertex, Graph<V,E> graph){
		return new VirtualVertexG<V,E>(vertex, graph);
	}
	
	@Override
	public List<E> actions() {
		return this.graph.outgoingEdgesOf(vertex).stream().toList();
	}

	@Override
	public VirtualVertexG<V, E> neighbor(E edge) {
		return VirtualVertexG.of(Graphs.getOppositeVertex(graph, edge, vertex),graph);
	}

	@Override
	public VirtualEdgeG<V, E> edge(E edge) {
		return VirtualEdgeG.of(this,neighbor(edge),edge,graph.getEdgeWeight(edge));
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

