package grammar;

import grammar.term.NonTerminal;
import grammar.term.Terminal;

import java.util.*;

public class Grammar {
    private String name;
    private NonTerminal start;
    final private Set<Terminal> terminals;
    final private Set<NonTerminal> nonTerminals;
    final private Map<NonTerminal, Set<Rule>> mapRules;
    final private Map<NonTerminal, String> heritableAttrsSignatures;
    final private Map<NonTerminal, String> synAttrsSignatures;

    public Grammar() {
        terminals = new HashSet<>();
        nonTerminals = new HashSet<>();
        mapRules = new HashMap<>();
        heritableAttrsSignatures = new HashMap<>();
        synAttrsSignatures = new HashMap<>();
    }

    public Grammar(String name,
                   NonTerminal start,
                   Set<Terminal> terminals,
                   Set<NonTerminal> nonTerminals,
                   Map<NonTerminal, Set<Rule>> mapRules,
                   Map<NonTerminal, String> heritableAttrsSignatures,
                   Map<NonTerminal, String> synAttrsSignatures) {
        this.name = name;
        this.start = start;
        this.terminals = terminals;
        this.nonTerminals = nonTerminals;
        this.mapRules = mapRules;
        this.heritableAttrsSignatures = heritableAttrsSignatures;
        this.synAttrsSignatures = synAttrsSignatures;
    }

    public String getHeritableAttrsSignature(NonTerminal nT) {
        return heritableAttrsSignatures.getOrDefault(nT, "");
    }

    public String getSynAttrsSignature(NonTerminal nT) {
        return synAttrsSignatures.getOrDefault(nT, "");
    }

    public static String makeFirstCharUpperCase(String name) {
        StringBuilder className = new StringBuilder(name);
        className.setCharAt(0, Character.toUpperCase(className.charAt(0)));
        return className.toString();
    }

    public void setName(String name) {
        this.name = makeFirstCharUpperCase(name);
    }

    public void setStart(String name) {
        this.start = new NonTerminal(name);
    }

    public void addTerminal(String name, String regExpr) {
        terminals.add(new Terminal(name, regExpr));
    }

    public void addNonTerminal(String name) {
        nonTerminals.add(new NonTerminal(name));
    }

    public void addHeritableAttrsSignature(String name, String signature) {
        heritableAttrsSignatures.put(new NonTerminal(name), signature);
    }

    public void addSynAttrsSignature(String name, String signature) {
        synAttrsSignatures.put(new NonTerminal(name), signature);
    }

    public void addRule(NonTerminal nT, Rule rule) {
        mapRules.putIfAbsent(nT, new HashSet<>());
        mapRules.get(nT).add(rule);
    }

    public String getName() {
        return name;
    }

    public NonTerminal getStart() {
        return start;
    }

    public Set<Terminal> getTerminals() {
        return terminals;
    }

    public Set<NonTerminal> getNonTerminals() {
        return nonTerminals;
    }

    public Set<Rule> getRules() {
        Set<Rule> result = new HashSet<>();
        mapRules.values().forEach(result::addAll);
        return result;
    }

    public Set<Rule> getRulesOf(NonTerminal nT) {
        return mapRules.get(nT);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("NAME: ").append(name).append("\n=========================\n");
        result.append("START: ").append(start.getName()).append("\n=========================\n");
        result.append("TERMINALS:\n");
        for (Terminal terminal : terminals) {
            result.append(terminal.toString()).append("\n");
        }
        result.append("=========================\nNON_TERMINALS:\n");
        for (NonTerminal nT : nonTerminals) {
            result.append(nT.getName()).append(" ").append(getHeritableAttrsSignature(nT)).append(" ")
                    .append(getSynAttrsSignature(nT)).append("\n");
        }
        result.append("=========================\n");
        result.append("RULES:\n");
        for (NonTerminal nT : nonTerminals) {
            result.append(nT.getName()).append(" RULES:\n");
            for (Rule rule : getRulesOf(nT)) {
                result.append(rule.toString()).append("\n");
            }
            result.append("\n");
        }
        return result.toString();
    }
}
