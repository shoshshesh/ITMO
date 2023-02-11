package expression;

public class UnaryMinus extends UnaryAction implements Expression, TripleExpression {

    public UnaryMinus(Expression value) {
        super(value);
        setSign("-");
    }

    @Override
    public int evaluate(int x) {
        return -getPart().evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        TripleExpression part = (TripleExpression) getPart();
        return -part.evaluate(x, y, z);
    }
}
