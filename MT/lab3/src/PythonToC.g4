// Вариант - 3. Перевод с Python на C.
// Грамматика для трансляции Python кода в C код.
// Поддерживаемые типы:
// 1) int   -> int
// 2) float -> double
// Поддерживаемые команды (на примерах):
// 1) a = int(float(input())) -- input() работает только в присваивании, только с преобразованием к поддерживаемым типам
//                               и без других операций в выражении.
// 2) b = 5 + (a - 0.725) * float(a) / 3
// 3) print(a + b - 3 * 57.5 / (a - b))
// 4) if
// 5) while

grammar PythonToC;

@header {
    import java.util.*;
}

program returns [StringBuilder cProgram]
    @init {
        Map<String, String> typeOfVars = new HashMap<>();
        StringBuilder result = new StringBuilder();
        $cProgram = result.append("\nint main() {\n");
        String tabs = "\t";
    }
    @after {
        for (Map.Entry<String, String> varType : typeOfVars.entrySet()) {
            result.insert(0, varType.getValue() + " " + varType.getKey() + ";\n");
        }
        result.insert(0, "#include <stdio.h>\n\n");
        result.append("}");
    }
    : commands[typeOfVars, result, tabs] spaces EOF {
        result.append($commands.toC);
    }
    ;

commands [Map<String, String> typeOfVars, StringBuilder result, String tabs] returns [String toC]
    : command[typeOfVars, result, tabs] commands[typeOfVars, result, tabs] {
        $toC = tabs + $command.toC + "\n" + $commands.toC;
    }
    | {
        $toC = "";
    }
    ;

inner_commands [Map<String, String> typeOfVars, StringBuilder result, String tabs] returns [String toC]
    : tabulation command[typeOfVars, result, tabs] inner_commands[typeOfVars, result, tabs] {
        $toC = $tabulation.text + tabs + $command.toC + "\n" + $inner_commands.toC;
    }
    | {
        $toC = "";
    }
    ;

tabulation: '    '+;

command [Map<String, String> typeOfVars, StringBuilder result, String tabs] returns [String toC]
    : assignment[typeOfVars, result, tabs] {
        $toC = $assignment.toC;
    }
    | print[typeOfVars, result, tabs] {
        $toC = $print.toC;
    }
    | if_rule[typeOfVars, result, tabs] {
        $toC = $if_rule.toC;
    }
    | while_rule[typeOfVars, result, tabs] {
        $toC = $while_rule.toC;
    }
    ;

while_rule [Map<String, String> typeOfVars, StringBuilder result, String tabs] returns [String toC]
    : WHILE spaces expr[typeOfVars] spaces COLON inner_commands[typeOfVars, result, tabs] {
        $toC = "while (" + $expr.toC + ") {\n" + $inner_commands.toC + tabs + "}";
    }
    ;

if_rule [Map<String, String> typeOfVars, StringBuilder result, String tabs] returns [String toC]
    : IF spaces expr[typeOfVars] spaces COLON inner_commands[typeOfVars, result, tabs] elif[typeOfVars, result, tabs] else_rule[typeOfVars, result, tabs] {
        $toC = "if (" + $expr.toC + ") {\n" + $inner_commands.toC + tabs + "} " + $elif.toC + $else_rule.toC;
    }
    ;

else_rule [Map<String, String> typeOfVars, StringBuilder result, String tabs] returns [String toC]
    : ELSE spaces COLON spaces inner_commands[typeOfVars, result, tabs] {
        $toC = "else {\n" + $inner_commands.toC + tabs + "}";
    }
    | {
        $toC = "";
    }
    ;

elif [Map<String, String> typeOfVars, StringBuilder result, String tabs] returns [String toC]
    : ELIF spaces expr[typeOfVars] spaces COLON inner_commands[typeOfVars, result, tabs] elif[typeOfVars, result, tabs] {
        $toC = "else if (" + $expr.toC + ") {\n" + $inner_commands.toC + tabs + "} " + $elif.toC;
    }
    | {
        $toC = "";
    }
    ;

assignment [Map<String, String> typeOfVars, StringBuilder result, String tabs] returns [String toC]
    : variable spaces EQ spaces expr[typeOfVars] {
        $toC = $variable.text + " = " + $expr.toC + ";";
        if (!typeOfVars.containsKey($variable.text)) {
            typeOfVars.put($variable.text, $expr.typeC);
        }
    }
    | variable spaces EQ spaces input {
        $toC = "scanf(\"" + $input.formatType + "\", &" + $variable.text + ");";
        if (!typeOfVars.containsKey($variable.text)) {
            typeOfVars.put($variable.text, $input.typeC);
        }
    }
    ;

print [Map<String, String> typeOfVars, StringBuilder result, String tabs] returns [String toC]
    : PRINT spaces L_BRACKET spaces expr[typeOfVars] spaces R_BRACKET spaces {
        $toC = "printf(\"" + $expr.formatType + "\\n\", " + $expr.toC + ");";
    }
    ;

input returns [String typeC, String formatType]
    : (type spaces L_BRACKET spaces INPUT spaces R_BRACKET spaces | type spaces L_BRACKET spaces input spaces R_BRACKET spaces) {
        if ($type.typeC.equals("int")) {
            $typeC = "int";
            $formatType = "%d";
        } else {
            $typeC = "double";
            $formatType = "%lf";
        }
    }
    ;


