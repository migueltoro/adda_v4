package us.lsi.tiposrecursivos.ast;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

public record Nary(List<Exp> operands, String name) implements Exp {

	public static Nary of(List<Exp> operands, String op) {
		return new Nary(operands, op);
	}
	
	public Operator operator() {
		List<Type> t = operands.stream().map(op->op.type()).toList();
		OperatorId id = OperatorId.ofN(name,t.get(0));
		return Operators.operators.get(id);
	}
	
	public Object value() {
		Operator.Nary op = (Operator.Nary) this.operator();
		List<Object> values = operands.stream().map(e->e.value()).toList();
		return op.function().apply(values);
	}
	
	public Type type() {
		return this.operator().resultType();
	}
	
	@Override
	public String toString() {
		return String.format("%s(%s)", this.name(), this.operands().stream()
				.map(e->e.toString()).collect(Collectors.joining(",")));
	}
	
	@Override
	public Set<Var> vars() {
		return this.operands.stream().flatMap(e->e.vars().stream()).collect(Collectors.toSet());
	}

	@Override
	public Boolean isConst() {
		return this.operands().stream().allMatch(p->p.isConst());
	}

	@Override
	public Exp simplify() {
		Exp r;
		if(this.isConst()) r = Const.of(this.value(),this.type());
		else r = Nary.of(operands.stream().map(op->op.simplify()).toList(),this.name());
		return r;
	}
	
	@Override
	public void toGraph(SimpleDirectedGraph<Vertex, DefaultEdge> graph) {
		if(!graph.containsVertex(this)) graph.addVertex(this);
		this.operands().stream()
			.forEach(v->{if(!graph.containsVertex(v)) graph.addVertex(v);});
		this.operands().stream().forEach(v->graph.addEdge(this,v));
		this.operands().stream().forEach(v->v.toGraph(graph));
	}

	@Override
	public String label() {
		return this.operator().operatorId().name();
	}
	
}
