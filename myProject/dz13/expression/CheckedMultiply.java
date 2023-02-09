package dz13.expression;


import expression.Exep;

//умножить
public class CheckedMultiply extends CheckedOperation {
    public CheckedMultiply(Exep metod1, Exep metod2){
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
    public int doOperation(int x, int y) throws OverflowException {
        if(y!=0 & x!=0) {
            if ((x<0 & y<0 & x<Integer.MAX_VALUE/y) |
                    (x>0 & y>0 & x>Integer.MAX_VALUE/y) |
                    (x>0 & y<0 & x>(y==-1?Integer.MAX_VALUE:Integer.MIN_VALUE/y)) |
                    (x<0 & y>0 & x<(y==-1?Integer.MAX_VALUE:Integer.MIN_VALUE/y))){
                throw new OverflowException("overflow");
            }
        }
        return x * y;
    }
}
