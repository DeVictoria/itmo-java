
package markup;


public class Text implements tooMarkdown {
    String text;

    Text(String text) {
        this.text = text;
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(text);
    }

    @Override
    public void toTex(StringBuilder sb) {
        sb.append(text);
    }
}

