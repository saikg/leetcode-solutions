package io.github.saikg.leetcode.s338;

import java.util.Arrays;

public class Solution {

    public int[] countBits(int n) {
        int[] bits = new int[n+1];
        if (n == 0) {
            return bits;
        }

        bits[1] = 1;
        for (int i = 2; i <= n; i++) {
            bits[i] = 1 + bits[i & (i-1)];
        }
        return bits;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.countBits(1000)));
    }
}
