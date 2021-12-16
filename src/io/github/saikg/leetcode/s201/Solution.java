package io.github.saikg.leetcode.s201;

import java.util.Random;

public class Solution {
    public int rangeBitwiseAnd(int left, int right) {
        int ans = godLevel(left, right);
        int nans = optimised(left, right);
        if (ans != nans) {
            System.out.println("mismatch => ans = " + ans);
        }
        return ans;
    }

    // O(right - left) TLE
    private int linearScan(int left, int right) {
        int ans = left;
        for (int t = left; 0 < t &&  t <= right; t++) {
            ans = ans & t;
        }
        return ans;
    }

    // O(log (r-l))
    private int optimised(int left, int right) {
        int ans = left;
        int n = right - left;
        for (int s = 0; s < 31; s++) {
            int t = 1 << s;
            if (t > right) break;
            long p = neededToReset(left, t);
            if (p <= right && (left & t) != 0) {
                ans ^= t;
            }
        }
        return ans;
    }

    private long neededToReset(int n, int t) {
        long p = (long)n + (long)t;
        p = p - (p & (t - 1));
        return p;
    }

    // god level solution
    private int godLevel(int left, int right) {
        while (left < right) {
            right = right & (right - 1);
        }
        return left & right;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        for (int i = 0; i < 100; i++) {
            int right = Math.abs(new Random().nextInt()) + 1;
            int left = Math.abs(new Random().nextInt(right));
            int ans = solution.rangeBitwiseAnd(left, right);
            System.out.printf("f(%d, %d) = %d\n", left, right, ans);
        }
    }
}

