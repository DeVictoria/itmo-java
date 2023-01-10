package expression;
//делить
public class Divide extends Operation{
    public Divide(Exep metod1, Exep metod2){
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
    public int doOperation(int x, int y){
        return x / y;
    }
}
