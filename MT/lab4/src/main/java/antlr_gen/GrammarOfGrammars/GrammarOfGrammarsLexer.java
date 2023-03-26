// Generated from java-escape by ANTLR 4.11.1
package antlr_gen.GrammarOfGrammars;

import grammar.Grammar;
import grammar.Rule;
import grammar.term.Code;
import grammar.term.NonTerminal;
import grammar.term.Term;
import grammar.term.Terminal;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class GrammarOfGrammarsLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.11.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		GRAMMAR=1, START=2, RETURNS=3, TOKEN_NAME=4, RULE_NAME=5, OR=6, COLON=7, 
		SEMICOLON=8, BRACKET_OPEN=9, BRACKET_CLOSE=10, SQUARE_OPEN=11, SQUARE_CLOSE=12, 
		QUOTE=13, CODE=14, ATTRIBUTES=15, REGEXP=16, WHITESPACE=17, EPS=18;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"GRAMMAR", "START", "RETURNS", "TOKEN_NAME", "RULE_NAME", "OR", "COLON", 
			"SEMICOLON", "BRACKET_OPEN", "BRACKET_CLOSE", "SQUARE_OPEN", "SQUARE_CLOSE", 
			"QUOTE", "CODE", "ATTRIBUTES", "REGEXP", "WHITESPACE", "EPS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'grammar'", "'start'", "'returns'", null, null, "'|'", "':'", 
			"';'", "'{'", "'}'", "'['", "']'", "'\"'", null, null, null, null, "'eps'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "GRAMMAR", "START", "RETURNS", "TOKEN_NAME", "RULE_NAME", "OR", 
			"COLON", "SEMICOLON", "BRACKET_OPEN", "BRACKET_CLOSE", "SQUARE_OPEN", 
			"SQUARE_CLOSE", "QUOTE", "CODE", "ATTRIBUTES", "REGEXP", "WHITESPACE", 
			"EPS"
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


	public GrammarOfGrammarsLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "GrammarOfGrammars.g4"; }

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
		"\u0004\u0000\u0012\u0086\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0004\u0003"+
		"=\b\u0003\u000b\u0003\f\u0003>\u0001\u0003\u0005\u0003B\b\u0003\n\u0003"+
		"\f\u0003E\t\u0003\u0001\u0004\u0004\u0004H\b\u0004\u000b\u0004\f\u0004"+
		"I\u0001\u0004\u0005\u0004M\b\u0004\n\u0004\f\u0004P\t\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001"+
		"\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001"+
		"\f\u0001\r\u0001\r\u0005\rd\b\r\n\r\f\rg\t\r\u0001\r\u0001\r\u0001\u000e"+
		"\u0001\u000e\u0004\u000em\b\u000e\u000b\u000e\f\u000en\u0001\u000e\u0001"+
		"\u000e\u0001\u000f\u0001\u000f\u0005\u000fu\b\u000f\n\u000f\f\u000fx\t"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0004\u0010}\b\u0010\u000b"+
		"\u0010\f\u0010~\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0003env\u0000\u0012\u0001\u0001\u0003\u0002\u0005"+
		"\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n"+
		"\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011"+
		"#\u0012\u0001\u0000\u0005\u0001\u0000AZ\u0002\u0000AZ__\u0001\u0000az"+
		"\u0003\u0000AZ__az\u0003\u0000\t\n\r\r  \u008d\u0000\u0001\u0001\u0000"+
		"\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000"+
		"\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000"+
		"\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000"+
		"\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000"+
		"\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000"+
		"\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000"+
		"\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000"+
		"\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000"+
		"#\u0001\u0000\u0000\u0000\u0001%\u0001\u0000\u0000\u0000\u0003-\u0001"+
		"\u0000\u0000\u0000\u00053\u0001\u0000\u0000\u0000\u0007<\u0001\u0000\u0000"+
		"\u0000\tG\u0001\u0000\u0000\u0000\u000bQ\u0001\u0000\u0000\u0000\rS\u0001"+
		"\u0000\u0000\u0000\u000fU\u0001\u0000\u0000\u0000\u0011W\u0001\u0000\u0000"+
		"\u0000\u0013Y\u0001\u0000\u0000\u0000\u0015[\u0001\u0000\u0000\u0000\u0017"+
		"]\u0001\u0000\u0000\u0000\u0019_\u0001\u0000\u0000\u0000\u001ba\u0001"+
		"\u0000\u0000\u0000\u001dj\u0001\u0000\u0000\u0000\u001fr\u0001\u0000\u0000"+
		"\u0000!|\u0001\u0000\u0000\u0000#\u0082\u0001\u0000\u0000\u0000%&\u0005"+
		"g\u0000\u0000&\'\u0005r\u0000\u0000\'(\u0005a\u0000\u0000()\u0005m\u0000"+
		"\u0000)*\u0005m\u0000\u0000*+\u0005a\u0000\u0000+,\u0005r\u0000\u0000"+
		",\u0002\u0001\u0000\u0000\u0000-.\u0005s\u0000\u0000./\u0005t\u0000\u0000"+
		"/0\u0005a\u0000\u000001\u0005r\u0000\u000012\u0005t\u0000\u00002\u0004"+
		"\u0001\u0000\u0000\u000034\u0005r\u0000\u000045\u0005e\u0000\u000056\u0005"+
		"t\u0000\u000067\u0005u\u0000\u000078\u0005r\u0000\u000089\u0005n\u0000"+
		"\u00009:\u0005s\u0000\u0000:\u0006\u0001\u0000\u0000\u0000;=\u0007\u0000"+
		"\u0000\u0000<;\u0001\u0000\u0000\u0000=>\u0001\u0000\u0000\u0000><\u0001"+
		"\u0000\u0000\u0000>?\u0001\u0000\u0000\u0000?C\u0001\u0000\u0000\u0000"+
		"@B\u0007\u0001\u0000\u0000A@\u0001\u0000\u0000\u0000BE\u0001\u0000\u0000"+
		"\u0000CA\u0001\u0000\u0000\u0000CD\u0001\u0000\u0000\u0000D\b\u0001\u0000"+
		"\u0000\u0000EC\u0001\u0000\u0000\u0000FH\u0007\u0002\u0000\u0000GF\u0001"+
		"\u0000\u0000\u0000HI\u0001\u0000\u0000\u0000IG\u0001\u0000\u0000\u0000"+
		"IJ\u0001\u0000\u0000\u0000JN\u0001\u0000\u0000\u0000KM\u0007\u0003\u0000"+
		"\u0000LK\u0001\u0000\u0000\u0000MP\u0001\u0000\u0000\u0000NL\u0001\u0000"+
		"\u0000\u0000NO\u0001\u0000\u0000\u0000O\n\u0001\u0000\u0000\u0000PN\u0001"+
		"\u0000\u0000\u0000QR\u0005|\u0000\u0000R\f\u0001\u0000\u0000\u0000ST\u0005"+
		":\u0000\u0000T\u000e\u0001\u0000\u0000\u0000UV\u0005;\u0000\u0000V\u0010"+
		"\u0001\u0000\u0000\u0000WX\u0005{\u0000\u0000X\u0012\u0001\u0000\u0000"+
		"\u0000YZ\u0005}\u0000\u0000Z\u0014\u0001\u0000\u0000\u0000[\\\u0005[\u0000"+
		"\u0000\\\u0016\u0001\u0000\u0000\u0000]^\u0005]\u0000\u0000^\u0018\u0001"+
		"\u0000\u0000\u0000_`\u0005\"\u0000\u0000`\u001a\u0001\u0000\u0000\u0000"+
		"ae\u0003\u0011\b\u0000bd\t\u0000\u0000\u0000cb\u0001\u0000\u0000\u0000"+
		"dg\u0001\u0000\u0000\u0000ef\u0001\u0000\u0000\u0000ec\u0001\u0000\u0000"+
		"\u0000fh\u0001\u0000\u0000\u0000ge\u0001\u0000\u0000\u0000hi\u0003\u0013"+
		"\t\u0000i\u001c\u0001\u0000\u0000\u0000jl\u0003\u0015\n\u0000km\t\u0000"+
		"\u0000\u0000lk\u0001\u0000\u0000\u0000mn\u0001\u0000\u0000\u0000no\u0001"+
		"\u0000\u0000\u0000nl\u0001\u0000\u0000\u0000op\u0001\u0000\u0000\u0000"+
		"pq\u0003\u0017\u000b\u0000q\u001e\u0001\u0000\u0000\u0000rv\u0003\u0019"+
		"\f\u0000su\t\u0000\u0000\u0000ts\u0001\u0000\u0000\u0000ux\u0001\u0000"+
		"\u0000\u0000vw\u0001\u0000\u0000\u0000vt\u0001\u0000\u0000\u0000wy\u0001"+
		"\u0000\u0000\u0000xv\u0001\u0000\u0000\u0000yz\u0003\u0019\f\u0000z \u0001"+
		"\u0000\u0000\u0000{}\u0007\u0004\u0000\u0000|{\u0001\u0000\u0000\u0000"+
		"}~\u0001\u0000\u0000\u0000~|\u0001\u0000\u0000\u0000~\u007f\u0001\u0000"+
		"\u0000\u0000\u007f\u0080\u0001\u0000\u0000\u0000\u0080\u0081\u0006\u0010"+
		"\u0000\u0000\u0081\"\u0001\u0000\u0000\u0000\u0082\u0083\u0005e\u0000"+
		"\u0000\u0083\u0084\u0005p\u0000\u0000\u0084\u0085\u0005s\u0000\u0000\u0085"+
		"$\u0001\u0000\u0000\u0000\t\u0000>CINenv~\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}