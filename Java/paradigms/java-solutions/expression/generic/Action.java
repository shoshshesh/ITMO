package expression.generic;

public abstract class Action<T extends Number> {
    private String sign;
    private final CommonExpression<T> part1;
    private final CommonExpression<T> part2;
    private final EvaluateInterface<T> type;

    public Action(CommonExpression<T> part1, CommonExpression<T> part2, EvaluateInterface<T> type) {
        this.part1 = part1;
        this.part2 = part2;
        this.type = type;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public CommonExpression<T> getPart1() {
        return part1;
    }

    public CommonExpression<T> getPart2() {
        return part2;
    }

    public EvaluateInterface<T> getType() {
        return type;
    }


    @Override
    public String toString() {
        return "(" + part1.toString() + " " + sign + " " + part2.toString() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Action<?> action = (Action<?>) o;
        if (!part1.equals(action.part1)) {
            return false;
        }
        return part2.equals(action.part2);
    }

    @Override
    public int hashCode() {
        return  39 * part1.hashCode() + 50 * part2.hashCode() + 25 * this.getClass().hashCode();
    }
}
