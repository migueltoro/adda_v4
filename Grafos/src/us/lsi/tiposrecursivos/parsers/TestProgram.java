package us.lsi.tiposrecursivos.parsers;

import java.io.IOException;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import us.lsi.tiposrecursivos.ast.Ast;
import us.lsi.tiposrecursivos.ast.AstVisitorC;
import us.lsi.tiposrecursivos.parsers.program.ProgramLexer;
import us.lsi.tiposrecursivos.parsers.program.ProgramParser;


public class TestProgram {
		
		public static void main(String[] args) throws IOException {
			ProgramLexer lexer = new ProgramLexer(CharStreams.fromFileName("ficheros/program.txt"));
			ProgramParser parser = new ProgramParser(new CommonTokenStream(lexer));
		    ParseTree parseTree = parser.program();
		    Ast program =  (Ast) parseTree.accept(new AstVisitorC());
		    System.out.println(program);
		}

	}

