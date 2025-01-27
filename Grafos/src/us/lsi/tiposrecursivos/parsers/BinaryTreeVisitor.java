// Generated from BinaryTree.g4 by ANTLR 4.9.3
package us.lsi.tiposrecursivos.parsers;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link BinaryTreeParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface BinaryTreeVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code emptyTree}
	 * labeled alternative in {@link BinaryTreeParser#binary_tree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyTree(BinaryTreeParser.EmptyTreeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code labelTree}
	 * labeled alternative in {@link BinaryTreeParser#binary_tree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabelTree(BinaryTreeParser.LabelTreeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binaryTree}
	 * labeled alternative in {@link BinaryTreeParser#binary_tree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryTree(BinaryTreeParser.BinaryTreeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code idLabel}
	 * labeled alternative in {@link BinaryTreeParser#label}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdLabel(BinaryTreeParser.IdLabelContext ctx);
}