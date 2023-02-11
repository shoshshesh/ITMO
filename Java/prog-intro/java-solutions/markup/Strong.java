package markup;

import java.util.List;

public class Strong extends AbstractMarkup implements Markup {
    private List<Markup> list;

    public Strong(List<Markup> list) {
        this.list = list;
    }
    @Override
    public void toMarkdown(StringBuilder stringBuilder) {
        stringBuilder.append(mask(getMarkdownText(list), "__", "__"));
    }

    @Override
    public void toHtml(StringBuilder stringBuilder) {
        stringBuilder.append(mask(getHtmlText(list), "<strong>", "</strong>"));
    }

}
