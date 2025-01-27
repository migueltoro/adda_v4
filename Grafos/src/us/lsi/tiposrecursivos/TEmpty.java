package us.lsi.tiposrecursivos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

public record TEmpty<E>() implements Tree<E> {
	public TreeType type() { return TreeType.Empty;}
	public boolean isEmpty() {return true;};
	public Optional<E> optionalLabel() { return Optional.empty(); }
	public List<Tree<E>> children() {return new ArrayList<>();}
	public int size() { return 0; }
	public int height() { return -1; }
	public Tree<E> copy() { return Tree.empty(); }
	public Tree<E> reverse() { return Tree.empty(); }
	public <R> Tree<R> map(Function<E, R> f) { return Tree.empty();}
	public String toString() { return "_"; }
	public int childrenNumber() { return 0;}
	public void toGraph(SimpleDirectedGraph<Tree<E>, DefaultEdge> graph) {
		if(!graph.containsVertex(this)) graph.addVertex(this);
	}
}
