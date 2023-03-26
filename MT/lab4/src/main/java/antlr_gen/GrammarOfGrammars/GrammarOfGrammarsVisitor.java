// Generated from java-escape by ANTLR 4.11.1
package antlr_gen.GrammarOfGrammars;

import grammar.Grammar;
import grammar.Rule;
import grammar.term.Code;
import grammar.term.NonTerminal;
import grammar.term.Term;
import grammar.term.Terminal;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GrammarOfGrammarsParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GrammarOfGrammarsVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GrammarOfGrammarsParser#grammarOfGrammars}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGrammarOfGrammars(GrammarOfGrammarsParser.GrammarOfGrammarsContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarOfGrammarsParser#startName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStartName(GrammarOfGrammarsParser.StartNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarOfGrammarsParser#grammarName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGrammarName(GrammarOfGrammarsParser.GrammarNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarOfGrammarsParser#rules}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRules(GrammarOfGrammarsParser.RulesContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarOfGrammarsParser#grammarRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGrammarRule(GrammarOfGrammarsParser.GrammarRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarOfGrammarsParser#terminalRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerminalRule(GrammarOfGrammarsParser.TerminalRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarOfGrammarsParser#name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName(GrammarOfGrammarsParser.NameContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarOfGrammarsParser#nonTerminalRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNonTerminalRule(GrammarOfGrammarsParser.NonTerminalRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarOfGrammarsParser#rightPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRightPart(GrammarOfGrammarsParser.RightPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarOfGrammarsParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(GrammarOfGrammarsParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarOfGrammarsParser#synAttr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSynAttr(GrammarOfGrammarsParser.SynAttrContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarOfGrammarsParser#heritableAttr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeritableAttr(GrammarOfGrammarsParser.HeritableAttrContext ctx);
}