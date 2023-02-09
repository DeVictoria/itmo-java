package dz13.expression;




import expression.Exep;

import java.util.Objects;

public abstract class Operation implements expression.Exep {
    expression.Exep metod1;
    expression.Exep metod2;
    String tag;

    public Operation(expression.Exep metod1, expression.Exep metod2, String tag) {
        this.metod1 = metod1;
        this.metod2 = metod2;
        this.tag = tag;
    }


    @Override
    public String toString() {
        return "(" + metod1 + " " + tag + " " + metod2 + ")";
    }


    @Override
    public String toMiniString() {
        return takeBrackets(metod1.priority() < priority(), metod1)
                + ' ' + tag + ' ' + takeBrackets(metod2.priority() < priority()
                || (priority() == metod2.priority()
                && (this.needBrackets() || (metod2.needBrackets() && metod2.priority() > 2))), metod2);
    }

    private String takeBrackets(final boolean brackets, final Exep nowOperand) {
        if (brackets) {
            return '(' + nowOperand.toMiniString() + ')';
        } else {
            return nowOperand.toMiniString();
        }
    }

    @Override
    public int evaluate(int x, int y, int z) throws ExceptionExpression {
        return doOperation(metod1.evaluate(x, y, z), metod2.evaluate(x, y, z));
    }

    public int doOperation(int x, int y) {
        return 0;
    }

    @Override
    public boolean equals(Object metod) {
        if (this == metod) return true;
        if (!(metod instanceof Operation operation)) return false;
        return metod1.equals(operation.metod1) && metod2.equals(operation.metod2) && tag.equals(operation.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(metod1, metod2, tag);
    }

    public int evaluate(int result) throws ExceptionExpression {
        return doOperation(metod1.evaluate(result), metod2.evaluate(result));
    }
}