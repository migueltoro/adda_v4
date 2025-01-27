package us.lsi.tiposrecursivos.ast;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.nio.Attribute;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.colors.GraphColors.Style;
import us.lsi.tiposrecursivos.parsers.program.ProgramLexer;
import us.lsi.tiposrecursivos.parsers.program.ProgramParser;

public record Ast(List<ParamDeclaration> parameters, Block block) implements Vertex {
	
	public static Ast of(List<ParamDeclaration> parameters,Block block) {
		return new Ast(parameters,block);
	}
	
	public static Ast parse(String file) throws IOException {
		ProgramLexer lexer = new ProgramLexer(CharStreams.fromFileName(file));
		ProgramParser parser = new ProgramParser(new CommonTokenStream(lexer));
	    ParseTree parseTree = parser.program();
	    Ast program =  (Ast) parseTree.accept(new AstVisitorC());
	    return program;
	}
	
	public static Block parseBlock(String file) throws IOException {
		ProgramLexer lexer = new ProgramLexer(CharStreams.fromFileName(file));
		ProgramParser parser = new ProgramParser(new CommonTokenStream(lexer));
	    ParseTree parseTree = parser.block();
	    Block block =  (Block) parseTree.accept(new AstVisitorC());
	    return block;
	}
	

	@Override
	public String toString() {
		String b = this.block()!=null?this.block().toString():"";
		return String.format("Program\n%s",b);
	}
	
	@Override
	public void toGraph(SimpleDirectedGraph<Vertex, DefaultEdge> graph) {
		if(!graph.containsVertex(this)) graph.addVertex(this);
		Integer n = this.parameters().size();
		Vertex p = Vertex.of("Pp",Vertex.circle(Color.green));
		graph.addVertex(p);
		graph.addEdge(this,p);
		for( int i = 0; i< n; i++) {
			if(!graph.containsVertex(this.parameters().get(i))) graph.addVertex(this.parameters().get(i));
			graph.addEdge(p,this.parameters().get(i));
		}
		if(!graph.containsVertex(this.block())) graph.addVertex(this.block());
		graph.addEdge(this,this.block());
		this.block().toGraph(graph);
	}

	@Override
	public String label() {
		return "P";
	}

	@Override
	public Map<String,Attribute> styleAndShape() {
		Map<String,Attribute> m1 = new HashMap<>(GraphColors.color(Color.blue));
		Map<String,Attribute> m2 = GraphColors.style(Style.bold);
		m1.putAll(m2);
		return m1;
	}
	
	public static void main(String[] args) throws IOException {

	}
}
