package us.lsi.tiposrecursivos.ast;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import us.lsi.colors.GraphColors.Color;

public record Block(List<Declaration> declarations, List<Sentence> sentences, Map<String,Object> symbolTable) implements Sentence{
	
	public static Block of(List<Declaration> declarations,List<Sentence> sentences, Map<String,Object> symbolTable) {
		return new Block(declarations,sentences,symbolTable);
	}

	@Override
	public String toString() {
		String d = "";
		if(!this.declarations().isEmpty()) {
			d = this.declarations().stream()
					.map(x -> x.toString())
					.collect(Collectors.joining("\n"));
		}
		String s = this.sentences().stream().map(x -> x.toString())
				.collect(Collectors.joining("\n"));
		if(!this.declarations().isEmpty())
			return String.format("{\n%s\n%s\n}", d, s);
		else
			return String.format("{\n%s\n}", s);
	}
	
	@Override
	public String name() {
		return "Bloque";
	}
	
	public Boolean isEmpty() {
		return this.declarations().isEmpty() && this.sentences().isEmpty();
	}
	
	@Override
	public void toGraph(SimpleDirectedGraph<Vertex, DefaultEdge> graph) {
		Integer n = this.declarations().size();
		Integer m = this.sentences().size();
		if (!this.isEmpty() && !graph.containsVertex(this))
			graph.addVertex(this);
		if (n > 0) {
			Vertex p = Vertex.of("Bd",Vertex.circle(Color.red));
			graph.addVertex(p);
			graph.addEdge(this,p);
			this.declarations().stream()
				.filter(v -> !graph.containsVertex(v))
				.forEach(v -> graph.addVertex(v));
			IntStream.range(0, n)
				.forEach(i -> graph.addEdge(p, this.declarations().get(i)));
			this.declarations().stream().forEach(v -> v.toGraph(graph));
		}		
		if (m > 0) {
			this.sentences().stream()
				.filter(v -> !graph.containsVertex(v))
				.forEach(v -> graph.addVertex(v));
			graph.addEdge(this, this.sentences().get(0));
			IntStream.range(0, m - 1).forEach(i -> graph.addEdge(this.sentences().get(i), this.sentences().get(i + 1)));
			this.sentences().stream().forEach(v -> v.toGraph(graph));	
		}
		
	}

	@Override
	public String label() {
		return "B";
	}

}
