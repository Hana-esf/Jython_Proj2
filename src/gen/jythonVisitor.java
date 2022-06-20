package gen;// Generated from C:/Users/MelikaZ/IdeaProjects/Jython_2/grammar\jython.g4 by ANTLR 4.10.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link jythonParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface jythonVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link jythonParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(jythonParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#importclass}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportclass(jythonParser.ImportclassContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#classDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDef(jythonParser.ClassDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#class_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClass_body(jythonParser.Class_bodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#varDec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDec(jythonParser.VarDecContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#arrayDec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayDec(jythonParser.ArrayDecContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#methodDec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodDec(jythonParser.MethodDecContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#constructor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructor(jythonParser.ConstructorContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(jythonParser.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(jythonParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#return_statment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_statment(jythonParser.Return_statmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#condition_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition_list(jythonParser.Condition_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(jythonParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#if_statment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_statment(jythonParser.If_statmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#while_statment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile_statment(jythonParser.While_statmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#if_else_statment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_else_statment(jythonParser.If_else_statmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#print_statment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint_statment(jythonParser.Print_statmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#for_statment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor_statment(jythonParser.For_statmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#method_call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethod_call(jythonParser.Method_callContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(jythonParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp(jythonParser.ExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#prefixexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefixexp(jythonParser.PrefixexpContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgs(jythonParser.ArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#explist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExplist(jythonParser.ExplistContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#arithmetic_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmetic_operator(jythonParser.Arithmetic_operatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#relational_operators}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelational_operators(jythonParser.Relational_operatorsContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#assignment_operators}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment_operators(jythonParser.Assignment_operatorsContext ctx);
}