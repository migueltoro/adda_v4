package us.lsi.tiposrecursivos.ast;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

public record While(Exp guard, Block block) implements Sentence {
	
	public static While of(Exp guard, Block block) {
		return new While(guard, block);
	}
	
	@Override
	public String toString() {
		return String.format("while (%s) %s", this.guard, this.block);
	}

	@Override
	public String name() {
		return "while";
	}
	
	@Override
	public void toGraph(SimpleDirectedGraph<Vertex, DefaultEdge> graph) {
		if(!graph.containsVertex(this)) graph.addVertex(this);
		if(!graph.containsVertex(this.guard())) graph.addVertex(this.guard());
		if(!graph.containsVertex(this.block())) graph.addVertex(this.block());
		graph.addEdge(this,this.guard());
		graph.addEdge(this,this.block());
		this.guard().toGraph(graph);
		this.block().toGraph(graph);
	}

	@Override
	public String label() {
		return "W";
	}

}
