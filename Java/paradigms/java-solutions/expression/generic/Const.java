package expression.generic;

public class Const<T extends Number> implements CommonExpression<T> {
    private final T value;
    private final EvaluateInterface<T> type;

    public Const(T value, EvaluateInterface<T> type) {
        this.value = value;
        this.type = type;
    }

    @Override
    public T evaluate(int x) {
        return value;
    }

    public EvaluateInterface<T> getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Const<?> aConst = (Const<?>) o;
        return value.equals(aConst.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public T evaluate(int x, int y, int z) {
        return value;
    }
}
