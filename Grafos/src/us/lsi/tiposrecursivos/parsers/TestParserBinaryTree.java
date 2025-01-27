package us.lsi.tiposrecursivos.parsers;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.BinaryTreeVisitorC;



public class TestParserBinaryTree {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		BinaryTreeLexer lexer = new BinaryTreeLexer(CharStreams.fromString("-43.7(2.1,abc34(-27.3(_,2),78.2(3,4)))"));
		BinaryTreeParser parser = new BinaryTreeParser(new CommonTokenStream(lexer));
	    ParseTree parseTree = parser.binary_tree();
	    BinaryTree<String> tree =  (BinaryTree<String>) parseTree.accept(new BinaryTreeVisitorC());
	    System.out.println(tree);
	}

}
