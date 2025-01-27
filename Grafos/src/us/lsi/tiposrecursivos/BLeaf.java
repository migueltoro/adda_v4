package us.lsi.tiposrecursivos;

import java.util.Optional;
import java.util.function.Function;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

public record BLeaf<E>(E label) implements BinaryTree<E> {
	public BinaryType type() { return BinaryType.Leaf;}
	public boolean isEmpty() {return false;}
	public Optional<E> optionalLabel() { return Optional.of(this.label()); }
	public int size() { return 1; }
	public int height() { return 0; }
	public BinaryTree<E> copy() { return BinaryTree.leaf(this.label()); }
	public BinaryTree<E> reverse() { return BinaryTree.leaf(this.label()); }
	public <R> BinaryTree<R> map(Function<E, R> f) { return BinaryTree.leaf(f.apply(this.label()));}
	public String toString() { return this.label().toString(); }
	public void toGraph(SimpleDirectedGraph<BinaryTree<E>, DefaultEdge> graph) {
		if(!graph.containsVertex(this)) graph.addVertex(this);
	}
}
