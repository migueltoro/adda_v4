// Generated from Program.g4 by ANTLR 4.9.3
package us.lsi.tiposrecursivos.parsers.program;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ProgramParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		BOOL=32, TYPE=33, INT=34, DOUBLE=35, ID=36, STR=37, WS=38;
	public static final int
		RULE_program = 0, RULE_declaration = 1, RULE_formal_parameters = 2, RULE_formal_parameter = 3, 
		RULE_sentence = 4, RULE_block = 5, RULE_exp = 6, RULE_real_parameters = 7;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "declaration", "formal_parameters", "formal_parameter", "sentence", 
			"block", "exp", "real_parameters"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'Program'", "':'", "'='", "'('", "','", "')'", "'if'", "'else'", 
			"'while'", "'return'", "'{'", "'}'", "'int'", "'double'", "'str'", "'bool'", 
			"'sqrt'", "'+'", "'-'", "'!'", "'*'", "'/'", "'%'", "'<='", "'<'", "'>='", 
			"'>'", "'!='", "'&&'", "'||'", "'?'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, "BOOL", "TYPE", "INT", 
			"DOUBLE", "ID", "STR", "WS"
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
	public String getGrammarFileName() { return "Program.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ProgramParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public Formal_parametersContext formal_parameters() {
			return getRuleContext(Formal_parametersContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProgramVisitor ) return ((ProgramVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
			match(T__0);
			setState(17);
			formal_parameters();
			setState(18);
			block();
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
	public static class FunDeclarationContext extends DeclarationContext {
		public Token id;
		public Token type;
		public BlockContext body;
		public Formal_parametersContext formal_parameters() {
			return getRuleContext(Formal_parametersContext.class,0);
		}
		public TerminalNode ID() { return getToken(ProgramParser.ID, 0); }
		public TerminalNode TYPE() { return getToken(ProgramParser.TYPE, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public FunDeclarationContext(DeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProgramVisitor ) return ((ProgramVisitor<? extends T>)visitor).visitFunDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VarDeclarationContext extends DeclarationContext {
		public Token id;
		public Token type;
		public TerminalNode ID() { return getToken(ProgramParser.ID, 0); }
		public TerminalNode TYPE() { return getToken(ProgramParser.TYPE, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public VarDeclarationContext(DeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProgramVisitor ) return ((ProgramVisitor<? extends T>)visitor).visitVarDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declaration);
		int _la;
		try {
			setState(33);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				_localctx = new VarDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(20);
				((VarDeclarationContext)_localctx).id = match(ID);
				setState(21);
				match(T__1);
				setState(22);
				((VarDeclarationContext)_localctx).type = match(TYPE);
				setState(25);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(23);
					match(T__2);
					setState(24);
					exp(0);
					}
				}

				}
				break;
			case 2:
				_localctx = new FunDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(27);
				((FunDeclarationContext)_localctx).id = match(ID);
				setState(28);
				formal_parameters();
				setState(29);
				match(T__1);
				setState(30);
				((FunDeclarationContext)_localctx).type = match(TYPE);
				setState(31);
				((FunDeclarationContext)_localctx).body = block();
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProgramVisitor ) return ((ProgramVisitor<? extends T>)visitor).visitFormal_parameters(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Formal_parametersContext formal_parameters() throws RecognitionException {
		Formal_parametersContext _localctx = new Formal_parametersContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_formal_parameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			match(T__3);
			setState(44);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(36);
				formal_parameter();
				setState(41);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__4) {
					{
					{
					setState(37);
					match(T__4);
					setState(38);
					formal_parameter();
					}
					}
					setState(43);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(46);
			match(T__5);
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
		public Token id;
		public Token type;
		public TerminalNode ID() { return getToken(ProgramParser.ID, 0); }
		public TerminalNode TYPE() { return getToken(ProgramParser.TYPE, 0); }
		public Formal_parameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formal_parameter; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProgramVisitor ) return ((ProgramVisitor<? extends T>)visitor).visitFormal_parameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Formal_parameterContext formal_parameter() throws RecognitionException {
		Formal_parameterContext _localctx = new Formal_parameterContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_formal_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			((Formal_parameterContext)_localctx).id = match(ID);
			setState(49);
			match(T__1);
			setState(50);
			((Formal_parameterContext)_localctx).type = match(TYPE);
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

	public static class SentenceContext extends ParserRuleContext {
		public SentenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sentence; }
	 
		public SentenceContext() { }
		public void copyFrom(SentenceContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class IfSentenceContext extends SentenceContext {
		public BlockContext trueBlock;
		public BlockContext elseBlock;
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public IfSentenceContext(SentenceContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProgramVisitor ) return ((ProgramVisitor<? extends T>)visitor).visitIfSentence(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AsignSentenceContext extends SentenceContext {
		public Token id;
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode ID() { return getToken(ProgramParser.ID, 0); }
		public AsignSentenceContext(SentenceContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProgramVisitor ) return ((ProgramVisitor<? extends T>)visitor).visitAsignSentence(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ReturnSentenceContext extends SentenceContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public ReturnSentenceContext(SentenceContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProgramVisitor ) return ((ProgramVisitor<? extends T>)visitor).visitReturnSentence(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WhileSentenceContext extends SentenceContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public WhileSentenceContext(SentenceContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProgramVisitor ) return ((ProgramVisitor<? extends T>)visitor).visitWhileSentence(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SentenceContext sentence() throws RecognitionException {
		SentenceContext _localctx = new SentenceContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_sentence);
		int _la;
		try {
			setState(72);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				_localctx = new AsignSentenceContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(52);
				((AsignSentenceContext)_localctx).id = match(ID);
				setState(53);
				match(T__2);
				setState(54);
				exp(0);
				}
				break;
			case T__6:
				_localctx = new IfSentenceContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(55);
				match(T__6);
				setState(56);
				match(T__3);
				setState(57);
				exp(0);
				setState(58);
				match(T__5);
				setState(59);
				((IfSentenceContext)_localctx).trueBlock = block();
				setState(62);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__7) {
					{
					setState(60);
					match(T__7);
					setState(61);
					((IfSentenceContext)_localctx).elseBlock = block();
					}
				}

				}
				break;
			case T__8:
				_localctx = new WhileSentenceContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(64);
				match(T__8);
				setState(65);
				match(T__3);
				setState(66);
				exp(0);
				setState(67);
				match(T__5);
				setState(68);
				block();
				}
				break;
			case T__9:
				_localctx = new ReturnSentenceContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(70);
				match(T__9);
				setState(71);
				exp(0);
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

	public static class BlockContext extends ParserRuleContext {
		public DeclarationContext declarations;
		public SentenceContext sentences;
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public List<SentenceContext> sentence() {
			return getRuleContexts(SentenceContext.class);
		}
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProgramVisitor ) return ((ProgramVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_block);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			match(T__10);
			setState(78);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(75);
					((BlockContext)_localctx).declarations = declaration();
					}
					} 
				}
				setState(80);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			setState(82); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(81);
				((BlockContext)_localctx).sentences = sentence();
				}
				}
				setState(84); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__8) | (1L << T__9) | (1L << ID))) != 0) );
			setState(86);
			match(T__11);
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
	public static class StrExprContext extends ExpContext {
		public TerminalNode STR() { return getToken(ProgramParser.STR, 0); }
		public StrExprContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProgramVisitor ) return ((ProgramVisitor<? extends T>)visitor).visitStrExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnaryExprContext extends ExpContext {
		public Token op;
		public ExpContext right;
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public UnaryExprContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProgramVisitor ) return ((ProgramVisitor<? extends T>)visitor).visitUnaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntExprContext extends ExpContext {
		public TerminalNode INT() { return getToken(ProgramParser.INT, 0); }
		public IntExprContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProgramVisitor ) return ((ProgramVisitor<? extends T>)visitor).visitIntExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TernaryExprContext extends ExpContext {
		public ExpContext guard;
		public ExpContext left;
		public ExpContext right;
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TernaryExprContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProgramVisitor ) return ((ProgramVisitor<? extends T>)visitor).visitTernaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinaryExprContext extends ExpContext {
		public ExpContext left;
		public Token op;
		public ExpContext right;
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public BinaryExprContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProgramVisitor ) return ((ProgramVisitor<? extends T>)visitor).visitBinaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CallExprContext extends ExpContext {
		public Token name;
		public TerminalNode ID() { return getToken(ProgramParser.ID, 0); }
		public Real_parametersContext real_parameters() {
			return getRuleContext(Real_parametersContext.class,0);
		}
		public CallExprContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProgramVisitor ) return ((ProgramVisitor<? extends T>)visitor).visitCallExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BoolExprContext extends ExpContext {
		public TerminalNode BOOL() { return getToken(ProgramParser.BOOL, 0); }
		public BoolExprContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProgramVisitor ) return ((ProgramVisitor<? extends T>)visitor).visitBoolExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenExprContext extends ExpContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public ParenExprContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProgramVisitor ) return ((ProgramVisitor<? extends T>)visitor).visitParenExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DoubleExpContext extends ExpContext {
		public TerminalNode DOUBLE() { return getToken(ProgramParser.DOUBLE, 0); }
		public DoubleExpContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProgramVisitor ) return ((ProgramVisitor<? extends T>)visitor).visitDoubleExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IdExprContext extends ExpContext {
		public Token id;
		public TerminalNode ID() { return getToken(ProgramParser.ID, 0); }
		public IdExprContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProgramVisitor ) return ((ProgramVisitor<? extends T>)visitor).visitIdExpr(this);
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
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_exp, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				_localctx = new UnaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(89);
				((UnaryExprContext)_localctx).op = match(T__12);
				setState(90);
				match(T__3);
				setState(91);
				((UnaryExprContext)_localctx).right = exp(0);
				setState(92);
				match(T__5);
				}
				break;
			case 2:
				{
				_localctx = new UnaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(94);
				((UnaryExprContext)_localctx).op = match(T__13);
				setState(95);
				match(T__3);
				setState(96);
				((UnaryExprContext)_localctx).right = exp(0);
				setState(97);
				match(T__5);
				}
				break;
			case 3:
				{
				_localctx = new UnaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(99);
				((UnaryExprContext)_localctx).op = match(T__14);
				setState(100);
				match(T__3);
				setState(101);
				((UnaryExprContext)_localctx).right = exp(0);
				setState(102);
				match(T__5);
				}
				break;
			case 4:
				{
				_localctx = new UnaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(104);
				((UnaryExprContext)_localctx).op = match(T__15);
				setState(105);
				match(T__3);
				setState(106);
				((UnaryExprContext)_localctx).right = exp(0);
				setState(107);
				match(T__5);
				}
				break;
			case 5:
				{
				_localctx = new UnaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(109);
				((UnaryExprContext)_localctx).op = match(T__16);
				setState(110);
				match(T__3);
				setState(111);
				((UnaryExprContext)_localctx).right = exp(0);
				setState(112);
				match(T__5);
				}
				break;
			case 6:
				{
				_localctx = new UnaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(114);
				((UnaryExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__17) | (1L << T__18) | (1L << T__19))) != 0)) ) {
					((UnaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(115);
				((UnaryExprContext)_localctx).right = exp(14);
				}
				break;
			case 7:
				{
				_localctx = new ParenExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(116);
				match(T__3);
				setState(117);
				exp(0);
				setState(118);
				match(T__5);
				}
				break;
			case 8:
				{
				_localctx = new CallExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(120);
				((CallExprContext)_localctx).name = match(ID);
				setState(121);
				match(T__3);
				setState(123);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << BOOL) | (1L << INT) | (1L << DOUBLE) | (1L << ID) | (1L << STR))) != 0)) {
					{
					setState(122);
					real_parameters();
					}
				}

				setState(125);
				match(T__5);
				}
				break;
			case 9:
				{
				_localctx = new IdExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(126);
				((IdExprContext)_localctx).id = match(ID);
				}
				break;
			case 10:
				{
				_localctx = new DoubleExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(127);
				match(DOUBLE);
				}
				break;
			case 11:
				{
				_localctx = new IntExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(128);
				match(INT);
				}
				break;
			case 12:
				{
				_localctx = new StrExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(129);
				match(STR);
				}
				break;
			case 13:
				{
				_localctx = new BoolExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(130);
				match(BOOL);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(156);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(154);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
					case 1:
						{
						_localctx = new BinaryExprContext(new ExpContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(133);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(134);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__20) | (1L << T__21) | (1L << T__22))) != 0)) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(135);
						((BinaryExprContext)_localctx).right = exp(14);
						}
						break;
					case 2:
						{
						_localctx = new BinaryExprContext(new ExpContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(136);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(137);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__17 || _la==T__18) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(138);
						((BinaryExprContext)_localctx).right = exp(13);
						}
						break;
					case 3:
						{
						_localctx = new BinaryExprContext(new ExpContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(139);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(140);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26))) != 0)) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(141);
						((BinaryExprContext)_localctx).right = exp(12);
						}
						break;
					case 4:
						{
						_localctx = new BinaryExprContext(new ExpContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(142);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(143);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__2 || _la==T__27) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(144);
						((BinaryExprContext)_localctx).right = exp(11);
						}
						break;
					case 5:
						{
						_localctx = new BinaryExprContext(new ExpContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(145);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(146);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__28 || _la==T__29) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(147);
						((BinaryExprContext)_localctx).right = exp(10);
						}
						break;
					case 6:
						{
						_localctx = new TernaryExprContext(new ExpContext(_parentctx, _parentState));
						((TernaryExprContext)_localctx).guard = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(148);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(149);
						match(T__30);
						setState(150);
						((TernaryExprContext)_localctx).left = exp(0);
						setState(151);
						match(T__1);
						setState(152);
						((TernaryExprContext)_localctx).right = exp(9);
						}
						break;
					}
					} 
				}
				setState(158);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ProgramVisitor ) return ((ProgramVisitor<? extends T>)visitor).visitReal_parameters(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Real_parametersContext real_parameters() throws RecognitionException {
		Real_parametersContext _localctx = new Real_parametersContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_real_parameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			exp(0);
			setState(164);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(160);
				match(T__4);
				setState(161);
				exp(0);
				}
				}
				setState(166);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 6:
			return exp_sempred((ExpContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean exp_sempred(ExpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 13);
		case 1:
			return precpred(_ctx, 12);
		case 2:
			return precpred(_ctx, 11);
		case 3:
			return precpred(_ctx, 10);
		case 4:
			return precpred(_ctx, 9);
		case 5:
			return precpred(_ctx, 8);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3(\u00aa\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\2\3\2"+
		"\3\3\3\3\3\3\3\3\3\3\5\3\34\n\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3$\n\3\3\4\3"+
		"\4\3\4\3\4\7\4*\n\4\f\4\16\4-\13\4\5\4/\n\4\3\4\3\4\3\5\3\5\3\5\3\5\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6A\n\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\5\6K\n\6\3\7\3\7\7\7O\n\7\f\7\16\7R\13\7\3\7\6\7U\n\7\r\7\16"+
		"\7V\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\5\b~\n\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u0086\n\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\7\b\u009d\n\b\f\b\16\b\u00a0\13\b\3\t\3\t\3\t\7\t\u00a5\n\t\f\t\16"+
		"\t\u00a8\13\t\3\t\2\3\16\n\2\4\6\b\n\f\16\20\2\b\3\2\24\26\3\2\27\31\3"+
		"\2\24\25\3\2\32\35\4\2\5\5\36\36\3\2\37 \2\u00bf\2\22\3\2\2\2\4#\3\2\2"+
		"\2\6%\3\2\2\2\b\62\3\2\2\2\nJ\3\2\2\2\fL\3\2\2\2\16\u0085\3\2\2\2\20\u00a1"+
		"\3\2\2\2\22\23\7\3\2\2\23\24\5\6\4\2\24\25\5\f\7\2\25\3\3\2\2\2\26\27"+
		"\7&\2\2\27\30\7\4\2\2\30\33\7#\2\2\31\32\7\5\2\2\32\34\5\16\b\2\33\31"+
		"\3\2\2\2\33\34\3\2\2\2\34$\3\2\2\2\35\36\7&\2\2\36\37\5\6\4\2\37 \7\4"+
		"\2\2 !\7#\2\2!\"\5\f\7\2\"$\3\2\2\2#\26\3\2\2\2#\35\3\2\2\2$\5\3\2\2\2"+
		"%.\7\6\2\2&+\5\b\5\2\'(\7\7\2\2(*\5\b\5\2)\'\3\2\2\2*-\3\2\2\2+)\3\2\2"+
		"\2+,\3\2\2\2,/\3\2\2\2-+\3\2\2\2.&\3\2\2\2./\3\2\2\2/\60\3\2\2\2\60\61"+
		"\7\b\2\2\61\7\3\2\2\2\62\63\7&\2\2\63\64\7\4\2\2\64\65\7#\2\2\65\t\3\2"+
		"\2\2\66\67\7&\2\2\678\7\5\2\28K\5\16\b\29:\7\t\2\2:;\7\6\2\2;<\5\16\b"+
		"\2<=\7\b\2\2=@\5\f\7\2>?\7\n\2\2?A\5\f\7\2@>\3\2\2\2@A\3\2\2\2AK\3\2\2"+
		"\2BC\7\13\2\2CD\7\6\2\2DE\5\16\b\2EF\7\b\2\2FG\5\f\7\2GK\3\2\2\2HI\7\f"+
		"\2\2IK\5\16\b\2J\66\3\2\2\2J9\3\2\2\2JB\3\2\2\2JH\3\2\2\2K\13\3\2\2\2"+
		"LP\7\r\2\2MO\5\4\3\2NM\3\2\2\2OR\3\2\2\2PN\3\2\2\2PQ\3\2\2\2QT\3\2\2\2"+
		"RP\3\2\2\2SU\5\n\6\2TS\3\2\2\2UV\3\2\2\2VT\3\2\2\2VW\3\2\2\2WX\3\2\2\2"+
		"XY\7\16\2\2Y\r\3\2\2\2Z[\b\b\1\2[\\\7\17\2\2\\]\7\6\2\2]^\5\16\b\2^_\7"+
		"\b\2\2_\u0086\3\2\2\2`a\7\20\2\2ab\7\6\2\2bc\5\16\b\2cd\7\b\2\2d\u0086"+
		"\3\2\2\2ef\7\21\2\2fg\7\6\2\2gh\5\16\b\2hi\7\b\2\2i\u0086\3\2\2\2jk\7"+
		"\22\2\2kl\7\6\2\2lm\5\16\b\2mn\7\b\2\2n\u0086\3\2\2\2op\7\23\2\2pq\7\6"+
		"\2\2qr\5\16\b\2rs\7\b\2\2s\u0086\3\2\2\2tu\t\2\2\2u\u0086\5\16\b\20vw"+
		"\7\6\2\2wx\5\16\b\2xy\7\b\2\2y\u0086\3\2\2\2z{\7&\2\2{}\7\6\2\2|~\5\20"+
		"\t\2}|\3\2\2\2}~\3\2\2\2~\177\3\2\2\2\177\u0086\7\b\2\2\u0080\u0086\7"+
		"&\2\2\u0081\u0086\7%\2\2\u0082\u0086\7$\2\2\u0083\u0086\7\'\2\2\u0084"+
		"\u0086\7\"\2\2\u0085Z\3\2\2\2\u0085`\3\2\2\2\u0085e\3\2\2\2\u0085j\3\2"+
		"\2\2\u0085o\3\2\2\2\u0085t\3\2\2\2\u0085v\3\2\2\2\u0085z\3\2\2\2\u0085"+
		"\u0080\3\2\2\2\u0085\u0081\3\2\2\2\u0085\u0082\3\2\2\2\u0085\u0083\3\2"+
		"\2\2\u0085\u0084\3\2\2\2\u0086\u009e\3\2\2\2\u0087\u0088\f\17\2\2\u0088"+
		"\u0089\t\3\2\2\u0089\u009d\5\16\b\20\u008a\u008b\f\16\2\2\u008b\u008c"+
		"\t\4\2\2\u008c\u009d\5\16\b\17\u008d\u008e\f\r\2\2\u008e\u008f\t\5\2\2"+
		"\u008f\u009d\5\16\b\16\u0090\u0091\f\f\2\2\u0091\u0092\t\6\2\2\u0092\u009d"+
		"\5\16\b\r\u0093\u0094\f\13\2\2\u0094\u0095\t\7\2\2\u0095\u009d\5\16\b"+
		"\f\u0096\u0097\f\n\2\2\u0097\u0098\7!\2\2\u0098\u0099\5\16\b\2\u0099\u009a"+
		"\7\4\2\2\u009a\u009b\5\16\b\13\u009b\u009d\3\2\2\2\u009c\u0087\3\2\2\2"+
		"\u009c\u008a\3\2\2\2\u009c\u008d\3\2\2\2\u009c\u0090\3\2\2\2\u009c\u0093"+
		"\3\2\2\2\u009c\u0096\3\2\2\2\u009d\u00a0\3\2\2\2\u009e\u009c\3\2\2\2\u009e"+
		"\u009f\3\2\2\2\u009f\17\3\2\2\2\u00a0\u009e\3\2\2\2\u00a1\u00a6\5\16\b"+
		"\2\u00a2\u00a3\7\7\2\2\u00a3\u00a5\5\16\b\2\u00a4\u00a2\3\2\2\2\u00a5"+
		"\u00a8\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\21\3\2\2"+
		"\2\u00a8\u00a6\3\2\2\2\17\33#+.@JPV}\u0085\u009c\u009e\u00a6";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}