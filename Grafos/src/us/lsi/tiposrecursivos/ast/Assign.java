package us.lsi.tiposrecursivos.ast;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

public record Assign(Var id, Exp exp) implements Sentence {

	public static Assign of(Var id, Exp exp) {
		return new Assign(id, exp);
	}
	
	@Override
	public String toString() {
		return String.format("%s = %s", this.id, this.exp);
	}

	@Override
	public String name() {
		return "<==";
	}

	
	@Override
	public void toGraph(SimpleDirectedGraph<Vertex, DefaultEdge> graph) {
		if(!graph.containsVertex(this)) graph.addVertex(this);
		if(!graph.containsVertex(this.id())) graph.addVertex(this.id());
		if(!graph.containsVertex(this.exp())) graph.addVertex(this.exp());
		graph.addEdge(this,this.id());
		graph.addEdge(this,this.exp());
		this.id().toGraph(graph);
		this.exp().toGraph(graph);
	}

	@Override
	public String label() {
		return "A";
	}
	
}
