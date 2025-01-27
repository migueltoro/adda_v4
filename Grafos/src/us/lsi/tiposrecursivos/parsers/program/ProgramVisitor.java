// Generated from Program.g4 by ANTLR 4.9.3
package us.lsi.tiposrecursivos.parsers.program;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ProgramParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ProgramVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ProgramParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(ProgramParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varDeclaration}
	 * labeled alternative in {@link ProgramParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDeclaration(ProgramParser.VarDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funDeclaration}
	 * labeled alternative in {@link ProgramParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunDeclaration(ProgramParser.FunDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProgramParser#formal_parameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormal_parameters(ProgramParser.Formal_parametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProgramParser#formal_parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormal_parameter(ProgramParser.Formal_parameterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code asignSentence}
	 * labeled alternative in {@link ProgramParser#sentence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsignSentence(ProgramParser.AsignSentenceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifSentence}
	 * labeled alternative in {@link ProgramParser#sentence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfSentence(ProgramParser.IfSentenceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code whileSentence}
	 * labeled alternative in {@link ProgramParser#sentence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileSentence(ProgramParser.WhileSentenceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code returnSentence}
	 * labeled alternative in {@link ProgramParser#sentence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnSentence(ProgramParser.ReturnSentenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProgramParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(ProgramParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code strExpr}
	 * labeled alternative in {@link ProgramParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStrExpr(ProgramParser.StrExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unaryExpr}
	 * labeled alternative in {@link ProgramParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpr(ProgramParser.UnaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intExpr}
	 * labeled alternative in {@link ProgramParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntExpr(ProgramParser.IntExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ternaryExpr}
	 * labeled alternative in {@link ProgramParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTernaryExpr(ProgramParser.TernaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binaryExpr}
	 * labeled alternative in {@link ProgramParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryExpr(ProgramParser.BinaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callExpr}
	 * labeled alternative in {@link ProgramParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallExpr(ProgramParser.CallExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolExpr}
	 * labeled alternative in {@link ProgramParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolExpr(ProgramParser.BoolExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link ProgramParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExpr(ProgramParser.ParenExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code doubleExp}
	 * labeled alternative in {@link ProgramParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoubleExp(ProgramParser.DoubleExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code idExpr}
	 * labeled alternative in {@link ProgramParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdExpr(ProgramParser.IdExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProgramParser#real_parameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReal_parameters(ProgramParser.Real_parametersContext ctx);
}