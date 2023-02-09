package expression2;
//добавить
public class Add extends Operation{
    public Add(Exep metod1, Exep metod2){
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
