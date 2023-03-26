package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Rule {
    private final String N;
    private final List<String> alpha;

    public Rule(String N, List<String> alpha) {
        this.N = N;
        this.alpha = alpha;
    }

    public String getN() {
        return N;
    }

    public List<String> getAlpha() {
        return alpha;
    }

    /*
    Фабрика для генерации списка правил
     */
    public static List<Rule> createRules(String... rules) {
        final List<Rule> result = new ArrayList<>();
        for (int i = 0; i < rules.length; i += 2) {
            result.add(new Rule(rules[i], Arrays.stream(rules[i + 1].split(" ")).collect(Collectors.toList())));
        }
        return result;
    }
}
