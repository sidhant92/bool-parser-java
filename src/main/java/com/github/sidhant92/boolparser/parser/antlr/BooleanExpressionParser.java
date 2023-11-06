package com.github.sidhant92.boolparser.parser.antlr;// Generated from /Users/sid/Desktop/filter1/BooleanExpression.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class BooleanExpressionParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, IN=2, TO=3, AND=4, OR=5, NOT=6, TRUE=7, FALSE=8, CONTAINS_ALL=9, 
		CONTAINS_ANY=10, NE=11, GT=12, GE=13, LT=14, LE=15, EQ=16, LPAREN=17, 
		RPAREN=18, DECIMAL=19, APP_VERSION=20, INTEGER=21, WS=22, WORD=23, ALPHANUMERIC=24, 
		SQ=25, DQ=26;
	public static final int
		RULE_parse = 0, RULE_expression = 1, RULE_comparator = 2, RULE_wordlist = 3, 
		RULE_arrayOperators = 4, RULE_numericTypes = 5, RULE_types = 6, RULE_binary = 7, 
		RULE_bool = 8;
	private static String[] makeRuleNames() {
		return new String[] {
			"parse", "expression", "comparator", "wordlist", "arrayOperators", "numericTypes", 
			"types", "binary", "bool"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "','", null, null, null, null, null, null, null, null, null, "'!='", 
			"'>'", "'>='", "'<'", "'<='", "'='", "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, "IN", "TO", "AND", "OR", "NOT", "TRUE", "FALSE", "CONTAINS_ALL", 
			"CONTAINS_ANY", "NE", "GT", "GE", "LT", "LE", "EQ", "LPAREN", "RPAREN", 
			"DECIMAL", "APP_VERSION", "INTEGER", "WS", "WORD", "ALPHANUMERIC", "SQ", 
			"DQ"
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
	public String getGrammarFileName() { return "BooleanExpression.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public BooleanExpressionParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParseContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode EOF() { return getToken(BooleanExpressionParser.EOF, 0); }
		public ParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanExpressionListener ) ((BooleanExpressionListener)listener).enterParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanExpressionListener ) ((BooleanExpressionListener)listener).exitParse(this);
		}
	}

	public final ParseContext parse() throws RecognitionException {
		ParseContext _localctx = new ParseContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_parse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(18);
			expression(0);
			setState(19);
			match(EOF);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BinaryExpressionContext extends ExpressionContext {
		public ExpressionContext left;
		public BinaryContext op;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public BinaryContext binary() {
			return getRuleContext(BinaryContext.class,0);
		}
		public BinaryExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanExpressionListener ) ((BooleanExpressionListener)listener).enterBinaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanExpressionListener ) ((BooleanExpressionListener)listener).exitBinaryExpression(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TypesExpressionContext extends ExpressionContext {
		public TypesContext types() {
			return getRuleContext(TypesContext.class,0);
		}
		public TypesExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanExpressionListener ) ((BooleanExpressionListener)listener).enterTypesExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanExpressionListener ) ((BooleanExpressionListener)listener).exitTypesExpression(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InExpressionContext extends ExpressionContext {
		public Token field;
		public Token not;
		public WordlistContext data;
		public TerminalNode IN() { return getToken(BooleanExpressionParser.IN, 0); }
		public WordlistContext wordlist() {
			return getRuleContext(WordlistContext.class,0);
		}
		public TerminalNode WORD() { return getToken(BooleanExpressionParser.WORD, 0); }
		public TerminalNode NOT() { return getToken(BooleanExpressionParser.NOT, 0); }
		public InExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanExpressionListener ) ((BooleanExpressionListener)listener).enterInExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanExpressionListener ) ((BooleanExpressionListener)listener).exitInExpression(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ArrayExpressionContext extends ExpressionContext {
		public Token field;
		public ArrayOperatorsContext op;
		public WordlistContext data;
		public ArrayOperatorsContext arrayOperators() {
			return getRuleContext(ArrayOperatorsContext.class,0);
		}
		public WordlistContext wordlist() {
			return getRuleContext(WordlistContext.class,0);
		}
		public TerminalNode WORD() { return getToken(BooleanExpressionParser.WORD, 0); }
		public ArrayExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanExpressionListener ) ((BooleanExpressionListener)listener).enterArrayExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanExpressionListener ) ((BooleanExpressionListener)listener).exitArrayExpression(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ToExpressionContext extends ExpressionContext {
		public Token field;
		public NumericTypesContext lower;
		public NumericTypesContext upper;
		public TerminalNode TO() { return getToken(BooleanExpressionParser.TO, 0); }
		public List<NumericTypesContext> numericTypes() {
			return getRuleContexts(NumericTypesContext.class);
		}
		public NumericTypesContext numericTypes(int i) {
			return getRuleContext(NumericTypesContext.class,i);
		}
		public TerminalNode WORD() { return getToken(BooleanExpressionParser.WORD, 0); }
		public ToExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanExpressionListener ) ((BooleanExpressionListener)listener).enterToExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanExpressionListener ) ((BooleanExpressionListener)listener).exitToExpression(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NotExpressionContext extends ExpressionContext {
		public TerminalNode NOT() { return getToken(BooleanExpressionParser.NOT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public NotExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanExpressionListener ) ((BooleanExpressionListener)listener).enterNotExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanExpressionListener ) ((BooleanExpressionListener)listener).exitNotExpression(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ComparatorExpressionContext extends ExpressionContext {
		public ExpressionContext left;
		public ComparatorContext op;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ComparatorContext comparator() {
			return getRuleContext(ComparatorContext.class,0);
		}
		public ComparatorExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanExpressionListener ) ((BooleanExpressionListener)listener).enterComparatorExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanExpressionListener ) ((BooleanExpressionListener)listener).exitComparatorExpression(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParentExpressionContext extends ExpressionContext {
		public TerminalNode LPAREN() { return getToken(BooleanExpressionParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(BooleanExpressionParser.RPAREN, 0); }
		public ParentExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanExpressionListener ) ((BooleanExpressionListener)listener).enterParentExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanExpressionListener ) ((BooleanExpressionListener)listener).exitParentExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				_localctx = new ParentExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(22);
				match(LPAREN);
				setState(23);
				expression(0);
				setState(24);
				match(RPAREN);
				}
				break;
			case 2:
				{
				_localctx = new NotExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(26);
				match(NOT);
				setState(27);
				expression(7);
				}
				break;
			case 3:
				{
				_localctx = new TypesExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(28);
				types();
				}
				break;
			case 4:
				{
				_localctx = new ToExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(30);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WORD) {
					{
					setState(29);
					((ToExpressionContext)_localctx).field = match(WORD);
					}
				}

				setState(32);
				((ToExpressionContext)_localctx).lower = numericTypes();
				setState(33);
				match(TO);
				setState(34);
				((ToExpressionContext)_localctx).upper = numericTypes();
				}
				break;
			case 5:
				{
				_localctx = new InExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(37);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WORD) {
					{
					setState(36);
					((InExpressionContext)_localctx).field = match(WORD);
					}
				}

				setState(40);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(39);
					((InExpressionContext)_localctx).not = match(NOT);
					}
				}

				setState(42);
				match(IN);
				setState(43);
				((InExpressionContext)_localctx).data = wordlist();
				}
				break;
			case 6:
				{
				_localctx = new ArrayExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(45);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WORD) {
					{
					setState(44);
					((ArrayExpressionContext)_localctx).field = match(WORD);
					}
				}

				setState(47);
				((ArrayExpressionContext)_localctx).op = arrayOperators();
				setState(48);
				((ArrayExpressionContext)_localctx).data = wordlist();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(62);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(60);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
					case 1:
						{
						_localctx = new ComparatorExpressionContext(new ExpressionContext(_parentctx, _parentState));
						((ComparatorExpressionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(52);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(53);
						((ComparatorExpressionContext)_localctx).op = comparator();
						setState(54);
						((ComparatorExpressionContext)_localctx).right = expression(7);
						}
						break;
					case 2:
						{
						_localctx = new BinaryExpressionContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExpressionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(56);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(57);
						((BinaryExpressionContext)_localctx).op = binary();
						setState(58);
						((BinaryExpressionContext)_localctx).right = expression(6);
						}
						break;
					}
					} 
				}
				setState(64);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ComparatorContext extends ParserRuleContext {
		public TerminalNode GT() { return getToken(BooleanExpressionParser.GT, 0); }
		public TerminalNode GE() { return getToken(BooleanExpressionParser.GE, 0); }
		public TerminalNode LT() { return getToken(BooleanExpressionParser.LT, 0); }
		public TerminalNode LE() { return getToken(BooleanExpressionParser.LE, 0); }
		public TerminalNode EQ() { return getToken(BooleanExpressionParser.EQ, 0); }
		public TerminalNode NE() { return getToken(BooleanExpressionParser.NE, 0); }
		public ComparatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanExpressionListener ) ((BooleanExpressionListener)listener).enterComparator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanExpressionListener ) ((BooleanExpressionListener)listener).exitComparator(this);
		}
	}

	public final ComparatorContext comparator() throws RecognitionException {
		ComparatorContext _localctx = new ComparatorContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_comparator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 129024L) != 0)) ) {
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

	@SuppressWarnings("CheckReturnValue")
	public static class WordlistContext extends ParserRuleContext {
		public TypesContext first;
		public TypesContext rest;
		public TerminalNode LPAREN() { return getToken(BooleanExpressionParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(BooleanExpressionParser.RPAREN, 0); }
		public List<TypesContext> types() {
			return getRuleContexts(TypesContext.class);
		}
		public TypesContext types(int i) {
			return getRuleContext(TypesContext.class,i);
		}
		public List<TerminalNode> WS() { return getTokens(BooleanExpressionParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(BooleanExpressionParser.WS, i);
		}
		public WordlistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_wordlist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanExpressionListener ) ((BooleanExpressionListener)listener).enterWordlist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanExpressionListener ) ((BooleanExpressionListener)listener).exitWordlist(this);
		}
	}

	public final WordlistContext wordlist() throws RecognitionException {
		WordlistContext _localctx = new WordlistContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_wordlist);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			match(LPAREN);
			setState(71);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(68);
					match(WS);
					}
					} 
				}
				setState(73);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			setState(74);
			((WordlistContext)_localctx).first = types();
			setState(78);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WS) {
				{
				{
				setState(75);
				match(WS);
				}
				}
				setState(80);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(81);
				match(T__0);
				setState(85);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
				while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(82);
						match(WS);
						}
						} 
					}
					setState(87);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
				}
				setState(88);
				((WordlistContext)_localctx).rest = types();
				setState(92);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==WS) {
					{
					{
					setState(89);
					match(WS);
					}
					}
					setState(94);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(99);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(100);
			match(RPAREN);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ArrayOperatorsContext extends ParserRuleContext {
		public TerminalNode CONTAINS_ANY() { return getToken(BooleanExpressionParser.CONTAINS_ANY, 0); }
		public TerminalNode CONTAINS_ALL() { return getToken(BooleanExpressionParser.CONTAINS_ALL, 0); }
		public ArrayOperatorsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayOperators; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanExpressionListener ) ((BooleanExpressionListener)listener).enterArrayOperators(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanExpressionListener ) ((BooleanExpressionListener)listener).exitArrayOperators(this);
		}
	}

	public final ArrayOperatorsContext arrayOperators() throws RecognitionException {
		ArrayOperatorsContext _localctx = new ArrayOperatorsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_arrayOperators);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			_la = _input.LA(1);
			if ( !(_la==CONTAINS_ALL || _la==CONTAINS_ANY) ) {
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

	@SuppressWarnings("CheckReturnValue")
	public static class NumericTypesContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(BooleanExpressionParser.INTEGER, 0); }
		public TerminalNode DECIMAL() { return getToken(BooleanExpressionParser.DECIMAL, 0); }
		public NumericTypesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numericTypes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanExpressionListener ) ((BooleanExpressionListener)listener).enterNumericTypes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanExpressionListener ) ((BooleanExpressionListener)listener).exitNumericTypes(this);
		}
	}

	public final NumericTypesContext numericTypes() throws RecognitionException {
		NumericTypesContext _localctx = new NumericTypesContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_numericTypes);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			_la = _input.LA(1);
			if ( !(_la==DECIMAL || _la==INTEGER) ) {
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

	@SuppressWarnings("CheckReturnValue")
	public static class TypesContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(BooleanExpressionParser.INTEGER, 0); }
		public TerminalNode DECIMAL() { return getToken(BooleanExpressionParser.DECIMAL, 0); }
		public TerminalNode APP_VERSION() { return getToken(BooleanExpressionParser.APP_VERSION, 0); }
		public BoolContext bool() {
			return getRuleContext(BoolContext.class,0);
		}
		public TerminalNode WORD() { return getToken(BooleanExpressionParser.WORD, 0); }
		public TypesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_types; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanExpressionListener ) ((BooleanExpressionListener)listener).enterTypes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanExpressionListener ) ((BooleanExpressionListener)listener).exitTypes(this);
		}
	}

	public final TypesContext types() throws RecognitionException {
		TypesContext _localctx = new TypesContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_types);
		try {
			setState(112);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(106);
				match(INTEGER);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(107);
				match(DECIMAL);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(108);
				match(APP_VERSION);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(109);
				bool();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(110);
				match(WORD);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
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

	@SuppressWarnings("CheckReturnValue")
	public static class BinaryContext extends ParserRuleContext {
		public TerminalNode AND() { return getToken(BooleanExpressionParser.AND, 0); }
		public TerminalNode OR() { return getToken(BooleanExpressionParser.OR, 0); }
		public BinaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanExpressionListener ) ((BooleanExpressionListener)listener).enterBinary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanExpressionListener ) ((BooleanExpressionListener)listener).exitBinary(this);
		}
	}

	public final BinaryContext binary() throws RecognitionException {
		BinaryContext _localctx = new BinaryContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_binary);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			_la = _input.LA(1);
			if ( !(_la==AND || _la==OR) ) {
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

	@SuppressWarnings("CheckReturnValue")
	public static class BoolContext extends ParserRuleContext {
		public TerminalNode TRUE() { return getToken(BooleanExpressionParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(BooleanExpressionParser.FALSE, 0); }
		public BoolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bool; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanExpressionListener ) ((BooleanExpressionListener)listener).enterBool(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanExpressionListener ) ((BooleanExpressionListener)listener).exitBool(this);
		}
	}

	public final BoolContext bool() throws RecognitionException {
		BoolContext _localctx = new BoolContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_bool);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			_la = _input.LA(1);
			if ( !(_la==TRUE || _la==FALSE) ) {
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
		case 1:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 6);
		case 1:
			return precpred(_ctx, 5);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u001aw\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0003\u0001\u001f\b\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0003\u0001&\b\u0001\u0001\u0001\u0003\u0001"+
		")\b\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001.\b\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0003\u00013\b\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0005\u0001=\b\u0001\n\u0001\f\u0001@\t\u0001\u0001\u0002\u0001"+
		"\u0002\u0001\u0003\u0001\u0003\u0005\u0003F\b\u0003\n\u0003\f\u0003I\t"+
		"\u0003\u0001\u0003\u0001\u0003\u0005\u0003M\b\u0003\n\u0003\f\u0003P\t"+
		"\u0003\u0001\u0003\u0001\u0003\u0005\u0003T\b\u0003\n\u0003\f\u0003W\t"+
		"\u0003\u0001\u0003\u0001\u0003\u0005\u0003[\b\u0003\n\u0003\f\u0003^\t"+
		"\u0003\u0005\u0003`\b\u0003\n\u0003\f\u0003c\t\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006q\b"+
		"\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0000\u0001\u0002"+
		"\t\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0000\u0005\u0001\u0000\u000b"+
		"\u0010\u0001\u0000\t\n\u0002\u0000\u0013\u0013\u0015\u0015\u0001\u0000"+
		"\u0004\u0005\u0001\u0000\u0007\b\u0082\u0000\u0012\u0001\u0000\u0000\u0000"+
		"\u00022\u0001\u0000\u0000\u0000\u0004A\u0001\u0000\u0000\u0000\u0006C"+
		"\u0001\u0000\u0000\u0000\bf\u0001\u0000\u0000\u0000\nh\u0001\u0000\u0000"+
		"\u0000\fp\u0001\u0000\u0000\u0000\u000er\u0001\u0000\u0000\u0000\u0010"+
		"t\u0001\u0000\u0000\u0000\u0012\u0013\u0003\u0002\u0001\u0000\u0013\u0014"+
		"\u0005\u0000\u0000\u0001\u0014\u0001\u0001\u0000\u0000\u0000\u0015\u0016"+
		"\u0006\u0001\uffff\uffff\u0000\u0016\u0017\u0005\u0011\u0000\u0000\u0017"+
		"\u0018\u0003\u0002\u0001\u0000\u0018\u0019\u0005\u0012\u0000\u0000\u0019"+
		"3\u0001\u0000\u0000\u0000\u001a\u001b\u0005\u0006\u0000\u0000\u001b3\u0003"+
		"\u0002\u0001\u0007\u001c3\u0003\f\u0006\u0000\u001d\u001f\u0005\u0017"+
		"\u0000\u0000\u001e\u001d\u0001\u0000\u0000\u0000\u001e\u001f\u0001\u0000"+
		"\u0000\u0000\u001f \u0001\u0000\u0000\u0000 !\u0003\n\u0005\u0000!\"\u0005"+
		"\u0003\u0000\u0000\"#\u0003\n\u0005\u0000#3\u0001\u0000\u0000\u0000$&"+
		"\u0005\u0017\u0000\u0000%$\u0001\u0000\u0000\u0000%&\u0001\u0000\u0000"+
		"\u0000&(\u0001\u0000\u0000\u0000\')\u0005\u0006\u0000\u0000(\'\u0001\u0000"+
		"\u0000\u0000()\u0001\u0000\u0000\u0000)*\u0001\u0000\u0000\u0000*+\u0005"+
		"\u0002\u0000\u0000+3\u0003\u0006\u0003\u0000,.\u0005\u0017\u0000\u0000"+
		"-,\u0001\u0000\u0000\u0000-.\u0001\u0000\u0000\u0000./\u0001\u0000\u0000"+
		"\u0000/0\u0003\b\u0004\u000001\u0003\u0006\u0003\u000013\u0001\u0000\u0000"+
		"\u00002\u0015\u0001\u0000\u0000\u00002\u001a\u0001\u0000\u0000\u00002"+
		"\u001c\u0001\u0000\u0000\u00002\u001e\u0001\u0000\u0000\u00002%\u0001"+
		"\u0000\u0000\u00002-\u0001\u0000\u0000\u00003>\u0001\u0000\u0000\u0000"+
		"45\n\u0006\u0000\u000056\u0003\u0004\u0002\u000067\u0003\u0002\u0001\u0007"+
		"7=\u0001\u0000\u0000\u000089\n\u0005\u0000\u00009:\u0003\u000e\u0007\u0000"+
		":;\u0003\u0002\u0001\u0006;=\u0001\u0000\u0000\u0000<4\u0001\u0000\u0000"+
		"\u0000<8\u0001\u0000\u0000\u0000=@\u0001\u0000\u0000\u0000><\u0001\u0000"+
		"\u0000\u0000>?\u0001\u0000\u0000\u0000?\u0003\u0001\u0000\u0000\u0000"+
		"@>\u0001\u0000\u0000\u0000AB\u0007\u0000\u0000\u0000B\u0005\u0001\u0000"+
		"\u0000\u0000CG\u0005\u0011\u0000\u0000DF\u0005\u0016\u0000\u0000ED\u0001"+
		"\u0000\u0000\u0000FI\u0001\u0000\u0000\u0000GE\u0001\u0000\u0000\u0000"+
		"GH\u0001\u0000\u0000\u0000HJ\u0001\u0000\u0000\u0000IG\u0001\u0000\u0000"+
		"\u0000JN\u0003\f\u0006\u0000KM\u0005\u0016\u0000\u0000LK\u0001\u0000\u0000"+
		"\u0000MP\u0001\u0000\u0000\u0000NL\u0001\u0000\u0000\u0000NO\u0001\u0000"+
		"\u0000\u0000Oa\u0001\u0000\u0000\u0000PN\u0001\u0000\u0000\u0000QU\u0005"+
		"\u0001\u0000\u0000RT\u0005\u0016\u0000\u0000SR\u0001\u0000\u0000\u0000"+
		"TW\u0001\u0000\u0000\u0000US\u0001\u0000\u0000\u0000UV\u0001\u0000\u0000"+
		"\u0000VX\u0001\u0000\u0000\u0000WU\u0001\u0000\u0000\u0000X\\\u0003\f"+
		"\u0006\u0000Y[\u0005\u0016\u0000\u0000ZY\u0001\u0000\u0000\u0000[^\u0001"+
		"\u0000\u0000\u0000\\Z\u0001\u0000\u0000\u0000\\]\u0001\u0000\u0000\u0000"+
		"]`\u0001\u0000\u0000\u0000^\\\u0001\u0000\u0000\u0000_Q\u0001\u0000\u0000"+
		"\u0000`c\u0001\u0000\u0000\u0000a_\u0001\u0000\u0000\u0000ab\u0001\u0000"+
		"\u0000\u0000bd\u0001\u0000\u0000\u0000ca\u0001\u0000\u0000\u0000de\u0005"+
		"\u0012\u0000\u0000e\u0007\u0001\u0000\u0000\u0000fg\u0007\u0001\u0000"+
		"\u0000g\t\u0001\u0000\u0000\u0000hi\u0007\u0002\u0000\u0000i\u000b\u0001"+
		"\u0000\u0000\u0000jq\u0005\u0015\u0000\u0000kq\u0005\u0013\u0000\u0000"+
		"lq\u0005\u0014\u0000\u0000mq\u0003\u0010\b\u0000nq\u0005\u0017\u0000\u0000"+
		"oq\u0001\u0000\u0000\u0000pj\u0001\u0000\u0000\u0000pk\u0001\u0000\u0000"+
		"\u0000pl\u0001\u0000\u0000\u0000pm\u0001\u0000\u0000\u0000pn\u0001\u0000"+
		"\u0000\u0000po\u0001\u0000\u0000\u0000q\r\u0001\u0000\u0000\u0000rs\u0007"+
		"\u0003\u0000\u0000s\u000f\u0001\u0000\u0000\u0000tu\u0007\u0004\u0000"+
		"\u0000u\u0011\u0001\u0000\u0000\u0000\r\u001e%(-2<>GNU\\ap";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}