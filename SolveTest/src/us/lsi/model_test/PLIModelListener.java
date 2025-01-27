// Generated from PLIModel.g4 by ANTLR 4.9.3
package us.lsi.model_test;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PLIModelParser}.
 */
public interface PLIModelListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#model}.
	 * @param ctx the parse tree
	 */
	void enterModel(PLIModelParser.ModelContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#model}.
	 * @param ctx the parse tree
	 */
	void exitModel(PLIModelParser.ModelContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#head}.
	 * @param ctx the parse tree
	 */
	void enterHead(PLIModelParser.HeadContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#head}.
	 * @param ctx the parse tree
	 */
	void exitHead(PLIModelParser.HeadContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varDeclar}
	 * labeled alternative in {@link PLIModelParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclar(PLIModelParser.VarDeclarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varDeclar}
	 * labeled alternative in {@link PLIModelParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclar(PLIModelParser.VarDeclarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funDeclar}
	 * labeled alternative in {@link PLIModelParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterFunDeclar(PLIModelParser.FunDeclarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funDeclar}
	 * labeled alternative in {@link PLIModelParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitFunDeclar(PLIModelParser.FunDeclarContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#formal_parameters}.
	 * @param ctx the parse tree
	 */
	void enterFormal_parameters(PLIModelParser.Formal_parametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#formal_parameters}.
	 * @param ctx the parse tree
	 */
	void exitFormal_parameters(PLIModelParser.Formal_parametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#formal_parameter}.
	 * @param ctx the parse tree
	 */
	void enterFormal_parameter(PLIModelParser.Formal_parameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#formal_parameter}.
	 * @param ctx the parse tree
	 */
	void exitFormal_parameter(PLIModelParser.Formal_parameterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code goalSection}
	 * labeled alternative in {@link PLIModelParser#goal}.
	 * @param ctx the parse tree
	 */
	void enterGoalSection(PLIModelParser.GoalSectionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code goalSection}
	 * labeled alternative in {@link PLIModelParser#goal}.
	 * @param ctx the parse tree
	 */
	void exitGoalSection(PLIModelParser.GoalSectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#constraints}.
	 * @param ctx the parse tree
	 */
	void enterConstraints(PLIModelParser.ConstraintsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#constraints}.
	 * @param ctx the parse tree
	 */
	void exitConstraints(PLIModelParser.ConstraintsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#c_list}.
	 * @param ctx the parse tree
	 */
	void enterC_list(PLIModelParser.C_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#c_list}.
	 * @param ctx the parse tree
	 */
	void exitC_list(PLIModelParser.C_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#list}.
	 * @param ctx the parse tree
	 */
	void enterList(PLIModelParser.ListContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#list}.
	 * @param ctx the parse tree
	 */
	void exitList(PLIModelParser.ListContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#indx}.
	 * @param ctx the parse tree
	 */
	void enterIndx(PLIModelParser.IndxContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#indx}.
	 * @param ctx the parse tree
	 */
	void exitIndx(PLIModelParser.IndxContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#indexed_elem}.
	 * @param ctx the parse tree
	 */
	void enterIndexed_elem(PLIModelParser.Indexed_elemContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#indexed_elem}.
	 * @param ctx the parse tree
	 */
	void exitIndexed_elem(PLIModelParser.Indexed_elemContext ctx);
	/**
	 * Enter a parse tree produced by the {@code differentValueConstraint}
	 * labeled alternative in {@link PLIModelParser#constraint}.
	 * @param ctx the parse tree
	 */
	void enterDifferentValueConstraint(PLIModelParser.DifferentValueConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code differentValueConstraint}
	 * labeled alternative in {@link PLIModelParser#constraint}.
	 * @param ctx the parse tree
	 */
	void exitDifferentValueConstraint(PLIModelParser.DifferentValueConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code atomConstraint}
	 * labeled alternative in {@link PLIModelParser#constraint}.
	 * @param ctx the parse tree
	 */
	void enterAtomConstraint(PLIModelParser.AtomConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code atomConstraint}
	 * labeled alternative in {@link PLIModelParser#constraint}.
	 * @param ctx the parse tree
	 */
	void exitAtomConstraint(PLIModelParser.AtomConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code maxConstraint}
	 * labeled alternative in {@link PLIModelParser#constraint}.
	 * @param ctx the parse tree
	 */
	void enterMaxConstraint(PLIModelParser.MaxConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code maxConstraint}
	 * labeled alternative in {@link PLIModelParser#constraint}.
	 * @param ctx the parse tree
	 */
	void exitMaxConstraint(PLIModelParser.MaxConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code orBinConstraint}
	 * labeled alternative in {@link PLIModelParser#constraint}.
	 * @param ctx the parse tree
	 */
	void enterOrBinConstraint(PLIModelParser.OrBinConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code orBinConstraint}
	 * labeled alternative in {@link PLIModelParser#constraint}.
	 * @param ctx the parse tree
	 */
	void exitOrBinConstraint(PLIModelParser.OrBinConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code minConstraint}
	 * labeled alternative in {@link PLIModelParser#constraint}.
	 * @param ctx the parse tree
	 */
	void enterMinConstraint(PLIModelParser.MinConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code minConstraint}
	 * labeled alternative in {@link PLIModelParser#constraint}.
	 * @param ctx the parse tree
	 */
	void exitMinConstraint(PLIModelParser.MinConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code equalsConstraint}
	 * labeled alternative in {@link PLIModelParser#constraint}.
	 * @param ctx the parse tree
	 */
	void enterEqualsConstraint(PLIModelParser.EqualsConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code equalsConstraint}
	 * labeled alternative in {@link PLIModelParser#constraint}.
	 * @param ctx the parse tree
	 */
	void exitEqualsConstraint(PLIModelParser.EqualsConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code valueInValuesConstraint}
	 * labeled alternative in {@link PLIModelParser#constraint}.
	 * @param ctx the parse tree
	 */
	void enterValueInValuesConstraint(PLIModelParser.ValueInValuesConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code valueInValuesConstraint}
	 * labeled alternative in {@link PLIModelParser#constraint}.
	 * @param ctx the parse tree
	 */
	void exitValueInValuesConstraint(PLIModelParser.ValueInValuesConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code allInValuesConstraint}
	 * labeled alternative in {@link PLIModelParser#constraint}.
	 * @param ctx the parse tree
	 */
	void enterAllInValuesConstraint(PLIModelParser.AllInValuesConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code allInValuesConstraint}
	 * labeled alternative in {@link PLIModelParser#constraint}.
	 * @param ctx the parse tree
	 */
	void exitAllInValuesConstraint(PLIModelParser.AllInValuesConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code allDifferentValuesConstraint}
	 * labeled alternative in {@link PLIModelParser#constraint}.
	 * @param ctx the parse tree
	 */
	void enterAllDifferentValuesConstraint(PLIModelParser.AllDifferentValuesConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code allDifferentValuesConstraint}
	 * labeled alternative in {@link PLIModelParser#constraint}.
	 * @param ctx the parse tree
	 */
	void exitAllDifferentValuesConstraint(PLIModelParser.AllDifferentValuesConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code absConstraint}
	 * labeled alternative in {@link PLIModelParser#constraint}.
	 * @param ctx the parse tree
	 */
	void enterAbsConstraint(PLIModelParser.AbsConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code absConstraint}
	 * labeled alternative in {@link PLIModelParser#constraint}.
	 * @param ctx the parse tree
	 */
	void exitAbsConstraint(PLIModelParser.AbsConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code indicatorConstraint}
	 * labeled alternative in {@link PLIModelParser#constraint}.
	 * @param ctx the parse tree
	 */
	void enterIndicatorConstraint(PLIModelParser.IndicatorConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code indicatorConstraint}
	 * labeled alternative in {@link PLIModelParser#constraint}.
	 * @param ctx the parse tree
	 */
	void exitIndicatorConstraint(PLIModelParser.IndicatorConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code implyConstraint}
	 * labeled alternative in {@link PLIModelParser#constraint}.
	 * @param ctx the parse tree
	 */
	void enterImplyConstraint(PLIModelParser.ImplyConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code implyConstraint}
	 * labeled alternative in {@link PLIModelParser#constraint}.
	 * @param ctx the parse tree
	 */
	void exitImplyConstraint(PLIModelParser.ImplyConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code orConstraint}
	 * labeled alternative in {@link PLIModelParser#constraint}.
	 * @param ctx the parse tree
	 */
	void enterOrConstraint(PLIModelParser.OrConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code orConstraint}
	 * labeled alternative in {@link PLIModelParser#constraint}.
	 * @param ctx the parse tree
	 */
	void exitOrConstraint(PLIModelParser.OrConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code piecewiseConstraint}
	 * labeled alternative in {@link PLIModelParser#constraint}.
	 * @param ctx the parse tree
	 */
	void enterPiecewiseConstraint(PLIModelParser.PiecewiseConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code piecewiseConstraint}
	 * labeled alternative in {@link PLIModelParser#constraint}.
	 * @param ctx the parse tree
	 */
	void exitPiecewiseConstraint(PLIModelParser.PiecewiseConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andBinConstraint}
	 * labeled alternative in {@link PLIModelParser#constraint}.
	 * @param ctx the parse tree
	 */
	void enterAndBinConstraint(PLIModelParser.AndBinConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andBinConstraint}
	 * labeled alternative in {@link PLIModelParser#constraint}.
	 * @param ctx the parse tree
	 */
	void exitAndBinConstraint(PLIModelParser.AndBinConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#pair}.
	 * @param ctx the parse tree
	 */
	void enterPair(PLIModelParser.PairContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#pair}.
	 * @param ctx the parse tree
	 */
	void exitPair(PLIModelParser.PairContext ctx);
	/**
	 * Enter a parse tree produced by the {@code factorGenerateExp}
	 * labeled alternative in {@link PLIModelParser#generate_exp}.
	 * @param ctx the parse tree
	 */
	void enterFactorGenerateExp(PLIModelParser.FactorGenerateExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code factorGenerateExp}
	 * labeled alternative in {@link PLIModelParser#generate_exp}.
	 * @param ctx the parse tree
	 */
	void exitFactorGenerateExp(PLIModelParser.FactorGenerateExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sumGenerateExp}
	 * labeled alternative in {@link PLIModelParser#generate_exp}.
	 * @param ctx the parse tree
	 */
	void enterSumGenerateExp(PLIModelParser.SumGenerateExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sumGenerateExp}
	 * labeled alternative in {@link PLIModelParser#generate_exp}.
	 * @param ctx the parse tree
	 */
	void exitSumGenerateExp(PLIModelParser.SumGenerateExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#generate_exps}.
	 * @param ctx the parse tree
	 */
	void enterGenerate_exps(PLIModelParser.Generate_expsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#generate_exps}.
	 * @param ctx the parse tree
	 */
	void exitGenerate_exps(PLIModelParser.Generate_expsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code plusFactor}
	 * labeled alternative in {@link PLIModelParser#s_factor}.
	 * @param ctx the parse tree
	 */
	void enterPlusFactor(PLIModelParser.PlusFactorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code plusFactor}
	 * labeled alternative in {@link PLIModelParser#s_factor}.
	 * @param ctx the parse tree
	 */
	void exitPlusFactor(PLIModelParser.PlusFactorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code minusFactor}
	 * labeled alternative in {@link PLIModelParser#s_factor}.
	 * @param ctx the parse tree
	 */
	void enterMinusFactor(PLIModelParser.MinusFactorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code minusFactor}
	 * labeled alternative in {@link PLIModelParser#s_factor}.
	 * @param ctx the parse tree
	 */
	void exitMinusFactor(PLIModelParser.MinusFactorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code plusSum}
	 * labeled alternative in {@link PLIModelParser#s_factor}.
	 * @param ctx the parse tree
	 */
	void enterPlusSum(PLIModelParser.PlusSumContext ctx);
	/**
	 * Exit a parse tree produced by the {@code plusSum}
	 * labeled alternative in {@link PLIModelParser#s_factor}.
	 * @param ctx the parse tree
	 */
	void exitPlusSum(PLIModelParser.PlusSumContext ctx);
	/**
	 * Enter a parse tree produced by the {@code minusSum}
	 * labeled alternative in {@link PLIModelParser#s_factor}.
	 * @param ctx the parse tree
	 */
	void enterMinusSum(PLIModelParser.MinusSumContext ctx);
	/**
	 * Exit a parse tree produced by the {@code minusSum}
	 * labeled alternative in {@link PLIModelParser#s_factor}.
	 * @param ctx the parse tree
	 */
	void exitMinusSum(PLIModelParser.MinusSumContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varFactor}
	 * labeled alternative in {@link PLIModelParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterVarFactor(PLIModelParser.VarFactorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varFactor}
	 * labeled alternative in {@link PLIModelParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitVarFactor(PLIModelParser.VarFactorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expFactor}
	 * labeled alternative in {@link PLIModelParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterExpFactor(PLIModelParser.ExpFactorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expFactor}
	 * labeled alternative in {@link PLIModelParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitExpFactor(PLIModelParser.ExpFactorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varIdFactor}
	 * labeled alternative in {@link PLIModelParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterVarIdFactor(PLIModelParser.VarIdFactorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varIdFactor}
	 * labeled alternative in {@link PLIModelParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitVarIdFactor(PLIModelParser.VarIdFactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#var_id}.
	 * @param ctx the parse tree
	 */
	void enterVar_id(PLIModelParser.Var_idContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#var_id}.
	 * @param ctx the parse tree
	 */
	void exitVar_id(PLIModelParser.Var_idContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#var_ids}.
	 * @param ctx the parse tree
	 */
	void enterVar_ids(PLIModelParser.Var_idsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#var_ids}.
	 * @param ctx the parse tree
	 */
	void exitVar_ids(PLIModelParser.Var_idsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#index_var_id}.
	 * @param ctx the parse tree
	 */
	void enterIndex_var_id(PLIModelParser.Index_var_idContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#index_var_id}.
	 * @param ctx the parse tree
	 */
	void exitIndex_var_id(PLIModelParser.Index_var_idContext ctx);
	/**
	 * Enter a parse tree produced by the {@code oneSideBound}
	 * labeled alternative in {@link PLIModelParser#bound}.
	 * @param ctx the parse tree
	 */
	void enterOneSideBound(PLIModelParser.OneSideBoundContext ctx);
	/**
	 * Exit a parse tree produced by the {@code oneSideBound}
	 * labeled alternative in {@link PLIModelParser#bound}.
	 * @param ctx the parse tree
	 */
	void exitOneSideBound(PLIModelParser.OneSideBoundContext ctx);
	/**
	 * Enter a parse tree produced by the {@code twoSideBound}
	 * labeled alternative in {@link PLIModelParser#bound}.
	 * @param ctx the parse tree
	 */
	void enterTwoSideBound(PLIModelParser.TwoSideBoundContext ctx);
	/**
	 * Exit a parse tree produced by the {@code twoSideBound}
	 * labeled alternative in {@link PLIModelParser#bound}.
	 * @param ctx the parse tree
	 */
	void exitTwoSideBound(PLIModelParser.TwoSideBoundContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#bounds}.
	 * @param ctx the parse tree
	 */
	void enterBounds(PLIModelParser.BoundsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#bounds}.
	 * @param ctx the parse tree
	 */
	void exitBounds(PLIModelParser.BoundsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#bin_vars}.
	 * @param ctx the parse tree
	 */
	void enterBin_vars(PLIModelParser.Bin_varsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#bin_vars}.
	 * @param ctx the parse tree
	 */
	void exitBin_vars(PLIModelParser.Bin_varsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#int_vars}.
	 * @param ctx the parse tree
	 */
	void enterInt_vars(PLIModelParser.Int_varsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#int_vars}.
	 * @param ctx the parse tree
	 */
	void exitInt_vars(PLIModelParser.Int_varsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#free_vars}.
	 * @param ctx the parse tree
	 */
	void enterFree_vars(PLIModelParser.Free_varsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#free_vars}.
	 * @param ctx the parse tree
	 */
	void exitFree_vars(PLIModelParser.Free_varsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#semi_continuous_vars}.
	 * @param ctx the parse tree
	 */
	void enterSemi_continuous_vars(PLIModelParser.Semi_continuous_varsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#semi_continuous_vars}.
	 * @param ctx the parse tree
	 */
	void exitSemi_continuous_vars(PLIModelParser.Semi_continuous_varsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryOpExpr}
	 * labeled alternative in {@link PLIModelParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterUnaryOpExpr(PLIModelParser.UnaryOpExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryOpExpr}
	 * labeled alternative in {@link PLIModelParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitUnaryOpExpr(PLIModelParser.UnaryOpExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intExpr}
	 * labeled alternative in {@link PLIModelParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterIntExpr(PLIModelParser.IntExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intExpr}
	 * labeled alternative in {@link PLIModelParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitIntExpr(PLIModelParser.IntExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code opExpr}
	 * labeled alternative in {@link PLIModelParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterOpExpr(PLIModelParser.OpExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code opExpr}
	 * labeled alternative in {@link PLIModelParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitOpExpr(PLIModelParser.OpExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funExpr}
	 * labeled alternative in {@link PLIModelParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterFunExpr(PLIModelParser.FunExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funExpr}
	 * labeled alternative in {@link PLIModelParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitFunExpr(PLIModelParser.FunExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link PLIModelParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterParenExpr(PLIModelParser.ParenExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link PLIModelParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitParenExpr(PLIModelParser.ParenExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code doubleExp}
	 * labeled alternative in {@link PLIModelParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterDoubleExp(PLIModelParser.DoubleExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code doubleExp}
	 * labeled alternative in {@link PLIModelParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitDoubleExp(PLIModelParser.DoubleExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code idExpr}
	 * labeled alternative in {@link PLIModelParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterIdExpr(PLIModelParser.IdExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code idExpr}
	 * labeled alternative in {@link PLIModelParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitIdExpr(PLIModelParser.IdExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#call_function}.
	 * @param ctx the parse tree
	 */
	void enterCall_function(PLIModelParser.Call_functionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#call_function}.
	 * @param ctx the parse tree
	 */
	void exitCall_function(PLIModelParser.Call_functionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#exps}.
	 * @param ctx the parse tree
	 */
	void enterExps(PLIModelParser.ExpsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#exps}.
	 * @param ctx the parse tree
	 */
	void exitExps(PLIModelParser.ExpsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#rel_op}.
	 * @param ctx the parse tree
	 */
	void enterRel_op(PLIModelParser.Rel_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#rel_op}.
	 * @param ctx the parse tree
	 */
	void exitRel_op(PLIModelParser.Rel_opContext ctx);
}