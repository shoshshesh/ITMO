package expression;

public class Divide extends Action implements Expression, TripleExpression {

    public Divide(Expression part1, Expression part2) {
        super(part1, part2);
        setSign("/");
    }

    @Override
    public int evaluate(int x) {
        return getPart1().evaluate(x) / getPart2().evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        TripleExpression part1 = (TripleExpression) getPart1();
        TripleExpression part2 = (TripleExpression) getPart2();
        return part1.evaluate(x, y, z) / part2.evaluate(x, y, z);
    }
}
