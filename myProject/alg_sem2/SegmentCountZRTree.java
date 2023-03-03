package alg_sem2;

import static java.lang.Math.pow;

public class SegmentCountZRTree {
    long[] tree;

    private int lenM;

    public SegmentCountZRTree(long[] m) {
        bildTree(m);
    }

    private void bildTree(long[] m) {
        int len = m.length - 1;
        int co = 0;
        while (len > 0) {
            len /= 2;
            co++;
        }
        len = (int) pow(2, co);
        lenM = len;
        this.tree = new long[len * 2 - 1];
        for (int i = len - 1; i <= len * 2 - 2; i++) {
            if ((i - len + 1) < m.length) {
                this.tree[i] = m[i - len + 1] == 0? 1: 0;
            } else {
                this.tree[i] = 0;
            }
        }
        for (int i = len - 2; i>= 0; i--) {
            if((tree[2 * i + 1] == tree[2 * i + 2]) &(tree[2 * i + 1]==1)) {
                tree[i] = tree[2 * i + 1] + tree[2 * i + 2];
            }
        }
    }


    public void set(int ind, long value) {
        int n = lenM - 1 + ind;
        tree[n] = value==0? 1:0;
        n = (n - 1) / 2;
        while (true) {
            tree[n] = tree[2 * n + 1] + tree[2 * n + 2];
            if(n==0){
                break;
            }
            n = (n - 1) / 2;
        }
    }

    public void seeTree() {
        for (long l : tree) {
            System.out.print(l + " ");
        }
        System.out.println();
    }

    public long getCountZ(int ml, int mr) {
        int l = lenM - 1 + ml;
        int r = lenM - 2 + mr;
        long sum = 0;
        int ind =0;
        while (l <= r) {
            if(l==r){
                ind = 1;
            }
            if (l % 2 == 0) {
                sum += tree[l];
                l = l / 2;
            } else {
                l = (l - 1) / 2;
            }
            if (r % 2 == 1) {
                sum += tree[r];
                r = r / 2 - 1;
            } else {
                r = (r - 1) / 2;
            }
            if(ind == 1){
                break;
            }
        }
        return sum;
    }
}
