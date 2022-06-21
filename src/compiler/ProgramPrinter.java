package compiler;
import gen.jythonListener;
import gen.jythonParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;

public class ProgramPrinter implements jythonListener {
    private int hashNumber = 0;
    private int previousHashNum;
    String[] importedClasses = new String[10];
    ArrayList<String> blockStarts = new ArrayList();
    int numberOfimportedClasses = 0;
    private ArrayList<Hashtable<String, String>> items = new ArrayList<Hashtable<String, String>>();
    @Override
    public void enterProgram(jythonParser.ProgramContext ctx) {
        Hashtable<String, String> temp = new Hashtable<>();
        items.add(temp);
        blockStarts.add("---------program:" + ctx.start.getLine() + "---------");
    }

    @Override
    public void exitProgram(jythonParser.ProgramContext ctx) {
        printItem();
    }

    @Override
    public void enterImportclass(jythonParser.ImportclassContext ctx) {
        items.get(0).put("import_" + ctx.CLASSNAME(), "import (name: " + ctx.CLASSNAME() + ")");
        importedClasses[numberOfimportedClasses] = ctx.CLASSNAME().getText();
    }

    @Override
    public void exitImportclass(jythonParser.ImportclassContext ctx) {
    }

    @Override
    public void enterClassDef(jythonParser.ClassDefContext ctx) {
//        if(items.size() == 1){
//            Hashtable<String, String> temp = new Hashtable<>();
//            items.add(temp);
//        }
        String tempStr = "Class (name: " + ctx.CLASSNAME(0) + ")" + "(parent: ";
        for (int i = 1; i < ctx.CLASSNAME().size() - 1; i++) {
            tempStr += ctx.CLASSNAME(i) + ", ";
        }
        tempStr += ctx.CLASSNAME(ctx.CLASSNAME().size() - 1);
        tempStr += ")";
        items.get(0).put("Class_" + ctx.CLASSNAME(0),tempStr);
        blockStarts.add("---------" + ctx.CLASSNAME(0) + ":" + ctx.start.getLine() + "---------");
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
                        ctx.getChild(0).getChild(0).getText() + ", isDefiend: True]";
                items.get(hashNumber).put("Field_" + ctx.getChild(0).getChild(1).getText(),tempStr);
            }else if(ctx.getChild(0).getChild(1).getText().equals("[")) {
                tempStr = "ClassArrayField (name: " + ctx.getChild(0).getChild(4).getText() +") (type: [ classType="+
                        ctx.getChild(0).getChild(0).getText() + ", isDefiend: False]";
                items.get(hashNumber).put("Field_" + ctx.getChild(0).getChild(4).getText(),tempStr);
            }else {
                boolean isDefined = false;
                for(int i = 0; i < numberOfimportedClasses; i++){
                    if(ctx.getChild(0).getChild(0).getText().equals(importedClasses[i])){
                        isDefined = true;
                    }
                }
                tempStr = "ClassField ";
                tempStr += "(name: " + ctx.getChild(0).getChild(1).getText() +") (type: [ classType="+
                        ctx.getChild(0).getChild(0).getText() + ", isDefiend:" + isDefined + "]";
                items.get(hashNumber).put("Field_" + ctx.getChild(0).getChild(1).getText(),tempStr);
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
        blockStarts.add("---------" + ctx.getChild(2).getText() + ":" + ctx.start.getLine() + "---------");
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
        int camaCounter = 0;
        if(!ctx.getChild(counter).getText().equals(")")){
            tempStr += "[parameter list: ";
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
            tempStr += "]";
        }
        items.get(hashNumber).put("Method_" + ctx.getChild(2).getText(),tempStr);
        Hashtable<String, String> temp = new Hashtable<>();
        items.add(temp);
        hashNumber = items.size()-1;
        if(!ctx.getChild(counter).getText().equals(")")) {
            for (int i = 0, j = 0; i < 2 * camaCounter + 1; i++) {
                if (ctx.getChild(counter).getChild(i).getText().equals(",")) {
                    continue;
                }
                tempStr = "Parameter (name:" + ctx.getChild(counter).getChild(i).getChild(1).getText() + ") (type:" + ctx.getChild(counter).getChild(i).getChild(0).getText() + ") (index: " + (j + 1) + ")";
                items.get(hashNumber).put("Field_" + ctx.getChild(counter).getChild(i).getChild(1).getText(), tempStr);
                j++;
            }
        }
    }


    @Override
    public void exitMethodDec(jythonParser.MethodDecContext ctx) {
        hashNumber = 1;
    }

    @Override
    public void enterConstructor(jythonParser.ConstructorContext ctx) {
        blockStarts.add("---------" + ctx.getChild(1).getText() + ":" + ctx.start.getLine() + "---------");
        if(items.size() == 1){
            Hashtable<String, String> temp = new Hashtable<>();
            items.add(temp);
            hashNumber++;
        }
        String tempStr = "Constructor (name : " + ctx.getChild(1).getText() + ") [parameter list: " ;
        int counter = 3;
        int camaCounter = 0;
        if(!ctx.getChild(counter).getText().equals(")")){
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
        }
        tempStr += "]";
        items.get(hashNumber).put("Constructor_" + ctx.getChild(1).getText(),tempStr);
        Hashtable<String, String> temp = new Hashtable<>();
        items.add(temp);
        hashNumber = items.size()-1;
        if(!ctx.getChild(counter).getText().equals(")")) {
            for (int i = 0, j = 0; i < 2 * camaCounter + 1; i++) {
                if (ctx.getChild(counter).getChild(i).getText().equals(",")) {
                    continue;
                }
                tempStr = "Parameter (name:" + ctx.getChild(counter).getChild(i).getChild(1).getText() + ") (type:" + ctx.getChild(counter).getChild(i).getChild(0).getText() + ") (index: " + (j + 1) + ")";
                items.get(hashNumber).put("Field_" + ctx.getChild(counter).getChild(i).getChild(1).getText(), tempStr);
                j++;
            }
        }
    }
    @Override
    public void exitConstructor(jythonParser.ConstructorContext ctx) {
        hashNumber = 1;
    }

