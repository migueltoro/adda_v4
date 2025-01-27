package us.lsi.tiposrecursivos.ast;

public sealed interface Declaration extends Vertex  permits FunDeclaration, VarDeclaration {
	String name();
}
