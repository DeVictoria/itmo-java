package dz13.expression;


public interface Exep extends ToMiniString, TripleExpression {
    int evaluate(int x) throws ExceptionExpression;
    int evaluate(int x, int y, int z) throws ExceptionExpression;
    String toString();

    boolean equals(Object o);

    int priority();
    boolean needBrackets();

}
