package com.github.sidhant92.boolparser.parser.antlr;// Generated from /Users/sid/Desktop/filter1/BooleanExpression.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class BooleanExpressionLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, IN=2, TO=3, AND=4, OR=5, NOT=6, TRUE=7, FALSE=8, CONTAINS_ALL=9, 
		CONTAINS_ANY=10, NE=11, GT=12, GE=13, LT=14, LE=15, EQ=16, LPAREN=17, 
		RPAREN=18, DECIMAL=19, APP_VERSION=20, INTEGER=21, WS=22, WORD=23, ALPHANUMERIC=24, 
		SQ=25, DQ=26;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "IN", "TO", "AND", "OR", "NOT", "TRUE", "FALSE", "CONTAINS_ALL", 
			"CONTAINS_ANY", "NE", "GT", "GE", "LT", "LE", "EQ", "LPAREN", "RPAREN", 
			"DECIMAL", "APP_VERSION", "INTEGER", "WS", "WORD", "ALPHANUMERIC", "SQ", 
			"DQ"
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


	public BooleanExpressionLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "BooleanExpression.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u001a\u00f4\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0002\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001<\b\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002B\b\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0003\u0003L\b\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004T\b\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0003\u0005\\\b\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006f\b\u0006"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007r\b\u0007"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003\b\u008c\b\b\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\t\u00a6\b\t\u0001\n\u0001"+
		"\n\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\r\u0001"+
		"\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u0010"+
		"\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0012\u0004\u0012\u00bc\b\u0012"+
		"\u000b\u0012\f\u0012\u00bd\u0001\u0012\u0001\u0012\u0004\u0012\u00c2\b"+
		"\u0012\u000b\u0012\f\u0012\u00c3\u0001\u0013\u0001\u0013\u0001\u0013\u0004"+
		"\u0013\u00c9\b\u0013\u000b\u0013\f\u0013\u00ca\u0001\u0014\u0004\u0014"+
		"\u00ce\b\u0014\u000b\u0014\f\u0014\u00cf\u0001\u0015\u0004\u0015\u00d3"+
		"\b\u0015\u000b\u0015\f\u0015\u00d4\u0001\u0015\u0001\u0015\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0004\u0016\u00dd\b\u0016\u000b\u0016"+
		"\f\u0016\u00de\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0005\u0018"+
		"\u00e5\b\u0018\n\u0018\f\u0018\u00e8\t\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0019\u0001\u0019\u0005\u0019\u00ee\b\u0019\n\u0019\f\u0019\u00f1\t\u0019"+
		"\u0001\u0019\u0001\u0019\u0002\u00e6\u00ef\u0000\u001a\u0001\u0001\u0003"+
		"\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011"+
		"\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010"+
		"!\u0011#\u0012%\u0013\'\u0014)\u0015+\u0016-\u0017/\u00181\u00193\u001a"+
		"\u0001\u0000\u0004\u0001\u000009\u0003\u0000\t\n\f\r  \u0002\u0000-._"+
		"_\u0003\u000009AZaz\u0109\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003"+
		"\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007"+
		"\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001"+
		"\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000"+
		"\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000"+
		"\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000"+
		"\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000"+
		"\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000"+
		"\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000"+
		"\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000"+
		")\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001"+
		"\u0000\u0000\u0000\u0000/\u0001\u0000\u0000\u0000\u00001\u0001\u0000\u0000"+
		"\u0000\u00003\u0001\u0000\u0000\u0000\u00015\u0001\u0000\u0000\u0000\u0003"+
		";\u0001\u0000\u0000\u0000\u0005A\u0001\u0000\u0000\u0000\u0007K\u0001"+
		"\u0000\u0000\u0000\tS\u0001\u0000\u0000\u0000\u000b[\u0001\u0000\u0000"+
		"\u0000\re\u0001\u0000\u0000\u0000\u000fq\u0001\u0000\u0000\u0000\u0011"+
		"\u008b\u0001\u0000\u0000\u0000\u0013\u00a5\u0001\u0000\u0000\u0000\u0015"+
		"\u00a7\u0001\u0000\u0000\u0000\u0017\u00aa\u0001\u0000\u0000\u0000\u0019"+
		"\u00ac\u0001\u0000\u0000\u0000\u001b\u00af\u0001\u0000\u0000\u0000\u001d"+
		"\u00b1\u0001\u0000\u0000\u0000\u001f\u00b4\u0001\u0000\u0000\u0000!\u00b6"+
		"\u0001\u0000\u0000\u0000#\u00b8\u0001\u0000\u0000\u0000%\u00bb\u0001\u0000"+
		"\u0000\u0000\'\u00c5\u0001\u0000\u0000\u0000)\u00cd\u0001\u0000\u0000"+
		"\u0000+\u00d2\u0001\u0000\u0000\u0000-\u00dc\u0001\u0000\u0000\u0000/"+
		"\u00e0\u0001\u0000\u0000\u00001\u00e2\u0001\u0000\u0000\u00003\u00eb\u0001"+
		"\u0000\u0000\u000056\u0005,\u0000\u00006\u0002\u0001\u0000\u0000\u0000"+
		"78\u0005I\u0000\u00008<\u0005N\u0000\u00009:\u0005i\u0000\u0000:<\u0005"+
		"n\u0000\u0000;7\u0001\u0000\u0000\u0000;9\u0001\u0000\u0000\u0000<\u0004"+
		"\u0001\u0000\u0000\u0000=>\u0005T\u0000\u0000>B\u0005O\u0000\u0000?@\u0005"+
		"t\u0000\u0000@B\u0005o\u0000\u0000A=\u0001\u0000\u0000\u0000A?\u0001\u0000"+
		"\u0000\u0000B\u0006\u0001\u0000\u0000\u0000CD\u0005A\u0000\u0000DE\u0005"+
		"N\u0000\u0000EL\u0005D\u0000\u0000FG\u0005a\u0000\u0000GH\u0005n\u0000"+
		"\u0000HL\u0005d\u0000\u0000IJ\u0005&\u0000\u0000JL\u0005&\u0000\u0000"+
		"KC\u0001\u0000\u0000\u0000KF\u0001\u0000\u0000\u0000KI\u0001\u0000\u0000"+
		"\u0000L\b\u0001\u0000\u0000\u0000MN\u0005O\u0000\u0000NT\u0005R\u0000"+
		"\u0000OP\u0005o\u0000\u0000PT\u0005r\u0000\u0000QR\u0005|\u0000\u0000"+
		"RT\u0005|\u0000\u0000SM\u0001\u0000\u0000\u0000SO\u0001\u0000\u0000\u0000"+
		"SQ\u0001\u0000\u0000\u0000T\n\u0001\u0000\u0000\u0000UV\u0005N\u0000\u0000"+
		"VW\u0005O\u0000\u0000W\\\u0005T\u0000\u0000XY\u0005n\u0000\u0000YZ\u0005"+
		"o\u0000\u0000Z\\\u0005t\u0000\u0000[U\u0001\u0000\u0000\u0000[X\u0001"+
		"\u0000\u0000\u0000\\\f\u0001\u0000\u0000\u0000]^\u0005T\u0000\u0000^_"+
		"\u0005R\u0000\u0000_`\u0005U\u0000\u0000`f\u0005E\u0000\u0000ab\u0005"+
		"t\u0000\u0000bc\u0005r\u0000\u0000cd\u0005u\u0000\u0000df\u0005e\u0000"+
		"\u0000e]\u0001\u0000\u0000\u0000ea\u0001\u0000\u0000\u0000f\u000e\u0001"+
		"\u0000\u0000\u0000gh\u0005F\u0000\u0000hi\u0005A\u0000\u0000ij\u0005L"+
		"\u0000\u0000jk\u0005S\u0000\u0000kr\u0005E\u0000\u0000lm\u0005f\u0000"+
		"\u0000mn\u0005a\u0000\u0000no\u0005l\u0000\u0000op\u0005s\u0000\u0000"+
		"pr\u0005e\u0000\u0000qg\u0001\u0000\u0000\u0000ql\u0001\u0000\u0000\u0000"+
		"r\u0010\u0001\u0000\u0000\u0000st\u0005C\u0000\u0000tu\u0005O\u0000\u0000"+
		"uv\u0005N\u0000\u0000vw\u0005T\u0000\u0000wx\u0005A\u0000\u0000xy\u0005"+
		"I\u0000\u0000yz\u0005N\u0000\u0000z{\u0005S\u0000\u0000{|\u0005_\u0000"+
		"\u0000|}\u0005A\u0000\u0000}~\u0005L\u0000\u0000~\u008c\u0005L\u0000\u0000"+
		"\u007f\u0080\u0005c\u0000\u0000\u0080\u0081\u0005o\u0000\u0000\u0081\u0082"+
		"\u0005n\u0000\u0000\u0082\u0083\u0005t\u0000\u0000\u0083\u0084\u0005a"+
		"\u0000\u0000\u0084\u0085\u0005i\u0000\u0000\u0085\u0086\u0005n\u0000\u0000"+
		"\u0086\u0087\u0005s\u0000\u0000\u0087\u0088\u0005_\u0000\u0000\u0088\u0089"+
		"\u0005a\u0000\u0000\u0089\u008a\u0005l\u0000\u0000\u008a\u008c\u0005l"+
		"\u0000\u0000\u008bs\u0001\u0000\u0000\u0000\u008b\u007f\u0001\u0000\u0000"+
		"\u0000\u008c\u0012\u0001\u0000\u0000\u0000\u008d\u008e\u0005C\u0000\u0000"+
		"\u008e\u008f\u0005O\u0000\u0000\u008f\u0090\u0005N\u0000\u0000\u0090\u0091"+
		"\u0005T\u0000\u0000\u0091\u0092\u0005A\u0000\u0000\u0092\u0093\u0005I"+
		"\u0000\u0000\u0093\u0094\u0005N\u0000\u0000\u0094\u0095\u0005S\u0000\u0000"+
		"\u0095\u0096\u0005_\u0000\u0000\u0096\u0097\u0005A\u0000\u0000\u0097\u0098"+
		"\u0005N\u0000\u0000\u0098\u00a6\u0005Y\u0000\u0000\u0099\u009a\u0005c"+
		"\u0000\u0000\u009a\u009b\u0005o\u0000\u0000\u009b\u009c\u0005n\u0000\u0000"+
		"\u009c\u009d\u0005t\u0000\u0000\u009d\u009e\u0005a\u0000\u0000\u009e\u009f"+
		"\u0005i\u0000\u0000\u009f\u00a0\u0005n\u0000\u0000\u00a0\u00a1\u0005s"+
		"\u0000\u0000\u00a1\u00a2\u0005_\u0000\u0000\u00a2\u00a3\u0005a\u0000\u0000"+
		"\u00a3\u00a4\u0005n\u0000\u0000\u00a4\u00a6\u0005y\u0000\u0000\u00a5\u008d"+
		"\u0001\u0000\u0000\u0000\u00a5\u0099\u0001\u0000\u0000\u0000\u00a6\u0014"+
		"\u0001\u0000\u0000\u0000\u00a7\u00a8\u0005!\u0000\u0000\u00a8\u00a9\u0005"+
		"=\u0000\u0000\u00a9\u0016\u0001\u0000\u0000\u0000\u00aa\u00ab\u0005>\u0000"+
		"\u0000\u00ab\u0018\u0001\u0000\u0000\u0000\u00ac\u00ad\u0005>\u0000\u0000"+
		"\u00ad\u00ae\u0005=\u0000\u0000\u00ae\u001a\u0001\u0000\u0000\u0000\u00af"+
		"\u00b0\u0005<\u0000\u0000\u00b0\u001c\u0001\u0000\u0000\u0000\u00b1\u00b2"+
		"\u0005<\u0000\u0000\u00b2\u00b3\u0005=\u0000\u0000\u00b3\u001e\u0001\u0000"+
		"\u0000\u0000\u00b4\u00b5\u0005=\u0000\u0000\u00b5 \u0001\u0000\u0000\u0000"+
		"\u00b6\u00b7\u0005(\u0000\u0000\u00b7\"\u0001\u0000\u0000\u0000\u00b8"+
		"\u00b9\u0005)\u0000\u0000\u00b9$\u0001\u0000\u0000\u0000\u00ba\u00bc\u0007"+
		"\u0000\u0000\u0000\u00bb\u00ba\u0001\u0000\u0000\u0000\u00bc\u00bd\u0001"+
		"\u0000\u0000\u0000\u00bd\u00bb\u0001\u0000\u0000\u0000\u00bd\u00be\u0001"+
		"\u0000\u0000\u0000\u00be\u00bf\u0001\u0000\u0000\u0000\u00bf\u00c1\u0005"+
		".\u0000\u0000\u00c0\u00c2\u0007\u0000\u0000\u0000\u00c1\u00c0\u0001\u0000"+
		"\u0000\u0000\u00c2\u00c3\u0001\u0000\u0000\u0000\u00c3\u00c1\u0001\u0000"+
		"\u0000\u0000\u00c3\u00c4\u0001\u0000\u0000\u0000\u00c4&\u0001\u0000\u0000"+
		"\u0000\u00c5\u00c8\u0007\u0000\u0000\u0000\u00c6\u00c7\u0005.\u0000\u0000"+
		"\u00c7\u00c9\u0003)\u0014\u0000\u00c8\u00c6\u0001\u0000\u0000\u0000\u00c9"+
		"\u00ca\u0001\u0000\u0000\u0000\u00ca\u00c8\u0001\u0000\u0000\u0000\u00ca"+
		"\u00cb\u0001\u0000\u0000\u0000\u00cb(\u0001\u0000\u0000\u0000\u00cc\u00ce"+
		"\u0007\u0000\u0000\u0000\u00cd\u00cc\u0001\u0000\u0000\u0000\u00ce\u00cf"+
		"\u0001\u0000\u0000\u0000\u00cf\u00cd\u0001\u0000\u0000\u0000\u00cf\u00d0"+
		"\u0001\u0000\u0000\u0000\u00d0*\u0001\u0000\u0000\u0000\u00d1\u00d3\u0007"+
		"\u0001\u0000\u0000\u00d2\u00d1\u0001\u0000\u0000\u0000\u00d3\u00d4\u0001"+
		"\u0000\u0000\u0000\u00d4\u00d2\u0001\u0000\u0000\u0000\u00d4\u00d5\u0001"+
		"\u0000\u0000\u0000\u00d5\u00d6\u0001\u0000\u0000\u0000\u00d6\u00d7\u0006"+
		"\u0015\u0000\u0000\u00d7,\u0001\u0000\u0000\u0000\u00d8\u00dd\u0003/\u0017"+
		"\u0000\u00d9\u00dd\u0007\u0002\u0000\u0000\u00da\u00dd\u00031\u0018\u0000"+
		"\u00db\u00dd\u00033\u0019\u0000\u00dc\u00d8\u0001\u0000\u0000\u0000\u00dc"+
		"\u00d9\u0001\u0000\u0000\u0000\u00dc\u00da\u0001\u0000\u0000\u0000\u00dc"+
		"\u00db\u0001\u0000\u0000\u0000\u00dd\u00de\u0001\u0000\u0000\u0000\u00de"+
		"\u00dc\u0001\u0000\u0000\u0000\u00de\u00df\u0001\u0000\u0000\u0000\u00df"+
		".\u0001\u0000\u0000\u0000\u00e0\u00e1\u0007\u0003\u0000\u0000\u00e10\u0001"+
		"\u0000\u0000\u0000\u00e2\u00e6\u0005\'\u0000\u0000\u00e3\u00e5\t\u0000"+
		"\u0000\u0000\u00e4\u00e3\u0001\u0000\u0000\u0000\u00e5\u00e8\u0001\u0000"+
		"\u0000\u0000\u00e6\u00e7\u0001\u0000\u0000\u0000\u00e6\u00e4\u0001\u0000"+
		"\u0000\u0000\u00e7\u00e9\u0001\u0000\u0000\u0000\u00e8\u00e6\u0001\u0000"+
		"\u0000\u0000\u00e9\u00ea\u0005\'\u0000\u0000\u00ea2\u0001\u0000\u0000"+
		"\u0000\u00eb\u00ef\u0005\"\u0000\u0000\u00ec\u00ee\t\u0000\u0000\u0000"+
		"\u00ed\u00ec\u0001\u0000\u0000\u0000\u00ee\u00f1\u0001\u0000\u0000\u0000"+
		"\u00ef\u00f0\u0001\u0000\u0000\u0000\u00ef\u00ed\u0001\u0000\u0000\u0000"+
		"\u00f0\u00f2\u0001\u0000\u0000\u0000\u00f1\u00ef\u0001\u0000\u0000\u0000"+
		"\u00f2\u00f3\u0005\"\u0000\u0000\u00f34\u0001\u0000\u0000\u0000\u0013"+
		"\u0000;AKS[eq\u008b\u00a5\u00bd\u00c3\u00ca\u00cf\u00d4\u00dc\u00de\u00e6"+
		"\u00ef\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}