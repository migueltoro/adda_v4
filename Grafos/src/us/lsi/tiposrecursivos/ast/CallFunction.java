package us.lsi.tiposrecursivos.ast;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;


public record CallFunction(String name,List<Exp> parameters, FunDeclaration funDeclaration) implements Exp {
	
	public static CallFunction of(String id, List<Exp> parameters,FunDeclaration funDeclaration) {
		return new CallFunction(id, parameters,funDeclaration);
	}
	
	public Object value() {
		return null;
	}
	
	public Type type() {
		return null;
	}
	
	@Override
	public String toString() {
		String d = this.parameters().stream().map(x->x.toString()).collect(Collectors.joining(","));
		return String.format("%s(%s)",this.name(),d);
	}

	@Override
	public Operator operator() {
		return null;
	}
	
	@Override
	public Set<Var> vars() {
		return this.parameters().stream().flatMap(e->e.vars().stream()).collect(Collectors.toSet());
	}
	
	@Override
	public Boolean isConst() {
		return this.parameters().stream().allMatch(p->p.isConst());
	}

	@Override
	public Exp simplify() {
		Exp r;
		if(this.isConst()) r = Const.of(this.value(),this.type());
		else r = this;
		return r;
	}
	
	@Override
	public void toGraph(SimpleDirectedGraph<Vertex, DefaultEdge> graph) {
		if(!graph.containsVertex(this)) graph.addVertex(this);
		this.parameters().stream().filter(v->!graph.containsVertex(v))
			.forEach(v->graph.addVertex(v));
		this.parameters().stream().forEach(v->graph.addEdge(this,v));
		this.parameters().stream().forEach(v->v.toGraph(graph));
	}

	@Override
	public String label() {
		return this.name();
	}

}
