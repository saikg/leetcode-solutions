package io.github.saikg.leetcode.s96;

public class Solution {
    public int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        for (int nodeCount = 1; nodeCount <= n; nodeCount++) {
            for (int left = 0; left < nodeCount; left++) {
                dp[nodeCount] += dp[left] * dp[nodeCount-1-left];
            }
        }
        return dp[n];
    }
}