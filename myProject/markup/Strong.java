package markup;

import java.util.List;

public class Strong implements tooMarkdown {
    String start = "__";
    String st = "\\textbf{";
    String end = "}";
    List<tooMarkdown> list;

    Strong(List<tooMarkdown> list) {
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

    public void toTex(StringBuilder sb) {
        sb.append(st);
        for (tooMarkdown element : list) {
            element.toTex(sb);
        }
        sb.append(end);
    }
}
