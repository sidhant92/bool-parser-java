package com.github.sidhant92.boolparser.parser.antlr;// Generated from java-escape by ANTLR 4.11.1

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings ({ "all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue" })
public class BooleanExpressionBoolParser extends Parser {
    static {
        RuntimeMetaData.checkVersion("4.11.1", RuntimeMetaData.VERSION);
    }

    protected static final DFA[] _decisionToDFA;

    protected static final PredictionContextCache _sharedContextCache = new PredictionContextCache();

    public static final int T__0 = 1, IN = 2, TO = 3, AND = 4, OR = 5, NOT = 6, TRUE = 7, FALSE = 8, NE = 9, GT = 10, GE = 11, LT = 12, LE = 13, EQ = 14, LPAREN = 15, RPAREN = 16, DECIMAL = 17, INTEGER = 18, APP_VERSION = 19, WS = 20, WORD = 21, ALPHANUMERIC = 22, SQ = 23, DQ = 24;

    public static final int RULE_parse = 0, RULE_expression = 1, RULE_comparator = 2, RULE_wordlist = 3, RULE_numericTypes = 4, RULE_types = 5, RULE_binary = 6, RULE_bool = 7;

    private static String[] makeRuleNames() {
        return new String[] { "parse", "expression", "comparator", "wordlist", "numericTypes", "types", "binary", "bool" };
    }

    public static final String[] ruleNames = makeRuleNames();

    private static String[] makeLiteralNames() {
        return new String[] { null, "','", null, null, null, null, null, null, null, "'!='", "'>'", "'>='", "'<'", "'<='", "'='", "'('", "')'" };
    }

    private static final String[] _LITERAL_NAMES = makeLiteralNames();

