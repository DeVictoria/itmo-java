package alg_sem2;

import static java.lang.Math.max;
import static java.lang.Math.pow;

public class SegmentMaxTree {
    long[] tree;

    long[] add;

    private int lenM;

    public SegmentMaxTree(long[] m) {
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
        this.add = new long[len * 2 - 1];
        for (int i = len - 1; i <= len * 2 - 2; i++) {
            if ((i - len + 1) < m.length) {
                this.tree[i] = m[i - len + 1];
            } else {
                this.tree[i] = 0;
            }
        }
        for (int i = lenM - 2; i>= 0; i--) {
            if(tree[2 * i + 1]>=tree[2 * i + 2]) {
                tree[i] = tree[2 * i + 1];
            }else {
                tree[i] = tree[2 * i + 2];
            }
        }
    }

    public void add(int l, int r, long v) {
        addgo(0,0,lenM,l,r,v);
    }


    public void addgo(int v, int l, int r, int ql, int qr, long val) {
        if (qr <= l | ql >= r) {
            return;
        }
        if (ql <= l & r <= qr) {
            tree[v] += val;
            add[v] += val;
            return;
        }
        push(v);
        int m = (l + r) / 2;
        addgo(2 * v + 1, l, m, ql, qr, val);
        addgo(2 * v + 2, m, r, ql, qr, val);
        tree[v]=max(tree[2 * v + 1], tree[2 * v + 2]);
    }
    private void push(int v) {
        tree[2*v+1]+=add[v];
        tree[2*v+2]+=add[v];
        add[2*v+1]+=add[v];
        add[2*v+2]+=add[v];
        add[v]=0;
    }

    public void seeTree() {
        for (long l : tree) {
            System.out.print(l + " ");
        }
        System.out.println();
    }

    public long getMax(int ml, int mr) {
        int l = lenM - 1 + ml;
        int r = lenM - 1 + mr;
        long res;
        res=Long.MIN_VALUE;
        int ind =0;
        while (l <= r) {
            if(l==r){
                ind = 1;
            }
            if (l % 2 == 0) {
                if(res<tree[l]) {
                    res = tree[l];
                }
                l = l / 2;
            } else {
                l = (l - 1) / 2;
            }
            if (r % 2 == 1) {
                if(res<tree[r]) {
                    res = tree[r];
                }
                r = r / 2 - 1;
            } else {
                r = (r - 1) / 2;
            }
            if(ind == 1){
                break;
            }
        }

        return res;
    }
}
