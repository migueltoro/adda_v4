package us.lsi.model_test_old;

// Generated from PLIModel.g4 by ANTLR 4.8
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
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		ID=39, INT=40, DOUBLE=41, WS=42;
	public static final int
		RULE_model = 0, RULE_head = 1, RULE_declaration = 2, RULE_formal_parameters = 3, 
		RULE_formal_parameter = 4, RULE_goal = 5, RULE_constraints = 6, RULE_list = 7, 
		RULE_indx = 8, RULE_indexed_elem = 9, RULE_constraint = 10, RULE_generate_exp = 11, 
		RULE_s_factor = 12, RULE_factor = 13, RULE_var_id = 14, RULE_index_var_id = 15, 
		RULE_bound = 16, RULE_bounds = 17, RULE_bin_vars = 18, RULE_int_vars = 19, 
		RULE_free_vars = 20, RULE_exp = 21, RULE_call_function = 22, RULE_real_parameters = 23, 
		RULE_rel_op = 24;
	private static String[] makeRuleNames() {
		return new String[] {
			"model", "head", "declaration", "formal_parameters", "formal_parameter", 
			"goal", "constraints", "list", "indx", "indexed_elem", "constraint", 
			"generate_exp", "s_factor", "factor", "var_id", "index_var_id", "bound", 
			"bounds", "bin_vars", "int_vars", "free_vars", "exp", "call_function", 
			"real_parameters", "rel_op"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'head section'", "'='", "'('", "')'", "','", "'goal section'", 
			"'min'", "'max'", "'constraints section'", "'|'", "'in'", "'..'", "'->'", 
			"'or'", "'=>'", "'!='", "'allDifferent'", "'sum'", "'+'", "'-'", "'['", 
			"']'", "'<='", "'bounds section'", "'bin'", "'int'", "'free'", "'(int)'", 
			"'(double)'", "'!'", "'*'", "'/'", "'%'", "'<'", "'>='", "'>'", "'&&'", 
			"'||'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, "ID", "INT", "DOUBLE", "WS"
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
			setState(51);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(50);
				head();
				}
			}

			setState(53);
			goal();
			setState(54);
			constraints();
			setState(56);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__23) {
				{
				setState(55);
				bounds();
				}
			}

			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__24) {
				{
				setState(58);
				bin_vars();
				}
			}

			setState(62);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__25) {
				{
				setState(61);
				int_vars();
				}
			}

			setState(65);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__26) {
				{
				setState(64);
				free_vars();
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
			setState(67);
			match(T__0);
			setState(71);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(68);
				declaration();
				}
				}
				setState(73);
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
			setState(85);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				_localctx = new VarDeclarContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(74);
				((VarDeclarContext)_localctx).type = match(ID);
				setState(75);
				((VarDeclarContext)_localctx).name = match(ID);
				setState(76);
				match(T__1);
				setState(77);
				((VarDeclarContext)_localctx).val = exp(0);
				}
				break;
			case 2:
				_localctx = new FunDeclarContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(78);
				((FunDeclarContext)_localctx).type = match(ID);
				setState(79);
				((FunDeclarContext)_localctx).name = match(ID);
				setState(80);
				match(T__2);
				setState(82);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(81);
					formal_parameters();
					}
				}

				setState(84);
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
			setState(87);
			formal_parameter();
			setState(92);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(88);
				match(T__4);
				setState(89);
				formal_parameter();
				}
				}
				setState(94);
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
			setState(95);
			((Formal_parameterContext)_localctx).type = match(ID);
			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(96);
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
			setState(99);
			match(T__5);
			setState(100);
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
			setState(101);
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
			setState(103);
			match(T__8);
			setState(105); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(104);
				list();
				}
				}
				setState(107); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__13) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << ID) | (1L << INT) | (1L << DOUBLE))) != 0) );
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
		enterRule(_localctx, 14, RULE_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			indexed_elem();
			setState(114);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(110);
				match(T__4);
				setState(111);
				indx();
				}
				}
				setState(116);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(119);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__9) {
				{
				setState(117);
				match(T__9);
				setState(118);
				exp(0);
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
		enterRule(_localctx, 16, RULE_indx);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			((IndxContext)_localctx).index_name = match(ID);
			setState(122);
			match(T__10);
			setState(123);
			((IndxContext)_localctx).li = exp(0);
			setState(124);
			match(T__11);
			setState(125);
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
		enterRule(_localctx, 18, RULE_indexed_elem);
		try {
			setState(130);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(127);
				constraint(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(128);
				bound();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(129);
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
	public static class AllDifferentValuesConstraintContext extends ConstraintContext {
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
	public static class IndicatorConstraintContext extends ConstraintContext {
		public Var_idContext var_id() {
			return getRuleContext(Var_idContext.class,0);
		}
		public ConstraintContext constraint() {
			return getRuleContext(ConstraintContext.class,0);
		}
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

	public final ConstraintContext constraint() throws RecognitionException {
		return constraint(0);
	}

	private ConstraintContext constraint(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ConstraintContext _localctx = new ConstraintContext(_ctx, _parentState);
		ConstraintContext _prevctx = _localctx;
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_constraint, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				_localctx = new AtomConstraintContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(133);
				generate_exp();
				setState(134);
				rel_op();
				setState(135);
				exp(0);
				}
				break;
			case 2:
				{
				_localctx = new IndicatorConstraintContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(137);
				var_id();
				setState(138);
				match(T__12);
				setState(139);
				constraint(5);
				}
				break;
			case 3:
				{
				_localctx = new OrConstraintContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(141);
				match(T__13);
				setState(142);
				match(T__2);
				setState(143);
				rel_op();
				setState(144);
				((OrConstraintContext)_localctx).n = match(INT);
				setState(145);
				match(T__4);
				setState(146);
				constraint(0);
				setState(149); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(147);
					match(T__9);
					setState(148);
					constraint(0);
					}
					}
					setState(151); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==T__9 );
				setState(153);
				match(T__3);
				}
				break;
			case 4:
				{
				_localctx = new DifferentValueConstraintContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(155);
				((DifferentValueConstraintContext)_localctx).left = var_id();
				setState(156);
				match(T__15);
				setState(157);
				((DifferentValueConstraintContext)_localctx).right = var_id();
				}
				break;
			case 5:
				{
				_localctx = new AllDifferentValuesConstraintContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(159);
				match(T__16);
				setState(160);
				match(T__2);
				setState(161);
				list();
				setState(162);
				match(T__3);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(171);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ImplyConstraintContext(new ConstraintContext(_parentctx, _parentState));
					((ImplyConstraintContext)_localctx).left = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_constraint);
					setState(166);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(167);
					match(T__14);
					setState(168);
					((ImplyConstraintContext)_localctx).right = constraint(4);
					}
					} 
				}
				setState(173);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
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
		enterRule(_localctx, 22, RULE_generate_exp);
		try {
			int _alt;
			setState(191);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
			case T__18:
			case T__19:
			case T__27:
			case T__28:
			case T__29:
			case ID:
			case INT:
			case DOUBLE:
				_localctx = new FactorGenerateExpContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(174);
				factor();
				setState(178);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(175);
						s_factor();
						}
						} 
					}
					setState(180);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
				}
				}
				break;
			case T__17:
				_localctx = new SumGenerateExpContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(181);
				match(T__17);
				setState(182);
				match(T__2);
				setState(183);
				list();
				setState(184);
				match(T__3);
				setState(188);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(185);
						s_factor();
						}
						} 
					}
					setState(190);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
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
		enterRule(_localctx, 24, RULE_s_factor);
		try {
			setState(209);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				_localctx = new PlusFactorContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(193);
				match(T__18);
				setState(194);
				factor();
				}
				break;
			case 2:
				_localctx = new PlusSumContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(195);
				match(T__18);
				setState(196);
				match(T__17);
				setState(197);
				match(T__2);
				setState(198);
				list();
				setState(199);
				match(T__3);
				}
				break;
			case 3:
				_localctx = new MinusSumContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(201);
				match(T__19);
				setState(202);
				match(T__17);
				setState(203);
				match(T__2);
				setState(204);
				list();
				setState(205);
				match(T__3);
				}
				break;
			case 4:
				_localctx = new MinusFactorContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(207);
				match(T__19);
				setState(208);
				factor();
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
	public static class VarFactorContext extends FactorContext {
		public Var_idContext var_id() {
			return getRuleContext(Var_idContext.class,0);
		}
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
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
		enterRule(_localctx, 26, RULE_factor);
		try {
			_localctx = new VarFactorContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				{
				setState(211);
				exp(0);
				}
				break;
			}
			setState(214);
			var_id();
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
		public Index_var_idContext index_var_id() {
			return getRuleContext(Index_var_idContext.class,0);
		}
		public TerminalNode ID() { return getToken(PLIModelParser.ID, 0); }
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
		enterRule(_localctx, 28, RULE_var_id);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(216);
			((Var_idContext)_localctx).name = match(ID);
			setState(217);
			index_var_id();
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
		enterRule(_localctx, 30, RULE_index_var_id);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(230);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				{
				setState(219);
				match(T__20);
				setState(220);
				exp(0);
				setState(225);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__4) {
					{
					{
					setState(221);
					match(T__4);
					setState(222);
					exp(0);
					}
					}
					setState(227);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(228);
				match(T__21);
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
		enterRule(_localctx, 32, RULE_bound);
		try {
			setState(242);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				_localctx = new OneSideBoundContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(232);
				((OneSideBoundContext)_localctx).name = var_id();
				setState(233);
				((OneSideBoundContext)_localctx).op = rel_op();
				setState(234);
				exp(0);
				}
				break;
			case 2:
				_localctx = new TwoSideBoundContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(236);
				((TwoSideBoundContext)_localctx).li = exp(0);
				setState(237);
				match(T__22);
				setState(238);
				((TwoSideBoundContext)_localctx).name = var_id();
				setState(239);
				match(T__22);
				setState(240);
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
		enterRule(_localctx, 34, RULE_bounds);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(244);
			match(T__23);
			setState(246); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(245);
				list();
				}
				}
				setState(248); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__13) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << ID) | (1L << INT) | (1L << DOUBLE))) != 0) );
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
		enterRule(_localctx, 36, RULE_bin_vars);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(250);
			match(T__24);
			setState(252); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(251);
				list();
				}
				}
				setState(254); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__13) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << ID) | (1L << INT) | (1L << DOUBLE))) != 0) );
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
		enterRule(_localctx, 38, RULE_int_vars);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256);
			match(T__25);
			setState(258); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(257);
				list();
				}
				}
				setState(260); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__13) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << ID) | (1L << INT) | (1L << DOUBLE))) != 0) );
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
		enterRule(_localctx, 40, RULE_free_vars);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262);
			match(T__26);
			setState(264); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(263);
				list();
				}
				}
				setState(266); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__13) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << ID) | (1L << INT) | (1L << DOUBLE))) != 0) );
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
		int _startState = 42;
		enterRecursionRule(_localctx, 42, RULE_exp, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(283);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				{
				_localctx = new UnaryOpExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(269);
				((UnaryOpExprContext)_localctx).op = match(T__27);
				setState(270);
				((UnaryOpExprContext)_localctx).right = exp(13);
				}
				break;
			case 2:
				{
				_localctx = new UnaryOpExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(271);
				((UnaryOpExprContext)_localctx).op = match(T__28);
				setState(272);
				((UnaryOpExprContext)_localctx).right = exp(12);
				}
				break;
			case 3:
				{
				_localctx = new UnaryOpExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(273);
				((UnaryOpExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__18) | (1L << T__19) | (1L << T__29))) != 0)) ) {
					((UnaryOpExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(274);
				((UnaryOpExprContext)_localctx).right = exp(11);
				}
				break;
			case 4:
				{
				_localctx = new ParenExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(275);
				match(T__2);
				setState(276);
				exp(0);
				setState(277);
				match(T__3);
				}
				break;
			case 5:
				{
				_localctx = new FunExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(279);
				call_function();
				}
				break;
			case 6:
				{
				_localctx = new IdExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(280);
				((IdExprContext)_localctx).id = match(ID);
				}
				break;
			case 7:
				{
				_localctx = new DoubleExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(281);
				match(DOUBLE);
				}
				break;
			case 8:
				{
				_localctx = new IntExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(282);
				match(INT);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(302);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(300);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
					case 1:
						{
						_localctx = new OpExprContext(new ExpContext(_parentctx, _parentState));
						((OpExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(285);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(286);
						((OpExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__30) | (1L << T__31) | (1L << T__32))) != 0)) ) {
							((OpExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(287);
						((OpExprContext)_localctx).right = exp(11);
						}
						break;
					case 2:
						{
						_localctx = new OpExprContext(new ExpContext(_parentctx, _parentState));
						((OpExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(288);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(289);
						((OpExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__18 || _la==T__19) ) {
							((OpExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(290);
						((OpExprContext)_localctx).right = exp(10);
						}
						break;
					case 3:
						{
						_localctx = new OpExprContext(new ExpContext(_parentctx, _parentState));
						((OpExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(291);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(292);
						((OpExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__22) | (1L << T__33) | (1L << T__34) | (1L << T__35))) != 0)) ) {
							((OpExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(293);
						((OpExprContext)_localctx).right = exp(9);
						}
						break;
					case 4:
						{
						_localctx = new OpExprContext(new ExpContext(_parentctx, _parentState));
						((OpExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(294);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(295);
						((OpExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__1 || _la==T__15) ) {
							((OpExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(296);
						((OpExprContext)_localctx).right = exp(8);
						}
						break;
					case 5:
						{
						_localctx = new OpExprContext(new ExpContext(_parentctx, _parentState));
						((OpExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(297);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(298);
						((OpExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__36 || _la==T__37) ) {
							((OpExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(299);
						((OpExprContext)_localctx).right = exp(7);
						}
						break;
					}
					} 
				}
				setState(304);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
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
		public Real_parametersContext real_parameters() {
			return getRuleContext(Real_parametersContext.class,0);
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
		enterRule(_localctx, 44, RULE_call_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(305);
			((Call_functionContext)_localctx).name = match(ID);
			setState(306);
			match(T__2);
			setState(308);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__18) | (1L << T__19) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << ID) | (1L << INT) | (1L << DOUBLE))) != 0)) {
				{
				setState(307);
				real_parameters();
				}
			}

			setState(310);
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

	public static class Real_parametersContext extends ParserRuleContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public Real_parametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_real_parameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).enterReal_parameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PLIModelListener ) ((PLIModelListener)listener).exitReal_parameters(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PLIModelVisitor ) return ((PLIModelVisitor<? extends T>)visitor).visitReal_parameters(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Real_parametersContext real_parameters() throws RecognitionException {
		Real_parametersContext _localctx = new Real_parametersContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_real_parameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(312);
			exp(0);
			setState(317);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(313);
				match(T__4);
				setState(314);
				exp(0);
				}
				}
				setState(319);
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
		enterRule(_localctx, 48, RULE_rel_op);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(320);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__22) | (1L << T__33) | (1L << T__34) | (1L << T__35))) != 0)) ) {
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
		case 10:
			return constraint_sempred((ConstraintContext)_localctx, predIndex);
		case 21:
			return exp_sempred((ExpContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean constraint_sempred(ConstraintContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3,\u0145\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\3\2\5\2\66\n\2\3\2\3\2\3\2\5\2;\n\2\3\2\5\2>\n\2\3\2\5\2A\n"+
		"\2\3\2\5\2D\n\2\3\3\3\3\7\3H\n\3\f\3\16\3K\13\3\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\5\4U\n\4\3\4\5\4X\n\4\3\5\3\5\3\5\7\5]\n\5\f\5\16\5`\13\5\3"+
		"\6\3\6\5\6d\n\6\3\7\3\7\3\7\3\7\3\b\3\b\6\bl\n\b\r\b\16\bm\3\t\3\t\3\t"+
		"\7\ts\n\t\f\t\16\tv\13\t\3\t\3\t\5\tz\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13"+
		"\3\13\3\13\5\13\u0085\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\6\f\u0098\n\f\r\f\16\f\u0099\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00a7\n\f\3\f\3\f\3\f\7\f\u00ac\n\f\f"+
		"\f\16\f\u00af\13\f\3\r\3\r\7\r\u00b3\n\r\f\r\16\r\u00b6\13\r\3\r\3\r\3"+
		"\r\3\r\3\r\7\r\u00bd\n\r\f\r\16\r\u00c0\13\r\5\r\u00c2\n\r\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5"+
		"\16\u00d4\n\16\3\17\5\17\u00d7\n\17\3\17\3\17\3\20\3\20\3\20\3\21\3\21"+
		"\3\21\3\21\7\21\u00e2\n\21\f\21\16\21\u00e5\13\21\3\21\3\21\5\21\u00e9"+
		"\n\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u00f5\n\22"+
		"\3\23\3\23\6\23\u00f9\n\23\r\23\16\23\u00fa\3\24\3\24\6\24\u00ff\n\24"+
		"\r\24\16\24\u0100\3\25\3\25\6\25\u0105\n\25\r\25\16\25\u0106\3\26\3\26"+
		"\6\26\u010b\n\26\r\26\16\26\u010c\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3"+
		"\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\5\27\u011e\n\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\7\27\u012f"+
		"\n\27\f\27\16\27\u0132\13\27\3\30\3\30\3\30\5\30\u0137\n\30\3\30\3\30"+
		"\3\31\3\31\3\31\7\31\u013e\n\31\f\31\16\31\u0141\13\31\3\32\3\32\3\32"+
		"\2\4\26,\33\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\2\n"+
		"\3\2\t\n\4\2\25\26  \3\2!#\3\2\25\26\4\2\31\31$&\4\2\4\4\22\22\3\2\'("+
		"\5\2\4\4\31\31$&\2\u015c\2\65\3\2\2\2\4E\3\2\2\2\6W\3\2\2\2\bY\3\2\2\2"+
		"\na\3\2\2\2\fe\3\2\2\2\16i\3\2\2\2\20o\3\2\2\2\22{\3\2\2\2\24\u0084\3"+
		"\2\2\2\26\u00a6\3\2\2\2\30\u00c1\3\2\2\2\32\u00d3\3\2\2\2\34\u00d6\3\2"+
		"\2\2\36\u00da\3\2\2\2 \u00e8\3\2\2\2\"\u00f4\3\2\2\2$\u00f6\3\2\2\2&\u00fc"+
		"\3\2\2\2(\u0102\3\2\2\2*\u0108\3\2\2\2,\u011d\3\2\2\2.\u0133\3\2\2\2\60"+
		"\u013a\3\2\2\2\62\u0142\3\2\2\2\64\66\5\4\3\2\65\64\3\2\2\2\65\66\3\2"+
		"\2\2\66\67\3\2\2\2\678\5\f\7\28:\5\16\b\29;\5$\23\2:9\3\2\2\2:;\3\2\2"+
		"\2;=\3\2\2\2<>\5&\24\2=<\3\2\2\2=>\3\2\2\2>@\3\2\2\2?A\5(\25\2@?\3\2\2"+
		"\2@A\3\2\2\2AC\3\2\2\2BD\5*\26\2CB\3\2\2\2CD\3\2\2\2D\3\3\2\2\2EI\7\3"+
		"\2\2FH\5\6\4\2GF\3\2\2\2HK\3\2\2\2IG\3\2\2\2IJ\3\2\2\2J\5\3\2\2\2KI\3"+
		"\2\2\2LM\7)\2\2MN\7)\2\2NO\7\4\2\2OX\5,\27\2PQ\7)\2\2QR\7)\2\2RT\7\5\2"+
		"\2SU\5\b\5\2TS\3\2\2\2TU\3\2\2\2UV\3\2\2\2VX\7\6\2\2WL\3\2\2\2WP\3\2\2"+
		"\2X\7\3\2\2\2Y^\5\n\6\2Z[\7\7\2\2[]\5\n\6\2\\Z\3\2\2\2]`\3\2\2\2^\\\3"+
		"\2\2\2^_\3\2\2\2_\t\3\2\2\2`^\3\2\2\2ac\7)\2\2bd\7)\2\2cb\3\2\2\2cd\3"+
		"\2\2\2d\13\3\2\2\2ef\7\b\2\2fg\t\2\2\2gh\5\30\r\2h\r\3\2\2\2ik\7\13\2"+
		"\2jl\5\20\t\2kj\3\2\2\2lm\3\2\2\2mk\3\2\2\2mn\3\2\2\2n\17\3\2\2\2ot\5"+
		"\24\13\2pq\7\7\2\2qs\5\22\n\2rp\3\2\2\2sv\3\2\2\2tr\3\2\2\2tu\3\2\2\2"+
		"uy\3\2\2\2vt\3\2\2\2wx\7\f\2\2xz\5,\27\2yw\3\2\2\2yz\3\2\2\2z\21\3\2\2"+
		"\2{|\7)\2\2|}\7\r\2\2}~\5,\27\2~\177\7\16\2\2\177\u0080\5,\27\2\u0080"+
		"\23\3\2\2\2\u0081\u0085\5\26\f\2\u0082\u0085\5\"\22\2\u0083\u0085\5\30"+
		"\r\2\u0084\u0081\3\2\2\2\u0084\u0082\3\2\2\2\u0084\u0083\3\2\2\2\u0085"+
		"\25\3\2\2\2\u0086\u0087\b\f\1\2\u0087\u0088\5\30\r\2\u0088\u0089\5\62"+
		"\32\2\u0089\u008a\5,\27\2\u008a\u00a7\3\2\2\2\u008b\u008c\5\36\20\2\u008c"+
		"\u008d\7\17\2\2\u008d\u008e\5\26\f\7\u008e\u00a7\3\2\2\2\u008f\u0090\7"+
		"\20\2\2\u0090\u0091\7\5\2\2\u0091\u0092\5\62\32\2\u0092\u0093\7*\2\2\u0093"+
		"\u0094\7\7\2\2\u0094\u0097\5\26\f\2\u0095\u0096\7\f\2\2\u0096\u0098\5"+
		"\26\f\2\u0097\u0095\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u0097\3\2\2\2\u0099"+
		"\u009a\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u009c\7\6\2\2\u009c\u00a7\3\2"+
		"\2\2\u009d\u009e\5\36\20\2\u009e\u009f\7\22\2\2\u009f\u00a0\5\36\20\2"+
		"\u00a0\u00a7\3\2\2\2\u00a1\u00a2\7\23\2\2\u00a2\u00a3\7\5\2\2\u00a3\u00a4"+
		"\5\20\t\2\u00a4\u00a5\7\6\2\2\u00a5\u00a7\3\2\2\2\u00a6\u0086\3\2\2\2"+
		"\u00a6\u008b\3\2\2\2\u00a6\u008f\3\2\2\2\u00a6\u009d\3\2\2\2\u00a6\u00a1"+
		"\3\2\2\2\u00a7\u00ad\3\2\2\2\u00a8\u00a9\f\5\2\2\u00a9\u00aa\7\21\2\2"+
		"\u00aa\u00ac\5\26\f\6\u00ab\u00a8\3\2\2\2\u00ac\u00af\3\2\2\2\u00ad\u00ab"+
		"\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\27\3\2\2\2\u00af\u00ad\3\2\2\2\u00b0"+
		"\u00b4\5\34\17\2\u00b1\u00b3\5\32\16\2\u00b2\u00b1\3\2\2\2\u00b3\u00b6"+
		"\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00c2\3\2\2\2\u00b6"+
		"\u00b4\3\2\2\2\u00b7\u00b8\7\24\2\2\u00b8\u00b9\7\5\2\2\u00b9\u00ba\5"+
		"\20\t\2\u00ba\u00be\7\6\2\2\u00bb\u00bd\5\32\16\2\u00bc\u00bb\3\2\2\2"+
		"\u00bd\u00c0\3\2\2\2\u00be\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00c2"+
		"\3\2\2\2\u00c0\u00be\3\2\2\2\u00c1\u00b0\3\2\2\2\u00c1\u00b7\3\2\2\2\u00c2"+
		"\31\3\2\2\2\u00c3\u00c4\7\25\2\2\u00c4\u00d4\5\34\17\2\u00c5\u00c6\7\25"+
		"\2\2\u00c6\u00c7\7\24\2\2\u00c7\u00c8\7\5\2\2\u00c8\u00c9\5\20\t\2\u00c9"+
		"\u00ca\7\6\2\2\u00ca\u00d4\3\2\2\2\u00cb\u00cc\7\26\2\2\u00cc\u00cd\7"+
		"\24\2\2\u00cd\u00ce\7\5\2\2\u00ce\u00cf\5\20\t\2\u00cf\u00d0\7\6\2\2\u00d0"+
		"\u00d4\3\2\2\2\u00d1\u00d2\7\26\2\2\u00d2\u00d4\5\34\17\2\u00d3\u00c3"+
		"\3\2\2\2\u00d3\u00c5\3\2\2\2\u00d3\u00cb\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d4"+
		"\33\3\2\2\2\u00d5\u00d7\5,\27\2\u00d6\u00d5\3\2\2\2\u00d6\u00d7\3\2\2"+
		"\2\u00d7\u00d8\3\2\2\2\u00d8\u00d9\5\36\20\2\u00d9\35\3\2\2\2\u00da\u00db"+
		"\7)\2\2\u00db\u00dc\5 \21\2\u00dc\37\3\2\2\2\u00dd\u00de\7\27\2\2\u00de"+
		"\u00e3\5,\27\2\u00df\u00e0\7\7\2\2\u00e0\u00e2\5,\27\2\u00e1\u00df\3\2"+
		"\2\2\u00e2\u00e5\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4"+
		"\u00e6\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e6\u00e7\7\30\2\2\u00e7\u00e9\3"+
		"\2\2\2\u00e8\u00dd\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9!\3\2\2\2\u00ea\u00eb"+
		"\5\36\20\2\u00eb\u00ec\5\62\32\2\u00ec\u00ed\5,\27\2\u00ed\u00f5\3\2\2"+
		"\2\u00ee\u00ef\5,\27\2\u00ef\u00f0\7\31\2\2\u00f0\u00f1\5\36\20\2\u00f1"+
		"\u00f2\7\31\2\2\u00f2\u00f3\5,\27\2\u00f3\u00f5\3\2\2\2\u00f4\u00ea\3"+
		"\2\2\2\u00f4\u00ee\3\2\2\2\u00f5#\3\2\2\2\u00f6\u00f8\7\32\2\2\u00f7\u00f9"+
		"\5\20\t\2\u00f8\u00f7\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa\u00f8\3\2\2\2"+
		"\u00fa\u00fb\3\2\2\2\u00fb%\3\2\2\2\u00fc\u00fe\7\33\2\2\u00fd\u00ff\5"+
		"\20\t\2\u00fe\u00fd\3\2\2\2\u00ff\u0100\3\2\2\2\u0100\u00fe\3\2\2\2\u0100"+
		"\u0101\3\2\2\2\u0101\'\3\2\2\2\u0102\u0104\7\34\2\2\u0103\u0105\5\20\t"+
		"\2\u0104\u0103\3\2\2\2\u0105\u0106\3\2\2\2\u0106\u0104\3\2\2\2\u0106\u0107"+
		"\3\2\2\2\u0107)\3\2\2\2\u0108\u010a\7\35\2\2\u0109\u010b\5\20\t\2\u010a"+
		"\u0109\3\2\2\2\u010b\u010c\3\2\2\2\u010c\u010a\3\2\2\2\u010c\u010d\3\2"+
		"\2\2\u010d+\3\2\2\2\u010e\u010f\b\27\1\2\u010f\u0110\7\36\2\2\u0110\u011e"+
		"\5,\27\17\u0111\u0112\7\37\2\2\u0112\u011e\5,\27\16\u0113\u0114\t\3\2"+
		"\2\u0114\u011e\5,\27\r\u0115\u0116\7\5\2\2\u0116\u0117\5,\27\2\u0117\u0118"+
		"\7\6\2\2\u0118\u011e\3\2\2\2\u0119\u011e\5.\30\2\u011a\u011e\7)\2\2\u011b"+
		"\u011e\7+\2\2\u011c\u011e\7*\2\2\u011d\u010e\3\2\2\2\u011d\u0111\3\2\2"+
		"\2\u011d\u0113\3\2\2\2\u011d\u0115\3\2\2\2\u011d\u0119\3\2\2\2\u011d\u011a"+
		"\3\2\2\2\u011d\u011b\3\2\2\2\u011d\u011c\3\2\2\2\u011e\u0130\3\2\2\2\u011f"+
		"\u0120\f\f\2\2\u0120\u0121\t\4\2\2\u0121\u012f\5,\27\r\u0122\u0123\f\13"+
		"\2\2\u0123\u0124\t\5\2\2\u0124\u012f\5,\27\f\u0125\u0126\f\n\2\2\u0126"+
		"\u0127\t\6\2\2\u0127\u012f\5,\27\13\u0128\u0129\f\t\2\2\u0129\u012a\t"+
		"\7\2\2\u012a\u012f\5,\27\n\u012b\u012c\f\b\2\2\u012c\u012d\t\b\2\2\u012d"+
		"\u012f\5,\27\t\u012e\u011f\3\2\2\2\u012e\u0122\3\2\2\2\u012e\u0125\3\2"+
		"\2\2\u012e\u0128\3\2\2\2\u012e\u012b\3\2\2\2\u012f\u0132\3\2\2\2\u0130"+
		"\u012e\3\2\2\2\u0130\u0131\3\2\2\2\u0131-\3\2\2\2\u0132\u0130\3\2\2\2"+
		"\u0133\u0134\7)\2\2\u0134\u0136\7\5\2\2\u0135\u0137\5\60\31\2\u0136\u0135"+
		"\3\2\2\2\u0136\u0137\3\2\2\2\u0137\u0138\3\2\2\2\u0138\u0139\7\6\2\2\u0139"+
		"/\3\2\2\2\u013a\u013f\5,\27\2\u013b\u013c\7\7\2\2\u013c\u013e\5,\27\2"+
		"\u013d\u013b\3\2\2\2\u013e\u0141\3\2\2\2\u013f\u013d\3\2\2\2\u013f\u0140"+
		"\3\2\2\2\u0140\61\3\2\2\2\u0141\u013f\3\2\2\2\u0142\u0143\t\t\2\2\u0143"+
		"\63\3\2\2\2$\65:=@CITW^cmty\u0084\u0099\u00a6\u00ad\u00b4\u00be\u00c1"+
		"\u00d3\u00d6\u00e3\u00e8\u00f4\u00fa\u0100\u0106\u010c\u011d\u012e\u0130"+
		"\u0136\u013f";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}