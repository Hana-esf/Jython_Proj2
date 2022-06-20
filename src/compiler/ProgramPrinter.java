package compiler;
import gen.jythonListener;
import gen.jythonParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;

public class ProgramPrinter implements jythonListener {
    private int hashNumber = 0;
    private ArrayList<Hashtable<String, String>> items = new ArrayList<Hashtable<String, String>>();
    @Override
    public void enterProgram(jythonParser.ProgramContext ctx) {
        Hashtable<String, String> temp = new Hashtable<>();
        items.add(temp);
        System.out.println("---------program: 1 ---------");
        System.out.println(ctx.getChild(0).getText());
    }

    @Override
    public void exitProgram(jythonParser.ProgramContext ctx) {

    }

    @Override
    public void enterImportclass(jythonParser.ImportclassContext ctx) {
        items.get(0).put("import_" + ctx.CLASSNAME(), "import (name: " + ctx.CLASSNAME() + ")");
    }

    @Override
    public void exitImportclass(jythonParser.ImportclassContext ctx) {
    }

    @Override
    public void enterClassDef(jythonParser.ClassDefContext ctx) {
//        if(items.size() == 0){
//            Hashtable<String, String> temp = new Hashtable<>();
//            items.add(temp);
//        }
        String tempStr = "Class (name: " + ctx.CLASSNAME(0) + ")" + "(parent: ";
        for (int i = 1; i < ctx.CLASSNAME().size() - 1; i++) {
            tempStr += ctx.CLASSNAME(i) + ", ";
        }
        tempStr += ctx.CLASSNAME(ctx.CLASSNAME().size() - 1);
        tempStr += ")";
        items.get(hashNumber).put("Class_" + ctx.CLASSNAME(0),tempStr);
        System.out.println(printItem(items.get(hashNumber)));
    }

    @Override
    public void exitClassDef(jythonParser.ClassDefContext ctx) {

    }

    @Override
    public void enterClass_body(jythonParser.Class_bodyContext ctx) {
        if (ctx.getChild(0).getClass().getName().contains("Array")||ctx.getChild(0).getClass().getName().contains("Var")) {
            if(items.size() == 1){
                Hashtable<String, String> temp = new Hashtable<>();
                items.add(temp);
                hashNumber++;
            }
            String tmp = ctx.getChild(0).getChild(0).getText();
            String tempStr;
            if(tmp.equals("int") || tmp.equals("float") || tmp.equals("string") || tmp.equals("bool")) {
                tempStr = "Field ";
                tempStr += "(name: " + ctx.getChild(0).getChild(1).getText() +") (type: [ classType="+
                        ctx.getChild(0).getChild(0).getText() + ", isDefiend: ???";
                items.get(hashNumber).put("Field_" + ctx.getChild(0).getChild(1).getText(),tempStr);
                System.out.println(printItem(items.get(hashNumber)));
            }else if(ctx.getChild(0).getChild(1).getText().equals("[")) {
                tempStr = "ClassArrayField (name: " + ctx.getChild(0).getChild(4).getText() +") (type: [ classType="+
                        ctx.getChild(0).getChild(0).getText() + ", isDefiend: ???";
                items.get(hashNumber).put("Field_" + ctx.getChild(0).getChild(4).getText(),tempStr);
                System.out.println(printItem(items.get(hashNumber)));
            }else {
                tempStr = "ClassField ";
                tempStr += "(name: " + ctx.getChild(0).getChild(1).getText() +") (type: [ classType="+
                        ctx.getChild(0).getChild(0).getText() + ", isDefiend: ???";
                items.get(hashNumber).put("Field_" + ctx.getChild(0).getChild(1).getText(),tempStr);
                System.out.println(printItem(items.get(hashNumber)));
            }
        }
    }

    @Override
    public void exitClass_body(jythonParser.Class_bodyContext ctx) {

    }

    @Override
    public void enterVarDec(jythonParser.VarDecContext ctx) {

    }

    @Override
    public void exitVarDec(jythonParser.VarDecContext ctx) {

    }

