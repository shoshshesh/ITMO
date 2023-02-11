package markup;

import java.util.List;

public class Emphasis extends AbstractMarkup implements Markup {
    private List<Markup> list;

    public Emphasis(List<Markup> list) {
        this.list = list;
    }

    @Override
    public void toMarkdown(StringBuilder stringBuilder) {
        stringBuilder.append(mask(getMarkdownText(list), "*", "*"));
    }

    @Override
    public void toHtml(StringBuilder stringBuilder) {
        stringBuilder.append(mask(getHtmlText(list), "<em>", "</em>"));
    }

}
