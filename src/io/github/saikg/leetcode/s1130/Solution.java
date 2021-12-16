package io.github.saikg.leetcode.s1130;

import java.util.Arrays;

public class Solution {

    private int[][][] dp;

    public int mctFromLeafValues(int[] arr) {
        int n = arr.length;
        dp = new int[n][n][2];
        for (int[][] row: dp) {
            for (int[] field: row) {
                Arrays.fill(field, -1);
            }
        }

        int[] ans = mctFromLeafValues(arr, 0, n-1);
        return ans[1];
    }

    private int[] mctFromLeafValues(int[] arr, int low, int high) {
        if (low == high) {
            return new int[]{arr[low], 0};
        }

        if (dp[low][high][0] != -1) {
            return dp[low][high];
        }

        int n = arr.length;
        int minCost = Integer.MAX_VALUE;
        int[] ans = new int[2];
        for (int i = low; i < high; i++) {
            int[] left = mctFromLeafValues(arr, low, i);
            int[] right = mctFromLeafValues(arr, i+1, high);
            int max = Math.max(left[0], right[0]);
            int sum = left[1] + right[1] + left[0] * right[0];
            if (minCost > sum) {
                minCost = sum;
                ans[0] = max;
                ans[1] = sum;
            }
        }
        dp[low][high] = ans;
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {6, 2, 4};
        Solution solution = new Solution();
        int ans = solution.mctFromLeafValues(arr);
        System.out.println("ans = " + ans);
    }
}
