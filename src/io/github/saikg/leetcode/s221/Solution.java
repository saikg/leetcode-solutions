package io.github.saikg.leetcode.s221;

public class Solution {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] dp = new int[n+1];

        int ans = 0;
        for (int i = 1; i <= m; i++) {
            int prev = dp[0];
            for (int j = 1; j <= n; j++) {
                if (matrix[i-1][j-1] == '1') {
                    dp[j] = 1 + Math.min(prev, Math.min(dp[j], dp[j-1]));
                    ans = Math.max(ans, dp[j]*dp[j]);
                }
                prev = dp[j];
            }
        }
        return ans;
    }
}