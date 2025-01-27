package us.lsi.tiposrecursivos;

import java.util.Optional;
import java.util.function.Function;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

public record BEmpty<E>() implements BinaryTree<E> {
	public BinaryType type() { return BinaryType.Empty;}
	public boolean isEmpty() {return true;}
	public Optional<E> optionalLabel() { return Optional.empty(); }
	public int size() { return 0; }
	public int height() { return -1; }
	public BinaryTree<E> copy() { return BinaryTree.empty(); }
	public BinaryTree<E> reverse() { return BinaryTree.empty(); }
	public <R> BinaryTree<R> map(Function<E, R> f) { return BinaryTree.empty();}
	public String toString() { return "_"; }
	public void toGraph(SimpleDirectedGraph<BinaryTree<E>, DefaultEdge> graph) {
		if(!graph.containsVertex(this)) graph.addVertex(this);
	}
}
