// Generated from java-escape by ANTLR 4.11.1
package gen;

    import java.util.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class PythonToCParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.11.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, WHILE=4, IF=5, ELIF=6, ELSE=7, COLON=8, PLUS=9, 
		MINUS=10, MUL=11, DIV=12, OR=13, AND=14, NOT=15, L_BRACKET=16, R_BRACKET=17, 
		EQ=18, LT=19, GT=20, INT=21, FLOAT=22, BOOL=23, TRUE=24, FALSE=25, INPUT=26, 
		PRINT=27, VAR=28, VAR_START_CHAR=29, INT_N=30, FLOAT_N=31, WS=32;
	public static final int
		RULE_program = 0, RULE_commands = 1, RULE_inner_commands = 2, RULE_tabulation = 3, 
		RULE_command = 4, RULE_while_rule = 5, RULE_if_rule = 6, RULE_else_rule = 7, 
		RULE_elif = 8, RULE_assignment = 9, RULE_print = 10, RULE_input = 11, 
		RULE_expr = 12, RULE_atom = 13, RULE_exprContinue = 14, RULE_binaryOp = 15, 
		RULE_unaryOp = 16, RULE_number = 17, RULE_variable = 18, RULE_bool = 19, 
		RULE_type = 20, RULE_spaces = 21;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "commands", "inner_commands", "tabulation", "command", "while_rule", 
			"if_rule", "else_rule", "elif", "assignment", "print", "input", "expr", 
			"atom", "exprContinue", "binaryOp", "unaryOp", "number", "variable", 
			"bool", "type", "spaces"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'    '", "'!'", "' '", "'while'", "'if'", "'elif'", "'else'", 
			"':'", "'+'", "'-'", "'*'", "'/'", "'or'", "'and'", "'not'", "'('", "')'", 
			"'='", "'<'", "'>'", "'int'", "'float'", "'bool'", "'True'", "'False'", 
			"'input()'", "'print'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, "WHILE", "IF", "ELIF", "ELSE", "COLON", "PLUS", 
			"MINUS", "MUL", "DIV", "OR", "AND", "NOT", "L_BRACKET", "R_BRACKET", 
			"EQ", "LT", "GT", "INT", "FLOAT", "BOOL", "TRUE", "FALSE", "INPUT", "PRINT", 
			"VAR", "VAR_START_CHAR", "INT_N", "FLOAT_N", "WS"
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

	public PythonToCParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public StringBuilder cProgram;
		public CommandsContext commands;
		public CommandsContext commands() {
			return getRuleContext(CommandsContext.class,0);
		}
		public SpacesContext spaces() {
			return getRuleContext(SpacesContext.class,0);
		}
		public TerminalNode EOF() { return getToken(PythonToCParser.EOF, 0); }
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonToCVisitor ) return ((PythonToCVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);

		        Map<String, String> typeOfVars = new HashMap<>();
		        StringBuilder result = new StringBuilder();
		        ((ProgramContext)_localctx).cProgram =  result.append("\nint main() {\n");
		        String tabs = "\t";
		    
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			((ProgramContext)_localctx).commands = commands(typeOfVars, result, tabs);
			setState(45);
			spaces();
			setState(46);
			match(EOF);

			        result.append(((ProgramContext)_localctx).commands.toC);
			    
			}
			_ctx.stop = _input.LT(-1);

			        for (Map.Entry<String, String> varType : typeOfVars.entrySet()) {
			            result.insert(0, varType.getValue() + " " + varType.getKey() + ";\n");
			        }
			        result.insert(0, "#include <stdio.h>\n\n");
			        result.append("}");
			    
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
	public static class CommandsContext extends ParserRuleContext {
		public Map<String, String> typeOfVars;
		public StringBuilder result;
		public String tabs;
		public String toC;
		public CommandContext command;
		public CommandsContext commands;
		public CommandContext command() {
			return getRuleContext(CommandContext.class,0);
		}
		public CommandsContext commands() {
			return getRuleContext(CommandsContext.class,0);
		}
		public CommandsContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public CommandsContext(ParserRuleContext parent, int invokingState, Map<String, String> typeOfVars, StringBuilder result, String tabs) {
			super(parent, invokingState);
			this.typeOfVars = typeOfVars;
			this.result = result;
			this.tabs = tabs;
		}
		@Override public int getRuleIndex() { return RULE_commands; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).enterCommands(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).exitCommands(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonToCVisitor ) return ((PythonToCVisitor<? extends T>)visitor).visitCommands(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CommandsContext commands(Map<String, String> typeOfVars,StringBuilder result,String tabs) throws RecognitionException {
		CommandsContext _localctx = new CommandsContext(_ctx, getState(), typeOfVars, result, tabs);
		enterRule(_localctx, 2, RULE_commands);
		try {
			setState(54);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WHILE:
			case IF:
			case PRINT:
			case VAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(49);
				((CommandsContext)_localctx).command = command(typeOfVars, result, tabs);
				setState(50);
				((CommandsContext)_localctx).commands = commands(typeOfVars, result, tabs);

				        ((CommandsContext)_localctx).toC =  tabs + ((CommandsContext)_localctx).command.toC + "\n" + ((CommandsContext)_localctx).commands.toC;
				    
				}
				break;
			case EOF:
			case T__2:
				enterOuterAlt(_localctx, 2);
				{

				        ((CommandsContext)_localctx).toC =  "";
				    
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
	public static class Inner_commandsContext extends ParserRuleContext {
		public Map<String, String> typeOfVars;
		public StringBuilder result;
		public String tabs;
		public String toC;
		public TabulationContext tabulation;
		public CommandContext command;
		public Inner_commandsContext inner_commands;
		public TabulationContext tabulation() {
			return getRuleContext(TabulationContext.class,0);
		}
		public CommandContext command() {
			return getRuleContext(CommandContext.class,0);
		}
		public Inner_commandsContext inner_commands() {
			return getRuleContext(Inner_commandsContext.class,0);
		}
		public Inner_commandsContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public Inner_commandsContext(ParserRuleContext parent, int invokingState, Map<String, String> typeOfVars, StringBuilder result, String tabs) {
			super(parent, invokingState);
			this.typeOfVars = typeOfVars;
			this.result = result;
			this.tabs = tabs;
		}
		@Override public int getRuleIndex() { return RULE_inner_commands; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).enterInner_commands(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).exitInner_commands(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonToCVisitor ) return ((PythonToCVisitor<? extends T>)visitor).visitInner_commands(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Inner_commandsContext inner_commands(Map<String, String> typeOfVars,StringBuilder result,String tabs) throws RecognitionException {
		Inner_commandsContext _localctx = new Inner_commandsContext(_ctx, getState(), typeOfVars, result, tabs);
		enterRule(_localctx, 4, RULE_inner_commands);
		try {
			setState(62);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(56);
				((Inner_commandsContext)_localctx).tabulation = tabulation();
				setState(57);
				((Inner_commandsContext)_localctx).command = command(typeOfVars, result, tabs);
				setState(58);
				((Inner_commandsContext)_localctx).inner_commands = inner_commands(typeOfVars, result, tabs);

				        ((Inner_commandsContext)_localctx).toC =  (((Inner_commandsContext)_localctx).tabulation!=null?_input.getText(((Inner_commandsContext)_localctx).tabulation.start,((Inner_commandsContext)_localctx).tabulation.stop):null) + tabs + ((Inner_commandsContext)_localctx).command.toC + "\n" + ((Inner_commandsContext)_localctx).inner_commands.toC;
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{

				        ((Inner_commandsContext)_localctx).toC =  "";
				    
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
	public static class TabulationContext extends ParserRuleContext {
		public TabulationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tabulation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).enterTabulation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).exitTabulation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonToCVisitor ) return ((PythonToCVisitor<? extends T>)visitor).visitTabulation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TabulationContext tabulation() throws RecognitionException {
		TabulationContext _localctx = new TabulationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_tabulation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(64);
				match(T__0);
				}
				}
				setState(67); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__0 );
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
	public static class CommandContext extends ParserRuleContext {
		public Map<String, String> typeOfVars;
		public StringBuilder result;
		public String tabs;
		public String toC;
		public AssignmentContext assignment;
		public PrintContext print;
		public If_ruleContext if_rule;
		public While_ruleContext while_rule;
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public PrintContext print() {
			return getRuleContext(PrintContext.class,0);
		}
		public If_ruleContext if_rule() {
			return getRuleContext(If_ruleContext.class,0);
		}
		public While_ruleContext while_rule() {
			return getRuleContext(While_ruleContext.class,0);
		}
		public CommandContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public CommandContext(ParserRuleContext parent, int invokingState, Map<String, String> typeOfVars, StringBuilder result, String tabs) {
			super(parent, invokingState);
			this.typeOfVars = typeOfVars;
			this.result = result;
			this.tabs = tabs;
		}
		@Override public int getRuleIndex() { return RULE_command; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).enterCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).exitCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonToCVisitor ) return ((PythonToCVisitor<? extends T>)visitor).visitCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CommandContext command(Map<String, String> typeOfVars,StringBuilder result,String tabs) throws RecognitionException {
		CommandContext _localctx = new CommandContext(_ctx, getState(), typeOfVars, result, tabs);
		enterRule(_localctx, 8, RULE_command);
		try {
			setState(81);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(69);
				((CommandContext)_localctx).assignment = assignment(typeOfVars, result, tabs);

				        ((CommandContext)_localctx).toC =  ((CommandContext)_localctx).assignment.toC;
				    
				}
				break;
			case PRINT:
				enterOuterAlt(_localctx, 2);
				{
				setState(72);
				((CommandContext)_localctx).print = print(typeOfVars, result, tabs);

				        ((CommandContext)_localctx).toC =  ((CommandContext)_localctx).print.toC;
				    
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 3);
				{
				setState(75);
				((CommandContext)_localctx).if_rule = if_rule(typeOfVars, result, tabs);

				        ((CommandContext)_localctx).toC =  ((CommandContext)_localctx).if_rule.toC;
				    
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 4);
				{
				setState(78);
				((CommandContext)_localctx).while_rule = while_rule(typeOfVars, result, tabs);

				        ((CommandContext)_localctx).toC =  ((CommandContext)_localctx).while_rule.toC;
				    
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
	public static class While_ruleContext extends ParserRuleContext {
		public Map<String, String> typeOfVars;
		public StringBuilder result;
		public String tabs;
		public String toC;
		public ExprContext expr;
		public Inner_commandsContext inner_commands;
		public TerminalNode WHILE() { return getToken(PythonToCParser.WHILE, 0); }
		public List<SpacesContext> spaces() {
			return getRuleContexts(SpacesContext.class);
		}
		public SpacesContext spaces(int i) {
			return getRuleContext(SpacesContext.class,i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode COLON() { return getToken(PythonToCParser.COLON, 0); }
		public Inner_commandsContext inner_commands() {
			return getRuleContext(Inner_commandsContext.class,0);
		}
		public While_ruleContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public While_ruleContext(ParserRuleContext parent, int invokingState, Map<String, String> typeOfVars, StringBuilder result, String tabs) {
			super(parent, invokingState);
			this.typeOfVars = typeOfVars;
			this.result = result;
			this.tabs = tabs;
		}
		@Override public int getRuleIndex() { return RULE_while_rule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).enterWhile_rule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).exitWhile_rule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonToCVisitor ) return ((PythonToCVisitor<? extends T>)visitor).visitWhile_rule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final While_ruleContext while_rule(Map<String, String> typeOfVars,StringBuilder result,String tabs) throws RecognitionException {
		While_ruleContext _localctx = new While_ruleContext(_ctx, getState(), typeOfVars, result, tabs);
		enterRule(_localctx, 10, RULE_while_rule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			match(WHILE);
			setState(84);
			spaces();
			setState(85);
			((While_ruleContext)_localctx).expr = expr(typeOfVars);
			setState(86);
			spaces();
			setState(87);
			match(COLON);
			setState(88);
			((While_ruleContext)_localctx).inner_commands = inner_commands(typeOfVars, result, tabs);

			        ((While_ruleContext)_localctx).toC =  "while (" + ((While_ruleContext)_localctx).expr.toC + ") {\n" + ((While_ruleContext)_localctx).inner_commands.toC + tabs + "}";
			    
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
	public static class If_ruleContext extends ParserRuleContext {
		public Map<String, String> typeOfVars;
		public StringBuilder result;
		public String tabs;
		public String toC;
		public ExprContext expr;
		public Inner_commandsContext inner_commands;
		public ElifContext elif;
		public Else_ruleContext else_rule;
		public TerminalNode IF() { return getToken(PythonToCParser.IF, 0); }
		public List<SpacesContext> spaces() {
			return getRuleContexts(SpacesContext.class);
		}
		public SpacesContext spaces(int i) {
			return getRuleContext(SpacesContext.class,i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode COLON() { return getToken(PythonToCParser.COLON, 0); }
		public Inner_commandsContext inner_commands() {
			return getRuleContext(Inner_commandsContext.class,0);
		}
		public ElifContext elif() {
			return getRuleContext(ElifContext.class,0);
		}
		public Else_ruleContext else_rule() {
			return getRuleContext(Else_ruleContext.class,0);
		}
		public If_ruleContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public If_ruleContext(ParserRuleContext parent, int invokingState, Map<String, String> typeOfVars, StringBuilder result, String tabs) {
			super(parent, invokingState);
			this.typeOfVars = typeOfVars;
			this.result = result;
			this.tabs = tabs;
		}
		@Override public int getRuleIndex() { return RULE_if_rule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).enterIf_rule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).exitIf_rule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonToCVisitor ) return ((PythonToCVisitor<? extends T>)visitor).visitIf_rule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final If_ruleContext if_rule(Map<String, String> typeOfVars,StringBuilder result,String tabs) throws RecognitionException {
		If_ruleContext _localctx = new If_ruleContext(_ctx, getState(), typeOfVars, result, tabs);
		enterRule(_localctx, 12, RULE_if_rule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			match(IF);
			setState(92);
			spaces();
			setState(93);
			((If_ruleContext)_localctx).expr = expr(typeOfVars);
			setState(94);
			spaces();
			setState(95);
			match(COLON);
			setState(96);
			((If_ruleContext)_localctx).inner_commands = inner_commands(typeOfVars, result, tabs);
			setState(97);
			((If_ruleContext)_localctx).elif = elif(typeOfVars, result, tabs);
			setState(98);
			((If_ruleContext)_localctx).else_rule = else_rule(typeOfVars, result, tabs);

			        ((If_ruleContext)_localctx).toC =  "if (" + ((If_ruleContext)_localctx).expr.toC + ") {\n" + ((If_ruleContext)_localctx).inner_commands.toC + tabs + "} " + ((If_ruleContext)_localctx).elif.toC + ((If_ruleContext)_localctx).else_rule.toC;
			    
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
	public static class Else_ruleContext extends ParserRuleContext {
		public Map<String, String> typeOfVars;
		public StringBuilder result;
		public String tabs;
		public String toC;
		public Inner_commandsContext inner_commands;
		public TerminalNode ELSE() { return getToken(PythonToCParser.ELSE, 0); }
		public List<SpacesContext> spaces() {
			return getRuleContexts(SpacesContext.class);
		}
		public SpacesContext spaces(int i) {
			return getRuleContext(SpacesContext.class,i);
		}
		public TerminalNode COLON() { return getToken(PythonToCParser.COLON, 0); }
		public Inner_commandsContext inner_commands() {
			return getRuleContext(Inner_commandsContext.class,0);
		}
		public Else_ruleContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public Else_ruleContext(ParserRuleContext parent, int invokingState, Map<String, String> typeOfVars, StringBuilder result, String tabs) {
			super(parent, invokingState);
			this.typeOfVars = typeOfVars;
			this.result = result;
			this.tabs = tabs;
		}
		@Override public int getRuleIndex() { return RULE_else_rule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).enterElse_rule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).exitElse_rule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonToCVisitor ) return ((PythonToCVisitor<? extends T>)visitor).visitElse_rule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Else_ruleContext else_rule(Map<String, String> typeOfVars,StringBuilder result,String tabs) throws RecognitionException {
		Else_ruleContext _localctx = new Else_ruleContext(_ctx, getState(), typeOfVars, result, tabs);
		enterRule(_localctx, 14, RULE_else_rule);
		try {
			setState(109);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(101);
				match(ELSE);
				setState(102);
				spaces();
				setState(103);
				match(COLON);
				setState(104);
				spaces();
				setState(105);
				((Else_ruleContext)_localctx).inner_commands = inner_commands(typeOfVars, result, tabs);

				        ((Else_ruleContext)_localctx).toC =  "else {\n" + ((Else_ruleContext)_localctx).inner_commands.toC + tabs + "}";
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{

				        ((Else_ruleContext)_localctx).toC =  "";
				    
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
	public static class ElifContext extends ParserRuleContext {
		public Map<String, String> typeOfVars;
		public StringBuilder result;
		public String tabs;
		public String toC;
		public ExprContext expr;
		public Inner_commandsContext inner_commands;
		public ElifContext elif;
		public TerminalNode ELIF() { return getToken(PythonToCParser.ELIF, 0); }
		public List<SpacesContext> spaces() {
			return getRuleContexts(SpacesContext.class);
		}
		public SpacesContext spaces(int i) {
			return getRuleContext(SpacesContext.class,i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode COLON() { return getToken(PythonToCParser.COLON, 0); }
		public Inner_commandsContext inner_commands() {
			return getRuleContext(Inner_commandsContext.class,0);
		}
		public ElifContext elif() {
			return getRuleContext(ElifContext.class,0);
		}
		public ElifContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ElifContext(ParserRuleContext parent, int invokingState, Map<String, String> typeOfVars, StringBuilder result, String tabs) {
			super(parent, invokingState);
			this.typeOfVars = typeOfVars;
			this.result = result;
			this.tabs = tabs;
		}
		@Override public int getRuleIndex() { return RULE_elif; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).enterElif(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).exitElif(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonToCVisitor ) return ((PythonToCVisitor<? extends T>)visitor).visitElif(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElifContext elif(Map<String, String> typeOfVars,StringBuilder result,String tabs) throws RecognitionException {
		ElifContext _localctx = new ElifContext(_ctx, getState(), typeOfVars, result, tabs);
		enterRule(_localctx, 16, RULE_elif);
		try {
			setState(121);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(111);
				match(ELIF);
				setState(112);
				spaces();
				setState(113);
				((ElifContext)_localctx).expr = expr(typeOfVars);
				setState(114);
				spaces();
				setState(115);
				match(COLON);
				setState(116);
				((ElifContext)_localctx).inner_commands = inner_commands(typeOfVars, result, tabs);
				setState(117);
				((ElifContext)_localctx).elif = elif(typeOfVars, result, tabs);

				        ((ElifContext)_localctx).toC =  "else if (" + ((ElifContext)_localctx).expr.toC + ") {\n" + ((ElifContext)_localctx).inner_commands.toC + tabs + "} " + ((ElifContext)_localctx).elif.toC;
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{

				        ((ElifContext)_localctx).toC =  "";
				    
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
	public static class AssignmentContext extends ParserRuleContext {
		public Map<String, String> typeOfVars;
		public StringBuilder result;
		public String tabs;
		public String toC;
		public VariableContext variable;
		public ExprContext expr;
		public InputContext input;
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public List<SpacesContext> spaces() {
			return getRuleContexts(SpacesContext.class);
		}
		public SpacesContext spaces(int i) {
			return getRuleContext(SpacesContext.class,i);
		}
		public TerminalNode EQ() { return getToken(PythonToCParser.EQ, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public InputContext input() {
			return getRuleContext(InputContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public AssignmentContext(ParserRuleContext parent, int invokingState, Map<String, String> typeOfVars, StringBuilder result, String tabs) {
			super(parent, invokingState);
			this.typeOfVars = typeOfVars;
			this.result = result;
			this.tabs = tabs;
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).exitAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonToCVisitor ) return ((PythonToCVisitor<? extends T>)visitor).visitAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentContext assignment(Map<String, String> typeOfVars,StringBuilder result,String tabs) throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState(), typeOfVars, result, tabs);
		enterRule(_localctx, 18, RULE_assignment);
		try {
			setState(137);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(123);
				((AssignmentContext)_localctx).variable = variable();
				setState(124);
				spaces();
				setState(125);
				match(EQ);
				setState(126);
				spaces();
				setState(127);
				((AssignmentContext)_localctx).expr = expr(typeOfVars);

				        ((AssignmentContext)_localctx).toC =  (((AssignmentContext)_localctx).variable!=null?_input.getText(((AssignmentContext)_localctx).variable.start,((AssignmentContext)_localctx).variable.stop):null) + " = " + ((AssignmentContext)_localctx).expr.toC + ";";
				        if (!typeOfVars.containsKey((((AssignmentContext)_localctx).variable!=null?_input.getText(((AssignmentContext)_localctx).variable.start,((AssignmentContext)_localctx).variable.stop):null))) {
				            typeOfVars.put((((AssignmentContext)_localctx).variable!=null?_input.getText(((AssignmentContext)_localctx).variable.start,((AssignmentContext)_localctx).variable.stop):null), ((AssignmentContext)_localctx).expr.typeC);
				        }
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(130);
				((AssignmentContext)_localctx).variable = variable();
				setState(131);
				spaces();
				setState(132);
				match(EQ);
				setState(133);
				spaces();
				setState(134);
				((AssignmentContext)_localctx).input = input();

				        ((AssignmentContext)_localctx).toC =  "scanf(\"" + ((AssignmentContext)_localctx).input.formatType + "\", &" + (((AssignmentContext)_localctx).variable!=null?_input.getText(((AssignmentContext)_localctx).variable.start,((AssignmentContext)_localctx).variable.stop):null) + ");";
				        if (!typeOfVars.containsKey((((AssignmentContext)_localctx).variable!=null?_input.getText(((AssignmentContext)_localctx).variable.start,((AssignmentContext)_localctx).variable.stop):null))) {
				            typeOfVars.put((((AssignmentContext)_localctx).variable!=null?_input.getText(((AssignmentContext)_localctx).variable.start,((AssignmentContext)_localctx).variable.stop):null), ((AssignmentContext)_localctx).input.typeC);
				        }
				    
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
	public static class PrintContext extends ParserRuleContext {
		public Map<String, String> typeOfVars;
		public StringBuilder result;
		public String tabs;
		public String toC;
		public ExprContext expr;
		public TerminalNode PRINT() { return getToken(PythonToCParser.PRINT, 0); }
		public List<SpacesContext> spaces() {
			return getRuleContexts(SpacesContext.class);
		}
		public SpacesContext spaces(int i) {
			return getRuleContext(SpacesContext.class,i);
		}
		public TerminalNode L_BRACKET() { return getToken(PythonToCParser.L_BRACKET, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode R_BRACKET() { return getToken(PythonToCParser.R_BRACKET, 0); }
		public PrintContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public PrintContext(ParserRuleContext parent, int invokingState, Map<String, String> typeOfVars, StringBuilder result, String tabs) {
			super(parent, invokingState);
			this.typeOfVars = typeOfVars;
			this.result = result;
			this.tabs = tabs;
		}
		@Override public int getRuleIndex() { return RULE_print; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).enterPrint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).exitPrint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonToCVisitor ) return ((PythonToCVisitor<? extends T>)visitor).visitPrint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrintContext print(Map<String, String> typeOfVars,StringBuilder result,String tabs) throws RecognitionException {
		PrintContext _localctx = new PrintContext(_ctx, getState(), typeOfVars, result, tabs);
		enterRule(_localctx, 20, RULE_print);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
			match(PRINT);
			setState(140);
			spaces();
			setState(141);
			match(L_BRACKET);
			setState(142);
			spaces();
			setState(143);
			((PrintContext)_localctx).expr = expr(typeOfVars);
			setState(144);
			spaces();
			setState(145);
			match(R_BRACKET);
			setState(146);
			spaces();

			        ((PrintContext)_localctx).toC =  "printf(\"" + ((PrintContext)_localctx).expr.formatType + "\\n\", " + ((PrintContext)_localctx).expr.toC + ");";
			    
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
	public static class InputContext extends ParserRuleContext {
		public String typeC;
		public String formatType;
		public TypeContext type;
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<SpacesContext> spaces() {
			return getRuleContexts(SpacesContext.class);
		}
		public SpacesContext spaces(int i) {
			return getRuleContext(SpacesContext.class,i);
		}
		public TerminalNode L_BRACKET() { return getToken(PythonToCParser.L_BRACKET, 0); }
		public TerminalNode INPUT() { return getToken(PythonToCParser.INPUT, 0); }
		public TerminalNode R_BRACKET() { return getToken(PythonToCParser.R_BRACKET, 0); }
		public InputContext input() {
			return getRuleContext(InputContext.class,0);
		}
		public InputContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_input; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).enterInput(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).exitInput(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonToCVisitor ) return ((PythonToCVisitor<? extends T>)visitor).visitInput(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InputContext input() throws RecognitionException {
		InputContext _localctx = new InputContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_input);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				setState(149);
				((InputContext)_localctx).type = type();
				setState(150);
				spaces();
				setState(151);
				match(L_BRACKET);
				setState(152);
				spaces();
				setState(153);
				match(INPUT);
				setState(154);
				spaces();
				setState(155);
				match(R_BRACKET);
				setState(156);
				spaces();
				}
				break;
			case 2:
				{
				setState(158);
				((InputContext)_localctx).type = type();
				setState(159);
				spaces();
				setState(160);
				match(L_BRACKET);
				setState(161);
				spaces();
				setState(162);
				input();
				setState(163);
				spaces();
				setState(164);
				match(R_BRACKET);
				setState(165);
				spaces();
				}
				break;
			}

			        if (((InputContext)_localctx).type.typeC.equals("int")) {
			            ((InputContext)_localctx).typeC =  "int";
			            ((InputContext)_localctx).formatType =  "%d";
			        } else {
			            ((InputContext)_localctx).typeC =  "double";
			            ((InputContext)_localctx).formatType =  "%lf";
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
	public static class ExprContext extends ParserRuleContext {
		public Map<String, String> typeOfVars;
		public String toC;
		public String typeC;
		public String formatType;
		public AtomContext atom;
		public ExprContinueContext exprContinue;
		public List<SpacesContext> spaces() {
			return getRuleContexts(SpacesContext.class);
		}
		public SpacesContext spaces(int i) {
			return getRuleContext(SpacesContext.class,i);
		}
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public ExprContinueContext exprContinue() {
			return getRuleContext(ExprContinueContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ExprContext(ParserRuleContext parent, int invokingState, Map<String, String> typeOfVars) {
			super(parent, invokingState);
			this.typeOfVars = typeOfVars;
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonToCVisitor ) return ((PythonToCVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr(Map<String, String> typeOfVars) throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState(), typeOfVars);
		enterRule(_localctx, 24, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
			spaces();
			setState(172);
			((ExprContext)_localctx).atom = atom(typeOfVars);
			setState(173);
			spaces();
			setState(174);
			((ExprContext)_localctx).exprContinue = exprContinue(typeOfVars);
			setState(175);
			spaces();

			        if (((ExprContext)_localctx).atom.typeC.equals("int") && ((ExprContext)_localctx).exprContinue.op.equals("/")) {
			            ((ExprContext)_localctx).toC =  "(double) " + ((ExprContext)_localctx).atom.toC + ((ExprContext)_localctx).exprContinue.toC;
			        } else {
			            ((ExprContext)_localctx).toC =  ((ExprContext)_localctx).atom.toC + ((ExprContext)_localctx).exprContinue.toC;
			        }
			        if (((ExprContext)_localctx).atom.typeC.equals("double") || ((ExprContext)_localctx).exprContinue.typeC.equals("double") || ((ExprContext)_localctx).exprContinue.op.equals("/")) {
			            ((ExprContext)_localctx).typeC =  "double";
			            ((ExprContext)_localctx).formatType =  "%lf";
			        } else if (((ExprContext)_localctx).atom.typeC.equals("_Bool") || ((ExprContext)_localctx).exprContinue.typeC.equals("_Bool")
			            || ((ExprContext)_localctx).exprContinue.op.equals("==") || ((ExprContext)_localctx).exprContinue.op.equals("!=") || ((ExprContext)_localctx).exprContinue.op.equals("<")
			            || ((ExprContext)_localctx).exprContinue.op.equals(">") || ((ExprContext)_localctx).exprContinue.op.equals("<=") || ((ExprContext)_localctx).exprContinue.op.equals(">=")
			            || ((ExprContext)_localctx).exprContinue.op.equals("||") || ((ExprContext)_localctx).exprContinue.op.equals("&&")) {
			            ((ExprContext)_localctx).typeC =  "_Bool";
			            ((ExprContext)_localctx).formatType =  "%d";
			        } else {
			            ((ExprContext)_localctx).typeC =  "int";
			            ((ExprContext)_localctx).formatType =  "%d";
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
	public static class AtomContext extends ParserRuleContext {
		public Map<String, String> typeOfVars;
		public String toC;
		public String typeC;
		public String formatType;
		public UnaryOpContext unaryOp;
		public ExprContext expr;
		public NumberContext number;
		public BoolContext bool;
		public VariableContext variable;
		public TypeContext type;
		public UnaryOpContext unaryOp() {
			return getRuleContext(UnaryOpContext.class,0);
		}
		public List<SpacesContext> spaces() {
			return getRuleContexts(SpacesContext.class);
		}
		public SpacesContext spaces(int i) {
			return getRuleContext(SpacesContext.class,i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public BoolContext bool() {
			return getRuleContext(BoolContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode L_BRACKET() { return getToken(PythonToCParser.L_BRACKET, 0); }
		public TerminalNode R_BRACKET() { return getToken(PythonToCParser.R_BRACKET, 0); }
		public AtomContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public AtomContext(ParserRuleContext parent, int invokingState, Map<String, String> typeOfVars) {
			super(parent, invokingState);
			this.typeOfVars = typeOfVars;
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).exitAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonToCVisitor ) return ((PythonToCVisitor<? extends T>)visitor).visitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom(Map<String, String> typeOfVars) throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState(), typeOfVars);
		enterRule(_localctx, 26, RULE_atom);
		try {
			setState(208);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MINUS:
			case NOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(178);
				((AtomContext)_localctx).unaryOp = unaryOp();
				setState(179);
				spaces();
				setState(180);
				((AtomContext)_localctx).expr = expr(typeOfVars);

				        ((AtomContext)_localctx).toC =  ((AtomContext)_localctx).unaryOp.op + ((AtomContext)_localctx).expr.toC;
				        ((AtomContext)_localctx).typeC =  ((AtomContext)_localctx).expr.typeC;
				        ((AtomContext)_localctx).formatType =  ((AtomContext)_localctx).expr.formatType;
				    
				}
				break;
			case INT_N:
			case FLOAT_N:
				enterOuterAlt(_localctx, 2);
				{
				setState(183);
				((AtomContext)_localctx).number = number();

				        ((AtomContext)_localctx).toC =  (((AtomContext)_localctx).number!=null?_input.getText(((AtomContext)_localctx).number.start,((AtomContext)_localctx).number.stop):null);
				        if ((((AtomContext)_localctx).number!=null?_input.getText(((AtomContext)_localctx).number.start,((AtomContext)_localctx).number.stop):null).contains(".")) {
				            ((AtomContext)_localctx).typeC =  "double";
				            ((AtomContext)_localctx).formatType =  "%lf";
				        } else {
				            ((AtomContext)_localctx).typeC =  "int";
				            ((AtomContext)_localctx).formatType =  "%d";
				        }
				    
				}
				break;
			case TRUE:
			case FALSE:
				enterOuterAlt(_localctx, 3);
				{
				setState(186);
				((AtomContext)_localctx).bool = bool();

				        if ((((AtomContext)_localctx).bool!=null?_input.getText(((AtomContext)_localctx).bool.start,((AtomContext)_localctx).bool.stop):null).equals("True")) {
				            ((AtomContext)_localctx).toC =  "1";
				        } else {
				            ((AtomContext)_localctx).toC =  "0";
				        }
				        ((AtomContext)_localctx).typeC =  "_Bool";
				        ((AtomContext)_localctx).formatType =  "%d";
				    
				}
				break;
			case VAR:
				enterOuterAlt(_localctx, 4);
				{
				setState(189);
				((AtomContext)_localctx).variable = variable();

				        ((AtomContext)_localctx).toC =  (((AtomContext)_localctx).variable!=null?_input.getText(((AtomContext)_localctx).variable.start,((AtomContext)_localctx).variable.stop):null);
				        if (typeOfVars.get((((AtomContext)_localctx).variable!=null?_input.getText(((AtomContext)_localctx).variable.start,((AtomContext)_localctx).variable.stop):null)).equals("double")) {
				            ((AtomContext)_localctx).typeC =  "double";
				            ((AtomContext)_localctx).formatType =  "%lf";
				        } else if (typeOfVars.get((((AtomContext)_localctx).variable!=null?_input.getText(((AtomContext)_localctx).variable.start,((AtomContext)_localctx).variable.stop):null)).equals("int")) {
				            ((AtomContext)_localctx).typeC =  "int";
				            ((AtomContext)_localctx).formatType =  "%d";
				        } else {
				            ((AtomContext)_localctx).typeC =  "_Bool";
				            ((AtomContext)_localctx).formatType =  "%d";
				        }
				    
				}
				break;
			case INT:
			case FLOAT:
			case BOOL:
				enterOuterAlt(_localctx, 5);
				{
				setState(192);
				((AtomContext)_localctx).type = type();
				setState(193);
				spaces();
				setState(194);
				match(L_BRACKET);
				setState(195);
				spaces();
				setState(196);
				((AtomContext)_localctx).expr = expr(typeOfVars);
				setState(197);
				spaces();
				setState(198);
				match(R_BRACKET);

				        ((AtomContext)_localctx).toC =  "(" + ((AtomContext)_localctx).type.typeC + ") " + "(" + ((AtomContext)_localctx).expr.toC + ")";
				        ((AtomContext)_localctx).typeC =  ((AtomContext)_localctx).type.typeC;
				        ((AtomContext)_localctx).formatType =  ((AtomContext)_localctx).type.formatType;
				    
				}
				break;
			case L_BRACKET:
				enterOuterAlt(_localctx, 6);
				{
				setState(201);
				match(L_BRACKET);
				setState(202);
				spaces();
				setState(203);
				((AtomContext)_localctx).expr = expr(typeOfVars);
				setState(204);
				spaces();
				setState(205);
				match(R_BRACKET);

				        ((AtomContext)_localctx).toC =  "(" + ((AtomContext)_localctx).expr.toC + ")";
				        ((AtomContext)_localctx).typeC =  ((AtomContext)_localctx).expr.typeC;
				        ((AtomContext)_localctx).formatType =  ((AtomContext)_localctx).expr.formatType;
				    
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
	public static class ExprContinueContext extends ParserRuleContext {
		public Map<String, String> typeOfVars;
		public String toC;
		public String typeC;
		public String op;
		public BinaryOpContext binaryOp;
		public ExprContext expr;
		public List<SpacesContext> spaces() {
			return getRuleContexts(SpacesContext.class);
		}
		public SpacesContext spaces(int i) {
			return getRuleContext(SpacesContext.class,i);
		}
		public BinaryOpContext binaryOp() {
			return getRuleContext(BinaryOpContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ExprContinueContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ExprContinueContext(ParserRuleContext parent, int invokingState, Map<String, String> typeOfVars) {
			super(parent, invokingState);
			this.typeOfVars = typeOfVars;
		}
		@Override public int getRuleIndex() { return RULE_exprContinue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).enterExprContinue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).exitExprContinue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonToCVisitor ) return ((PythonToCVisitor<? extends T>)visitor).visitExprContinue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContinueContext exprContinue(Map<String, String> typeOfVars) throws RecognitionException {
		ExprContinueContext _localctx = new ExprContinueContext(_ctx, getState(), typeOfVars);
		enterRule(_localctx, 28, RULE_exprContinue);
		try {
			setState(218);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(210);
				spaces();
				setState(211);
				((ExprContinueContext)_localctx).binaryOp = binaryOp();
				setState(212);
				spaces();
				setState(213);
				((ExprContinueContext)_localctx).expr = expr(typeOfVars);
				setState(214);
				spaces();

				        ((ExprContinueContext)_localctx).toC =  " " + ((ExprContinueContext)_localctx).binaryOp.op + " " + ((ExprContinueContext)_localctx).expr.toC;
				        ((ExprContinueContext)_localctx).typeC =  ((ExprContinueContext)_localctx).expr.typeC;
				        ((ExprContinueContext)_localctx).op =  ((ExprContinueContext)_localctx).binaryOp.op;
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{

				        ((ExprContinueContext)_localctx).toC =  "";
				        ((ExprContinueContext)_localctx).typeC =  "";
				        ((ExprContinueContext)_localctx).op =  "";
				    
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
	public static class BinaryOpContext extends ParserRuleContext {
		public String op;
		public TerminalNode PLUS() { return getToken(PythonToCParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(PythonToCParser.MINUS, 0); }
		public TerminalNode MUL() { return getToken(PythonToCParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(PythonToCParser.DIV, 0); }
		public TerminalNode OR() { return getToken(PythonToCParser.OR, 0); }
		public TerminalNode AND() { return getToken(PythonToCParser.AND, 0); }
		public List<TerminalNode> EQ() { return getTokens(PythonToCParser.EQ); }
		public TerminalNode EQ(int i) {
			return getToken(PythonToCParser.EQ, i);
		}
		public TerminalNode LT() { return getToken(PythonToCParser.LT, 0); }
		public TerminalNode GT() { return getToken(PythonToCParser.GT, 0); }
		public BinaryOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binaryOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).enterBinaryOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).exitBinaryOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonToCVisitor ) return ((PythonToCVisitor<? extends T>)visitor).visitBinaryOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BinaryOpContext binaryOp() throws RecognitionException {
		BinaryOpContext _localctx = new BinaryOpContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_binaryOp);
		try {
			setState(248);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(220);
				match(PLUS);
				 ((BinaryOpContext)_localctx).op =  "+";  
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(222);
				match(MINUS);
				 ((BinaryOpContext)_localctx).op =  "-";  
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(224);
				match(MUL);
				 ((BinaryOpContext)_localctx).op =  "*";  
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(226);
				match(DIV);
				 ((BinaryOpContext)_localctx).op =  "/";  
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(228);
				match(OR);
				 ((BinaryOpContext)_localctx).op =  "||"; 
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(230);
				match(AND);
				 ((BinaryOpContext)_localctx).op =  "&&"; 
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(232);
				match(EQ);
				setState(233);
				match(EQ);
				 ((BinaryOpContext)_localctx).op =  "=="; 
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(235);
				match(T__1);
				setState(236);
				match(EQ);
				 ((BinaryOpContext)_localctx).op =  "!="; 
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(238);
				match(LT);
				 ((BinaryOpContext)_localctx).op =  "<";  
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(240);
				match(GT);
				 ((BinaryOpContext)_localctx).op =  ">";  
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(242);
				match(LT);
				setState(243);
				match(EQ);
				 ((BinaryOpContext)_localctx).op =  "<="; 
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(245);
				match(GT);
				setState(246);
				match(EQ);
				 ((BinaryOpContext)_localctx).op =  ">="; 
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
	public static class UnaryOpContext extends ParserRuleContext {
		public String op;
		public TerminalNode MINUS() { return getToken(PythonToCParser.MINUS, 0); }
		public TerminalNode NOT() { return getToken(PythonToCParser.NOT, 0); }
		public UnaryOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).enterUnaryOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).exitUnaryOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonToCVisitor ) return ((PythonToCVisitor<? extends T>)visitor).visitUnaryOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryOpContext unaryOp() throws RecognitionException {
		UnaryOpContext _localctx = new UnaryOpContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_unaryOp);
		try {
			setState(254);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MINUS:
				enterOuterAlt(_localctx, 1);
				{
				setState(250);
				match(MINUS);
				 ((UnaryOpContext)_localctx).op =  "-"; 
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 2);
				{
				setState(252);
				match(NOT);
				 ((UnaryOpContext)_localctx).op =  "!"; 
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
	public static class NumberContext extends ParserRuleContext {
		public TerminalNode FLOAT_N() { return getToken(PythonToCParser.FLOAT_N, 0); }
		public TerminalNode INT_N() { return getToken(PythonToCParser.INT_N, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).exitNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonToCVisitor ) return ((PythonToCVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256);
			_la = _input.LA(1);
			if ( !(_la==INT_N || _la==FLOAT_N) ) {
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
	public static class VariableContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(PythonToCParser.VAR, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).exitVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonToCVisitor ) return ((PythonToCVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(258);
			match(VAR);
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
		public TerminalNode TRUE() { return getToken(PythonToCParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(PythonToCParser.FALSE, 0); }
		public BoolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bool; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).enterBool(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).exitBool(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonToCVisitor ) return ((PythonToCVisitor<? extends T>)visitor).visitBool(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolContext bool() throws RecognitionException {
		BoolContext _localctx = new BoolContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_bool);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(260);
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

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public String typeC;
		public String formatType;
		public TerminalNode INT() { return getToken(PythonToCParser.INT, 0); }
		public TerminalNode FLOAT() { return getToken(PythonToCParser.FLOAT, 0); }
		public TerminalNode BOOL() { return getToken(PythonToCParser.BOOL, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonToCVisitor ) return ((PythonToCVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_type);
		try {
			setState(268);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(262);
				match(INT);

				        ((TypeContext)_localctx).typeC =  "int";
				        ((TypeContext)_localctx).formatType =  "%d";
				    
				}
				break;
			case FLOAT:
				enterOuterAlt(_localctx, 2);
				{
				setState(264);
				match(FLOAT);

				        ((TypeContext)_localctx).typeC =  "double";
				        ((TypeContext)_localctx).formatType =  "%lf";
				    
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 3);
				{
				setState(266);
				match(BOOL);

				        ((TypeContext)_localctx).typeC =  "_Bool";
				        ((TypeContext)_localctx).formatType =  "%d";
				    
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
	public static class SpacesContext extends ParserRuleContext {
		public SpacesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_spaces; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).enterSpaces(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonToCListener ) ((PythonToCListener)listener).exitSpaces(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonToCVisitor ) return ((PythonToCVisitor<? extends T>)visitor).visitSpaces(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SpacesContext spaces() throws RecognitionException {
		SpacesContext _localctx = new SpacesContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_spaces);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(273);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(270);
					match(T__2);
					}
					} 
				}
				setState(275);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
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

	public static final String _serializedATN =
		"\u0004\u0001 \u0115\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u00017\b\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0003\u0002?\b\u0002\u0001\u0003\u0004\u0003B\b\u0003\u000b\u0003\f\u0003"+
		"C\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0003\u0004R\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007n\b\u0007"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0003\bz\b\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003"+
		"\t\u008a\b\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0003\u000b\u00a8\b\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0003\r\u00d1\b\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u00db\b\u000e\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u00f9\b\u000f\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u00ff\b\u0010\u0001"+
		"\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0003"+
		"\u0014\u010d\b\u0014\u0001\u0015\u0005\u0015\u0110\b\u0015\n\u0015\f\u0015"+
		"\u0113\t\u0015\u0001\u0015\u0000\u0000\u0016\u0000\u0002\u0004\u0006\b"+
		"\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*\u0000"+
		"\u0002\u0001\u0000\u001e\u001f\u0001\u0000\u0018\u0019\u011d\u0000,\u0001"+
		"\u0000\u0000\u0000\u00026\u0001\u0000\u0000\u0000\u0004>\u0001\u0000\u0000"+
		"\u0000\u0006A\u0001\u0000\u0000\u0000\bQ\u0001\u0000\u0000\u0000\nS\u0001"+
		"\u0000\u0000\u0000\f[\u0001\u0000\u0000\u0000\u000em\u0001\u0000\u0000"+
		"\u0000\u0010y\u0001\u0000\u0000\u0000\u0012\u0089\u0001\u0000\u0000\u0000"+
		"\u0014\u008b\u0001\u0000\u0000\u0000\u0016\u00a7\u0001\u0000\u0000\u0000"+
		"\u0018\u00ab\u0001\u0000\u0000\u0000\u001a\u00d0\u0001\u0000\u0000\u0000"+
		"\u001c\u00da\u0001\u0000\u0000\u0000\u001e\u00f8\u0001\u0000\u0000\u0000"+
		" \u00fe\u0001\u0000\u0000\u0000\"\u0100\u0001\u0000\u0000\u0000$\u0102"+
		"\u0001\u0000\u0000\u0000&\u0104\u0001\u0000\u0000\u0000(\u010c\u0001\u0000"+
		"\u0000\u0000*\u0111\u0001\u0000\u0000\u0000,-\u0003\u0002\u0001\u0000"+
		"-.\u0003*\u0015\u0000./\u0005\u0000\u0000\u0001/0\u0006\u0000\uffff\uffff"+
		"\u00000\u0001\u0001\u0000\u0000\u000012\u0003\b\u0004\u000023\u0003\u0002"+
		"\u0001\u000034\u0006\u0001\uffff\uffff\u000047\u0001\u0000\u0000\u0000"+
		"57\u0006\u0001\uffff\uffff\u000061\u0001\u0000\u0000\u000065\u0001\u0000"+
		"\u0000\u00007\u0003\u0001\u0000\u0000\u000089\u0003\u0006\u0003\u0000"+
		"9:\u0003\b\u0004\u0000:;\u0003\u0004\u0002\u0000;<\u0006\u0002\uffff\uffff"+
		"\u0000<?\u0001\u0000\u0000\u0000=?\u0006\u0002\uffff\uffff\u0000>8\u0001"+
		"\u0000\u0000\u0000>=\u0001\u0000\u0000\u0000?\u0005\u0001\u0000\u0000"+
		"\u0000@B\u0005\u0001\u0000\u0000A@\u0001\u0000\u0000\u0000BC\u0001\u0000"+
		"\u0000\u0000CA\u0001\u0000\u0000\u0000CD\u0001\u0000\u0000\u0000D\u0007"+
		"\u0001\u0000\u0000\u0000EF\u0003\u0012\t\u0000FG\u0006\u0004\uffff\uffff"+
		"\u0000GR\u0001\u0000\u0000\u0000HI\u0003\u0014\n\u0000IJ\u0006\u0004\uffff"+
		"\uffff\u0000JR\u0001\u0000\u0000\u0000KL\u0003\f\u0006\u0000LM\u0006\u0004"+
		"\uffff\uffff\u0000MR\u0001\u0000\u0000\u0000NO\u0003\n\u0005\u0000OP\u0006"+
		"\u0004\uffff\uffff\u0000PR\u0001\u0000\u0000\u0000QE\u0001\u0000\u0000"+
		"\u0000QH\u0001\u0000\u0000\u0000QK\u0001\u0000\u0000\u0000QN\u0001\u0000"+
		"\u0000\u0000R\t\u0001\u0000\u0000\u0000ST\u0005\u0004\u0000\u0000TU\u0003"+
		"*\u0015\u0000UV\u0003\u0018\f\u0000VW\u0003*\u0015\u0000WX\u0005\b\u0000"+
		"\u0000XY\u0003\u0004\u0002\u0000YZ\u0006\u0005\uffff\uffff\u0000Z\u000b"+
		"\u0001\u0000\u0000\u0000[\\\u0005\u0005\u0000\u0000\\]\u0003*\u0015\u0000"+
		"]^\u0003\u0018\f\u0000^_\u0003*\u0015\u0000_`\u0005\b\u0000\u0000`a\u0003"+
		"\u0004\u0002\u0000ab\u0003\u0010\b\u0000bc\u0003\u000e\u0007\u0000cd\u0006"+
		"\u0006\uffff\uffff\u0000d\r\u0001\u0000\u0000\u0000ef\u0005\u0007\u0000"+
		"\u0000fg\u0003*\u0015\u0000gh\u0005\b\u0000\u0000hi\u0003*\u0015\u0000"+
		"ij\u0003\u0004\u0002\u0000jk\u0006\u0007\uffff\uffff\u0000kn\u0001\u0000"+
		"\u0000\u0000ln\u0006\u0007\uffff\uffff\u0000me\u0001\u0000\u0000\u0000"+
		"ml\u0001\u0000\u0000\u0000n\u000f\u0001\u0000\u0000\u0000op\u0005\u0006"+
		"\u0000\u0000pq\u0003*\u0015\u0000qr\u0003\u0018\f\u0000rs\u0003*\u0015"+
		"\u0000st\u0005\b\u0000\u0000tu\u0003\u0004\u0002\u0000uv\u0003\u0010\b"+
		"\u0000vw\u0006\b\uffff\uffff\u0000wz\u0001\u0000\u0000\u0000xz\u0006\b"+
		"\uffff\uffff\u0000yo\u0001\u0000\u0000\u0000yx\u0001\u0000\u0000\u0000"+
		"z\u0011\u0001\u0000\u0000\u0000{|\u0003$\u0012\u0000|}\u0003*\u0015\u0000"+
		"}~\u0005\u0012\u0000\u0000~\u007f\u0003*\u0015\u0000\u007f\u0080\u0003"+
		"\u0018\f\u0000\u0080\u0081\u0006\t\uffff\uffff\u0000\u0081\u008a\u0001"+
		"\u0000\u0000\u0000\u0082\u0083\u0003$\u0012\u0000\u0083\u0084\u0003*\u0015"+
		"\u0000\u0084\u0085\u0005\u0012\u0000\u0000\u0085\u0086\u0003*\u0015\u0000"+
		"\u0086\u0087\u0003\u0016\u000b\u0000\u0087\u0088\u0006\t\uffff\uffff\u0000"+
		"\u0088\u008a\u0001\u0000\u0000\u0000\u0089{\u0001\u0000\u0000\u0000\u0089"+
		"\u0082\u0001\u0000\u0000\u0000\u008a\u0013\u0001\u0000\u0000\u0000\u008b"+
		"\u008c\u0005\u001b\u0000\u0000\u008c\u008d\u0003*\u0015\u0000\u008d\u008e"+
		"\u0005\u0010\u0000\u0000\u008e\u008f\u0003*\u0015\u0000\u008f\u0090\u0003"+
		"\u0018\f\u0000\u0090\u0091\u0003*\u0015\u0000\u0091\u0092\u0005\u0011"+
		"\u0000\u0000\u0092\u0093\u0003*\u0015\u0000\u0093\u0094\u0006\n\uffff"+
		"\uffff\u0000\u0094\u0015\u0001\u0000\u0000\u0000\u0095\u0096\u0003(\u0014"+
		"\u0000\u0096\u0097\u0003*\u0015\u0000\u0097\u0098\u0005\u0010\u0000\u0000"+
		"\u0098\u0099\u0003*\u0015\u0000\u0099\u009a\u0005\u001a\u0000\u0000\u009a"+
		"\u009b\u0003*\u0015\u0000\u009b\u009c\u0005\u0011\u0000\u0000\u009c\u009d"+
		"\u0003*\u0015\u0000\u009d\u00a8\u0001\u0000\u0000\u0000\u009e\u009f\u0003"+
		"(\u0014\u0000\u009f\u00a0\u0003*\u0015\u0000\u00a0\u00a1\u0005\u0010\u0000"+
		"\u0000\u00a1\u00a2\u0003*\u0015\u0000\u00a2\u00a3\u0003\u0016\u000b\u0000"+
		"\u00a3\u00a4\u0003*\u0015\u0000\u00a4\u00a5\u0005\u0011\u0000\u0000\u00a5"+
		"\u00a6\u0003*\u0015\u0000\u00a6\u00a8\u0001\u0000\u0000\u0000\u00a7\u0095"+
		"\u0001\u0000\u0000\u0000\u00a7\u009e\u0001\u0000\u0000\u0000\u00a8\u00a9"+
		"\u0001\u0000\u0000\u0000\u00a9\u00aa\u0006\u000b\uffff\uffff\u0000\u00aa"+
		"\u0017\u0001\u0000\u0000\u0000\u00ab\u00ac\u0003*\u0015\u0000\u00ac\u00ad"+
		"\u0003\u001a\r\u0000\u00ad\u00ae\u0003*\u0015\u0000\u00ae\u00af\u0003"+
		"\u001c\u000e\u0000\u00af\u00b0\u0003*\u0015\u0000\u00b0\u00b1\u0006\f"+
		"\uffff\uffff\u0000\u00b1\u0019\u0001\u0000\u0000\u0000\u00b2\u00b3\u0003"+
		" \u0010\u0000\u00b3\u00b4\u0003*\u0015\u0000\u00b4\u00b5\u0003\u0018\f"+
		"\u0000\u00b5\u00b6\u0006\r\uffff\uffff\u0000\u00b6\u00d1\u0001\u0000\u0000"+
		"\u0000\u00b7\u00b8\u0003\"\u0011\u0000\u00b8\u00b9\u0006\r\uffff\uffff"+
		"\u0000\u00b9\u00d1\u0001\u0000\u0000\u0000\u00ba\u00bb\u0003&\u0013\u0000"+
		"\u00bb\u00bc\u0006\r\uffff\uffff\u0000\u00bc\u00d1\u0001\u0000\u0000\u0000"+
		"\u00bd\u00be\u0003$\u0012\u0000\u00be\u00bf\u0006\r\uffff\uffff\u0000"+
		"\u00bf\u00d1\u0001\u0000\u0000\u0000\u00c0\u00c1\u0003(\u0014\u0000\u00c1"+
		"\u00c2\u0003*\u0015\u0000\u00c2\u00c3\u0005\u0010\u0000\u0000\u00c3\u00c4"+
		"\u0003*\u0015\u0000\u00c4\u00c5\u0003\u0018\f\u0000\u00c5\u00c6\u0003"+
		"*\u0015\u0000\u00c6\u00c7\u0005\u0011\u0000\u0000\u00c7\u00c8\u0006\r"+
		"\uffff\uffff\u0000\u00c8\u00d1\u0001\u0000\u0000\u0000\u00c9\u00ca\u0005"+
		"\u0010\u0000\u0000\u00ca\u00cb\u0003*\u0015\u0000\u00cb\u00cc\u0003\u0018"+
		"\f\u0000\u00cc\u00cd\u0003*\u0015\u0000\u00cd\u00ce\u0005\u0011\u0000"+
		"\u0000\u00ce\u00cf\u0006\r\uffff\uffff\u0000\u00cf\u00d1\u0001\u0000\u0000"+
		"\u0000\u00d0\u00b2\u0001\u0000\u0000\u0000\u00d0\u00b7\u0001\u0000\u0000"+
		"\u0000\u00d0\u00ba\u0001\u0000\u0000\u0000\u00d0\u00bd\u0001\u0000\u0000"+
		"\u0000\u00d0\u00c0\u0001\u0000\u0000\u0000\u00d0\u00c9\u0001\u0000\u0000"+
		"\u0000\u00d1\u001b\u0001\u0000\u0000\u0000\u00d2\u00d3\u0003*\u0015\u0000"+
		"\u00d3\u00d4\u0003\u001e\u000f\u0000\u00d4\u00d5\u0003*\u0015\u0000\u00d5"+
		"\u00d6\u0003\u0018\f\u0000\u00d6\u00d7\u0003*\u0015\u0000\u00d7\u00d8"+
		"\u0006\u000e\uffff\uffff\u0000\u00d8\u00db\u0001\u0000\u0000\u0000\u00d9"+
		"\u00db\u0006\u000e\uffff\uffff\u0000\u00da\u00d2\u0001\u0000\u0000\u0000"+
		"\u00da\u00d9\u0001\u0000\u0000\u0000\u00db\u001d\u0001\u0000\u0000\u0000"+
		"\u00dc\u00dd\u0005\t\u0000\u0000\u00dd\u00f9\u0006\u000f\uffff\uffff\u0000"+
		"\u00de\u00df\u0005\n\u0000\u0000\u00df\u00f9\u0006\u000f\uffff\uffff\u0000"+
		"\u00e0\u00e1\u0005\u000b\u0000\u0000\u00e1\u00f9\u0006\u000f\uffff\uffff"+
		"\u0000\u00e2\u00e3\u0005\f\u0000\u0000\u00e3\u00f9\u0006\u000f\uffff\uffff"+
		"\u0000\u00e4\u00e5\u0005\r\u0000\u0000\u00e5\u00f9\u0006\u000f\uffff\uffff"+
		"\u0000\u00e6\u00e7\u0005\u000e\u0000\u0000\u00e7\u00f9\u0006\u000f\uffff"+
		"\uffff\u0000\u00e8\u00e9\u0005\u0012\u0000\u0000\u00e9\u00ea\u0005\u0012"+
		"\u0000\u0000\u00ea\u00f9\u0006\u000f\uffff\uffff\u0000\u00eb\u00ec\u0005"+
		"\u0002\u0000\u0000\u00ec\u00ed\u0005\u0012\u0000\u0000\u00ed\u00f9\u0006"+
		"\u000f\uffff\uffff\u0000\u00ee\u00ef\u0005\u0013\u0000\u0000\u00ef\u00f9"+
		"\u0006\u000f\uffff\uffff\u0000\u00f0\u00f1\u0005\u0014\u0000\u0000\u00f1"+
		"\u00f9\u0006\u000f\uffff\uffff\u0000\u00f2\u00f3\u0005\u0013\u0000\u0000"+
		"\u00f3\u00f4\u0005\u0012\u0000\u0000\u00f4\u00f9\u0006\u000f\uffff\uffff"+
		"\u0000\u00f5\u00f6\u0005\u0014\u0000\u0000\u00f6\u00f7\u0005\u0012\u0000"+
		"\u0000\u00f7\u00f9\u0006\u000f\uffff\uffff\u0000\u00f8\u00dc\u0001\u0000"+
		"\u0000\u0000\u00f8\u00de\u0001\u0000\u0000\u0000\u00f8\u00e0\u0001\u0000"+
		"\u0000\u0000\u00f8\u00e2\u0001\u0000\u0000\u0000\u00f8\u00e4\u0001\u0000"+
		"\u0000\u0000\u00f8\u00e6\u0001\u0000\u0000\u0000\u00f8\u00e8\u0001\u0000"+
		"\u0000\u0000\u00f8\u00eb\u0001\u0000\u0000\u0000\u00f8\u00ee\u0001\u0000"+
		"\u0000\u0000\u00f8\u00f0\u0001\u0000\u0000\u0000\u00f8\u00f2\u0001\u0000"+
		"\u0000\u0000\u00f8\u00f5\u0001\u0000\u0000\u0000\u00f9\u001f\u0001\u0000"+
		"\u0000\u0000\u00fa\u00fb\u0005\n\u0000\u0000\u00fb\u00ff\u0006\u0010\uffff"+
		"\uffff\u0000\u00fc\u00fd\u0005\u000f\u0000\u0000\u00fd\u00ff\u0006\u0010"+
		"\uffff\uffff\u0000\u00fe\u00fa\u0001\u0000\u0000\u0000\u00fe\u00fc\u0001"+
		"\u0000\u0000\u0000\u00ff!\u0001\u0000\u0000\u0000\u0100\u0101\u0007\u0000"+
		"\u0000\u0000\u0101#\u0001\u0000\u0000\u0000\u0102\u0103\u0005\u001c\u0000"+
		"\u0000\u0103%\u0001\u0000\u0000\u0000\u0104\u0105\u0007\u0001\u0000\u0000"+
		"\u0105\'\u0001\u0000\u0000\u0000\u0106\u0107\u0005\u0015\u0000\u0000\u0107"+
		"\u010d\u0006\u0014\uffff\uffff\u0000\u0108\u0109\u0005\u0016\u0000\u0000"+
		"\u0109\u010d\u0006\u0014\uffff\uffff\u0000\u010a\u010b\u0005\u0017\u0000"+
		"\u0000\u010b\u010d\u0006\u0014\uffff\uffff\u0000\u010c\u0106\u0001\u0000"+
		"\u0000\u0000\u010c\u0108\u0001\u0000\u0000\u0000\u010c\u010a\u0001\u0000"+
		"\u0000\u0000\u010d)\u0001\u0000\u0000\u0000\u010e\u0110\u0005\u0003\u0000"+
		"\u0000\u010f\u010e\u0001\u0000\u0000\u0000\u0110\u0113\u0001\u0000\u0000"+
		"\u0000\u0111\u010f\u0001\u0000\u0000\u0000\u0111\u0112\u0001\u0000\u0000"+
		"\u0000\u0112+\u0001\u0000\u0000\u0000\u0113\u0111\u0001\u0000\u0000\u0000"+
		"\u000e6>CQmy\u0089\u00a7\u00d0\u00da\u00f8\u00fe\u010c\u0111";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}