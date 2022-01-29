package io.github.saikg.leetcode.s1463;

import java.util.Arrays;

public class Solution {
    int m, n;
    int[][][] dp;

    public int cherryPickup(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        dp = new int[n][m][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        return solve(grid, 0, 0, m-1);
    }

    private int solve(int[][] grid, int row, int col1, int col2) {
        if (row == grid.length ||
                isOutsideRange(0, m-1, col1) ||
                isOutsideRange(0, m-1, col2)) {
            return 0;
        }

        if (dp[row][col1][col2] != -1) {
            return dp[row][col1][col2];
        }

        int ans = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                int ncol1 = col1 + i;
                int ncol2 = col2 + j;
                ans = Math.max(ans, solve(grid, row+1, ncol1, ncol2));
            }
        }

        ans += grid[row][col1];
        if (col1 != col2) {
            ans += grid[row][col2];
        }

        dp[row][col1][col2] = ans;
        return ans;
    }

    private boolean isOutsideRange(int low, int high, int val) {
        return low > val || val > high;
    }
}