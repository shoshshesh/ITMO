package markup;

import java.util.List;

public abstract class AbstractMarkup {
    public static StringBuilder getMarkdownText(List<Markup> list) {
        StringBuilder temp = new StringBuilder();
        for (Markup markup : list) {
            markup.toMarkdown(temp);
        }
        return temp;
    }

    public static StringBuilder getHtmlText(List<Markup> list) {
        StringBuilder temp = new StringBuilder();
        for (Markup markup : list) {
            markup.toHtml(temp);
        }
        return temp;
    }

    public static StringBuilder mask(StringBuilder builder, String begin, String end) {
        return builder.insert(0, begin).append(end);
    }
}