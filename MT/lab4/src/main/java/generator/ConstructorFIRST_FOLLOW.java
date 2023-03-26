package generator;

import grammar.Grammar;
import grammar.Rule;
import grammar.term.Code;
import grammar.term.NonTerminal;
import grammar.term.Term;
import grammar.term.Terminal;

import java.util.*;

public class ConstructorFIRST_FOLLOW {
    private final Set<NonTerminal> N;
    private final Set<Rule> P;
    private final Map<NonTerminal, Set<Terminal>> FIRST = new HashMap<>();
    private final Map<NonTerminal, Set<Terminal>> FOLLOW = new HashMap<>();
    private final Terminal EPS = new Terminal("EPS", "eps");
    private final Terminal _END = new Terminal("_END", "$");
    private final NonTerminal s;

    public ConstructorFIRST_FOLLOW(Grammar grammar) {
        this.N = grammar.getNonTerminals();
        this.P = grammar.getRules();
        this.s = grammar.getStart();
    }

    public void constructFIRST() {
        for (NonTerminal A : N) {
            FIRST.put(A, new HashSet<>());
        }
        boolean changed = true;
        while (changed) {
            changed = false;
            for (Rule rule : P) {
                final NonTerminal A = rule.getLeft();
                final List<Term> right = rule.getRight();
                final Set<Terminal> FIRST_A = FIRST.get(A);
                final int before = FIRST_A.size();
                FIRST_A.addAll(first(right));
                if (before != FIRST_A.size()) {
                    changed = true;
                }
            }
        }
    }

    public void constructFOLLOW() {
        for (NonTerminal A : N) {
            FOLLOW.put(A, new HashSet<>());
        }
        FOLLOW.get(s).add(_END);
        boolean changed = true;
        while (changed) {
            changed = false;
            for (Rule rule : P) {
                final List<Term> right = new ArrayList<>(rule.getRight());
                right.removeIf(term -> term instanceof Code);
                while (right.size() > 0) {
                    final Term B = right.remove(0);
                    if (B instanceof Terminal || B instanceof Code) {
                        continue;
                    }
                    final Set<Terminal> FOLLOW_B = FOLLOW.get((NonTerminal) B);
                    final int before = FOLLOW_B.size();
                    FOLLOW_B.addAll(first(right));
                    if (FOLLOW_B.remove(EPS)) {
                        FOLLOW_B.addAll(FOLLOW.get(rule.getLeft()));
                    }
                    if (before != FOLLOW_B.size()) {
                        changed = true;
                    }
                }
            }
        }
    }

    public Set<Terminal> getPossibleTerminals(NonTerminal nT) {
        Set<Terminal> result = new HashSet<>(FIRST.get(nT));
        if (result.contains(EPS)) {
            result.remove(EPS);
            result.addAll(FOLLOW.get(nT));
        }
        return result;
    }

    public Set<Terminal> first(List<Term> right) {
        final Set<Terminal> result = new HashSet<>();
        if (right.size() == 0) {
            result.add(EPS);
            return result;
        }
        final Term term = right.get(0);
        if (term instanceof NonTerminal) {
            final Set<Terminal> FIRST_A = FIRST.get(term);
            result.addAll(FIRST_A);
            if (result.remove(EPS)) {
                result.addAll(first(right.subList(1, right.size())));
            }
        } else if (term instanceof Terminal) {
            result.add((Terminal) term);
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("FIRST:\n");
        for (Map.Entry<NonTerminal, Set<Terminal>> entry : FIRST.entrySet()) {
            result.append(entry.getKey()).append(" : ");
            for (Terminal first : entry.getValue()) {
                result.append(first).append(" ");
            }
            result.append("\n");
        }
        result.append("\nFOLLOW:\n");
        for (Map.Entry<NonTerminal, Set<Terminal>> entry : FOLLOW.entrySet()) {
            result.append(entry.getKey()).append(" : ");
            for (Terminal follow : entry.getValue()) {
                result.append(follow).append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }
}