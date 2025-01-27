package us.lsi.tiposrecursivos;

import java.util.Optional;
import java.util.function.Function;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

public record BTree<E>(E label, BinaryTree<E> left, BinaryTree<E> right) implements BinaryTree<E> {
	public BinaryType type() {
		return BinaryType.Binary;
	}

	public boolean isEmpty() {
		return false;
	}

	public Optional<E> optionalLabel() {
		return Optional.of(this.label());
	}

	public int size() {
		return 1 + this.left().size() + this.right().size();
	}

	public int height() {
		return 1 + Math.max(this.left().height(), this.right().height());
	}

	public BinaryTree<E> copy() {
		return BinaryTree.binary(this.label(), this.left().copy(), this.right().copy());
	}

	public BinaryTree<E> reverse() {
		return BinaryTree.binary(this.label(), this.right().copy(), this.left().copy());
	}

	public <R> BinaryTree<R> map(Function<E, R> f) {
		return BinaryTree.binary(f.apply(this.label()), this.left().map(f), this.right().map(f));
	}

	public String toString() {
		return String.format("%s(%s,%s)", this.label().toString(), this.left().toString(), this.right().toString());
	}

	public void toGraph(SimpleDirectedGraph<BinaryTree<E>, DefaultEdge> graph) {
		if (!graph.containsVertex(this))
			graph.addVertex(this);
		if (!graph.containsVertex(this.left()))
			graph.addVertex(this.left());
		if (!graph.containsVertex(this.right()))
			graph.addVertex(this.right());
		graph.addEdge(this, this.left());
		graph.addEdge(this, this.right());
		this.left().toGraph(graph);
		this.right().toGraph(graph);
	}
}
