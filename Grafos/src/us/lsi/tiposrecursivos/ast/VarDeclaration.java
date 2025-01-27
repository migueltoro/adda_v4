package us.lsi.tiposrecursivos.ast;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

public record VarDeclaration(Var var) implements Vertex, Declaration {
	
	public static VarDeclaration of(Var var) {
		return new VarDeclaration(var);
	}
	
	@Override
	public String toString() {
		String type = ""+this.var().type().toString().charAt(0);
		return String.format("%s:%s", var().name(),type);
	}
	
	@Override
	public void toGraph(SimpleDirectedGraph<Vertex, DefaultEdge> graph) {
		if(!graph.containsVertex(this)) graph.addVertex(this);
	}

	@Override
	public String label() {
		String type = ""+this.var().type().toString().charAt(0);
		return String.format("%s:%s",this.var().name(),type);
	}
	
	public String name() {
		return this.var().name();
	}

}
