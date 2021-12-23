package io.github.saikg.leetcode.s560;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int subarraySum(int[] nums, int k) {
        int n = nums.length, sum = 0, ans = 0;
        Map<Integer, Integer> seen = new HashMap<>();
        seen.put(0, 1);
        for (int num: nums) {
            sum += num;
            ans += seen.getOrDefault(sum - k, 0);
            seen.put(sum, seen.getOrDefault(sum, 0) + 1);
        }
        return ans;
    }
}