package io.github.saikg.leetcode.s1;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> valToIndex = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (valToIndex.containsKey(target - nums[i])) {
                return new int[]{valToIndex.get(target - nums[i]), i};
            }
            valToIndex.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }
}