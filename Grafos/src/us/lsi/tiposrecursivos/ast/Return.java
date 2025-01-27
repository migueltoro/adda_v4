package us.lsi.tiposrecursivos.ast;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

public record Return(Exp exp) implements Sentence  {
	
	public static Return of(Exp exp) {
		return new Return(exp);
	}

	@Override
	public void toGraph(SimpleDirectedGraph<Vertex, DefaultEdge> graph) {
		if(!graph.containsVertex(this)) graph.addVertex(this);
		if(!graph.containsVertex(this.exp())) graph.addVertex(this.exp());
		graph.addEdge(this,this.exp());
		this.exp().toGraph(graph);
	}

	@Override
	public String label() {
		return "R";
	}

	@Override
	public String name() {
		return "R";
	}
	
	@Override
	public String toString() {
		return String.format("return %s", this.exp());
	}
}
