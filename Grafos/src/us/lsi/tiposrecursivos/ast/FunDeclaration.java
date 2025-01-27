package us.lsi.tiposrecursivos.ast;


import java.util.List;
import java.util.stream.Collectors;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import us.lsi.colors.GraphColors.Color;

public record FunDeclaration(String id,Type resultType,List<ParamDeclaration> parameters, Block block) 
	implements Declaration {

	public static FunDeclaration of(String id,Type resultType,List<ParamDeclaration> parameters, Block block) {
		return new FunDeclaration(id, resultType,parameters,block);
	}
	
	@Override
	public String toString() {
		String d = this.parameters().stream().map(x->x.toString()).collect(Collectors.joining(","));
		return String.format("%s(%s):%s",this.id(),d,this.resultType().toString().charAt(0));
	}
	
	@Override
	public String name() {
		return String.format("%s(%s):%s",this.id(),
				this.parameters().stream().map(p->p.var().type().toString()).collect(Collectors.joining(",")), 
				this.resultType());
	}
	
	@Override
	public void toGraph(SimpleDirectedGraph<Vertex, DefaultEdge> graph) {
		if(!graph.containsVertex(this)) graph.addVertex(this);
		Integer n = this.parameters().size();
		Vertex p = Vertex.of("Fp",Vertex.circle(Color.green));
		graph.addVertex(p);
		graph.addEdge(this,p);
		for( int i = 0; i< n; i++) {
			if(!graph.containsVertex(this.parameters().get(i))) 
				graph.addVertex(this.parameters().get(i));
			graph.addEdge(p,this.parameters().get(i));
		}
		if(!graph.containsVertex(this.block())) graph.addVertex(this.block());
		graph.addEdge(this,this.block());
		this.block().toGraph(graph);	
	}

	@Override
	public String label() {
			return this.id();
	}
}
