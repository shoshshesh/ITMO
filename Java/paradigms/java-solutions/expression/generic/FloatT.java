package expression.generic;

import expression.exceptions.DBZException;

public class FloatT implements EvaluateInterface<Float> {
    @Override
    public Float getTypedValue(int x) {
        return (float) x;
    }

    @Override
    public Float getTypedValue(String x) {
        return Float.parseFloat(x);
    }

    @Override
    public Float add(Float x, Float y) {
        return x + y;
    }

    @Override
    public Float subtract(Float x, Float y) {
        return x - y;
    }

    @Override
    public Float multiply(Float x, Float y) {
        return x * y;
    }

    @Override
    public Float divide(Float x, Float y) {
        return x / y;
    }

    @Override
    public Float negate(Float x) {
        return -x;
    }
}
