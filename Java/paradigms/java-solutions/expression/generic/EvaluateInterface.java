package expression.generic;

public interface EvaluateInterface<T extends Number> {
    T getTypedValue(int x);
    T getTypedValue(String x);
    T add(T x, T y);
    T subtract(T x, T y);
    T multiply(T x, T y);
    T divide(T x, T y);
    T negate(T x);
}
