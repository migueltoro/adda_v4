package us.lsi.tiposrecursivos.ast;

import java.util.Set;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import us.lsi.common.Set2;

public final class Var implements Exp, Operator {
	
	private String name;
	private Object value;
	private Type type;

	protected Var(String name, Type type, Object value) {
		super();
		this.name = name;
		this.value = value;
		this.type = type;
	}
	
	public static Var of(String name,Type type, Object value) {
		return new Var(name,type,value);
	}
	
	public static Var of(String name,Type type) {
		return new Var(name,type,null);
	}
	
	public String name() {
		return name;
	}
	
	public Object value() {
		return value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}
	public Type type() {
		return type;
	}

	@Override
	public String toString() {
		return this.name();
	}

	@Override
	public Operator operator() {
		return this;
	}

	@Override
	public Set<Var> vars() {
		return Set2.of(this);
	}

	@Override
	public OperatorId operatorId() {
		return OperatorId.of0(name);
	}

	@Override
	public Type resultType() {
		return this.type();
	}

	@Override
	public Boolean isConst() {
		return false;
	}

	@Override
	public Var simplify() {
		return this;
	}
	
	@Override
	public void toGraph(SimpleDirectedGraph<Vertex, DefaultEdge> graph) {
		if(!graph.containsVertex(this)) graph.addVertex(this);
	}

	@Override
	public String label() {
		String type = this.type()==null?"_":""+this.type().toString().charAt(0);
		if(this.value() == null)
			return String.format("%s:%s=_",this.name(),type);
		else
			return String.format("%s:%s=%s",this.name(),type,
					this.value().toString());
	}
	
}
