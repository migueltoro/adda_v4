package us.lsi.tiposrecursivos;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import us.lsi.common.List2;

public record TNary<E>(E label, List<Tree<E>> children) implements Tree<E> {
	public TreeType type() { return TreeType.Nary;}
	public boolean isEmpty() { return false;}
	public Optional<E> optionalLabel() { return Optional.of(this.label());}
	public int size() {
		return 1 + (int) children().stream().mapToInt(x -> x.size()).sum();
	}
	public int height() {
		return 1 + this.children().stream().mapToInt(x -> x.height()).max().getAsInt();
	}
	public Tree<E> copy() {
		return Tree.nary(label(), children().stream().map(x -> x.copy()).toList());
	}
	public Tree<E> reverse() {
		List<Tree<E>> nElements = List2.reverse(this.children()).stream().map(x -> x.reverse()).toList();
		return Tree.nary(this.label(), nElements);
	}
	public <R> Tree<R> map(Function<E, R> f) {
		List<Tree<R>> nElements = this.children().stream().map(x -> x.map(f)).collect(Collectors.toList());
		return Tree.nary(f.apply(label), nElements);
	}
	public String toString() {
		return label().toString()
				+ children().stream().map(x -> x.toString()).collect(Collectors.joining(",", "(", ")"));
	}
	public Tree<E> child(int index) {
		return this.children().get(index);
	}
	public int childrenNumber() {
		return this.children().size();
	}
	public void toGraph(SimpleDirectedGraph<Tree<E>, DefaultEdge> graph) {
		if (!graph.containsVertex(this))
			graph.addVertex(this);
		this.children.stream().forEach(v -> {
			if (!graph.containsVertex(v))
				graph.addVertex(v);
		});
		this.children.stream().forEach(v -> graph.addEdge(this, v));
		this.children.stream().forEach(v -> v.toGraph(graph));
	}
}
