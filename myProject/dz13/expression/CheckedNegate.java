package dz13.expression;

import java.util.Objects;

public class CheckedNegate implements Exep {
    Exep metod;

    public CheckedNegate(Exep metod) {
        this.metod = metod;
    }


    @Override
    public int evaluate(int x) throws ExceptionExpression {
        return doOperation(metod.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) throws ExceptionExpression {
        return doOperation(metod.evaluate(x, y, z));
    }

    private int doOperation(int x) throws OverflowException {
        if (x==Integer.MIN_VALUE) {
            throw new OverflowException("overflow");
        }
        return  -x;
    }

    @Override
    public String toString() {
        return "-("+metod.toString()+")";
    }
    @Override
    public String toMiniString() {
        if(metod.getClass()== Const.class || metod.getClass()== Variable.class || metod.getClass()== CheckedNegate.class){
            return "- " + metod.toMiniString();
        }
        else {
            return "-(" + metod.toMiniString()+")";
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CheckedNegate negative)) return false;
        return Objects.equals(metod, negative.metod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(metod);
    }

    @Override
    public int priority() {
        return 5;
    }

    @Override
    public boolean needBrackets() {
        return false;
    }

}