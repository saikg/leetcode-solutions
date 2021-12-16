package io.github.saikg.leetcode.s198;

import java.util.Arrays;

public class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return Arrays.stream(nums).max().getAsInt();
        }

        int[] dp = new int[n];
        dp[n-1] = nums[n-1];
        dp[n-2] = Math.max(nums[n-1], nums[n-2]);
        for (int i = n-3; i >= 0; i--) {
            dp[i] = Math.max(dp[i+2] + nums[i], dp[i+1]);
        }
        return dp[0];
    }
}