    @Override
    public void enterArrayDec(jythonParser.ArrayDecContext ctx) {
    }

    @Override
    public void exitArrayDec(jythonParser.ArrayDecContext ctx) {

    }

    @Override
    public void enterMethodDec(jythonParser.MethodDecContext ctx) {
        if(items.size() == 1){
            Hashtable<String, String> temp = new Hashtable<>();
            items.add(temp);
            hashNumber++;
        }

        String tempStr = "Method (name : " + ctx.getChild(2).getText() + ") (return type: [" ;

        String tmp = ctx.getChild(1).getText();
        if(tmp.equals("void") || tmp.equals("int") || tmp.equals("float") || tmp.equals("string") || tmp.equals("bool"))
            tempStr += tmp;
        else
            tempStr += "class type = " + tmp;
        tempStr += "]) ";

        int counter = 4;
        if(!ctx.getChild(counter).getText().equals(")")){
            tempStr += "[parameter list: ";
            int camaCounter = 0;
            for (int i = 0; i < ctx.getChild(counter).getText().length(); i++) {
                if (ctx.getChild(counter).getText().charAt(i) == ',') {
                    camaCounter++;
                }
            }
            for(int i = 0, j = 0; i < 2 * camaCounter + 1; i++){
                if(ctx.getChild(counter).getChild(i).getText().equals(",")){
                    continue;
                }
                tempStr += "[type: "+ ctx.getChild(counter).getChild(i).getChild(0).getText() + ",index:" + (j + 1) + "]";
                j++;
                if(i <2 * camaCounter)
                    tempStr += ", ";
            }
            counter++;
            tempStr += "]";
        }
        items.get(hashNumber).put("Method_" + ctx.getChild(2).getText(),tempStr);
        System.out.println(printItem(items.get(hashNumber)));
    }


    @Override
    public void exitMethodDec(jythonParser.MethodDecContext ctx) {

    }

    @Override
    public void enterConstructor(jythonParser.ConstructorContext ctx) {
        if(items.size() == 1){
            Hashtable<String, String> temp = new Hashtable<>();
            items.add(temp);
            hashNumber++;
        }
        String tmp = ctx.getChild(3).getText();
        System.out.println(tmp);
        String tempStr = "Constructor (name : " + ctx.getChild(1).getText() + ") [parameter list: " ;
        int counter = 3;
        if(!ctx.getChild(counter).getText().equals(")")){
            int camaCounter = 0;
            for (int i = 0; i < ctx.getChild(counter).getText().length(); i++) {
                if (ctx.getChild(counter).getText().charAt(i) == ',') {
                    camaCounter++;
                }
            }
            for(int i = 0, j = 0; i < 2 * camaCounter + 1; i++){
                if(ctx.getChild(counter).getChild(i).getText().equals(",")){
                    continue;
                }
                tempStr += "[type: "+ ctx.getChild(counter).getChild(i).getChild(0).getText() + ",index:" + (j + 1) + "]";
                j++;
                if(i <2 * camaCounter)
                    tempStr += ", ";
            }
            counter++;
        }
        tempStr += "]";
        items.get(hashNumber).put("Constructor_" + ctx.getChild(1).getText(),tempStr);
        System.out.println(printItem(items.get(hashNumber)));

    }
    @Override
    public void exitConstructor(jythonParser.ConstructorContext ctx) {

    }

    @Override
    public void enterParameter(jythonParser.ParameterContext ctx) {

    }

    @Override
    public void exitParameter(jythonParser.ParameterContext ctx) {

    }

    @Override
    public void enterStatement(jythonParser.StatementContext ctx){

    }

    @Override
    public void exitStatement(jythonParser.StatementContext ctx) {
    }

    @Override
    public void enterReturn_statment(jythonParser.Return_statmentContext ctx) {

    }

    @Override
    public void exitReturn_statment(jythonParser.Return_statmentContext ctx) {

    }

    @Override
    public void enterCondition_list(jythonParser.Condition_listContext ctx) {

    }

