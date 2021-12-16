package io.github.saikg.leetcode.s1937;

public class Solution {

    public long maxPoints(int[][] points) {
        return naiveDP(points);
    }

    // TLE: TC O(m * n), SC O(m * n)
    private long naiveDP(int[][] points) {
        int m = points.length;
        int n = points[0].length;

        long[][] dp = new long[m][n];
        for (int col = 0; col < n; col++) {
            dp[0][col] = points[0][col];
        }
        for (int row = 1; row < m; row++) {
            dp[row-1] = processPrevRow(dp[row-1]);
            for (int col = 0; col < n; col++) {
                dp[row][col] = points[row][col] + dp[row-1][col];
            }
        }
        long ans = Long.MIN_VALUE;
        for (int col = 0; col < n; col++) {
            ans = Math.max(ans, dp[m-1][col]);
        }
        return ans;
    }

    private long[] processPrevRow(long[] prev) {
        int n = prev.length;
        long[] updated = new long[n];
        int choice = 0;
        for (int i = 0; i < n; i++) {
            long choiceValue = prev[choice] - Math.abs(i - choice);
            if (prev[i] > choiceValue) {
                choice = i;
            }
            updated[i] = Math.max(prev[i], choiceValue);
        }
        choice = n-1;
        for (int i = n-1; i >= 0; i--) {
            long choiceValue = prev[choice] - Math.abs(i - choice);
            if (updated[i] > choiceValue) {
                choice = i;
            }
            updated[i] = Math.max(updated[i], choiceValue);
        }
        return updated;
    }

    public static void main(String[] args) {
        int[][] points = new int[][]{
                {1, 2, 3},
                {1, 5, 1},
                {3, 1, 1}
        };
        Solution solution = new Solution();
        System.out.println(solution.maxPoints(points));
    }
}

