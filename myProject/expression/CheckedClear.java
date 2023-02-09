package expression;

import static java.lang.Math.pow;

//делить
public class CheckedClear extends CheckedOperation {
    public CheckedClear(Exep metod1, Exep metod2){
        super(metod1, metod2, "clear");
    }
    @Override
    public int priority() {
        return 0;
    }

    @Override
    public boolean needBrackets() {
        return true;
    }
    @Override
    public int doOperation(int x, int y){
        int z = x>=9? (int)pow(2,31): -1;
        if (y % 32 >= 0) {
            return x &=z- (int) pow(2, y % 32) * (y % 32 == 31 ? -1 : 1)+(y % 32 == 31 ? +1 : 0);
        } else if (y%32<-1){
            return x &= z-(int) pow(2, (32+y%32));
        }else {
            return x &=z- (int) pow(2, (32+y%32))*(-1) +1;
        }
    }
}
