import java.util.ArrayList;

public class Steck<O> {
    private int size;

    private int current = 0;

    private ArrayList<O> steck;

    public Steck(int size){
        this.size = size;
        this.steck = new ArrayList<O>(size);
    }
    public Steck(){
        this.size = 100;
        this.steck = new ArrayList<O>(size);
    }

    public void push(O n){
        steck.add(current, n);
        current++;
    }
    public int size(){
        return current;
    }
    public O pop(){
        current--;
        O ans = steck.get(current);
        steck.remove(current);
        return ans;
    }
    public O top(){
        return steck.get(current-1);
    }
    public void clear(){
        current=0;
        steck.clear();
    }
}