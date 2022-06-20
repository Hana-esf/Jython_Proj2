package gen;// Generated from C:/Users/MelikaZ/IdeaProjects/Jython_2/grammar\jython.g4 by ANTLR 4.10.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link jythonParser}.
 */
public interface jythonListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link jythonParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(jythonParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(jythonParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#importclass}.
	 * @param ctx the parse tree
	 */
	void enterImportclass(jythonParser.ImportclassContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#importclass}.
	 * @param ctx the parse tree
	 */
	void exitImportclass(jythonParser.ImportclassContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#classDef}.
	 * @param ctx the parse tree
	 */
	void enterClassDef(jythonParser.ClassDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#classDef}.
	 * @param ctx the parse tree
	 */
	void exitClassDef(jythonParser.ClassDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#class_body}.
	 * @param ctx the parse tree
	 */
	void enterClass_body(jythonParser.Class_bodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#class_body}.
	 * @param ctx the parse tree
	 */
	void exitClass_body(jythonParser.Class_bodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#varDec}.
	 * @param ctx the parse tree
	 */
	void enterVarDec(jythonParser.VarDecContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#varDec}.
	 * @param ctx the parse tree
	 */
	void exitVarDec(jythonParser.VarDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#arrayDec}.
	 * @param ctx the parse tree
	 */
	void enterArrayDec(jythonParser.ArrayDecContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#arrayDec}.
	 * @param ctx the parse tree
	 */
	void exitArrayDec(jythonParser.ArrayDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#methodDec}.
	 * @param ctx the parse tree
	 */
	void enterMethodDec(jythonParser.MethodDecContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#methodDec}.
	 * @param ctx the parse tree
	 */
	void exitMethodDec(jythonParser.MethodDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#constructor}.
	 * @param ctx the parse tree
	 */
	void enterConstructor(jythonParser.ConstructorContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#constructor}.
	 * @param ctx the parse tree
	 */
	void exitConstructor(jythonParser.ConstructorContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(jythonParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(jythonParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(jythonParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(jythonParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#return_statment}.
	 * @param ctx the parse tree
	 */
	void enterReturn_statment(jythonParser.Return_statmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#return_statment}.
	 * @param ctx the parse tree
	 */
	void exitReturn_statment(jythonParser.Return_statmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#condition_list}.
	 * @param ctx the parse tree
	 */
	void enterCondition_list(jythonParser.Condition_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#condition_list}.
	 * @param ctx the parse tree
	 */
	void exitCondition_list(jythonParser.Condition_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(jythonParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(jythonParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#if_statment}.
	 * @param ctx the parse tree
	 */
	void enterIf_statment(jythonParser.If_statmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#if_statment}.
	 * @param ctx the parse tree
	 */
	void exitIf_statment(jythonParser.If_statmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#while_statment}.
	 * @param ctx the parse tree
	 */
	void enterWhile_statment(jythonParser.While_statmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#while_statment}.
	 * @param ctx the parse tree
	 */
	void exitWhile_statment(jythonParser.While_statmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#if_else_statment}.
	 * @param ctx the parse tree
	 */
	void enterIf_else_statment(jythonParser.If_else_statmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#if_else_statment}.
	 * @param ctx the parse tree
	 */
	void exitIf_else_statment(jythonParser.If_else_statmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#print_statment}.
	 * @param ctx the parse tree
	 */
	void enterPrint_statment(jythonParser.Print_statmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#print_statment}.
	 * @param ctx the parse tree
	 */
	void exitPrint_statment(jythonParser.Print_statmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#for_statment}.
	 * @param ctx the parse tree
	 */
	void enterFor_statment(jythonParser.For_statmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#for_statment}.
	 * @param ctx the parse tree
	 */
	void exitFor_statment(jythonParser.For_statmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#method_call}.
	 * @param ctx the parse tree
	 */
	void enterMethod_call(jythonParser.Method_callContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#method_call}.
	 * @param ctx the parse tree
	 */
	void exitMethod_call(jythonParser.Method_callContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(jythonParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(jythonParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExp(jythonParser.ExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExp(jythonParser.ExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#prefixexp}.
	 * @param ctx the parse tree
	 */
	void enterPrefixexp(jythonParser.PrefixexpContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#prefixexp}.
	 * @param ctx the parse tree
	 */
	void exitPrefixexp(jythonParser.PrefixexpContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs(jythonParser.ArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs(jythonParser.ArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#explist}.
	 * @param ctx the parse tree
	 */
	void enterExplist(jythonParser.ExplistContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#explist}.
	 * @param ctx the parse tree
	 */
	void exitExplist(jythonParser.ExplistContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#arithmetic_operator}.
	 * @param ctx the parse tree
	 */
	void enterArithmetic_operator(jythonParser.Arithmetic_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#arithmetic_operator}.
	 * @param ctx the parse tree
	 */
	void exitArithmetic_operator(jythonParser.Arithmetic_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#relational_operators}.
	 * @param ctx the parse tree
	 */
	void enterRelational_operators(jythonParser.Relational_operatorsContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#relational_operators}.
	 * @param ctx the parse tree
	 */
	void exitRelational_operators(jythonParser.Relational_operatorsContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#assignment_operators}.
	 * @param ctx the parse tree
	 */
	void enterAssignment_operators(jythonParser.Assignment_operatorsContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#assignment_operators}.
	 * @param ctx the parse tree
	 */
	void exitAssignment_operators(jythonParser.Assignment_operatorsContext ctx);
}