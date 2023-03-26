// Generated from java-escape by ANTLR 4.11.1
package gen;

    import java.util.*;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link PythonToCParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface PythonToCVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link PythonToCParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(PythonToCParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonToCParser#commands}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommands(PythonToCParser.CommandsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonToCParser#inner_commands}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInner_commands(PythonToCParser.Inner_commandsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonToCParser#tabulation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTabulation(PythonToCParser.TabulationContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonToCParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommand(PythonToCParser.CommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonToCParser#while_rule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile_rule(PythonToCParser.While_ruleContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonToCParser#if_rule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_rule(PythonToCParser.If_ruleContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonToCParser#else_rule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElse_rule(PythonToCParser.Else_ruleContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonToCParser#elif}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElif(PythonToCParser.ElifContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonToCParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(PythonToCParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonToCParser#print}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(PythonToCParser.PrintContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonToCParser#input}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInput(PythonToCParser.InputContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonToCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(PythonToCParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonToCParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(PythonToCParser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonToCParser#exprContinue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprContinue(PythonToCParser.ExprContinueContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonToCParser#binaryOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryOp(PythonToCParser.BinaryOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonToCParser#unaryOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOp(PythonToCParser.UnaryOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonToCParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(PythonToCParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonToCParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(PythonToCParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonToCParser#bool}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool(PythonToCParser.BoolContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonToCParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(PythonToCParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonToCParser#spaces}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpaces(PythonToCParser.SpacesContext ctx);
}