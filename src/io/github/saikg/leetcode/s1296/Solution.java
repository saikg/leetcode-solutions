package io.github.saikg.leetcode.s1296;

import java.util.TreeMap;

public class Solution {
    public boolean isPossibleDivide(int[] nums, int k) {
        int n = nums.length;
        if (n % k != 0) {
            return false;
        }

        TreeMap<Integer, Integer> counter = new TreeMap<>();
        for (int num: nums) {
            int v = counter.getOrDefault(num, 0);
            counter.put(num, v + 1);
        }

        int numSets = n / k;
        for (int _i = 0; _i < numSets; _i++) {
            int lowestKey = counter.firstKey();
            for (int i = lowestKey; i < k; i++) {
                int v = counter.getOrDefault(i, 0);
                if (v == 0) {
                    return false;
                }
                counter.put(i, v - 1);
                if (v == 1) {
                    counter.remove(i);
                }
            }
        }
        return true;
    }
}