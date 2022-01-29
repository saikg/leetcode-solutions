package io.github.saikg.leetcode.s85;

import java.util.Stack;

public class Solution {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = matrix[i][0] - '0';
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == '0') {
                    continue;
                }
                dp[i][j] = 1 + dp[i][j-1];
            }
        }

        int ans = 0;
        for (int col = 0; col < n; col++) {
            int[] lengths = new int[m];
            for (int row = 0; row < m; row++) {
                lengths[row] = dp[row][col];
            }
            ans = Math.max(Histogram.maxRectangle(lengths), ans);
        }
        return ans;
    }

    static class Histogram {

        public static int maxRectangle(int[] heights) {
            int height, width, maxArea = 0;

            Stack<Integer> indices = new Stack<>();
            indices.push(-1);
            for (int i = 0; i < heights.length; i++) {
                while (indices.peek() != -1 && heights[indices.peek()] > heights[i]) {
                    height = heights[indices.pop()];
                    width = i - indices.peek() - 1;
                    maxArea = Math.max(width * height, maxArea);
                }
                indices.push(i);
            }

            while (indices.peek() != -1) {
                height = heights[indices.pop()];
                width = heights.length - indices.peek() - 1;
                maxArea = Math.max(width * height, maxArea);
            }

            return maxArea;
        }

    }
}