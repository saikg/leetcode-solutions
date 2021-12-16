package io.github.saikg.leetcode.s1641;

import java.util.Arrays;

public class Solution {
    private int[][] dp;

    public int countVowelStrings(int n) {
        dp = new int[n+1][5];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return countVowelStrings(n, 0);
    }

    private int countVowelStrings(int n, int p) {
        if (n == 1) {
            return 5-p;
        }
        if (dp[n][p] != -1) {
            return dp[n][p];
        }
        int ans = 0;
        for (int i = 0; i < 5; i++) {
            ans += countVowelStrings(n-1, i);
        }
        dp[n][p] = ans;
        return ans;
    }
}
