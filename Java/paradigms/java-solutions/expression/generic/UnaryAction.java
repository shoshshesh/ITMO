package expression.generic;

public abstract class UnaryAction<T extends Number> {
    private String sign;
    private final CommonExpression<T> part;
    private final EvaluateInterface<T> type;

    public UnaryAction(CommonExpression<T> part1, EvaluateInterface<T> type) {
        this.part = part1;
        this.type = type;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public CommonExpression<T> getPart() {
        return part;
    }

    public EvaluateInterface<T> getType() {
        return type;
    }

    @Override
    public String toString() {
        return sign + part.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if(o == null || this.getClass() != o.getClass()) {
            return false;
        }
        UnaryAction<?> action = (UnaryAction<?>) o;
        return part.equals(action.part);
    }

    @Override
    public int hashCode() {
        return  39 * part.hashCode() + 25 * this.getClass().hashCode();
    }
}
