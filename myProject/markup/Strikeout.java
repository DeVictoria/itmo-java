package markup;

import java.util.List;

public class Strikeout implements tooMarkdown {
    String start = "~";
    String st = "\\textst{";
    String end = "}";
    List<tooMarkdown> list;

    Strikeout(List<tooMarkdown> list) {
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
