package dz13.expression;


import expression.Exep;
import expression.Operation;

import static java.lang.Math.pow;

//делить
public class Set extends Operation {
    public Set(Exep metod1, Exep metod2){
        super(metod1, metod2, "set");
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
    public int doOperation(int x, int y) {
        if (y % 32 >= 0) {
            return x |= (int) pow(2, y % 32) * (y % 32 == 31 ? -1 : 1)+(y % 32 == 31 ? -1 : 0);
        } else if (y%32<-1){
            return x |= (int) pow(2, (32+y%32));
        }else {
            return x |= (int) pow(2, (32+y%32))*(-1) -1;
        }
    }
}
