package io.github.saikg.leetcode.s562;

import java.util.Arrays;

public class Solution {
    public int longestLine(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        // v, h, d, a
        int ans = 0;
        int[][][] dp = new int[m][n][4];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] != 1) {
                    continue;
                }

                Arrays.fill(dp[i][j], 1);
                if (i > 0)
                    dp[i][j][0] += dp[i-1][j][0];
                if (j > 0)
                    dp[i][j][1] += dp[i][j-1][1];
                if (i > 0 && j > 0)
                    dp[i][j][2] += dp[i-1][j-1][2];
                if (i > 0 && j != n-1)
                    dp[i][j][3] += dp[i-1][j+1][3];

                int max = Arrays.stream(dp[i][j]).max().getAsInt();
                ans = Math.max(max, ans);
            }
        }
        return ans;
    }
}