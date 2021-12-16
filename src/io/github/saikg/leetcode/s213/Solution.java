package io.github.saikg.leetcode.s213;

import java.util.Arrays;

public class Solution {
    private int[] dp;

    public int rob(int[] nums) {
        int n = nums.length;
        dp = new int[n];
        Arrays.fill(dp, -1);
        int ignoreLast = rob(nums, 0, n-1);
        Arrays.fill(dp, -1);
        int ignoreFirst = rob(nums, 1, n);
        return Math.max(ignoreFirst, ignoreLast);
    }

    private int rob(int[] nums, int j, int n) {
        if (j >= n) {
            return 0;
        }
        if (dp[j] != -1) {
            return dp[j];
        }
        return dp[j] = Math.max(
                nums[j] + rob(nums, j + 2, n),
                rob(nums, j + 1, n)
        );
    }
}
