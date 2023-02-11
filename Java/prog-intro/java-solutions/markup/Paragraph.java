package markup;

import java.util.List;

public class Paragraph implements Markup {
    private List<Markup> list;

    public Paragraph(List<Markup> list) {
        this.list = list;
    }

    @Override
    public void toMarkdown(StringBuilder stringBuilder) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).toMarkdown(stringBuilder);
        }
    }

    @Override
    public void toHtml(StringBuilder stringBuilder) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).toHtml(stringBuilder);
        }
    }
}
