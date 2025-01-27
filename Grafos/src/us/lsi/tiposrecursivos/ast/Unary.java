package us.lsi.tiposrecursivos.ast;


import java.util.Set;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import us.lsi.common.Preconditions;
import us.lsi.common.Set2;

public record Unary(Exp operand, String name) implements Exp {

	public static Unary of(Exp operand, String op) {
		return new Unary(operand, op);
	}
	
	public Operator operator() {
		Type t1 = operand.type();
		OperatorId id = OperatorId.of1(name,t1);
		Operator op = Operators.operators.get(id);
		if(op == null)
			System.out.println(String.format("No existe el operador %s",id.longName()));
		return op;
	}
	
	public Object value() {
		Preconditions.checkNotNull(operand.value(),String.format("Valor nulo de %s",this.name()));
		Operator.Unary op = (Operator.Unary) this.operator();
		return op.function().apply(operand.value());
	}
	
	public Type type() {
		return this.operator().resultType();
	}
	

	@Override
	public String toString() {
		return String.format("%s(%s)", this.name, this.operand);
	}
	
	@Override
	public Set<Var> vars() {
		return Set2.of(this.operand.vars());
	}
	
	@Override
	public Boolean isConst() {
		return this.operand().isConst();
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
		if(!graph.containsVertex(this.operand())) graph.addVertex(this.operand());
		graph.addEdge(this,this.operand());
		this.operand().toGraph(graph);
	}

	@Override
	public String label() {
		return this.name();
	}
	
}
