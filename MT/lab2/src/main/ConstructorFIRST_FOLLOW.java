package main;

import java.util.*;

public class ConstructorFIRST_FOLLOW {
    private final Set<String> N;
    private final List<Rule> P;
    private final Map<String, Set<String>> FIRST = new HashMap<>();
    private final Map<String, Set<String>> FOLLOW = new HashMap<>();
    private final String EPS = "EPS";
    private final String START;

    public ConstructorFIRST_FOLLOW(Set<String> N, List<Rule> P, String START) {
        this.N = N;
        this.P = P;
        this.START = START;
    }

    public void constructFIRST() {
        for (String A : N) {
            FIRST.put(A, new HashSet<>());
        }
        boolean changed = true;
        while (changed) {
            changed = false;
            for (Rule rule : P) {
                final String A = rule.getN();
                final List<String> alpha = rule.getAlpha();
                final Set<String> FIRST_A = FIRST.get(A);
                final int before = FIRST_A.size();
                FIRST_A.addAll(first(alpha));
                if (before != FIRST_A.size()) {
                    changed = true;
                }
            }
        }
    }

    public void constructFOLLOW() {
        for (String A : N) {
            FOLLOW.put(A, new HashSet<>());
        }
        FOLLOW.get(START).add("$");
        boolean changed = true;
        while (changed) {
            changed = false;
            for (Rule rule : P) {
                final List<String> alpha = new ArrayList<>(rule.getAlpha());
                while (alpha.size() > 0) {
                    final String B = alpha.remove(0);
                    if (!N.contains(B)) {
                        continue;
                    }
                    final Set<String> FOLLOW_B = FOLLOW.get(B);
                    final int before = FOLLOW_B.size();
                    FOLLOW_B.addAll(first(alpha));
                    if (FOLLOW_B.remove(EPS)) {
                        FOLLOW_B.addAll(FOLLOW.get(rule.getN()));
                    }
                    if (before != FOLLOW_B.size()) {
                        changed = true;
                    }
                }
            }
        }
    }

    public Set<String> first(List<String> alpha) {
        final Set<String> result = new HashSet<>();
        if (alpha.size() == 0) {
            result.add(EPS);
            return result;
        }
        final String term = alpha.get(0);
        if (term.equals("")) {
            result.add(EPS);
        } else if (N.contains(term)) {
            final Set<String> FIRST_A = FIRST.get(term);
            result.addAll(FIRST_A);
            if (result.remove(EPS)) {
                result.addAll(first(alpha.subList(1, alpha.size())));
            }
        } else {
            result.add(term);
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("FIRST:\n");
        for (Map.Entry<String, Set<String>> entry : FIRST.entrySet()) {
            result.append(entry.getKey()).append(" : ");
            for (String first : entry.getValue()) {
                result.append(first).append(" ");
            }
            result.append("\n");
        }
        result.append("\nFOLLOW:\n");
        for (Map.Entry<String, Set<String>> entry : FOLLOW.entrySet()) {
            result.append(entry.getKey()).append(" : ");
            for (String follow : entry.getValue()) {
                result.append(follow).append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }
}
