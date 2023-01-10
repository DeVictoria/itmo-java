package markup;

import java.util.List;

public class Paragraph {
    String start = "";
    String st = "";
    String end = "";
    List<tooMarkdown> list;

    Paragraph(List<tooMarkdown> list) {
        this.list = list;
    }

    public void toMarkdown(StringBuilder sb) {
        sb.append(start);
        for (tooMarkdown element : list) {
            element.toMarkdown(sb);
        }
        sb.append(start);
    }

    public void toTex(StringBuilder sb) {
        sb.append(st);
        for (tooMarkdown element : list) {
            element.toTex(sb);
        }
        sb.append(end);
    }
}
