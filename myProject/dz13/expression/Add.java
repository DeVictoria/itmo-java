package dz13.expression;

import expression.Exep;
import expression.Operation;

//добавить
public class Add extends Operation {
    public Add(expression.Exep metod1, Exep metod2){
        super(metod1, metod2, "+");
    }
    @Override
    public int doOperation(int x, int y){
        return x + y;
    }

    @Override
    public int priority() {
        return 2;
    }

    @Override
    public boolean needBrackets() {
        return false;
    }
}
