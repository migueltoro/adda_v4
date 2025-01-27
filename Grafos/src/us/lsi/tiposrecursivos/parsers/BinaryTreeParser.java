// Generated from BinaryTree.g4 by ANTLR 4.9.3
package us.lsi.tiposrecursivos.parsers;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BinaryTreeParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, PARENTESIS_C=4, PARENTESIS_A=5, COMA=6, EMPTY=7, 
		ID=8, WS=9;
	public static final int
		RULE_binary_tree = 0, RULE_label = 1;
	private static String[] makeRuleNames() {
		return new String[] {
			"binary_tree", "label"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "','", "')'", "'/('", "'/)'", "'/,'", "'/_'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, "PARENTESIS_C", "PARENTESIS_A", "COMA", "EMPTY", 
			"ID", "WS"
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
	public String getGrammarFileName() { return "BinaryTree.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public BinaryTreeParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class Binary_treeContext extends ParserRuleContext {
		public Binary_treeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binary_tree; }
	 
		public Binary_treeContext() { }
		public void copyFrom(Binary_treeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class LabelTreeContext extends Binary_treeContext {
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public LabelTreeContext(Binary_treeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryTreeVisitor ) return ((BinaryTreeVisitor<? extends T>)visitor).visitLabelTree(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EmptyTreeContext extends Binary_treeContext {
		public TerminalNode EMPTY() { return getToken(BinaryTreeParser.EMPTY, 0); }
		public EmptyTreeContext(Binary_treeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryTreeVisitor ) return ((BinaryTreeVisitor<? extends T>)visitor).visitEmptyTree(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinaryTreeContext extends Binary_treeContext {
		public LabelContext treeLabel;
		public Binary_treeContext left;
		public Binary_treeContext right;
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public List<Binary_treeContext> binary_tree() {
			return getRuleContexts(Binary_treeContext.class);
		}
		public Binary_treeContext binary_tree(int i) {
			return getRuleContext(Binary_treeContext.class,i);
		}
		public BinaryTreeContext(Binary_treeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryTreeVisitor ) return ((BinaryTreeVisitor<? extends T>)visitor).visitBinaryTree(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Binary_treeContext binary_tree() throws RecognitionException {
		Binary_treeContext _localctx = new Binary_treeContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_binary_tree);
		try {
			setState(13);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				_localctx = new EmptyTreeContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(4);
				match(EMPTY);
				}
				break;
			case 2:
				_localctx = new LabelTreeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(5);
				label();
				}
				break;
			case 3:
				_localctx = new BinaryTreeContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(6);
				((BinaryTreeContext)_localctx).treeLabel = label();
				setState(7);
				match(T__0);
				setState(8);
				((BinaryTreeContext)_localctx).left = binary_tree();
				setState(9);
				match(T__1);
				setState(10);
				((BinaryTreeContext)_localctx).right = binary_tree();
				setState(11);
				match(T__2);
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

	public static class LabelContext extends ParserRuleContext {
		public LabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_label; }
	 
		public LabelContext() { }
		public void copyFrom(LabelContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class IdLabelContext extends LabelContext {
		public TerminalNode ID() { return getToken(BinaryTreeParser.ID, 0); }
		public IdLabelContext(LabelContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryTreeVisitor ) return ((BinaryTreeVisitor<? extends T>)visitor).visitIdLabel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LabelContext label() throws RecognitionException {
		LabelContext _localctx = new LabelContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_label);
		try {
			_localctx = new IdLabelContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(15);
			match(ID);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\13\24\4\2\t\2\4\3"+
		"\t\3\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\20\n\2\3\3\3\3\3\3\2\2\4"+
		"\2\4\2\2\2\23\2\17\3\2\2\2\4\21\3\2\2\2\6\20\7\t\2\2\7\20\5\4\3\2\b\t"+
		"\5\4\3\2\t\n\7\3\2\2\n\13\5\2\2\2\13\f\7\4\2\2\f\r\5\2\2\2\r\16\7\5\2"+
		"\2\16\20\3\2\2\2\17\6\3\2\2\2\17\7\3\2\2\2\17\b\3\2\2\2\20\3\3\2\2\2\21"+
		"\22\7\n\2\2\22\5\3\2\2\2\3\17";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}