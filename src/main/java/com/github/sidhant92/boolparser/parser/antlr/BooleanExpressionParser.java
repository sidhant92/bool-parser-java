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
		CONTAINS_ANY=10, MIN=11, MAX=12, AVG=13, SUM=14, MEAN=15, MODE=16, MEDIAN=17, 
		LEN=18, INT=19, ADD=20, SUBTRACT=21, MULTIPLY=22, DIVIDE=23, MODULUS=24, 
		EXPONENT=25, NE=26, GT=27, GE=28, LT=29, LE=30, EQ=31, LPAREN=32, RPAREN=33, 
		DECIMAL=34, APP_VERSION=35, INTEGER=36, WS=37, WORD=38, ALPHANUMERIC=39, 
		SQ=40, DQ=41;
	public static final int
		RULE_parse = 0, RULE_expression = 1, RULE_comparator = 2, RULE_arithmeticOperator = 3, 
		RULE_arithmeticFunction = 4, RULE_wordlist = 5, RULE_arrayOperators = 6, 
		RULE_numericTypes = 7, RULE_types = 8, RULE_binary = 9, RULE_bool = 10;
	private static String[] makeRuleNames() {
		return new String[] {
			"parse", "expression", "comparator", "arithmeticOperator", "arithmeticFunction", 
			"wordlist", "arrayOperators", "numericTypes", "types", "binary", "bool"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "','", null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, "'+'", "'-'", "'*'", 
			"'/'", "'%'", "'^'", "'!='", "'>'", "'>='", "'<'", "'<='", "'='", "'('", 
			"')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, "IN", "TO", "AND", "OR", "NOT", "TRUE", "FALSE", "CONTAINS_ALL", 
			"CONTAINS_ANY", "MIN", "MAX", "AVG", "SUM", "MEAN", "MODE", "MEDIAN", 
			"LEN", "INT", "ADD", "SUBTRACT", "MULTIPLY", "DIVIDE", "MODULUS", "EXPONENT", 
			"NE", "GT", "GE", "LT", "LE", "EQ", "LPAREN", "RPAREN", "DECIMAL", "APP_VERSION", 
			"INTEGER", "WS", "WORD", "ALPHANUMERIC", "SQ", "DQ"
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
			setState(22);
			expression(0);
			setState(23);
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
	public static class UnaryArithmeticExpressionContext extends ExpressionContext {
		public Token op;
		public ExpressionContext exp;
		public TerminalNode SUBTRACT() { return getToken(BooleanExpressionParser.SUBTRACT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public UnaryArithmeticExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanExpressionListener ) ((BooleanExpressionListener)listener).enterUnaryArithmeticExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanExpressionListener ) ((BooleanExpressionListener)listener).exitUnaryArithmeticExpression(this);
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
	public static class ArithmeticFunctionExpressionContext extends ExpressionContext {
		public ArithmeticFunctionContext left;
		public WordlistContext data;
		public ArithmeticFunctionContext arithmeticFunction() {
			return getRuleContext(ArithmeticFunctionContext.class,0);
		}
		public WordlistContext wordlist() {
			return getRuleContext(WordlistContext.class,0);
		}
		public ArithmeticFunctionExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanExpressionListener ) ((BooleanExpressionListener)listener).enterArithmeticFunctionExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanExpressionListener ) ((BooleanExpressionListener)listener).exitArithmeticFunctionExpression(this);
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
	public static class ArithmeticExpressionContext extends ExpressionContext {
		public ExpressionContext left;
		public Token op;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode EXPONENT() { return getToken(BooleanExpressionParser.EXPONENT, 0); }
		public TerminalNode DIVIDE() { return getToken(BooleanExpressionParser.DIVIDE, 0); }
		public TerminalNode MULTIPLY() { return getToken(BooleanExpressionParser.MULTIPLY, 0); }
		public TerminalNode MODULUS() { return getToken(BooleanExpressionParser.MODULUS, 0); }
		public TerminalNode ADD() { return getToken(BooleanExpressionParser.ADD, 0); }
		public TerminalNode SUBTRACT() { return getToken(BooleanExpressionParser.SUBTRACT, 0); }
		public ArithmeticExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanExpressionListener ) ((BooleanExpressionListener)listener).enterArithmeticExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanExpressionListener ) ((BooleanExpressionListener)listener).exitArithmeticExpression(this);
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
			setState(59);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				_localctx = new ParentExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(26);
				match(LPAREN);
				setState(27);
				expression(0);
				setState(28);
				match(RPAREN);
				}
				break;
			case 2:
				{
				_localctx = new NotExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(30);
				match(NOT);
				setState(31);
				expression(15);
				}
				break;
			case 3:
				{
				_localctx = new UnaryArithmeticExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(32);
				((UnaryArithmeticExpressionContext)_localctx).op = match(SUBTRACT);
				setState(33);
				((UnaryArithmeticExpressionContext)_localctx).exp = expression(13);
				}
				break;
			case 4:
				{
				_localctx = new ArithmeticFunctionExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(34);
				((ArithmeticFunctionExpressionContext)_localctx).left = arithmeticFunction();
				setState(35);
				((ArithmeticFunctionExpressionContext)_localctx).data = wordlist();
				}
				break;
			case 5:
				{
				_localctx = new TypesExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(37);
				types();
				}
				break;
			case 6:
				{
				_localctx = new ToExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(39);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WORD) {
					{
					setState(38);
					((ToExpressionContext)_localctx).field = match(WORD);
					}
				}

				setState(41);
				((ToExpressionContext)_localctx).lower = numericTypes();
				setState(42);
				match(TO);
				setState(43);
				((ToExpressionContext)_localctx).upper = numericTypes();
				}
				break;
			case 7:
				{
				_localctx = new InExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(46);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WORD) {
					{
					setState(45);
					((InExpressionContext)_localctx).field = match(WORD);
					}
				}

				setState(49);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(48);
					((InExpressionContext)_localctx).not = match(NOT);
					}
				}

				setState(51);
				match(IN);
				setState(52);
				((InExpressionContext)_localctx).data = wordlist();
				}
				break;
			case 8:
				{
				_localctx = new ArrayExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(54);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WORD) {
					{
					setState(53);
					((ArrayExpressionContext)_localctx).field = match(WORD);
					}
				}

				setState(56);
				((ArrayExpressionContext)_localctx).op = arrayOperators();
				setState(57);
				((ArrayExpressionContext)_localctx).data = wordlist();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(89);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(87);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
					case 1:
						{
						_localctx = new ComparatorExpressionContext(new ExpressionContext(_parentctx, _parentState));
						((ComparatorExpressionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(61);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(62);
						((ComparatorExpressionContext)_localctx).op = comparator();
						setState(63);
						((ComparatorExpressionContext)_localctx).right = expression(15);
						}
						break;
					case 2:
						{
						_localctx = new ArithmeticExpressionContext(new ExpressionContext(_parentctx, _parentState));
						((ArithmeticExpressionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(65);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(66);
						((ArithmeticExpressionContext)_localctx).op = match(EXPONENT);
						setState(67);
						((ArithmeticExpressionContext)_localctx).right = expression(13);
						}
						break;
					case 3:
						{
						_localctx = new ArithmeticExpressionContext(new ExpressionContext(_parentctx, _parentState));
						((ArithmeticExpressionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(68);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(69);
						((ArithmeticExpressionContext)_localctx).op = match(DIVIDE);
						setState(70);
						((ArithmeticExpressionContext)_localctx).right = expression(12);
						}
						break;
					case 4:
						{
						_localctx = new ArithmeticExpressionContext(new ExpressionContext(_parentctx, _parentState));
						((ArithmeticExpressionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(71);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(72);
						((ArithmeticExpressionContext)_localctx).op = match(MULTIPLY);
						setState(73);
						((ArithmeticExpressionContext)_localctx).right = expression(11);
						}
						break;
					case 5:
						{
						_localctx = new ArithmeticExpressionContext(new ExpressionContext(_parentctx, _parentState));
						((ArithmeticExpressionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(74);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(75);
						((ArithmeticExpressionContext)_localctx).op = match(MODULUS);
						setState(76);
						((ArithmeticExpressionContext)_localctx).right = expression(10);
						}
						break;
					case 6:
						{
						_localctx = new ArithmeticExpressionContext(new ExpressionContext(_parentctx, _parentState));
						((ArithmeticExpressionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(77);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(78);
						((ArithmeticExpressionContext)_localctx).op = match(ADD);
						setState(79);
						((ArithmeticExpressionContext)_localctx).right = expression(9);
						}
						break;
					case 7:
						{
						_localctx = new ArithmeticExpressionContext(new ExpressionContext(_parentctx, _parentState));
						((ArithmeticExpressionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(80);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(81);
						((ArithmeticExpressionContext)_localctx).op = match(SUBTRACT);
						setState(82);
						((ArithmeticExpressionContext)_localctx).right = expression(8);
						}
						break;
					case 8:
						{
						_localctx = new BinaryExpressionContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExpressionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(83);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(84);
						((BinaryExpressionContext)_localctx).op = binary();
						setState(85);
						((BinaryExpressionContext)_localctx).right = expression(6);
						}
						break;
					}
					} 
				}
				setState(91);
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
			setState(92);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 4227858432L) != 0)) ) {
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
	public static class ArithmeticOperatorContext extends ParserRuleContext {
		public TerminalNode MULTIPLY() { return getToken(BooleanExpressionParser.MULTIPLY, 0); }
		public TerminalNode DIVIDE() { return getToken(BooleanExpressionParser.DIVIDE, 0); }
		public TerminalNode ADD() { return getToken(BooleanExpressionParser.ADD, 0); }
		public TerminalNode SUBTRACT() { return getToken(BooleanExpressionParser.SUBTRACT, 0); }
		public TerminalNode MODULUS() { return getToken(BooleanExpressionParser.MODULUS, 0); }
		public TerminalNode EXPONENT() { return getToken(BooleanExpressionParser.EXPONENT, 0); }
		public ArithmeticOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmeticOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanExpressionListener ) ((BooleanExpressionListener)listener).enterArithmeticOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanExpressionListener ) ((BooleanExpressionListener)listener).exitArithmeticOperator(this);
		}
	}

	public final ArithmeticOperatorContext arithmeticOperator() throws RecognitionException {
		ArithmeticOperatorContext _localctx = new ArithmeticOperatorContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_arithmeticOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 66060288L) != 0)) ) {
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
	public static class ArithmeticFunctionContext extends ParserRuleContext {
		public TerminalNode MIN() { return getToken(BooleanExpressionParser.MIN, 0); }
		public TerminalNode MAX() { return getToken(BooleanExpressionParser.MAX, 0); }
		public TerminalNode SUM() { return getToken(BooleanExpressionParser.SUM, 0); }
		public TerminalNode AVG() { return getToken(BooleanExpressionParser.AVG, 0); }
		public TerminalNode MEAN() { return getToken(BooleanExpressionParser.MEAN, 0); }
		public TerminalNode MODE() { return getToken(BooleanExpressionParser.MODE, 0); }
		public TerminalNode LEN() { return getToken(BooleanExpressionParser.LEN, 0); }
		public TerminalNode MEDIAN() { return getToken(BooleanExpressionParser.MEDIAN, 0); }
		public TerminalNode INT() { return getToken(BooleanExpressionParser.INT, 0); }
		public ArithmeticFunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmeticFunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanExpressionListener ) ((BooleanExpressionListener)listener).enterArithmeticFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanExpressionListener ) ((BooleanExpressionListener)listener).exitArithmeticFunction(this);
		}
	}

	public final ArithmeticFunctionContext arithmeticFunction() throws RecognitionException {
		ArithmeticFunctionContext _localctx = new ArithmeticFunctionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_arithmeticFunction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1046528L) != 0)) ) {
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
		public ExpressionContext first;
		public ExpressionContext rest;
		public TerminalNode LPAREN() { return getToken(BooleanExpressionParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(BooleanExpressionParser.RPAREN, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
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
		enterRule(_localctx, 10, RULE_wordlist);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			match(LPAREN);
			setState(102);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(99);
					match(WS);
					}
					} 
				}
				setState(104);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			setState(105);
			((WordlistContext)_localctx).first = expression(0);
			setState(109);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WS) {
				{
				{
				setState(106);
				match(WS);
				}
				}
				setState(111);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(128);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(112);
				match(T__0);
				setState(116);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
				while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(113);
						match(WS);
						}
						} 
					}
					setState(118);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
				}
				setState(119);
				((WordlistContext)_localctx).rest = expression(0);
				setState(123);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==WS) {
					{
					{
					setState(120);
					match(WS);
					}
					}
					setState(125);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(130);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(131);
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
		enterRule(_localctx, 12, RULE_arrayOperators);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
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
		enterRule(_localctx, 14, RULE_numericTypes);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
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
		enterRule(_localctx, 16, RULE_types);
		try {
			setState(143);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(137);
				match(INTEGER);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(138);
				match(DECIMAL);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(139);
				match(APP_VERSION);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(140);
				bool();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(141);
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
		enterRule(_localctx, 18, RULE_binary);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
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
		enterRule(_localctx, 20, RULE_bool);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
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
			return precpred(_ctx, 14);
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
		case 6:
			return precpred(_ctx, 7);
		case 7:
			return precpred(_ctx, 5);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001)\u0096\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0003\u0001(\b\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001/\b\u0001\u0001"+
		"\u0001\u0003\u00012\b\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003"+
		"\u00017\b\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001<\b\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0005\u0001X\b\u0001\n\u0001\f\u0001[\t\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0005\u0001\u0005\u0005\u0005e\b\u0005\n\u0005\f\u0005h\t\u0005"+
		"\u0001\u0005\u0001\u0005\u0005\u0005l\b\u0005\n\u0005\f\u0005o\t\u0005"+
		"\u0001\u0005\u0001\u0005\u0005\u0005s\b\u0005\n\u0005\f\u0005v\t\u0005"+
		"\u0001\u0005\u0001\u0005\u0005\u0005z\b\u0005\n\u0005\f\u0005}\t\u0005"+
		"\u0005\u0005\u007f\b\u0005\n\u0005\f\u0005\u0082\t\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001\b"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0003\b\u0090\b\b\u0001\t\u0001\t\u0001"+
		"\n\u0001\n\u0001\n\u0000\u0001\u0002\u000b\u0000\u0002\u0004\u0006\b\n"+
		"\f\u000e\u0010\u0012\u0014\u0000\u0007\u0001\u0000\u001a\u001f\u0001\u0000"+
		"\u0014\u0019\u0001\u0000\u000b\u0013\u0001\u0000\t\n\u0002\u0000\"\"$"+
		"$\u0001\u0000\u0004\u0005\u0001\u0000\u0007\b\u00a7\u0000\u0016\u0001"+
		"\u0000\u0000\u0000\u0002;\u0001\u0000\u0000\u0000\u0004\\\u0001\u0000"+
		"\u0000\u0000\u0006^\u0001\u0000\u0000\u0000\b`\u0001\u0000\u0000\u0000"+
		"\nb\u0001\u0000\u0000\u0000\f\u0085\u0001\u0000\u0000\u0000\u000e\u0087"+
		"\u0001\u0000\u0000\u0000\u0010\u008f\u0001\u0000\u0000\u0000\u0012\u0091"+
		"\u0001\u0000\u0000\u0000\u0014\u0093\u0001\u0000\u0000\u0000\u0016\u0017"+
		"\u0003\u0002\u0001\u0000\u0017\u0018\u0005\u0000\u0000\u0001\u0018\u0001"+
		"\u0001\u0000\u0000\u0000\u0019\u001a\u0006\u0001\uffff\uffff\u0000\u001a"+
		"\u001b\u0005 \u0000\u0000\u001b\u001c\u0003\u0002\u0001\u0000\u001c\u001d"+
		"\u0005!\u0000\u0000\u001d<\u0001\u0000\u0000\u0000\u001e\u001f\u0005\u0006"+
		"\u0000\u0000\u001f<\u0003\u0002\u0001\u000f !\u0005\u0015\u0000\u0000"+
		"!<\u0003\u0002\u0001\r\"#\u0003\b\u0004\u0000#$\u0003\n\u0005\u0000$<"+
		"\u0001\u0000\u0000\u0000%<\u0003\u0010\b\u0000&(\u0005&\u0000\u0000\'"+
		"&\u0001\u0000\u0000\u0000\'(\u0001\u0000\u0000\u0000()\u0001\u0000\u0000"+
		"\u0000)*\u0003\u000e\u0007\u0000*+\u0005\u0003\u0000\u0000+,\u0003\u000e"+
		"\u0007\u0000,<\u0001\u0000\u0000\u0000-/\u0005&\u0000\u0000.-\u0001\u0000"+
		"\u0000\u0000./\u0001\u0000\u0000\u0000/1\u0001\u0000\u0000\u000002\u0005"+
		"\u0006\u0000\u000010\u0001\u0000\u0000\u000012\u0001\u0000\u0000\u0000"+
		"23\u0001\u0000\u0000\u000034\u0005\u0002\u0000\u00004<\u0003\n\u0005\u0000"+
		"57\u0005&\u0000\u000065\u0001\u0000\u0000\u000067\u0001\u0000\u0000\u0000"+
		"78\u0001\u0000\u0000\u000089\u0003\f\u0006\u00009:\u0003\n\u0005\u0000"+
		":<\u0001\u0000\u0000\u0000;\u0019\u0001\u0000\u0000\u0000;\u001e\u0001"+
		"\u0000\u0000\u0000; \u0001\u0000\u0000\u0000;\"\u0001\u0000\u0000\u0000"+
		";%\u0001\u0000\u0000\u0000;\'\u0001\u0000\u0000\u0000;.\u0001\u0000\u0000"+
		"\u0000;6\u0001\u0000\u0000\u0000<Y\u0001\u0000\u0000\u0000=>\n\u000e\u0000"+
		"\u0000>?\u0003\u0004\u0002\u0000?@\u0003\u0002\u0001\u000f@X\u0001\u0000"+
		"\u0000\u0000AB\n\f\u0000\u0000BC\u0005\u0019\u0000\u0000CX\u0003\u0002"+
		"\u0001\rDE\n\u000b\u0000\u0000EF\u0005\u0017\u0000\u0000FX\u0003\u0002"+
		"\u0001\fGH\n\n\u0000\u0000HI\u0005\u0016\u0000\u0000IX\u0003\u0002\u0001"+
		"\u000bJK\n\t\u0000\u0000KL\u0005\u0018\u0000\u0000LX\u0003\u0002\u0001"+
		"\nMN\n\b\u0000\u0000NO\u0005\u0014\u0000\u0000OX\u0003\u0002\u0001\tP"+
		"Q\n\u0007\u0000\u0000QR\u0005\u0015\u0000\u0000RX\u0003\u0002\u0001\b"+
		"ST\n\u0005\u0000\u0000TU\u0003\u0012\t\u0000UV\u0003\u0002\u0001\u0006"+
		"VX\u0001\u0000\u0000\u0000W=\u0001\u0000\u0000\u0000WA\u0001\u0000\u0000"+
		"\u0000WD\u0001\u0000\u0000\u0000WG\u0001\u0000\u0000\u0000WJ\u0001\u0000"+
		"\u0000\u0000WM\u0001\u0000\u0000\u0000WP\u0001\u0000\u0000\u0000WS\u0001"+
		"\u0000\u0000\u0000X[\u0001\u0000\u0000\u0000YW\u0001\u0000\u0000\u0000"+
		"YZ\u0001\u0000\u0000\u0000Z\u0003\u0001\u0000\u0000\u0000[Y\u0001\u0000"+
		"\u0000\u0000\\]\u0007\u0000\u0000\u0000]\u0005\u0001\u0000\u0000\u0000"+
		"^_\u0007\u0001\u0000\u0000_\u0007\u0001\u0000\u0000\u0000`a\u0007\u0002"+
		"\u0000\u0000a\t\u0001\u0000\u0000\u0000bf\u0005 \u0000\u0000ce\u0005%"+
		"\u0000\u0000dc\u0001\u0000\u0000\u0000eh\u0001\u0000\u0000\u0000fd\u0001"+
		"\u0000\u0000\u0000fg\u0001\u0000\u0000\u0000gi\u0001\u0000\u0000\u0000"+
		"hf\u0001\u0000\u0000\u0000im\u0003\u0002\u0001\u0000jl\u0005%\u0000\u0000"+
		"kj\u0001\u0000\u0000\u0000lo\u0001\u0000\u0000\u0000mk\u0001\u0000\u0000"+
		"\u0000mn\u0001\u0000\u0000\u0000n\u0080\u0001\u0000\u0000\u0000om\u0001"+
		"\u0000\u0000\u0000pt\u0005\u0001\u0000\u0000qs\u0005%\u0000\u0000rq\u0001"+
		"\u0000\u0000\u0000sv\u0001\u0000\u0000\u0000tr\u0001\u0000\u0000\u0000"+
		"tu\u0001\u0000\u0000\u0000uw\u0001\u0000\u0000\u0000vt\u0001\u0000\u0000"+
		"\u0000w{\u0003\u0002\u0001\u0000xz\u0005%\u0000\u0000yx\u0001\u0000\u0000"+
		"\u0000z}\u0001\u0000\u0000\u0000{y\u0001\u0000\u0000\u0000{|\u0001\u0000"+
		"\u0000\u0000|\u007f\u0001\u0000\u0000\u0000}{\u0001\u0000\u0000\u0000"+
		"~p\u0001\u0000\u0000\u0000\u007f\u0082\u0001\u0000\u0000\u0000\u0080~"+
		"\u0001\u0000\u0000\u0000\u0080\u0081\u0001\u0000\u0000\u0000\u0081\u0083"+
		"\u0001\u0000\u0000\u0000\u0082\u0080\u0001\u0000\u0000\u0000\u0083\u0084"+
		"\u0005!\u0000\u0000\u0084\u000b\u0001\u0000\u0000\u0000\u0085\u0086\u0007"+
		"\u0003\u0000\u0000\u0086\r\u0001\u0000\u0000\u0000\u0087\u0088\u0007\u0004"+
		"\u0000\u0000\u0088\u000f\u0001\u0000\u0000\u0000\u0089\u0090\u0005$\u0000"+
		"\u0000\u008a\u0090\u0005\"\u0000\u0000\u008b\u0090\u0005#\u0000\u0000"+
		"\u008c\u0090\u0003\u0014\n\u0000\u008d\u0090\u0005&\u0000\u0000\u008e"+
		"\u0090\u0001\u0000\u0000\u0000\u008f\u0089\u0001\u0000\u0000\u0000\u008f"+
		"\u008a\u0001\u0000\u0000\u0000\u008f\u008b\u0001\u0000\u0000\u0000\u008f"+
		"\u008c\u0001\u0000\u0000\u0000\u008f\u008d\u0001\u0000\u0000\u0000\u008f"+
		"\u008e\u0001\u0000\u0000\u0000\u0090\u0011\u0001\u0000\u0000\u0000\u0091"+
		"\u0092\u0007\u0005\u0000\u0000\u0092\u0013\u0001\u0000\u0000\u0000\u0093"+
		"\u0094\u0007\u0006\u0000\u0000\u0094\u0015\u0001\u0000\u0000\u0000\r\'"+
		".16;WYfmt{\u0080\u008f";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}