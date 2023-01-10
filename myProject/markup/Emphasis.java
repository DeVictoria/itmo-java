package markup;

import java.util.List;

public class Emphasis implements tooMarkdown {
    String start = "*";
    String st = "\\emph{";
    String end = "}";
    List<tooMarkdown> list;

    Emphasis(List<tooMarkdown> list) {
        this.list = list;
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(start);
        for (tooMarkdown element : list) {
            element.toMarkdown(sb);
        }
        sb.append(start);
    }

    @Override
    public void toTex(StringBuilder sb) {
        sb.append(st);
        for (tooMarkdown element : list) {
            element.toTex(sb);
        }
        sb.append(end);
    }
}
