package us.lsi.tiposrecursivos;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import us.lsi.tiposrecursivos.Trees.TreeLevel;
import us.lsi.tiposrecursivos.parsers.TreeLexer;
import us.lsi.tiposrecursivos.parsers.TreeParser;

public sealed interface Tree<E> permits TEmpty, TLeaf, TNary{
	
	public enum TreeType{Empty,Leaf,Nary}
	
	public static <R> Tree<R> empty() {
		return new TEmpty<>();
	}
	
	public static <R> Tree<R> leaf(R label) {
		return new TLeaf<>(label);
	}

	public static <R> Tree<R> nary(R label, List<Tree<R>> elements) {
		if(elements.isEmpty()) return Tree.leaf(label);
		else return new TNary<R>(label,new ArrayList<>(elements));
	}

	@SafeVarargs
	public static <R> Tree<R> nary(R label, Tree<R>... elements) {
		List<Tree<R>> ls = Arrays.stream(elements).collect(Collectors.toList());
		if(ls.isEmpty()) return Tree.leaf(label);
		else return Tree.nary(label,ls);
	}

	@SuppressWarnings("unchecked")
	public static Tree<String> parse(String s){
		TreeLexer lexer = new TreeLexer(CharStreams.fromString(s));
		TreeParser parser = new TreeParser(new CommonTokenStream(lexer));
	    ParseTree parseTree = parser.nary_tree();
	    Tree<String> tree =  (Tree<String>) parseTree.accept(new TreeVisitorC());
	    return tree;
	}
	
	public static <R> Tree<R> parse(String s, Function<String,R> f){
		Tree<String> tree = Tree.parse(s);
		return tree.map(f);
	}
		
	TreeType type();
	boolean isEmpty();
	int size();
	default int sizeDifferent() {
		return (int) this.byDepth().distinct().count();
	}
	int height();
	Tree<E> copy();
	Tree<E> reverse();
	<R> Tree<R> map(Function<E, R> f);
	String toString();
	List<Tree<E>> children();
	int childrenNumber();
	Optional<E> optionalLabel();
	boolean equals(Object obj);
	int hashCode();
	void toGraph(SimpleDirectedGraph<Tree<E>, DefaultEdge> graph);
	
	default SimpleDirectedGraph<Tree<E>, DefaultEdge> toGraph() {
		SimpleDirectedGraph<Tree<E>, DefaultEdge> graph = 
				new SimpleDirectedGraph<>(null,()->new DefaultEdge(), false);
		this.toGraph(graph);
		return graph;
	}
	
	public default Stream<Tree<E>> byDepth(){
		return Trees.byDeph(this);
	}
	
	public default Stream<TreeLevel<E>> byLevel(){
		return Trees.byLevel(this);
	}
	
	public default List<Tree<E>> level(Integer n){
		return this.byLevel().filter(e->e.level().equals(n)).map(e->e.tree()).toList();
	}
	
}