    @Override
    public void exitCondition_list(jythonParser.Condition_listContext ctx) {

    }

    @Override
    public void enterCondition(jythonParser.ConditionContext ctx) {

    }

    @Override
    public void exitCondition(jythonParser.ConditionContext ctx) {

    }

    @Override
    public void enterIf_statment(jythonParser.If_statmentContext ctx) {

    }

    @Override
    public void exitIf_statment(jythonParser.If_statmentContext ctx) {

    }

    @Override
    public void enterWhile_statment(jythonParser.While_statmentContext ctx) {

    }

    @Override
    public void exitWhile_statment(jythonParser.While_statmentContext ctx) {

    }

    @Override
    public void enterIf_else_statment(jythonParser.If_else_statmentContext ctx) {

    }

    @Override
    public void exitIf_else_statment(jythonParser.If_else_statmentContext ctx) {

    }

    @Override
    public void enterPrint_statment(jythonParser.Print_statmentContext ctx) {

    }

    @Override
    public void exitPrint_statment(jythonParser.Print_statmentContext ctx) {

    }

    @Override
    public void enterFor_statment(jythonParser.For_statmentContext ctx) {

    }

    @Override
    public void exitFor_statment(jythonParser.For_statmentContext ctx) {

    }

    @Override
    public void enterMethod_call(jythonParser.Method_callContext ctx) {

    }

    @Override
    public void exitMethod_call(jythonParser.Method_callContext ctx) {

    }

    @Override
    public void enterAssignment(jythonParser.AssignmentContext ctx) {

    }

    @Override
    public void exitAssignment(jythonParser.AssignmentContext ctx) {

    }

    @Override
    public void enterExp(jythonParser.ExpContext ctx) {

    }

    @Override
    public void exitExp(jythonParser.ExpContext ctx) {

    }

    @Override
    public void enterPrefixexp(jythonParser.PrefixexpContext ctx) {

    }

    @Override
    public void exitPrefixexp(jythonParser.PrefixexpContext ctx) {

    }

    @Override
    public void enterArgs(jythonParser.ArgsContext ctx) {

    }

    @Override
    public void exitArgs(jythonParser.ArgsContext ctx) {

    }

    @Override
    public void enterExplist(jythonParser.ExplistContext ctx) {

    }

    @Override
    public void exitExplist(jythonParser.ExplistContext ctx) {

    }

    @Override
    public void enterArithmetic_operator(jythonParser.Arithmetic_operatorContext ctx) {

    }

    @Override
    public void exitArithmetic_operator(jythonParser.Arithmetic_operatorContext ctx) {

    }

    @Override
    public void enterRelational_operators(jythonParser.Relational_operatorsContext ctx) {

    }

    @Override
    public void exitRelational_operators(jythonParser.Relational_operatorsContext ctx) {

    }

    @Override
    public void enterAssignment_operators(jythonParser.Assignment_operatorsContext ctx) {

    }

    @Override
    public void exitAssignment_operators(jythonParser.Assignment_operatorsContext ctx) {

    }

    @Override
    public void visitTerminal(TerminalNode terminalNode) {

    }

    @Override
    public void visitErrorNode(ErrorNode errorNode) {

    }

    @Override
    public void enterEveryRule(ParserRuleContext parserRuleContext) {

    }

    @Override
    public void exitEveryRule(ParserRuleContext parserRuleContext) {

    }

//    public String toString(String name, int scopeNumber){
//        return "------------ " + name + " : " + scopeNumber + " -------------\n" +
//                printItem() + "---------------------------------------------\n";
//    }

    public String printItem(Hashtable<String, String> tempHash){

        String itemStr = "";
        for (Map.Entry<String,String> entry : tempHash.entrySet()) {
            itemStr += "Key = " + entry.getKey() + " | Value = " + entry.getValue() + "\n";
        }
        //System.out.println(tempHash.size());
//        while (e.hasMoreElements()) {
//            String key = e.nextElement();
//            itemStr += "key = " + key + " | Value = " + tempHash.get(key) + "\n";
//        }
        return itemStr;
    }
}
