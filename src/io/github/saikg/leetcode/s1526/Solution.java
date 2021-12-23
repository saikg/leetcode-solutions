package io.github.saikg.leetcode.s1526;

public class Solution {
    public int minNumberOperations(int[] target) {
//        SegmentTree st = new SegmentTree(target);
//        return computeOperations(st, 0, target.length - 1, 0);
        return optimised(target);
    }

    private int computeOperations(SegmentTree tree, int start, int end, int base) {
        if (start > end) {
            return 0;
        }

        int minIdx = tree.query(1, start, end, 0, tree.n-1);
        int minVal = tree.base[minIdx];
        int left = computeOperations(tree, start, minIdx - 1, minVal);
        int right = computeOperations(tree, minIdx + 1, end, minVal);
        return (tree.base[minIdx] - base) + left + right;
    }

    private int optimised(int[] target) {
        int ans = target[0];
        int min = target[0], max = target[0];
        for (int i = 1; i < target.length; i++) {
            if (target[i-1] < target[i]) {
                ans += target[i] - target[i-1];
            }
        }
        return ans;
    }

    class SegmentTree {

        int n;
        int[] tree, base;

        SegmentTree(int[] base) {
            n = base.length;
            this.base = base;
            this.tree = new int[4*n];
            build(1, 0, n-1);
        }

        private void build(int v, int s, int e) {
            if (s == e) {
                tree[v] = s;
                return;
            }

            int m = (s + e) / 2;
            build(2 * v, s, m);
            build(2 * v + 1, m + 1, e);
            if (base[tree[2*v]] < base[tree[2*v+1]]) {
                tree[v] = tree[2*v];
            } else {
                tree[v] = tree[2*v+1];
            }
        }

        public int query(int v, int s, int e, int l, int r) {
            if (e < l || r < s) {
                return -1;
            }

            if (s <= l && r <= e) {
                return tree[v];
            }

            int m = (l + r) / 2;
            int left = query(2*v, s, e, l, m);
            int right = query(2*v + 1, s, e, m+1, r);
            if (left == -1) {
                return right;
            } else if (right == -1) {
                return left;
            }
            return base[left] < base[right] ? left : right;
        }
    }
}