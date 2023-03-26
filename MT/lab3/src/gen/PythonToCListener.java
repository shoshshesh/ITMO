// Generated from java-escape by ANTLR 4.11.1
package gen;

    import java.util.*;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PythonToCParser}.
 */
public interface PythonToCListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PythonToCParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(PythonToCParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonToCParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(PythonToCParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonToCParser#commands}.
	 * @param ctx the parse tree
	 */
	void enterCommands(PythonToCParser.CommandsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonToCParser#commands}.
	 * @param ctx the parse tree
	 */
	void exitCommands(PythonToCParser.CommandsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonToCParser#inner_commands}.
	 * @param ctx the parse tree
	 */
	void enterInner_commands(PythonToCParser.Inner_commandsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonToCParser#inner_commands}.
	 * @param ctx the parse tree
	 */
	void exitInner_commands(PythonToCParser.Inner_commandsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonToCParser#tabulation}.
	 * @param ctx the parse tree
	 */
	void enterTabulation(PythonToCParser.TabulationContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonToCParser#tabulation}.
	 * @param ctx the parse tree
	 */
	void exitTabulation(PythonToCParser.TabulationContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonToCParser#command}.
	 * @param ctx the parse tree
	 */
	void enterCommand(PythonToCParser.CommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonToCParser#command}.
	 * @param ctx the parse tree
	 */
	void exitCommand(PythonToCParser.CommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonToCParser#while_rule}.
	 * @param ctx the parse tree
	 */
	void enterWhile_rule(PythonToCParser.While_ruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonToCParser#while_rule}.
	 * @param ctx the parse tree
	 */
	void exitWhile_rule(PythonToCParser.While_ruleContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonToCParser#if_rule}.
	 * @param ctx the parse tree
	 */
	void enterIf_rule(PythonToCParser.If_ruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonToCParser#if_rule}.
	 * @param ctx the parse tree
	 */
	void exitIf_rule(PythonToCParser.If_ruleContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonToCParser#else_rule}.
	 * @param ctx the parse tree
	 */
	void enterElse_rule(PythonToCParser.Else_ruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonToCParser#else_rule}.
	 * @param ctx the parse tree
	 */
	void exitElse_rule(PythonToCParser.Else_ruleContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonToCParser#elif}.
	 * @param ctx the parse tree
	 */
	void enterElif(PythonToCParser.ElifContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonToCParser#elif}.
	 * @param ctx the parse tree
	 */
	void exitElif(PythonToCParser.ElifContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonToCParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(PythonToCParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonToCParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(PythonToCParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonToCParser#print}.
	 * @param ctx the parse tree
	 */
	void enterPrint(PythonToCParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonToCParser#print}.
	 * @param ctx the parse tree
	 */
	void exitPrint(PythonToCParser.PrintContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonToCParser#input}.
	 * @param ctx the parse tree
	 */
	void enterInput(PythonToCParser.InputContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonToCParser#input}.
	 * @param ctx the parse tree
	 */
	void exitInput(PythonToCParser.InputContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonToCParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(PythonToCParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonToCParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(PythonToCParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonToCParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(PythonToCParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonToCParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(PythonToCParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonToCParser#exprContinue}.
	 * @param ctx the parse tree
	 */
	void enterExprContinue(PythonToCParser.ExprContinueContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonToCParser#exprContinue}.
	 * @param ctx the parse tree
	 */
	void exitExprContinue(PythonToCParser.ExprContinueContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonToCParser#binaryOp}.
	 * @param ctx the parse tree
	 */
	void enterBinaryOp(PythonToCParser.BinaryOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonToCParser#binaryOp}.
	 * @param ctx the parse tree
	 */
	void exitBinaryOp(PythonToCParser.BinaryOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonToCParser#unaryOp}.
	 * @param ctx the parse tree
	 */
	void enterUnaryOp(PythonToCParser.UnaryOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonToCParser#unaryOp}.
	 * @param ctx the parse tree
	 */
	void exitUnaryOp(PythonToCParser.UnaryOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonToCParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(PythonToCParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonToCParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(PythonToCParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonToCParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(PythonToCParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonToCParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(PythonToCParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonToCParser#bool}.
	 * @param ctx the parse tree
	 */
	void enterBool(PythonToCParser.BoolContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonToCParser#bool}.
	 * @param ctx the parse tree
	 */
	void exitBool(PythonToCParser.BoolContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonToCParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(PythonToCParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonToCParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(PythonToCParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonToCParser#spaces}.
	 * @param ctx the parse tree
	 */
	void enterSpaces(PythonToCParser.SpacesContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonToCParser#spaces}.
	 * @param ctx the parse tree
	 */
	void exitSpaces(PythonToCParser.SpacesContext ctx);
}