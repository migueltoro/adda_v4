package us.lsi.tiposrecursivos.ast;

import java.util.Set;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import us.lsi.common.Preconditions;
import us.lsi.common.Set2;

public record Ternary(Exp guard, Exp left, Exp right, String name) implements Exp {
	
	public static Ternary of(Exp guard, Exp left, Exp right, String op) {
		return new Ternary(guard,left, right, op);
	}
	
	public Operator operator() {
		Type t1 = left.type();
		Type t2 = right.type();
		OperatorId id = OperatorId.of2(name,t1,t2);
		Operator op = Operators.operators.get(id);
		if(op == null) 
			System.out.println(String.format("No existe el operador %s",id.longName()));
		return op;
	}
	
	public Object value() {
		Operator.Binary op = (Operator.Binary) this.operator();
		return op.function().apply(left.value(),right.value());
	}
	
	public Type type() {
		return this.operator().resultType();
	}
	
	@Override
	public String toString() {
		return String.format("%s ? %s : %s)", this.guard(),this.left, this.right);
	}
	
	@Override
	public Set<Var> vars() {
		return Set2.union(this.left().vars(),this.right().vars());
	}

	@Override
	public Boolean isConst() {
		return this.left().isConst() && this.right().isConst();
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
		if(!graph.containsVertex(this.guard())) graph.addVertex(this.guard());
		if(!graph.containsVertex(this.left())) graph.addVertex(this.left());
		if(!graph.containsVertex(this.right())) graph.addVertex(this.right());
		graph.addEdge(this,this.guard());
		graph.addEdge(this,this.left());
		graph.addEdge(this,this.right());
		this.guard().toGraph(graph);
		this.left().toGraph(graph);
		this.right().toGraph(graph);
	}

	@Override
	public String label() {
		return this.name();
	}
	
}
