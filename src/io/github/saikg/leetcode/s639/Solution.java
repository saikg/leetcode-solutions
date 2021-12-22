package io.github.saikg.leetcode.s639;

public class Solution {
    public int numDecodings(String s) {
        if (s.charAt(0) == '0') {
            return 0;
        }

        int n = s.length();
        int[] dp = new int[n];
        dp[0] = s.charAt(0) == '0' ? 0 : (s.charAt(0) == '*' ? 2 : 1);
        for (int i = 0; i < n; i++) {

        }
        return 1;
    }
}