package io.github.saikg.leetcode.s1074;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length, ans = 0;
        int[][] ps = computePrefixSum(matrix);
        Map<Integer, Integer> seen = new HashMap<>();
        for (int r1 = 1; r1 <= m; r1++) {
            for (int r2 = r1; r2 <= m; r2++) {
                seen.clear();
                seen.put(0, 1);
                for (int c = 1; c <= n; c++) {
                    int sum = ps[r2][c] - ps[r1-1][c];
                    ans += seen.getOrDefault(sum - target, 0);
                    seen.put(sum, seen.getOrDefault(sum, 0) + 1);
                }
            }
        }
        return ans;
    }

    private int[][] computePrefixSum(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] ps = new int[m+1][n+1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int r = i - 1;
                int c = j - 1;
                ps[i][j] = matrix[r][c] + ps[i-1][j] + ps[i][j-1] - ps[i-1][j-1];
            }
        }
        return ps;
    }
}