package dz13.expression;

import expression.Exep;

import java.util.Objects;

public class Const implements Exep {
    int constanta;

    public Const(int constanta) {
        this.constanta = constanta;
    }

    @Override
    public int evaluate(int x) {
        return constanta;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return constanta;
    }

    @Override
    public String toString() {
        return String.valueOf(constanta);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Const aConst)) return false;
        return constanta == aConst.constanta;
    }

    @Override
    public int hashCode() {
        return Objects.hash(constanta);
    }

    @Override
    public int priority() {
        return 6;
    }

    @Override
    public boolean needBrackets() {
        return false;
    }

}
