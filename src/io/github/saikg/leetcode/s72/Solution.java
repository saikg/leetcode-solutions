package io.github.saikg.leetcode.s72;

import java.util.Arrays;

public class Solution {

    private static final int MAX_VALUE = 100001;
    private int[][] dp;

    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        dp = new int[m+1][n+1];
        for (int[] a: dp) {
            Arrays.fill(a, -1);
        }

        return minDistance(word1, word2, 0, 0);
    }

    private int minDistance(String word1, String word2, int pos1, int pos2) {
        if (pos2 == word2.length()) {
            return word1.length() - pos1;
        }
        if (pos1 == word1.length()) {
            return MAX_VALUE;
        }
        if (dp[pos1][pos2] != -1) {
            return dp[pos1][pos2];
        }

        if (word1.charAt(pos1) == word2.charAt(pos2)) {
            return dp[pos1][pos2] = minDistance(word1, word2, pos1 + 1, pos2 + 1);
        }
        int replace = minDistance(word1, word2, pos1 + 1, pos2 + 1);
        int delete = minDistance(word1, word2, pos1 + 1, pos2);
        int insert = minDistance(word1, word2, pos1, pos2 + 1);
        return dp[pos1][pos2] = 1 + Math.min(replace, Math.min(delete, insert));
    }
}