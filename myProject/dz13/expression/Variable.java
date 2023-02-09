package dz13.expression;

import expression.Exep;

import java.util.Objects;

//переменная
public class Variable implements Exep {
    String VariableName;
    public Variable(String VariableName){
        this.VariableName = VariableName;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        if (Objects.equals(VariableName, "x")){
            return x;
        }else{
            return Objects.equals(VariableName, "y") ? y:z;
        }
    }

    @Override
    public String toString(){
        return String.valueOf(VariableName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Variable variable)) return false;
        return Objects.equals(VariableName, variable.VariableName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(VariableName);
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
