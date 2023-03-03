package alg_sem2;

import java.util.Scanner;

import static java.lang.Math.pow;


public class Main {

    public static class SegmentSumTree {
        long[] tree;
        long[] add;
        private int lenM;

        public SegmentSumTree(long[] m) {
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
            for (int i = len - 2; i>= 0; i--) {
                tree[i] =tree[2 * i + 1] + tree[2 * i + 2];
            }
        }


        public void add(int l, int r, long v) {
            addgo(0, 0, lenM, l, r, v);
        }


        public void addgo(int v, int l, int r, int ql, int qr, long val) {
            if (qr <= l | ql >= r) {
                return;
            }
            if (ql <= l & r <= qr) {
                tree[v] = tree[v]^val;
                add[v] = add[v]^val;
                return;
            }
            push(v);
            int m = (l + r) / 2;
            addgo(2 * v + 1, l, m, ql, qr, val);
            addgo(2 * v + 2, m, r, ql, qr, val);
            tree[v] = tree[2 * v + 1]+ tree[2 * v + 2];
        }

        private void push(int v) {
            tree[2 * v + 1] ^= add[v];
            tree[2 * v + 2] ^= add[v];
            add[2 * v + 1] ^= add[v];
            add[2 * v + 2] ^= add[v];
            add[v] = 0;
        }

        public void seeTree() {
            for (int i =0; i<tree.length; i++) {
                System.out.print(tree[i]+" ");
            }
            System.out.print(" | ");
            for (int i =0; i<tree.length; i++) {
                System.out.print(add[i]+ " ");
            }
            System.out.println();
        }

        public long getSum(int l, int r) {
            return getSumgo(0, 0, lenM, l, r);
        }

        public long getSumgo(int v, int l, int r, int ql, int qr) {
            if (qr <= l | ql >= r) {
                return 0;
            }
            if (ql <= l & r <= qr) {
                return tree[v];
            }
            push(v);
            int m = (l + r) / 2;
            return getSumgo(2*v+1, l, m, ql, qr)+
                    getSumgo(2*v+2, m, r, ql, qr);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] m = new long[n];
        for (int i = 0; i < n; i++) {
            m[i] = sc.nextLong();
        }
        SegmentSumTree smt = new SegmentSumTree(m);
        int k = sc.nextInt();
        for (int i = 0; i < k; i++) {
            smt.seeTree();
            int s = sc.nextInt();
            if (s==1) {
                int l = sc.nextInt();
                int r = sc.nextInt();
                System.out.println(smt.getSum(l -1 , r));
            } else {
                int l = sc.nextInt();
                int r = sc.nextInt();
                long val = sc.nextLong();
                smt.add(l - 1, r , val);
            }
        }
        smt.seeTree();
        System.out.println(14^3);
        System.out.println(13^3);
    }

}