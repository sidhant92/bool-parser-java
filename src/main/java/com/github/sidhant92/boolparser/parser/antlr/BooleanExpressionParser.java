// Generated from /Users/sidhantaggarwal/bool-parser-java/src/main/java/resources/BooleanExpression.g4 by ANTLR 4.13.2
package com.github.sidhant92.boolparser.parser.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class BooleanExpressionParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, IN=2, TO=3, AND=4, OR=5, NOT=6, TRUE=7, FALSE=8, CONTAINS_ALL=9, 
		CONTAINS_ANY=10, MIN=11, MAX=12, AVG=13, SUM=14, MEAN=15, MODE=16, MEDIAN=17, 
		LEN=18, INT=19, DAYS_ELAPSED=20, ADD=21, SUBTRACT=22, MULTIPLY=23, DIVIDE=24, 
		MODULUS=25, EXPONENT=26, NE=27, GT=28, GE=29, LT=30, LE=31, EQ=32, LPAREN=33, 
		RPAREN=34, DECIMAL=35, APP_VERSION=36, INTEGER=37, DATE=38, DATETIME=39, 
		WS=40, WORD=41, SQSTR=42, DQSTR=43, FIELD=44, ALPHANUMERIC=45, SQ=46, 
		DQ=47;
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
			null, null, null, null, null, null, null, null, null, "'+'", "'-'", "'*'", 
			"'/'", "'%'", "'^'", "'!='", "'>'", "'>='", "'<'", "'<='", "'='", "'('", 
			"')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, "IN", "TO", "AND", "OR", "NOT", "TRUE", "FALSE", "CONTAINS_ALL", 
			"CONTAINS_ANY", "MIN", "MAX", "AVG", "SUM", "MEAN", "MODE", "MEDIAN", 
			"LEN", "INT", "DAYS_ELAPSED", "ADD", "SUBTRACT", "MULTIPLY", "DIVIDE", 
			"MODULUS", "EXPONENT", "NE", "GT", "GE", "LT", "LE", "EQ", "LPAREN", 
			"RPAREN", "DECIMAL", "APP_VERSION", "INTEGER", "DATE", "DATETIME", "WS", 
			"WORD", "SQSTR", "DQSTR", "FIELD", "ALPHANUMERIC", "SQ", "DQ"
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
		public TerminalNode FIELD() { return getToken(BooleanExpressionParser.FIELD, 0); }
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
		public TerminalNode FIELD() { return getToken(BooleanExpressionParser.FIELD, 0); }
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
		public TerminalNode FIELD() { return getToken(BooleanExpressionParser.FIELD, 0); }
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
			setState(53);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
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
				{
				setState(38);
				((ToExpressionContext)_localctx).field = match(FIELD);
				}
				setState(39);
				((ToExpressionContext)_localctx).lower = numericTypes();
				setState(40);
				match(TO);
				setState(41);
				((ToExpressionContext)_localctx).upper = numericTypes();
				}
				break;
			case 7:
				{
				_localctx = new InExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				{
				setState(43);
				((InExpressionContext)_localctx).field = match(FIELD);
				}
				setState(45);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(44);
					((InExpressionContext)_localctx).not = match(NOT);
					}
				}

				setState(47);
				match(IN);
				setState(48);
				((InExpressionContext)_localctx).data = wordlist();
				}
				break;
			case 8:
				{
				_localctx = new ArrayExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				{
				setState(49);
				((ArrayExpressionContext)_localctx).field = match(FIELD);
				}
				setState(50);
				((ArrayExpressionContext)_localctx).op = arrayOperators();
				setState(51);
				((ArrayExpressionContext)_localctx).data = wordlist();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(83);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(81);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
					case 1:
						{
						_localctx = new ComparatorExpressionContext(new ExpressionContext(_parentctx, _parentState));
						((ComparatorExpressionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(55);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(56);
						((ComparatorExpressionContext)_localctx).op = comparator();
						setState(57);
						((ComparatorExpressionContext)_localctx).right = expression(15);
						}
						break;
					case 2:
						{
						_localctx = new ArithmeticExpressionContext(new ExpressionContext(_parentctx, _parentState));
						((ArithmeticExpressionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(59);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(60);
						((ArithmeticExpressionContext)_localctx).op = match(EXPONENT);
						setState(61);
						((ArithmeticExpressionContext)_localctx).right = expression(13);
						}
						break;
					case 3:
						{
						_localctx = new ArithmeticExpressionContext(new ExpressionContext(_parentctx, _parentState));
						((ArithmeticExpressionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(62);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(63);
						((ArithmeticExpressionContext)_localctx).op = match(DIVIDE);
						setState(64);
						((ArithmeticExpressionContext)_localctx).right = expression(12);
						}
						break;
					case 4:
						{
						_localctx = new ArithmeticExpressionContext(new ExpressionContext(_parentctx, _parentState));
						((ArithmeticExpressionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(65);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(66);
						((ArithmeticExpressionContext)_localctx).op = match(MULTIPLY);
						setState(67);
						((ArithmeticExpressionContext)_localctx).right = expression(11);
						}
						break;
					case 5:
						{
						_localctx = new ArithmeticExpressionContext(new ExpressionContext(_parentctx, _parentState));
						((ArithmeticExpressionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(68);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(69);
						((ArithmeticExpressionContext)_localctx).op = match(MODULUS);
						setState(70);
						((ArithmeticExpressionContext)_localctx).right = expression(10);
						}
						break;
					case 6:
						{
						_localctx = new ArithmeticExpressionContext(new ExpressionContext(_parentctx, _parentState));
						((ArithmeticExpressionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(71);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(72);
						((ArithmeticExpressionContext)_localctx).op = match(ADD);
						setState(73);
						((ArithmeticExpressionContext)_localctx).right = expression(9);
						}
						break;
					case 7:
						{
						_localctx = new ArithmeticExpressionContext(new ExpressionContext(_parentctx, _parentState));
						((ArithmeticExpressionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(74);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(75);
						((ArithmeticExpressionContext)_localctx).op = match(SUBTRACT);
						setState(76);
						((ArithmeticExpressionContext)_localctx).right = expression(8);
						}
						break;
					case 8:
						{
						_localctx = new BinaryExpressionContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExpressionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(77);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(78);
						((BinaryExpressionContext)_localctx).op = binary();
						setState(79);
						((BinaryExpressionContext)_localctx).right = expression(6);
						}
						break;
					}
					} 
				}
				setState(85);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
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
			setState(86);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 8455716864L) != 0)) ) {
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
			setState(88);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 132120576L) != 0)) ) {
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
		public TerminalNode DAYS_ELAPSED() { return getToken(BooleanExpressionParser.DAYS_ELAPSED, 0); }
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
			setState(90);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 2095104L) != 0)) ) {
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
			setState(92);
			match(LPAREN);
			setState(96);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(93);
					match(WS);
					}
					} 
				}
				setState(98);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			setState(99);
			((WordlistContext)_localctx).first = expression(0);
			setState(103);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WS) {
				{
				{
				setState(100);
				match(WS);
				}
				}
				setState(105);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(122);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(106);
				match(T__0);
				setState(110);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
				while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(107);
						match(WS);
						}
						} 
					}
					setState(112);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
				}
				setState(113);
				((WordlistContext)_localctx).rest = expression(0);
				setState(117);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==WS) {
					{
					{
					setState(114);
					match(WS);
					}
					}
					setState(119);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(124);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(125);
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
			setState(127);
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
			setState(129);
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
		public TerminalNode FIELD() { return getToken(BooleanExpressionParser.FIELD, 0); }
		public TerminalNode DATE() { return getToken(BooleanExpressionParser.DATE, 0); }
		public TerminalNode DATETIME() { return getToken(BooleanExpressionParser.DATETIME, 0); }
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
			setState(140);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(131);
				match(INTEGER);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(132);
				match(DECIMAL);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(133);
				match(APP_VERSION);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(134);
				bool();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(135);
				match(WORD);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(136);
				match(FIELD);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(137);
				match(DATE);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(138);
				match(DATETIME);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
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
			setState(142);
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
			setState(144);
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
		"\u0004\u0001/\u0093\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0003\u0001.\b\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u00016\b"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0005\u0001R\b\u0001\n\u0001\f\u0001U\t"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001"+
		"\u0004\u0001\u0005\u0001\u0005\u0005\u0005_\b\u0005\n\u0005\f\u0005b\t"+
		"\u0005\u0001\u0005\u0001\u0005\u0005\u0005f\b\u0005\n\u0005\f\u0005i\t"+
		"\u0005\u0001\u0005\u0001\u0005\u0005\u0005m\b\u0005\n\u0005\f\u0005p\t"+
		"\u0005\u0001\u0005\u0001\u0005\u0005\u0005t\b\u0005\n\u0005\f\u0005w\t"+
		"\u0005\u0005\u0005y\b\u0005\n\u0005\f\u0005|\t\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001\b"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003\b\u008d"+
		"\b\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0000\u0001\u0002\u000b\u0000"+
		"\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0000\u0007\u0001\u0000"+
		"\u001b \u0001\u0000\u0015\u001a\u0001\u0000\u000b\u0014\u0001\u0000\t"+
		"\n\u0002\u0000##%%\u0001\u0000\u0004\u0005\u0001\u0000\u0007\b\u00a4\u0000"+
		"\u0016\u0001\u0000\u0000\u0000\u00025\u0001\u0000\u0000\u0000\u0004V\u0001"+
		"\u0000\u0000\u0000\u0006X\u0001\u0000\u0000\u0000\bZ\u0001\u0000\u0000"+
		"\u0000\n\\\u0001\u0000\u0000\u0000\f\u007f\u0001\u0000\u0000\u0000\u000e"+
		"\u0081\u0001\u0000\u0000\u0000\u0010\u008c\u0001\u0000\u0000\u0000\u0012"+
		"\u008e\u0001\u0000\u0000\u0000\u0014\u0090\u0001\u0000\u0000\u0000\u0016"+
		"\u0017\u0003\u0002\u0001\u0000\u0017\u0018\u0005\u0000\u0000\u0001\u0018"+
		"\u0001\u0001\u0000\u0000\u0000\u0019\u001a\u0006\u0001\uffff\uffff\u0000"+
		"\u001a\u001b\u0005!\u0000\u0000\u001b\u001c\u0003\u0002\u0001\u0000\u001c"+
		"\u001d\u0005\"\u0000\u0000\u001d6\u0001\u0000\u0000\u0000\u001e\u001f"+
		"\u0005\u0006\u0000\u0000\u001f6\u0003\u0002\u0001\u000f !\u0005\u0016"+
		"\u0000\u0000!6\u0003\u0002\u0001\r\"#\u0003\b\u0004\u0000#$\u0003\n\u0005"+
		"\u0000$6\u0001\u0000\u0000\u0000%6\u0003\u0010\b\u0000&\'\u0005,\u0000"+
		"\u0000\'(\u0003\u000e\u0007\u0000()\u0005\u0003\u0000\u0000)*\u0003\u000e"+
		"\u0007\u0000*6\u0001\u0000\u0000\u0000+-\u0005,\u0000\u0000,.\u0005\u0006"+
		"\u0000\u0000-,\u0001\u0000\u0000\u0000-.\u0001\u0000\u0000\u0000./\u0001"+
		"\u0000\u0000\u0000/0\u0005\u0002\u0000\u000006\u0003\n\u0005\u000012\u0005"+
		",\u0000\u000023\u0003\f\u0006\u000034\u0003\n\u0005\u000046\u0001\u0000"+
		"\u0000\u00005\u0019\u0001\u0000\u0000\u00005\u001e\u0001\u0000\u0000\u0000"+
		"5 \u0001\u0000\u0000\u00005\"\u0001\u0000\u0000\u00005%\u0001\u0000\u0000"+
		"\u00005&\u0001\u0000\u0000\u00005+\u0001\u0000\u0000\u000051\u0001\u0000"+
		"\u0000\u00006S\u0001\u0000\u0000\u000078\n\u000e\u0000\u000089\u0003\u0004"+
		"\u0002\u00009:\u0003\u0002\u0001\u000f:R\u0001\u0000\u0000\u0000;<\n\f"+
		"\u0000\u0000<=\u0005\u001a\u0000\u0000=R\u0003\u0002\u0001\r>?\n\u000b"+
		"\u0000\u0000?@\u0005\u0018\u0000\u0000@R\u0003\u0002\u0001\fAB\n\n\u0000"+
		"\u0000BC\u0005\u0017\u0000\u0000CR\u0003\u0002\u0001\u000bDE\n\t\u0000"+
		"\u0000EF\u0005\u0019\u0000\u0000FR\u0003\u0002\u0001\nGH\n\b\u0000\u0000"+
		"HI\u0005\u0015\u0000\u0000IR\u0003\u0002\u0001\tJK\n\u0007\u0000\u0000"+
		"KL\u0005\u0016\u0000\u0000LR\u0003\u0002\u0001\bMN\n\u0005\u0000\u0000"+
		"NO\u0003\u0012\t\u0000OP\u0003\u0002\u0001\u0006PR\u0001\u0000\u0000\u0000"+
		"Q7\u0001\u0000\u0000\u0000Q;\u0001\u0000\u0000\u0000Q>\u0001\u0000\u0000"+
		"\u0000QA\u0001\u0000\u0000\u0000QD\u0001\u0000\u0000\u0000QG\u0001\u0000"+
		"\u0000\u0000QJ\u0001\u0000\u0000\u0000QM\u0001\u0000\u0000\u0000RU\u0001"+
		"\u0000\u0000\u0000SQ\u0001\u0000\u0000\u0000ST\u0001\u0000\u0000\u0000"+
		"T\u0003\u0001\u0000\u0000\u0000US\u0001\u0000\u0000\u0000VW\u0007\u0000"+
		"\u0000\u0000W\u0005\u0001\u0000\u0000\u0000XY\u0007\u0001\u0000\u0000"+
		"Y\u0007\u0001\u0000\u0000\u0000Z[\u0007\u0002\u0000\u0000[\t\u0001\u0000"+
		"\u0000\u0000\\`\u0005!\u0000\u0000]_\u0005(\u0000\u0000^]\u0001\u0000"+
		"\u0000\u0000_b\u0001\u0000\u0000\u0000`^\u0001\u0000\u0000\u0000`a\u0001"+
		"\u0000\u0000\u0000ac\u0001\u0000\u0000\u0000b`\u0001\u0000\u0000\u0000"+
		"cg\u0003\u0002\u0001\u0000df\u0005(\u0000\u0000ed\u0001\u0000\u0000\u0000"+
		"fi\u0001\u0000\u0000\u0000ge\u0001\u0000\u0000\u0000gh\u0001\u0000\u0000"+
		"\u0000hz\u0001\u0000\u0000\u0000ig\u0001\u0000\u0000\u0000jn\u0005\u0001"+
		"\u0000\u0000km\u0005(\u0000\u0000lk\u0001\u0000\u0000\u0000mp\u0001\u0000"+
		"\u0000\u0000nl\u0001\u0000\u0000\u0000no\u0001\u0000\u0000\u0000oq\u0001"+
		"\u0000\u0000\u0000pn\u0001\u0000\u0000\u0000qu\u0003\u0002\u0001\u0000"+
		"rt\u0005(\u0000\u0000sr\u0001\u0000\u0000\u0000tw\u0001\u0000\u0000\u0000"+
		"us\u0001\u0000\u0000\u0000uv\u0001\u0000\u0000\u0000vy\u0001\u0000\u0000"+
		"\u0000wu\u0001\u0000\u0000\u0000xj\u0001\u0000\u0000\u0000y|\u0001\u0000"+
		"\u0000\u0000zx\u0001\u0000\u0000\u0000z{\u0001\u0000\u0000\u0000{}\u0001"+
		"\u0000\u0000\u0000|z\u0001\u0000\u0000\u0000}~\u0005\"\u0000\u0000~\u000b"+
		"\u0001\u0000\u0000\u0000\u007f\u0080\u0007\u0003\u0000\u0000\u0080\r\u0001"+
		"\u0000\u0000\u0000\u0081\u0082\u0007\u0004\u0000\u0000\u0082\u000f\u0001"+
		"\u0000\u0000\u0000\u0083\u008d\u0005%\u0000\u0000\u0084\u008d\u0005#\u0000"+
		"\u0000\u0085\u008d\u0005$\u0000\u0000\u0086\u008d\u0003\u0014\n\u0000"+
		"\u0087\u008d\u0005)\u0000\u0000\u0088\u008d\u0005,\u0000\u0000\u0089\u008d"+
		"\u0005&\u0000\u0000\u008a\u008d\u0005\'\u0000\u0000\u008b\u008d\u0001"+
		"\u0000\u0000\u0000\u008c\u0083\u0001\u0000\u0000\u0000\u008c\u0084\u0001"+
		"\u0000\u0000\u0000\u008c\u0085\u0001\u0000\u0000\u0000\u008c\u0086\u0001"+
		"\u0000\u0000\u0000\u008c\u0087\u0001\u0000\u0000\u0000\u008c\u0088\u0001"+
		"\u0000\u0000\u0000\u008c\u0089\u0001\u0000\u0000\u0000\u008c\u008a\u0001"+
		"\u0000\u0000\u0000\u008c\u008b\u0001\u0000\u0000\u0000\u008d\u0011\u0001"+
		"\u0000\u0000\u0000\u008e\u008f\u0007\u0005\u0000\u0000\u008f\u0013\u0001"+
		"\u0000\u0000\u0000\u0090\u0091\u0007\u0006\u0000\u0000\u0091\u0015\u0001"+
		"\u0000\u0000\u0000\n-5QS`gnuz\u008c";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}