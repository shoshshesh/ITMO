package main;

import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        final Set<String> N = Set.of("S", "TNF", "TN", "P", "A", "A'", "M");
        final List<Rule> P = main.Rule.createRules(
                "S" , "TNF ( A ) ;",
                "TNF", "type_or_name P type_or_name",
                "TN", "TNF M",
                "P" , "* P",
                "P", "",
                "M", "",
                "M", "[ ]",
                "A", "TN A'",
                "A" , "",
                "A'" , ", TN A'",
                "A'" , ""
        );
        main.ConstructorFIRST_FOLLOW cFF = new main.ConstructorFIRST_FOLLOW(N, P, "S");
        cFF.constructFIRST();
        cFF.constructFOLLOW();
        System.out.println(cFF);
    }
}