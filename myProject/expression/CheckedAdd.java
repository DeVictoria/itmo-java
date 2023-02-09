package expression;


//добавить
public class CheckedAdd extends CheckedOperation {
    public CheckedAdd(Exep metod1, Exep metod2){
        super(metod1, metod2, "+");
    }
    @Override
    public int doOperation(int x, int y) throws OverflowException {

        if(x>Integer.MAX_VALUE-(y<0?0:y)|x<Integer.MIN_VALUE-(y>0?0:y)){
            throw new OverflowException("overflow");
        }
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
