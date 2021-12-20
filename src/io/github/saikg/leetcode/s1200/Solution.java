package io.github.saikg.leetcode.s1200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;

        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < n-1; i++) {
            minDiff = Math.min(arr[i+1] - arr[i], minDiff);
        }

        List<List<Integer>> minDiffPairs = new ArrayList<>();
        for (int i = 0; i < n-1; i++) {
            if (arr[i+1] - arr[i] == minDiff) {
                minDiffPairs.add(Arrays.asList(arr[i], arr[i+1]));
            }
        }
        return minDiffPairs;
    }
}