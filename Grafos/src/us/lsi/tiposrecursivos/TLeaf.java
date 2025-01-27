package us.lsi.tiposrecursivos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

public record TLeaf<E>(E label) implements Tree<E> {
	public TreeType type() { return TreeType.Leaf;}
	public boolean isEmpty() {return false;};
	public Optional<E> optionalLabel() { return Optional.of(this.label()); }
	public List<Tree<E>> children() {return new ArrayList<>();}
	public int size() { return 1; }
	public int height() { return 0; }
	public Tree<E> copy() { return Tree.leaf(this.label()); }
	public Tree<E> reverse() { return Tree.leaf(this.label()); }
	public <R> Tree<R> map(Function<E, R> f) { return Tree.leaf(f.apply(this.label()));}
	public String toString() { return this.label().toString(); }
	public int childrenNumber() { return 0;}
	public void toGraph(SimpleDirectedGraph<Tree<E>, DefaultEdge> graph) {
		if(!graph.containsVertex(this)) graph.addVertex(this);
	}
}
