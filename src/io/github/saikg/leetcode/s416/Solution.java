package io.github.saikg.leetcode.s416;

import java.util.Arrays;

public class Solution {
    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 == 1) {
            return false;
        }

        int n = nums.length, subsetSum = sum / 2;
        boolean[] dp = new boolean[subsetSum + 1];
        dp[0] = true;
        for (int num : nums) {
            boolean[] updated = new boolean[subsetSum + 1];
            for (int j = 0; j <= subsetSum; j++) {
                if (num > j) {
                    updated[j] = dp[j];
                } else {
                    updated[j] = dp[j] || dp[j - num];
                }
            }
            dp = updated;
        }
        return dp[subsetSum];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 2, 4, 5};
        boolean ans = solution.canPartition(nums);
        System.out.println(ans);
    }
}