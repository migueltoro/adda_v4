package us.lsi.model_test_old;

// Generated from PLIModel.g4 by ANTLR 4.8
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link PLIModelParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface PLIModelVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#model}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModel(PLIModelParser.ModelContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#head}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHead(PLIModelParser.HeadContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varDeclar}
	 * labeled alternative in {@link PLIModelParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDeclar(PLIModelParser.VarDeclarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funDeclar}
	 * labeled alternative in {@link PLIModelParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunDeclar(PLIModelParser.FunDeclarContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#formal_parameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormal_parameters(PLIModelParser.Formal_parametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#formal_parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormal_parameter(PLIModelParser.Formal_parameterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code goalSection}
	 * labeled alternative in {@link PLIModelParser#goal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGoalSection(PLIModelParser.GoalSectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#constraints}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstraints(PLIModelParser.ConstraintsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitList(PLIModelParser.ListContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#indx}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndx(PLIModelParser.IndxContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#indexed_elem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexed_elem(PLIModelParser.Indexed_elemContext ctx);
	/**
	 * Visit a parse tree produced by the {@code differentValueConstraint}
	 * labeled alternative in {@link PLIModelParser#constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDifferentValueConstraint(PLIModelParser.DifferentValueConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code allDifferentValuesConstraint}
	 * labeled alternative in {@link PLIModelParser#constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAllDifferentValuesConstraint(PLIModelParser.AllDifferentValuesConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code indicatorConstraint}
	 * labeled alternative in {@link PLIModelParser#constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndicatorConstraint(PLIModelParser.IndicatorConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code implyConstraint}
	 * labeled alternative in {@link PLIModelParser#constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImplyConstraint(PLIModelParser.ImplyConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atomConstraint}
	 * labeled alternative in {@link PLIModelParser#constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomConstraint(PLIModelParser.AtomConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code orConstraint}
	 * labeled alternative in {@link PLIModelParser#constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrConstraint(PLIModelParser.OrConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code factorGenerateExp}
	 * labeled alternative in {@link PLIModelParser#generate_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactorGenerateExp(PLIModelParser.FactorGenerateExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sumGenerateExp}
	 * labeled alternative in {@link PLIModelParser#generate_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSumGenerateExp(PLIModelParser.SumGenerateExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code plusFactor}
	 * labeled alternative in {@link PLIModelParser#s_factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlusFactor(PLIModelParser.PlusFactorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code plusSum}
	 * labeled alternative in {@link PLIModelParser#s_factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlusSum(PLIModelParser.PlusSumContext ctx);
	/**
	 * Visit a parse tree produced by the {@code minusSum}
	 * labeled alternative in {@link PLIModelParser#s_factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMinusSum(PLIModelParser.MinusSumContext ctx);
	/**
	 * Visit a parse tree produced by the {@code minusFactor}
	 * labeled alternative in {@link PLIModelParser#s_factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMinusFactor(PLIModelParser.MinusFactorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varFactor}
	 * labeled alternative in {@link PLIModelParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarFactor(PLIModelParser.VarFactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#var_id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_id(PLIModelParser.Var_idContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#index_var_id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndex_var_id(PLIModelParser.Index_var_idContext ctx);
	/**
	 * Visit a parse tree produced by the {@code oneSideBound}
	 * labeled alternative in {@link PLIModelParser#bound}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOneSideBound(PLIModelParser.OneSideBoundContext ctx);
	/**
	 * Visit a parse tree produced by the {@code twoSideBound}
	 * labeled alternative in {@link PLIModelParser#bound}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTwoSideBound(PLIModelParser.TwoSideBoundContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#bounds}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBounds(PLIModelParser.BoundsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#bin_vars}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBin_vars(PLIModelParser.Bin_varsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#int_vars}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt_vars(PLIModelParser.Int_varsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#free_vars}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFree_vars(PLIModelParser.Free_varsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unaryOpExpr}
	 * labeled alternative in {@link PLIModelParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOpExpr(PLIModelParser.UnaryOpExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intExpr}
	 * labeled alternative in {@link PLIModelParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntExpr(PLIModelParser.IntExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code opExpr}
	 * labeled alternative in {@link PLIModelParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpExpr(PLIModelParser.OpExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funExpr}
	 * labeled alternative in {@link PLIModelParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunExpr(PLIModelParser.FunExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link PLIModelParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExpr(PLIModelParser.ParenExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code doubleExp}
	 * labeled alternative in {@link PLIModelParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoubleExp(PLIModelParser.DoubleExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code idExpr}
	 * labeled alternative in {@link PLIModelParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdExpr(PLIModelParser.IdExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#call_function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCall_function(PLIModelParser.Call_functionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#real_parameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReal_parameters(PLIModelParser.Real_parametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#rel_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRel_op(PLIModelParser.Rel_opContext ctx);
}