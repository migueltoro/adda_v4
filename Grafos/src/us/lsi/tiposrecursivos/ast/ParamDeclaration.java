package us.lsi.tiposrecursivos.ast;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

public record ParamDeclaration(Var var) implements Vertex {
	
	public static ParamDeclaration of(Var var) {
		return new ParamDeclaration(var);
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

}
