package us.lsi.tiposrecursivos.ast;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

public record IfThenElse(Exp guard,Block trueBlock,Block falseBlock) implements Sentence {
	
	public static IfThenElse of(Exp guard, Block trueBlock, Block falseBlock) {
		return new IfThenElse(guard, trueBlock, falseBlock);
	}
	
	@Override
	public String toString() {
		return String.format("if (%s) %selse %s", this.guard, this.trueBlock(),this.falseBlock());
	}
	
	@Override
	public String name() {
		return "ifThenElse";
	}
	
	@Override
	public void toGraph(SimpleDirectedGraph<Vertex, DefaultEdge> graph) {
		if(!graph.containsVertex(this)) graph.addVertex(this);
		if(!graph.containsVertex(this.guard())) graph.addVertex(this.guard());
		if(!graph.containsVertex(this.falseBlock())) graph.addVertex(this.falseBlock());
		if(!graph.containsVertex(this.trueBlock())) graph.addVertex(this.trueBlock());
		graph.addEdge(this,this.guard());
		graph.addEdge(this,this.falseBlock());
		graph.addEdge(this,this.trueBlock());
		this.guard().toGraph(graph);
		this.falseBlock().toGraph(graph);
		this.trueBlock().toGraph(graph);
	}

	@Override
	public String label() {
		return "If";
	}

}
