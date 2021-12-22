package io.github.saikg.leetcode.s91;

public class Solution {

    public int numDecodings(String s) {
        if (s.charAt(0) == '0') {
            return 0;
        }

        int n = s.length();
        int[] dp = new int[n];
        dp[0] = s.charAt(0) == '0' ? 0 : 1;

        for (int i = 1; i < n; i++) {
            int curr = s.charAt(i) - '0';
            if (isValid(curr)) {
                dp[i] += dp[i-1];
            }

            int prev = s.charAt(i-1) - '0';
            if (prev == 0) {
                continue;
            }

            int offset = prev * 10 + curr;
            if (isValid(offset)) {
                dp[i] += i > 1 ? dp[i-2] : 1;
            }
        }

        return dp[n-1];
    }

    private boolean isValid(int v) {
        return 1 <= v && v <= 26;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().numDecodings("21021"));
    }
}