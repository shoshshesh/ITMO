package markup;

import java.util.List;

public class Strikeout extends AbstractMarkup implements Markup {
    private List<Markup> list;

    public Strikeout(List<Markup> list) {
        this.list = list;
    }

    @Override
    public void toMarkdown(StringBuilder stringBuilder) {
        // toMarkdown(stringBuilder, "~");
        stringBuilder.append(mask(getMarkdownText(list), "~", "~"));
    }

    @Override
    public void toHtml(StringBuilder stringBuilder) {
        stringBuilder.append(mask(getHtmlText(list), "<s>", "</s>"));
    }
}
