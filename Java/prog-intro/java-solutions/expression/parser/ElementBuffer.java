package expression.parser;

import java.util.List;

public class ElementBuffer {
    private int pos;

    public List<Element> elements;

    public ElementBuffer(List<Element> elements) {
        this.elements = elements;
    }

    public Element next() {
        return elements.get(pos++);
    }

    public void back() {
        pos--;
    }

    public int getPos() {
        return pos;
    }

    public Element get(int index) {
        return elements.get(index);
    }
}
