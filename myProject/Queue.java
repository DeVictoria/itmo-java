import java.util.ArrayList;

public class Queue<O> {
    int size;
    int head = 0;
    int tail = 0;
    ArrayList<O> queue;

    public Queue(int size) {
        this.size = size;
        this.queue = new ArrayList<O>(size);
    }

    public Queue() {
        this.size = 100;
        this.queue = new ArrayList<O>(size);
    }

    public void push(O n) {
        queue.add(0, n);
        head++;
    }

    public void pop() {
        head--;
        O ans = queue.get(head);
        queue.remove(head);
    }

    public int size() {
        return head - tail;
    }

    public O top() {
        return queue.get(head - 1);
    }

    public void clear() {
        head = 0;
        queue.clear();
    }
}