package dz13.expression;


import expression.Exep;

//делить
public class CheckedDivide extends CheckedOperation {
    public CheckedDivide(expression.Exep metod1, Exep metod2) {
        super(metod1, metod2, "/");
    }

    @Override
    public int priority() {
        return 3;
    }

    @Override
    public boolean needBrackets() {
        return true;
    }

    @Override
    public int doOperation(int x, int y) throws DivByZero, OverflowException {
        if (x==Integer.MIN_VALUE && y == -1) {
            throw new OverflowException("overflow");
        }
        if (y == 0) {
            throw new DivByZero("division by zero");
        }
        return x / y;
    }
}