expr [Map<String, String> typeOfVars] returns [String toC, String typeC, String formatType]
    : spaces atom[typeOfVars] spaces exprContinue[typeOfVars] spaces {
        if ($atom.typeC.equals("int") && $exprContinue.op.equals("/")) {
            $toC = "(double) " + $atom.toC + $exprContinue.toC;
        } else {
            $toC = $atom.toC + $exprContinue.toC;
        }
        if ($atom.typeC.equals("double") || $exprContinue.typeC.equals("double") || $exprContinue.op.equals("/")) {
            $typeC = "double";
            $formatType = "%lf";
        } else if ($atom.typeC.equals("_Bool") || $exprContinue.typeC.equals("_Bool")
            || $exprContinue.op.equals("==") || $exprContinue.op.equals("!=") || $exprContinue.op.equals("<")
            || $exprContinue.op.equals(">") || $exprContinue.op.equals("<=") || $exprContinue.op.equals(">=")
            || $exprContinue.op.equals("||") || $exprContinue.op.equals("&&")) {
            $typeC = "_Bool";
            $formatType = "%d";
        } else {
            $typeC = "int";
            $formatType = "%d";
        }
    }
    ;

atom [Map<String, String> typeOfVars] returns [String toC, String typeC, String formatType]
    : unaryOp spaces expr[typeOfVars] {
        $toC = $unaryOp.op + $expr.toC;
        $typeC = $expr.typeC;
        $formatType = $expr.formatType;
    }
    | number {
        $toC = $number.text;
        if ($number.text.contains(".")) {
            $typeC = "double";
            $formatType = "%lf";
        } else {
            $typeC = "int";
            $formatType = "%d";
        }
    }
    | bool {
        if ($bool.text.equals("True")) {
            $toC = "1";
        } else {
            $toC = "0";
        }
        $typeC = "_Bool";
        $formatType = "%d";
    }
    | variable {
        $toC = $variable.text;
        if (typeOfVars.get($variable.text).equals("double")) {
            $typeC = "double";
            $formatType = "%lf";
        } else if (typeOfVars.get($variable.text).equals("int")) {
            $typeC = "int";
            $formatType = "%d";
        } else {
            $typeC = "_Bool";
            $formatType = "%d";
        }
    }
    | type spaces L_BRACKET spaces expr[typeOfVars] spaces R_BRACKET {
        $toC = "(" + $type.typeC + ") " + "(" + $expr.toC + ")";
        $typeC = $type.typeC;
        $formatType = $type.formatType;
    }
    | L_BRACKET spaces expr[typeOfVars] spaces R_BRACKET {
        $toC = "(" + $expr.toC + ")";
        $typeC = $expr.typeC;
        $formatType = $expr.formatType;
    }
    ;

exprContinue [Map<String, String> typeOfVars] returns [String toC, String typeC, String op]
    : spaces binaryOp spaces expr[typeOfVars] spaces {
        $toC = " " + $binaryOp.op + " " + $expr.toC;
        $typeC = $expr.typeC;
        $op = $binaryOp.op;
    }
    | {
        $toC = "";
        $typeC = "";
        $op = "";
    }
    ;

binaryOp returns [String op]
    : PLUS   { $op = "+";  }
    | MINUS  { $op = "-";  }
    | MUL    { $op = "*";  }
    | DIV    { $op = "/";  }
    | OR     { $op = "||"; }
    | AND    { $op = "&&"; }
    | EQ EQ  { $op = "=="; }
    | '!' EQ { $op = "!="; }
    | LT     { $op = "<";  }
    | GT     { $op = ">";  }
    | LT EQ  { $op = "<="; }
    | GT EQ  { $op = ">="; }
    ;
unaryOp returns [String op]
    : MINUS { $op = "-"; }
    | NOT   { $op = "!"; }
    ;

number: FLOAT_N | INT_N;
variable: VAR;
bool: TRUE | FALSE;
type returns [String typeC, String formatType]
    : INT {
        $typeC = "int";
        $formatType = "%d";
    }
    | FLOAT {
        $typeC = "double";
        $formatType = "%lf";
    }
    | BOOL {
        $typeC = "_Bool";
        $formatType = "%d";
    }
    ;

spaces:         ' '*;
WHILE:          'while';
IF:             'if';
ELIF:           'elif';
ELSE:           'else';
COLON:          ':';
PLUS:           '+';
MINUS:          '-';
MUL:            '*';
DIV:            '/';
OR:             'or';
AND:            'and';
NOT:            'not';
L_BRACKET:      '(';
R_BRACKET:      ')';
EQ:             '=';
LT:             '<';
GT:             '>';
INT:            'int';
FLOAT:          'float';
BOOL:           'bool';
TRUE:           'True';
FALSE:          'False';
INPUT:          'input()';
PRINT:          'print';
VAR:            VAR_START_CHAR (VAR_START_CHAR | [0-9])*;
VAR_START_CHAR: ([a-z] | [A-Z] | '_');
INT_N:          ([0] | [1-9][0-9]*);
FLOAT_N:        INT_N ('.' [0-9]*);
WS: ([\n\r]+) -> skip;