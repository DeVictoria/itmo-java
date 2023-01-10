package expression.parser;

public interface CharSource {
    void setPos(int ind);

    boolean canBack();

    boolean hasNext();

    char next();

    char back();

    IllegalArgumentException error(String message);

}