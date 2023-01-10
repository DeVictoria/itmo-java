package expression.parser;

public interface CharSource {
    void setPos(int ind);
    boolean canBack();
    boolean hasNext();
    char next();
    void back();
    IllegalArgumentException error(String message);

}
