package io.github.saikg.leetcode.s877;

public class Solution {
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        // maximum points that can be scored using piles[i....j]
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = piles[i];
        }
        return stoneGame(piles, 0, piles.length - 1, 0, 0) > 0;
    }

    private int stoneGame(int[] piles, int start, int end, int score1, int score2) {
        if (start == end) {
            return (score1 + piles[start] - score2);
        }

        int pickLeft = stoneGame(piles, start + 1, end, score2, score1 + piles[start]);
        int pickRight = stoneGame(piles, start, end - 1, score2, score1 + piles[end]);
        return Math.max(pickLeft, pickRight);
    }

    public static void main(String[] args) {

    }
}
