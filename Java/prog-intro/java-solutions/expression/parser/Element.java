package expression.parser;

public class Element {
    ElementsType type;
    String value;

    public Element(ElementsType type, String value) {
        this.type = type;
        this.value = value;
    }
}
