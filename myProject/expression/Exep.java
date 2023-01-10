package expression;

public interface Exep extends Expression, ToMiniString, TripleExpression{
    int evaluate(int x);
    int evaluate(int x, int y, int z);
    String toString();

    boolean equals(Object o);

    int priority();
    boolean needBrackets();

}
