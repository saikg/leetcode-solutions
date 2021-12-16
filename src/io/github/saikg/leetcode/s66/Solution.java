package io.github.saikg.leetcode.s66;

public class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for (int p = n - 1; p >= 0; p--) {
            if (digits[p] == 9) {
                digits[p] = 0;
            } else {
                digits[p]++;
                return digits;
            }
        }
        digits = new int[n+1];
        digits[0] = 1;
        return digits;
    }
}