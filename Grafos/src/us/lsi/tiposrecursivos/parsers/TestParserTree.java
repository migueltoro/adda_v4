package us.lsi.tiposrecursivos.parsers;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import us.lsi.tiposrecursivos.Tree;
import us.lsi.tiposrecursivos.TreeVisitorC;



public class TestParserTree {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		TreeLexer lexer = new TreeLexer(CharStreams.fromString("39(2.,_abc(_,2,3,4),9(8.,_))"));
		TreeParser parser = new TreeParser(new CommonTokenStream(lexer));
	    ParseTree parseTree = parser.nary_tree();
	    Tree<String> tree =  (Tree<String>) parseTree.accept(new TreeVisitorC());
	    System.out.println(tree);
	}

}
