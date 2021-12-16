package io.github.saikg.leetcode.s69;

public class Solution {

    public int mySqrt(int x) {
        double guess = x / 2.0;
        double EPS = 1e-2;
        double curr = guess, prev = guess - 1;
        while (Math.abs(curr - prev) > EPS) {
            prev = curr;
            curr = 0.5 * (prev + x/prev);
        }
        return (int)curr;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int ans = solution.mySqrt(144);
        System.out.println("ans = " + ans);
    }
}
