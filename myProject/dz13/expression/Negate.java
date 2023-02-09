package dz13.expression;



import expression.Const;
import expression.Exep;
import expression.Variable;

import java.util.Objects;

public class Negate implements expression.Exep {
    expression.Exep metod;

    public Negate(Exep metod) {
        this.metod = metod;
    }


    @Override
    public int evaluate(int x) throws ExceptionExpression {
        return -metod.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) throws ExceptionExpression {
        return  -metod.evaluate(x, y, z);
    }

    @Override
    public String toString() {
        return "-("+metod.toString()+")";
    }
    @Override
    public String toMiniString() {
        if(metod.getClass()== Const.class || metod.getClass()== Variable.class || metod.getClass()== Negate.class){
            return "- " + metod.toMiniString();
        }
        else {
            return "-(" + metod.toMiniString()+")";
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Negate negative)) return false;
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