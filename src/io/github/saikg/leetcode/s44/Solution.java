package io.github.saikg.leetcode.s44;

public class Solution {

    Boolean[][] dp;

    public boolean isMatch(String s, String p) {
        s = s.replaceAll("[*]+", "*");
        p = p.replaceAll("[*]+", "*");

        int n = s.length();
        int m = p.length();
        dp = new Boolean[n][m];
        return isMatch(s, p, 0, 0);
    }

    private boolean isMatch(String s, String p, int i, int j) {
        if (i == s.length() && j == p.length()) {
            return true;
        }

        if (j == p.length()) {
            return false;
        }

        if (i == s.length()) {
            while (j < p.length() && p.charAt(j) == '*') j++;
            return j == p.length();
        }

        if (dp[i][j] != null) {
            return dp[i][j];
        }

        char queryChar = p.charAt(j);
        switch (queryChar) {
            case '?':
                return dp[i][j] = isMatch(s, p, i+1, j+1);
            case '*':
                return dp[i][j] = isMatch(s, p, i+1, j+1) || isMatch(s, p, i+1, j) || isMatch(s, p, i, j+1);
            default:
                return dp[i][j] = (p.charAt(j) == s.charAt(i)) && isMatch(s, p, i+1, j+1);
        }
    }
}