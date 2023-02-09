package dz13.expression;

import expression.Exep;
import expression.Operation;

//умножить
public class Multiply extends Operation {
    public Multiply(expression.Exep metod1, Exep metod2){
        super(metod1, metod2, "*");
    }
    @Override
    public int priority() {
        return 3;
    }

    @Override
    public boolean needBrackets() {
        return false;
    }
    @Override
    public int doOperation(int x, int y){
        return x * y;
    }
}
