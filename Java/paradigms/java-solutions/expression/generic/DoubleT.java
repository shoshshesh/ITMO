package expression.generic;

import expression.exceptions.DBZException;

public class DoubleT implements EvaluateInterface<Double> {

    @Override
    public Double getTypedValue(int x) {
        return (double) x;
    }

    @Override
    public Double getTypedValue(String x) {
        return Double.parseDouble(x);
    }

    @Override
    public Double add(Double x, Double y) {
        return x + y;
    }

    @Override
    public Double subtract(Double x, Double y) {
        return x - y;
    }

    @Override
    public Double multiply(Double x, Double y) {
        return x * y;
    }

    @Override
    public Double divide(Double x, Double y) {
        return x / y;
    }

    @Override
    public Double negate(Double x) {
        return -x;
    }
}