    private static String[] makeSymbolicNames() {
        return new String[] { null, null, "IN", "TO", "AND", "OR", "NOT", "TRUE", "FALSE", "NE", "GT", "GE", "LT", "LE", "EQ", "LPAREN", "RPAREN", "DECIMAL", "INTEGER", "APP_VERSION", "WS", "WORD", "ALPHANUMERIC", "SQ", "DQ" };
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
    public String getGrammarFileName() {
        return "java-escape";
    }

    @Override
    public String[] getRuleNames() {
        return ruleNames;
    }

    @Override
    public String getSerializedATN() {
        return _serializedATN;
    }

    @Override
    public ATN getATN() {
        return _ATN;
    }

    public BooleanExpressionBoolParser(TokenStream input) {
        super(input);
        _interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    @SuppressWarnings ("CheckReturnValue")
    public static class ParseContext extends ParserRuleContext {
        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class, 0);
        }

        public TerminalNode EOF() {
            return getToken(BooleanExpressionBoolParser.EOF, 0);
        }

        public ParseContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_parse;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof BooleanExpressionListener) {
                ((BooleanExpressionListener) listener).enterParse(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof BooleanExpressionListener) {
                ((BooleanExpressionListener) listener).exitParse(this);
            }
        }
    }

    public final ParseContext parse() throws RecognitionException {
        ParseContext _localctx = new ParseContext(_ctx, getState());
        enterRule(_localctx, 0, RULE_parse);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(16);
                expression(0);
                setState(17);
                match(EOF);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings ("CheckReturnValue")
    public static class ExpressionContext extends ParserRuleContext {
        public ExpressionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_expression;
        }

        public ExpressionContext() {
        }

        public void copyFrom(ExpressionContext ctx) {
            super.copyFrom(ctx);
        }
    }

    @SuppressWarnings ("CheckReturnValue")
    public static class BinaryExpressionContext extends ExpressionContext {
        public ExpressionContext left;

        public BinaryContext op;

        public ExpressionContext right;

        public List<ExpressionContext> expression() {
            return getRuleContexts(ExpressionContext.class);
        }

        public ExpressionContext expression(int i) {
            return getRuleContext(ExpressionContext.class, i);
        }

        public BinaryContext binary() {
            return getRuleContext(BinaryContext.class, 0);
        }

        public BinaryExpressionContext(ExpressionContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof BooleanExpressionListener) {
                ((BooleanExpressionListener) listener).enterBinaryExpression(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof BooleanExpressionListener) {
                ((BooleanExpressionListener) listener).exitBinaryExpression(this);
            }
        }
    }

    @SuppressWarnings ("CheckReturnValue")
    public static class TypesExpressionContext extends ExpressionContext {
        public TypesContext types() {
            return getRuleContext(TypesContext.class, 0);
        }

        public TypesExpressionContext(ExpressionContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof BooleanExpressionListener) {
                ((BooleanExpressionListener) listener).enterTypesExpression(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof BooleanExpressionListener) {
                ((BooleanExpressionListener) listener).exitTypesExpression(this);
            }
        }
    }

    @SuppressWarnings ("CheckReturnValue")
    public static class InExpressionContext extends ExpressionContext {
        public Token field;

        public WordlistContext data;

        public TerminalNode IN() {
            return getToken(BooleanExpressionBoolParser.IN, 0);
        }

        public TerminalNode WORD() {
            return getToken(BooleanExpressionBoolParser.WORD, 0);
        }

        public WordlistContext wordlist() {
            return getRuleContext(WordlistContext.class, 0);
        }

        public InExpressionContext(ExpressionContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof BooleanExpressionListener) {
                ((BooleanExpressionListener) listener).enterInExpression(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof BooleanExpressionListener) {
                ((BooleanExpressionListener) listener).exitInExpression(this);
            }
        }
    }

    @SuppressWarnings ("CheckReturnValue")
    public static class ToExpressionContext extends ExpressionContext {
        public Token field;

        public NumericTypesContext lower;

        public NumericTypesContext upper;

        public TerminalNode TO() {
            return getToken(BooleanExpressionBoolParser.TO, 0);
        }

        public TerminalNode WORD() {
            return getToken(BooleanExpressionBoolParser.WORD, 0);
        }

        public List<NumericTypesContext> numericTypes() {
            return getRuleContexts(NumericTypesContext.class);
        }

        public NumericTypesContext numericTypes(int i) {
            return getRuleContext(NumericTypesContext.class, i);
        }

        public ToExpressionContext(ExpressionContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof BooleanExpressionListener) {
                ((BooleanExpressionListener) listener).enterToExpression(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof BooleanExpressionListener) {
                ((BooleanExpressionListener) listener).exitToExpression(this);
            }
        }
    }

    @SuppressWarnings ("CheckReturnValue")
    public static class NotExpressionContext extends ExpressionContext {
        public TerminalNode NOT() {
            return getToken(BooleanExpressionBoolParser.NOT, 0);
        }

        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class, 0);
        }

        public NotExpressionContext(ExpressionContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof BooleanExpressionListener) {
                ((BooleanExpressionListener) listener).enterNotExpression(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof BooleanExpressionListener) {
                ((BooleanExpressionListener) listener).exitNotExpression(this);
            }
        }
    }

    @SuppressWarnings ("CheckReturnValue")
    public static class ComparatorExpressionContext extends ExpressionContext {
        public ExpressionContext left;

        public ComparatorContext op;

        public ExpressionContext right;

        public List<ExpressionContext> expression() {
            return getRuleContexts(ExpressionContext.class);
        }

        public ExpressionContext expression(int i) {
            return getRuleContext(ExpressionContext.class, i);
        }

        public ComparatorContext comparator() {
            return getRuleContext(ComparatorContext.class, 0);
        }

        public ComparatorExpressionContext(ExpressionContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof BooleanExpressionListener) {
                ((BooleanExpressionListener) listener).enterComparatorExpression(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof BooleanExpressionListener) {
                ((BooleanExpressionListener) listener).exitComparatorExpression(this);
            }
        }
    }

    @SuppressWarnings ("CheckReturnValue")
    public static class ParentExpressionContext extends ExpressionContext {
        public TerminalNode LPAREN() {
            return getToken(BooleanExpressionBoolParser.LPAREN, 0);
        }

        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class, 0);
        }

        public TerminalNode RPAREN() {
            return getToken(BooleanExpressionBoolParser.RPAREN, 0);
        }

        public ParentExpressionContext(ExpressionContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof BooleanExpressionListener) {
                ((BooleanExpressionListener) listener).enterParentExpression(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof BooleanExpressionListener) {
                ((BooleanExpressionListener) listener).exitParentExpression(this);
            }
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
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(35);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 0, _ctx)) {
                    case 1: {
                        _localctx = new ParentExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;

                        setState(20);
                        match(LPAREN);
                        setState(21);
                        expression(0);
                        setState(22);
                        match(RPAREN);
                    }
                    break;
                    case 2: {
                        _localctx = new NotExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(24);
                        match(NOT);
                        setState(25);
                        expression(6);
                    }
                    break;
                    case 3: {
                        _localctx = new TypesExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(26);
                        types();
                    }
                    break;
                    case 4: {
                        _localctx = new ToExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(27);
                        ((ToExpressionContext) _localctx).field = match(WORD);
                        setState(28);
                        ((ToExpressionContext) _localctx).lower = numericTypes();
                        setState(29);
                        match(TO);
                        setState(30);
                        ((ToExpressionContext) _localctx).upper = numericTypes();
                    }
                    break;
                    case 5: {
                        _localctx = new InExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(32);
                        ((InExpressionContext) _localctx).field = match(WORD);
                        setState(33);
                        match(IN);
                        setState(34);
                        ((InExpressionContext) _localctx).data = wordlist();
                    }
                    break;
                }
                _ctx.stop = _input.LT(-1);
                setState(47);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 2, _ctx);
                while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        if (_parseListeners != null) {
                            triggerExitRuleEvent();
                        }
                        _prevctx = _localctx;
                        {
                            setState(45);
                            _errHandler.sync(this);
                            switch (getInterpreter().adaptivePredict(_input, 1, _ctx)) {
                                case 1: {
                                    _localctx = new ComparatorExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    ((ComparatorExpressionContext) _localctx).left = _prevctx;
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(37);
                                    if (!(precpred(_ctx, 5))) {
                                        throw new FailedPredicateException(this, "precpred(_ctx, 5)");
                                    }
                                    setState(38);
                                    ((ComparatorExpressionContext) _localctx).op = comparator();
                                    setState(39);
                                    ((ComparatorExpressionContext) _localctx).right = expression(6);
                                }
                                break;
                                case 2: {
                                    _localctx = new BinaryExpressionContext(new ExpressionContext(_parentctx, _parentState));
                                    ((BinaryExpressionContext) _localctx).left = _prevctx;
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(41);
                                    if (!(precpred(_ctx, 4))) {
                                        throw new FailedPredicateException(this, "precpred(_ctx, 4)");
                                    }
                                    setState(42);
                                    ((BinaryExpressionContext) _localctx).op = binary();
                                    setState(43);
                                    ((BinaryExpressionContext) _localctx).right = expression(5);
                                }
                                break;
                            }
                        }
                    }
                    setState(49);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 2, _ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            unrollRecursionContexts(_parentctx);
        }
        return _localctx;
    }

    @SuppressWarnings ("CheckReturnValue")
    public static class ComparatorContext extends ParserRuleContext {
        public TerminalNode GT() {
            return getToken(BooleanExpressionBoolParser.GT, 0);
        }

        public TerminalNode GE() {
            return getToken(BooleanExpressionBoolParser.GE, 0);
        }

        public TerminalNode LT() {
            return getToken(BooleanExpressionBoolParser.LT, 0);
        }

        public TerminalNode LE() {
            return getToken(BooleanExpressionBoolParser.LE, 0);
        }

        public TerminalNode EQ() {
            return getToken(BooleanExpressionBoolParser.EQ, 0);
        }

        public TerminalNode NE() {
            return getToken(BooleanExpressionBoolParser.NE, 0);
        }

        public ComparatorContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_comparator;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof BooleanExpressionListener) {
                ((BooleanExpressionListener) listener).enterComparator(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof BooleanExpressionListener) {
                ((BooleanExpressionListener) listener).exitComparator(this);
            }
        }
    }

    public final ComparatorContext comparator() throws RecognitionException {
        ComparatorContext _localctx = new ComparatorContext(_ctx, getState());
        enterRule(_localctx, 4, RULE_comparator);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(50);
                _la = _input.LA(1);
                if (!(((_la) & ~0x3f) == 0 && ((1L << _la) & 32256L) != 0)) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) {
                        matchedEOF = true;
                    }
                    _errHandler.reportMatch(this);
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings ("CheckReturnValue")
    public static class WordlistContext extends ParserRuleContext {
        public TypesContext first;

        public TypesContext rest;

        public TerminalNode LPAREN() {
            return getToken(BooleanExpressionBoolParser.LPAREN, 0);
        }

        public TerminalNode RPAREN() {
            return getToken(BooleanExpressionBoolParser.RPAREN, 0);
        }

        public List<TypesContext> types() {
            return getRuleContexts(TypesContext.class);
        }

        public TypesContext types(int i) {
            return getRuleContext(TypesContext.class, i);
        }

        public List<TerminalNode> WS() {
            return getTokens(BooleanExpressionBoolParser.WS);
        }

        public TerminalNode WS(int i) {
            return getToken(BooleanExpressionBoolParser.WS, i);
        }

        public WordlistContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_wordlist;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof BooleanExpressionListener) {
                ((BooleanExpressionListener) listener).enterWordlist(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof BooleanExpressionListener) {
                ((BooleanExpressionListener) listener).exitWordlist(this);
            }
        }
    }

    public final WordlistContext wordlist() throws RecognitionException {
        WordlistContext _localctx = new WordlistContext(_ctx, getState());
        enterRule(_localctx, 6, RULE_wordlist);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(52);
                match(LPAREN);
                setState(56);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == WS) {
                    {
                        {
                            setState(53);
                            match(WS);
                        }
                    }
                    setState(58);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(59);
                ((WordlistContext) _localctx).first = types();
                setState(63);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == WS) {
                    {
                        {
                            setState(60);
                            match(WS);
                        }
                    }
                    setState(65);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(82);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == T__0) {
                    {
                        {
                            setState(66);
                            match(T__0);
                            setState(70);
                            _errHandler.sync(this);
                            _la = _input.LA(1);
                            while (_la == WS) {
                                {
                                    {
                                        setState(67);
                                        match(WS);
                                    }
                                }
                                setState(72);
                                _errHandler.sync(this);
                                _la = _input.LA(1);
                            }
                            setState(73);
                            ((WordlistContext) _localctx).rest = types();
                            setState(77);
                            _errHandler.sync(this);
                            _la = _input.LA(1);
                            while (_la == WS) {
                                {
                                    {
                                        setState(74);
                                        match(WS);
                                    }
                                }
                                setState(79);
                                _errHandler.sync(this);
                                _la = _input.LA(1);
                            }
                        }
                    }
                    setState(84);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(85);
                match(RPAREN);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings ("CheckReturnValue")
    public static class NumericTypesContext extends ParserRuleContext {
        public TerminalNode INTEGER() {
            return getToken(BooleanExpressionBoolParser.INTEGER, 0);
        }

        public TerminalNode DECIMAL() {
            return getToken(BooleanExpressionBoolParser.DECIMAL, 0);
        }

        public NumericTypesContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_numericTypes;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof BooleanExpressionListener) {
                ((BooleanExpressionListener) listener).enterNumericTypes(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof BooleanExpressionListener) {
                ((BooleanExpressionListener) listener).exitNumericTypes(this);
            }
        }
    }

    public final NumericTypesContext numericTypes() throws RecognitionException {
        NumericTypesContext _localctx = new NumericTypesContext(_ctx, getState());
        enterRule(_localctx, 8, RULE_numericTypes);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(87);
                _la = _input.LA(1);
                if (!(_la == DECIMAL || _la == INTEGER)) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) {
                        matchedEOF = true;
                    }
                    _errHandler.reportMatch(this);
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings ("CheckReturnValue")
    public static class TypesContext extends ParserRuleContext {
        public TerminalNode WORD() {
            return getToken(BooleanExpressionBoolParser.WORD, 0);
        }

        public TerminalNode INTEGER() {
            return getToken(BooleanExpressionBoolParser.INTEGER, 0);
        }

        public TerminalNode DECIMAL() {
            return getToken(BooleanExpressionBoolParser.DECIMAL, 0);
        }

        public TerminalNode APP_VERSION() {
            return getToken(BooleanExpressionBoolParser.APP_VERSION, 0);
        }

        public BoolContext bool() {
            return getRuleContext(BoolContext.class, 0);
        }

        public TypesContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_types;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof BooleanExpressionListener) {
                ((BooleanExpressionListener) listener).enterTypes(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof BooleanExpressionListener) {
                ((BooleanExpressionListener) listener).exitTypes(this);
            }
        }
    }

    public final TypesContext types() throws RecognitionException {
        TypesContext _localctx = new TypesContext(_ctx, getState());
        enterRule(_localctx, 10, RULE_types);
        try {
            setState(94);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case WORD:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(89);
                    match(WORD);
                }
                break;
                case INTEGER:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(90);
                    match(INTEGER);
                }
                break;
                case DECIMAL:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(91);
                    match(DECIMAL);
                }
                break;
                case APP_VERSION:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(92);
                    match(APP_VERSION);
                }
                break;
                case TRUE:
                case FALSE:
                    enterOuterAlt(_localctx, 5);
                {
                    setState(93);
                    bool();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings ("CheckReturnValue")
    public static class BinaryContext extends ParserRuleContext {
        public TerminalNode AND() {
            return getToken(BooleanExpressionBoolParser.AND, 0);
        }

        public TerminalNode OR() {
            return getToken(BooleanExpressionBoolParser.OR, 0);
        }

        public BinaryContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_binary;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof BooleanExpressionListener) {
                ((BooleanExpressionListener) listener).enterBinary(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof BooleanExpressionListener) {
                ((BooleanExpressionListener) listener).exitBinary(this);
            }
        }
    }

    public final BinaryContext binary() throws RecognitionException {
        BinaryContext _localctx = new BinaryContext(_ctx, getState());
        enterRule(_localctx, 12, RULE_binary);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(96);
                _la = _input.LA(1);
                if (!(_la == AND || _la == OR)) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) {
                        matchedEOF = true;
                    }
                    _errHandler.reportMatch(this);
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings ("CheckReturnValue")
    public static class BoolContext extends ParserRuleContext {
        public TerminalNode TRUE() {
            return getToken(BooleanExpressionBoolParser.TRUE, 0);
        }

        public TerminalNode FALSE() {
            return getToken(BooleanExpressionBoolParser.FALSE, 0);
        }

        public BoolContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_bool;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof BooleanExpressionListener) {
                ((BooleanExpressionListener) listener).enterBool(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof BooleanExpressionListener) {
                ((BooleanExpressionListener) listener).exitBool(this);
            }
        }
    }

    public final BoolContext bool() throws RecognitionException {
        BoolContext _localctx = new BoolContext(_ctx, getState());
        enterRule(_localctx, 14, RULE_bool);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(98);
                _la = _input.LA(1);
                if (!(_la == TRUE || _la == FALSE)) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) {
                        matchedEOF = true;
                    }
                    _errHandler.reportMatch(this);
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
        switch (ruleIndex) {
            case 1:
                return expression_sempred((ExpressionContext) _localctx, predIndex);
        }
        return true;
    }

    private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
        switch (predIndex) {
            case 0:
                return precpred(_ctx, 5);
            case 1:
                return precpred(_ctx, 4);
        }
        return true;
    }

    public static final String _serializedATN = "\u0004\u0001\u0018e\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002" + "\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002" + "\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0001" + "\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001" + "\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001" + "\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001" + "\u0001\u0003\u0001$\b\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001" + "\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001.\b" + "\u0001\n\u0001\f\u00011\t\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001" + "\u0003\u0005\u00037\b\u0003\n\u0003\f\u0003:\t\u0003\u0001\u0003\u0001" + "\u0003\u0005\u0003>\b\u0003\n\u0003\f\u0003A\t\u0003\u0001\u0003\u0001" + "\u0003\u0005\u0003E\b\u0003\n\u0003\f\u0003H\t\u0003\u0001\u0003\u0001" + "\u0003\u0005\u0003L\b\u0003\n\u0003\f\u0003O\t\u0003\u0005\u0003Q\b\u0003" + "\n\u0003\f\u0003T\t\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004" + "\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005" + "_\b\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007" + "\u0000\u0001\u0002\b\u0000\u0002\u0004\u0006\b\n\f\u000e\u0000\u0004\u0001" + "\u0000\t\u000e\u0001\u0000\u0011\u0012\u0001\u0000\u0004\u0005\u0001\u0000" + "\u0007\bk\u0000\u0010\u0001\u0000\u0000\u0000\u0002#\u0001\u0000\u0000" + "\u0000\u00042\u0001\u0000\u0000\u0000\u00064\u0001\u0000\u0000\u0000\b" + "W\u0001\u0000\u0000\u0000\n^\u0001\u0000\u0000\u0000\f`\u0001\u0000\u0000" + "\u0000\u000eb\u0001\u0000\u0000\u0000\u0010\u0011\u0003\u0002\u0001\u0000" + "\u0011\u0012\u0005\u0000\u0000\u0001\u0012\u0001\u0001\u0000\u0000\u0000" + "\u0013\u0014\u0006\u0001\uffff\uffff\u0000\u0014\u0015\u0005\u000f\u0000" + "\u0000\u0015\u0016\u0003\u0002\u0001\u0000\u0016\u0017\u0005\u0010\u0000" + "\u0000\u0017$\u0001\u0000\u0000\u0000\u0018\u0019\u0005\u0006\u0000\u0000" + "\u0019$\u0003\u0002\u0001\u0006\u001a$\u0003\n\u0005\u0000\u001b\u001c" + "\u0005\u0015\u0000\u0000\u001c\u001d\u0003\b\u0004\u0000\u001d\u001e\u0005" + "\u0003\u0000\u0000\u001e\u001f\u0003\b\u0004\u0000\u001f$\u0001\u0000" + "\u0000\u0000 !\u0005\u0015\u0000\u0000!\"\u0005\u0002\u0000\u0000\"$\u0003" + "\u0006\u0003\u0000#\u0013\u0001\u0000\u0000\u0000#\u0018\u0001\u0000\u0000" + "\u0000#\u001a\u0001\u0000\u0000\u0000#\u001b\u0001\u0000\u0000\u0000#" + " \u0001\u0000\u0000\u0000$/\u0001\u0000\u0000\u0000%&\n\u0005\u0000\u0000" + "&\'\u0003\u0004\u0002\u0000\'(\u0003\u0002\u0001\u0006(.\u0001\u0000\u0000" + "\u0000)*\n\u0004\u0000\u0000*+\u0003\f\u0006\u0000+,\u0003\u0002\u0001" + "\u0005,.\u0001\u0000\u0000\u0000-%\u0001\u0000\u0000\u0000-)\u0001\u0000" + "\u0000\u0000.1\u0001\u0000\u0000\u0000/-\u0001\u0000\u0000\u0000/0\u0001" + "\u0000\u0000\u00000\u0003\u0001\u0000\u0000\u00001/\u0001\u0000\u0000" + "\u000023\u0007\u0000\u0000\u00003\u0005\u0001\u0000\u0000\u000048\u0005" + "\u000f\u0000\u000057\u0005\u0014\u0000\u000065\u0001\u0000\u0000\u0000" + "7:\u0001\u0000\u0000\u000086\u0001\u0000\u0000\u000089\u0001\u0000\u0000" + "\u00009;\u0001\u0000\u0000\u0000:8\u0001\u0000\u0000\u0000;?\u0003\n\u0005" + "\u0000<>\u0005\u0014\u0000\u0000=<\u0001\u0000\u0000\u0000>A\u0001\u0000" + "\u0000\u0000?=\u0001\u0000\u0000\u0000?@\u0001\u0000\u0000\u0000@R\u0001" + "\u0000\u0000\u0000A?\u0001\u0000\u0000\u0000BF\u0005\u0001\u0000\u0000" + "CE\u0005\u0014\u0000\u0000DC\u0001\u0000\u0000\u0000EH\u0001\u0000\u0000" + "\u0000FD\u0001\u0000\u0000\u0000FG\u0001\u0000\u0000\u0000GI\u0001\u0000" + "\u0000\u0000HF\u0001\u0000\u0000\u0000IM\u0003\n\u0005\u0000JL\u0005\u0014" + "\u0000\u0000KJ\u0001\u0000\u0000\u0000LO\u0001\u0000\u0000\u0000MK\u0001" + "\u0000\u0000\u0000MN\u0001\u0000\u0000\u0000NQ\u0001\u0000\u0000\u0000" + "OM\u0001\u0000\u0000\u0000PB\u0001\u0000\u0000\u0000QT\u0001\u0000\u0000" + "\u0000RP\u0001\u0000\u0000\u0000RS\u0001\u0000\u0000\u0000SU\u0001\u0000" + "\u0000\u0000TR\u0001\u0000\u0000\u0000UV\u0005\u0010\u0000\u0000V\u0007" + "\u0001\u0000\u0000\u0000WX\u0007\u0001\u0000\u0000X\t\u0001\u0000\u0000" + "\u0000Y_\u0005\u0015\u0000\u0000Z_\u0005\u0012\u0000\u0000[_\u0005\u0011" + "\u0000\u0000\\_\u0005\u0013\u0000\u0000]_\u0003\u000e\u0007\u0000^Y\u0001" + "\u0000\u0000\u0000^Z\u0001\u0000\u0000\u0000^[\u0001\u0000\u0000\u0000" + "^\\\u0001\u0000\u0000\u0000^]\u0001\u0000\u0000\u0000_\u000b\u0001\u0000" + "\u0000\u0000`a\u0007\u0002\u0000\u0000a\r\u0001\u0000\u0000\u0000bc\u0007" + "\u0003\u0000\u0000c\u000f\u0001\u0000\u0000\u0000\t#-/8?FMR^";

    public static final ATN _ATN = new ATNDeserializer().deserialize(_serializedATN.toCharArray());

    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }
    }
}