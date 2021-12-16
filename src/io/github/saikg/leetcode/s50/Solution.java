package io.github.saikg.leetcode.s50;

public class Solution {
    public double myPow(double x, int n) {
        long N = Math.abs(n);
        double ans = 1;
        x = (n < 0 ? 1.0 / x : x);
        while (N > 0) {
            if ((N & 1) == 1) {
                ans *= x;
            }
            x *= x;
            N /= 2;
        }
        return ans;
    }
}