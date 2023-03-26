/*
  Грамматика грамматик для преобразования текстового описания LL(1)-грамматики в структурированный класс Grammar.
  Пустое правило - обязательно EPS.
  */

grammar GrammarOfGrammars;

@header {
import grammar.Grammar;
import grammar.Rule;
import grammar.term.Code;
import grammar.term.NonTerminal;
import grammar.term.Term;
import grammar.term.Terminal;
}

grammarOfGrammars returns[Grammar grammar]
@init {
    Grammar grammar = new Grammar();
    $grammar = grammar;
}
    : grammarName startName rules[grammar] {
        $grammar.setName($grammarName.nameGrammar);
        $grammar.setStart($startName.nameStart);
      }
      EOF
    ;

startName returns [String nameStart]
    : START name SEMICOLON {
        $nameStart = $name.text;
    }
    ;

grammarName returns[String nameGrammar]
    : GRAMMAR name SEMICOLON {
        $nameGrammar = $name.text;
      }
    ;

rules[Grammar grammar]: grammarRule[grammar]*;

grammarRule[Grammar grammar]
    : terminalRule[grammar]
    | nonTerminalRule[grammar]
    ;

terminalRule[Grammar grammar]
    : name COLON REGEXP SEMICOLON {
        $grammar.addTerminal($name.text, $REGEXP.text.substring(1, $REGEXP.text.length() - 1));
      }
    ;

name: RULE_NAME | TOKEN_NAME;

nonTerminalRule[Grammar grammar]
    : name heritableAttr? synAttr? COLON
    rightPart[grammar, new NonTerminal($name.text)]
    (OR rightPart[grammar, new NonTerminal($name.text)])*
    SEMICOLON {
        $grammar.addNonTerminal($name.text);
        try {
            $grammar.addHeritableAttrsSignature($name.text, $heritableAttr.attributes);
        } catch (NullPointerException ignored) {

        }
        try {
            String[] attrsArray = $synAttr.attributes.split(",");
            StringBuilder attrs = new StringBuilder();
            for (String attr : attrsArray) {
                attrs.append("public ").append(attr).append(";\n");
            }
            $grammar.addSynAttrsSignature($name.text, attrs.toString());
        } catch (NullPointerException ignored) {

        }
    }
    ;

rightPart[Grammar grammar, NonTerminal nT]
@init {
    List<Term> rightPart = new ArrayList<>();
}
    : (term {
        rightPart.add($term.t);
      })+ {
        $grammar.addRule($nT, new Rule($nT, rightPart));
      }
    ;

term returns [Term t]
    : RULE_NAME heritableAttr? {
        try {
            $t = new NonTerminal($RULE_NAME.text, $heritableAttr.attributes);
        } catch (NullPointerException ignored) {
            $t = new NonTerminal($RULE_NAME.text);
        }

      }
    | TOKEN_NAME {
        $t = new Terminal($TOKEN_NAME.text);
      }
    | CODE {
        $t = new Code($CODE.text);
      }
    ;

synAttr returns [String attributes]
    : RETURNS ATTRIBUTES {
        $attributes = $ATTRIBUTES.text.substring(1, $ATTRIBUTES.text.length() - 1);
    }
    ;

heritableAttr returns [String attributes]
    : ATTRIBUTES {
        $attributes = $ATTRIBUTES.text.substring(1, $ATTRIBUTES.text.length() - 1);
    }
    ;

GRAMMAR: 'grammar';
START: 'start';
RETURNS: 'returns';

TOKEN_NAME: [A-Z]+([_A-Z])*;
RULE_NAME: [a-z]+([_a-zA-Z])*;

OR: '|';
COLON: ':';
SEMICOLON: ';' ;
BRACKET_OPEN: '{';
BRACKET_CLOSE: '}';
SQUARE_OPEN: '[';
SQUARE_CLOSE: ']';
QUOTE: '"';


CODE: BRACKET_OPEN .*? BRACKET_CLOSE;
ATTRIBUTES: SQUARE_OPEN .+? SQUARE_CLOSE;
REGEXP: QUOTE .*? QUOTE;

WHITESPACE: [ \t\r\n]+ -> skip;
EPS: 'eps';