    @Override
    public void enterParameter(jythonParser.ParameterContext ctx) {

    }

    @Override
    public void exitParameter(jythonParser.ParameterContext ctx) {

    }

    @Override
    public void enterStatement(jythonParser.StatementContext ctx){
        //tu input if(1) gozashte ghalate?
//        System.out.println(ctx.getParent().getClass().getName());
//        System.out.println(ctx.getText());
//        System.out.println("__________");
        if(!ctx.getChild(0).getChild(0).getText().equals("if") &&
                !ctx.getChild(0).getChild(0).getText().equals("while")){
            if(ctx.getChild(0).getClass().getName().contains("VarDec")) {
                boolean isDefined = false;
                for(int i = 0; i < numberOfimportedClasses; i++){
                    if(ctx.getChild(0).getChild(0).getText().equals(importedClasses[i])){
                        isDefined = true;
                    }
                }
                if(ctx.getChild(0).getChild(0).getText().equals("int")||ctx.getChild(0).getChild(0).getText().equals("string")
                        ||ctx.getChild(0).getChild(0).getText().equals("float")||ctx.getChild(0).getChild(0).getText().equals("bool"))
                    isDefined = true;
                String tempStr = "MethodField (name: " + ctx.getChild(0).getChild(1).getText() +
                        ") (type: [classtyped= "+ ctx.getChild(0).getChild(0).getText()  +", isDefiend:" + isDefined + "])";
                items.get(hashNumber).put("Field_" + ctx.getChild(0).getChild(1).getText(),tempStr);
            }else if(ctx.getChild(0).getClass().getName().contains("Assignment") && ctx.getChild(0).getChild(0).getClass().getName().contains("VarDec")){
                boolean isDefined = false;
                for(int i = 0; i < numberOfimportedClasses; i++){
                    if(ctx.getChild(0).getChild(0).getText().equals(importedClasses[i])){
                        isDefined = true;
                    }
                }
                if(ctx.getChild(0).getChild(0).getText().equals("int")||ctx.getChild(0).getChild(0).getText().equals("string")
                        ||ctx.getChild(0).getChild(0).getText().equals("float")||ctx.getChild(0).getChild(0).getText().equals("bool"))
                    isDefined = true;
                String tempStr = "MethodField (name: " + ctx.getChild(0).getChild(0).getChild(1).getText() +
                        ") (type: [classtyped= "+ ctx.getChild(0).getChild(0).getChild(0).getText()  +", isDefiend:" + isDefined + "])";
                items.get(hashNumber).put("Field_" + ctx.getChild(0).getChild(0).getChild(1).getText(),tempStr);

            }
        }
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
        blockStarts.add("---------if:" + ctx.start.getLine() + "---------");
        Hashtable<String, String> temp = new Hashtable<>();
        items.add(temp);
        previousHashNum = hashNumber;
        hashNumber = items.size()-1;
    }

    @Override
    public void exitIf_statment(jythonParser.If_statmentContext ctx) {
        hashNumber = previousHashNum;
        previousHashNum--;
    }

    @Override
    public void enterWhile_statment(jythonParser.While_statmentContext ctx) {
        blockStarts.add("---------while:" + ctx.start.getLine() + "---------");
        Hashtable<String, String> temp = new Hashtable<>();
        items.add(temp);
        previousHashNum = hashNumber;
        hashNumber = items.size()-1;
    }

    @Override
    public void exitWhile_statment(jythonParser.While_statmentContext ctx) {
        hashNumber = previousHashNum;
        previousHashNum--;
    }

    @Override
    public void enterIf_else_statment(jythonParser.If_else_statmentContext ctx) {
        blockStarts.add("---------if_else:" + ctx.start.getLine() + "---------");
        Hashtable<String, String> temp = new Hashtable<>();
        items.add(temp);
        previousHashNum = hashNumber;
        hashNumber = items.size()-1;
    }

    @Override
    public void exitIf_else_statment(jythonParser.If_else_statmentContext ctx) {
        hashNumber = previousHashNum;
        previousHashNum--;

    }

    @Override
    public void enterPrint_statment(jythonParser.Print_statmentContext ctx) {

    }

    @Override
    public void exitPrint_statment(jythonParser.Print_statmentContext ctx) {

    }

    @Override
    public void enterFor_statment(jythonParser.For_statmentContext ctx) {
        blockStarts.add("---------for:" + ctx.start.getLine() + "---------");
        Hashtable<String, String> temp = new Hashtable<>();
        items.add(temp);
        previousHashNum = hashNumber;
        hashNumber = items.size()-1;

    }

    @Override
    public void exitFor_statment(jythonParser.For_statmentContext ctx) {
        hashNumber = previousHashNum;
        previousHashNum--;

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

    public void printItem(){
        for(int i = 0; i < items.size(); i++) {
            String itemStr = "";
            for (Map.Entry<String, String> entry : items.get(i).entrySet()) {
                itemStr += "Key = " + entry.getKey() + " | Value = " + entry.getValue() + "\n";
            }
            System.out.println(blockStarts.get(i));
            System.out.println(itemStr);
            System.out.println("==========================================================================================");
        }

    }
}