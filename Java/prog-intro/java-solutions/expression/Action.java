package expression;

public abstract class Action {
    private String sign;
    private final Expression part1;
    private final Expression part2;

    public Action(Expression part1, Expression part2) {
        this.part1 = part1;
        this.part2 = part2;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Expression getPart1() {
        return part1;
    }

    public Expression getPart2() {
        return part2;
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
        if(o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Action action = (Action) o;
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
