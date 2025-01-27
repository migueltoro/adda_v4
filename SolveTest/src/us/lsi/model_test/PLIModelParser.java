// Generated from PLIModel.g4 by ANTLR 4.9.3
package us.lsi.model_test;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PLIModelParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		T__45=46, T__46=47, T__47=48, T__48=49, ID=50, INT=51, DOUBLE=52, WS=53, 
		COMMENT=54, LINE_COMMENT=55;
	public static final int
		RULE_model = 0, RULE_head = 1, RULE_declaration = 2, RULE_formal_parameters = 3, 
		RULE_formal_parameter = 4, RULE_goal = 5, RULE_constraints = 6, RULE_c_list = 7, 
		RULE_list = 8, RULE_indx = 9, RULE_indexed_elem = 10, RULE_constraint = 11, 
		RULE_pair = 12, RULE_generate_exp = 13, RULE_generate_exps = 14, RULE_s_factor = 15, 
		RULE_factor = 16, RULE_var_id = 17, RULE_var_ids = 18, RULE_index_var_id = 19, 
		RULE_bound = 20, RULE_bounds = 21, RULE_bin_vars = 22, RULE_int_vars = 23, 
		RULE_free_vars = 24, RULE_semi_continuous_vars = 25, RULE_exp = 26, RULE_call_function = 27, 
		RULE_exps = 28, RULE_rel_op = 29;
	private static String[] makeRuleNames() {
		return new String[] {
			"model", "head", "declaration", "formal_parameters", "formal_parameter", 
			"goal", "constraints", "c_list", "list", "indx", "indexed_elem", "constraint", 
			"pair", "generate_exp", "generate_exps", "s_factor", "factor", "var_id", 
			"var_ids", "index_var_id", "bound", "bounds", "bin_vars", "int_vars", 
			"free_vars", "semi_continuous_vars", "exp", "call_function", "exps", 
			"rel_op"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'head section'", "'='", "'('", "')'", "','", "'goal section'", 
			"'min'", "'max'", "'constraints section'", "'|'", "'in'", "'..'", "'->'", 
			"'=='", "'or'", "'=>'", "'!='", "'allDifferent'", "'permutation'", "';'", 
			"'MAX'", "'MIN'", "'OR'", "'AND'", "'ABS'", "'PWL'", "':'", "'sum'", 
			"'+'", "'-'", "'['", "']'", "'<='", "'bounds section'", "'bin'", "'int'", 
			"'free'", "'semi-continuous'", "'(int)'", "'(double)'", "'!'", "'*'", 
			"'/'", "'%'", "'<'", "'>='", "'>'", "'&&'", "'||'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "ID", "INT", "DOUBLE", "WS", "COMMENT", "LINE_COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "PLIModel.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PLIModelParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ModelContext extends ParserRuleContext {
		public GoalContext goal() {
			return getRuleContext(GoalContext.class,0);
		}
		public ConstraintsContext constraints() {
			return getRuleContext(ConstraintsContext.class,0);
		}
		public HeadContext head() {
			return getRuleContext(HeadContext.class,0);
		}
		public BoundsContext bounds() {
			return getRuleContext(BoundsContext.class,0);
		}
		public Bin_varsContext bin_vars() {
			return getRuleContext(Bin_varsContext.class,0);
		}
		public Int_varsContext int_vars() {
			return getRuleContext(Int_varsContext.class,0);
		}
		public Free_varsContext free_vars() {
			return getRuleContext(Free_varsContext.class,0);
		}
		public Semi_continuous_varsContext semi_continuous_vars() {
			return getRuleContext(Semi_continuous_varsContext.class,0);
		}
		public ModelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_model; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterModel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitModel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitModel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModelContext model() throws RecognitionException {
		ModelContext _localctx = new ModelContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_model);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(60);
				head();
				}
			}

			setState(63);
			goal();
			setState(64);
			constraints();
			setState(66);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__33) {
				{
				setState(65);
				bounds();
				}
			}

			setState(69);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__34) {
				{
				setState(68);
				bin_vars();
				}
			}

			setState(72);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__35) {
				{
				setState(71);
				int_vars();
				}
			}

			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__36) {
				{
				setState(74);
				free_vars();
				}
			}

			setState(78);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__37) {
				{
				setState(77);
				semi_continuous_vars();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HeadContext extends ParserRuleContext {
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public HeadContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_head; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterHead(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitHead(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitHead(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HeadContext head() throws RecognitionException {
		HeadContext _localctx = new HeadContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_head);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(T__0);
			setState(84);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(81);
				declaration();
				}
				}
				setState(86);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationContext extends ParserRuleContext {
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
	 
		public DeclarationContext() { }
		public void copyFrom(DeclarationContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class FunDeclarContext extends DeclarationContext {
		public Token type;
		public Token name;
		public List<TerminalNode> ID() { return getTokens(PLIModelParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(PLIModelParser.ID, i);
		}
		public Formal_parametersContext formal_parameters() {
			return getRuleContext(Formal_parametersContext.class,0);
		}
		public FunDeclarContext(DeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterFunDeclar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitFunDeclar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitFunDeclar(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VarDeclarContext extends DeclarationContext {
		public Token type;
		public Token name;
		public ExpContext val;
		public List<TerminalNode> ID() { return getTokens(PLIModelParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(PLIModelParser.ID, i);
		}
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public VarDeclarContext(DeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterVarDeclar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitVarDeclar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitVarDeclar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declaration);
		int _la;
		try {
			setState(98);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				_localctx = new VarDeclarContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(87);
				((VarDeclarContext)_localctx).type = match(ID);
				setState(88);
				((VarDeclarContext)_localctx).name = match(ID);
				setState(89);
				match(T__1);
				setState(90);
				((VarDeclarContext)_localctx).val = exp(0);
				}
				break;
			case 2:
				_localctx = new FunDeclarContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(91);
				((FunDeclarContext)_localctx).type = match(ID);
				setState(92);
				((FunDeclarContext)_localctx).name = match(ID);
				setState(93);
				match(T__2);
				setState(95);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(94);
					formal_parameters();
					}
				}

				setState(97);
				match(T__3);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Formal_parametersContext extends ParserRuleContext {
		public List<Formal_parameterContext> formal_parameter() {
			return getRuleContexts(Formal_parameterContext.class);
		}
		public Formal_parameterContext formal_parameter(int i) {
			return getRuleContext(Formal_parameterContext.class,i);
		}
		public Formal_parametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formal_parameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterFormal_parameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitFormal_parameters(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitFormal_parameters(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Formal_parametersContext formal_parameters() throws RecognitionException {
		Formal_parametersContext _localctx = new Formal_parametersContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_formal_parameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			formal_parameter();
			setState(105);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(101);
				match(T__4);
				setState(102);
				formal_parameter();
				}
				}
				setState(107);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Formal_parameterContext extends ParserRuleContext {
		public Token type;
		public Token name;
		public List<TerminalNode> ID() { return getTokens(PLIModelParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(PLIModelParser.ID, i);
		}
		public Formal_parameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formal_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterFormal_parameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitFormal_parameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitFormal_parameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Formal_parameterContext formal_parameter() throws RecognitionException {
		Formal_parameterContext _localctx = new Formal_parameterContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_formal_parameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			((Formal_parameterContext)_localctx).type = match(ID);
			setState(110);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(109);
				((Formal_parameterContext)_localctx).name = match(ID);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GoalContext extends ParserRuleContext {
		public GoalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_goal; }
	 
		public GoalContext() { }
		public void copyFrom(GoalContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class GoalSectionContext extends GoalContext {
		public Token obj;
		public Generate_expContext generate_exp() {
			return getRuleContext(Generate_expContext.class,0);
		}
		public GoalSectionContext(GoalContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterGoalSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitGoalSection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitGoalSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GoalContext goal() throws RecognitionException {
		GoalContext _localctx = new GoalContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_goal);
		int _la;
		try {
			_localctx = new GoalSectionContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			match(T__5);
			setState(113);
			((GoalSectionContext)_localctx).obj = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==T__6 || _la==T__7) ) {
				((GoalSectionContext)_localctx).obj = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(114);
			generate_exp();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstraintsContext extends ParserRuleContext {
		public List<ListContext> list() {
			return getRuleContexts(ListContext.class);
		}
		public ListContext list(int i) {
			return getRuleContext(ListContext.class,i);
		}
		public ConstraintsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constraints; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterConstraints(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitConstraints(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitConstraints(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstraintsContext constraints() throws RecognitionException {
		ConstraintsContext _localctx = new ConstraintsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_constraints);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			match(T__8);
			setState(118); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(117);
				list();
				}
				}
				setState(120); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__14) | (1L << T__17) | (1L << T__18) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__38) | (1L << T__39) | (1L << T__40) | (1L << ID) | (1L << INT) | (1L << DOUBLE))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class C_listContext extends ParserRuleContext {
		public Indexed_elemContext indexed_elem() {
			return getRuleContext(Indexed_elemContext.class,0);
		}
		public List<IndxContext> indx() {
			return getRuleContexts(IndxContext.class);
		}
		public IndxContext indx(int i) {
			return getRuleContext(IndxContext.class,i);
		}
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public C_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_c_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterC_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitC_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitC_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final C_listContext c_list() throws RecognitionException {
		C_listContext _localctx = new C_listContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_c_list);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			indexed_elem();
			setState(127);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(123);
					match(T__4);
					setState(124);
					indx();
					}
					} 
				}
				setState(129);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			}
			setState(132);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(130);
				match(T__9);
				setState(131);
				exp(0);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListContext extends ParserRuleContext {
		public C_listContext c_list() {
			return getRuleContext(C_listContext.class,0);
		}
		public Var_idsContext var_ids() {
			return getRuleContext(Var_idsContext.class,0);
		}
		public ExpsContext exps() {
			return getRuleContext(ExpsContext.class,0);
		}
		public Generate_expsContext generate_exps() {
			return getRuleContext(Generate_expsContext.class,0);
		}
		public ListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListContext list() throws RecognitionException {
		ListContext _localctx = new ListContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_list);
		try {
			setState(138);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(134);
				c_list();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(135);
				var_ids();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(136);
				exps();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(137);
				generate_exps();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IndxContext extends ParserRuleContext {
		public Token index_name;
		public ExpContext li;
		public ExpContext ls;
		public TerminalNode ID() { return getToken(PLIModelParser.ID, 0); }
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public IndxContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indx; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterIndx(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitIndx(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitIndx(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndxContext indx() throws RecognitionException {
		IndxContext _localctx = new IndxContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_indx);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
			((IndxContext)_localctx).index_name = match(ID);
			setState(141);
			match(T__10);
			setState(142);
			((IndxContext)_localctx).li = exp(0);
			setState(143);
			match(T__11);
			setState(144);
			((IndxContext)_localctx).ls = exp(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Indexed_elemContext extends ParserRuleContext {
		public ConstraintContext constraint() {
			return getRuleContext(ConstraintContext.class,0);
		}
		public BoundContext bound() {
			return getRuleContext(BoundContext.class,0);
		}
		public Generate_expContext generate_exp() {
			return getRuleContext(Generate_expContext.class,0);
		}
		public Indexed_elemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexed_elem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterIndexed_elem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitIndexed_elem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitIndexed_elem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Indexed_elemContext indexed_elem() throws RecognitionException {
		Indexed_elemContext _localctx = new Indexed_elemContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_indexed_elem);
		try {
			setState(149);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(146);
				constraint(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(147);
				bound();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(148);
				generate_exp();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstraintContext extends ParserRuleContext {
		public ConstraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constraint; }
	 
		public ConstraintContext() { }
		public void copyFrom(ConstraintContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class DifferentValueConstraintContext extends ConstraintContext {
		public Var_idContext left;
		public Var_idContext right;
		public List<Var_idContext> var_id() {
			return getRuleContexts(Var_idContext.class);
		}
		public Var_idContext var_id(int i) {
			return getRuleContext(Var_idContext.class,i);
		}
		public DifferentValueConstraintContext(ConstraintContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterDifferentValueConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitDifferentValueConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitDifferentValueConstraint(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AtomConstraintContext extends ConstraintContext {
		public Generate_expContext generate_exp() {
			return getRuleContext(Generate_expContext.class,0);
		}
		public Rel_opContext rel_op() {
			return getRuleContext(Rel_opContext.class,0);
		}
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public AtomConstraintContext(ConstraintContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterAtomConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitAtomConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitAtomConstraint(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MaxConstraintContext extends ConstraintContext {
		public Var_idContext left;
		public ListContext vars;
		public Var_idContext var_id() {
			return getRuleContext(Var_idContext.class,0);
		}
		public ListContext list() {
			return getRuleContext(ListContext.class,0);
		}
		public MaxConstraintContext(ConstraintContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterMaxConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitMaxConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitMaxConstraint(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OrBinConstraintContext extends ConstraintContext {
		public Var_idContext left;
		public ListContext vars;
		public Var_idContext var_id() {
			return getRuleContext(Var_idContext.class,0);
		}
		public ListContext list() {
			return getRuleContext(ListContext.class,0);
		}
		public OrBinConstraintContext(ConstraintContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterOrBinConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitOrBinConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitOrBinConstraint(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MinConstraintContext extends ConstraintContext {
		public Var_idContext left;
		public ListContext vars;
		public Var_idContext var_id() {
			return getRuleContext(Var_idContext.class,0);
		}
		public ListContext list() {
			return getRuleContext(ListContext.class,0);
		}
		public MinConstraintContext(ConstraintContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterMinConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitMinConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitMinConstraint(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EqualsConstraintContext extends ConstraintContext {
		public ListContext left;
		public ListContext right;
		public List<ListContext> list() {
			return getRuleContexts(ListContext.class);
		}
		public ListContext list(int i) {
			return getRuleContext(ListContext.class,i);
		}
		public EqualsConstraintContext(ConstraintContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterEqualsConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitEqualsConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitEqualsConstraint(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ValueInValuesConstraintContext extends ConstraintContext {
		public Var_idContext var;
		public ListContext values;
		public Var_idContext var_id() {
			return getRuleContext(Var_idContext.class,0);
		}
		public ListContext list() {
			return getRuleContext(ListContext.class,0);
		}
		public ValueInValuesConstraintContext(ConstraintContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterValueInValuesConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitValueInValuesConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitValueInValuesConstraint(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AllInValuesConstraintContext extends ConstraintContext {
		public ListContext vars;
		public ListContext values;
		public List<ListContext> list() {
			return getRuleContexts(ListContext.class);
		}
		public ListContext list(int i) {
			return getRuleContext(ListContext.class,i);
		}
		public AllInValuesConstraintContext(ConstraintContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterAllInValuesConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitAllInValuesConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitAllInValuesConstraint(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AllDifferentValuesConstraintContext extends ConstraintContext {
		public ListContext vars;
		public ListContext list() {
			return getRuleContext(ListContext.class,0);
		}
		public AllDifferentValuesConstraintContext(ConstraintContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterAllDifferentValuesConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitAllDifferentValuesConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitAllDifferentValuesConstraint(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AbsConstraintContext extends ConstraintContext {
		public Var_idContext left;
		public Generate_expContext right;
		public Var_idContext var_id() {
			return getRuleContext(Var_idContext.class,0);
		}
		public Generate_expContext generate_exp() {
			return getRuleContext(Generate_expContext.class,0);
		}
		public AbsConstraintContext(ConstraintContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterAbsConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitAbsConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitAbsConstraint(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IndicatorConstraintContext extends ConstraintContext {
		public Token values;
		public Var_idContext var_id() {
			return getRuleContext(Var_idContext.class,0);
		}
		public ConstraintContext constraint() {
			return getRuleContext(ConstraintContext.class,0);
		}
		public TerminalNode INT() { return getToken(PLIModelParser.INT, 0); }
		public IndicatorConstraintContext(ConstraintContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterIndicatorConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitIndicatorConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitIndicatorConstraint(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ImplyConstraintContext extends ConstraintContext {
		public ConstraintContext left;
		public ConstraintContext right;
		public List<ConstraintContext> constraint() {
			return getRuleContexts(ConstraintContext.class);
		}
		public ConstraintContext constraint(int i) {
			return getRuleContext(ConstraintContext.class,i);
		}
		public ImplyConstraintContext(ConstraintContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterImplyConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitImplyConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitImplyConstraint(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OrConstraintContext extends ConstraintContext {
		public Token n;
		public Rel_opContext rel_op() {
			return getRuleContext(Rel_opContext.class,0);
		}
		public List<ConstraintContext> constraint() {
			return getRuleContexts(ConstraintContext.class);
		}
		public ConstraintContext constraint(int i) {
			return getRuleContext(ConstraintContext.class,i);
		}
		public TerminalNode INT() { return getToken(PLIModelParser.INT, 0); }
		public OrConstraintContext(ConstraintContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterOrConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitOrConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitOrConstraint(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PiecewiseConstraintContext extends ConstraintContext {
		public Var_idContext left;
		public Var_idContext right;
		public PairContext data;
		public List<Var_idContext> var_id() {
			return getRuleContexts(Var_idContext.class);
		}
		public Var_idContext var_id(int i) {
			return getRuleContext(Var_idContext.class,i);
		}
		public List<PairContext> pair() {
			return getRuleContexts(PairContext.class);
		}
		public PairContext pair(int i) {
			return getRuleContext(PairContext.class,i);
		}
		public PiecewiseConstraintContext(ConstraintContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterPiecewiseConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitPiecewiseConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitPiecewiseConstraint(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AndBinConstraintContext extends ConstraintContext {
		public Var_idContext left;
		public ListContext vars;
		public Var_idContext var_id() {
			return getRuleContext(Var_idContext.class,0);
		}
		public ListContext list() {
			return getRuleContext(ListContext.class,0);
		}
		public AndBinConstraintContext(ConstraintContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterAndBinConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitAndBinConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitAndBinConstraint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstraintContext constraint() throws RecognitionException {
		return constraint(0);
	}

	private ConstraintContext constraint(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ConstraintContext _localctx = new ConstraintContext(_ctx, _parentState);
		ConstraintContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_constraint, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(249);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				{
				_localctx = new AtomConstraintContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(152);
				generate_exp();
				setState(153);
				rel_op();
				setState(154);
				exp(0);
				}
				break;
			case 2:
				{
				_localctx = new IndicatorConstraintContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(156);
				var_id();
				setState(157);
				match(T__1);
				setState(158);
				((IndicatorConstraintContext)_localctx).values = match(INT);
				setState(159);
				match(T__12);
				setState(160);
				constraint(14);
				}
				break;
			case 3:
				{
				_localctx = new EqualsConstraintContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(162);
				match(T__2);
				setState(163);
				((EqualsConstraintContext)_localctx).left = list();
				setState(164);
				match(T__13);
				setState(165);
				((EqualsConstraintContext)_localctx).right = list();
				setState(166);
				match(T__3);
				}
				break;
			case 4:
				{
				_localctx = new OrConstraintContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(168);
				match(T__14);
				setState(169);
				match(T__2);
				setState(170);
				rel_op();
				setState(171);
				((OrConstraintContext)_localctx).n = match(INT);
				setState(172);
				match(T__4);
				setState(173);
				constraint(0);
				setState(176); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(174);
					match(T__9);
					setState(175);
					constraint(0);
					}
					}
					setState(178); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==T__9 );
				setState(180);
				match(T__3);
				}
				break;
			case 5:
				{
				_localctx = new DifferentValueConstraintContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(182);
				((DifferentValueConstraintContext)_localctx).left = var_id();
				setState(183);
				match(T__16);
				setState(184);
				((DifferentValueConstraintContext)_localctx).right = var_id();
				}
				break;
			case 6:
				{
				_localctx = new AllDifferentValuesConstraintContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(186);
				match(T__17);
				setState(187);
				match(T__2);
				setState(188);
				((AllDifferentValuesConstraintContext)_localctx).vars = list();
				setState(189);
				match(T__3);
				}
				break;
			case 7:
				{
				_localctx = new AllInValuesConstraintContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(191);
				match(T__18);
				setState(192);
				match(T__2);
				setState(193);
				((AllInValuesConstraintContext)_localctx).vars = list();
				setState(194);
				match(T__19);
				setState(195);
				((AllInValuesConstraintContext)_localctx).values = list();
				setState(196);
				match(T__3);
				}
				break;
			case 8:
				{
				_localctx = new ValueInValuesConstraintContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(198);
				((ValueInValuesConstraintContext)_localctx).var = var_id();
				setState(199);
				match(T__10);
				setState(200);
				((ValueInValuesConstraintContext)_localctx).values = list();
				}
				break;
			case 9:
				{
				_localctx = new MaxConstraintContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(202);
				((MaxConstraintContext)_localctx).left = var_id();
				setState(203);
				match(T__1);
				setState(204);
				match(T__20);
				setState(205);
				match(T__2);
				setState(206);
				((MaxConstraintContext)_localctx).vars = list();
				setState(207);
				match(T__3);
				}
				break;
			case 10:
				{
				_localctx = new MinConstraintContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(209);
				((MinConstraintContext)_localctx).left = var_id();
				setState(210);
				match(T__1);
				setState(211);
				match(T__21);
				setState(212);
				match(T__2);
				setState(213);
				((MinConstraintContext)_localctx).vars = list();
				setState(214);
				match(T__3);
				}
				break;
			case 11:
				{
				_localctx = new OrBinConstraintContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(216);
				((OrBinConstraintContext)_localctx).left = var_id();
				setState(217);
				match(T__1);
				setState(218);
				match(T__22);
				setState(219);
				match(T__2);
				setState(220);
				((OrBinConstraintContext)_localctx).vars = list();
				setState(221);
				match(T__3);
				}
				break;
			case 12:
				{
				_localctx = new AndBinConstraintContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(223);
				((AndBinConstraintContext)_localctx).left = var_id();
				setState(224);
				match(T__1);
				setState(225);
				match(T__23);
				setState(226);
				match(T__2);
				setState(227);
				((AndBinConstraintContext)_localctx).vars = list();
				setState(228);
				match(T__3);
				}
				break;
			case 13:
				{
				_localctx = new AbsConstraintContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(230);
				((AbsConstraintContext)_localctx).left = var_id();
				setState(231);
				match(T__1);
				setState(232);
				match(T__24);
				setState(233);
				match(T__2);
				setState(234);
				((AbsConstraintContext)_localctx).right = generate_exp();
				setState(235);
				match(T__3);
				}
				break;
			case 14:
				{
				_localctx = new PiecewiseConstraintContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(237);
				((PiecewiseConstraintContext)_localctx).left = var_id();
				setState(238);
				match(T__1);
				setState(239);
				match(T__25);
				setState(240);
				match(T__2);
				setState(241);
				((PiecewiseConstraintContext)_localctx).right = var_id();
				setState(242);
				match(T__3);
				setState(243);
				match(T__26);
				setState(245); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(244);
						((PiecewiseConstraintContext)_localctx).data = pair();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(247); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(256);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ImplyConstraintContext(new ConstraintContext(_parentctx, _parentState));
					((ImplyConstraintContext)_localctx).left = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_constraint);
					setState(251);
					if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
					setState(252);
					match(T__15);
					setState(253);
					((ImplyConstraintContext)_localctx).right = constraint(12);
					}
					} 
				}
				setState(258);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class PairContext extends ParserRuleContext {
		public List<TerminalNode> INT() { return getTokens(PLIModelParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(PLIModelParser.INT, i);
		}
		public PairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pair; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterPair(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitPair(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitPair(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairContext pair() throws RecognitionException {
		PairContext _localctx = new PairContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_pair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(259);
			match(T__2);
			setState(260);
			match(INT);
			setState(261);
			match(T__4);
			setState(262);
			match(INT);
			setState(263);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Generate_expContext extends ParserRuleContext {
		public Generate_expContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_generate_exp; }
	 
		public Generate_expContext() { }
		public void copyFrom(Generate_expContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class FactorGenerateExpContext extends Generate_expContext {
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public List<S_factorContext> s_factor() {
			return getRuleContexts(S_factorContext.class);
		}
		public S_factorContext s_factor(int i) {
			return getRuleContext(S_factorContext.class,i);
		}
		public FactorGenerateExpContext(Generate_expContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterFactorGenerateExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitFactorGenerateExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitFactorGenerateExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SumGenerateExpContext extends Generate_expContext {
		public ListContext list() {
			return getRuleContext(ListContext.class,0);
		}
		public List<S_factorContext> s_factor() {
			return getRuleContexts(S_factorContext.class);
		}
		public S_factorContext s_factor(int i) {
			return getRuleContext(S_factorContext.class,i);
		}
		public SumGenerateExpContext(Generate_expContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterSumGenerateExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitSumGenerateExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitSumGenerateExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Generate_expContext generate_exp() throws RecognitionException {
		Generate_expContext _localctx = new Generate_expContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_generate_exp);
		try {
			int _alt;
			setState(282);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
			case T__28:
			case T__29:
			case T__38:
			case T__39:
			case T__40:
			case ID:
			case INT:
			case DOUBLE:
				_localctx = new FactorGenerateExpContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(265);
				factor();
				setState(269);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(266);
						s_factor();
						}
						} 
					}
					setState(271);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
				}
				}
				break;
			case T__27:
				_localctx = new SumGenerateExpContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(272);
				match(T__27);
				setState(273);
				match(T__2);
				setState(274);
				list();
				setState(275);
				match(T__3);
				setState(279);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(276);
						s_factor();
						}
						} 
					}
					setState(281);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Generate_expsContext extends ParserRuleContext {
		public List<Generate_expContext> generate_exp() {
			return getRuleContexts(Generate_expContext.class);
		}
		public Generate_expContext generate_exp(int i) {
			return getRuleContext(Generate_expContext.class,i);
		}
		public Generate_expsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_generate_exps; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterGenerate_exps(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitGenerate_exps(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitGenerate_exps(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Generate_expsContext generate_exps() throws RecognitionException {
		Generate_expsContext _localctx = new Generate_expsContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_generate_exps);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(284);
			generate_exp();
			{
			setState(285);
			match(T__4);
			setState(286);
			generate_exp();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class S_factorContext extends ParserRuleContext {
		public S_factorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_s_factor; }
	 
		public S_factorContext() { }
		public void copyFrom(S_factorContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class MinusSumContext extends S_factorContext {
		public ListContext list() {
			return getRuleContext(ListContext.class,0);
		}
		public MinusSumContext(S_factorContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterMinusSum(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitMinusSum(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitMinusSum(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PlusFactorContext extends S_factorContext {
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public PlusFactorContext(S_factorContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterPlusFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitPlusFactor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitPlusFactor(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MinusFactorContext extends S_factorContext {
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public MinusFactorContext(S_factorContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterMinusFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitMinusFactor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitMinusFactor(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PlusSumContext extends S_factorContext {
		public ListContext list() {
			return getRuleContext(ListContext.class,0);
		}
		public PlusSumContext(S_factorContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterPlusSum(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitPlusSum(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitPlusSum(this);
			else return visitor.visitChildren(this);
		}
	}

	public final S_factorContext s_factor() throws RecognitionException {
		S_factorContext _localctx = new S_factorContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_s_factor);
		try {
			setState(304);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				_localctx = new PlusFactorContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(288);
				match(T__28);
				setState(289);
				factor();
				}
				break;
			case 2:
				_localctx = new MinusFactorContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(290);
				match(T__29);
				setState(291);
				factor();
				}
				break;
			case 3:
				_localctx = new PlusSumContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(292);
				match(T__28);
				setState(293);
				match(T__27);
				setState(294);
				match(T__2);
				setState(295);
				list();
				setState(296);
				match(T__3);
				}
				break;
			case 4:
				_localctx = new MinusSumContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(298);
				match(T__29);
				setState(299);
				match(T__27);
				setState(300);
				match(T__2);
				setState(301);
				list();
				setState(302);
				match(T__3);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FactorContext extends ParserRuleContext {
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
	 
		public FactorContext() { }
		public void copyFrom(FactorContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ExpFactorContext extends FactorContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public ExpFactorContext(FactorContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterExpFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitExpFactor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitExpFactor(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VarIdFactorContext extends FactorContext {
		public Var_idContext var_id() {
			return getRuleContext(Var_idContext.class,0);
		}
		public VarIdFactorContext(FactorContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterVarIdFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitVarIdFactor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitVarIdFactor(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VarFactorContext extends FactorContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public Var_idContext var_id() {
			return getRuleContext(Var_idContext.class,0);
		}
		public VarFactorContext(FactorContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterVarFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitVarFactor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitVarFactor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_factor);
		try {
			setState(311);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				_localctx = new VarFactorContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(306);
				exp(0);
				setState(307);
				var_id();
				}
				break;
			case 2:
				_localctx = new ExpFactorContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(309);
				exp(0);
				}
				break;
			case 3:
				_localctx = new VarIdFactorContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(310);
				var_id();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Var_idContext extends ParserRuleContext {
		public Token name;
		public TerminalNode ID() { return getToken(PLIModelParser.ID, 0); }
		public Index_var_idContext index_var_id() {
			return getRuleContext(Index_var_idContext.class,0);
		}
		public Var_idContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_id; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterVar_id(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitVar_id(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitVar_id(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Var_idContext var_id() throws RecognitionException {
		Var_idContext _localctx = new Var_idContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_var_id);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(313);
			((Var_idContext)_localctx).name = match(ID);
			setState(314);
			match(T__30);
			setState(316);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__28) | (1L << T__29) | (1L << T__38) | (1L << T__39) | (1L << T__40) | (1L << ID) | (1L << INT) | (1L << DOUBLE))) != 0)) {
				{
				setState(315);
				index_var_id();
				}
			}

			setState(318);
			match(T__31);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Var_idsContext extends ParserRuleContext {
		public List<Var_idContext> var_id() {
			return getRuleContexts(Var_idContext.class);
		}
		public Var_idContext var_id(int i) {
			return getRuleContext(Var_idContext.class,i);
		}
		public Var_idsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_ids; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterVar_ids(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitVar_ids(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitVar_ids(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Var_idsContext var_ids() throws RecognitionException {
		Var_idsContext _localctx = new Var_idsContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_var_ids);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(320);
			var_id();
			{
			setState(321);
			match(T__4);
			setState(322);
			var_id();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Index_var_idContext extends ParserRuleContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public Index_var_idContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_index_var_id; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterIndex_var_id(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitIndex_var_id(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitIndex_var_id(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Index_var_idContext index_var_id() throws RecognitionException {
		Index_var_idContext _localctx = new Index_var_idContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_index_var_id);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(324);
			exp(0);
			setState(329);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(325);
				match(T__4);
				setState(326);
				exp(0);
				}
				}
				setState(331);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoundContext extends ParserRuleContext {
		public BoundContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bound; }
	 
		public BoundContext() { }
		public void copyFrom(BoundContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TwoSideBoundContext extends BoundContext {
		public ExpContext li;
		public Var_idContext name;
		public ExpContext ls;
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public Var_idContext var_id() {
			return getRuleContext(Var_idContext.class,0);
		}
		public TwoSideBoundContext(BoundContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterTwoSideBound(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitTwoSideBound(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitTwoSideBound(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OneSideBoundContext extends BoundContext {
		public Var_idContext name;
		public Rel_opContext op;
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public Var_idContext var_id() {
			return getRuleContext(Var_idContext.class,0);
		}
		public Rel_opContext rel_op() {
			return getRuleContext(Rel_opContext.class,0);
		}
		public OneSideBoundContext(BoundContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterOneSideBound(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitOneSideBound(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitOneSideBound(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoundContext bound() throws RecognitionException {
		BoundContext _localctx = new BoundContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_bound);
		try {
			setState(342);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				_localctx = new OneSideBoundContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(332);
				((OneSideBoundContext)_localctx).name = var_id();
				setState(333);
				((OneSideBoundContext)_localctx).op = rel_op();
				setState(334);
				exp(0);
				}
				break;
			case 2:
				_localctx = new TwoSideBoundContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(336);
				((TwoSideBoundContext)_localctx).li = exp(0);
				setState(337);
				match(T__32);
				setState(338);
				((TwoSideBoundContext)_localctx).name = var_id();
				setState(339);
				match(T__32);
				setState(340);
				((TwoSideBoundContext)_localctx).ls = exp(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoundsContext extends ParserRuleContext {
		public List<ListContext> list() {
			return getRuleContexts(ListContext.class);
		}
		public ListContext list(int i) {
			return getRuleContext(ListContext.class,i);
		}
		public BoundsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bounds; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterBounds(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitBounds(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitBounds(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoundsContext bounds() throws RecognitionException {
		BoundsContext _localctx = new BoundsContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_bounds);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(344);
			match(T__33);
			setState(346); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(345);
				list();
				}
				}
				setState(348); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__14) | (1L << T__17) | (1L << T__18) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__38) | (1L << T__39) | (1L << T__40) | (1L << ID) | (1L << INT) | (1L << DOUBLE))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Bin_varsContext extends ParserRuleContext {
		public List<ListContext> list() {
			return getRuleContexts(ListContext.class);
		}
		public ListContext list(int i) {
			return getRuleContext(ListContext.class,i);
		}
		public Bin_varsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bin_vars; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterBin_vars(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitBin_vars(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitBin_vars(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Bin_varsContext bin_vars() throws RecognitionException {
		Bin_varsContext _localctx = new Bin_varsContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_bin_vars);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(350);
			match(T__34);
			setState(352); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(351);
				list();
				}
				}
				setState(354); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__14) | (1L << T__17) | (1L << T__18) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__38) | (1L << T__39) | (1L << T__40) | (1L << ID) | (1L << INT) | (1L << DOUBLE))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Int_varsContext extends ParserRuleContext {
		public List<ListContext> list() {
			return getRuleContexts(ListContext.class);
		}
		public ListContext list(int i) {
			return getRuleContext(ListContext.class,i);
		}
		public Int_varsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_int_vars; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterInt_vars(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitInt_vars(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitInt_vars(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Int_varsContext int_vars() throws RecognitionException {
		Int_varsContext _localctx = new Int_varsContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_int_vars);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(356);
			match(T__35);
			setState(358); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(357);
				list();
				}
				}
				setState(360); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__14) | (1L << T__17) | (1L << T__18) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__38) | (1L << T__39) | (1L << T__40) | (1L << ID) | (1L << INT) | (1L << DOUBLE))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Free_varsContext extends ParserRuleContext {
		public List<ListContext> list() {
			return getRuleContexts(ListContext.class);
		}
		public ListContext list(int i) {
			return getRuleContext(ListContext.class,i);
		}
		public Free_varsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_free_vars; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterFree_vars(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitFree_vars(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitFree_vars(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Free_varsContext free_vars() throws RecognitionException {
		Free_varsContext _localctx = new Free_varsContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_free_vars);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(362);
			match(T__36);
			setState(364); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(363);
				list();
				}
				}
				setState(366); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__14) | (1L << T__17) | (1L << T__18) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__38) | (1L << T__39) | (1L << T__40) | (1L << ID) | (1L << INT) | (1L << DOUBLE))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Semi_continuous_varsContext extends ParserRuleContext {
		public List<ListContext> list() {
			return getRuleContexts(ListContext.class);
		}
		public ListContext list(int i) {
			return getRuleContext(ListContext.class,i);
		}
		public Semi_continuous_varsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_semi_continuous_vars; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterSemi_continuous_vars(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitSemi_continuous_vars(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitSemi_continuous_vars(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Semi_continuous_varsContext semi_continuous_vars() throws RecognitionException {
		Semi_continuous_varsContext _localctx = new Semi_continuous_varsContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_semi_continuous_vars);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(368);
			match(T__37);
			setState(370); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(369);
				list();
				}
				}
				setState(372); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__14) | (1L << T__17) | (1L << T__18) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__38) | (1L << T__39) | (1L << T__40) | (1L << ID) | (1L << INT) | (1L << DOUBLE))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpContext extends ParserRuleContext {
		public ExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp; }
	 
		public ExpContext() { }
		public void copyFrom(ExpContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class UnaryOpExprContext extends ExpContext {
		public Token op;
		public ExpContext right;
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public UnaryOpExprContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterUnaryOpExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitUnaryOpExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitUnaryOpExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntExprContext extends ExpContext {
		public TerminalNode INT() { return getToken(PLIModelParser.INT, 0); }
		public IntExprContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterIntExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitIntExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitIntExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OpExprContext extends ExpContext {
		public ExpContext left;
		public Token op;
		public ExpContext right;
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public OpExprContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterOpExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitOpExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitOpExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FunExprContext extends ExpContext {
		public Call_functionContext call_function() {
			return getRuleContext(Call_functionContext.class,0);
		}
		public FunExprContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterFunExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitFunExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitFunExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenExprContext extends ExpContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public ParenExprContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterParenExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitParenExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitParenExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DoubleExpContext extends ExpContext {
		public TerminalNode DOUBLE() { return getToken(PLIModelParser.DOUBLE, 0); }
		public DoubleExpContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterDoubleExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitDoubleExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitDoubleExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IdExprContext extends ExpContext {
		public Token id;
		public TerminalNode ID() { return getToken(PLIModelParser.ID, 0); }
		public IdExprContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterIdExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitIdExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitIdExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpContext exp() throws RecognitionException {
		return exp(0);
	}

	private ExpContext exp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpContext _localctx = new ExpContext(_ctx, _parentState);
		ExpContext _prevctx = _localctx;
		int _startState = 52;
		enterRecursionRule(_localctx, 52, RULE_exp, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(389);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				{
				_localctx = new UnaryOpExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(375);
				((UnaryOpExprContext)_localctx).op = match(T__38);
				setState(376);
				((UnaryOpExprContext)_localctx).right = exp(13);
				}
				break;
			case 2:
				{
				_localctx = new UnaryOpExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(377);
				((UnaryOpExprContext)_localctx).op = match(T__39);
				setState(378);
				((UnaryOpExprContext)_localctx).right = exp(12);
				}
				break;
			case 3:
				{
				_localctx = new UnaryOpExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(379);
				((UnaryOpExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__28) | (1L << T__29) | (1L << T__40))) != 0)) ) {
					((UnaryOpExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(380);
				((UnaryOpExprContext)_localctx).right = exp(11);
				}
				break;
			case 4:
				{
				_localctx = new ParenExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(381);
				match(T__2);
				setState(382);
				exp(0);
				setState(383);
				match(T__3);
				}
				break;
			case 5:
				{
				_localctx = new FunExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(385);
				call_function();
				}
				break;
			case 6:
				{
				_localctx = new IdExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(386);
				((IdExprContext)_localctx).id = match(ID);
				}
				break;
			case 7:
				{
				_localctx = new DoubleExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(387);
				match(DOUBLE);
				}
				break;
			case 8:
				{
				_localctx = new IntExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(388);
				match(INT);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(408);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(406);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
					case 1:
						{
						_localctx = new OpExprContext(new ExpContext(_parentctx, _parentState));
						((OpExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(391);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(392);
						((OpExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__41) | (1L << T__42) | (1L << T__43))) != 0)) ) {
							((OpExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(393);
						((OpExprContext)_localctx).right = exp(11);
						}
						break;
					case 2:
						{
						_localctx = new OpExprContext(new ExpContext(_parentctx, _parentState));
						((OpExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(394);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(395);
						((OpExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__28 || _la==T__29) ) {
							((OpExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(396);
						((OpExprContext)_localctx).right = exp(10);
						}
						break;
					case 3:
						{
						_localctx = new OpExprContext(new ExpContext(_parentctx, _parentState));
						((OpExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(397);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(398);
						((OpExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__32) | (1L << T__44) | (1L << T__45) | (1L << T__46))) != 0)) ) {
							((OpExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(399);
						((OpExprContext)_localctx).right = exp(9);
						}
						break;
					case 4:
						{
						_localctx = new OpExprContext(new ExpContext(_parentctx, _parentState));
						((OpExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(400);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(401);
						((OpExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__1 || _la==T__16) ) {
							((OpExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(402);
						((OpExprContext)_localctx).right = exp(8);
						}
						break;
					case 5:
						{
						_localctx = new OpExprContext(new ExpContext(_parentctx, _parentState));
						((OpExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(403);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(404);
						((OpExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__47 || _la==T__48) ) {
							((OpExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(405);
						((OpExprContext)_localctx).right = exp(7);
						}
						break;
					}
					} 
				}
				setState(410);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Call_functionContext extends ParserRuleContext {
		public Token name;
		public TerminalNode ID() { return getToken(PLIModelParser.ID, 0); }
		public ExpsContext exps() {
			return getRuleContext(ExpsContext.class,0);
		}
		public Call_functionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_call_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterCall_function(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitCall_function(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitCall_function(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Call_functionContext call_function() throws RecognitionException {
		Call_functionContext _localctx = new Call_functionContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_call_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(411);
			((Call_functionContext)_localctx).name = match(ID);
			setState(412);
			match(T__2);
			setState(414);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__28) | (1L << T__29) | (1L << T__38) | (1L << T__39) | (1L << T__40) | (1L << ID) | (1L << INT) | (1L << DOUBLE))) != 0)) {
				{
				setState(413);
				exps();
				}
			}

			setState(416);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpsContext extends ParserRuleContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public ExpsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exps; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterExps(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitExps(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitExps(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpsContext exps() throws RecognitionException {
		ExpsContext _localctx = new ExpsContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_exps);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(418);
			exp(0);
			setState(423);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(419);
					match(T__4);
					setState(420);
					exp(0);
					}
					} 
				}
				setState(425);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Rel_opContext extends ParserRuleContext {
		public Rel_opContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rel_op; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterRel_op(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitRel_op(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitRel_op(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Rel_opContext rel_op() throws RecognitionException {
		Rel_opContext _localctx = new Rel_opContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_rel_op);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(426);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__32) | (1L << T__44) | (1L << T__45) | (1L << T__46))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 11:
			return constraint_sempred((ConstraintContext)_localctx, predIndex);
		case 26:
			return exp_sempred((ExpContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean constraint_sempred(ConstraintContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 11);
		}
		return true;
	}
	private boolean exp_sempred(ExpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 10);
		case 2:
			return precpred(_ctx, 9);
		case 3:
			return precpred(_ctx, 8);
		case 4:
			return precpred(_ctx, 7);
		case 5:
			return precpred(_ctx, 6);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\39\u01af\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2\5\2@"+
		"\n\2\3\2\3\2\3\2\5\2E\n\2\3\2\5\2H\n\2\3\2\5\2K\n\2\3\2\5\2N\n\2\3\2\5"+
		"\2Q\n\2\3\3\3\3\7\3U\n\3\f\3\16\3X\13\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\5\4b\n\4\3\4\5\4e\n\4\3\5\3\5\3\5\7\5j\n\5\f\5\16\5m\13\5\3\6\3\6\5"+
		"\6q\n\6\3\7\3\7\3\7\3\7\3\b\3\b\6\by\n\b\r\b\16\bz\3\t\3\t\3\t\7\t\u0080"+
		"\n\t\f\t\16\t\u0083\13\t\3\t\3\t\5\t\u0087\n\t\3\n\3\n\3\n\3\n\5\n\u008d"+
		"\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\5\f\u0098\n\f\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\6\r\u00b3\n\r\r\r\16\r\u00b4\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\6\r\u00f8\n\r\r\r\16\r\u00f9\5\r\u00fc"+
		"\n\r\3\r\3\r\3\r\7\r\u0101\n\r\f\r\16\r\u0104\13\r\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\17\3\17\7\17\u010e\n\17\f\17\16\17\u0111\13\17\3\17\3\17"+
		"\3\17\3\17\3\17\7\17\u0118\n\17\f\17\16\17\u011b\13\17\5\17\u011d\n\17"+
		"\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u0133\n\21\3\22\3\22\3\22\3\22\3\22"+
		"\5\22\u013a\n\22\3\23\3\23\3\23\5\23\u013f\n\23\3\23\3\23\3\24\3\24\3"+
		"\24\3\24\3\25\3\25\3\25\7\25\u014a\n\25\f\25\16\25\u014d\13\25\3\26\3"+
		"\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u0159\n\26\3\27\3\27"+
		"\6\27\u015d\n\27\r\27\16\27\u015e\3\30\3\30\6\30\u0163\n\30\r\30\16\30"+
		"\u0164\3\31\3\31\6\31\u0169\n\31\r\31\16\31\u016a\3\32\3\32\6\32\u016f"+
		"\n\32\r\32\16\32\u0170\3\33\3\33\6\33\u0175\n\33\r\33\16\33\u0176\3\34"+
		"\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\5\34\u0188\n\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\7\34\u0199\n\34\f\34\16\34\u019c\13\34\3\35\3\35"+
		"\3\35\5\35\u01a1\n\35\3\35\3\35\3\36\3\36\3\36\7\36\u01a8\n\36\f\36\16"+
		"\36\u01ab\13\36\3\37\3\37\3\37\2\4\30\66 \2\4\6\b\n\f\16\20\22\24\26\30"+
		"\32\34\36 \"$&(*,.\60\62\64\668:<\2\n\3\2\t\n\4\2\37 ++\3\2,.\3\2\37 "+
		"\4\2##/\61\4\2\4\4\23\23\3\2\62\63\5\2\4\4##/\61\2\u01d1\2?\3\2\2\2\4"+
		"R\3\2\2\2\6d\3\2\2\2\bf\3\2\2\2\nn\3\2\2\2\fr\3\2\2\2\16v\3\2\2\2\20|"+
		"\3\2\2\2\22\u008c\3\2\2\2\24\u008e\3\2\2\2\26\u0097\3\2\2\2\30\u00fb\3"+
		"\2\2\2\32\u0105\3\2\2\2\34\u011c\3\2\2\2\36\u011e\3\2\2\2 \u0132\3\2\2"+
		"\2\"\u0139\3\2\2\2$\u013b\3\2\2\2&\u0142\3\2\2\2(\u0146\3\2\2\2*\u0158"+
		"\3\2\2\2,\u015a\3\2\2\2.\u0160\3\2\2\2\60\u0166\3\2\2\2\62\u016c\3\2\2"+
		"\2\64\u0172\3\2\2\2\66\u0187\3\2\2\28\u019d\3\2\2\2:\u01a4\3\2\2\2<\u01ac"+
		"\3\2\2\2>@\5\4\3\2?>\3\2\2\2?@\3\2\2\2@A\3\2\2\2AB\5\f\7\2BD\5\16\b\2"+
		"CE\5,\27\2DC\3\2\2\2DE\3\2\2\2EG\3\2\2\2FH\5.\30\2GF\3\2\2\2GH\3\2\2\2"+
		"HJ\3\2\2\2IK\5\60\31\2JI\3\2\2\2JK\3\2\2\2KM\3\2\2\2LN\5\62\32\2ML\3\2"+
		"\2\2MN\3\2\2\2NP\3\2\2\2OQ\5\64\33\2PO\3\2\2\2PQ\3\2\2\2Q\3\3\2\2\2RV"+
		"\7\3\2\2SU\5\6\4\2TS\3\2\2\2UX\3\2\2\2VT\3\2\2\2VW\3\2\2\2W\5\3\2\2\2"+
		"XV\3\2\2\2YZ\7\64\2\2Z[\7\64\2\2[\\\7\4\2\2\\e\5\66\34\2]^\7\64\2\2^_"+
		"\7\64\2\2_a\7\5\2\2`b\5\b\5\2a`\3\2\2\2ab\3\2\2\2bc\3\2\2\2ce\7\6\2\2"+
		"dY\3\2\2\2d]\3\2\2\2e\7\3\2\2\2fk\5\n\6\2gh\7\7\2\2hj\5\n\6\2ig\3\2\2"+
		"\2jm\3\2\2\2ki\3\2\2\2kl\3\2\2\2l\t\3\2\2\2mk\3\2\2\2np\7\64\2\2oq\7\64"+
		"\2\2po\3\2\2\2pq\3\2\2\2q\13\3\2\2\2rs\7\b\2\2st\t\2\2\2tu\5\34\17\2u"+
		"\r\3\2\2\2vx\7\13\2\2wy\5\22\n\2xw\3\2\2\2yz\3\2\2\2zx\3\2\2\2z{\3\2\2"+
		"\2{\17\3\2\2\2|\u0081\5\26\f\2}~\7\7\2\2~\u0080\5\24\13\2\177}\3\2\2\2"+
		"\u0080\u0083\3\2\2\2\u0081\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0086"+
		"\3\2\2\2\u0083\u0081\3\2\2\2\u0084\u0085\7\f\2\2\u0085\u0087\5\66\34\2"+
		"\u0086\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\21\3\2\2\2\u0088\u008d"+
		"\5\20\t\2\u0089\u008d\5&\24\2\u008a\u008d\5:\36\2\u008b\u008d\5\36\20"+
		"\2\u008c\u0088\3\2\2\2\u008c\u0089\3\2\2\2\u008c\u008a\3\2\2\2\u008c\u008b"+
		"\3\2\2\2\u008d\23\3\2\2\2\u008e\u008f\7\64\2\2\u008f\u0090\7\r\2\2\u0090"+
		"\u0091\5\66\34\2\u0091\u0092\7\16\2\2\u0092\u0093\5\66\34\2\u0093\25\3"+
		"\2\2\2\u0094\u0098\5\30\r\2\u0095\u0098\5*\26\2\u0096\u0098\5\34\17\2"+
		"\u0097\u0094\3\2\2\2\u0097\u0095\3\2\2\2\u0097\u0096\3\2\2\2\u0098\27"+
		"\3\2\2\2\u0099\u009a\b\r\1\2\u009a\u009b\5\34\17\2\u009b\u009c\5<\37\2"+
		"\u009c\u009d\5\66\34\2\u009d\u00fc\3\2\2\2\u009e\u009f\5$\23\2\u009f\u00a0"+
		"\7\4\2\2\u00a0\u00a1\7\65\2\2\u00a1\u00a2\7\17\2\2\u00a2\u00a3\5\30\r"+
		"\20\u00a3\u00fc\3\2\2\2\u00a4\u00a5\7\5\2\2\u00a5\u00a6\5\22\n\2\u00a6"+
		"\u00a7\7\20\2\2\u00a7\u00a8\5\22\n\2\u00a8\u00a9\7\6\2\2\u00a9\u00fc\3"+
		"\2\2\2\u00aa\u00ab\7\21\2\2\u00ab\u00ac\7\5\2\2\u00ac\u00ad\5<\37\2\u00ad"+
		"\u00ae\7\65\2\2\u00ae\u00af\7\7\2\2\u00af\u00b2\5\30\r\2\u00b0\u00b1\7"+
		"\f\2\2\u00b1\u00b3\5\30\r\2\u00b2\u00b0\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4"+
		"\u00b2\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00b7\7\6"+
		"\2\2\u00b7\u00fc\3\2\2\2\u00b8\u00b9\5$\23\2\u00b9\u00ba\7\23\2\2\u00ba"+
		"\u00bb\5$\23\2\u00bb\u00fc\3\2\2\2\u00bc\u00bd\7\24\2\2\u00bd\u00be\7"+
		"\5\2\2\u00be\u00bf\5\22\n\2\u00bf\u00c0\7\6\2\2\u00c0\u00fc\3\2\2\2\u00c1"+
		"\u00c2\7\25\2\2\u00c2\u00c3\7\5\2\2\u00c3\u00c4\5\22\n\2\u00c4\u00c5\7"+
		"\26\2\2\u00c5\u00c6\5\22\n\2\u00c6\u00c7\7\6\2\2\u00c7\u00fc\3\2\2\2\u00c8"+
		"\u00c9\5$\23\2\u00c9\u00ca\7\r\2\2\u00ca\u00cb\5\22\n\2\u00cb\u00fc\3"+
		"\2\2\2\u00cc\u00cd\5$\23\2\u00cd\u00ce\7\4\2\2\u00ce\u00cf\7\27\2\2\u00cf"+
		"\u00d0\7\5\2\2\u00d0\u00d1\5\22\n\2\u00d1\u00d2\7\6\2\2\u00d2\u00fc\3"+
		"\2\2\2\u00d3\u00d4\5$\23\2\u00d4\u00d5\7\4\2\2\u00d5\u00d6\7\30\2\2\u00d6"+
		"\u00d7\7\5\2\2\u00d7\u00d8\5\22\n\2\u00d8\u00d9\7\6\2\2\u00d9\u00fc\3"+
		"\2\2\2\u00da\u00db\5$\23\2\u00db\u00dc\7\4\2\2\u00dc\u00dd\7\31\2\2\u00dd"+
		"\u00de\7\5\2\2\u00de\u00df\5\22\n\2\u00df\u00e0\7\6\2\2\u00e0\u00fc\3"+
		"\2\2\2\u00e1\u00e2\5$\23\2\u00e2\u00e3\7\4\2\2\u00e3\u00e4\7\32\2\2\u00e4"+
		"\u00e5\7\5\2\2\u00e5\u00e6\5\22\n\2\u00e6\u00e7\7\6\2\2\u00e7\u00fc\3"+
		"\2\2\2\u00e8\u00e9\5$\23\2\u00e9\u00ea\7\4\2\2\u00ea\u00eb\7\33\2\2\u00eb"+
		"\u00ec\7\5\2\2\u00ec\u00ed\5\34\17\2\u00ed\u00ee\7\6\2\2\u00ee\u00fc\3"+
		"\2\2\2\u00ef\u00f0\5$\23\2\u00f0\u00f1\7\4\2\2\u00f1\u00f2\7\34\2\2\u00f2"+
		"\u00f3\7\5\2\2\u00f3\u00f4\5$\23\2\u00f4\u00f5\7\6\2\2\u00f5\u00f7\7\35"+
		"\2\2\u00f6\u00f8\5\32\16\2\u00f7\u00f6\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9"+
		"\u00f7\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa\u00fc\3\2\2\2\u00fb\u0099\3\2"+
		"\2\2\u00fb\u009e\3\2\2\2\u00fb\u00a4\3\2\2\2\u00fb\u00aa\3\2\2\2\u00fb"+
		"\u00b8\3\2\2\2\u00fb\u00bc\3\2\2\2\u00fb\u00c1\3\2\2\2\u00fb\u00c8\3\2"+
		"\2\2\u00fb\u00cc\3\2\2\2\u00fb\u00d3\3\2\2\2\u00fb\u00da\3\2\2\2\u00fb"+
		"\u00e1\3\2\2\2\u00fb\u00e8\3\2\2\2\u00fb\u00ef\3\2\2\2\u00fc\u0102\3\2"+
		"\2\2\u00fd\u00fe\f\r\2\2\u00fe\u00ff\7\22\2\2\u00ff\u0101\5\30\r\16\u0100"+
		"\u00fd\3\2\2\2\u0101\u0104\3\2\2\2\u0102\u0100\3\2\2\2\u0102\u0103\3\2"+
		"\2\2\u0103\31\3\2\2\2\u0104\u0102\3\2\2\2\u0105\u0106\7\5\2\2\u0106\u0107"+
		"\7\65\2\2\u0107\u0108\7\7\2\2\u0108\u0109\7\65\2\2\u0109\u010a\7\6\2\2"+
		"\u010a\33\3\2\2\2\u010b\u010f\5\"\22\2\u010c\u010e\5 \21\2\u010d\u010c"+
		"\3\2\2\2\u010e\u0111\3\2\2\2\u010f\u010d\3\2\2\2\u010f\u0110\3\2\2\2\u0110"+
		"\u011d\3\2\2\2\u0111\u010f\3\2\2\2\u0112\u0113\7\36\2\2\u0113\u0114\7"+
		"\5\2\2\u0114\u0115\5\22\n\2\u0115\u0119\7\6\2\2\u0116\u0118\5 \21\2\u0117"+
		"\u0116\3\2\2\2\u0118\u011b\3\2\2\2\u0119\u0117\3\2\2\2\u0119\u011a\3\2"+
		"\2\2\u011a\u011d\3\2\2\2\u011b\u0119\3\2\2\2\u011c\u010b\3\2\2\2\u011c"+
		"\u0112\3\2\2\2\u011d\35\3\2\2\2\u011e\u011f\5\34\17\2\u011f\u0120\7\7"+
		"\2\2\u0120\u0121\5\34\17\2\u0121\37\3\2\2\2\u0122\u0123\7\37\2\2\u0123"+
		"\u0133\5\"\22\2\u0124\u0125\7 \2\2\u0125\u0133\5\"\22\2\u0126\u0127\7"+
		"\37\2\2\u0127\u0128\7\36\2\2\u0128\u0129\7\5\2\2\u0129\u012a\5\22\n\2"+
		"\u012a\u012b\7\6\2\2\u012b\u0133\3\2\2\2\u012c\u012d\7 \2\2\u012d\u012e"+
		"\7\36\2\2\u012e\u012f\7\5\2\2\u012f\u0130\5\22\n\2\u0130\u0131\7\6\2\2"+
		"\u0131\u0133\3\2\2\2\u0132\u0122\3\2\2\2\u0132\u0124\3\2\2\2\u0132\u0126"+
		"\3\2\2\2\u0132\u012c\3\2\2\2\u0133!\3\2\2\2\u0134\u0135\5\66\34\2\u0135"+
		"\u0136\5$\23\2\u0136\u013a\3\2\2\2\u0137\u013a\5\66\34\2\u0138\u013a\5"+
		"$\23\2\u0139\u0134\3\2\2\2\u0139\u0137\3\2\2\2\u0139\u0138\3\2\2\2\u013a"+
		"#\3\2\2\2\u013b\u013c\7\64\2\2\u013c\u013e\7!\2\2\u013d\u013f\5(\25\2"+
		"\u013e\u013d\3\2\2\2\u013e\u013f\3\2\2\2\u013f\u0140\3\2\2\2\u0140\u0141"+
		"\7\"\2\2\u0141%\3\2\2\2\u0142\u0143\5$\23\2\u0143\u0144\7\7\2\2\u0144"+
		"\u0145\5$\23\2\u0145\'\3\2\2\2\u0146\u014b\5\66\34\2\u0147\u0148\7\7\2"+
		"\2\u0148\u014a\5\66\34\2\u0149\u0147\3\2\2\2\u014a\u014d\3\2\2\2\u014b"+
		"\u0149\3\2\2\2\u014b\u014c\3\2\2\2\u014c)\3\2\2\2\u014d\u014b\3\2\2\2"+
		"\u014e\u014f\5$\23\2\u014f\u0150\5<\37\2\u0150\u0151\5\66\34\2\u0151\u0159"+
		"\3\2\2\2\u0152\u0153\5\66\34\2\u0153\u0154\7#\2\2\u0154\u0155\5$\23\2"+
		"\u0155\u0156\7#\2\2\u0156\u0157\5\66\34\2\u0157\u0159\3\2\2\2\u0158\u014e"+
		"\3\2\2\2\u0158\u0152\3\2\2\2\u0159+\3\2\2\2\u015a\u015c\7$\2\2\u015b\u015d"+
		"\5\22\n\2\u015c\u015b\3\2\2\2\u015d\u015e\3\2\2\2\u015e\u015c\3\2\2\2"+
		"\u015e\u015f\3\2\2\2\u015f-\3\2\2\2\u0160\u0162\7%\2\2\u0161\u0163\5\22"+
		"\n\2\u0162\u0161\3\2\2\2\u0163\u0164\3\2\2\2\u0164\u0162\3\2\2\2\u0164"+
		"\u0165\3\2\2\2\u0165/\3\2\2\2\u0166\u0168\7&\2\2\u0167\u0169\5\22\n\2"+
		"\u0168\u0167\3\2\2\2\u0169\u016a\3\2\2\2\u016a\u0168\3\2\2\2\u016a\u016b"+
		"\3\2\2\2\u016b\61\3\2\2\2\u016c\u016e\7\'\2\2\u016d\u016f\5\22\n\2\u016e"+
		"\u016d\3\2\2\2\u016f\u0170\3\2\2\2\u0170\u016e\3\2\2\2\u0170\u0171\3\2"+
		"\2\2\u0171\63\3\2\2\2\u0172\u0174\7(\2\2\u0173\u0175\5\22\n\2\u0174\u0173"+
		"\3\2\2\2\u0175\u0176\3\2\2\2\u0176\u0174\3\2\2\2\u0176\u0177\3\2\2\2\u0177"+
		"\65\3\2\2\2\u0178\u0179\b\34\1\2\u0179\u017a\7)\2\2\u017a\u0188\5\66\34"+
		"\17\u017b\u017c\7*\2\2\u017c\u0188\5\66\34\16\u017d\u017e\t\3\2\2\u017e"+
		"\u0188\5\66\34\r\u017f\u0180\7\5\2\2\u0180\u0181\5\66\34\2\u0181\u0182"+
		"\7\6\2\2\u0182\u0188\3\2\2\2\u0183\u0188\58\35\2\u0184\u0188\7\64\2\2"+
		"\u0185\u0188\7\66\2\2\u0186\u0188\7\65\2\2\u0187\u0178\3\2\2\2\u0187\u017b"+
		"\3\2\2\2\u0187\u017d\3\2\2\2\u0187\u017f\3\2\2\2\u0187\u0183\3\2\2\2\u0187"+
		"\u0184\3\2\2\2\u0187\u0185\3\2\2\2\u0187\u0186\3\2\2\2\u0188\u019a\3\2"+
		"\2\2\u0189\u018a\f\f\2\2\u018a\u018b\t\4\2\2\u018b\u0199\5\66\34\r\u018c"+
		"\u018d\f\13\2\2\u018d\u018e\t\5\2\2\u018e\u0199\5\66\34\f\u018f\u0190"+
		"\f\n\2\2\u0190\u0191\t\6\2\2\u0191\u0199\5\66\34\13\u0192\u0193\f\t\2"+
		"\2\u0193\u0194\t\7\2\2\u0194\u0199\5\66\34\n\u0195\u0196\f\b\2\2\u0196"+
		"\u0197\t\b\2\2\u0197\u0199\5\66\34\t\u0198\u0189\3\2\2\2\u0198\u018c\3"+
		"\2\2\2\u0198\u018f\3\2\2\2\u0198\u0192\3\2\2\2\u0198\u0195\3\2\2\2\u0199"+
		"\u019c\3\2\2\2\u019a\u0198\3\2\2\2\u019a\u019b\3\2\2\2\u019b\67\3\2\2"+
		"\2\u019c\u019a\3\2\2\2\u019d\u019e\7\64\2\2\u019e\u01a0\7\5\2\2\u019f"+
		"\u01a1\5:\36\2\u01a0\u019f\3\2\2\2\u01a0\u01a1\3\2\2\2\u01a1\u01a2\3\2"+
		"\2\2\u01a2\u01a3\7\6\2\2\u01a39\3\2\2\2\u01a4\u01a9\5\66\34\2\u01a5\u01a6"+
		"\7\7\2\2\u01a6\u01a8\5\66\34\2\u01a7\u01a5\3\2\2\2\u01a8\u01ab\3\2\2\2"+
		"\u01a9\u01a7\3\2\2\2\u01a9\u01aa\3\2\2\2\u01aa;\3\2\2\2\u01ab\u01a9\3"+
		"\2\2\2\u01ac\u01ad\t\t\2\2\u01ad=\3\2\2\2(?DGJMPVadkpz\u0081\u0086\u008c"+
		"\u0097\u00b4\u00f9\u00fb\u0102\u010f\u0119\u011c\u0132\u0139\u013e\u014b"+
		"\u0158\u015e\u0164\u016a\u0170\u0176\u0187\u0198\u019a\u01a0\u01a9";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}