package expression;

public abstract class UnaryAction {
    private String sign;
    private final Expression part;

    public UnaryAction(Expression part1) {
        this.part = part1;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Expression getPart() {
        return part;
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
        UnaryAction action = (UnaryAction) o;
        return part.equals(action.part);
    }

    @Override
    public int hashCode() {
        return  39 * part.hashCode() + 25 * this.getClass().hashCode();
    }
}
