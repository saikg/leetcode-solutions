package io.github.saikg.leetcode.s1043;

public class Solution {

    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n+1];
        for (int i = 0; i <= n; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 1; j <= k; j++) {
                if (i - j < 0) break;
                max = Math.max(arr[i-j], max);
                dp[i] = Math.max(dp[i], dp[i-j] + max * j);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int[] arr = {1,4,1,5,7,3,6,1,9,9,3};
        int k = 4;

        Solution solution = new Solution();
        int ans = solution.maxSumAfterPartitioning(arr, k);
        System.out.println("ans = " + ans);
    }
}
