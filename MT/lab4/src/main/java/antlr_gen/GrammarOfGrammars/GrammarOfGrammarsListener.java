// Generated from java-escape by ANTLR 4.11.1
package antlr_gen.GrammarOfGrammars;

import grammar.Grammar;
import grammar.Rule;
import grammar.term.Code;
import grammar.term.NonTerminal;
import grammar.term.Term;
import grammar.term.Terminal;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GrammarOfGrammarsParser}.
 */
public interface GrammarOfGrammarsListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GrammarOfGrammarsParser#grammarOfGrammars}.
	 * @param ctx the parse tree
	 */
	void enterGrammarOfGrammars(GrammarOfGrammarsParser.GrammarOfGrammarsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarOfGrammarsParser#grammarOfGrammars}.
	 * @param ctx the parse tree
	 */
	void exitGrammarOfGrammars(GrammarOfGrammarsParser.GrammarOfGrammarsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarOfGrammarsParser#startName}.
	 * @param ctx the parse tree
	 */
	void enterStartName(GrammarOfGrammarsParser.StartNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarOfGrammarsParser#startName}.
	 * @param ctx the parse tree
	 */
	void exitStartName(GrammarOfGrammarsParser.StartNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarOfGrammarsParser#grammarName}.
	 * @param ctx the parse tree
	 */
	void enterGrammarName(GrammarOfGrammarsParser.GrammarNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarOfGrammarsParser#grammarName}.
	 * @param ctx the parse tree
	 */
	void exitGrammarName(GrammarOfGrammarsParser.GrammarNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarOfGrammarsParser#rules}.
	 * @param ctx the parse tree
	 */
	void enterRules(GrammarOfGrammarsParser.RulesContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarOfGrammarsParser#rules}.
	 * @param ctx the parse tree
	 */
	void exitRules(GrammarOfGrammarsParser.RulesContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarOfGrammarsParser#grammarRule}.
	 * @param ctx the parse tree
	 */
	void enterGrammarRule(GrammarOfGrammarsParser.GrammarRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarOfGrammarsParser#grammarRule}.
	 * @param ctx the parse tree
	 */
	void exitGrammarRule(GrammarOfGrammarsParser.GrammarRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarOfGrammarsParser#terminalRule}.
	 * @param ctx the parse tree
	 */
	void enterTerminalRule(GrammarOfGrammarsParser.TerminalRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarOfGrammarsParser#terminalRule}.
	 * @param ctx the parse tree
	 */
	void exitTerminalRule(GrammarOfGrammarsParser.TerminalRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarOfGrammarsParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(GrammarOfGrammarsParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarOfGrammarsParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(GrammarOfGrammarsParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarOfGrammarsParser#nonTerminalRule}.
	 * @param ctx the parse tree
	 */
	void enterNonTerminalRule(GrammarOfGrammarsParser.NonTerminalRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarOfGrammarsParser#nonTerminalRule}.
	 * @param ctx the parse tree
	 */
	void exitNonTerminalRule(GrammarOfGrammarsParser.NonTerminalRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarOfGrammarsParser#rightPart}.
	 * @param ctx the parse tree
	 */
	void enterRightPart(GrammarOfGrammarsParser.RightPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarOfGrammarsParser#rightPart}.
	 * @param ctx the parse tree
	 */
	void exitRightPart(GrammarOfGrammarsParser.RightPartContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarOfGrammarsParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(GrammarOfGrammarsParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarOfGrammarsParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(GrammarOfGrammarsParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarOfGrammarsParser#synAttr}.
	 * @param ctx the parse tree
	 */
	void enterSynAttr(GrammarOfGrammarsParser.SynAttrContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarOfGrammarsParser#synAttr}.
	 * @param ctx the parse tree
	 */
	void exitSynAttr(GrammarOfGrammarsParser.SynAttrContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarOfGrammarsParser#heritableAttr}.
	 * @param ctx the parse tree
	 */
	void enterHeritableAttr(GrammarOfGrammarsParser.HeritableAttrContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarOfGrammarsParser#heritableAttr}.
	 * @param ctx the parse tree
	 */
	void exitHeritableAttr(GrammarOfGrammarsParser.HeritableAttrContext ctx);
}