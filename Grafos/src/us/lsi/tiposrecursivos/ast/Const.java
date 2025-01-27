package us.lsi.tiposrecursivos.ast;

import java.util.Set;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import us.lsi.common.Set2;

public record Const(String name, Type type, Object value) implements Exp, Operator {

	public static Const of(Object value, Type type) {
		return new Const("",type,value);
	}
	
	public static Const of(String name,Object value,Type type) {
		return new Const(name,type,value);
	}

	@Override
	public String toString() {
		return String.format("%s",this.value);
	}
	
	@Override
	public Operator operator() {
		return this;
	}
	
	@Override
	public Set<Var> vars() {
		return Set2.empty();
	}

	@Override
	public OperatorId operatorId() {
		return OperatorId.of0(this.name());
	}

	@Override
	public Type resultType() {
		return type;
	}
	
	@Override
	public Boolean isConst() {
		return true;
	}

	@Override
	public Const simplify() {
		return this;
	}
	
	@Override
	public void toGraph(SimpleDirectedGraph<Vertex, DefaultEdge> graph) {
		if(!graph.containsVertex(this)) graph.addVertex(this);
	}

	@Override
	public String label() {
		return this.value().toString();
	}
	
	
}
