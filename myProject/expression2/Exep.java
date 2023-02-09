package expression2;

public interface Exep {
    int evaluate(int x);
    int evaluate(int x, int y, int z);
    String toString();

    boolean equals(Object o);

    int priority();
    boolean needBrackets();
    public String toMiniString();

}
