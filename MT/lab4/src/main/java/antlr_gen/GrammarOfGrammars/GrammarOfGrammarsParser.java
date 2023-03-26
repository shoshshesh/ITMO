// Generated from java-escape by ANTLR 4.11.1
package antlr_gen.GrammarOfGrammars;

import grammar.Grammar;
import grammar.Rule;
import grammar.term.Code;
import grammar.term.NonTerminal;
import grammar.term.Term;
import grammar.term.Terminal;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class GrammarOfGrammarsParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.11.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		GRAMMAR=1, START=2, RETURNS=3, TOKEN_NAME=4, RULE_NAME=5, OR=6, COLON=7, 
		SEMICOLON=8, BRACKET_OPEN=9, BRACKET_CLOSE=10, SQUARE_OPEN=11, SQUARE_CLOSE=12, 
		QUOTE=13, CODE=14, ATTRIBUTES=15, REGEXP=16, WHITESPACE=17, EPS=18;
	public static final int
		RULE_grammarOfGrammars = 0, RULE_startName = 1, RULE_grammarName = 2, 
		RULE_rules = 3, RULE_grammarRule = 4, RULE_terminalRule = 5, RULE_name = 6, 
		RULE_nonTerminalRule = 7, RULE_rightPart = 8, RULE_term = 9, RULE_synAttr = 10, 
		RULE_heritableAttr = 11;
	private static String[] makeRuleNames() {
		return new String[] {
			"grammarOfGrammars", "startName", "grammarName", "rules", "grammarRule", 
			"terminalRule", "name", "nonTerminalRule", "rightPart", "term", "synAttr", 
			"heritableAttr"
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

	@Override
	public String getGrammarFileName() { return "java-escape"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public GrammarOfGrammarsParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GrammarOfGrammarsContext extends ParserRuleContext {
		public Grammar grammar;
		public GrammarNameContext grammarName;
		public StartNameContext startName;
		public GrammarNameContext grammarName() {
			return getRuleContext(GrammarNameContext.class,0);
		}
		public StartNameContext startName() {
			return getRuleContext(StartNameContext.class,0);
		}
		public RulesContext rules() {
			return getRuleContext(RulesContext.class,0);
		}
		public TerminalNode EOF() { return getToken(GrammarOfGrammarsParser.EOF, 0); }
		public GrammarOfGrammarsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_grammarOfGrammars; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarOfGrammarsListener ) ((GrammarOfGrammarsListener)listener).enterGrammarOfGrammars(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarOfGrammarsListener ) ((GrammarOfGrammarsListener)listener).exitGrammarOfGrammars(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarOfGrammarsVisitor ) return ((GrammarOfGrammarsVisitor<? extends T>)visitor).visitGrammarOfGrammars(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GrammarOfGrammarsContext grammarOfGrammars() throws RecognitionException {
		GrammarOfGrammarsContext _localctx = new GrammarOfGrammarsContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_grammarOfGrammars);

		    Grammar grammar = new Grammar();
		    ((GrammarOfGrammarsContext)_localctx).grammar =  grammar;

		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			((GrammarOfGrammarsContext)_localctx).grammarName = grammarName();
			setState(25);
			((GrammarOfGrammarsContext)_localctx).startName = startName();
			setState(26);
			rules(grammar);

			        _localctx.grammar.setName(((GrammarOfGrammarsContext)_localctx).grammarName.nameGrammar);
			        _localctx.grammar.setStart(((GrammarOfGrammarsContext)_localctx).startName.nameStart);
			      
			setState(28);
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
	public static class StartNameContext extends ParserRuleContext {
		public String nameStart;
		public NameContext name;
		public TerminalNode START() { return getToken(GrammarOfGrammarsParser.START, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(GrammarOfGrammarsParser.SEMICOLON, 0); }
		public StartNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_startName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarOfGrammarsListener ) ((GrammarOfGrammarsListener)listener).enterStartName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarOfGrammarsListener ) ((GrammarOfGrammarsListener)listener).exitStartName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarOfGrammarsVisitor ) return ((GrammarOfGrammarsVisitor<? extends T>)visitor).visitStartName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartNameContext startName() throws RecognitionException {
		StartNameContext _localctx = new StartNameContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_startName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			match(START);
			setState(31);
			((StartNameContext)_localctx).name = name();
			setState(32);
			match(SEMICOLON);

			        ((StartNameContext)_localctx).nameStart =  (((StartNameContext)_localctx).name!=null?_input.getText(((StartNameContext)_localctx).name.start,((StartNameContext)_localctx).name.stop):null);
			    
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
	public static class GrammarNameContext extends ParserRuleContext {
		public String nameGrammar;
		public NameContext name;
		public TerminalNode GRAMMAR() { return getToken(GrammarOfGrammarsParser.GRAMMAR, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(GrammarOfGrammarsParser.SEMICOLON, 0); }
		public GrammarNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_grammarName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarOfGrammarsListener ) ((GrammarOfGrammarsListener)listener).enterGrammarName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarOfGrammarsListener ) ((GrammarOfGrammarsListener)listener).exitGrammarName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarOfGrammarsVisitor ) return ((GrammarOfGrammarsVisitor<? extends T>)visitor).visitGrammarName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GrammarNameContext grammarName() throws RecognitionException {
		GrammarNameContext _localctx = new GrammarNameContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_grammarName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			match(GRAMMAR);
			setState(36);
			((GrammarNameContext)_localctx).name = name();
			setState(37);
			match(SEMICOLON);

			        ((GrammarNameContext)_localctx).nameGrammar =  (((GrammarNameContext)_localctx).name!=null?_input.getText(((GrammarNameContext)_localctx).name.start,((GrammarNameContext)_localctx).name.stop):null);
			      
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
	public static class RulesContext extends ParserRuleContext {
		public Grammar grammar;
		public List<GrammarRuleContext> grammarRule() {
			return getRuleContexts(GrammarRuleContext.class);
		}
		public GrammarRuleContext grammarRule(int i) {
			return getRuleContext(GrammarRuleContext.class,i);
		}
		public RulesContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public RulesContext(ParserRuleContext parent, int invokingState, Grammar grammar) {
			super(parent, invokingState);
			this.grammar = grammar;
		}
		@Override public int getRuleIndex() { return RULE_rules; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarOfGrammarsListener ) ((GrammarOfGrammarsListener)listener).enterRules(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarOfGrammarsListener ) ((GrammarOfGrammarsListener)listener).exitRules(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarOfGrammarsVisitor ) return ((GrammarOfGrammarsVisitor<? extends T>)visitor).visitRules(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RulesContext rules(Grammar grammar) throws RecognitionException {
		RulesContext _localctx = new RulesContext(_ctx, getState(), grammar);
		enterRule(_localctx, 6, RULE_rules);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TOKEN_NAME || _la==RULE_NAME) {
				{
				{
				setState(40);
				grammarRule(grammar);
				}
				}
				setState(45);
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

	@SuppressWarnings("CheckReturnValue")
	public static class GrammarRuleContext extends ParserRuleContext {
		public Grammar grammar;
		public TerminalRuleContext terminalRule() {
			return getRuleContext(TerminalRuleContext.class,0);
		}
		public NonTerminalRuleContext nonTerminalRule() {
			return getRuleContext(NonTerminalRuleContext.class,0);
		}
		public GrammarRuleContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public GrammarRuleContext(ParserRuleContext parent, int invokingState, Grammar grammar) {
			super(parent, invokingState);
			this.grammar = grammar;
		}
		@Override public int getRuleIndex() { return RULE_grammarRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarOfGrammarsListener ) ((GrammarOfGrammarsListener)listener).enterGrammarRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarOfGrammarsListener ) ((GrammarOfGrammarsListener)listener).exitGrammarRule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarOfGrammarsVisitor ) return ((GrammarOfGrammarsVisitor<? extends T>)visitor).visitGrammarRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GrammarRuleContext grammarRule(Grammar grammar) throws RecognitionException {
		GrammarRuleContext _localctx = new GrammarRuleContext(_ctx, getState(), grammar);
		enterRule(_localctx, 8, RULE_grammarRule);
		try {
			setState(48);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(46);
				terminalRule(grammar);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(47);
				nonTerminalRule(grammar);
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
	public static class TerminalRuleContext extends ParserRuleContext {
		public Grammar grammar;
		public NameContext name;
		public Token REGEXP;
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode COLON() { return getToken(GrammarOfGrammarsParser.COLON, 0); }
		public TerminalNode REGEXP() { return getToken(GrammarOfGrammarsParser.REGEXP, 0); }
		public TerminalNode SEMICOLON() { return getToken(GrammarOfGrammarsParser.SEMICOLON, 0); }
		public TerminalRuleContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public TerminalRuleContext(ParserRuleContext parent, int invokingState, Grammar grammar) {
			super(parent, invokingState);
			this.grammar = grammar;
		}
		@Override public int getRuleIndex() { return RULE_terminalRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarOfGrammarsListener ) ((GrammarOfGrammarsListener)listener).enterTerminalRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarOfGrammarsListener ) ((GrammarOfGrammarsListener)listener).exitTerminalRule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarOfGrammarsVisitor ) return ((GrammarOfGrammarsVisitor<? extends T>)visitor).visitTerminalRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TerminalRuleContext terminalRule(Grammar grammar) throws RecognitionException {
		TerminalRuleContext _localctx = new TerminalRuleContext(_ctx, getState(), grammar);
		enterRule(_localctx, 10, RULE_terminalRule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			((TerminalRuleContext)_localctx).name = name();
			setState(51);
			match(COLON);
			setState(52);
			((TerminalRuleContext)_localctx).REGEXP = match(REGEXP);
			setState(53);
			match(SEMICOLON);

			        _localctx.grammar.addTerminal((((TerminalRuleContext)_localctx).name!=null?_input.getText(((TerminalRuleContext)_localctx).name.start,((TerminalRuleContext)_localctx).name.stop):null), (((TerminalRuleContext)_localctx).REGEXP!=null?((TerminalRuleContext)_localctx).REGEXP.getText():null).substring(1, (((TerminalRuleContext)_localctx).REGEXP!=null?((TerminalRuleContext)_localctx).REGEXP.getText():null).length() - 1));
			      
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
	public static class NameContext extends ParserRuleContext {
		public TerminalNode RULE_NAME() { return getToken(GrammarOfGrammarsParser.RULE_NAME, 0); }
		public TerminalNode TOKEN_NAME() { return getToken(GrammarOfGrammarsParser.TOKEN_NAME, 0); }
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarOfGrammarsListener ) ((GrammarOfGrammarsListener)listener).enterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarOfGrammarsListener ) ((GrammarOfGrammarsListener)listener).exitName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarOfGrammarsVisitor ) return ((GrammarOfGrammarsVisitor<? extends T>)visitor).visitName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			_la = _input.LA(1);
			if ( !(_la==TOKEN_NAME || _la==RULE_NAME) ) {
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
	public static class NonTerminalRuleContext extends ParserRuleContext {
		public Grammar grammar;
		public NameContext name;
		public HeritableAttrContext heritableAttr;
		public SynAttrContext synAttr;
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode COLON() { return getToken(GrammarOfGrammarsParser.COLON, 0); }
		public List<RightPartContext> rightPart() {
			return getRuleContexts(RightPartContext.class);
		}
		public RightPartContext rightPart(int i) {
			return getRuleContext(RightPartContext.class,i);
		}
		public TerminalNode SEMICOLON() { return getToken(GrammarOfGrammarsParser.SEMICOLON, 0); }
		public HeritableAttrContext heritableAttr() {
			return getRuleContext(HeritableAttrContext.class,0);
		}
		public SynAttrContext synAttr() {
			return getRuleContext(SynAttrContext.class,0);
		}
		public List<TerminalNode> OR() { return getTokens(GrammarOfGrammarsParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(GrammarOfGrammarsParser.OR, i);
		}
		public NonTerminalRuleContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public NonTerminalRuleContext(ParserRuleContext parent, int invokingState, Grammar grammar) {
			super(parent, invokingState);
			this.grammar = grammar;
		}
		@Override public int getRuleIndex() { return RULE_nonTerminalRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarOfGrammarsListener ) ((GrammarOfGrammarsListener)listener).enterNonTerminalRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarOfGrammarsListener ) ((GrammarOfGrammarsListener)listener).exitNonTerminalRule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarOfGrammarsVisitor ) return ((GrammarOfGrammarsVisitor<? extends T>)visitor).visitNonTerminalRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NonTerminalRuleContext nonTerminalRule(Grammar grammar) throws RecognitionException {
		NonTerminalRuleContext _localctx = new NonTerminalRuleContext(_ctx, getState(), grammar);
		enterRule(_localctx, 14, RULE_nonTerminalRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			((NonTerminalRuleContext)_localctx).name = name();
			setState(60);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ATTRIBUTES) {
				{
				setState(59);
				((NonTerminalRuleContext)_localctx).heritableAttr = heritableAttr();
				}
			}

			setState(63);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==RETURNS) {
				{
				setState(62);
				((NonTerminalRuleContext)_localctx).synAttr = synAttr();
				}
			}

			setState(65);
			match(COLON);
			setState(66);
			rightPart(grammar, new NonTerminal((((NonTerminalRuleContext)_localctx).name!=null?_input.getText(((NonTerminalRuleContext)_localctx).name.start,((NonTerminalRuleContext)_localctx).name.stop):null)));
			setState(71);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(67);
				match(OR);
				setState(68);
				rightPart(grammar, new NonTerminal((((NonTerminalRuleContext)_localctx).name!=null?_input.getText(((NonTerminalRuleContext)_localctx).name.start,((NonTerminalRuleContext)_localctx).name.stop):null)));
				}
				}
				setState(73);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(74);
			match(SEMICOLON);

			        _localctx.grammar.addNonTerminal((((NonTerminalRuleContext)_localctx).name!=null?_input.getText(((NonTerminalRuleContext)_localctx).name.start,((NonTerminalRuleContext)_localctx).name.stop):null));
			        try {
			            _localctx.grammar.addHeritableAttrsSignature((((NonTerminalRuleContext)_localctx).name!=null?_input.getText(((NonTerminalRuleContext)_localctx).name.start,((NonTerminalRuleContext)_localctx).name.stop):null), ((NonTerminalRuleContext)_localctx).heritableAttr.attributes);
			        } catch (NullPointerException ignored) {

			        }
			        try {
			            String[] attrsArray = ((NonTerminalRuleContext)_localctx).synAttr.attributes.split(",");
			            StringBuilder attrs = new StringBuilder();
			            for (String attr : attrsArray) {
			                attrs.append("public ").append(attr).append(";\n");
			            }
			            _localctx.grammar.addSynAttrsSignature((((NonTerminalRuleContext)_localctx).name!=null?_input.getText(((NonTerminalRuleContext)_localctx).name.start,((NonTerminalRuleContext)_localctx).name.stop):null), attrs.toString());
			        } catch (NullPointerException ignored) {

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
	public static class RightPartContext extends ParserRuleContext {
		public Grammar grammar;
		public NonTerminal nT;
		public TermContext term;
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public RightPartContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public RightPartContext(ParserRuleContext parent, int invokingState, Grammar grammar, NonTerminal nT) {
			super(parent, invokingState);
			this.grammar = grammar;
			this.nT = nT;
		}
		@Override public int getRuleIndex() { return RULE_rightPart; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarOfGrammarsListener ) ((GrammarOfGrammarsListener)listener).enterRightPart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarOfGrammarsListener ) ((GrammarOfGrammarsListener)listener).exitRightPart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarOfGrammarsVisitor ) return ((GrammarOfGrammarsVisitor<? extends T>)visitor).visitRightPart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RightPartContext rightPart(Grammar grammar,NonTerminal nT) throws RecognitionException {
		RightPartContext _localctx = new RightPartContext(_ctx, getState(), grammar, nT);
		enterRule(_localctx, 16, RULE_rightPart);

		    List<Term> rightPart = new ArrayList<>();

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(77);
				((RightPartContext)_localctx).term = term();

				        rightPart.add(((RightPartContext)_localctx).term.t);
				      
				}
				}
				setState(82); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( ((_la) & ~0x3f) == 0 && ((1L << _la) & 16432L) != 0 );

			        _localctx.grammar.addRule(_localctx.nT, new Rule(_localctx.nT, rightPart));
			      
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
	public static class TermContext extends ParserRuleContext {
		public Term t;
		public Token RULE_NAME;
		public HeritableAttrContext heritableAttr;
		public Token TOKEN_NAME;
		public Token CODE;
		public TerminalNode RULE_NAME() { return getToken(GrammarOfGrammarsParser.RULE_NAME, 0); }
		public HeritableAttrContext heritableAttr() {
			return getRuleContext(HeritableAttrContext.class,0);
		}
		public TerminalNode TOKEN_NAME() { return getToken(GrammarOfGrammarsParser.TOKEN_NAME, 0); }
		public TerminalNode CODE() { return getToken(GrammarOfGrammarsParser.CODE, 0); }
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarOfGrammarsListener ) ((GrammarOfGrammarsListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarOfGrammarsListener ) ((GrammarOfGrammarsListener)listener).exitTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarOfGrammarsVisitor ) return ((GrammarOfGrammarsVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_term);
		int _la;
		try {
			setState(95);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case RULE_NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(86);
				((TermContext)_localctx).RULE_NAME = match(RULE_NAME);
				setState(88);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ATTRIBUTES) {
					{
					setState(87);
					((TermContext)_localctx).heritableAttr = heritableAttr();
					}
				}


				        try {
				            ((TermContext)_localctx).t =  new NonTerminal((((TermContext)_localctx).RULE_NAME!=null?((TermContext)_localctx).RULE_NAME.getText():null), ((TermContext)_localctx).heritableAttr.attributes);
				        } catch (NullPointerException ignored) {
				            ((TermContext)_localctx).t =  new NonTerminal((((TermContext)_localctx).RULE_NAME!=null?((TermContext)_localctx).RULE_NAME.getText():null));
				        }

				      
				}
				break;
			case TOKEN_NAME:
				enterOuterAlt(_localctx, 2);
				{
				setState(91);
				((TermContext)_localctx).TOKEN_NAME = match(TOKEN_NAME);

				        ((TermContext)_localctx).t =  new Terminal((((TermContext)_localctx).TOKEN_NAME!=null?((TermContext)_localctx).TOKEN_NAME.getText():null));
				      
				}
				break;
			case CODE:
				enterOuterAlt(_localctx, 3);
				{
				setState(93);
				((TermContext)_localctx).CODE = match(CODE);

				        ((TermContext)_localctx).t =  new Code((((TermContext)_localctx).CODE!=null?((TermContext)_localctx).CODE.getText():null));
				      
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

	@SuppressWarnings("CheckReturnValue")
	public static class SynAttrContext extends ParserRuleContext {
		public String attributes;
		public Token ATTRIBUTES;
		public TerminalNode RETURNS() { return getToken(GrammarOfGrammarsParser.RETURNS, 0); }
		public TerminalNode ATTRIBUTES() { return getToken(GrammarOfGrammarsParser.ATTRIBUTES, 0); }
		public SynAttrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_synAttr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarOfGrammarsListener ) ((GrammarOfGrammarsListener)listener).enterSynAttr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarOfGrammarsListener ) ((GrammarOfGrammarsListener)listener).exitSynAttr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarOfGrammarsVisitor ) return ((GrammarOfGrammarsVisitor<? extends T>)visitor).visitSynAttr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SynAttrContext synAttr() throws RecognitionException {
		SynAttrContext _localctx = new SynAttrContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_synAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			match(RETURNS);
			setState(98);
			((SynAttrContext)_localctx).ATTRIBUTES = match(ATTRIBUTES);

			        ((SynAttrContext)_localctx).attributes =  (((SynAttrContext)_localctx).ATTRIBUTES!=null?((SynAttrContext)_localctx).ATTRIBUTES.getText():null).substring(1, (((SynAttrContext)_localctx).ATTRIBUTES!=null?((SynAttrContext)_localctx).ATTRIBUTES.getText():null).length() - 1);
			    
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
	public static class HeritableAttrContext extends ParserRuleContext {
		public String attributes;
		public Token ATTRIBUTES;
		public TerminalNode ATTRIBUTES() { return getToken(GrammarOfGrammarsParser.ATTRIBUTES, 0); }
		public HeritableAttrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_heritableAttr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarOfGrammarsListener ) ((GrammarOfGrammarsListener)listener).enterHeritableAttr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarOfGrammarsListener ) ((GrammarOfGrammarsListener)listener).exitHeritableAttr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarOfGrammarsVisitor ) return ((GrammarOfGrammarsVisitor<? extends T>)visitor).visitHeritableAttr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HeritableAttrContext heritableAttr() throws RecognitionException {
		HeritableAttrContext _localctx = new HeritableAttrContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_heritableAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101);
			((HeritableAttrContext)_localctx).ATTRIBUTES = match(ATTRIBUTES);

			        ((HeritableAttrContext)_localctx).attributes =  (((HeritableAttrContext)_localctx).ATTRIBUTES!=null?((HeritableAttrContext)_localctx).ATTRIBUTES.getText():null).substring(1, (((HeritableAttrContext)_localctx).ATTRIBUTES!=null?((HeritableAttrContext)_localctx).ATTRIBUTES.getText():null).length() - 1);
			    
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
		"\u0004\u0001\u0012i\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0005\u0003*\b"+
		"\u0003\n\u0003\f\u0003-\t\u0003\u0001\u0004\u0001\u0004\u0003\u00041\b"+
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0003\u0007=\b"+
		"\u0007\u0001\u0007\u0003\u0007@\b\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0005\u0007F\b\u0007\n\u0007\f\u0007I\t\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0004\bQ\b\b\u000b"+
		"\b\f\bR\u0001\b\u0001\b\u0001\t\u0001\t\u0003\tY\b\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0003\t`\b\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0000\u0000\f\u0000\u0002\u0004"+
		"\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0000\u0001\u0001\u0000\u0004"+
		"\u0005e\u0000\u0018\u0001\u0000\u0000\u0000\u0002\u001e\u0001\u0000\u0000"+
		"\u0000\u0004#\u0001\u0000\u0000\u0000\u0006+\u0001\u0000\u0000\u0000\b"+
		"0\u0001\u0000\u0000\u0000\n2\u0001\u0000\u0000\u0000\f8\u0001\u0000\u0000"+
		"\u0000\u000e:\u0001\u0000\u0000\u0000\u0010P\u0001\u0000\u0000\u0000\u0012"+
		"_\u0001\u0000\u0000\u0000\u0014a\u0001\u0000\u0000\u0000\u0016e\u0001"+
		"\u0000\u0000\u0000\u0018\u0019\u0003\u0004\u0002\u0000\u0019\u001a\u0003"+
		"\u0002\u0001\u0000\u001a\u001b\u0003\u0006\u0003\u0000\u001b\u001c\u0006"+
		"\u0000\uffff\uffff\u0000\u001c\u001d\u0005\u0000\u0000\u0001\u001d\u0001"+
		"\u0001\u0000\u0000\u0000\u001e\u001f\u0005\u0002\u0000\u0000\u001f \u0003"+
		"\f\u0006\u0000 !\u0005\b\u0000\u0000!\"\u0006\u0001\uffff\uffff\u0000"+
		"\"\u0003\u0001\u0000\u0000\u0000#$\u0005\u0001\u0000\u0000$%\u0003\f\u0006"+
		"\u0000%&\u0005\b\u0000\u0000&\'\u0006\u0002\uffff\uffff\u0000\'\u0005"+
		"\u0001\u0000\u0000\u0000(*\u0003\b\u0004\u0000)(\u0001\u0000\u0000\u0000"+
		"*-\u0001\u0000\u0000\u0000+)\u0001\u0000\u0000\u0000+,\u0001\u0000\u0000"+
		"\u0000,\u0007\u0001\u0000\u0000\u0000-+\u0001\u0000\u0000\u0000.1\u0003"+
		"\n\u0005\u0000/1\u0003\u000e\u0007\u00000.\u0001\u0000\u0000\u00000/\u0001"+
		"\u0000\u0000\u00001\t\u0001\u0000\u0000\u000023\u0003\f\u0006\u000034"+
		"\u0005\u0007\u0000\u000045\u0005\u0010\u0000\u000056\u0005\b\u0000\u0000"+
		"67\u0006\u0005\uffff\uffff\u00007\u000b\u0001\u0000\u0000\u000089\u0007"+
		"\u0000\u0000\u00009\r\u0001\u0000\u0000\u0000:<\u0003\f\u0006\u0000;="+
		"\u0003\u0016\u000b\u0000<;\u0001\u0000\u0000\u0000<=\u0001\u0000\u0000"+
		"\u0000=?\u0001\u0000\u0000\u0000>@\u0003\u0014\n\u0000?>\u0001\u0000\u0000"+
		"\u0000?@\u0001\u0000\u0000\u0000@A\u0001\u0000\u0000\u0000AB\u0005\u0007"+
		"\u0000\u0000BG\u0003\u0010\b\u0000CD\u0005\u0006\u0000\u0000DF\u0003\u0010"+
		"\b\u0000EC\u0001\u0000\u0000\u0000FI\u0001\u0000\u0000\u0000GE\u0001\u0000"+
		"\u0000\u0000GH\u0001\u0000\u0000\u0000HJ\u0001\u0000\u0000\u0000IG\u0001"+
		"\u0000\u0000\u0000JK\u0005\b\u0000\u0000KL\u0006\u0007\uffff\uffff\u0000"+
		"L\u000f\u0001\u0000\u0000\u0000MN\u0003\u0012\t\u0000NO\u0006\b\uffff"+
		"\uffff\u0000OQ\u0001\u0000\u0000\u0000PM\u0001\u0000\u0000\u0000QR\u0001"+
		"\u0000\u0000\u0000RP\u0001\u0000\u0000\u0000RS\u0001\u0000\u0000\u0000"+
		"ST\u0001\u0000\u0000\u0000TU\u0006\b\uffff\uffff\u0000U\u0011\u0001\u0000"+
		"\u0000\u0000VX\u0005\u0005\u0000\u0000WY\u0003\u0016\u000b\u0000XW\u0001"+
		"\u0000\u0000\u0000XY\u0001\u0000\u0000\u0000YZ\u0001\u0000\u0000\u0000"+
		"Z`\u0006\t\uffff\uffff\u0000[\\\u0005\u0004\u0000\u0000\\`\u0006\t\uffff"+
		"\uffff\u0000]^\u0005\u000e\u0000\u0000^`\u0006\t\uffff\uffff\u0000_V\u0001"+
		"\u0000\u0000\u0000_[\u0001\u0000\u0000\u0000_]\u0001\u0000\u0000\u0000"+
		"`\u0013\u0001\u0000\u0000\u0000ab\u0005\u0003\u0000\u0000bc\u0005\u000f"+
		"\u0000\u0000cd\u0006\n\uffff\uffff\u0000d\u0015\u0001\u0000\u0000\u0000"+
		"ef\u0005\u000f\u0000\u0000fg\u0006\u000b\uffff\uffff\u0000g\u0017\u0001"+
		"\u0000\u0000\u0000\b+0<?GRX_";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}