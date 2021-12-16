package io.github.saikg.leetcode.s474;

import java.util.Arrays;

public class Solution {

    int[][][] dp;

    public int findMaxForm(String[] strs, int zeroes, int ones) {
        int n = strs.length;
        dp = new int[n][zeroes][ones];
        for (int[][] tda: dp) {
            for (int[] oda: tda) {
                Arrays.fill(oda, -1);
            }
        }

        int[] zeroCount = new int[n];
        int[] oneCount = new int[n];
        for (int i = 0; i < n; i++) {
            for (char ch: strs[i].toCharArray()) {
                int v = ch - '0';
                if (v == 0) zeroCount[i]++;
                else oneCount[i]++;
            }
        }
        return doubleKnapsack(0, zeroCount, oneCount, zeroes, ones);
    }

    private int doubleKnapsack(int idx, int[] zeroCount, int[] oneCount, int zeroes, int ones) {
        if (idx == zeroCount.length) {
            return 0;
        }

        if (dp[idx][zeroes][ones] != -1) {
            return dp[idx][zeroes][ones];
        }

        int take = 0, ignore = 0;
        if (zeroes >= zeroCount[idx] && ones >= oneCount[idx]) {
            take = 1 + doubleKnapsack(idx + 1, zeroCount, oneCount,
                    zeroes - zeroCount[idx], ones - oneCount[idx]);
        }
        ignore = doubleKnapsack(idx + 1, zeroCount, oneCount, zeroes, ones);
        return dp[idx][zeroes][ones] = Math.max(take, ignore);
    }
}