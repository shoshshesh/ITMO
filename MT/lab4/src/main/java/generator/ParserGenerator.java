package generator;

import grammar.Grammar;
import grammar.Rule;
import grammar.term.Code;
import grammar.term.NonTerminal;
import grammar.term.Term;
import grammar.term.Terminal;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ParserGenerator extends AbstractGenerator {

    private final ConstructorFIRST_FOLLOW cFF;

    public ParserGenerator(Grammar grammar) {
        super(grammar, grammar.getName() + "Parser");
        this.cFF = new ConstructorFIRST_FOLLOW(grammar);
        cFF.constructFIRST();
        cFF.constructFOLLOW();
        System.out.println(cFF);
    }

    @Override
    protected String generateImports() {
        return "";
    }

    @Override
    protected String generateFields() {
        return String.format("""
                    private %sLexicalAnalyzer lex;

                """, grammar.getName());
    }

    @Override
    protected String generateConstructors() {
        return "";
    }

    @Override
    protected String generateMethods() {
        StringBuilder methods = new StringBuilder();
        for (NonTerminal nT : grammar.getNonTerminals()) {
            methods.append(generateMethod(nT));
        }
        methods.append(String.format("""
                        public %s parse(String expression) {
                            this.lex = new %sLexicalAnalyzer(expression);
                            lex.nextToken();
                            return %s();
                        }

                    """,
                grammar.getStart().getClassName(),
                grammar.getName(),
                grammar.getStart().getName()
                )
        );
        methods.append(generateClasses());
        return methods.toString();
    }



    private String generateMethod(NonTerminal nT) {
        StringBuilder method = new StringBuilder(
                String.format("""
                            private %s %s(%s) throws ParseException {
                                %s %s_0 = new %s("%s");
                                switch (lex.curToken().getType()) {
                        """,
                        nT.getClassName(),
                        nT.getName(),
                        grammar.getHeritableAttrsSignature(nT),
                        nT.getClassName(),
                        nT.getName(),
                        nT.getClassName(),
                        nT.getName()
                )
        );
        Set<Terminal> possibleTerminals = cFF.getPossibleTerminals(nT);
        Map<String, StringBuilder> cases = new HashMap<>();
        for (Terminal terminal: possibleTerminals) {
            cases.put(
                    terminal.getName(),
                    new StringBuilder(
                            String.format("""
                                                case %s -> {
                                    """, terminal.getName()
                            )
                    )
            );
        }
        StringBuilder codeIfEmpty = new StringBuilder();
        for (Rule rule : grammar.getRulesOf(nT)) {
            Set<Terminal> terminals = cFF.first(rule.getRight());
            for (Terminal terminal : terminals) {
                if (terminal.getName().equals("EPS")) {
                    if (rule.getRight().size() == 2) {
                        Term code = rule.getRight().get(1);
                        if (code instanceof Code) {
                            codeIfEmpty.append(code);
                        } else {
                            throw new AssertionError("Empty rule of " + rule.getLeft().getName() + " can have only code term.");
                        }
                    }
                    continue;
                }
                StringBuilder curCase = cases.get(terminal.getName());
                int counter = 1;
                for (Term term : rule.getRight()) {
                    if (term instanceof Terminal) {
                        curCase.append(String.format(
                                        """
                                                                                if (!lex.curToken().getType().name().equals("%s")) {
                                                                                    throw new ParseException("Unexpected token: " + lex.curToken().getType().name() + ". Expected: %s");
                                                                                }
                                                                                String %s_%s = lex.curToken().getValue();
                                                                                %s_0.add(new Tree("%s"));
                                                                                lex.nextToken();
                                                                """,
                                        term.getName(),
                                        term.getName(),
                                        term.getName(),
                                        counter,
                                        nT.getName(),
                                        term.getName()
                                )
                        );
                        counter++;
                    } else if (term instanceof Code) {
                        curCase.append("\t\t\t\t").append(((Code) term).getCode()).append("\n");
                    } else {
                        curCase.append(String.format("""
                                                        %s %s_%s = %s(%s);
                                                        %s_0.add(%s_%s);
                                        """,
                                        ((NonTerminal) term).getClassName(),
                                        term.getName(),
                                        counter,
                                        term.getName(),
                                        ((NonTerminal) term).getHeritableAttrs(),
                                        nT.getName(),
                                        term.getName(),
                                        counter
                                )
                        );
                        counter++;
                    }
                }
            }
        }
        for (Map.Entry<String, StringBuilder> entry : cases.entrySet()) {
            entry.getValue().append("\t\t\t}\n");
            method.append(entry.getValue().toString());
        }
        method.append(String.format("""
                                    default -> throw new ParseException("Unexpected token: " + lex.curToken().getType().name());
                                }
                                if (%s_0.getChildren().size() == 0) {
                                    %s_0.add(new Tree("EPS"));
                                    %s
                                }
                                return %s_0;
                            }

                        """,
                        nT.getName(),
                        nT.getName(),
                        codeIfEmpty,
                        nT.getName()
                )
        );
        return method.toString();
    }

    private String generateClasses() {
        StringBuilder classes = new StringBuilder();
        for (NonTerminal nT : grammar.getNonTerminals()) {
            classes.append(
                    String.format("""
                            public class %s extends Tree {
                        """,
                            nT.getClassName()
                    )
            );
            classes.append("\t\t").append(grammar.getSynAttrsSignature(nT)).append("\n");
            classes.append(
                    String.format("""
                                    public %s(String node) {
                                        super(node);
                                    }
                                }

                            """,
                            nT.getClassName()
                    )
            );
        }
        return classes.toString();
    }
}