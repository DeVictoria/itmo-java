package expression2;
//вычитать
public class Subtract extends Operation{
    public Subtract(Exep metod1, Exep metod2) {
        super(metod1, metod2, "-");
    }
    @Override
    public int doOperation(int x, int y){
        return x - y;
    }
    @Override
    public int priority() {
        return 2;
    }

    @Override
    public boolean needBrackets() {
        return true;
    }
}
