package io.github.saikg.leetcode.s975;

import java.util.*;

public class Solution {
    public int oddEvenJumps(int[] arr) {
        int[] nextHigher = computeNextHigher(arr);
        int[] nextLower = computeNextLower(arr);

        int n = arr.length;

        int[][] dp = new int[n][2];
        dp[n-1][0] = 1;
        dp[n-1][1] = 1;

        int goodIndices = 1;
        for (int i = n-2; i >= 0; i--) {

            if (nextLower[i] != -1)
                dp[i][0] = dp[nextLower[i]][1];

            if (nextHigher[i] != -1)
                dp[i][1] = dp[nextHigher[i]][0];

            if (dp[i][1] == 1) {
                goodIndices++;
            }
        }
        return goodIndices;
    }

    private int[] computeNextHigher(int[] arr) {
        int n = arr.length;
        int[] indices = new int[n];
        TreeMap<Integer, Integer> valToPos = new TreeMap<>();
        for (int i = n-1; i >= 0; i--) {
            Map.Entry<Integer, Integer> nh = valToPos.higherEntry(arr[i]-1);
            if (nh == null) {
                indices[i] = -1;
            } else {
                indices[i] = nh.getValue();
            }
            valToPos.put(arr[i], i);
        }
        return indices;
    }

    private int[] computeNextLower(int[] arr) {
        int n = arr.length;
        TreeMap<Integer, Integer> valToPos = new TreeMap<>();
        int[] indices = new int[n];
        for (int i = n-1; i >= 0; i--) {
            Map.Entry<Integer, Integer> nl = valToPos.lowerEntry(arr[i]+1);
            if (nl == null) {
                indices[i] = -1;
            } else {
                indices[i] = nl.getValue();
            }
            valToPos.put(arr[i], i);
        }
        return indices;
    }
}