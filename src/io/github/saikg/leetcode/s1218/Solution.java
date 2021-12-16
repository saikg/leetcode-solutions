package io.github.saikg.leetcode.s1218;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int longestSubsequence(int[] arr, int difference) {
        int n = arr.length;
        Map<Integer, Integer> dp = new HashMap<>();
        int ans = 1;
        for (int num: arr) {
            int p = dp.getOrDefault(num - difference, 0);
            dp.put(num, p+1);
            ans = Math.max(p+1, ans);
        }
        return ans;
    }
}